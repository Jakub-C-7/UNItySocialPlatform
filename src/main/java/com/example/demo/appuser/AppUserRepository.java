package com.example.demo.appuser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * AppUserRepository Interface performs AppUser queries between the application and the database.
 *
 * @author jakub
 */
@Repository
@Transactional(readOnly = true)
public interface AppUserRepository extends JpaRepository<AppUser, Long> {

    Optional<AppUser> findByEmail(String email);

    @Transactional
    @Modifying
    @Query("UPDATE AppUser a " + "SET a.enabled = TRUE WHERE a.email = ?1")
    int enableAppUser(String email);

    @Transactional
    @Modifying
    @Query("UPDATE AppUser a " + "SET a.email = :email WHERE a.email = :currentEmail" )
    void updateEmail(String currentEmail, @Param("email") String email);

    @Transactional
    @Modifying
    @Query("UPDATE AppUser a " + "SET a.firstName = :firstName WHERE a.email = :email" )
    void updateFirstName(String email, @Param("firstName") String firstName);

    @Transactional
    @Modifying
    @Query("UPDATE AppUser a " + "SET a.lastName = :lastName WHERE a.email = :email" )
    void updateLastName(String email, @Param("lastName") String lastName);
}
