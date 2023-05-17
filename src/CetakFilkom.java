/*
 * 225150200111038 Jaya Winata
 * 225150201111031 Tiara Calista Kusumawardani A.
 * 225150201111032 Najwa Muthia Aziz
 * 225150200111045 Sonia Anindhiya Putri Kurniawan
 * 225150207111073 Dayang Alyssa Raisaputri
 */

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

public class CetakFilkom {
    static Scanner in = new Scanner(System.in);
    static HashMap<String, Pelanggan> mapPelanggan = new HashMap<>();

    public static void main(String[] args) {
        while (in.hasNextLine()) {
            try {
                String input = in.nextLine();
                if (input.contains("CREATE MEMBER")) {
                    buatMember(input);
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
}
