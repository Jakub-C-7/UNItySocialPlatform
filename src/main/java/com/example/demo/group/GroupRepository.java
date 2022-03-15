package com.example.demo.group;

import com.example.demo.appuser.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * GroupRepository Interface performs Group queries between the application and the database.
 *
 * Creating, deleting, editing groups.
 *
 * @author jakub
 */
@Repository
@Transactional(readOnly = true)
public interface GroupRepository extends JpaRepository<AppGroup, Long> {

    List<AppGroup> findByCreator(AppUser creator);

//    SELECT * FROM post ORDER BY postDateTime in DESCENDING order
    @Query("SELECT g FROM AppGroup g ORDER BY g.name DESC")
    List<AppGroup> findAllByName();

}
