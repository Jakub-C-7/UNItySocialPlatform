package com.example.demo.post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Repository
@Transactional(readOnly = true)
public interface PostRepository extends JpaRepository<Post, Long> {

    //SELECT * FROM post WHERE postAuthor = PARSED postAuthor
    @Query("SELECT p FROM Post p WHERE p.postAuthorEmail = ?1")
    Optional<Post> findByEmail(String postAuthorEmail);

    //SELECT * FROM post ORDER BY postDateTime in DESCENDING order
    @Query("SELECT p FROM Post p ORDER BY p.postDateTime DESC")
    List<Post> findAllByDate();

}
