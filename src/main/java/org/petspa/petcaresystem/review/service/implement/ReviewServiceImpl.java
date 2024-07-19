package org.petspa.petcaresystem.review.service.implement;

import org.petspa.petcaresystem.appointment.model.payload.Appointment;
import org.petspa.petcaresystem.appointment.model.payload.GuessInfor;
import org.petspa.petcaresystem.appointment.repository.AppointmentRepository;
import org.petspa.petcaresystem.authenuser.model.payload.AuthenUser;
import org.petspa.petcaresystem.authenuser.repository.AuthenUserRepository;
import org.petspa.petcaresystem.enums.ReviewRating;
import org.petspa.petcaresystem.pet.model.entity.Pet;
import org.petspa.petcaresystem.pet.model.entity.PetData;
import org.petspa.petcaresystem.pet.repository.PetRepository;
import org.petspa.petcaresystem.review.model.entity.Review;
import org.petspa.petcaresystem.review.model.request.UpdateReviewRequestDTO;
import org.petspa.petcaresystem.review.model.response.*;
import org.petspa.petcaresystem.review.repository.ReviewRepository;
import org.petspa.petcaresystem.review.service.ReviewService;
import org.petspa.petcaresystem.serviceAppointment.model.Services;
import org.petspa.petcaresystem.serviceAppointment.model.ServicesData;
import org.petspa.petcaresystem.serviceAppointment.repository.ServicesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {
    private static final String format_pattern = "yyyy-MM-dd HH:mm";

    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private AuthenUserRepository authenUserRepository;
    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private ServicesRepository servicesRepository;
    @Autowired
    private PetRepository petRepository;

    @Override
    public ReviewResponseDTO findAllReview() {
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format_pattern);
        String timeStamp = localDateTime.format(formatter);
        String message = "Review found";
        int statusCode = HttpStatus.OK.value();
        HttpStatus statusValue = HttpStatus.OK;

        ResponseInfor responseInfor = new ResponseInfor();
        List<ReviewResponseData> reviewResponseDataList = new ArrayList<>();

        responseInfor.setMessage(message);
        responseInfor.setTimeStamp(timeStamp);
        responseInfor.setStatusCode(statusCode);
        responseInfor.setStatusValue(statusValue);

        try {

            List<Review> reviewList = reviewRepository.findAll();
            if (reviewList == null) {
                message = "Review not found!";
                statusCode = HttpStatus.NOT_FOUND.value();
                statusValue = HttpStatus.NOT_FOUND;
                responseInfor.setTimeStamp(timeStamp);
                responseInfor.setStatusCode(statusCode);
                responseInfor.setStatusValue(statusValue);
                return new ReviewResponseDTO(responseInfor, null, reviewResponseDataList);
            }

            for (Review review : reviewList) {
                ReviewResponseData reviewResponseData = new ReviewResponseData();
                reviewResponseData.setReviewId(review.getReviewId());
                reviewResponseData.setDescription(reviewResponseData.getDescription());
                reviewResponseData.setReviewRating(reviewResponseData.getReviewRating());
                reviewResponseData.setStatus(review.getStatus());
                reviewResponseData.setUserId(review.getAuthor().getUserId());

                reviewResponseDataList.add(reviewResponseData);
            }

        } catch (Exception e) {
            message = "Something went wrong, server error!";
            statusCode = HttpStatus.INTERNAL_SERVER_ERROR.value();
            statusValue = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ReviewResponseDTO(responseInfor, null, reviewResponseDataList);
    }

    @Override
    public ReviewResponseDTO findReviewByAuthor(Long userId) {
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format_pattern);
        String timeStamp = localDateTime.format(formatter);
        String message = "Review found";
        int statusCode = HttpStatus.OK.value();
        HttpStatus statusValue = HttpStatus.OK;

        ResponseInfor responseInfor = new ResponseInfor();
        List<ReviewResponseData> reviewResponseDataList = new ArrayList<>();

        responseInfor.setMessage(message);
        responseInfor.setTimeStamp(timeStamp);
        responseInfor.setStatusCode(statusCode);
        responseInfor.setStatusValue(statusValue);

        try {

            AuthenUser authenUser = authenUserRepository.findByUserId(userId);
            if (authenUser == null) {
                message = "User not found!";
                statusCode = HttpStatus.NOT_FOUND.value();
                statusValue = HttpStatus.NOT_FOUND;
                responseInfor.setTimeStamp(timeStamp);
                responseInfor.setStatusCode(statusCode);
                responseInfor.setStatusValue(statusValue);
                return new ReviewResponseDTO(responseInfor, null, null);
            }
            List<Review> reviewList = reviewRepository.findByAuthor(authenUser);
            if (reviewList == null) {
                message = "Review not found!";
                statusCode = HttpStatus.NOT_FOUND.value();
                statusValue = HttpStatus.NOT_FOUND;
                responseInfor.setTimeStamp(timeStamp);
                responseInfor.setStatusCode(statusCode);
                responseInfor.setStatusValue(statusValue);
                return new ReviewResponseDTO(responseInfor, null, null);
            }

            for (Review review : reviewList) {
                ReviewResponseData reviewResponseData = new ReviewResponseData();
                reviewResponseData.setReviewId(review.getReviewId());
                reviewResponseData.setDescription(review.getDescription());
                reviewResponseData.setReviewRating(review.getRating());
                reviewResponseData.setStatus(review.getStatus());
                reviewResponseData.setUserId(review.getAuthor().getUserId());

                reviewResponseDataList.add(reviewResponseData);
            }

        } catch (Exception e) {
            message = "Something went wrong, server error!";
            statusCode = HttpStatus.INTERNAL_SERVER_ERROR.value();
            statusValue = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ReviewResponseDTO(responseInfor, null, reviewResponseDataList);
    }

    @Override
    public ResponseInfor updateStatusReview(Long reviewId, UpdateReviewRequestDTO updateReviewRequestDTO) {
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format_pattern);
        String timeStamp = localDateTime.format(formatter);
        String message = "Review updated successfully";
        int statusCode = HttpStatus.OK.value();
        HttpStatus statusValue = HttpStatus.OK;

        try {

            Review review = reviewRepository.findByReviewId(reviewId);
            if (review == null) {
                message = "Review not found!";
                statusCode = HttpStatus.NOT_FOUND.value();
                statusValue = HttpStatus.NOT_FOUND;
                return new ResponseInfor(message, timeStamp, statusCode, statusValue);
            }

            review.setDescription(updateReviewRequestDTO.getDescription());
            review.setRating(updateReviewRequestDTO.getRating());
            review.setStatus(updateReviewRequestDTO.getStatus());

            reviewRepository.save(review);


        } catch (Exception e) {
            message = "Something went wrong, server error!";
            statusCode = HttpStatus.INTERNAL_SERVER_ERROR.value();
            statusValue = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseInfor(message, timeStamp, statusCode, statusValue);
    }

    @Override
    public ReviewResponseDTO2 findReviewByApppointmentId(Long appointmentId) {
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format_pattern);
        String timeStamp = localDateTime.format(formatter);
        String message = "Review found";
        int statusCode = HttpStatus.OK.value();
        HttpStatus statusValue = HttpStatus.OK;

        ResponseInfor responseInfor = new ResponseInfor();
        ReviewResponseData2 reviewResponseData2 = new ReviewResponseData2();

        responseInfor.setMessage(message);
        responseInfor.setTimeStamp(timeStamp);
        responseInfor.setStatusCode(statusCode);
        responseInfor.setStatusValue(statusValue);

        try {

            Appointment appointment = appointmentRepository.findByAppointmentId(appointmentId);
            if (appointment == null) {
                message = "Appointment not found!";
                statusCode = HttpStatus.NOT_FOUND.value();
                statusValue = HttpStatus.NOT_FOUND;
                responseInfor.setTimeStamp(timeStamp);
                responseInfor.setStatusCode(statusCode);
                responseInfor.setStatusValue(statusValue);
                return new ReviewResponseDTO2(responseInfor, null);
            }

            Review review = reviewRepository.findByAppointment(appointment);
            if (review == null) {
                message = "Review not found!";
                statusCode = HttpStatus.NOT_FOUND.value();
                statusValue = HttpStatus.NOT_FOUND;
                responseInfor.setTimeStamp(timeStamp);
                responseInfor.setStatusCode(statusCode);
                responseInfor.setStatusValue(statusValue);
                return new ReviewResponseDTO2(responseInfor, null);
            }


            reviewResponseData2.setReviewId(review.getReviewId());
            reviewResponseData2.setDescription(review.getDescription());
            reviewResponseData2.setReviewRating(review.getRating());
            reviewResponseData2.setStatus(review.getStatus());
            reviewResponseData2.setUserId(review.getAuthor().getUserId());

            AuthenUser authenUser = authenUserRepository.findByUserId(review.getAuthor().getUserId());
            reviewResponseData2.setFullName(authenUser.getFullName());

            // service
            Collection<Services> bookedServices = appointment.getBookedService();
            List<Services> serviceList = new ArrayList<>(bookedServices);
            ServicesData servicesData = new ServicesData();
            for (Services services : serviceList) {
                servicesData.setServiceName(services.getServiceName());
            }
            reviewResponseData2.setServiceName(servicesData.getServiceName());


            // find pet
            if (appointment.getPet() != null) {
                Pet pet = petRepository.findByPetId(appointment.getPet().getPetId());
                if (pet != null) {
                    PetData petData = new PetData();
                    petData.setPet_name(pet.getPet_name());
                    petData.setAge(pet.getAge());
                    petData.setGender(pet.getGender());
                    petData.setStatus(pet.getStatus());
                    petData.setPetId(pet.getPetId());
                    petData.setType_of_species(pet.getType_of_species());
                    petData.setOwnerId(pet.getOwner().getUserId());
                    petData.setSpecies(pet.getSpecies());
                    reviewResponseData2.setPetData(petData);

                }
            } else {
                reviewResponseData2.setPetData(null);
            }


        } catch (Exception e) {
            message = "Something went wrong, server error!";
            statusCode = HttpStatus.INTERNAL_SERVER_ERROR.value();
            statusValue = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ReviewResponseDTO2(responseInfor, reviewResponseData2);
    }
}
