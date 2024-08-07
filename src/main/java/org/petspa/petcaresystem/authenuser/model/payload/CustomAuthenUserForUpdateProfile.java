package org.petspa.petcaresystem.authenuser.model.payload;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CustomAuthenUserForUpdateProfile {
    private Long userId;
    private String userName;
    private String email;
    private String fullName;
    private String gender;
    private String address;
    private String phone;
}
