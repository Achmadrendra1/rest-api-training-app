package com.example.aeon.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import javax.persistence.*;


@Entity
@Getter
@Setter
@Table(name = "rekening")
public class rekening {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nama")
    @NotNull(message = "Nama Rekening harus diisi")
    private String nama;

    @Column(name = "jenis")
    @NotNull(message = "Jenis Rekening harus diisi")
    private String jenis;

    @Column(name = "nomor")
    @NotNull(message = "Nomor Rekening harus diisi")
    private String nomor;

    @ManyToOne
    @JoinColumn(name = "karyawan_id")
    private karyawan karyawan;

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
    public rekening() {}

    public rekening(String nama, String jenis, String nomor, karyawan karyawan) {
        this.nama = nama;
        this.jenis = jenis;
        this.nomor = nomor;
        this.karyawan = karyawan;
    }
}
