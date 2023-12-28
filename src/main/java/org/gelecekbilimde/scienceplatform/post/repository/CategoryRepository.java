package org.gelecekbilimde.scienceplatform.post.repository;

import org.gelecekbilimde.scienceplatform.post.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface CategoryRepository extends JpaRepository<Category, Long> {

	//example:
	//input:  e
	//output: b, a
//	/**
//	 * 	Returns parents of the given category
//	 * @param categoryId: Long
//	 * @return Set<Category>
//	 */
//	Set<Category> findCategoryByChildren(Long categoryId);

	//example:
	//input:  e
	//output: b, a
	/**
	 * 	Returns parents of the given category
	 * @param categoryId: Long
	 * @return Set<Category>
	 */
	Set<Category> findCategoryByChildrenId(Long categoryId);
	Category findCategoryById(Long categoryId);

	//example:
	//input:  a
	//output: b, c, d, e, f, g, h, i, j
	/**
	 * Returns categories that is below the given category
	 *
	 * @param categoryId: Long
	 * @return Set<Category>
	 */
//	Set<Category> ... (Long categoryId);

}
