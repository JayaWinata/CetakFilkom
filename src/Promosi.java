interface Applicable {
    public boolean isEligible(Pelanggan pelanggan);

    public boolean isPriceEligible(double harga);

    public boolean isOngkirEligible(double ongkir);

    public double hitungDiskon(double biaya);

    public double hitungCashBack(double biaya);

    public double hitungDiskonOngkir(double biaya);
}

public abstract class Promosi implements Applicable {
    protected String promoCode;
    double diskon;
}

class PercentOffPromo extends Promosi {

    PercentOffPromo(int promoPersen) {
        this.diskon = diskon / 100.0;
    }

    public String getDiskonToString() {
        return String.format("Potongan %.0f%%", (diskon * 100));
    }

    @Override
    public boolean isEligible(Pelanggan pelanggan) {
        if (pelanggan instanceof Member) {
            if (((Member) pelanggan).getMemberTime() > 30) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isPriceEligible(double harga) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isPriceEligible'");
    }

    @Override
    public boolean isOngkirEligible(double ongkir) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isOngkirEligible'");
    }

    @Override
    public double hitungDiskon(double biaya) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'hitungDiskon'");
    }

    @Override
    public double hitungCashBack(double biaya) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'hitungCashBack'");
    }

    @Override
    public double hitungDiskonOngkir(double biaya) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'hitungDiskonOngkir'");
    }
}

class CashbackPromo extends Promosi {
    double cashback;

    CashbackPromo(int promoPersen, double biaya) {
        this.diskon = diskon / 100.0;
        this.cashback = biaya * (1 - diskon);
    }

    public double getCashBack() {
        return this.cashback;
    }

    public String getCashBackToString() {
        return String.format("Cashback %.0f%% (%.0f)", this.diskon, this.cashback);
    }

    @Override
    public boolean isEligible(Pelanggan pelanggan) {
        if (pelanggan instanceof Member) {
            if (((Member) pelanggan).getMemberTime() > 30) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isPriceEligible(double harga) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isPriceEligible'");
    }

    @Override
    public boolean isOngkirEligible(double ongkir) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isOngkirEligible'");
    }

    @Override
    public double hitungDiskon(double biaya) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'hitungDiskon'");
    }

    @Override
    public double hitungCashBack(double biaya) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'hitungCashBack'");
    }

    @Override
    public double hitungDiskonOngkir(double biaya) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'hitungDiskonOngkir'");
    }
}

class OngkirPromo extends Promosi {
    OngkirPromo(int promoPersen) {
        this.diskon = diskon / 100.0;
    }

    @Override
    public boolean isEligible(Pelanggan pelanggan) {
        if (pelanggan instanceof Member) {
            if (((Member) pelanggan).getMemberTime() > 30) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isPriceEligible(double harga) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isPriceEligible'");
    }

    @Override
    public boolean isOngkirEligible(double ongkir) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isOngkirEligible'");
    }

    @Override
    public double hitungDiskon(double biaya) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'hitungDiskon'");
    }

    @Override
    public double hitungCashBack(double biaya) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'hitungCashBack'");
    }

    @Override
    public double hitungDiskonOngkir(double biaya) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'hitungDiskonOngkir'");
    }

}
