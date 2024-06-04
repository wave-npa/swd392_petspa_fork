package org.petspa.petcaresystem.pet.model.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ResponseObj {
    String message;
//    String status;
    Object data;
}
