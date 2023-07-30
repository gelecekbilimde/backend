package org.gelecekbilimde.scienceplatform.repository;

import org.gelecekbilimde.scienceplatform.model.PostProcess;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostProcessRepository extends JpaRepository<PostProcess, Integer> {
	Optional <PostProcess> findByPostProcess(Integer id);
}
