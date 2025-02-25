package br.com.jatai.service_user.model;

import br.com.jatai.service_user.dto.UserRegistrationRequestDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "users")
public class UserModel {
    @Id
    @Column(columnDefinition = "UUID")
    private UUID uuid;

    private String password;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(
            name = "users_join_details",
            joinColumns = @JoinColumn(name = "uuid_user"),
            inverseJoinColumns = @JoinColumn(name = "uuid_details"))
    private UserDetails userDetails;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(
            name = "users_join_locations",
            joinColumns = @JoinColumn(name = "uuid_user"),
            inverseJoinColumns = @JoinColumn(name = "uuid_location"))
    private UserLocation userLocation;

    @ColumnDefault("false")
    private boolean active;

    @Column(name = "user_role")
    @ColumnDefault("ROLE_USER")
    private String userRole;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(
            name = "users_join_access",
            joinColumns = @JoinColumn(name = "uuid_user"),
            inverseJoinColumns = @JoinColumn(name = "uuid_access"))
    private UserAccess userAccess;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(
            name = "users_join_preferences",
            joinColumns = @JoinColumn(name = "uuid_user"),
            inverseJoinColumns = @JoinColumn(name = "uuid_preferences"))
    private UserPreferences userPreferences;

    public UserModel(UserDetails userDetails, UserLocation userLocation) {
        this.userDetails = userDetails;
        this.userLocation = userLocation;
    }

    public UserModel(UserRegistrationRequestDTO userSent) {
        this.userRole = "ROLE_USER";
        this.userDetails = new UserDetails(
                userSent.firstName(),
                userSent.lastName(),
                userSent.age(),
                Gender.valueOf(userSent.gender().toUpperCase()),
                userSent.email(),
                userSent.phoneNumber()
        );
        this.userLocation = new UserLocation(
                Country.valueOf(userSent.country().toUpperCase()),
                userSent.state(),
                userSent.city(),
                userSent.postalCode());
    }

}
