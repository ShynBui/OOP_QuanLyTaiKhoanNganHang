package QuanLyNganHang;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class AccountManager {
    private ArrayList<TaiKhoanKhongKyHan> quanLyTK;

    public AccountManager() {
        this.quanLyTK = new ArrayList<TaiKhoanKhongKyHan>();
    }

    public ArrayList<TaiKhoanKhongKyHan> getQuanLyTK() {
        return this.quanLyTK;
    }

    public void setQuanLyTK(ArrayList<TaiKhoanKhongKyHan> quanLyTK) {
        this.quanLyTK = quanLyTK;
    }

    // public LoginAccount taoTaiKhoan() {
    //     TaiKhoanKhongKyHan tk = new TaiKhoanKhongKyHan();
    //     quanLyTK.add(tk);
    //     return tk.getLoginAccount();
    // }

    public double tinhTienLai(String sTK) {
        TaiKhoanKhongKyHan tK = timKhachHangTheoSTK(sTK);

        double lai = tK.getSoDu() * tK.getLaiSuat();

        ArrayList <TaiKhoanCoKyHan> taiKhoanCKH = tK.getLoginAccount().getQuanLyTKCTH();
        for (TaiKhoanCoKyHan i : taiKhoanCKH) {
            lai += i.getSoDu() * i.getSoNgayGui() / 365 * i.getLaiSuat();
        }

        return lai;

    }

    public TaiKhoanKhongKyHan timKhachHangTheoSTK(String sTK) {
        for(TaiKhoanKhongKyHan i : quanLyTK) {
            if (i.getMaKhachHang().equals(sTK))
                return i;
        }

        return null;
    }

    public ArrayList<TaiKhoanKhongKyHan> traCuuKhachHang(String hoTen) {
        ArrayList<TaiKhoanKhongKyHan> tKT = new ArrayList<TaiKhoanKhongKyHan>();
        for (TaiKhoanKhongKyHan i : quanLyTK) {
            if (i.getHoTen().equals(hoTen))
                tKT.add(i);
        }

        return tKT;
    }

    public ArrayList<TaiKhoan> traCuuDanhSachTK(String maSo) {
        ArrayList<TaiKhoan> taiKhoan = new ArrayList<TaiKhoan>();
        TaiKhoanKhongKyHan tkkkh = timKhachHangTheoSTK(maSo);
        taiKhoan.add(tkkkh);

        for (TaiKhoanCoKyHan i : tkkkh.getLoginAccount().getQuanLyTKCTH()) {
            taiKhoan.add(i);
        }
        return taiKhoan;
    }

    public void sapXep() {

        Collections.sort(quanLyTK, new Comparator<TaiKhoanKhongKyHan>() {
            public int compare(TaiKhoanKhongKyHan t1, TaiKhoanKhongKyHan t2) {
                return t1.tongSoTienGui() > t2.tongSoTienGui()?1:0;
            }
        });

        quanLyTK.forEach(e -> {System.out.println(e.getSoDu());});
    }
}