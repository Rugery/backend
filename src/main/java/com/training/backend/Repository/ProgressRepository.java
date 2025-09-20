package com.training.backend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.training.backend.Entity.Progress;

public interface ProgressRepository extends JpaRepository<Progress, Long> {

}
