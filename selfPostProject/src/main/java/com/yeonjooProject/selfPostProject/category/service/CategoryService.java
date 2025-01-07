package com.yeonjooProject.selfPostProject.category.service;

import com.yeonjooProject.selfPostProject.category.dto.CategoryRequestDTO;
import com.yeonjooProject.selfPostProject.category.dto.CategoryResponseDTO;
import com.yeonjooProject.selfPostProject.category.entity.Category;
import com.yeonjooProject.selfPostProject.category.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Transactional
    public CategoryResponseDTO createCategory(CategoryRequestDTO requestDTO) {
        // 부모 카테고리 확인
        Category parent = null;
        if (requestDTO.getParentId() != null) {
            parent = categoryRepository.findById(requestDTO.getParentId())
                    .orElseThrow(() -> new RuntimeException("Parent category not found"));
        }

        // 카테고리 생성
        Category category = Category.builder()
                .name(requestDTO.getName())
                .displayOrder(requestDTO.getDisplayOrder())
                .parent(parent)
                .build();

        categoryRepository.save(category);

        return new CategoryResponseDTO(category, List.of());
    }

    @Transactional(readOnly = true)
    public List<CategoryResponseDTO> getTopLevelCategories() {
        List<Category> categories = categoryRepository.findAllByParentIsNullOrderByDisplayOrder();
        return categories.stream()
                .map(category -> new CategoryResponseDTO(category, getChildCategories(category.getId())))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<CategoryResponseDTO> getChildCategories(Long parentId) {
        List<Category> children = categoryRepository.findAllByParentIdOrderByDisplayOrder(parentId);
        return children.stream()
                .map(child -> new CategoryResponseDTO(child, getChildCategories(child.getId())))
                .collect(Collectors.toList());
    }

//    @Transactional
//    public CategoryResponseDTO updateCategory(Long id, CategoryRequestDTO requestDTO) {
//        Category category = categoryRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Category not found"));
//
//        category.setName(requestDTO.getName());
//        category.setDisplayOrder(requestDTO.getDisplayOrder());
//
//        Category updatedCategory = categoryRepository.save(category);
//        return new CategoryResponseDTO(updatedCategory, getChildCategories(updatedCategory.getId()));
//    }
    @Transactional
    public CategoryResponseDTO updateCategory(Long id, CategoryRequestDTO requestDTO) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        // update 메서드를 사용하여 필드 변경
        category.update(requestDTO.getName(), requestDTO.getDisplayOrder());

        // 변경된 엔티티를 저장하고 DTO로 반환
        Category updatedCategory = categoryRepository.save(category);
        return new CategoryResponseDTO(updatedCategory, getChildCategories(updatedCategory.getId()));
    }


    @Transactional
    public void deleteCategory(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        categoryRepository.delete(category);
    }
}
