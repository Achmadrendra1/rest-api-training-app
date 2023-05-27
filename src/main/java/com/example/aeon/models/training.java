package com.example.aeon.models;

import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name = "training")
public class training {
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nama_pengajar")
    @NotNull(message = "Nama Pengajar harus diisi")
    private String namaPengajar;

    @Column(name = "tema")
    @NotNull(message = "Tema Training harus diisi")
    private String tema;

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
    public training() {}

    public training(String nama_pengajar, String tema) {
        this.namaPengajar = nama_pengajar;
        this.tema = tema;
        
    }
}
