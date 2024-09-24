package chairing.chairing.controller.community;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import chairing.chairing.domain.community.Post;
import chairing.chairing.dto.community.PostRequest;
import chairing.chairing.dto.community.CommentRequest;
import chairing.chairing.service.community.PostService;
import chairing.chairing.service.community.CommentService;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/community")
public class CommunityController {

    private final PostService postService;
    private final CommentService commentService;

    @GetMapping
    public String listPosts(Model model) {
        List<Post> posts = postService.getAllPosts();
        model.addAttribute("posts", posts);
        return "community/postList";
    }

    @GetMapping("/post/{id}")
    public String viewPost(@PathVariable Long id, Model model) {
        Post post = postService.getPostById(id);
        model.addAttribute("post", post);
        model.addAttribute("commentRequest", new CommentRequest());
        return "community/postDetail";
    }

    @GetMapping("/createPost")
    public String createPostForm(Model model) {
        model.addAttribute("postRequest", new PostRequest());
        return "community/createPost";
    }

    @PostMapping("/createPost")
    public String createPost(@ModelAttribute PostRequest postRequest, Principal principal) {
        postService.createPost(principal, postRequest);
        return "redirect:/community";
    }

    @PostMapping("/post/{id}/comment")
    public String createComment(@PathVariable Long id, @ModelAttribute CommentRequest commentRequest,Principal principal) {
        commentRequest.setPostId(id);
        commentService.createComment(principal, commentRequest);
        return "redirect:/community/post/" + id;
    }

    @PostMapping("/post/{id}/delete")
    public String deletePost(@PathVariable Long id) {
        postService.deletePost(id);
        return "redirect:/community";
    }

    @PostMapping("/comment/{id}/delete")
    public String deleteComment(@PathVariable Long id) {
        commentService.deleteComment(id);
        return "redirect:/community";
    }
}
