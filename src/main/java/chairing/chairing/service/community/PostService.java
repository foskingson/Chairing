package chairing.chairing.service.community;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import chairing.chairing.domain.community.Post;
import chairing.chairing.domain.user.User;
import chairing.chairing.dto.community.PostRequest;
import chairing.chairing.repository.community.PostRepository;
import chairing.chairing.repository.user.UserRepository;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    private final String UPLOAD_DIR = "img/"; // img 폴더 경로
    
    public void createPost(Principal principal, PostRequest postRequest) {
        // User 객체 가져오기 (principal로부터)
        User user = userRepository.findByUsername(principal.getName()).orElseThrow(() -> new IllegalArgumentException("유저를 찾을 수 없습니다."));
    
        // 파일 저장 로직
        String imageUrl = saveFile(postRequest.getImageUrl()); // postRequest.getImageUrl()가 MultipartFile
    
        // Post 객체 생성
        Post post = new Post(user, postRequest.getTitle(), postRequest.getContent(), imageUrl);
        postRepository.save(post);
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




    private String saveFile(MultipartFile file) {
        if (file.isEmpty()) {
            return null; // 파일이 비어 있으면 null 반환
        }
    
        try {
            // 파일 저장 경로 설정
            Path path = Paths.get(UPLOAD_DIR + file.getOriginalFilename());
            Files.createDirectories(path.getParent()); // 디렉토리 생성
            Files.copy(file.getInputStream(), path); // 파일 복사
    
            // 저장된 파일의 URL 반환 (상대 경로)
            return "/" + UPLOAD_DIR + file.getOriginalFilename();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("파일 저장 중 오류가 발생했습니다."); // 명시적 예외 처리
        }
    }
}