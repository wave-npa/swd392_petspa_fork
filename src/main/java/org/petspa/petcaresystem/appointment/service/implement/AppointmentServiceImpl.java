package org.petspa.petcaresystem.appointment.service.implement;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.logging.Logger;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.petspa.petcaresystem.appointment.model.payload.Appointment;
import org.petspa.petcaresystem.appointment.model.request.CreateAppointmentRequestDTO;
import org.petspa.petcaresystem.appointment.model.response.AppointmentResponseDTO;
import org.petspa.petcaresystem.appointment.repository.AppointmentRepository;
import org.petspa.petcaresystem.appointment.service.AppointmentService;
import org.petspa.petcaresystem.authenuser.model.payload.AuthenUser;
import org.petspa.petcaresystem.authenuser.repository.AuthenUserRepository;
import org.petspa.petcaresystem.boarding.model.entity.BoardingAppointment;
import org.petspa.petcaresystem.boarding.repository.BoardingRepository;
import org.petspa.petcaresystem.boarding_detail.model.BoardingDetail;
import org.petspa.petcaresystem.boarding_detail.repository.BoardingDetailRepository;
import org.petspa.petcaresystem.config.JwtUtil;
import org.petspa.petcaresystem.doctor.model.Doctor;
import org.petspa.petcaresystem.doctor.repository.DoctorRepository;
import org.petspa.petcaresystem.enums.Option;
import org.petspa.petcaresystem.enums.ShelterStatus;
import org.petspa.petcaresystem.enums.Status;
import org.petspa.petcaresystem.order.model.UserOrder;
import org.petspa.petcaresystem.order.repository.OrdersRepository;
import org.petspa.petcaresystem.pet.model.entity.Pet;
import org.petspa.petcaresystem.pet.repository.PetRepository;
import org.petspa.petcaresystem.review.model.Review;
import org.petspa.petcaresystem.review.repository.ReviewRepository;
import org.petspa.petcaresystem.serviceAppointment.model.Services;
import org.petspa.petcaresystem.serviceAppointment.repository.ServicesRepository;
import org.petspa.petcaresystem.shelter.model.entity.Shelter;
import org.petspa.petcaresystem.shelter.repository.ShelterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    private static final String format_pattern = "yyyy-MM-dd HH:mm";
    private static final String logging_message = "An error occurred:";
