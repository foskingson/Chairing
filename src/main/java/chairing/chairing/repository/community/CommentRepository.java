package chairing.chairing.repository.community;
import chairing.chairing.domain.community.Comment;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByPost_PostId(Long postId);
}