import java.util.Scanner;

public class CetakFilkom {
    static Scanner in = new Scanner(System.in);
    static String batas = "=".repeat(50);
    static int jumlahBerkas;
    static Lembaran[] lembaran;
    static String pesanan = "";

    public static void main(String[] args) {
        tambahObjek();
        System.out.println(batas);
        System.out.println("Masuk sebagai:");
        System.out.println("1. Admin");
        System.out.println("2. Pembeli");
        int input = in.nextInt();
        System.out.println(batas);
        if (input == 1) {
            adminInput();
        } else if (input == 2) {
            pembeliInput();
        } else {
            System.err.println("Masukkan input dengan benar!");
        }
    }

    public static void tambahObjek() {
        System.out.print("Berapa jumlah lembaran yang akan dibuat: ");
        jumlahBerkas = in.nextInt();
        lembaran = new Lembaran[jumlahBerkas];
        for (int i = 0; i < jumlahBerkas; i++) {
            System.out.println("1. Buku");
            System.out.println("2. Lembaran");
            System.out.print("Berkas apa yang akan dibuat: ");
            int input = in.nextInt();
            if (input == 1) {
                in.nextLine();
                System.out.print("Judul: ");
                String judul = in.nextLine();
                System.out.print("Jumlah halaman: ");
                int jmlHalaman = in.nextInt();
                lembaran[i] = (Buku) new Buku(judul, jmlHalaman);
            } else if (input == 2) {
                System.out.print("Jumlah halaman: ");
                int jmlHalaman = in.nextInt();
                lembaran[i] = (Lembaran) new Lembaran(jmlHalaman);
            } else {
                System.err.println("Masukkan input dengan benar!");
            }
            System.out.println(batas);
        }
    }

    public static void pilihObjek(Konsumen konsumen) {
        System.out.println("Daftar lembaran:");
        for (int i = 0; i < jumlahBerkas; i++) {
            if (lembaran[i] instanceof Buku) {
                System.out.printf("%d. Buku: %s (%d halaman)\n", i + 1, (((Buku) lembaran[i]).getJudul()),
                        lembaran[i].getTotalHalaman());
            } else if (lembaran[i] instanceof Lembaran) {
                System.out.printf("%d. Lembaran: (%d halaman)\n", i + 1, lembaran[i].getTotalHalaman());
            }
        }

        System.out.print("Pilih lembaran: ");
        int pilih = in.nextInt();
        konsumen.setBerkas(lembaran[pilih - 1]);
        System.out.println(batas);
    }

    public static void adminInput() {
        in.nextLine();
        System.out.print("Nama: ");
        String namaAdmin = in.nextLine();
        System.out.print("No. Telp: ");
        String noTelpAdmin = in.nextLine();
        Admin admin = new Admin(namaAdmin, noTelpAdmin);
        System.out.print("Password: ");
        String password = in.nextLine();
        if (admin.isAdmin(password)) {
            do {
                System.out.println(batas);
                admin.tampilkanData();
                System.out.println(batas);
                System.out.println("1. Atur harga diskon");
                System.out.println("2. Atur harga print (hitam- putih)");
                System.out.println("3. Atur harga print (berwarna)");
                System.out.println("4. Atur harga fotokopi (hitam- putih)");
                System.out.println("5. Atur harga fotokopi (berwarna)");
                System.out.print("Pilih opsi: ");
                int opsi = in.nextInt();
                switch (opsi) {
                    case 1:
                        System.out.println("1. Atur harga diskon");
                        System.out.print("Diskon (%): ");
                        int diskon = in.nextInt();
                        admin.setDiskon(diskon);
                        break;
                    case 2:
                        System.out.println("2. Atur harga print (hitam- putih)");
                        System.out.print("Harga / lembar: ");
                        int harga = in.nextInt();
                        Order.setHargaPrint(harga);
                        break;
                    case 3:
                        System.out.println("3. Atur harga print (berwarna)");
                        System.out.print("Harga / lembar: ");
                        harga = in.nextInt();
                        Order.setHargaPrintWarna(harga);
                        break;
                    case 4:
                        System.out.println("4. Atur harga fotokopi (hitam- putih)");
                        System.out.print("Harga / lembar: ");
                        harga = in.nextInt();
                        Order.setHargaCopy(harga);
                        break;
                    case 5:
                        System.out.println("5. Atur harga fotokopi (berwarna)");
                        System.out.println("Harga / lembar: ");
                        harga = in.nextInt();
                        Order.setHargaCopyWarna(harga);
                        break;
                    default:
                        System.err.println("Masukkan input dengan benar!");
                        break;
                }
                System.out.println("Apakah anda ingin mengulang menu ini? (ketik 1 untuk mengulang)");
                int loop = in.nextInt();
                if (loop != 1)
                    return;
            } while (true);
        } else {
            System.out.println(batas);
            System.err.println("Password salah! \n");
        }
    }

