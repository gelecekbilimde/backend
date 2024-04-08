package org.gelecekbilimde.scienceplatform.post.repository;

import org.gelecekbilimde.scienceplatform.post.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;


public interface CategoryRepository extends JpaRepository<Category, Long> {
	Optional<Category> findByName(String name);

	Set<Category> findAllByParentId(Long parentId);

	boolean existsByName(String name);

	boolean existsByParentId(Long id);
}
