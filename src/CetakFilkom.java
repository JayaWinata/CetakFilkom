/*
 * 225150200111038 Jaya Winata
 * 225150201111031 Tiara Calista Kusumawardani A.
 * 225150201111032 Najwa Muthia Aziz
 * 225150200111045 Sonia Anindhiya Putri Kurniawan
 * 225150207111073 Dayang Alyssa Raisaputri
 */

import java.util.HashMap;
import java.util.Scanner;
import java.util.StringTokenizer;

public class CetakFilkom {
    private static Scanner in = new Scanner(System.in);
    private static HashMap<String, Pelanggan> mapPelanggan = new HashMap<>();
    private static HashMap<String, Lembaran> mapLembaran = new HashMap<>();
    private static HashMap<String, Lembaran> mapMenu = new HashMap<>();
    private static HashMap<String, Promosi> mapPromosi = new HashMap<>();
    private static HashMap<String, Order> mapCart = new HashMap<>();

    public static void main(String[] args) {
        while (in.hasNextLine()) {
            try {
                String input = in.nextLine();
                if (input.contains("CREATE MEMBER")) {
                    buatMember(input);
                } else if (input.contains("CREATE GUEST")) {
                    buatGuest(input);
                } else if (input.contains("CREATE MENU")) {
                    buatMenu(input);
                } else if (input.contains("CREATE PROMO")) {
                    buatPromo(input);
                } else if (input.contains("ADD_TO_CART")) {
                    buatCart(input);

                    // } else if () {
                    // // No.5
                    // }
                }
            } catch (Exception e) {
                StackTraceElement[] a = e.getStackTrace();
                for (StackTraceElement b : a) {
                    System.out.println("Line number: " + b.getLineNumber() + ". " + e.getMessage());
                }
                continue;
            }
        }
    }

    private static void buatPromo(String input) throws DateOutOfBoundsException {
        input = input.substring(12);
        StringTokenizer st = new StringTokenizer(input, " ");
        String tipe = st.nextToken();
        String[] data = st.nextToken().split("\\|");
        String promoCode = data[0];
        String[] tanggalAwal = data[1].split("/");
        int tanggal = Integer.parseInt(tanggalAwal[2]);
        int bulan = Integer.parseInt(tanggalAwal[1]);
        int tahun = Integer.parseInt(tanggalAwal[0]);
        String[] tanggalAkhir = data[2].split("/");
        int tanggal2 = Integer.parseInt(tanggalAkhir[2]);
        int bulan2 = Integer.parseInt(tanggalAkhir[1]);
        int tahun2 = Integer.parseInt(tanggalAkhir[0]);
        int persenPotongan = Integer.parseInt(data[3].replace("%", ""));
        int maksPotongan = Integer.parseInt(data[4]);
        int minPembelian = Integer.parseInt(data[5]);

        if (mapPromosi.containsKey(promoCode)) {
            System.out.println("CREATE PROMO " + tipe + " FAILED: " + promoCode + " IS EXISTS");
            return;
        }
        try {
            Promosi promo = null;
            if (tipe.equals("DISCOUNT")) {
                promo = new PercentOffPromo(persenPotongan, minPembelian, minPembelian * 0.1);
                promo.setTanggalAwal(tanggal, bulan, tahun);
                promo.setTanggalAkhir(tanggal2, bulan2, tahun2);
                promo.setMaksPotongan(maksPotongan);
            } else if (tipe.equals("CASHBACK")) {
                promo = new CashbackPromo(persenPotongan, minPembelian, minPembelian * 0.1);
                promo.setTanggalAwal(tanggal, bulan, tahun);
                promo.setTanggalAkhir(tanggal2, bulan2, tahun2);
                promo.setMaksPotongan(maksPotongan);
            } else if (tipe.equals("DELIVERY")) {
                promo = new OngkirPromo(persenPotongan, minPembelian, minPembelian * 0.1);
                promo.setTanggalAwal(tanggal, bulan, tahun);
                promo.setTanggalAkhir(tanggal2, bulan2, tahun2);
                promo.setMaksPotongan(maksPotongan);
            }
            mapPromosi.put(promoCode, promo);
            System.out.println("CREATE PROMO " + tipe + " SUCCESS: " + promoCode);
        } catch (DateOutOfBoundsException e) {
            throw new DateOutOfBoundsException("Date out of bounds.");
        }
    }

    private static void buatMenu(String input) {
        input = input.substring(12);
        String[] inputSplit = input.split(" ", 2);
        String[] data = inputSplit[1].split("\\|", 3);
        String idMenu = data[0];
        String namaMenu = data[1];
        int harga = Integer.parseInt(data[2]);
        // String customType = data[3];
        if (mapMenu.containsKey(idMenu)) {
            System.out.println("CREATE MENU FAILED: " + idMenu + " already exists");
            return;
        }
        Lembaran temp = null;
        if (namaMenu.contains("MONO") || namaMenu.contains("WARNA")) {
            temp = new Lembaran(namaMenu, harga);
        } else {
            temp = new Buku(namaMenu);
            temp.setHarga(harga);
        }
        mapMenu.put(idMenu, temp);
        System.out.println("CREATE MENU SUCCESS: " + idMenu + " " + namaMenu);
    }

    private static void buatMember(String input) throws DateOutOfBoundsException {
        input = input.substring(14);
        String[] data = input.split("\\|");
        String[] tanggalMember = data[2].split("/");
        int tanggal = Integer.parseInt(tanggalMember[2]);
        int bulan = Integer.parseInt(tanggalMember[1]);
        int tahun = Integer.parseInt(tanggalMember[0]);
        if (!mapPelanggan.containsKey(data[0])) {
            try {
                Pelanggan p = new Member(data[1], tanggal, bulan, tahun);
                p.setSaldo(Integer.parseInt(data[3]));
                mapPelanggan.put(data[0], p);
            } catch (DateOutOfBoundsException e) {
                throw new DateOutOfBoundsException("Date out of bounds.");
            }
            System.out.println("CREATE MEMBER SUCCESS: " + data[0] + " " + data[1]);
        } else {
            System.out.println("CREATE MEMBER FAILED: " + data[0] + " IS EXISTS");
        }
    }

    private static void buatGuest(String input) {
        input = input.substring(12);
        String[] data = input.split("\\|");
        if (!mapPelanggan.containsKey(data[0])) {
            Pelanggan p = new Guest();
            p.setSaldo(Integer.parseInt(data[1]));
            mapPelanggan.put(data[0], p);
            System.out.println("CREATE GUEST SUCCESS: " + data[0]);
        } else {
            System.out.println("CREATE GUEST FAILED: " + data[0] + " IS EXISTS");
        }
    }

    private static void buatCart(String input) {
        String[] data = input.split(" ");
        String idPelanggan = data[1];
        String idMenu = data[2];
        int qty = Integer.parseInt(data[3]);
        Pelanggan pelanggan = mapPelanggan.get(idPelanggan);
        Lembaran menu = mapMenu.get(idMenu);
        if (menu == null) {
            System.out.println("ADD_TO_CART FAILED: NON EXISTENT CUSTOMER OR MENU");
            return;
        }
        if (!pelanggan.getCart().containsKey(idMenu)) {
            pelanggan.addToCart(idMenu, menu, qty);
            System.out.println("ADD_TO_CART SUCCESS: " + qty + " " + menu.getMenu() + " IS ADDED");
        } else {
            pelanggan.addToCart(idMenu, menu, qty);
            System.out.println("ADD_TO_CART SUCCESS: " + qty + " " + menu.getMenu() + " QUANTITY IS INCREMENTED");
        }
    }
}
