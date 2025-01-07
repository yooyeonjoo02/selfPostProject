package com.yeonjooProject.selfPostProject.category.repository;

import com.yeonjooProject.selfPostProject.category.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Category> findAllByParentIsNullOrderByDisplayOrder(); // 최상위 카테고리 조회

    List<Category> findAllByParentIdOrderByDisplayOrder(Long parentId); // 특정 부모 카테고리의 자식 조회
}
