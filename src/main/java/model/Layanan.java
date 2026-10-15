/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ASUS
 */
public abstract class Layanan {
    protected String jenisLayanan;
    protected double hargaDasar;

    public Layanan(String jenisLayanan, double hargaDasar) {
        this.jenisLayanan = jenisLayanan;
        this.hargaDasar = hargaDasar;
    }

    public abstract double hitungTotalBiaya(int jumlah);
    
    public String getJenisLayanan() { return jenisLayanan; }
    public double getHargaDasar() { return hargaDasar; }
}
