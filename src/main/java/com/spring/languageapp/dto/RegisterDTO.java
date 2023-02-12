package com.spring.languageapp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

public class RegisterDTO {

    @NotBlank(message = "Username is mandatory")
    private String username;

    @NotBlank(message = "Password is mandatory")
    private String password;

    @Email(message = "Email provided is not valid")
    private String email;

    @NotBlank(message = "Country is mandatory")
    private String country;

    @NotBlank(message = "Native language is mandatory")
    private String nationality;

    @NotBlank(message = "Your native language is mandatory")
    private String nativeLanguage;

    @NotBlank(message = "The language you want to practiced is mandatory")
    private String practicedLanguage;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private LocalDateTime dateOfBirth;


    public RegisterDTO(String username, String password, String email, String country, String nationality, String nativeLanguage, String practicedLanguage, LocalDateTime dateOfBirth) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.country = country;
        this.nationality = nationality;
        this.nativeLanguage = nativeLanguage;
        this.practicedLanguage = practicedLanguage;
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getNativeLanguage() {
        return nativeLanguage;
    }

    public void setNativeLanguage(String nativeLanguage) {
        this.nativeLanguage = nativeLanguage;
    }

    public String getPracticedLanguage() {
        return practicedLanguage;
    }

    public void setPracticedLanguage(String practicedLanguage) {
        this.practicedLanguage = practicedLanguage;
    }

    public LocalDateTime getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDateTime dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}
