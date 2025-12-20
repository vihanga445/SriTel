package com.example.user_service.dto;

public class UserRegistrationDTO {

    private Long id;
    private String email;
    private String password;
    private String fullName;



    public UserRegistrationDTO() {}

    public UserRegistrationDTO(String email, String password, String fullName) {
        this.email = email;
        this.password = password;
        this.fullName = fullName;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }



    @Override
    public String toString() {
        return "UserRegistrationDTO{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", fullName='" + fullName + '\'' +
                '}';
    }

}
