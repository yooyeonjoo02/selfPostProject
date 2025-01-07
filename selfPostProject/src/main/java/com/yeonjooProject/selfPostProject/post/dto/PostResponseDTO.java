package com.yeonjooProject.selfPostProject.post.dto;

import com.yeonjooProject.selfPostProject.post.entity.Post;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PostResponseDTO {
    private Long id;
    private String title;
    private String content;
    private String imageUrl;
    private Long authorId;
    private Long categoryId;
    private String authorName;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public PostResponseDTO(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.imageUrl = post.getImageUrl();
        this.authorId = post.getAuthor().getId();
        this.categoryId = post.getCategory().getId();
        this.authorName = post.getAuthor().getUsername();
        this.createdAt = post.getCreatedAt();
        this.updatedAt = post.getUpdatedAt();
    }
}
