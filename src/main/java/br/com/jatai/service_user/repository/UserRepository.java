package br.com.jatai.service_user.repository;

import br.com.jatai.security.dto.AuthenticationRequestDTO;
import br.com.jatai.service_user.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<UserModel, UUID> {

    @Query("""
        SELECT
            u, ud, ul
        from UserModel u
        join u.userDetails ud
        join u.userLocation ul
        where u.uuid =:uuid
        """)
    UserModel findUserByUuid(@Param("uuid") UUID uuid);

    @Query("""
            SELECT
                u.password
            FROM UserModel u
            JOIN u.userDetails ud
            WHERE ud.email =:email
            """)
    Optional<String> findPasswordByEmail(@Param("email") String email);

    @Query("""
            SELECT
                u.uuid
            FROM UserModel u
            JOIN u.userDetails ud
            WHERE ud.email =:email
            """)
    Optional<String> findUuidByEmail(@Param("email") String email);

    @Query("""
            SELECT new br.com.jatai.security.dto.AuthenticationRequestDTO(
                ud.email, u.password, u.password, u.uuid, u.userRole
            )
            FROM UserModel u
            JOIN u.userDetails ud
            WHERE ud.email =:email
            """)
    Optional<AuthenticationRequestDTO> findUserByEmail(@Param("email") String email);
}