//    private static final Logger logger = (Logger) LoggerFactory.getLogger(AppointmentService.class);

    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private AuthenUserRepository authenUserRepository;
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private PetRepository petRepository;
    @Autowired
    private OrdersRepository ordersRepository;
    @Autowired
    private ServicesRepository servicesRepository;
    @Autowired
    private ShelterRepository shelterRepository;
    @Autowired
    private BoardingRepository boardingRepository;
    @Autowired
    private BoardingDetailRepository boardingDetailRepository;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private HttpServletRequest request;

    @Override
    public Collection<Appointment> findAllAppointment() {
        return appointmentRepository.findAll();
    }

    @Override
    public Appointment findAppointmentById(Long appointmentId) {
        return appointmentRepository.findById(appointmentId).orElse(null);
    }

    @Override
    public AppointmentResponseDTO saveAppointment(CreateAppointmentRequestDTO appointment, Option option) {
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format_pattern);
        String timeStamp = localDateTime.format(formatter);
        String message = "Create new appointment successfully";
        int statusCode = HttpStatus.OK.value();
        HttpStatus statusValue = HttpStatus.OK;

        // model
        AuthenUser authenUser = new AuthenUser();
        Appointment appointmentSaveForGuess = new Appointment();
        Appointment appointmentSaveForUser = new Appointment();
        Review review = new Review();
        List<Doctor> bookedDoctors = new ArrayList<>();
        Pet pet = new Pet();
        List<Services> bookedService = new ArrayList<>();
        UserOrder userOrder = new UserOrder();
        BoardingAppointment boardingAppointment = new BoardingAppointment();
        Shelter shelter = new Shelter();
        BoardingDetail boardingDetail = new BoardingDetail();

        // get token extract userId
        HttpSession session = request.getSession();
        String token = (String) session.getAttribute("jwtToken");

        // ------------------------------handle and prepare data-------------------------------

        // doctor
        Doctor doctor = doctorRepository.findByDoctorId(appointment.getDoctorId());
        bookedDoctors.add(doctor);
        appointmentSaveForGuess.setBookedDoctor(bookedDoctors);
        appointmentSaveForUser.setBookedDoctor(bookedDoctors);

        // service
        Services services = servicesRepository.findByServiceId(appointment.getServiceId());
        bookedService.add(services);
        appointmentSaveForGuess.setBookedService(bookedService);
        appointmentSaveForUser.setBookedService(bookedService);

        // create date
        appointmentSaveForGuess.setCreate_date(LocalDate.from(localDateTime));
        appointmentSaveForUser.setCreate_date(LocalDate.from(localDateTime));

        // start time
        appointmentSaveForGuess.setStartTime(appointment.getStartTime());
        appointmentSaveForUser.setStartTime((appointment.getStartTime()));

        // end time
        appointmentSaveForGuess.setEndTime(null);
        appointmentSaveForUser.setEndTime(null);

        // status
        appointmentSaveForGuess.setStatus(Status.valueOf("INACTIVE"));
        appointmentSaveForUser.setStatus(Status.valueOf("INACTIVE"));

        // user order: get price, set price, set order date
        Long price = (long) servicesRepository.findByServiceId(appointment.getServiceId()).getPrice();
        userOrder.setPrice(price);
        userOrder.setUserOrderDate(localDateTime);

        // shelter: find available shelter
        shelter = shelterRepository.findByShelterStatus(ShelterStatus.EMPTY);

        // boarding: check shelter status, set boarding date, status, shelter id
        if(shelter.getStatus() == Status.ACTIVE) {
            boardingAppointment.setBoardingTime(localDateTime);
            boardingAppointment.setStatus(Status.ACTIVE);
            boardingAppointment.setShelter(shelter);
        }

        // boarding detail
        java.sql.Date boardingDetailDate = java.sql.Date.valueOf(localDateTime.toLocalDate());
        boardingDetail.setDate(boardingDetailDate);
        boardingDetail.setBoardingAppointment(boardingAppointment);
        boardingDetail.setStartTime(localDateTime);
        boardingDetail.setEndTime(null);
        boardingDetail.setStatus(Status.ACTIVE);

        try {

            // --------------------------------user logged in------------------------------------
            if (token != null) {
                // extract user id, find user by id
                Long userId = jwtUtil.extractUserId(token);
                authenUser = authenUserRepository.findByUserId(userId);

                // review: set user id, set review id for appointment
                review.setAuthor(authenUser);
                appointmentSaveForUser.setReview(review);

                // pet: find bet by user, set pet id for appointment
                pet = petRepository.findByOwner(authenUser);
                appointmentSaveForUser.setPet(pet);

                // order: set price, date, user id for user order
                userOrder.setPrice(price);
                userOrder.setUserOrderDate(localDateTime);
                userOrder.setCustomer(authenUser);

            } else {
                // ----------------------------user not login-----------------------------------

                // review
                appointmentSaveForGuess.setReview(review);

                // pet
                appointmentSaveForGuess.setPet(null);

                // order
                userOrder.setPrice(price);
                userOrder.setUserOrderDate(localDateTime);
                userOrder.setCustomer(null);
            }

            // --------------------------------------JPA RUN------------------------------------------------

            // review: set des, rating, status for review, create review, set review id for appointment
            review.setDescription(null);
            review.setRating(null);
            review.setStatus(Status.ACTIVE);
            reviewRepository.save(review);
            appointmentSaveForGuess.setReview(review);
            appointmentSaveForUser.setReview(review);

            // create order
            ordersRepository.save(userOrder);
            appointmentSaveForGuess.setUserOrder(userOrder);
            appointmentSaveForUser.setUserOrder(userOrder);

            // create boarding & boarding detail
            if(option == Option.YES) {
                boardingRepository.save(boardingAppointment);
                boardingDetailRepository.save(boardingDetail);
                appointmentSaveForGuess.setBoardingAppointment(boardingAppointment);
                appointmentSaveForUser.setBoardingAppointment(boardingAppointment);
            }

            // create appointment
            if (token == null) {
                appointmentRepository.save(appointmentSaveForGuess);
            } else {
                appointmentRepository.save(appointmentSaveForUser);
            }

        } catch (Exception e) {
//            logger.error("Error occurred during create appointment", e);
            message = "Something went wrong, server error!";
            statusCode = HttpStatus.INTERNAL_SERVER_ERROR.value();
            statusValue = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new AppointmentResponseDTO(message, timeStamp, statusCode, statusValue);
    }

    @Override
    public Appointment updateAppointment(Appointment appointment) {
        Appointment existingAppointment = appointmentRepository.findById(appointment.getAppointment_id()).orElse(null);
        if (appointment.getBookedDoctor().isEmpty()) {
            // automatically pick doctor based on their schedule
        } else {
            existingAppointment.setBookedDoctor(appointment.getBookedDoctor());
        }
        existingAppointment.setBookedService(appointment.getBookedService());
        existingAppointment.setCreate_date(LocalDate.now());
        existingAppointment.setStartTime(appointment.getStartTime());
        existingAppointment.setEndTime(appointment.getEndTime());
        existingAppointment.setPet(appointment.getPet());
        return appointmentRepository.save(existingAppointment);
    }

    @Override
    public Appointment deleteAppointment(Long appointmentId) {
        Appointment appointment = appointmentRepository.findById(appointmentId).orElse(null);
        appointment.setStatus(Status.INACTIVE);
        return appointmentRepository.save(appointment);
    }

}
