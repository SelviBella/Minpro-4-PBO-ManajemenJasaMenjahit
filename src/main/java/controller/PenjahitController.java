/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import model.Pelanggan;
import model.Layanan;
import model.Pesanan;
import model.dao.PesananDAO;
import java.util.List;
/**
 *
 * @author ASUS
 */
public class PenjahitController {
     private final PesananDAO pesananDAO = new PesananDAO();

    public void tambahPesanan(Pelanggan pelanggan, Layanan layanan, String detail, String jenis, String ukuran, int jumlah) {
        double total = layanan.hitungTotalBiaya(jumlah);
        Pesanan baru = new Pesanan(pelanggan, layanan.getJenisLayanan(), detail, jenis, ukuran, jumlah, total);
        pesananDAO.save(baru);
    }

    public List<Pesanan> getDaftarPesanan() {
        return pesananDAO.getAllPesanan(); 
    }

    public boolean updateStatus(int id, String statusBaru) {
        return pesananDAO.updateStatus(id, statusBaru);
    }

    public boolean hapusPesanan(int id) {
        return pesananDAO.delete(id); 
    }
}
