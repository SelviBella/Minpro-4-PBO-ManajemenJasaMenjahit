/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import controller.PenjahitController;
import model.*;
import java.util.Scanner;
/**
 *
 * @author ASUS
 */
public class PenjahitView {
    private final PenjahitController controller = new PenjahitController();
    private final Scanner input = new Scanner(System.in);

    public void tampilkanMenu() {
        int pilihan = 0;
        do {
            System.out.println("\n=== MINPRO 4 - SISTEM JASA MENJAHIT DATABASE ===");
            System.out.println("1. Tambah Pesanan Baru");
            System.out.println("2. Tampilkan Semua Pesanan");
            System.out.println("3. Ubah Status Pesanan");
            System.out.println("4. Batalkan Pesanan");
            System.out.println("5. Keluar");
            System.out.print("Pilih menu: ");
            
            if (input.hasNextInt()) {
                pilihan = input.nextInt(); input.nextLine(); 
            } else {
                System.out.println("[Error] Input menu harus berupa angka bulat!");
                input.nextLine(); continue;
            }

            switch (pilihan) {
                case 1 -> prosesTambah();
                case 2 -> prosesTampil();
                case 3 -> prosesUpdate();
                case 4 -> prosesHapus();
                case 5 -> System.out.println("Keluar dari program. Terima kasih!");
                default -> System.out.println("Pilihan tidak tersedia!");
            }
        } while (pilihan != 5);
    }

    private void prosesTambah() {
        System.out.print("Nama Pelanggan: ");
        String nama = input.nextLine();
        if (nama.trim().isEmpty() || nama.matches("[0-9]+")) {
            System.out.println("[Error] Nama pelanggan tidak boleh angka murni."); return;
        }

        System.out.print("Nomor Telepon : ");
        String telp = input.nextLine();
        if (!telp.matches("[0-9]+") || telp.length() < 10 || telp.length() > 13) {
            System.out.println("[Error] Nomor telepon harus angka (10-13 digit)."); return;
        }

        System.out.print("Alamat        : ");
        String alamat = input.nextLine();
        if (alamat.trim().isEmpty() || alamat.matches("[0-9]+")) {
            System.out.println("[Error] Alamat tidak boleh angka murni."); return;
        }
        
        Pelanggan pelanggan = new Pelanggan(nama, telp, alamat);

        System.out.println("Pilih Layanan: 1. Jahit Baru | 2. Permak");
        System.out.print("Pilihan (1/2): ");
        int opsi = input.hasNextInt() ? input.nextInt() : 0; input.nextLine();

        Layanan layananTerpilih;
        String detailLayanan = "";
        if (opsi == 1) {
            System.out.print("Jenis Bahan Kain (Biasa/Sutra): ");
            detailLayanan = input.nextLine();
            if (!detailLayanan.equalsIgnoreCase("Biasa") && !detailLayanan.equalsIgnoreCase("Sutra")) {
                System.out.println("[Error] Bahan hanya boleh 'Biasa' atau 'Sutra'."); return;
            }
            layananTerpilih = new LayananJahitBaru(150000, detailLayanan);
        } else if (opsi == 2) {
            System.out.print("Tingkat Kesulitan (Ringan/Berat): ");
            detailLayanan = input.nextLine();
            if (!detailLayanan.equalsIgnoreCase("Ringan") && !detailLayanan.equalsIgnoreCase("Berat")) {
                System.out.println("[Error] Kesulitan hanya boleh 'Ringan' atau 'Berat'."); return;
            }
            layananTerpilih = new LayananPermak(50000, detailLayanan);
        } else {
            System.out.println("[Error] Opsi layanan tidak valid!"); return;
        }

        System.out.println("Pilih Jenis Pakaian: 1. Kemeja | 2. Gaun | 3. Celana");
        System.out.print("Pilihan (1/2/3): ");
        int opsiPakaian = input.hasNextInt() ? input.nextInt() : 0; input.nextLine();
        String jenis = switch (opsiPakaian) {
            case 1 -> "Kemeja"; case 2 -> "Gaun"; case 3 -> "Celana";
            default -> "";
        };
        if (jenis.isEmpty()) { System.out.println("[Error] Jenis pakaian tidak valid."); return; }

        System.out.print("Ukuran (S/M/L/XL): ");
        String ukuran = input.nextLine();
        if (!ukuran.equalsIgnoreCase("S") && !ukuran.equalsIgnoreCase("M") && !ukuran.equalsIgnoreCase("L") && !ukuran.equalsIgnoreCase("XL")) {
            System.out.println("[Error] Ukuran hanya boleh S, M, L, atau XL."); return;
        }

        System.out.print("Jumlah (pcs): ");
        int jml = input.hasNextInt() ? input.nextInt() : 0; input.nextLine();
        if (jml <= 0) { System.out.println("[Error] Jumlah pcs minimal harus 1."); return; }

        controller.tambahPesanan(pelanggan, layananTerpilih, detailLayanan, jenis, ukuran, jml);
        System.out.println("Pesanan Baru Berhasil Tersimpan ke Database!");
    }

    private void prosesTampil() {
        System.out.println("\n--- FORMAT NOTA ---\n1. Lengkap | 2. Ringkas");
        System.out.print("Pilih format (1/2): ");
        int format = input.hasNextInt() ? input.nextInt() : 1; input.nextLine();

        System.out.println("\n--- DAFTAR ANTRIAN DATABASE ---");
        var list = controller.getDaftarPesanan();
        if (list.isEmpty()) {
            System.out.println("Antrean di database kosong.");
        } else {
            for (Pesanan p : list) {
                if (format == 2) p.tampilkanInvoice(true);
                else p.tampilkanInvoice();
            }
        }
    }

    private void prosesUpdate() {
        System.out.print("Masukkan ID Pesanan: ");
        int id = input.hasNextInt() ? input.nextInt() : 0; input.nextLine();
        if (id <= 0) { System.out.println("[Error] ID tidak valid."); input.nextLine(); return; }
        
        System.out.print("Masukkan Status Baru (Selesai/Diambil): ");
        String status = input.nextLine();

        if (status.equalsIgnoreCase("Selesai") || status.equalsIgnoreCase("Diambil")) {
            if (controller.updateStatus(id, status)) System.out.println("Status Database Berhasil Diperbarui!");
            else System.out.println("ID Pesanan tidak ditemukan!");
        } else {
            System.out.println("[Error] Status tidak valid!");
        }
    }

    private void prosesHapus() {
        System.out.print("Masukkan ID Pesanan yang ingin dihapus: ");
        int id = input.hasNextInt() ? input.nextInt() : 0; input.nextLine();
        if (id <= 0) { System.out.println("[Error] ID tidak valid."); input.nextLine(); return; }

        if (controller.hapusPesanan(id)) System.out.println("Pesanan Berhasil Terhapus dari Database!");
        else System.out.println("ID Pesanan tidak ditemukan!");
    }
}
