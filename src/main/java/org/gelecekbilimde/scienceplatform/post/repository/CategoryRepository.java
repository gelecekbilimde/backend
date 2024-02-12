package org.gelecekbilimde.scienceplatform.post.repository;

import org.gelecekbilimde.scienceplatform.post.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;


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
}
