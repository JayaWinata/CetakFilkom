import java.util.InputMismatchException;
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
            // adminInput();
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

    public static void pilihObjek(Pelanggan pelanggan) {
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
        pelanggan.setLembaran(lembaran[pilih - 1]);
        System.out.println(batas);
    }

    // public static void adminInput() {
    // in.nextLine();
    // System.out.print("Nama: ");
    // String namaAdmin = in.nextLine();
    // System.out.print("No. Telp: ");
    // String noTelpAdmin = in.nextLine();
    // Admin admin = new Admin(namaAdmin, noTelpAdmin);
    // System.out.print("Password: ");
    // String password = in.nextLine();
    // if (admin.isAdmin(password)) {
    // do {
    // System.out.println(batas);
    // admin.tampilkanData();
    // System.out.println(batas);
    // System.out.println("1. Atur harga diskon");
    // System.out.println("2. Atur harga print (hitam- putih)");
    // System.out.println("3. Atur harga print (berwarna)");
    // System.out.println("4. Atur harga fotokopi (hitam- putih)");
    // System.out.println("5. Atur harga fotokopi (berwarna)");
    // System.out.print("Pilih opsi: ");
    // int opsi = in.nextInt();
    // switch (opsi) {
    // case 1:
    // System.out.println("1. Atur harga diskon");
    // System.out.print("Diskon (%): ");
    // int diskon = in.nextInt();
    // admin.setDiskon(diskon);
    // break;
    // case 2:
    // System.out.println("2. Atur harga print (hitam- putih)");
    // System.out.print("Harga / lembar: ");
    // int harga = in.nextInt();
    // Order.setHargaPrint(harga);
    // break;
    // case 3:
    // System.out.println("3. Atur harga print (berwarna)");
    // System.out.print("Harga / lembar: ");
    // harga = in.nextInt();
    // Order.setHargaPrintWarna(harga);
    // break;
    // case 4:
    // System.out.println("4. Atur harga fotokopi (hitam- putih)");
    // System.out.print("Harga / lembar: ");
    // harga = in.nextInt();
    // Order.setHargaCopy(harga);
    // break;
    // case 5:
    // System.out.println("5. Atur harga fotokopi (berwarna)");
    // System.out.println("Harga / lembar: ");
    // harga = in.nextInt();
    // Order.setHargaCopyWarna(harga);
    // break;
    // default:
    // System.err.println("Masukkan input dengan benar!");
    // break;
    // }
    // System.out.println("Apakah anda ingin mengulang menu ini? (ketik 1 untuk
    // mengulang)");
    // int loop = in.nextInt();
    // if (loop != 1)
    // return;
    // } while (true);
    // } else {
    // System.out.println(batas);
    // System.err.println("Password salah! \n");
    // }
    // }

    public static void pembeliInput() {
        in.nextLine();
        System.out.print("Nama: ");
        String namaPembeli = in.nextLine();
        Pelanggan pembeli;
        System.out.print("Apakah anda memiliki kartu member? (1 untuk ya) ");
        int member = in.nextInt();
        if (member == 1) {
            System.out.println("Tanggal berapa anda mulai berlanggan?");
            System.out.print("Tanggal: ");
            int tanggal = in.nextInt();
            System.out.print("Bulan: ");
            int bulan = in.nextInt();
            System.out.print("Tahun: ");
            int tahun = in.nextInt();
            pembeli = (Member) new Member(namaPembeli, tanggal, bulan, tahun);
        } else {
            pembeli = new Guest(namaPembeli);
        }
        System.out.println(batas);
        pilihObjek(pembeli);
        Order newOrder = pembeli.makeOrder();
        do {
            System.out.println("1. Print (hitam- putih)");
            System.out.println("2. Print (berwarna)");
            System.out.println("3. Fotokopi (hitam- putih)");
            System.out.println("4. Fotokopi (berwarna)");
            System.out.print("Pilih opsi: ");
            int opsi = in.nextInt();
            int biaya;
            System.out.println(batas);
            switch (opsi) {
                case 1:
                    System.out.println("1. Print (hitam- putih)");
                    biaya = Order.getHargaPrint();
                    newOrder.setPesanan(1);
                    break;
                case 2:
                    System.out.println("2. Print (berwarna)");
                    biaya = Order.getHargaPrintWarna();
                    newOrder.setPesanan(2);
                    break;
                case 3:
                    System.out.println("3. Fotokopi (hitam- putih)");
                    biaya = Order.getHargaCopy();
                    newOrder.setPesanan(3);
                    break;
                case 4:
                    System.out.println("4. Fotokopi (berwarna)");
                    biaya = Order.getHargaCopyWarna();
                    newOrder.setPesanan(4);
                    break;
                default:
                    throw new InputMismatchException("Masukkan input dengan benar!");
            }
            System.out.print("Berapa kali anda ingin mencetak / mencopy? ");
            int kuantitas = in.nextInt();
            if (pembeli.getLembaran() instanceof Buku) {
                System.out.println("Pilih halaman: ");
                System.out.print("Halaman awal: ");
                int halAwal = in.nextInt();
                System.out.println("Halaman akhir: ");
                int halAkhir = in.nextInt();
                if ((halAkhir - halAwal) < 0 || (halAkhir - halAwal) > pembeli.getLembaran().getTotalHalaman()) {
                    throw new ArithmeticException("Jumlah halaman tidak sesuai!");
                }
                newOrder.setBiaya((halAkhir - halAwal), kuantitas, biaya);
            } else {

            }
            System.out.println(
                    "Apakah anda ingin mengulang pemesanan dengan lembaran yang sama? (ketik 1 untuk mengulang)");
            int loop = in.nextInt();
            System.out.println(batas);
            if (loop != 1) {
                // if (pembeli instanceof Member) {
                // ((Member) pembeli).setHistori(pesanan);
                // }
                newOrder.printDetails();
                return;
            }
        } while (true);
    }
}