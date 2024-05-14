package uts;

import java.util.Scanner;

class Catatan {
    private String id;
    private String konten;

    public Catatan(String id, String konten) {
        this.id = id;
        this.konten = konten;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKonten() {
        return konten;
    }

    public void setKonten(String konten) {
        this.konten = konten;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Konten: " + konten;
    }
}

class AplikasiCatatan {
    private Catatan[] catatanArray;
    private int jumlahCatatan;

    public AplikasiCatatan(int ukuran) {
        catatanArray = new Catatan[ukuran];
        jumlahCatatan = 0;
    }

    public void tambahCatatan(String id, String konten) {
        if (jumlahCatatan < catatanArray.length) {
            catatanArray[jumlahCatatan] = new Catatan(id, konten);
            jumlahCatatan++;
        } else {
            System.out.println("Array Blog penuh, tidak bisa menambah Blog baru.");
        }
    }

    public void tampilkanCatatan() {
        if (jumlahCatatan == 0) {
            System.out.println("Tidak ada Blog yang tersedia.");
        } else {
            for (int i = 0; i < jumlahCatatan; i++) {
                System.out.println(catatanArray[i]);
            }
        }
    }

    public void ubahCatatan(String id, String kontenBaru) {
        int indeks = cariCatatan(id);
        if (indeks != -1) {
            catatanArray[indeks].setKonten(kontenBaru);
            System.out.println("Blog berhasil diubah.");
        } else {
            System.out.println("Blog tidak ditemukan.");
        }
    }

    public void hapusCatatan(String id) {
        int indeks = cariCatatan(id);
        if (indeks != -1) {
            for (int i = indeks; i < jumlahCatatan - 1; i++) {
                catatanArray[i] = catatanArray[i + 1];
            }
            catatanArray[jumlahCatatan - 1] = null;
            jumlahCatatan--;
            System.out.println("Blog berhasil dihapus.");
        } else {
            System.out.println("Blog tidak ditemukan.");
        }
    }

    private int cariCatatan(String id) {
        for (int i = 0; i < jumlahCatatan; i++) {
            if (catatanArray[i].getId().equals(id)) {
                return i;
            }
        }
        return -1;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AplikasiCatatan aplikasi = new AplikasiCatatan(100);

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Tambah Blog");
            System.out.println("2. Tampilkan Blog");
            System.out.println("3. Ubah Blog");
            System.out.println("4. Hapus Blog");
            System.out.println("5. Keluar");
            System.out.print("Pilih opsi: ");
            int pilihan = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (pilihan) {
                case 1:
                    System.out.print("Masukkan ID: ");
                    String id = scanner.nextLine();
                    System.out.print("Masukkan BLog: ");
                    String konten = scanner.nextLine();
                    aplikasi.tambahCatatan(id, konten);
                    break;
                case 2:
                    aplikasi.tampilkanCatatan();
                    break;
                case 3:
                    System.out.print("Masukkan ID Blog yang akan diubah: ");
                    String idUbah = scanner.nextLine();
                    System.out.print("Masukkan Blog Baru: ");
                    String kontenBaru = scanner.nextLine();
                    aplikasi.ubahCatatan(idUbah, kontenBaru);
                    break;
                case 4:
                    System.out.print("Masukkan ID Blog yang akan dihapus: ");
                    String idHapus = scanner.nextLine();
                    aplikasi.hapusCatatan(idHapus);
                    break;
                case 5:
                    System.out.println("Keluar dari aplikasi.");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        }
    }
}
