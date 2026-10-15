/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import jakarta.persistence.*;

@Entity
@Table(name = "pesanan")
/**
 *
 * @author ASUS
 */
public class Pesanan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pesanan")
    private int idPesanan;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_pelanggan")
    private Pelanggan pelanggan;

    @Column(name = "jenis_layanan")
    private String jenisLayanan;

    @Column(name = "detail_layanan")
    private String detailLayanan;

    @Column(name = "jenis_pakaian")
    private String jenisPakaian;

    @Column(name = "ukuran")
    private String ukuran;

    @Column(name = "jumlah")
    private int jumlah;

    @Column(name = "total_bayar")
    private double totalBayar;

    @Column(name = "status_pesanan")
    private String statusPesanan;

    public Pesanan() {} 

    public Pesanan(Pelanggan pelanggan, String jenisLayanan, String detailLayanan, String jenisPakaian, String ukuran, int jumlah, double totalBayar) {
        this.pelanggan = pelanggan;
        this.jenisLayanan = jenisLayanan;
        this.detailLayanan = detailLayanan;
        this.jenisPakaian = jenisPakaian;
        this.ukuran = ukuran;
        this.jumlah = jumlah;
        this.totalBayar = totalBayar;
        this.statusPesanan = "Diproses";
    }


    public int getIdPesanan() { return idPesanan; }
    public Pelanggan getPelanggan() { return pelanggan; }
    public String getJenisLayanan() { return jenisLayanan; }
    public String getDetailLayanan() { return detailLayanan; }
    public String getJenisPakaian() { return jenisPakaian; }
    public String getUkuran() { return ukuran; }
    public int getJumlah() { return jumlah; }
    public double getTotalBayar() { return totalBayar; }
    public String getStatusPesanan() { return statusPesanan; }
    
    public void tampilkanInvoice() {
        System.out.println("ID Pesanan     : " + idPesanan);
        System.out.println("Nama           : " + pelanggan.getNama());
        System.out.println("No. Telepon    : " + pelanggan.getNomorTelepon());
        System.out.println("Alamat         : " + pelanggan.getAlamat());
        System.out.println("Jenis Pakaian  : " + jenisPakaian);
        System.out.println("Ukuran         : " + ukuran);
        System.out.println("Jumlah         : " + jumlah + " pcs");
        System.out.println("Jenis Jasa     : " + jenisLayanan + " (" + detailLayanan + ")");
        System.out.println("Total Bayar    : Rp " + totalBayar);
        System.out.println("Status         : " + statusPesanan);
        System.out.println("----------------------------------------------");
    }

    public void tampilkanInvoice(boolean ringkas) {
        if (ringkas) {
            System.out.println("[ID: " + idPesanan + "] " + pelanggan.getNama() + " - " + jenisPakaian + " (" + jumlah + " pcs) -> Status: " + statusPesanan);
        } else {
            tampilkanInvoice();
        }
    }
}
