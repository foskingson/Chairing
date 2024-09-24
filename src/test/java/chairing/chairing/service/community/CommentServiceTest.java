package chairing.chairing.service.community;

import chairing.chairing.domain.community.Comment;
import chairing.chairing.domain.community.Post;
import chairing.chairing.domain.user.User;
import chairing.chairing.dto.community.CommentRequest;
import chairing.chairing.repository.community.CommentRepository;
import chairing.chairing.repository.community.PostRepository;
import chairing.chairing.repository.user.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.security.Principal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class CommentServiceTest {

    @InjectMocks
    private CommentService commentService;

    @Mock
    private CommentRepository commentRepository;

    @Mock
    private PostRepository postRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private Principal principal;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createComment() {
        // Arrange
        Long postId = 1L;
        String username = "user1";
        String content = "This is a comment";

        Post post = new Post();
        User user = new User();
        CommentRequest request = new CommentRequest(postId, content);

        when(principal.getName()).thenReturn(username);
        when(postRepository.findById(postId)).thenReturn(Optional.of(post));
        when(userRepository.findByusername(username)).thenReturn(Optional.of(user));
        when(commentRepository.save(any(Comment.class))).thenAnswer(invocation -> invocation.getArguments()[0]);

        // Act
        Comment comment = commentService.createComment(principal, request);

        // Assert
        assertNotNull(comment);
        assertEquals(post, comment.getPost());
        assertEquals(user, comment.getUser());
        assertEquals(content, comment.getContent());
        verify(postRepository, times(1)).findById(postId);
        verify(userRepository, times(1)).findByusername(username);
        verify(commentRepository, times(1)).save(any(Comment.class));
    }

    @Test
    void deleteComment() {
        // Arrange
        Long commentId = 1L;

        // Act
        commentService.deleteComment(commentId);

        // Assert
        verify(commentRepository, times(1)).deleteById(commentId);
    }
}
