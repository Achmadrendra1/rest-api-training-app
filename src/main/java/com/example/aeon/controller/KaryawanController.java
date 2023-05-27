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

import com.example.aeon.service.KaryawanService;
import com.example.aeon.models.karyawan;
import com.example.aeon.response.ResponseData;


@RestController
@RequestMapping("/v1/karyawan")
public class KaryawanController {
    @Autowired
    private KaryawanService karyawanService;

    @GetMapping("/{id}")
    public ResponseEntity<ResponseData<karyawan>> getKaryawanById(@PathVariable(value = "id") Long id) {
        karyawan karyawan = karyawanService.getKaryawanById(id);
        ResponseData<karyawan> responseData = new ResponseData<>();
        responseData.setStatus(true);
        responseData.setPayload(karyawan);
        return ResponseEntity.ok().body(responseData);
    }

    @GetMapping("/list")
    public ResponseEntity<ResponseData<Page<karyawan>>> PaginationAndSearchKaryawanByNama(
            @RequestParam(name = "nama", required = false) String nama,
            @PageableDefault(page = 0, size = 10) Pageable pageable) {
        Page<karyawan> karyawanPage;
        if (nama == null) {
            karyawanPage = karyawanService.findAllKaryawan(pageable);
        } else {
            karyawanPage = karyawanService.searchKaryawanByNama(nama, pageable);
        }
        List<karyawan> karyawanList = karyawanPage.getContent();
        Page<karyawan> pageKaryawan = new PageImpl<>(karyawanList, pageable, karyawanPage.getTotalElements());
        ResponseData<Page<karyawan>> responseData = new ResponseData<>();
        responseData.setStatus(true);
        responseData.setPayload(pageKaryawan);
        return ResponseEntity.ok().body(responseData);
    }

    @PostMapping
    public ResponseEntity<ResponseData<karyawan>> createKaryawan(@Valid @RequestBody karyawan karyawan, Errors errors) {
        ResponseData<karyawan> responseData = new ResponseData<>();
        if (errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {
                if (error instanceof FieldError) {
                    responseData.getMessage().add(error.getDefaultMessage());
                }
            }
            responseData.setStatus(false);
            return ResponseEntity.badRequest().body(responseData);
        }
        karyawan karyawanData = karyawanService.createKaryawan(karyawan);
        responseData.setStatus(true);
        responseData.setPayload(karyawanData);
        return ResponseEntity.created(null).body(responseData);
    }

    @PutMapping
    public ResponseEntity<ResponseData<karyawan>> updateKaryawan(@Valid @RequestBody karyawan karyawan, Errors errors) {
        ResponseData<karyawan> responseData = new ResponseData<>();
        if (errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {
                if (error instanceof FieldError) {
                    responseData.getMessage().add(error.getDefaultMessage());
                }
            }
            responseData.setStatus(false);
            return ResponseEntity.badRequest().body(responseData);
        }
        karyawan karyawanData = karyawanService.updateKaryawan(karyawan);
        responseData.setStatus(true);
        responseData.setPayload(karyawanData);
        return ResponseEntity.ok().body(responseData);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseData<karyawan>> deleteKaryawan(@PathVariable(value = "id") Long id) {
        karyawan karyawan = karyawanService.deleteKaryawan(id);
        ResponseData<karyawan> responseData = new ResponseData<>();
        responseData.setStatus(true);
        responseData.setPayload(karyawan);
        return ResponseEntity.ok().body(responseData);
    }
}
