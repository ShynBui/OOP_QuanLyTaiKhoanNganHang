package QuanLyNganHang;

import java.util.ArrayList;

public class LoginAccount {
    private TaiKhoanKhongKyHan taiKhoanKKH;
    private String userName;
    private String password;
    private ArrayList<TaiKhoanCoKyHan> quanLyTKCTH = new ArrayList<>();

    public LoginAccount(String userName, String password, TaiKhoanKhongKyHan tkkkh) {
        this.userName = userName;
        this.password = password;
        this.taiKhoanKKH = tkkkh;
    }

    public LoginAccount() {

    }

    public TaiKhoanKhongKyHan getTaiKhoanKKH() {
        return this.taiKhoanKKH;
    }

    public void setTaiKhoanKKH(TaiKhoanKhongKyHan taiKhoanKKH) {
        this.taiKhoanKKH = taiKhoanKKH;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<TaiKhoanCoKyHan> getQuanLyTKCTH() {
        return this.quanLyTKCTH;
    }

    public void setQuanLyTKCTH(ArrayList<TaiKhoanCoKyHan> quanLyTKCTH) {
        this.quanLyTKCTH = quanLyTKCTH;
    }

    public void moTaiKhoan(double soTienGui, KyHan kyHan) {
        TaiKhoanCoKyHan tk = new TaiKhoanCoKyHan(soTienGui, kyHan);
        quanLyTKCTH.add(tk);
    }

    public void doiMatKhau(String mk) {
        setPassword(mk);
    }

    public void rutTienCoKyHan(TaiKhoanCoKyHan tk, double soTienRut) {
        double tien = tk.rutTien(soTienRut);

        quanLyTKCTH.remove(tk);

        taiKhoanKKH.guiTien(tien);
    }

    public boolean guiTienVaoTaiKhoanCoKyHan(TaiKhoanCoKyHan taiKhoan, double soTien) {
        if (taiKhoan.kiemTraToiHan()) {
            double money = taiKhoanKKH.rutTien(soTien);
            if (money > 0) {
                if (taiKhoan.guiTien(soTien)) {
                    // Gui tien dung vao ngay dao han
                    return true;
                } else {
                    // Neu khong phai ngay dao han thi tra lai cho tai khoan chinh
                    taiKhoanKKH.guiTien(money);
                }
            }
        }
        return false;
    }
}
