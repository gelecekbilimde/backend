package org.gelecekbilimde.scienceplatform.postProcess.repository;

import org.gelecekbilimde.scienceplatform.postProcess.model.PostProcess;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostProcessRepository extends JpaRepository<PostProcess, Integer> {
	Optional <PostProcess> findByPostProcess(Integer id);
}
