/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import jakarta.persistence.*;

@Entity
@Table(name = "pelanggan")
/**
 *
 * @author ASUS
 */
public class Pelanggan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pelanggan")
    private int idPelanggan;

    @Column(name = "nama")
    private String nama;

    @Column(name = "nomor_telepon")
    private String nomorTelepon;

    @Column(name = "alamat")
    private String alamat;

    public Pelanggan() {}

    public Pelanggan(String nama, String nomorTelepon, String alamat) {
        this.nama = nama;
        this.nomorTelepon = nomorTelepon;
        this.alamat = alamat;
    }

    public int getIdPelanggan() { return idPelanggan; }
    public void setIdPelanggan(int idPelanggan) { this.idPelanggan = idPelanggan; }
    public String getNama() { return nama; }
    public String getNomorTelepon() { return nomorTelepon; }
    public String getAlamat() { return alamat; }
}
