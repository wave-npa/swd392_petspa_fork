package org.petspa.petcaresystem.appointment.service.implement;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.petspa.petcaresystem.appointment.model.payload.Appointment;
import org.petspa.petcaresystem.appointment.model.request.CreateAppointmentRequestDTO;
import org.petspa.petcaresystem.appointment.model.request.UpdateAppointmentRequestDTO;
import org.petspa.petcaresystem.appointment.model.response.AppointmentResponseDTO;
import org.petspa.petcaresystem.appointment.model.response.AppointmentResponseData;
import org.petspa.petcaresystem.appointment.model.response.AppointmentResponseInfor;
import org.petspa.petcaresystem.appointment.repository.AppointmentRepository;
import org.petspa.petcaresystem.appointment.service.AppointmentService;
import org.petspa.petcaresystem.authenuser.model.payload.AuthenUser;
import org.petspa.petcaresystem.authenuser.repository.AuthenUserRepository;
import org.petspa.petcaresystem.boarding.model.entity.BoardingAppointment;
import org.petspa.petcaresystem.boarding.repository.BoardingRepository;
import org.petspa.petcaresystem.boarding_detail.model.BoardingDetail;
import org.petspa.petcaresystem.boarding_detail.repository.BoardingDetailRepository;
import org.petspa.petcaresystem.config.EmailServiceImpl;
import org.petspa.petcaresystem.config.JwtUtil;
import org.petspa.petcaresystem.doctor.model.Doctor;
import org.petspa.petcaresystem.doctor.model.DoctorData;
import org.petspa.petcaresystem.doctor.repository.DoctorRepository;
import org.petspa.petcaresystem.enums.Option;
import org.petspa.petcaresystem.enums.ShelterStatus;
import org.petspa.petcaresystem.enums.Status;
import org.petspa.petcaresystem.order.model.UserOrder;
import org.petspa.petcaresystem.order.repository.OrdersRepository;
import org.petspa.petcaresystem.pet.model.entity.Pet;
import org.petspa.petcaresystem.pet.repository.PetRepository;
import org.petspa.petcaresystem.review.model.entity.Review;
import org.petspa.petcaresystem.review.repository.ReviewRepository;
import org.petspa.petcaresystem.serviceAppointment.model.Services;
import org.petspa.petcaresystem.serviceAppointment.model.ServicesData;
import org.petspa.petcaresystem.serviceAppointment.repository.ServicesRepository;
import org.petspa.petcaresystem.shelter.model.entity.Shelter;
import org.petspa.petcaresystem.shelter.repository.ShelterRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    private static final String format_pattern = "yyyy-MM-dd HH:mm";
    private static final String logging_message = "An error occurred:";
    private static final Logger logger = LoggerFactory.getLogger(AppointmentService.class);
    private static final String petStoreEmail = "petspa392@gmail.com";

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
    @Autowired
    private EmailServiceImpl emailService;
    @Autowired
    private SimpleMailMessage simpleMailMessage;

    @Override
    public AppointmentResponseDTO findAllAppointment() {
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format_pattern);
        String timeStamp = localDateTime.format(formatter);
        String message = "Appointments were found successfully";
        int statusCode = HttpStatus.OK.value();
        HttpStatus statusValue = HttpStatus.OK;


        AppointmentResponseInfor infor = new AppointmentResponseInfor();
        List<AppointmentResponseData> appointmentResponseDataList = new ArrayList<>();


        infor.setMessage(message);
        infor.setTimeStamp(timeStamp);
        infor.setStatusCode(statusCode);
        infor.setStatusValue(statusValue);

        try {
            List<Appointment> appointmentList = new ArrayList<>();
            appointmentList = appointmentRepository.findAll();
            if (appointmentList.isEmpty()) {
                message = "Appointment not found!";
                statusCode = HttpStatus.NOT_FOUND.value();
                statusValue = HttpStatus.NOT_FOUND;
                infor.setMessage(message);
                infor.setStatusCode(statusCode);
                infor.setStatusValue(statusValue);
                return new AppointmentResponseDTO(infor, appointmentResponseDataList);
            }
            for (Appointment appointment : appointmentList) {

                AppointmentResponseData data = new AppointmentResponseData();

                // id
                data.setAppointmentId(appointment.getAppointmentId());

                // status
                data.setStatus(appointment.getStatus());

                // create date
                data.setCreate_date(appointment.getCreate_date());

                // start time
                data.setStartTime(appointment.getStartTime());

                // end time
                data.setEndTime(appointment.getEndTime());

                // booked doctor
                Collection<Doctor> bookedDoctors = appointment.getBookedDoctor();
                List<Doctor> doctorList = new ArrayList<>(bookedDoctors);
                DoctorData doctorData = new DoctorData();
                for (Doctor doctor : doctorList) {
                    doctorData.setDoctorId(doctor.getDoctorId());
                }
                data.setBookedDoctorId(doctorData.getDoctorId());

                // booked service
                Collection<Services> bookedServices = appointment.getBookedService();
                List<Services> serviceList = new ArrayList<>(bookedServices);
                ServicesData servicesData = new ServicesData();
                for (Services services : serviceList) {
                    servicesData.setServiceId(services.getServiceId());

                }
                data.setBookedServiceId(servicesData.getServiceId());

                // find pet
                if (appointment.getPet() != null) {
                    Pet pet = petRepository.findByPetId(appointment.getPet().getPetId());
                    data.setPetId(appointment.getPet().getPetId());
                } else {
                    data.setPetId(null);
                }

                // user order
                UserOrder userOrder = ordersRepository.findByUserOrderId(appointment.getUserOrder().getUserOrderId());
                data.setUserOrderId(userOrder.getUserOrderId());

                // review
                Review review = reviewRepository.findByReviewId(appointment.getReview().getReviewId());
                data.setReviewId(review.getReviewId());

                // boarding appointment
                if (appointment.getBoardingAppointment() != null) {
                    BoardingAppointment boardingAppointment = boardingRepository.findByBoardingId(appointment.getBoardingAppointment().getBoardingId());
                    data.setBoardingAppointmentId(boardingAppointment.getBoardingId());
                } else {
                    data.setBoardingAppointmentId(null);
                }

                // add appointment to response data list
                appointmentResponseDataList.add(data);
            }

        } catch (Exception e) {
            logger.error("Error occurred during running:", e);
            e.printStackTrace();
            message = "Something went wrong, server error!";
            statusCode = HttpStatus.INTERNAL_SERVER_ERROR.value();
            statusValue = HttpStatus.INTERNAL_SERVER_ERROR;
            infor.setMessage(message);
            infor.setStatusCode(statusCode);
            infor.setStatusValue(statusValue);
        }

        return new AppointmentResponseDTO(infor, appointmentResponseDataList);
    }

    @Override
    public AppointmentResponseDTO findAppointmentById(Long appointmentId) {
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format_pattern);
        String timeStamp = localDateTime.format(formatter);
        String message = "Appointment was found successfully";
        int statusCode = HttpStatus.OK.value();
        HttpStatus statusValue = HttpStatus.OK;


        AppointmentResponseInfor infor = new AppointmentResponseInfor();
        AppointmentResponseData data = new AppointmentResponseData();
        Appointment appointment = new Appointment();


        infor.setMessage(message);
        infor.setTimeStamp(timeStamp);
        infor.setStatusCode(statusCode);
        infor.setStatusValue(statusValue);

        try {
            appointment = appointmentRepository.findByAppointmentId(appointmentId);
            if (appointment == null) {
                message = "Appointment not found!";
                statusCode = HttpStatus.NOT_FOUND.value();
                statusValue = HttpStatus.NOT_FOUND;
                infor.setMessage(message);
                infor.setStatusCode(statusCode);
                infor.setStatusValue(statusValue);
                return new AppointmentResponseDTO(infor, data);
            }

            // appointment id
            data.setAppointmentId(appointmentId);

            // status
            data.setStatus(appointment.getStatus());

            // create date
            data.setCreate_date(appointment.getCreate_date());

            // start time
            data.setStartTime(appointment.getStartTime());

            // end time
            data.setEndTime(appointment.getEndTime());

            // booked doctor id
            Collection<Doctor> bookedDoctors = appointment.getBookedDoctor();
            List<Doctor> doctorList = new ArrayList<>(bookedDoctors);
            DoctorData doctorData = new DoctorData();
            for (Doctor doctor : doctorList) {
                doctorData.setDoctorId(doctor.getDoctorId());
            }
            data.setBookedDoctorId(doctorData.getDoctorId());

            // booked service id
            Collection<Services> bookedServices = appointment.getBookedService();
            List<Services> serviceList = new ArrayList<>(bookedServices);
            ServicesData servicesData = new ServicesData();
            for (Services services : serviceList) {
                servicesData.setServiceId(services.getServiceId());

            }
            data.setBookedServiceId(servicesData.getServiceId());

            // pet id
            if (appointment.getPet() != null) {
                Pet pet = petRepository.findByPetId(appointment.getPet().getPetId());
                data.setPetId(appointment.getPet().getPetId());
            } else {
                data.setPetId(null);
            }


            // user order id
            UserOrder userOrder = ordersRepository.findByUserOrderId(appointment.getUserOrder().getUserOrderId());
            data.setUserOrderId(userOrder.getUserOrderId());

            // review id
            Review review = reviewRepository.findByReviewId(appointment.getReview().getReviewId());
            data.setReviewId(review.getReviewId());

            // boarding appointment id
            if(appointment.getBoardingAppointment() != null) {
                BoardingAppointment boardingAppointment = boardingRepository.findByBoardingId(appointment.getBoardingAppointment().getBoardingId());
                data.setBoardingAppointmentId(boardingAppointment.getBoardingId());
            }else{
                data.setBoardingAppointmentId(null);
            }

        } catch (Exception e) {
            logger.error("Error occurred during running:", e);
            e.printStackTrace();
            message = "Something went wrong, server error!";
            statusCode = HttpStatus.INTERNAL_SERVER_ERROR.value();
            statusValue = HttpStatus.INTERNAL_SERVER_ERROR;
            infor.setMessage(message);
            infor.setStatusCode(statusCode);
            infor.setStatusValue(statusValue);
        }

        return new AppointmentResponseDTO(infor, data);
    }

    @Override
    public AppointmentResponseInfor saveAppointment(CreateAppointmentRequestDTO appointment, Option option, String phone, String email) {
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
        Long doctorId = appointment.getDoctorId();
        if (doctorId != null) {
            Doctor doctor = doctorRepository.findByDoctorId(doctorId);
            if (doctor != null) {
                bookedDoctors.add(doctor);
                appointmentSaveForGuess.setBookedDoctor(bookedDoctors);
                appointmentSaveForUser.setBookedDoctor(bookedDoctors);
            } else {
                message = "Invalid doctor";
                statusCode = HttpStatus.BAD_REQUEST.value();
                statusValue = HttpStatus.BAD_REQUEST;
                return new AppointmentResponseInfor(message, timeStamp, statusCode, statusValue);
            }
        } else {
            message = "No doctor was choosed!";
            statusCode = HttpStatus.BAD_REQUEST.value();
            statusValue = HttpStatus.BAD_REQUEST;
            return new AppointmentResponseInfor(message, timeStamp, statusCode, statusValue);
        }


        // service
        Long serviceId = appointment.getServiceId();
        Services services = new Services();
        if (serviceId != null) {
            services = servicesRepository.findByServiceId(serviceId);
            if (services != null) {
                bookedService.add(services);
                appointmentSaveForGuess.setBookedService(bookedService);
                appointmentSaveForUser.setBookedService(bookedService);
            } else {
                message = "Invalid service!";
                statusCode = HttpStatus.BAD_REQUEST.value();
                statusValue = HttpStatus.BAD_REQUEST;
                return new AppointmentResponseInfor(message, timeStamp, statusCode, statusValue);
            }
        } else {
            message = "No service was choosed!";
            statusCode = HttpStatus.BAD_REQUEST.value();
            statusValue = HttpStatus.BAD_REQUEST;
            return new AppointmentResponseInfor(message, timeStamp, statusCode, statusValue);
        }


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
        Long price = null;
        services = servicesRepository.findByServiceId(serviceId);
        if (services != null) {
            price = (long) services.getPrice();
            userOrder.setPrice(price);
            userOrder.setUserOrderDate(localDateTime);
        } else {
            message = "Invalid service!";
            statusCode = HttpStatus.BAD_REQUEST.value();
            statusValue = HttpStatus.BAD_REQUEST;
            return new AppointmentResponseInfor(message, timeStamp, statusCode, statusValue);
        }

        // shelter: find available shelter
        shelter = shelterRepository.findFirstByShelterStatus(ShelterStatus.EMPTY);

        // boarding: check shelter status, set boarding date, status, shelter id
        if (shelter != null) {
            if (shelter.getStatus() == Status.ACTIVE) {
                boardingAppointment.setBoardingTime(localDateTime);
                boardingAppointment.setStatus(Status.ACTIVE);
                boardingAppointment.setShelter(shelter);
            } else {
                message = "No shelter available!";
                statusCode = HttpStatus.BAD_REQUEST.value();
                statusValue = HttpStatus.BAD_REQUEST;
                return new AppointmentResponseInfor(message, timeStamp, statusCode, statusValue);
            }
        } else {
            message = "No shelter available!";
            statusCode = HttpStatus.BAD_REQUEST.value();
            statusValue = HttpStatus.BAD_REQUEST;
            return new AppointmentResponseInfor(message, timeStamp, statusCode, statusValue);
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
                if (appointment.getPetId() == null || appointment.getPetId() == 0) {
                    message = "No pet was choosed!";
                    statusCode = HttpStatus.BAD_REQUEST.value();
                    statusValue = HttpStatus.BAD_REQUEST;
                    return new AppointmentResponseInfor(message, timeStamp, statusCode, statusValue);
                }

                // extract user id, find user by id
                Long userId = jwtUtil.extractUserId(token);
                authenUser = authenUserRepository.findByUserId(userId);

                // review: set user id, set review id for appointment
                review.setAuthor(authenUser);
                appointmentSaveForUser.setReview(review);

                // pet: find bet by user, set pet id for appointment
                AuthenUser owner = authenUserRepository.findByUserId(userId);

                List<Pet> petListbyOwner = petRepository.findAllByOwner(authenUser);
                boolean check = false;
                for (Pet pet1 : petListbyOwner) {
                    if (pet1.getPetId() == appointment.getPetId()) {
                        pet.setPetId(pet1.getPetId());
                        pet.setAge(pet1.getAge());
                        pet.setPet_name(pet1.getPet_name());
                        pet.setGender(pet1.getGender());
                        pet.setType_of_species(pet1.getType_of_species());
                        pet.setStatus(pet1.getStatus());
                        pet.setOwner(pet1.getOwner());
                        check = true;
                        break;
                    }
                }

                if (pet != null) {
                    if (check) {
                        appointmentSaveForUser.setPet(pet);
                    } else {
                        message = "This pet is not present on your profile!";
                        statusCode = HttpStatus.NOT_FOUND.value();
                        statusValue = HttpStatus.NOT_FOUND;
                        return new AppointmentResponseInfor(message, timeStamp, statusCode, statusValue);
                    }
                } else {
                    message = "Pet ID not found! Pet ID invalid or user hasn't added a pet to profile yet!";
                    statusCode = HttpStatus.NOT_FOUND.value();
                    statusValue = HttpStatus.NOT_FOUND;
                    return new AppointmentResponseInfor(message, timeStamp, statusCode, statusValue);
                }

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

                // email
                String appointmentInfor =
                        "Guess's phone: " + phone + "\n"
                                + "Guess email: " + email + "\n"
                                + "Appointment ID: " + appointmentSaveForUser.getAppointmentId();
                String text = "Guess booking information:\n" + appointmentInfor;
                String subject = "PETSPA - Register Verify code";
                emailService.sendSimpleMessage(petStoreEmail, subject, text);
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
            if (option == Option.YES) {
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

            shelter.setShelterStatus(ShelterStatus.USING);
            shelterRepository.save(shelter);

        } catch (Exception e) {
//            logger.error("Error occurred during create appointment", e);
            message = "Something went wrong, server error!";
            statusCode = HttpStatus.INTERNAL_SERVER_ERROR.value();
            statusValue = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new AppointmentResponseInfor(message, timeStamp, statusCode, statusValue);
    }

    @Override
    public AppointmentResponseInfor updateAppointment(UpdateAppointmentRequestDTO updateAppointmentRequestDTO) {

        //----------------------------------- response------------------------------------
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format_pattern);
        String timeStamp = localDateTime.format(formatter);
        String message = "Create new appointment successfully";
        int statusCode = HttpStatus.OK.value();
        HttpStatus statusValue = HttpStatus.OK;

        // --------------------------------model--------------------------------------------
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

        //---------------------------------------token-------------------------------------------
        // get token extract userId
        HttpSession session = request.getSession();
        String token = (String) session.getAttribute("jwtToken");

        // ------------------------------handle and prepare data-------------------------------

        // get appointment by id
        Appointment appointment = appointmentRepository.findByAppointmentId(updateAppointmentRequestDTO.getAppointmentId());
        if (appointment == null) {
            message = "Appointment not found!";
            statusCode = HttpStatus.NOT_FOUND.value();
            statusValue = HttpStatus.NOT_FOUND;
            return new AppointmentResponseInfor(message, timeStamp, statusCode, statusValue);
        }


        // appointment id
        appointment.setAppointmentId(updateAppointmentRequestDTO.getAppointmentId());

        // doctor
        Doctor doctor = doctorRepository.findByDoctorId(updateAppointmentRequestDTO.getDoctorId());
        bookedDoctors.add(doctor);
        appointment.setBookedDoctor(bookedDoctors);

        // service
        Services services = servicesRepository.findByServiceId(updateAppointmentRequestDTO.getServiceId());
        bookedService.add(services);
        appointment.setBookedService(bookedService);


        // start time
        appointment.setStartTime(updateAppointmentRequestDTO.getStartTime());

        // end time
        appointment.setEndTime(updateAppointmentRequestDTO.getEndTime());

        // status
        appointment.setStatus(updateAppointmentRequestDTO.getStatus());

        try {

            // --------------------------------user logged in------------------------------------
            if (token != null) {

                // extract user id, find user by id
                Long userId = jwtUtil.extractUserId(token);
                authenUser = authenUserRepository.findByUserId(userId);

                // pet: find pet by user id
                AuthenUser owner = authenUserRepository.findByUserId(userId);
                pet = petRepository.findByOwner(owner);

                // check owner's pet
                Long ownerId = pet.getOwner().getUserId();

                if (pet != null && ownerId == userId) {
                    appointmentSaveForUser.setPet(pet);
                } else if (pet == null) {
                    message = "Pet not found! Invalid pet or user hasn't added a pet to profile yet!";
                    statusCode = HttpStatus.NOT_FOUND.value();
                    statusValue = HttpStatus.NOT_FOUND;
                    return new AppointmentResponseInfor(message, timeStamp, statusCode, statusValue);
                } else if (ownerId != userId) {
                    message = "This pet is not belongs to this account user!";
                    statusCode = HttpStatus.NOT_FOUND.value();
                    statusValue = HttpStatus.NOT_FOUND;
                    return new AppointmentResponseInfor(message, timeStamp, statusCode, statusValue);
                }

                // update appointment
                appointmentRepository.save(appointment);

            }
        } catch (Exception e) {
//            logger.error("Error occurred during create appointment", e);
            message = "Something went wrong, server error!";
            statusCode = HttpStatus.INTERNAL_SERVER_ERROR.value();
            statusValue = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new AppointmentResponseInfor(message, timeStamp, statusCode, statusValue);
    }

    @Override
    public AppointmentResponseInfor updateAppointmentStatus(Long appointmentId, Status status) {
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format_pattern);
        String timeStamp = localDateTime.format(formatter);
        String message = "Appontment's status was changed successfully";
        int statusCode = HttpStatus.OK.value();
        HttpStatus statusValue = HttpStatus.OK;

        Appointment appointment = appointmentRepository.findByAppointmentId(appointmentId);
        if (appointment == null) {
            message = "Appointment not found!";
            statusCode = HttpStatus.NOT_FOUND.value();
            statusValue = HttpStatus.NOT_FOUND;
            return new AppointmentResponseInfor(message, timeStamp, statusCode, statusValue);
        }

        if (appointment.getStatus() == status) {
            message = "Appointment's status has already been " + status.toString();
            statusCode = HttpStatus.CONFLICT.value();
            statusValue = HttpStatus.CONFLICT;
            return new AppointmentResponseInfor(message, timeStamp, statusCode, statusValue);
        }

        appointment.setStatus(status);

        try {
            appointmentRepository.save(appointment);
        } catch (Exception e) {
            message = "Something went wrong, server error!";
            statusCode = HttpStatus.INTERNAL_SERVER_ERROR.value();
            statusValue = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new AppointmentResponseInfor(message, timeStamp, statusCode, statusValue);
    }

    @Override
    public AppointmentResponseDTO getAppointmentByUserId() {
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format_pattern);
        String timeStamp = localDateTime.format(formatter);
        String message = "Appontments were found";
        int statusCode = HttpStatus.OK.value();
        HttpStatus statusValue = HttpStatus.OK;

        // token
        HttpSession session = request.getSession();
        String token = (String) session.getAttribute("jwtToken");

        // infor and data response
        AppointmentResponseInfor infor = new AppointmentResponseInfor();
        List<AppointmentResponseData> appointmentResponseDataList = new ArrayList<>();
        infor.setTimeStamp(timeStamp);

        try {

            // user
            Long userId = jwtUtil.extractUserId(token);
            AuthenUser authenUser = authenUserRepository.findByUserId(userId);

            // user order id
            Collection<UserOrder> userOrderList = ordersRepository.findAllByCustomer(authenUser);

            for (UserOrder userOrder : userOrderList) {
                AppointmentResponseData appointmentResponseData = new AppointmentResponseData();
                Appointment appointment = appointmentRepository.findByUserOrder(userOrder);

                // appointment id
                appointmentResponseData.setAppointmentId(appointment.getAppointmentId());

                // status
                appointmentResponseData.setStatus(appointment.getStatus());

                // create date
                appointmentResponseData.setCreate_date(appointment.getCreate_date());

                // start time
                appointmentResponseData.setStartTime(appointment.getStartTime());

                // end time
                appointmentResponseData.setEndTime(appointment.getEndTime());

                // booked doctor
                Collection<Doctor> bookedDoctors = appointment.getBookedDoctor();
                List<Doctor> doctorList = new ArrayList<>(bookedDoctors);
                DoctorData doctorData = new DoctorData();
                for (Doctor doctor : doctorList) {
                    doctorData.setDoctorId(doctor.getDoctorId());
                }
                appointmentResponseData.setBookedDoctorId(doctorData.getDoctorId());

                // booked service
                Collection<Services> bookedServices = appointment.getBookedService();
                List<Services> serviceList = new ArrayList<>(bookedServices);
                ServicesData servicesData = new ServicesData();
                for (Services services : serviceList) {
                    servicesData.setServiceId(services.getServiceId());

                }
                appointmentResponseData.setBookedServiceId(servicesData.getServiceId());

                // find pet
                Pet pet = petRepository.findByPetId(appointment.getPet().getPetId());
                appointmentResponseData.setPetId(appointment.getPet().getPetId());

                // user order
                appointmentResponseData.setUserOrderId(userOrder.getUserOrderId());

                // review
                Review review = reviewRepository.findByReviewId(appointment.getReview().getReviewId());
                appointmentResponseData.setReviewId(review.getReviewId());

                // boarding appointment
                BoardingAppointment boardingAppointment = boardingRepository.findByBoardingId(appointment.getBoardingAppointment().getBoardingId());
                appointmentResponseData.setBoardingAppointmentId(boardingAppointment.getBoardingId());

                // add appointment to list
                appointmentResponseDataList.add(appointmentResponseData);
            }

            if (appointmentResponseDataList == null || appointmentResponseDataList.isEmpty()) {
                message = "This account hasn't booked any appointment yet!";
                statusCode = HttpStatus.NOT_FOUND.value();
                statusValue = HttpStatus.NOT_FOUND;
                infor.setMessage(message);
                infor.setStatusCode(statusCode);
                infor.setStatusValue(statusValue);
                return new AppointmentResponseDTO(infor, appointmentResponseDataList);
            }

        } catch (Exception e) {
            message = "Something went wrong, server error!";
            statusCode = HttpStatus.INTERNAL_SERVER_ERROR.value();
            statusValue = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        // info response
        infor.setMessage(message);
        infor.setStatusCode(statusCode);
        infor.setStatusValue(statusValue);

        return new AppointmentResponseDTO(infor, appointmentResponseDataList);
    }
}
