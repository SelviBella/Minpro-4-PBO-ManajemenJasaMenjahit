/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ASUS
 */
public class LayananJahitBaru extends Layanan implements DiskonKhusus {
    private String jenisBahan; 

    public LayananJahitBaru(double hargaDasar, String jenisBahan) {
        super("Jahit Pakaian Baru", hargaDasar); 
        this.jenisBahan = jenisBahan;
    }

    @Override
    public double hitungTotalBiaya(int jumlah) {
        double tambahanBahan = jenisBahan.equalsIgnoreCase("Sutra") ? 30000 : 0;
        double total = (this.hargaDasar + tambahanBahan) * jumlah;
        return total - hitungDiskon(total); 
    }

    @Override
    public double hitungDiskon(double totalBiaya) {
        if (totalBiaya > 250000) {
            return totalBiaya * 0.10;
        }
        return 0;
    }

    public String getJenisBahan() { return jenisBahan; }
}
