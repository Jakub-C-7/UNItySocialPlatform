package com.example.demo.postComment;

import com.example.demo.groupPost.GroupPost;
import com.example.demo.post.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * PostCommentRepository Interface performs Comment queries between the application and the database.
 *
 * @author jakub
 */
@Repository
@Transactional(readOnly = true)
public interface PostCommentRepository extends JpaRepository<PostComment, Long> {

    List<PostComment> findAllByPost(Post post);

    List<PostComment> findAllByGroupPost(GroupPost post);

}
