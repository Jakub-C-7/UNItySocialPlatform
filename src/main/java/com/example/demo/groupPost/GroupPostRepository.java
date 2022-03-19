package com.example.demo.groupPost;

import com.example.demo.group.AppGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * GroupPostRepository Interface performs Post queries between the application and the database.
 */
@Repository
@Transactional(readOnly = true)
public interface GroupPostRepository extends JpaRepository<GroupPost, Long> {

    //SELECT * FROM post WHERE postAuthor = PARSED postAuthor
    @Query("SELECT p FROM GroupPost p WHERE p.postUser.email = ?1")
    Optional<GroupPost> findByEmail(String postAuthorEmail);

    //SELECT * FROM post ORDER BY postDateTime in DESCENDING order
    @Query("SELECT p FROM GroupPost p WHERE p.group = ?1 ORDER BY p.postDateTime DESC")
    List<GroupPost> findAllByGroupSortedByDate(AppGroup group);

}