    public static void pembeliInput() {
        in.nextLine();
        System.out.print("Nama: ");
        String namaPembeli = in.nextLine();
        System.out.print("No. Telp: ");
        String noTelpPembeli = in.nextLine();
        Konsumen pembeli;
        System.out.print("Apakah anda memiliki kartu member? (1 untuk ya) ");
        int member = in.nextInt();
        if (member == 1) {
            pembeli = (Member) new Member(namaPembeli, noTelpPembeli);
        } else {
            pembeli = new Konsumen(namaPembeli, noTelpPembeli);
        }
        System.out.println(batas);
        pilihObjek(pembeli);
        do {
            System.out.println("1. Print (hitam- putih)");
            System.out.println("2. Print (berwarna)");
            System.out.println("3. Fotokopi (hitam- putih)");
            System.out.println("4. Fotokopi (berwarna)");
            System.out.print("Pilih opsi: ");
            int opsi = in.nextInt();
            System.out.println(batas);
            switch (opsi) {
                case 1:
                    System.out.println("1. Print (hitam- putih)");
                    pembeli.setBiaya(pembeli.lembaran.getHargaPrint());
                    pesanan += String.format("Print (hitam- putih)\t\tRp. %d\n", pembeli.lembaran.getHargaPrint());
                    break;
                case 2:
                    System.out.println("2. Print (berwarna)");
                    pembeli.setBiaya(pembeli.lembaran.getHargaPrintWarna());
                    pesanan += String.format("Print (berwarna)\t\tRp. %d\n", pembeli.lembaran.getHargaPrintWarna());
                    break;
                case 3:
                    System.out.println("3. Fotokopi (hitam- putih)");
                    pembeli.setBiaya(pembeli.lembaran.getHargaCopy());
                    pesanan += String.format("Fotokopi (hitam- putih)\t\tRp. %d\n", pembeli.lembaran.getHargaCopy());
                    break;
                case 4:
                    System.out.println("4. Fotokopi (berwarna)");
                    pembeli.setBiaya(pembeli.lembaran.getHargaCopyWarna());
                    pesanan += String.format("Fotokopi (berwarna)\t\tRp. %d\n", pembeli.lembaran.getHargaCopyWarna());
                    break;
                default:
                    System.err.println("Masukkan input dengan benar!");
                    break;
            }
            System.out.println(
                    "Apakah anda ingin mengulang pemesanan dengan lembaran yang sama? (ketik 1 untuk mengulang)");
            int loop = in.nextInt();
            System.out.println(batas);
            if (loop != 1) {
                // if (pembeli instanceof Member) {
                // ((Member) pembeli).setHistori(pesanan);
                // }
                printStruk(pembeli);
                return;
            }
        } while (true);
    }

    public static void printStruk(Pelanggan pelanggan) {
        System.out.println(batas + "\n");
        System.out.printf("%31s", "FILKOM CETAK\n");
        System.out.println("\n" + batas);
        pelanggan.lembaran.tampilkanData();
        System.out.println(batas);
        System.out.println(pesanan);
    }
}