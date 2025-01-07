package com.yeonjooProject.selfPostProject.post.dto;

import lombok.Data;

@Data
public class PostRequestDTO {
    private String title; // 게시글 제목
    private String content; // 게시글 내용
    private String imageUrl; // 이미지 URL (선택적)
    private Long categoryId; // 카테고리 ID
}
