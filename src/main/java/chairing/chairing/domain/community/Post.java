package chairing.chairing.domain.community;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

import chairing.chairing.domain.user.User;

@Entity
@Getter
@Setter // 테스트 용도
@NoArgsConstructor
public class Post {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    private String imageUrl;

    @Column(nullable = false)
    private LocalDateTime createDate;

    @Column(nullable = false)
    private LocalDateTime expireDate;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments; // 추가된 부분

    public Post(User user, String title, String content, String imageUrl) {
        this.user = user;
        this.title = title;
        this.content = content;
        this.imageUrl = imageUrl;
        this.createDate = LocalDateTime.now();
        this.expireDate = this.createDate.plusDays(1); // 하루 후 삭제
    }
}