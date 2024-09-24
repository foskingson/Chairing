package chairing.chairing.service.community;

import lombok.RequiredArgsConstructor;

import java.security.Principal;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import chairing.chairing.domain.community.Comment;
import chairing.chairing.domain.community.Post;
import chairing.chairing.domain.user.User;
import chairing.chairing.dto.community.CommentRequest;
import chairing.chairing.repository.community.CommentRepository;
import chairing.chairing.repository.community.PostRepository;
import chairing.chairing.repository.user.UserRepository;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Transactional
    public Comment createComment(Principal principal, CommentRequest request) {
        Post post = postRepository.findById(request.getPostId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid post ID"));
        
        String username = principal.getName();
        User user = userRepository.findByusername(username)
                .orElseThrow(() -> new IllegalArgumentException("유저를 찾을 수 없습니다."));

        Comment comment = new Comment(post, user, request.getContent());
        return commentRepository.save(comment);
    }

    @Transactional
    public void deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }
}