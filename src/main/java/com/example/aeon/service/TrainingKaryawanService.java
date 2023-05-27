package com.example.aeon.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import com.example.aeon.models.TrainingKaryawan;
import com.example.aeon.repository.TrainingKaryawanRepository;

@Service
public class TrainingKaryawanService {
    @Autowired
    private TrainingKaryawanRepository trainingKaryawanRepository;

    public TrainingKaryawan save(TrainingKaryawan karyawanTraining) {
        return trainingKaryawanRepository.save(karyawanTraining);
    }

    public List<TrainingKaryawan> getAllKaryawan() {
        return trainingKaryawanRepository.findAll();
    }

    public Page<TrainingKaryawan> findAllPage(Pageable pageable) {
        Page<TrainingKaryawan> result = trainingKaryawanRepository.findAll(pageable);
        if (result != null && result.hasContent()) {
            return result;
        } else {
            throw new ResourceNotFoundException("Data Training Karyawan Tidak Ditemukan");
        }
    }

    public Page<TrainingKaryawan> searchKaryawanByNamaAndTema(String nama, String tema, Pageable pageable) {
        Page<TrainingKaryawan> result = trainingKaryawanRepository
                .findByKaryawanObjNamaContainingIgnoreCaseAndTrainingObjTemaContainingIgnoreCase(nama, tema, pageable);
                System.out.println(result);
        if (result != null && result.hasContent()) {
            return result;
        } else {
            throw new ResourceNotFoundException("Data Training Karyawan Dengan Nama " + nama + " Dan Tema " + tema + " Tidak Ditemukan");
        }
    }

    public Page<TrainingKaryawan> searchKaryawanByNama(String nama, Pageable pageable) {
        Page<TrainingKaryawan> result = trainingKaryawanRepository
                .findByKaryawanObjNamaContainingIgnoreCase(nama, pageable);
                System.out.println(result);
        if (result != null && result.hasContent()) {
            return result;
        } else {
            throw new ResourceNotFoundException("Data Training Karyawan Dengan Nama " + nama + " Tidak Ditemukan");
        }
    }

    public Page<TrainingKaryawan> searchKaryawanByTema(String tema, Pageable pageable) {
        Page<TrainingKaryawan> result = trainingKaryawanRepository
                .findByTrainingObjTemaContainingIgnoreCase(tema, pageable);
                System.out.println(result);
        if (result != null && result.hasContent()) {
            return result;
        } else {
            throw new ResourceNotFoundException("Data Training Karyawan Dengan Tema " + tema + " Tidak Ditemukan");
        }
    }
}
