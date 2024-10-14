package org.gelecekbilimde.scienceplatform.post.repository;

import org.gelecekbilimde.scienceplatform.post.model.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;


public interface CategoryRepository extends JpaRepository<CategoryEntity, Long>, JpaSpecificationExecutor<CategoryEntity> {

	List<CategoryEntity> findAllByParentId(Long parentId);

	Optional<CategoryEntity> findBySlug(String slug);

	boolean existsBySlug(String slug);

	boolean existsByParentId(Long parentId);

}
