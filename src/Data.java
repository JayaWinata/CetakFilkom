import java.util.HashMap;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import File.*;

public class Data {
    private static HashMap<String, Pelanggan> mapPelanggan = new HashMap<>();
    private static HashMap<String, Order> mapOrder = new HashMap<>();
    private static HashMap<String, Lembaran> mapMenu = new HashMap<>();
    private static HashMap<String, Promosi> mapPromosi = new HashMap<>();
    private static int jumlahMember;
    private static int jumlahGuest;
    private static int jumlahMenu;
    private static int jumlahPromosi;

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
    public static void muat(String fileName) throws FileNotFoundException, IOException, DateOutOfBoundsException {
        try (BufferedReader read = new BufferedReader(new FileReader("src\\File\\" + fileName))) {
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
                                mapPelanggan.put(dataPelanggan[0], pelanggan);
                            } catch (DateOutOfBoundsException e) {
                                throw new DateOutOfBoundsException("Date out of bounds.");
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
                        int harga = Integer.parseInt(data[2].replaceAll("[A-Z|]", ""));
                        Lembaran temp = null;
                        if (namaMenu.contains("MONO") || namaMenu.contains("WARNA")) {
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
                            throw new DateOutOfBoundsException("Date out of bounds.");
                        }
                        break;
                    default:
                        throw new FileNotFoundException();
                }
            }
        }
    }

    public static void tambah(String key, Object objek, String isiTeks) throws IOException {
        FileWriter writer = null;
        if (objek instanceof Pelanggan && (!mapPelanggan.containsKey(key))) {
            Pelanggan o = null;
            if (objek instanceof Member) {
                o = (Member) objek;
                Data.jumlahMember++;
            } else {
                o = (Guest) objek;
                Data.jumlahGuest++;
            }
            mapPelanggan.put(key, o);
            writer = new FileWriter("src\\File\\Member.txt", true);
            writer.write(isiTeks);
        } else if (objek instanceof Promosi && (!mapPromosi.containsKey(key))) {
            Promosi o = null;
            if (objek instanceof PercentOffPromo) {
                o = (PercentOffPromo) objek;
            } else if (objek instanceof CashbackPromo) {
                o = (CashbackPromo) objek;
            } else {
                o = (OngkirPromo) objek;
            }
            Data.jumlahPromosi++;
            mapPromosi.put(key, o);
            writer = new FileWriter("src\\File\\Promosi.txt", true);
            writer.write(isiTeks);
        } else if (objek instanceof Lembaran && (!mapMenu.containsKey(key))) {
            Lembaran o = null;
            if (objek instanceof Buku) {
                o = (Buku) objek;
            } else {
                o = (Lembaran) objek;
            }
            Data.jumlahMenu++;
            mapMenu.put(key, o);
            writer = new FileWriter("src\\File\\Menu.txt", true);
            writer.write(isiTeks);
        } else if (objek instanceof Order) {
            mapOrder.put(key, (Order) objek);
        }
        writer.close();
    }

    public static void ubah(String key, String namaFile, String isiTeks) throws IOException {
        File file = new File("src\\File\\" + namaFile);
        File temp = new File("src\\File\\temp.txt");

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
    }

    public static void hapus(String key, String namaFile) throws IOException {
        File file = new File("src\\File\\" + namaFile);
        File temp = new File("src\\File\\temp.txt");

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
    }

    public static void main(String[] args) {
        try {
            muat("Menu.txt");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}