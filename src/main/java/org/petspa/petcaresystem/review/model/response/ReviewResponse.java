package org.petspa.petcaresystem.review.model.response;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.petspa.petcaresystem.appointment.model.payload.Appointment;
import org.petspa.petcaresystem.authenuser.model.payload.AuthenUser;
import org.petspa.petcaresystem.enums.ReviewRating;
import org.petspa.petcaresystem.enums.Status;

@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ReviewResponse {
     Long reviewId;
     String description;
     ReviewRating rating;
     Status status;
     Appointment appointment;
     AuthenUser author;
}
