/*
 * 225150200111038 Jaya Winata
 * 225150201111031 Tiara Calista Kusumawardani A.
 * 225150201111032 Najwa Muthia Aziz
 * 225150200111045 Sonia Anindhiya Putri Kurniawan
 * 225150207111073 Dayang Alyssa Raisaputri
 */

import java.util.HashMap;
import java.util.Scanner;

public class CetakFilkom {
    private static Scanner in = new Scanner(System.in);
    private static HashMap<String, Pelanggan> mapPelanggan = new HashMap<>();

    public static void main(String[] args) {
        while (in.hasNextLine()) {
            try {
                String input = in.nextLine();
                if (input.contains("CREATE MEMBER")) {
                    buatMember(input);
                } else if (input.contains("CREATE GUEST")) {
                    buatGuest(input);
                } else if () {
                    // No.2
                } else if () {
                    // No.3 
                } else if () {
                    // No.4
                } else if () {
                    // No.5
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

    private static void buatMember(String input) throws DateOutOfBoundsException {
        input = input.substring(13);
        String[] data = input.split("\\|");
        String[] tanggalMember = data[2].split("/");
        int tanggal = Integer.parseInt(tanggalMember[2]);
        int bulan = Integer.parseInt(tanggalMember[1]);
        int tahun = Integer.parseInt(tanggalMember[0]);
        if (!mapPelanggan.containsKey(data[0])) {
            try {
                Pelanggan p = new Member(data[1], tanggal, bulan, tahun);
                mapPelanggan.put(data[0], p);
            } catch (DateOutOfBoundsException e) {
                throw new DateOutOfBoundsException("Date out of bounds.");
            }
            System.out.println("CREATE MEMBER SUCCES: " + data[0] + " " + data[1]);
        } else {
            System.out.println("CREATE MEMBER FAILED: " + data[0] + " IS EXISTS");
        }
    }

    private static void buatGuest(String input) {
        input = input.substring(12);
        String[] data = input.split("\\|");
        if (!mapPelanggan.containsKey(data[0])) {
            Pelanggan p = new Guest(data[1]);
            mapPelanggan.put(data[0], p);
            System.out.println("CREATE GUEST SUCCES: " + data[0]);
        } else {
            System.out.println("CREATE GUEST FAILED: " + data[0] + " IS EXISTS");
        }
    }
}
