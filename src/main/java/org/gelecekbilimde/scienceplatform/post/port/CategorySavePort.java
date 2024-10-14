package org.gelecekbilimde.scienceplatform.post.port;

import org.gelecekbilimde.scienceplatform.post.model.Category;

import java.util.List;

public interface CategorySavePort {

	void save(Category category);

	void saveAll(List<Category> categories);

}
