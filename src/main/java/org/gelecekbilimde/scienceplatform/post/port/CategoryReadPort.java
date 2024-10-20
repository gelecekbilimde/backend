package org.gelecekbilimde.scienceplatform.post.port;

import org.gelecekbilimde.scienceplatform.common.model.BasePage;
import org.gelecekbilimde.scienceplatform.common.model.BasePageable;
import org.gelecekbilimde.scienceplatform.post.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryReadPort {

	List<Category> findAll();

	BasePage<Category> findAll(BasePageable basePageable);

	List<Category> findAllByParentId(Long parentId);

	Optional<Category> findById(Long id);

	Optional<Category> findBySlug(String slug);

	boolean notExistsById(Long id);

	boolean existsByParentId(Long parentId);

	boolean existsBySlug(String slug);

}
