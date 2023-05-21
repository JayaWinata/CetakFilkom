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
    private static StringBuilder output = new StringBuilder();
    private static HashMap<String, Pelanggan> mapPelanggan = new HashMap<>();
    private static HashMap<String, Order> mapOrder = new HashMap<>();
    private static HashMap<String, Lembaran> mapMenu = new HashMap<>();
    private static HashMap<String, Promosi> mapPromosi = new HashMap<>();

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
                    tambahCart(input);
                } else if (input.contains("REMOVE_FROM_CART")) {
                    hapusCart(input);
                } else if (input.contains("APPLY_PROMO")) {
                    terapkanPromo(input);
                }
            } catch (Exception e) {
                StackTraceElement[] a = e.getStackTrace();
                for (StackTraceElement b : a) {
                    System.out.println("Line number: " + b.getLineNumber() + ". " + e.getMessage());
                }
                continue;
            }
        }
        // System.out.println("\n\n" + output.toString());
        System.out.println(mapOrder.get("A001").getBiaya());
        in.close();
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
                Pelanggan pelanggan = new Member(data[1], tanggal, bulan, tahun);
                pelanggan.setSaldo(Integer.parseInt(data[3]));
                mapPelanggan.put(data[0], pelanggan);
            } catch (DateOutOfBoundsException e) {
                throw new DateOutOfBoundsException("Date out of bounds.");
            }
            output.append("CREATE MEMBER SUCCESS: " + data[0] + " " + data[1] + "\n");
        } else {
            output.append("CREATE MEMBER FAILED: " + data[0] + " IS EXISTS \n");
        }
    }

    private static void buatGuest(String input) {
        input = input.substring(13);
        String[] data = input.split("\\|");
        if (!mapPelanggan.containsKey(data[0])) {
            Pelanggan pelanggan = new Guest();
            pelanggan.setSaldo(Integer.parseInt(data[1]));
            mapPelanggan.put(data[0], pelanggan);
            output.append("CREATE GUEST SUCCESS: " + data[0] + "\n");
        } else {
            output.append("CREATE GUEST FAILED: " + data[0] + " IS EXISTS\n");
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
            output.append("CREATE MENU FAILED: " + idMenu + " IS EXISTS\n");
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
        output.append("CREATE MENU SUCCESS: " + idMenu + " " + namaMenu + "\n");
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
            output.append("CREATE PROMO " + tipe + " FAILED: " + promoCode + " IS EXISTS\n");
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
            output.append("CREATE PROMO " + tipe + " SUCCESS: " + promoCode + "\n");
        } catch (DateOutOfBoundsException e) {
            throw new DateOutOfBoundsException("Date out of bounds.");
        }
    }

    private static void tambahCart(String input) throws QuantityException {
        String[] data = input.split(" ");
        String idPelanggan = data[1];
        String idMenu = data[2];
        int qty = Integer.parseInt(data[3]);
        if ((!mapPelanggan.containsKey(idPelanggan)) || (!mapMenu.containsKey(idMenu))) {
            output.append("ADD_TO_CART FAILED: NON EXISTENT CUSTOMER OR MENU\n");
            return;
        }
        Pelanggan pelanggan = mapPelanggan.get(idPelanggan);
        mapOrder.put(idPelanggan, new Order(pelanggan));
        Order order = mapOrder.get(idPelanggan);
        Lembaran menu = mapMenu.get(idMenu);
        if (!order.getCartQty().containsKey(idMenu)) {
            order.addToCart(idMenu, menu, qty);
            output.append("ADD_TO_CART SUCCESS: " + qty + " " + menu.getMenu() + " IS ADDED\n");
        } else {
            order.addToCart(idMenu, menu, qty);
            output.append("ADD_TO_CART SUCCESS: " + qty + " " + menu.getMenu() + " QUANTITY IS INCREMENTED\n");
        }
    }

    private static void hapusCart(String input) {
        String[] data = input.split(" ");
        String idPelanggan = data[1];
        String idMenu = data[2];
        int qty = Integer.parseInt(data[3]);
        if ((!mapPelanggan.containsKey(idPelanggan)) || (!mapMenu.containsKey(idMenu))) {
            output.append("REMOVE_FROM_CART FAILED: NON EXISTENT CUSTOMER OR MENU\n");
            return;
        }
        Order order = mapOrder.get(idPelanggan);
        Lembaran lembaran = order.getCart().get(idMenu);
        int status = order.hapusCart(idMenu, qty);
        if (status == 1) {
            output.append("REMOVE_FROM_CART SUCCESS: " + lembaran.getMenu() + " QUANTITY IS DECREMENTED\n");
        } else {
            output.append("REMOVE_FROM_LAST SUCCESS: " + lembaran.getMenu() + " IS REMOVED\n");
        }
    }

    private static void terapkanPromo(String input) {
        String[] data = input.split(" ");
        String idPelanggan = data[1];
        String idPromo = data[2];
        Promosi promo = mapPromosi.get(idPromo);
        if (promo.isExpired()) {
            output.append("APPLY_PROMO FAILED: " + idPromo + " " + "IS EXPIRED\n");
            return;
        }
        Order order = mapOrder.get(idPelanggan);
        try {
            order.applyPromo(promo);
        } catch (PromotionNotMetExcpetion e) {
            output.append("APPLY_PROMO FAILED: " + idPromo + "\n");
        }
    }
}
