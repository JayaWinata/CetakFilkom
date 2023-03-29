import java.util.InputMismatchException;
import java.util.Scanner;

public class CetakFilkom {
    static Scanner in = new Scanner(System.in);
    static String batas = "=".repeat(50);
    static int jumlahBerkas;
    static Promosi[] promo;
    static Lembaran[] lembaran;
    static String pesanan = "";

    public static void main(String[] args) {
        try {
            tambahObjek();
            pembeliInput();
        } catch (Exception e) {
            System.err.println(e);
        }

    }

    public static void tambahObjek() {
        System.out.print("Berapa jumlah lembaran yang akan dibuat: ");
        jumlahBerkas = in.nextInt();
        lembaran = new Lembaran[jumlahBerkas];

        // array judul buku
        String[] judulBuku = { "Introduction Java Daniel Liang", "Elementary Linear Algebra",
                "Data Structure and Algorithm" };

        for (int i = 0; i < jumlahBerkas; i++) {
            System.out.println("1. Buku");
            System.out.println("2. Lembaran");
            System.out.print("Berkas apa yang akan dibuat: ");
            int input = in.nextInt();
            if (input == 1) {
                in.nextLine();

                // menampilkan pilihan judul buku
                System.out.println("Pilih judul buku: (pilih 4 jika selain buku dibawah ini)");
                for (int j = 0; j < judulBuku.length; j++) {
                    System.out.println((j + 1) + ". " + judulBuku[j]);
                }
                System.out.print("Pilihan: ");
                int pilihan = in.nextInt();
                String judul;
                if (pilihan >= 1 && pilihan <= judulBuku.length) {
                    judul = judulBuku[pilihan - 1];
                } else {
                    in.nextLine();
                    System.out.print("Judul: ");
                    judul = in.nextLine();
                }

                System.out.print("Jumlah halaman: ");
                int jmlHalaman = in.nextInt();
                try {
                    lembaran[i] = (Buku) new Buku(judul, jmlHalaman);
                } catch (PageOutOfBoundsException e) {
                    System.err.println(e);
                }
            } else if (input == 2) {
                System.out.print("Jumlah halaman: ");
                int jmlHalaman = in.nextInt();
                try {
                    lembaran[i] = (Lembaran) new Lembaran(jmlHalaman);
                } catch (PageOutOfBoundsException e) {
                    System.err.println(e);
                }
            } else {
                throw new InputMismatchException("Masukkan input dengan benar!");
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

    public static void pembeliInput() throws DateOutOfBoundsException {
        in.nextLine();
        System.out.print("Nama: ");
        String namaPembeli = in.nextLine();
        Pelanggan pembeli;
        System.out.print("Apakah anda memiliki kartu member? (1 untuk ya) ");
        int member = in.nextInt();
        if (member == 1) {
            System.out.println("Tanggal berapa anda mulai berlanggan?");
            System.out.print("Tanggal: ");
            int tanggalMember = in.nextInt();
            System.out.print("Bulan: ");
            int bulanMember = in.nextInt();
            System.out.print("Tahun: ");
            int tahunMember = in.nextInt();
            if ((tanggalMember <= 0 || tanggalMember > 31) || (bulanMember <= 0 || bulanMember > 12)) {
                throw new DateOutOfBoundsException("Tanggal tidak sesuai!");
            }
            pembeli = (Member) new Member(namaPembeli, tanggalMember, bulanMember, tahunMember);
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
                    break;
                case 2:
                    System.out.println("2. Print (berwarna)");
                    biaya = Order.getHargaPrintWarna();
                    break;
                case 3:
                    System.out.println("3. Fotokopi (hitam- putih)");
                    biaya = Order.getHargaCopy();
                    break;
                case 4:
                    System.out.println("4. Fotokopi (berwarna)");
                    biaya = Order.getHargaCopyWarna();
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
                System.out.print("Halaman akhir: ");
                int halAkhir = in.nextInt();
                if ((halAkhir - halAwal) < 0 || (halAkhir - halAwal) > pembeli.getLembaran().getTotalHalaman()) {
                    throw new ArithmeticException("Jumlah halaman tidak sesuai!");
                }
                try {
                    newOrder.setHalamanBuku((halAkhir - halAwal));
                } catch (PageOutOfBoundsException e) {
                    System.err.println(e);
                }
                try {
                    newOrder.setBiaya((halAkhir - halAwal), kuantitas, biaya);
                } catch (QuantityException e) {
                    System.err.println(e);
                }
                newOrder.setPesanan(opsi);
            } else {
                try {
                    newOrder.setHalamanBuku(pembeli.getLembaran().getTotalHalaman());
                } catch (PageOutOfBoundsException e) {
                    System.err.println(e);
                }
                try {
                    newOrder.setBiaya(pembeli.getLembaran().getTotalHalaman(), kuantitas, biaya);
                } catch (QuantityException e) {
                    System.err.println(e);
                }
                newOrder.setPesanan(opsi);
            }
            System.out.print(
                    "1. Checkout\n2. Batalkan pesanan\n3. Tambah pesanan (lembaran yang sama)\nPilih opsi: ");
            int opsi2 = in.nextInt();
            System.out.println(batas);
            switch (opsi2) {
                case 1:
                    newOrder.checkOut();
                    System.out.println("Masukkan tanggal:");
                    System.out.print("Tanggal: ");
                    int tanggal = in.nextInt();
                    System.out.print("Bulan: ");
                    int bulan = in.nextInt();
                    System.out.print("Tahun: ");
                    int tahun = in.nextInt();
                    newOrder.setTanggal(tanggal, bulan, tahun);
                    if (pembeli instanceof Member) {
                        ((Member) pembeli).hitungMemberTime(tanggal, bulan, tahun);
                    }

                    try {
                        int opsiPromo = listPromo();
                        newOrder.applyPromo(promo[opsiPromo - 1]);
                    } catch (PromotionNotMetExcpetion e) {
                        System.err.println(e);
                    } finally {
                        newOrder.pay();
                        newOrder.printDetails();
                    }
                    return;
                case 2:
                    newOrder.cancel();
                    return;
                case 3:
                    break;
                default:
                    throw new InputMismatchException("Masukkan input dengan benar!");
            }
        } while (true);
    }

    public static int listPromo() {
        promo = new Promosi[3];
        promo[0] = new PercentOffPromo(15, 20000, 2000);
        promo[1] = new CashbackPromo(5, 10000, 1000);
        promo[2] = new OngkirPromo(15, 15000, 1500);
        System.out.println("1. Potongan harga 15% (minimal belanja Rp.20000)");
        System.out.println("2. Cashback 5% (minimal belanja Rp.10000)");
        System.out.println("3. Potongan ongkir 15% (minimal belanja Rp.15000)");
        System.out.print("Pilih promo: ");
        int opsi = in.nextInt();
        return opsi;
    }
}