package br.com.jatai.service_user.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "users_access")
public class UserAccess {

    @Id
    @Column(columnDefinition = "UUID")
    private UUID uuid;

    @Column(name = "registration_date")
    private LocalDateTime registrationDate;

    @Column(name = "last_acess")
    private LocalDateTime lastAcess;

    @OneToOne(mappedBy = "userAccess")
    @PrimaryKeyJoinColumn(name = "uuid_user", referencedColumnName = "uuid")
    private UserModel user;


    public UserAccess(LocalDateTime registrationDate, LocalDateTime lastAcess) {
        this.registrationDate = registrationDate;
        this.lastAcess = lastAcess;
    }
    public UserAccess() {}

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }
}
