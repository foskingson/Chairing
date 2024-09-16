package chairing.chairing.service.community;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import chairing.chairing.domain.community.Post;
import chairing.chairing.domain.user.User;
import chairing.chairing.dto.community.PostRequest;
import chairing.chairing.repository.community.PostRepository;
import chairing.chairing.repository.user.UserRepository;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public Post createPost(Principal principal, PostRequest request) {
        String username = principal.getName();
        User user = userRepository.findByusername(username)
                .orElseThrow(() -> new IllegalArgumentException("유저를 찾을 수 없습니다."));
        Post post = new Post(user, request.getTitle(), request.getContent(), request.getImageUrl());
        return postRepository.save(post);
    }

    public void deletePost(Long postId) {
        postRepository.deleteById(postId);
    }

    // 매일 자정에 만료된 게시글 삭제
    @Scheduled(cron = "0 0 0 * * *")  // 매일 자정(00:00:00)에 실행
    public void deleteExpiredPosts() {
        List<Post> expiredPosts = postRepository.findAllByExpireDateBefore(LocalDateTime.now());
        postRepository.deleteAll(expiredPosts);
    }


    public List<Post> getAllPosts(){
        return postRepository.findAll();
    }

    public Post getPostById(Long id){
        return postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("유저를 찾을 수 없습니다.") );
    }
}