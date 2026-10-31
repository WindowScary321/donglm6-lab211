/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.util.Locale;
import java.util.Random;
import java.util.ResourceBundle;

/**
 *
 * @author pc
 */
public class Ebank {
    private ResourceBundle bundle;

    /* Ham setLocale
     * Muc dich: Cai dat ngon ngu hien thi cho chuong trinh
     * Cach thuc: Tai ResourceBundle resources.Language tuong ung voi Locale duoc truyen vao
     * Dau vao: locale doi tuong Locale dai dien cho ngon ngu can su dung
     * Dau ra: ko co
     */
    public void setLocale(Locale locale) {
        this.bundle = ResourceBundle.getBundle("resources.Language", locale);
    }

    /* Ham getMessage
     * Muc dich: Lay thong bao theo ngon ngu da chon
     * Cach thuc: Su dung ResourceBundle da thiet lap de lay chuoi tuong ung voi key
     * Dau vao: key khoa cua thong bao can lay
     * Dau ra: chuoi thong bao tuong ung voi key
     */
    public String getMessage(String key) {
        return bundle.getString(key);
    }

    /* Ham checkAccountNumber
     * Muc dich: Kiem tra so tai khoan co dung dinh dang hay khong
     * Cach thuc: Dung regex kiem tra chuoi co dung 10 chu so,
     * tra ve ket qua kiem tra de ham goi xu ly thong bao loi
     * Dau vao: accountNumber so tai khoan can kiem tra, luu dang String de giu chu so 0 o dau
     * Dau ra: true neu hop le, nguoc lai la false
     */
    public boolean checkAccountNumber(String accountNumber) {
        String regex = "^[0-9]{10}$";
        return accountNumber.matches(regex);
    }

    /* Ham checkPassword
     * Muc dich: Kiem tra dinh dang mat khau dang nhap
     * Cach thuc: Dung regex kiem tra do dai tu 8 den 31 ky tu, co it nhat mot chu cai va mot chu so,
     * regex hien tai van cho phep ky tu dac biet va khoang trang trong mat khau
     * Tra ve ket qua kiem tra de ham goi xu ly thong bao loi
     * Dau vao: password chuoi mat khau can kiem tra
     * Dau ra: true neu hop le, nguoc lai la false
     */
    public boolean checkPassword(String password) {
        String regex = "^(?=.*[A-Za-z])(?=.*[0-9]).{8,31}$";
        return password.matches(regex);
    }

    /* Ham generateCaptcha
     * Muc dich: Sinh ngau nhien ma captcha de nguoi dung nhap
     * Cach thuc: Dung Random chon 5 ky tu tu chuoi chars co san gom chu so, chu cai hoa va thuong,
     * ghep cac ky tu da chon bang StringBuilder thanh mot chuoi captcha
     * Dau vao: ko co
     * Dau ra: chuoi captcha gom 5 ky tu ngau nhien
     */
    public String generateCaptcha() {
        String chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        StringBuilder reqString = new StringBuilder();
        Random random = new Random();
        // Dung vong for de tao 5 ky tu captcha bang StringBuilder
        for (int i = 0; i < 5; i++) {
            // Chon 1 trong nhung ky tu o bien chars de dua vao ma catpcha
            int index = random.nextInt(chars.length());
            reqString.append(chars.charAt(index));
        }
        return reqString.toString(); // Tra ve ma captcha

    }

    /* Ham checkCaptcha
     * Muc dich: Kiem tra captcha do nguoi dung nhap
     * Cach thuc: Dung vong for duyet qua tung ky tu cua captchaInput
     * Su dung contains de kiem tra ky tu do co giong voi ky tu tai vi tri tuong ung trong captchaGenerate khong
     * Dam bao nguoi dung nhap dung ky tu, dung thu tu, ghep lien tiep (co the khong can nhap het)
     * Dau vao: captchaInput chuoi nguoi dung nhap, captchaGenerate ma captcha duoc sinh ra
     * Dau ra: true neu hop le, nguoc lai la false
     */
    public boolean checkCaptcha(String captchaInput, String captchaGenerate) {
        if (captchaInput == null || captchaInput.isEmpty()) {
            return false;
        }
        // Neu do dai chuoi dau vao dai hon ma captcha => sai ma captcha
        if (captchaInput.length() > captchaGenerate.length()) {
            return false;
        }
        // Tim vi tri bat dau trung khop cua chuoi input trong chuoi goc
        int startIndex = captchaGenerate.indexOf(Character.toString(captchaInput.charAt(0)));
        if (startIndex == -1) {
            return false;
        }

        // Duyet qua tung ky tu cua chuoi nhap vao
        for (int i = 0; i < captchaInput.length(); i++) {
            // Check vi tri tuong ung can kiem tra trong chuoi captcha goc
            int targetIndex = startIndex + i;
            // Neu vuot qua do dai cua captcha goc thi dau vao sai
            if (targetIndex >= captchaGenerate.length()) {
                return false;
            }
            String inputChar = Character.toString(captchaInput.charAt(i));
            // Ep ham contains kiem tra tai dung vi tri hien tai bang cach lay duy nhat 1 ky tu
            String expectedChar = Character.toString(captchaGenerate.charAt(targetIndex));
            // Dung contains de kiem tra
            if (!expectedChar.contains(inputChar)) {
                return false;
            }
        }
        return true; 
    }

    
}
