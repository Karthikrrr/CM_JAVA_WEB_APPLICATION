package com.contact_manager.forms;

import org.springframework.web.multipart.MultipartFile;

import com.contact_manager.Validators.ValidFile;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ContactForm {

    @NotBlank(message="Name Is Required")
    private String name;

    @NotBlank(message="Email Is Requried")
    @Email(message="Invalid Email Adress")
    private String email;

    @NotBlank(message="Phone Number Is Required")
    @Pattern(regexp="^[0-9]{10}$", message="Invalid Phone Number")
    private String phoneNumber;

    @NotBlank(message="Address Is Required")
    private String address;

    @NotBlank(message="Description Is Required")
    private String description;
    private boolean favorite;
    private String websiteLink;
    private String linkedInLink;

    @ValidFile
    private MultipartFile contactImage;

}
