package CetakFilkom.Pelanggan;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import CetakFilkom.Error.DateOutOfBoundsException;

public class Member extends Pelanggan {

    private int tanggalMember;
    private int bulanMember;
    private int tahunMember;
    private long hariMember;

    public Member(String nama, int dd, int MM, int YYYY) throws DateOutOfBoundsException {
        LocalDate date = LocalDate.of(YYYY, MM, dd);
        if ((dd <= 0 || dd > date.getDayOfMonth()) || (MM <= 0 || MM > date.getMonthValue())) {
            throw new DateOutOfBoundsException("Tanggal tidak sesuai!");
        }
        this.nama = nama;
        this.tanggalMember = dd;
        this.bulanMember = MM;
        this.tahunMember = YYYY;
    }

    public long hitungMemberTime() {
        LocalDate date = LocalDate.now();
        LocalDate memberDate = LocalDate.of(tahunMember, bulanMember, tanggalMember);
        hariMember = ChronoUnit.DAYS.between(memberDate, date);
        return hariMember;
    }

    public long getMemberTime() {
        return hitungMemberTime();
    }
}
