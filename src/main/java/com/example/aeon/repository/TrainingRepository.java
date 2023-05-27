package com.example.aeon.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

import com.example.aeon.models.training;

@Repository
public interface TrainingRepository extends JpaRepository<training, Long> {
    Page<training> findByNamaPengajarContainingIgnoreCase(String namaPengajar, Pageable pageable);
}
