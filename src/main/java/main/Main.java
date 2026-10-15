/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package main;

import model.dao.PesananDAO;
import view.PenjahitView;
/**
 *
 * @author ASUS
 */
public class Main {
    public static void main(String[] args) {
        PenjahitView aplikasi = new PenjahitView();
        try {
            aplikasi.tampilkanMenu();
        } finally {
            PesananDAO.closeFactory();
        }
    }    
}
