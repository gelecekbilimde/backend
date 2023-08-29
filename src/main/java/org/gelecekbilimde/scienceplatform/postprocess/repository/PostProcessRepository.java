package org.gelecekbilimde.scienceplatform.postprocess.repository;

import org.gelecekbilimde.scienceplatform.postprocess.model.PostProcess;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostProcessRepository extends JpaRepository<PostProcess, Integer> {
	Optional <PostProcess> findByPostProcess(Integer id);
}
