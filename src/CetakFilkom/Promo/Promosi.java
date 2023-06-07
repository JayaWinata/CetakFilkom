package CetakFilkom.Promo;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import CetakFilkom.Pelanggan.*;
import CetakFilkom.Error.*;

interface Applicable {
    public boolean isEligible(Pelanggan pelanggan);

    public boolean isPriceEligible(double harga);

    public boolean isOngkirEligible(double ongkir);

    public double hitungDiskon(double biaya);

    public double hitungCashBack(double biaya);

    public double hitungDiskonOngkir(double biaya);
}

public abstract class Promosi implements Applicable, Comparable<Promosi> {
    protected double diskon;
    protected double hargaMinimum;
    protected double ongkirMinimum;
    protected LocalDate tanggalAwal;
    protected LocalDate tanggalAkhir;
    protected double maksPotongan;

    public LocalDate getTanggalAwal() {
        return tanggalAwal;
    }

    public void setTanggalAwal(int dd, int MM, int YYYY) throws DateOutOfBoundsException {
        this.tanggalAwal = LocalDate.of(YYYY, MM, dd);
        if ((dd <= 0 || dd > tanggalAwal.getDayOfMonth()) || (MM <= 0 || MM > tanggalAwal.getMonthValue())) {
            throw new DateOutOfBoundsException("Tanggal tidak sesuai!");
        }
    }

    public LocalDate getTanggalAkhir() {
        return tanggalAkhir;
    }

    public void setTanggalAkhir(int dd, int MM, int YYYY) throws DateOutOfBoundsException {
        this.tanggalAkhir = LocalDate.of(YYYY, MM, dd);
        if ((dd <= 0 || dd > tanggalAkhir.getDayOfMonth()) || (MM <= 0 || MM > tanggalAkhir.getMonthValue())) {
            throw new DateOutOfBoundsException("Tanggal tidak sesuai!");
        }
    }

    public double getMaksPotongan() {
        return maksPotongan;
    }

    public void setMaksPotongan(double maksPotongan) {
        this.maksPotongan = maksPotongan;
    }

    public int compareTo(Promosi o) {
        if (this.diskon > o.diskon)
            return 1;
        else if (this.diskon == o.diskon)
            return 0;
        else
            return -1;
    }

    public boolean isExpired() {
        LocalDate current = LocalDate.now();
        return ChronoUnit.DAYS.between(current, tanggalAkhir) < 0;
    }

    public boolean isEligible(Pelanggan pelanggan) {
        if (pelanggan instanceof Member) {
            if (((Member) pelanggan).getMemberTime() > 30) {
                return true;
            }
        }
        return false;
    }
}
