package com.example.aeon.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.aeon.models.karyawan;
import com.example.aeon.repository.KaryawanRepository;

@Service
public class KaryawanService {

    @Autowired
    private KaryawanRepository karyawanRepository;

    public List<karyawan> getAllKaryawan() {
        return karyawanRepository.findAll();
    }

    public Page<karyawan> findAllKaryawan(Pageable pageable) {
        Page<karyawan> result = karyawanRepository.findAll(pageable);
        if (result != null && result.hasContent()) {
            return result;
        } else {
            throw new ResourceNotFoundException("Data Karyawan Tidak Ditemukan");
        }
    }

    public Page<karyawan> searchKaryawanByNama(String nama, Pageable pageable) {
        Page<karyawan> result = karyawanRepository.findByNamaContainingIgnoreCase(nama, pageable);
        if (result != null && result.hasContent()) {
            return result;
        } else {
            throw new ResourceNotFoundException("Data Karyawan Dengan Nama " + nama + " Tidak Ditemukan");
        }
    }

    public karyawan getKaryawanById(Long id) {
        return karyawanRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Data Karyawan Dengan ID " + id + " Tidak Ditemukan"));
    }

    public karyawan createKaryawan(karyawan karyawan) {
        return karyawanRepository.save(karyawan);
    }

    public karyawan updateKaryawan(karyawan karyawan) {
        Optional<karyawan> existingKaryawan = karyawanRepository.findById(karyawan.getId());
        if (existingKaryawan.isPresent()) {
            return karyawanRepository.save(karyawan);
        } else {
            throw new RuntimeException("Data Karyawan dengan ID " + karyawan.getId() + " tidak ditemukan");
        }
    }

    public karyawan deleteKaryawan(Long id) {
        karyawan karyawan = karyawanRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Data Karyawan dengan ID " + id
                        + " tidak ditemukan"));

        karyawanRepository.delete(karyawan);
        return null;
    }
}
