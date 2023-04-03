package QuanLyNganHang;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TaiKhoanCoKyHan extends TaiKhoan {
    private Date ngayDaoHan;

    public Date getNgayDaoHan() {
        return this.ngayDaoHan;
    }

    public void setNgayDaoHan(Date ngayDaoHan) {
        this.ngayDaoHan = ngayDaoHan;
    }

    public TaiKhoanCoKyHan(){

    }

    //Sua trong so do Class diagram
    //Constructor co them 2 tham so 
    public TaiKhoanCoKyHan(double soTienGui, KyHan kyHan){
        super(soTienGui, kyHan);
        giaHan(kyHan);
    }

    //Gia han tai khoan co ky han
    public void giaHan(KyHan kyHan){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, kyHan.getNgay());
        ngayDaoHan = calendar.getTime();
    }

    @Override
    public double rutTien(double soTienRut){
        double money;
        if(kiemTraToiHan()){
            money = getSoDu() + tinhTienLai(getKyHan());
        }
        else{
            money = getSoDu() + tinhTienLai(KyHan.DEFAULT);
        }
        return money;
    }

    @Override
    public boolean guiTien(double soTienGui){
        //Kiem tra login user truoc khi gui tien
        if(kiemTraToiHan()){
            return super.guiTien(soTienGui);
        }
        else{
            return false;
        }
    }

    public boolean kiemTraToiHan(){
        Date today = new Date();
        return today.compareTo(getNgayDaoHan()) == 0? true : false;
    }

    @Override
    public String toString(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String format = String.format("%s" + 
                                    "Ngay dao han: %s\n",
                                    super.toString(), sdf.format(ngayDaoHan), getNgayTao());
        return format;
    }
}
