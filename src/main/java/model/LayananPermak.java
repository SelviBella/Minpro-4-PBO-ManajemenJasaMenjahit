/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ASUS
 */
public class LayananPermak extends Layanan {
    private String tingkatKesulitan; 

    public LayananPermak(double hargaDasar, String tingkatKesulitan) {
        super("Permak / Perbaikan Pakaian", hargaDasar);
        this.tingkatKesulitan = tingkatKesulitan;
    }

    @Override
    public double hitungTotalBiaya(int jumlah) {
        double tambahanSulit = tingkatKesulitan.equalsIgnoreCase("Berat") ? 15000 : 0;
        return (this.hargaDasar + tambahanSulit) * jumlah;
    }

    public String getTingkatKesulitan() { return tingkatKesulitan; }
}
