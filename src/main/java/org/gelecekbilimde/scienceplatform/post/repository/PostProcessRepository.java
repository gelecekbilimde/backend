package org.gelecekbilimde.scienceplatform.post.repository;

import org.gelecekbilimde.scienceplatform.post.model.PostProcess;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostProcessRepository extends JpaRepository<PostProcess, Integer> {
}
