package com.example.demo.group;

import com.example.demo.appuser.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
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
public interface GroupRepository extends JpaRepository<Group, Long> {

    List<Group> findByCreator(AppUser creator);

}
