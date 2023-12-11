package org.gelecekbilimde.scienceplatform.post.repository;

import org.gelecekbilimde.scienceplatform.post.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
	// https://github.com/gelecekbilimde/gelecekbilimde-backend/issues/52#issuecomment-1850440283

	//example:
	//input:  e
	//output: b, a
	/**
	 * 	Returns all parents of the given category
	 * @param category: categoryId: Long
	 * @return List<Category>
	 */
//	List<Category> ... (Long categoryId);

	//example:
	//input:  a
	//output: b, c, d, e, f, g, h, i, j
	/**
	 * Returns categories that is below the given category
	 *
	 * @param category: categoryId: Long
	 * @return List<Category>
	 */
//	List<Category> ... (Long categoryId);

}
