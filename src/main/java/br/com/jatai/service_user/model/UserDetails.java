package br.com.jatai.service_user.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "users_details")
public class UserDetails {
    @Id
    private UUID uuid;

    @Column(nullable = false, name = "first_name")
    private String firstName;

    @Column(nullable = false, name = "last_name")
    private String lastName;

    @Column(nullable = false)
    private String age;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(unique = true)
    private String email;

    @Column(nullable = false, name = "phone_number")
    private String phoneNumber;

    public UserDetails(String firstName, String lastName, String age, Gender gender, String email, String phoneNumber){
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.gender = gender;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }
}
