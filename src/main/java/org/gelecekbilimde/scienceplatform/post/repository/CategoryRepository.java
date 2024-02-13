package org.gelecekbilimde.scienceplatform.post.repository;

import org.gelecekbilimde.scienceplatform.post.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;


public interface CategoryRepository extends JpaRepository<Category, Long> {

	/**
	 * Returns categories that is below the given category
	 *
	 * @param categoryId: Long
	 * @return Set<Category>
	 */
	Category findCategoryById(Long categoryId);

	/**
	 * Returns the category with the given name
	 *
	 * @param name: String
	 * @return Category
	 */
	Category findCategoryByName(String name);

	/**
	 * Returns the category with the given parentId
	 *
	 * @param parentId: Long
	 * @return Set<Category>
	 */
	Set<Category> findCategoriesByParentId(Long parentId);
}
