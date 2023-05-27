package com.example.aeon.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.aeon.models.TrainingKaryawan;
import com.example.aeon.models.karyawan;
import com.example.aeon.models.training;
import com.example.aeon.repository.KaryawanRepository;
import com.example.aeon.repository.TrainingRepository;
import com.example.aeon.response.ResponseData;
import com.example.aeon.service.KaryawanService;
import com.example.aeon.service.TrainingKaryawanService;
import com.example.aeon.service.TrainingService;

@RestController
@RequestMapping("/v1/training-karyawan")
public class TrainingKaryawanController {
    @Autowired
    private TrainingKaryawanService trainingKaryawanService;

    @Autowired
    private KaryawanRepository karyawanRepository;

    @Autowired
    private TrainingRepository trainingRepository;

    @PostMapping
    public ResponseEntity<ResponseData<TrainingKaryawan>> save(@RequestBody TrainingKaryawan karyawanTraining) {
        Optional<karyawan> checkKaryawan = karyawanRepository.findById(karyawanTraining.getKaryawanObj().getId());
        Optional<training> checkTraining = trainingRepository.findById(karyawanTraining.getTrainingObj().getId());
        karyawan existingKaryawan = checkKaryawan.orElse(null);
        training existingTraining = checkTraining.orElse(null);

        ResponseData<TrainingKaryawan> responseData = new ResponseData<>();
        if (existingKaryawan != null && existingTraining != null) {
            TrainingKaryawan savedKaryawan = trainingKaryawanService.save(karyawanTraining);
            responseData.setStatus(true);
            responseData.setPayload(savedKaryawan);
            return ResponseEntity.ok().body(responseData);
        } else {
            responseData.setStatus(false);
            responseData.setPayload(null);
            responseData.getMessage().add("Data Karyawan Atau Training Tidak Ditemukan !");
            return ResponseEntity.badRequest().body(responseData);
        }
    }

    @GetMapping("/list")
    public ResponseEntity<ResponseData<Page<TrainingKaryawan>>> PaginationAndSearchKaryawanByNama(
            @RequestParam(name = "nama", required = false) String nama,
            @RequestParam(name = "tema", required = false) String tema,
            @PageableDefault(page = 0, size = 10) Pageable pageable) {
        Page<TrainingKaryawan> TrainingKaryawanPage;
        if(nama != null && tema != null){
            TrainingKaryawanPage = trainingKaryawanService.searchKaryawanByNamaAndTema(nama, tema, pageable);
        } else if (nama != null){
            TrainingKaryawanPage = trainingKaryawanService.searchKaryawanByNama(nama, pageable);
        } else if (tema != null){
            TrainingKaryawanPage = trainingKaryawanService.searchKaryawanByTema(tema, pageable);
        } else {
            TrainingKaryawanPage = trainingKaryawanService.findAllPage(pageable);
        }
        List<TrainingKaryawan> trainingKaryawanList = TrainingKaryawanPage.getContent();
        Page<TrainingKaryawan> pageTrainingKaryawan = new PageImpl<>(trainingKaryawanList, pageable,
                TrainingKaryawanPage.getTotalElements());
        ResponseData<Page<TrainingKaryawan>> responseData = new ResponseData<>();
        responseData.setStatus(true);
        responseData.setPayload(pageTrainingKaryawan);
        return ResponseEntity.ok().body(responseData);
    }
}
