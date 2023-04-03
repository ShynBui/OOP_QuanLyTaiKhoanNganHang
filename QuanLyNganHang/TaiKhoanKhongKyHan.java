package QuanLyNganHang;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class TaiKhoanKhongKyHan extends TaiKhoan {
    private static int dem = 0;
    private String maKhachHang;
    private String hoTen;
    private String gioiTinh;
    private Date ngaySinh;
    private String queQuan;
    private String cccd;
    private LoginAccount LoginAccount;

    public LoginAccount getLoginAccount() {
        return this.LoginAccount;
    }

    public void setLoginAccount(LoginAccount LoginAccount) {
        this.LoginAccount = LoginAccount;
    }

    public String getHoTen() {
        return this.hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getGioiTinh() {
        return this.gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public Date getNgaySinh() {
        return this.ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getQueQuan() {
        return this.queQuan;
    }

    public void setQueQuan(String queQuan) {
        this.queQuan = queQuan;
    }

    public String getCccd() {
        return this.cccd;
    }

    public void setCccd(String cccd) {
        this.cccd = cccd;
    }

    public String getMaKhachHang() {
        return this.maKhachHang;
    }

    public void setMaKhachHang(String maKhachHang) {
        this.maKhachHang = maKhachHang;
    }

    public TaiKhoanKhongKyHan(String hoTen, String gioiTinh, Date ngaySinh, 
        String queQuan, String cccd, double soTienGui, KyHan kyHan){
        super(soTienGui, kyHan);
        Calendar calendar = Calendar.getInstance();
        ++dem;
        setMaKhachHang(String.format("%2s%2s%.2s%04d", calendar.get(Calendar.DAY_OF_MONTH), 
            calendar.get(Calendar.MONTH) + 1, Integer.toString(calendar.get(Calendar.YEAR)).substring(2), dem));
        setHoTen(hoTen);
        setGioiTinh(gioiTinh);
        setCccd(cccd);
        setNgaySinh(ngaySinh);
        setQueQuan(queQuan);
        setLoginAccount(new LoginAccount(getMaKhachHang(), createPassword(), this));
    }

    public TaiKhoanKhongKyHan(){

    }

    public String createPassword(){
        int numbersOfDigits = 6;
        //This method create a password with random 6 numbers
        Random r = new Random();
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < numbersOfDigits; i++)
        {
            sb.append(r.nextInt(0, 10));
        }
        return sb.toString();
    }

    public double tongSoTienGui(){
        double tongTien = 0;
        ArrayList<TaiKhoanCoKyHan> list = LoginAccount.getQuanLyTKCTH();
        for(TaiKhoanCoKyHan tkckh : list){
            tongTien += tkckh.getSoDu(); 
        } 
        return tongTien + getSoDu();
    }

    @Override
    public String toString(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String format = String.format("Ma khach hang: %s\n" +
                                    "Ho ten: %s\n"+
                                    "Ngay sinh: %s\n" +
                                    "Que quan: %s\n"+
                                    "CCCD: %s\n" +
                                    "Gioi tinh: %s\n" +
                                    "%s\n", maKhachHang, hoTen, sdf.format(ngaySinh),
                                    queQuan, cccd, gioiTinh, super.toString());
        return format;
    }
}