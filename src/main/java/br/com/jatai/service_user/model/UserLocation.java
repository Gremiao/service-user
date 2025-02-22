package br.com.jatai.service_user.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;

import java.util.UUID;

@Entity
@Table(name = "users_locations")
public class UserLocation {

    @Id
    @Column(columnDefinition = "UUID")
    private UUID uuid;

    @Enumerated(EnumType.STRING)
    private Country country;

    @Column(nullable = false)
    private String state;

    private String city;

    @Column(name = "postal_code")
    @Pattern(regexp = "\\d{8}")
    private String postalCode;

    public UserLocation(Country country, String state, String city, String postalCode) {
        this.country = country;
        this.state = state;
        this.city = city;
        this.postalCode = postalCode;
    }

    public UserLocation() {}

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }
}
