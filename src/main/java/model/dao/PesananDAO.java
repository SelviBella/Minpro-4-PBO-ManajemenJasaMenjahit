/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dao;

import config.Koneksi;
import model.Pesanan;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
/**
 *
 * @author ASUS
 */
public class PesananDAO {
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("menjahitPU");
    
    public void save(Pesanan pesanan) {
        Connection conn = Koneksi.getConnection();
        
        String sqlPelanggan = "INSERT INTO pelanggan (nama, nomor_telepon, alamat) VALUES (?, ?, ?)";
        String sqlPesanan = "INSERT INTO pesanan (id_pelanggan, jenis_layanan, detail_layanan, jenis_pakaian, ukuran, jumlah, total_bayar, status_pesanan) VALUES (LAST_INSERT_ID(), ?, ?, ?, ?, ?, ?, ?)";
        
        try {
            conn.setAutoCommit(false); 
            
            try (PreparedStatement psPel = conn.prepareStatement(sqlPelanggan)) {
                psPel.setString(1, pesanan.getPelanggan().getNama());
                psPel.setString(2, pesanan.getPelanggan().getNomorTelepon());
                psPel.setString(3, pesanan.getPelanggan().getAlamat());
                psPel.executeUpdate();
            }
            
            try (PreparedStatement psPes = conn.prepareStatement(sqlPesanan)) {
                psPes.setString(1, pesanan.getJenisLayanan());
                psPes.setString(2, pesanan.getDetailLayanan());
                psPes.setString(3, pesanan.getJenisPakaian());
                psPes.setString(4, pesanan.getUkuran());
                psPes.setInt(5, pesanan.getJumlah());
                psPes.setDouble(6, pesanan.getTotalBayar());
                psPes.setString(7, pesanan.getStatusPesanan());
                psPes.executeUpdate();
            }
            
            conn.commit(); 
        } catch (SQLException e) {
            try { conn.rollback(); } catch (SQLException ex) { System.out.println(ex.getMessage()); }
            System.out.println("[Error] Gagal simpan JDBC: " + e.getMessage());
        }
    }

 
    public List<Pesanan> getAllPesanan() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT p FROM PesananEntity p", Pesanan.class).getResultList();
        } finally {
            em.close();
        }
    }

    public boolean updateStatus(int id, String statusBaru) {
        String sql = "UPDATE pesanan SET status_pesanan = ? WHERE id_pesanan = ?";
        try (Connection conn = Koneksi.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, statusBaru);
            pstmt.setInt(2, id);
            int barisTerubah = pstmt.executeUpdate();
            return barisTerubah > 0;
        } catch (SQLException e) {
            System.out.println("[Error] Gagal update JDBC: " + e.getMessage());
            return false;
        }
    }

    public boolean delete(int id) {
        String sql = "DELETE FROM pesanan WHERE id_pesanan = ?";
        try (Connection conn = Koneksi.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            int barisTerhapus = pstmt.executeUpdate();
            return barisTerhapus > 0;
        } catch (SQLException e) {
            System.out.println("[Error] Gagal delete JDBC: " + e.getMessage());
            return false;
        }
    }
    
    public static void closeFactory() {
        if (emf != null && emf.isOpen()) emf.close();
    }
}
