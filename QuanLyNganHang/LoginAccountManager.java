package QuanLyNganHang;
import java.util.ArrayList;

public class LoginAccountManager {
    private ArrayList<LoginAccount> quanLyTk;

    public LoginAccountManager() {
        quanLyTk = new ArrayList<LoginAccount>();
    }

    public ArrayList<LoginAccount> getQuanLyTk() {
        return this.quanLyTk;
    }

    public void setQuanLyTk(ArrayList<LoginAccount> quanLyTk) {
        this.quanLyTk = quanLyTk;
    }

    public void themTK(LoginAccount lK) {
        quanLyTk.add(lK);
    }

    public void xoaTK(LoginAccount lK) {
        quanLyTk.remove(lK);
    }

    public LoginAccount dangNhap(String username, String password) {
        for (LoginAccount i : quanLyTk) {
            if (i.getUserName().equals(username)) {
                if (i.getPassword().equals(password)) {
                    return i;
                }
                else {
                    System.out.println("Sai mat khau!");
                    return null;
                }
            }
        }
        System.out.println("Ten dang nhap khong hop le!");
        return null;
    }
}