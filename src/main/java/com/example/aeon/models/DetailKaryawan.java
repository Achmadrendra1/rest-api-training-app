package com.example.aeon.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "detail_karyawan")
public class DetailKaryawan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nik", nullable = false)
    @NotNull(message = "NIK harus diisi")
    private String nik;

    @Column(name = "npwp", nullable = false)
    @NotNull(message = "NPWP harus diisi")
    private String npwp;

    @OneToOne
    @JoinColumn(name = "id_karyawan")
    private karyawan karyawan;

    // getter and setter

    // constructors
    public DetailKaryawan() {}

    public DetailKaryawan(String nik, String npwp, karyawan karyawan) {
        this.nik = nik;
        this.npwp = npwp;
        this.karyawan = karyawan;
    }
}
