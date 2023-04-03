import QuanLyNganHang.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Main {
    // file path bỏ vào đây
    public static String filePath = "resources/Data.txt";
    public static final Scanner inputSc = new Scanner(System.in);
    public static AccountManager am = new AccountManager();
    public static LoginAccountManager lgm = new LoginAccountManager();

    public static void main(String[] args) throws FileNotFoundException, ParseException {
        FileInputStream fileInputStream = new FileInputStream(filePath);
        Scanner sc = new Scanner(fileInputStream);
        while(sc.hasNextLine()){
            String temp = sc.nextLine();
            if(temp.equals("-")){
                TaiKhoanKhongKyHan tk = docKhachHang(sc.nextLine(), sc.nextLine(), sc.nextLine(),
                    sc.nextLine(), sc.nextLine(), sc.nextLine(), sc.nextLine(), sc.nextLine(), sc.nextLine());
                
                am.getQuanLyTK().add(tk);
                lgm.getQuanLyTk().add(tk.getLoginAccount());
            }
            else if (temp.equals("--")){
                TaiKhoanCoKyHan tkckh = docCoKyHan(sc.nextLine(), sc.nextLine(), sc.nextLine());
                lgm.getQuanLyTk().get(lgm.getQuanLyTk().size() - 1).getQuanLyTKCTH().add(tkckh);
            }
        }
        sc.close();
        mainMenu();
    }

    public static TaiKhoanKhongKyHan docKhachHang(String maKH, String hoTen, String gioiTinh, String ngaySinh, String queQuan, String cccd,
        String ngayMoThe, String soDu, String matKhau) throws ParseException{
            TaiKhoanKhongKyHan tk = new TaiKhoanKhongKyHan();
            tk.setMaKhachHang(maKH);
            tk.setHoTen(hoTen);
            tk.setGioiTinh(gioiTinh);
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            tk.setNgaySinh(sdf.parse(ngaySinh));
            tk.setQueQuan(queQuan);
            tk.setCccd(cccd);
            tk.setSoDu(Double.parseDouble(soDu));
            tk.setNgayTao(sdf.parse(ngayMoThe));
            tk.setKyHan(KyHan.DEFAULT);
            tk.setLaiSuat(tk.getKyHan().getLai());
            LoginAccount lg = new LoginAccount();
            lg.setUserName(maKH);
            lg.setPassword(matKhau);
            lg.setTaiKhoanKKH(tk);
            tk.setLoginAccount(lg);
            return tk;
    }

    public static TaiKhoanCoKyHan docCoKyHan(String ngayMo, String soDu, String kyHan) throws ParseException{
        Map<String, KyHan> data = new TreeMap<String, KyHan>();
        data.put("MOTTUAN", KyHan.MOTTUAN);
        data.put("MOTTHANG", KyHan.MOTTHANG);
        data.put("SAUTHANG", KyHan.SAUTHANG);
        data.put("MUOIHAITHANG", KyHan.MUOIHAITHANG);
        data.put("DEFAULT", KyHan.DEFAULT);
        TaiKhoanCoKyHan tkckh = new TaiKhoanCoKyHan();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        tkckh.setNgayTao(sdf.parse(ngayMo));
        tkckh.setSoDu(Double.parseDouble(soDu));
        tkckh.setKyHan(data.get(kyHan));
        tkckh.setLaiSuat(tkckh.getKyHan().getLai());
        tkckh.giaHan(tkckh.getKyHan());
        return tkckh;
    }

    public static void mainMenu() {
        int userInput = 0; // Mac dinh la 0
        boolean isUsing = true;
        while (isUsing) {
            System.out.print("1. Menu nhan vien\n" +
                    "2. Menu khach hang\n" +
                    "3. Exit\n" +
                    "Moi nhap: ");
            try {
                userInput = Integer.parseInt(inputSc.nextLine());
            } catch (Exception ex) {

            }
            switch (userInput) {
                case 1: {
                    menuNhanVien(am, lgm);
                    break;
                }
                case 2: {
                    menuKhachHang(am, lgm);
                    break;
                }
                case 3: {
                    System.out.println("Exiting!");
                    isUsing = false;
                    break;
                }
                default: {
                    System.out.println("Nhap khong dung");
                }
            }
        }
    }

    public static void menuNhanVien(AccountManager am, LoginAccountManager lgm) {
        int employeeInput = 0;
        boolean isUsing = true;
        while (isUsing) {
            System.out.print("1. Mo tai khoan khach hang\n" +
                    "2. Tra cuu khach hang theo ho ten\n" +
                    "3. Tra cuu khach hang theo ma khach hang\n" +
                    "4. Tra cuu danh sach tai khoan co ky han theo ma khach hang\n" +
                    "5. Sap xep danh sach khach hang\n" +
                    "6. Exit\n" +
                    "Moi nhap: ");
            try {
                employeeInput = Integer.parseInt(inputSc.nextLine());
            } catch (NumberFormatException ex) {

            }
            switch (employeeInput) {
                case 1: {
                    TaiKhoanKhongKyHan tkkkh = taoTaiKhoanKhongKyHan();
                    if (tkkkh != null) {
                        System.out.println("Tao tai khoan thanh cong!");
                        am.getQuanLyTK().add(tkkkh);
                        lgm.themTK(tkkkh.getLoginAccount());
                    } else {
                        System.out.println("Tao tai khoan that bai");
                    }
                    break;
                }
                case 2: {
                    System.out.print("Moi nhap ho ten de tra cuu: ");
                    String hoTen = inputSc.nextLine();
                    ArrayList<TaiKhoanKhongKyHan> list = am.traCuuKhachHang(hoTen);
                    if (!list.isEmpty()) {
                        for (TaiKhoanKhongKyHan tkkkh : list) {
                            System.out.print(tkkkh.toString());
                        }
                    } else {
                        System.out.println("Khong tim thay khach hang nao");
                    }
                    break;
                }
                case 3: {
                    System.out.print("Moi nhap ma tai khoan: ");
                    String maTK = inputSc.nextLine();
                    TaiKhoanKhongKyHan tkkkh = am.timKhachHangTheoSTK(maTK);
                    if (tkkkh != null)
                        System.out.print(tkkkh.toString());
                    else
                        System.out.println("khong tim thay khach hang nao");
                    break;
                }
                case 4: {
                    System.out.print("Moi nhap ma khach hang: ");
                    String maKH = inputSc.nextLine();
                    ArrayList<TaiKhoan> dsTaiKhoan = am.traCuuDanhSachTK(maKH);
                    if (!dsTaiKhoan.isEmpty()) {
                        for (TaiKhoan taiKhoan : dsTaiKhoan) {
                            if (taiKhoan instanceof TaiKhoanCoKyHan) {
                                System.out.print(((TaiKhoanCoKyHan) taiKhoan).toString());
                            } else {
                                System.out.print(((TaiKhoanKhongKyHan) taiKhoan).toString());
                            }
                        }
                    } else {
                        System.out.println("Khong tim thay tai khoan nao");
                    }
                    break;
                }
                case 5: {
                    am.sapXep();
                    for (TaiKhoanKhongKyHan tkkkh : am.getQuanLyTK()) {
                        System.out.print(tkkkh.toString());
                    }
                    break;
                }
                case 6: {
                    System.out.println("Exiting!");
                    isUsing = false;
                    break;
                }
                default: {
                    System.out.println("Nhap khong dung");
                }
            }
        }
    }

    public static void menuKhachHang(AccountManager am, LoginAccountManager lgm) {
        String username;
        String password;
        System.out.print("Ten dang nhap: ");
        username = inputSc.nextLine();
        System.out.print("Mat khau: ");
        password = inputSc.nextLine();
        LoginAccount lg = lgm.dangNhap(username, password);
        if (lg == null) {
            // Dang nhap that bai
            return;
        }

        int customerInput = 0;
        boolean isUsing = true;
        while (isUsing) {
            System.out.print("1. Gui tien\n" +
                    "2. Rut tien\n" +
                    "3. Xem danh sach tai khoan\n" +
                    "4. Rut tien tai khoan co ky han\n" +
                    "5. Nap tien tai khoan co ky han\n" +
                    "6. Tinh tien lai\n" +
                    "7. Mo tai khoan co ky han\n" +
                    "8. Doi mat khau\n" +
                    "9. Xem so du\n" +
                    "10. Exit\n" +
                    "Moi nhap: ");
            try {
                customerInput = Integer.parseInt(inputSc.nextLine());
            } catch (NumberFormatException ex) {

            }
            switch (customerInput) {
                case 1: {
                    double soTienGui = 0;
                    System.out.print("Moi nhap vao so tien gui: ");
                    try {
                        soTienGui = Double.parseDouble(inputSc.nextLine());
                    } catch (NumberFormatException ex) {
                        System.out.println("Gui tien that bai");
                        break;
                    }
                    if (lg.getTaiKhoanKKH().guiTien(soTienGui)) {
                        System.out.println(String.format("Gui tien thanh cong, so du hien tai %.1f",
                                lg.getTaiKhoanKKH().getSoDu()));
                    } else {
                        System.out.println("Gui tien that bai");
                    }
                    break;
                }
                case 2: {
                    double soTienRut = 0;
                    System.out.print("Moi nhap vao so tien rut: ");
                    try {
                        soTienRut = Double.parseDouble(inputSc.nextLine());
                    } catch (NumberFormatException ex) {
                        System.out.println("Rut tien that bai");
                        break;
                    }
                    if (lg.getTaiKhoanKKH().rutTien(soTienRut) != 0) {
                        System.out.println(String.format("Rut tien thanh cong, so du hien tai %.1f",
                                lg.getTaiKhoanKKH().getSoDu()));
                    } else {
                        System.out.println("Rut tien that bai");
                    }
                    break;
                }
                case 3: {
                    ArrayList<TaiKhoan> dsTaiKhoan = am.traCuuDanhSachTK(lg.getTaiKhoanKKH().getMaKhachHang());
                    if (!dsTaiKhoan.isEmpty()) {
                        for (TaiKhoan taiKhoan : dsTaiKhoan) {
                            if (taiKhoan instanceof TaiKhoanCoKyHan) {
                                System.out.print(((TaiKhoanCoKyHan) taiKhoan).toString());
                            } else {
                                System.out.print(((TaiKhoanKhongKyHan) taiKhoan).toString());
                            }
                        }
                    }
                    break;
                }
                case 4: {
                    double soTienRut = 0;

                    try {
                        // Lay tai khoan tu danh sach
                        TaiKhoanCoKyHan tk = chonTaiKhoan(am,lg);
                        System.out.print("Moi nhap so tien rut: ");
                        soTienRut = Double.parseDouble(inputSc.nextLine());
                        lg.rutTienCoKyHan(tk, soTienRut);
                        // Hien thi so du cho nguoi dung biet
                        System.out.println(String.format("Rut tien thanh cong, so du hien tai %.1f",
                                lg.getTaiKhoanKKH().getSoDu()));
                    } catch (NumberFormatException ex) {
                        System.out.println("Rut tien that bai");
                    }
                    break;
                }
                case 5: {
                    //Coding here
                    double money = 0;
                    System.out.print("Moi nhap vao so tien: ");
                    money = Double.parseDouble(inputSc.nextLine());
                    TaiKhoanCoKyHan tk = chonTaiKhoan(am, lg);
                    if(lg.guiTienVaoTaiKhoanCoKyHan(tk, money)){
                        System.out.print(String.format("Gui tien thanh cong\nSo du tai khoan co ky han: %.1f"+
                                        "Tai khoan chinh %.1f\n", 
                                        tk.getSoDu(), lg.getTaiKhoanKKH().getSoDu()));
                    }
                    else{
                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                        System.out.println(String.format("Gui that bai chua toi ngay dao han %s"
                                        ,sdf.format(tk.getNgayDaoHan())));
                    }
                    break;
                }
                case 6:{
                    System.out.println(String.format("So tien lai la: %.1f", 
                        am.tinhTienLai(lg.getTaiKhoanKKH().getMaKhachHang())));
                    break;
                }
                case 7:{
                    int id = 0;
                    double money = 0;
                    for(KyHan kh : KyHan.values()){
                        System.out.print(String.format("ID: %d" +
                                        "Loai ky han: %s" +
                                        "Lai suat: %.f1",
                                        id, kh.toString(), kh.getLai()));
                    }
                    System.out.print("Moi chon loai ky han: ");
                    int index = Integer.parseInt(inputSc.nextLine());
                    while(money < 100000){
                        System.out.print("Nhap so tien gui lon hon hoac bang 100 Nghin VND: ");
                        money = Double.parseDouble(inputSc.nextLine());
                    }
                    try{
                        lg.moTaiKhoan(money, KyHan.values()[index]);
                        System.out.println("Mo tai khoan thanh cong");
                    }catch(IndexOutOfBoundsException ex){
                        System.out.println("Mo tai khoan that bai");
                    }
                    break;
                }
                case 8:{
                    System.out.print("Nhap vao mat khau moi");
                    String pd = inputSc.nextLine();
                    lg.doiMatKhau(pd);
                    System.out.println("Doi mat khau thanh cong");
                    break;
                }
                case 9:{
                    System.out.print(String.format("So du tai khoan chinh: %.1f", lg.getTaiKhoanKKH().getSoDu()));
                    break;
                }
                case 10:{
                    isUsing = false;
                    System.out.println("Exiting");
                    break;
                }
                default: {
                    System.out.println("Nhap khong dung");
                }
            }
        }
    }

    public static TaiKhoanKhongKyHan taoTaiKhoanKhongKyHan() {
        TaiKhoanKhongKyHan tkkkh;
        String hoTen;
        String gioiTinh;
        Date ngaySinh;
        String queQuan;
        String cccd;
        double soTienGui = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            System.out.print("Moi nhap ho ten: ");
            hoTen = inputSc.nextLine();
            System.out.print("Moi nhap gioi tinh: ");
            gioiTinh = inputSc.nextLine();
            System.out.print("Moi nhap ngay sinh: ");
            ngaySinh = sdf.parse(inputSc.nextLine());
            System.out.print("Moi nhap que quan: ");
            queQuan = inputSc.nextLine();
            System.out.print("Moi nhap cccd: ");
            cccd = inputSc.nextLine();
            while (true) {
                System.out.print("Moi nhap so tien gui toi thieu 100 nghin VND: ");
                soTienGui = Double.parseDouble(inputSc.nextLine());
                if (soTienGui < 100000.0) {
                    System.out.print("Nhap khong dung, tiep tuc? Nhan phim bat ky de tiep tuc, Nhan N de thoat: ");
                    if (inputSc.nextLine().toLowerCase() == "n") {
                        return null;
                    }
                } else {
                    // Nhap toi thieu so tien
                    break;
                }
            }
            tkkkh = new TaiKhoanKhongKyHan(hoTen, gioiTinh, ngaySinh, queQuan, cccd, soTienGui, KyHan.DEFAULT);
        } catch (ParseException ex) {
            return null;
        } catch (NumberFormatException ex) {
            return null;
        }
        return tkkkh;
    }

    public static TaiKhoanCoKyHan chonTaiKhoan(AccountManager am, LoginAccount lg) {
        ArrayList<TaiKhoan> dsTaiKhoan = am.traCuuDanhSachTK(lg.getTaiKhoanKKH().getMaKhachHang());
        TaiKhoanCoKyHan tkckh = null;
        int id = 0;
        for (TaiKhoan taiKhoan : dsTaiKhoan) {
            if (taiKhoan instanceof TaiKhoanCoKyHan) {
                System.out.print(String.format("ID: %d\n%s",
                        id, ((TaiKhoanCoKyHan) taiKhoan).toString()));
            }
            ++id;
        }
        // Nhap id tu danh sach duoc xuat ra man hinh
        try{
            System.out.print("Moi nhap id: ");
            int index = Integer.parseInt(inputSc.nextLine());
            TaiKhoan tk = dsTaiKhoan.get(index);
            if(tk instanceof TaiKhoanCoKyHan){
                tkckh = (TaiKhoanCoKyHan) tk;
            }
        }catch(IndexOutOfBoundsException ex){
            
        }catch(NumberFormatException ex){
            
        }
        return tkckh;
    }
}
