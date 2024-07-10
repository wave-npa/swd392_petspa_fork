package org.petspa.petcaresystem.appointment.service.implement;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.logging.Logger;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.petspa.petcaresystem.appointment.model.payload.Appointment;
import org.petspa.petcaresystem.appointment.model.response.AppointmentResponseDTO;
import org.petspa.petcaresystem.appointment.repository.AppointmentRepository;
import org.petspa.petcaresystem.appointment.service.AppointmentService;
import org.petspa.petcaresystem.authenuser.model.payload.AuthenUser;
import org.petspa.petcaresystem.authenuser.repository.AuthenUserRepository;
import org.petspa.petcaresystem.config.JwtUtil;
import org.petspa.petcaresystem.doctor.model.Doctor;
import org.petspa.petcaresystem.doctor.repository.DoctorRepository;
import org.petspa.petcaresystem.enums.Status;
import org.petspa.petcaresystem.order.model.UserOrder;
import org.petspa.petcaresystem.order.repository.OrdersRepository;
import org.petspa.petcaresystem.pet.model.entity.Pet;
import org.petspa.petcaresystem.pet.repository.PetRepository;
import org.petspa.petcaresystem.review.model.Review;
import org.petspa.petcaresystem.review.repository.ReviewRepository;
import org.petspa.petcaresystem.serviceAppointment.model.Services;
import org.petspa.petcaresystem.serviceAppointment.repository.ServicesRepository;
import org.slf4j.LoggerFactory;
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
    public AppointmentResponseDTO saveAppointment(Appointment appointment) {
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
        Doctor bookedDoctor = new Doctor();
        Pet pet = new Pet();
        Services service = new Services();
        UserOrder userOrder = new UserOrder();

        // get token extract userId
        HttpSession session =request.getSession();
        String token = (String) session.getAttribute("jwtToken");
        Long userId = jwtUtil.extractUserId(token);

        //------------guess book appointment------------------
        authenUser = authenUserRepository.findByUserId(userId);
        if(token.isEmpty() || token== null){
            reviewRepository.save(review);
            // appointment param
            appointmentSaveForGuess.setCreate_date(LocalDate.from(localDateTime));
            appointmentSaveForGuess.setStartTime(appointment.getStartTime());
            appointmentSaveForGuess.setStatus(Status.valueOf("INACTIVE"));
            // service
            appointmentSaveForGuess.setBookedService((Collection<Services>) service);
            // review
            appointmentSaveForGuess.setReview(review);
            appointmentRepository.save(appointmentSaveForGuess);
            appointmentRepository.save(appointmentSaveForGuess);

            // user order
            Long price = (long) servicesRepository.findById(service.getServiceId()).get().getPrice();
            userOrder.setPrice(price);
            userOrder.setUserOrderDate(localDateTime);
        }

        //------------user logged in----------------
        if(!token.isEmpty() || token != null) {
            // review
            review.setAuthor(authenUser);
            reviewRepository.save(review);

            // doctor
            appointmentSaveForUser.setBookedDoctor((Collection<Doctor>) bookedDoctor);

            // pet
            pet = petRepository.findByOwner(authenUser);
            appointmentSaveForUser.setPet(pet);

            // appointment param
            appointmentSaveForUser.setCreate_date(LocalDate.from(localDateTime));
            appointmentSaveForUser.setStartTime(appointmentSaveForUser.getStartTime());
            appointmentSaveForUser.setStatus(Status.valueOf("INACTIVE"));

            // service
            appointmentSaveForGuess.setBookedService((Collection<Services>) service);

            // review
            appointmentSaveForUser.setReview(review);

            // user order
            Long price = (long) servicesRepository.findById(service.getServiceId()).get().getPrice();
            userOrder.setPrice(price);
            userOrder.setUserOrderDate(localDateTime);
        }

        try {
            if(token.isEmpty() || token== null) {
                appointmentRepository.save(appointmentSaveForGuess);
                ordersRepository.save(userOrder);
            }else {
                appointmentRepository.save(appointmentSaveForUser);
                ordersRepository.save(userOrder);
            }
        } catch (Exception e) {
//            logger.error("Error occurred during create appointment", e);
            message = "Something went wrong, server error!";
            statusCode = HttpStatus.INTERNAL_SERVER_ERROR.value();
            statusValue = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return null;
    }

    @Override
    public Appointment updateAppointment(Appointment appointment) {
        Appointment existingAppointment = appointmentRepository.findById(appointment.getAppointment_id()).orElse(null);
        if(appointment.getBookedDoctor().isEmpty()){
            // automatically pick doctor based on their schedule
        }
        else{
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
