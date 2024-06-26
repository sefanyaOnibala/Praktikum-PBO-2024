import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.io.IOException;
import Roti.*;

public final class App {
    public static final int idCounter = 1;
    private static Scanner scanner = new Scanner(System.in);
    private static ArrayList<roti> daftarRoti = new ArrayList<>();

    public static final void main(String[] args) throws IOException {

        while (true) {
            clearScreen();
            System.out.println("=================================================================");
            System.out.println("              Selamat datang di Toko Roti Onibala                ");
            System.out.println("=================================================================");
            System.out.println(" [1] Tambah Roti");
            System.out.println(" [2] Lihat Semua Roti");
            System.out.println(" [3] Perbarui Informasi Roti");
            System.out.println(" [4] Hapus Roti");
            System.out.println(" [5] Keluar");
            System.out.print(" Pilih menu: ");

            int pilihan = scanner.nextInt();
            scanner.nextLine();

            switch (pilihan) {
                case 1:
                    tambahRoti();
                    waitForEnter();
                    break;
                case 2:
                    lihatSemuaRoti();
                    waitForEnter();
                    break;
                case 3:
                    perbaruiRoti();
                    waitForEnter();
                    break;
                case 4:
                    hapusRoti();
                    waitForEnter();
                    break;
                case 5:
                    System.out.println("Terima kasih telah menggunakan layanan Toko Roti Onibala");
                    return;
                default:
                    System.out.println("Pilihan tidak valid, silakan coba lagi");
                    waitForEnter();
            }
        }
    }

    private static void tambahRoti() {
        clearScreen();
        System.out.println("=================================================================");
        System.out.println("              Selamat datang di Toko Roti Onibala                ");
        System.out.println("=================================================================");
        System.out.print("Masukkan nama roti    : ");
        String nama = scanner.nextLine();
        System.out.print("Masukkan harga roti   : ");
        int harga = scanner.nextInt();
        System.out.print("Masukkan stok roti    : ");
        int stok = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Roti Tawar atau Roti Manis (Tawar/Manis) : ");
        String jenisRoti = scanner.nextLine();

        if (jenisRoti.equalsIgnoreCase("Tawar")) {
            System.out.print("Masukkan jenis roti tawar (Misal: Gandum/Roti Sobek): ");
            String jenis = scanner.nextLine();
            RotiTawar roti = new RotiTawar(nama, harga, stok, jenis);
            daftarRoti.add(roti);
        } else if (jenisRoti.equalsIgnoreCase("Manis")) {
            System.out.print("Masukkan rasa roti manis (Misal: Coklat/Keju): ");
            String rasa = scanner.nextLine();
            RotiManis roti = new RotiManis(nama, harga, stok, rasa);
            daftarRoti.add(roti);
        } else {
            System.out.println("Jenis roti tidak valid");
            return;
        }
        System.out.println("Roti berhasil ditambahkan");
    }

    private static void lihatSemuaRoti() {
        clearScreen();
        System.out.println("=================================================================");
        System.out.println("              Selamat datang di Toko Roti Onibala                ");
        System.out.println("=================================================================");
    
        if (daftarRoti.isEmpty()) {
            System.out.println("Tidak ada roti yang tersedia");
        } else {
            System.out.println("                      DAFTAR ROTI                        ");
            for (roti roti : App.daftarRoti) {
                //menampilkan informasi roti
                System.out.println(roti.getId() + ". " + "Roti " + roti.getNama());
                System.out.println("   Harga    : Rp. " + roti.getHarga());
                System.out.println("   Stok     : " + roti.getStok());
                //memeriksa apakah roti adalah RotiManis atau RotiTawar
                if (roti instanceof RotiManis) {
                    RotiManis rotiManis = (RotiManis) roti;
                    System.out.println("   Rasa     : " + rotiManis.getRasa());
                } else if (roti instanceof RotiTawar) {
                    RotiTawar rotiTawar = (RotiTawar) roti;
                    System.out.println("   Jenis    : " + rotiTawar.getJenis());
                }
            }
        }
    }

    private static void perbaruiRoti() {
        clearScreen();
        System.out.println("=================================================================");
        System.out.println("              Selamat datang di Toko Roti Onibala                ");
        System.out.println("=================================================================");

        if (daftarRoti.isEmpty()) {
            System.out.println("Tidak ada roti yang tersedia untuk diperbarui");
            return;
        }

        System.out.print("Masukkan ID roti yang ingin diperbarui: ");
        int idRoti = scanner.nextInt();
        scanner.nextLine();

        for (roti roti : daftarRoti) {
            if (roti.getId() == idRoti) {
                System.out.print("Masukkan nama baru untuk roti     : ");
                String newNama = scanner.nextLine();
                System.out.print("Masukkan harga baru untuk roti    : ");
                int newHarga = scanner.nextInt();
                System.out.print("Masukkan stok baru untuk roti     : ");
                int newStok = scanner.nextInt();
                scanner.nextLine();

                roti.setNama(newNama);
                roti.setHarga(newHarga);
                roti.setStok(newStok);
                System.out.println("Informasi roti berhasil diperbarui");
                return;
            }
        }
        System.out.println("Roti dengan ID tersebut tidak ditemukan");
    }

   private static void hapusRoti() {
    clearScreen();
    System.out.println("=================================================================");
    System.out.println("              Selamat datang di Toko Roti Onibala                ");
    System.out.println("=================================================================");
    
    // Periksa apakah daftarRoti kosong
    if (daftarRoti.isEmpty()) {
        System.out.println("Tidak ada roti yang tersedia untuk dihapus");
        return;
    }
    
    try {

        System.out.print("Masukkan ID roti yang ingin dihapus: ");
        int idRoti = scanner.nextInt();
        scanner.nextLine();
        
        //mencari roti berdasarkan ID
        roti rotiToDelete = null;
        for (roti roti : daftarRoti) {
            if (roti.getId() == idRoti) {
                rotiToDelete = roti;
                break;
            }
        }
  
        if (rotiToDelete != null) {
            // Cek apakah stok roti lebih dari 1
            if (rotiToDelete.getStok() > 1) {
                // Jika stok lebih dari 1, kurangi stok roti
                rotiToDelete.setStok(rotiToDelete.getStok() - 1);
                System.out.println("Stok roti berhasil dikurangi");
            } else {
                // Jika stok hanya 1, hapus roti dari daftar
                daftarRoti.remove(rotiToDelete);
                System.out.println("Roti berhasil dihapus");
            }
        } else {
            // Jika roti dengan ID tersebut tidak ditemukan
            System.out.println("Error: Roti dengan ID " + idRoti + " tidak ditemukan");
        }

    } catch (InputMismatchException e) {
        //kesalahan jika input ID roti bukan bilangan bulat
        System.out.println("Error: Masukkan ID roti dalam bentuk bilangan bulat.");
    } catch (NoSuchElementException e) {
        //kesalahan jika tidak ada input tersedia
        System.out.println("Error: Tidak ada input tersedia.");
    } catch (Exception e) {
        //kesalahan umum lainnya
        System.out.println("Error: Terjadi kesalahan saat menghapus roti.");
        e.printStackTrace();
    }
}

    
    //metode static untuk membersihkan layar
    private static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
    

    private static void waitForEnter() {
        System.out.print("Tekan Enter untuk melanjutkan...");
        try {
            System.in.read();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
        


