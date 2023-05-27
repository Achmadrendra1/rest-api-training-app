package com.example.aeon.service;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.aeon.models.training;
import com.example.aeon.repository.TrainingRepository;

@Service
public class TrainingService {
    @Autowired
    private TrainingRepository trainingRepository;

    public Page<training> findAllTraining(Pageable pageable) {
        Page<training> result = trainingRepository.findAll(pageable);
        if (result != null && result.hasContent()) {
            return result;
        } else {
            throw new ResourceNotFoundException("Data Training Tidak Ditemukan");
        }
    }

    public Page<training> searchTrainingByNama(String nama, Pageable pageable) {
        Page<training> result = trainingRepository.findByNamaPengajarContainingIgnoreCase(nama, pageable);
        if (result != null && result.hasContent()) {
            return result;
        } else {
            throw new ResourceNotFoundException("Data Dengan Nama Pengajar " + nama + " Tidak Ditemukan");
        }
    }

    public training getTrainingById(Long id) {
        return trainingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Data Training Dengan ID " + id + " Tidak Ditemukan"));
    }

    public training createTraining(training training) {
        return trainingRepository.save(training);
    }

    public training updateTraining(training training) {
        Optional<training> existingTraining = trainingRepository.findById(training.getId());
        if (existingTraining.isPresent()) {
            return trainingRepository.save(training);
        } else {
            throw new RuntimeException("Data Training dengan ID " + training.getId() + " tidak ditemukan");
        }
    }

    public training deleteTraining(Long id) {
        training training = trainingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Data Training dengan ID " + id + " tidak ditemukan"));

        trainingRepository.delete(training);
        return null;
    }


}
