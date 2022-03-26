package com.example.demo.postLike;

import com.example.demo.appuser.AppUser;
import com.example.demo.post.Post;
import com.example.demo.postLike.PostLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * PostLikeRepository Interface performs Like queries between the application and the database.
 *
 * @author jakub
 */
@Repository
@Transactional(readOnly = true)
public interface PostLikeRepository extends JpaRepository<PostLike, Long> {

    List<PostLike> findAllByPost(Post post);

    List<PostLike> findAllByLikeUser(AppUser user);

    PostLike findByLikeUserAndPost(AppUser user, Post post);

    boolean existsByLikeUserAndPost(AppUser user, Post post);

}
