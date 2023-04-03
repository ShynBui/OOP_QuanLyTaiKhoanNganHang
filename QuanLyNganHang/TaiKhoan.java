package QuanLyNganHang;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public abstract class TaiKhoan {
    private Date ngayTao;
    private double soDu;
    private double laiSuat;
    private KyHan kyHan;
    private static double soTienRutToiThieu = 50000;

    public Date getNgayTao() {
        return this.ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

    public double getSoDu() {
        return this.soDu;
    }

    public void setSoDu(double soDu) {
        this.soDu = soDu;
    }

    public double getLaiSuat() {
        return this.laiSuat;
    }

    public void setLaiSuat(double laiSuat) {
        this.laiSuat = laiSuat;
    }

    public KyHan getKyHan() {
        return this.kyHan;
    }

    public void setKyHan(KyHan kyHan) {
        this.kyHan = kyHan;
    }

    public TaiKhoan(){
        
    }

    public double rutTien(double soTienRut){
        if(getSoDu() - soTienRut >= soTienRutToiThieu){
            setSoDu(getSoDu() - soTienRut);
            return soTienRut;
        }
        return 0;
    }

    public boolean guiTien(double soTienGui){
        //Kiem tra login user truoc khi gui tien
        setSoDu(getSoDu() + soTienGui);
        return true;
    }

    //Tinh theo cong thuc khong ky han
    public double tinhTienLai(KyHan kyHan){
        //Tinh toan lay so ngay da gui
        return getSoDu() * kyHan.getLai() * getSoNgayGui() / 365;
    }

    public int getSoNgayGui(){
        Calendar today = Calendar.getInstance();
        Calendar ngayTao = Calendar.getInstance();
        ngayTao.setTime(getNgayTao());
        return today.get(Calendar.DAY_OF_YEAR) - ngayTao.get(Calendar.DAY_OF_YEAR);
    }

    public TaiKhoan(double soDu, KyHan kyHan){
        setKyHan(kyHan);
        setNgayTao(new Date());
        setSoDu(soDu);
        setLaiSuat(kyHan.getLai());
    }

    
    @Override
    public String toString(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String format = String.format("Ngay tao, %s\n"+
                                    "So du: %.2f\n" +
                                    "Lai suat: %.1f\n" + 
                                    "Ky han: %s\n",
                                    sdf.format(ngayTao), soDu, laiSuat, getKyHan().toString());
        return format;
    }
}