package com.contact_manager.forms;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class UserForm {

    @NotBlank(message = "Username is Required")
    @Size(min = 3, message = "Min 3 Character is Required")
    private String name;

    @Email(message = "Invalid Email Address")
    @NotBlank(message = "Email is Required")
    private String email;

    @NotBlank(message = "Password is Required")
    @Size(min = 6, max = 18, message = "Min 6 Character is Required")
    private String password;

    @NotBlank
    private String about;

    @Size(min = 8, max = 12, message = "Invalid Phone Number")
    private String phoneNumber;
}
