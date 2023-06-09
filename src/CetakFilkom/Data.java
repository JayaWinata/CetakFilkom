package CetakFilkom;

import java.util.HashMap;
import java.util.StringTokenizer;

import CetakFilkom.Error.*;
import CetakFilkom.Lembaran.*;
import CetakFilkom.Pelanggan.*;
import CetakFilkom.Promo.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Data {
    private static HashMap<String, Pelanggan> mapPelanggan = new HashMap<>();
    private static HashMap<String, Order> mapOrder = new HashMap<>();
    private static HashMap<String, Lembaran> mapMenu = new HashMap<>();
    private static HashMap<String, Promosi> mapPromosi = new HashMap<>();

    public static HashMap<String, Pelanggan> getMapPelanggan() {
        return Data.mapPelanggan;
    }

    public static HashMap<String, Order> getMapOrder() {
        return Data.mapOrder;
    }

    public static HashMap<String, Lembaran> getMapMenu() {
        return Data.mapMenu;
    }

    public static HashMap<String, Promosi> getMapPromosi() {
        return Data.mapPromosi;
    }

    // memindahkan data dari file .txt ke hashmap
    public static void muat() throws FileNotFoundException, IOException, DateOutOfBoundsException {
        muat("Member.txt");
        muat("Guest.txt");
        muat("Menu.txt");
        muat("Promosi.txt");
    }

    public static void muat(String fileName) throws FileNotFoundException, IOException, DateOutOfBoundsException {
        try (BufferedReader read = new BufferedReader(new FileReader("src\\CetakFilkom\\File\\" + fileName))) {
            String line;
            while ((line = read.readLine()) != null) {
                String in = line;
                switch (fileName) {
                    case "Member.txt":
                        String[] dataPelanggan = in.split(",");
                        String[] tanggalMember = dataPelanggan[2].split("/");
                        int tanggal = Integer.parseInt(tanggalMember[2]);
                        int bulan = Integer.parseInt(tanggalMember[1]);
                        int tahun = Integer.parseInt(tanggalMember[0]);
                        if (!mapPelanggan.containsKey(dataPelanggan[0])) {
                            try {
                                Pelanggan pelanggan = new Member(dataPelanggan[1], tanggal, bulan, tahun);
                                pelanggan.tambahSaldo(Integer.parseInt(dataPelanggan[3]));
                                Data.mapPelanggan.put(dataPelanggan[0], pelanggan);
                            } catch (DateOutOfBoundsException e) {
                            }
                        }
                        break;
                    case "Guest.txt":
                        String[] dataGuest = in.split(",");
                        if (!mapPelanggan.containsKey(dataGuest[0])) {
                            Pelanggan pelanggan = new Guest();
                            pelanggan.tambahSaldo(Integer.parseInt(dataGuest[1]));
                            mapPelanggan.put(dataGuest[0], pelanggan);
                        }
                        break;
                    case "Menu.txt":
                        String[] dataMenu = in.split(" ", 2);
                        String[] data = dataMenu[1].split(",", 3);
                        String idMenu = data[0];
                        String namaMenu = data[1];
                        int harga = Integer.parseInt(data[2]);
                        Lembaran temp = null;
                        if (dataMenu[0].equals("Lembaran")) {
                            temp = new Lembaran(namaMenu, harga);
                        } else {
                            temp = new Buku(namaMenu);
                            temp.setHarga(harga);
                        }
                        mapMenu.put(idMenu, temp);
                        break;
                    case "Promosi.txt":
                        StringTokenizer st = new StringTokenizer(in, " ");
                        String tipe = st.nextToken();
                        String[] dataPromo = st.nextToken().split(",");
                        String promoCode = dataPromo[0];
                        String[] tanggalAwal = dataPromo[1].split("/");
                        int tgl = Integer.parseInt(tanggalAwal[2]);
                        int bln = Integer.parseInt(tanggalAwal[1]);
                        int thn = Integer.parseInt(tanggalAwal[0]);
                        String[] tanggalAkhir = dataPromo[2].split("/");
                        int tanggal2 = Integer.parseInt(tanggalAkhir[2]);
                        int bulan2 = Integer.parseInt(tanggalAkhir[1]);
                        int tahun2 = Integer.parseInt(tanggalAkhir[0]);
                        int persenPotongan = Integer.parseInt(dataPromo[3].replace("%", ""));
                        int maksPotongan = Integer.parseInt(dataPromo[4]);
                        int minPembelian = Integer.parseInt(dataPromo[5]);
                        try {
                            Promosi promo = null;
                            if (tipe.equals("DISCOUNT")) {
                                promo = new PercentOffPromo(persenPotongan, minPembelian, 0);
                                promo.setTanggalAwal(tgl, bln, thn);
                                promo.setTanggalAkhir(tanggal2, bulan2, tahun2);
                                promo.setMaksPotongan(maksPotongan);
                            } else if (tipe.equals("CASHBACK")) {
                                promo = new CashbackPromo(persenPotongan, minPembelian, 0);
                                promo.setTanggalAwal(tgl, bln, thn);
                                promo.setTanggalAkhir(tanggal2, bulan2, tahun2);
                                promo.setMaksPotongan(maksPotongan);
                            } else if (tipe.equals("DELIVERY")) {
                                promo = new OngkirPromo(persenPotongan, minPembelian, 0);
                                promo.setTanggalAwal(tgl, bln, thn);
                                promo.setTanggalAkhir(tanggal2, bulan2, tahun2);
                                promo.setMaksPotongan(maksPotongan);
                            }
                            mapPromosi.put(promoCode, promo);
                        } catch (DateOutOfBoundsException e) {
                        }
                        break;
                    default:
                        throw new FileNotFoundException();
                }
            }
        } catch (Exception e) {
        }
    }

    public static void tambah(String key, Object objek, String isiTeks) throws IOException {
        FileWriter writer = null;
        if (objek instanceof Pelanggan && (!mapPelanggan.containsKey(key))) {
            Pelanggan o = null;
            if (objek instanceof Member) {
                o = (Member) objek;
                writer = new FileWriter("src\\CetakFilkom\\File\\Member.txt", true);
            } else {
                o = (Guest) objek;
                writer = new FileWriter("src\\CetakFilkom\\File\\Guest.txt", true);
            }
            mapPelanggan.put(key, o);
            writer.write(isiTeks + "\n");
        } else if (objek instanceof Promosi && (!mapPromosi.containsKey(key))) {
            Promosi o = null;
            if (objek instanceof PercentOffPromo) {
                o = (PercentOffPromo) objek;
            } else if (objek instanceof CashbackPromo) {
                o = (CashbackPromo) objek;
            } else {
                o = (OngkirPromo) objek;
            }
            mapPromosi.put(key, o);
            writer = new FileWriter("src\\CetakFilkom\\File\\Promosi.txt", true);
            writer.write(isiTeks + "\n");
        } else if (objek instanceof Lembaran && (!mapMenu.containsKey(key))) {
            Lembaran o = null;
            if (objek instanceof Buku) {
                o = (Buku) objek;
            } else {
                o = (Lembaran) objek;
            }
            mapMenu.put(key, o);
            writer = new FileWriter("src\\CetakFilkom\\File\\Menu.txt", true);
            writer.write(isiTeks + "\n");
        } else if (objek instanceof Order) {
            mapOrder.put(key, (Order) objek);
        } else {
            return;
        }
        writer.close();
    }

    public static void ubah(String key, String namaFile, String isiTeks, Object o)
            throws IOException, DateOutOfBoundsException {
        File file = new File("src\\CetakFilkom\\File\\" + namaFile);
        File temp = new File("src\\CetakFilkom\\File\\temp.txt");

        try (BufferedReader read = new BufferedReader(new FileReader(file));
                BufferedWriter write = new BufferedWriter(new FileWriter(temp))) {
            String line;
            while ((line = read.readLine()) != null) {
                String in = line;
                if (in.contains(key)) {
                    in = in.replace(in, isiTeks);
                }
                write.write(in);
                write.newLine();
            }
        }

        if (file.delete()) {
            temp.renameTo(file);
        }

        if (namaFile.equals("Member.txt") && mapPelanggan.containsKey(key)) {
            mapPelanggan.put(key, (Member) o);
        } else if (namaFile.equals("Guest.txt") && mapPelanggan.containsKey(key)) {
            mapPelanggan.put(key, (Guest) o);
        } else if (namaFile.equals("Menu.txt") && mapMenu.containsKey(key)) {
            if (o instanceof Lembaran) {
                mapMenu.put(key, (Lembaran) o);
            } else {
                mapMenu.put(key, (Buku) o);
            }
        } else if (namaFile.equals("Promosi.txt") && mapPromosi.containsKey(key)) {
            if (o instanceof PercentOffPromo) {
                mapPromosi.put(key, (PercentOffPromo) o);
            } else if (o instanceof CashbackPromo) {
                mapPromosi.put(key, (CashbackPromo) o);
            } else {
                mapPromosi.put(key, (OngkirPromo) o);
            }
        }
    }

    public static void hapus(String key, String namaFile) throws IOException {
        File file = new File("src\\CetakFilkom\\File\\" + namaFile);
        File temp = new File("src\\CetakFilkom\\File\\temp.txt");

        try (BufferedReader read = new BufferedReader(new FileReader(file));
                BufferedWriter write = new BufferedWriter(new FileWriter(temp))) {
            String line;
            while ((line = read.readLine()) != null) {
                String in = line;
                if (!in.contains(key)) {
                    write.write(in);
                    write.newLine();
                }
            }
        }

        if (file.delete()) {
            temp.renameTo(file);
        }

        switch (namaFile) {
            case "Guest.txt":
            case "Member.txt":
                mapPelanggan.remove(key);
                break;
            case "Menu.txt":
                mapMenu.remove(key);
                break;
            case "Promosi.txt":
                mapPromosi.remove(key);
                break;
        }
    }

    // untuk mengubah nilai saldo pada file .txt
    public static void ubahSaldo(String key, String saldoCurr) throws FileNotFoundException, IOException {
        String namaFile;
        if (mapPelanggan.get(key) instanceof Guest) {
            namaFile = "Guest.txt";
        } else {
            namaFile = "Member.txt";
        }
        File file = new File("src\\CetakFilkom\\File\\" + namaFile);
        File temp = new File("src\\CetakFilkom\\File\\temp.txt");

        try (BufferedReader read = new BufferedReader(new FileReader(file));
                BufferedWriter write = new BufferedWriter(new FileWriter(temp))) {
            String line;
            while ((line = read.readLine()) != null) {
                String in = line;
                if (in.contains(key)) {
                    String saldo;
                    if (namaFile.equals("Member.txt")) {
                        saldo = (in.split(","))[3];
                    } else {
                        saldo = (in.split(","))[1];
                    }
                    in = in.replace(saldo, saldoCurr);
                }
                write.write(in);
                write.newLine();
            }
        }

        if (file.delete()) {
            temp.renameTo(file);
        }
    }

    // test
    public static void main(String[] args) {
        try {
            Guest g = new Guest();
            muat();
            tambah("G002", g, "G002 g");
            System.out.println(mapPelanggan.keySet());
            hapus("G002", "Guest.txt");
            System.out.println(mapPelanggan.keySet());
        } catch (Exception e) {
            System.out.println(e);
            StackTraceElement[] st = e.getStackTrace();
            for (StackTraceElement r : st) {
                System.out.println(r.getLineNumber());
            }
        }

    }
}