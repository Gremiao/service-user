package br.com.jatai.service_user.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "users_preferences")
public class UserPreferences {

    @Id
    @Column(columnDefinition = "UUID")
    private UUID uuid;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "age_min")
    private String ageMin;

    @Column(name = "age_max")
    private String ageMax;

    @Enumerated(EnumType.STRING)
    private Country country;

    private String state;

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public UserPreferences(Gender gender, String ageMin, String ageMax, Country country, String state){
        this.gender = gender;
        this.ageMin = ageMin;
        this.ageMax = ageMax;
        this.country = country;
        this.state = state;
    }
}
