package com.yeonjooProject.selfPostProject.post.controller;

import com.yeonjooProject.selfPostProject.post.dto.PostRequestDTO;
import com.yeonjooProject.selfPostProject.post.dto.PostResponseDTO;
import com.yeonjooProject.selfPostProject.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping
    public ResponseEntity<PostResponseDTO> createPost(@RequestBody PostRequestDTO requestDTO,
                                                      @RequestParam Long authorId) {
        return ResponseEntity.ok(postService.createPost(requestDTO, authorId));
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<PostResponseDTO>> getPostsByCategory(@PathVariable Long categoryId) {
        return ResponseEntity.ok(postService.getPostsByCategory(categoryId));
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostResponseDTO> getPostById(@PathVariable Long postId) {
        return ResponseEntity.ok(postService.getPostById(postId));
    }

    @PutMapping("/{postId}")
    public ResponseEntity<PostResponseDTO> updatePost(@PathVariable Long postId,
                                                      @RequestBody PostRequestDTO requestDTO) {
        return ResponseEntity.ok(postService.updatePost(postId, requestDTO));
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<String> deletePost(@PathVariable Long postId) {
        postService.deletePost(postId);
        return ResponseEntity.ok("Post deleted successfully");
    }
}
