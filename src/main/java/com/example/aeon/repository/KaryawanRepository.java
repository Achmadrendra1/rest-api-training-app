package com.example.aeon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

import com.example.aeon.models.karyawan;

@Repository
public interface KaryawanRepository extends JpaRepository<karyawan, Long> {
    Page<karyawan> findByNamaContainingIgnoreCase(String nama, Pageable pageable);
}