package com.example.aeon.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.aeon.models.training;
import com.example.aeon.response.ResponseData;
import com.example.aeon.service.TrainingService;

@RestController
@RequestMapping("v1/training")
public class TrainingContoller {
    @Autowired
    private TrainingService trainingService;

    @GetMapping("/{id}")
    public ResponseEntity<ResponseData<training>> getKaryawanById(@PathVariable(value = "id") Long id) {
        training training = trainingService.getTrainingById(id);
        ResponseData<training> responseData = new ResponseData<>();
        responseData.setStatus(true);
        responseData.setPayload(training);
        return ResponseEntity.ok().body(responseData);
    }

    @GetMapping("/list")
    public ResponseEntity<ResponseData<Page<training>>> PaginationAndSearchTrainingByNama(
            @RequestParam(name = "nama", required = false) String nama,
            @PageableDefault(page = 0, size = 10) Pageable pageable) {
        Page<training> trainingPage;
        if (nama == null) {
            trainingPage = trainingService.findAllTraining(pageable);
        } else {
            trainingPage = trainingService.searchTrainingByNama(nama, pageable);
        }
        List<training> trainingList = trainingPage.getContent();
        Page<training> pageTraining = new PageImpl<>(trainingList, pageable, trainingPage.getTotalElements());
        ResponseData<Page<training>> responseData = new ResponseData<>();
        responseData.setStatus(true);
        responseData.setPayload(pageTraining);
        return ResponseEntity.ok().body(responseData);
    }

    @PostMapping
    public ResponseEntity<ResponseData<training>> createTraining(@Valid @RequestBody training training, Errors errors) {
        ResponseData<training> responseData = new ResponseData<>();
        if (errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {
                if (error instanceof FieldError) {
                    responseData.getMessage().add(error.getDefaultMessage());
                }
            }
            responseData.setStatus(false);
            return ResponseEntity.badRequest().body(responseData);
        }
        training trainingData = trainingService.createTraining(training);
        responseData.setStatus(true);
        responseData.setPayload(trainingData);
        return ResponseEntity.created(null).body(responseData);
    }

    @PutMapping
    public ResponseEntity<ResponseData<training>> updateTraining(@Valid @RequestBody training training, Errors errors) {
        ResponseData<training> responseData = new ResponseData<>();
        if (errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {
                if (error instanceof FieldError) {
                    responseData.getMessage().add(error.getDefaultMessage());
                }
            }
            responseData.setStatus(false);
            return ResponseEntity.badRequest().body(responseData);
        }
        training trainingData = trainingService.updateTraining(training);
        responseData.setStatus(true);
        responseData.setPayload(trainingData);
        return ResponseEntity.created(null).body(responseData);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseData<training>> deleteKaryawan(@PathVariable(value = "id") Long id) {
        training training = trainingService.deleteTraining(id);
        ResponseData<training> responseData = new ResponseData<>();
        responseData.setStatus(true);
        responseData.setPayload(training);
        return ResponseEntity.ok().body(responseData);
    }

}
