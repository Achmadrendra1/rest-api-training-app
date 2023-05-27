package com.example.aeon.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import javax.persistence.*;

import java.sql.Date;

import javax.persistence.Column;

@Getter
@Setter
@Entity
@Table(name = "Karyawan")
public class karyawan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nama", nullable = false)
    @NotNull(message = "Nama harus diisi")
    private String nama;

    @Column(name = "alamat", nullable = false)
    @NotNull(message = "Alamat harus diisi")
    private String alamat;

    @Column(name = "jk", nullable = false)
    @NotNull(message = "Jenis Kelamin harus diisi")
    private String jk;

    @Column(name = "status", nullable = false)
    @NotNull(message = "Status harus diisi")
    private String status;

    @Column(name = "dob", nullable = false)
    @NotNull(message = "Tanggal lahir harus diisi")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-DD")
    private Date dob;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    // getter and setter

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    @PreRemove
    protected void onDelete() {
        deletedAt = LocalDateTime.now();
    }

    // constructors
    public karyawan() {}

    public karyawan(String nama, String alamat, String jk, String status, Date dob) {
        this.nama = nama;
        this.alamat = alamat;
        this.jk = jk;
        this.status = status;
        this.dob = dob;
    }
}
