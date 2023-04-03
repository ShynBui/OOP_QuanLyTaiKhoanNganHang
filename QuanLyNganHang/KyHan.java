package QuanLyNganHang;


public enum KyHan {
    MOTTUAN(2, 7),
    MOTTHANG(5.5, 30),
    SAUTHANG(7.5, 180),
    MUOIHAITHANG(7.9, 360),
    DEFAULT(0.2, 360);

    private double lai;
    private int ngay;

    public double getLai() {
        return this.lai;
    }

    public void setLai(double lai) {
        this.lai = lai;
    }

    public int getNgay() {
        return this.ngay;
    }

    public void setNgay(int ngay) {
        this.ngay = ngay;
    }

    KyHan(double lai, int ngay) {
        this.lai = lai;
        this.ngay = ngay;
    }
}
