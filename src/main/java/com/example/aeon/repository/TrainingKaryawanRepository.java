package com.example.aeon.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.aeon.models.TrainingKaryawan;

@Repository
// public interface TrainingKaryawanRepository extends
// JpaRepository<TrainingKaryawan, Long>{
// // Page<karyawan_training>
// findByKaryawan_NamaContainingAndTemaContaining(String namaKaryawan,
// // String tema, Pageable pageable);
// }

public interface TrainingKaryawanRepository extends JpaRepository<TrainingKaryawan, Long> {
    Page<TrainingKaryawan> findByKaryawanObjNamaContainingIgnoreCaseAndTrainingObjTemaContainingIgnoreCase(
            String nama,
            String tema,
            Pageable pageable
    );

    Page<TrainingKaryawan> findByKaryawanObjNamaContainingIgnoreCase(String nama, Pageable pageable);
    Page<TrainingKaryawan> findByTrainingObjTemaContainingIgnoreCase(String tema, Pageable pageable);
}
