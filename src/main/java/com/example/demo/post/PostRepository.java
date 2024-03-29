package com.example.demo.post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * PostRepository Interface performs Post queries between the application and the database.
 */
@Repository
@Transactional(readOnly = true)
public interface PostRepository extends JpaRepository<Post, Long> {

    //SELECT * FROM post WHERE postAuthor matches
    @Query("SELECT p FROM Post p WHERE p.postUser.email = ?1")
    Optional<Post> findByEmail(String postAuthorEmail);

    //SELECT * FROM post ORDER BY postDateTime in DESCENDING order
    @Query("SELECT p FROM Post p ORDER BY p.postDateTime DESC")
    List<Post> findAllByDate();

    //SELECT * FROM post WHERE userId matches ORDER BY postDateTime in DESCENDING order
    @Query("SELECT p FROM Post p WHERE p.postUser.id = ?1 ORDER BY p.postDateTime DESC")
    List<Post> findAllByAuthorIdSortedByDate(Long userId);

}
