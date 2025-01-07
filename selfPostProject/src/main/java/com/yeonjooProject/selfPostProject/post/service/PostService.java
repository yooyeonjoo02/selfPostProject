package com.yeonjooProject.selfPostProject.post.service;

import com.yeonjooProject.selfPostProject.category.entity.Category;
import com.yeonjooProject.selfPostProject.category.repository.CategoryRepository;
import com.yeonjooProject.selfPostProject.member.entity.Member;
import com.yeonjooProject.selfPostProject.member.repository.MemberRepository;
import com.yeonjooProject.selfPostProject.post.dto.PostRequestDTO;
import com.yeonjooProject.selfPostProject.post.dto.PostResponseDTO;
import com.yeonjooProject.selfPostProject.post.entity.Post;
import com.yeonjooProject.selfPostProject.post.repository.PostRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final CategoryRepository categoryRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public PostResponseDTO createPost(PostRequestDTO requestDTO, Long authorId) {
        // 작성자(Member) 조회
        Member author = memberRepository.findById(authorId)
                .orElseThrow(() -> new RuntimeException("Author not found"));

        // 카테고리(Category) 조회
        Category category = categoryRepository.findById(requestDTO.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        // 게시글 생성
        Post post = Post.builder()
                .title(requestDTO.getTitle())
                .content(requestDTO.getContent())
                .imageUrl(requestDTO.getImageUrl())
                .author(author)
                .category(category)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        postRepository.save(post);

        return new PostResponseDTO(post);
    }

    @Transactional
    public List<PostResponseDTO> getPostsByCategory(Long categoryId) {
        List<Post> posts = postRepository.findAllByCategoryId(categoryId);
        return posts.stream().map(PostResponseDTO::new).collect(Collectors.toList());
    }

    @Transactional
    public PostResponseDTO getPostById(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));
        return new PostResponseDTO(post);
    }

//    @Transactional
//    public PostResponseDTO updatePost(Long postId, PostRequestDTO requestDTO) {
//        Post post = postRepository.findById(postId)
//                .orElseThrow(() -> new RuntimeException("Post not found"));
//
//        post.setTitle(requestDTO.getTitle());
//        post.setContent(requestDTO.getContent());
//        post.setImageUrl(requestDTO.getImageUrl());
//        post.setUpdatedAt(LocalDateTime.now());
//
//        return new PostResponseDTO(postRepository.save(post));
//    }
    @Transactional
    public PostResponseDTO updatePost(Long postId, PostRequestDTO requestDTO) {
        // 게시글 조회
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));

        // 엔티티의 업데이트 메서드 호출
        post.update(requestDTO.getTitle(), requestDTO.getContent(), requestDTO.getImageUrl());

        // 저장 후 DTO로 반환
        return new PostResponseDTO(postRepository.save(post));
    }

    @Transactional
    public void deletePost(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));

        postRepository.delete(post);
    }
}