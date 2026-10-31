/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import java.util.Locale;
import java.util.Scanner;

import controller.Ebank;

/**
 *
 * @author pc
 */
public class Main {

    /* Ham main
     * Muc dich: Hien thi menu chon ngon ngu va dieu huong chuong trinh
     * Cach thuc: Lap hien thi menu va doc lua chon tu ban phim,
     * neu chon 1 hoac 2 thi thiet lap ngon ngu tuong ung, goi login va ket thuc chuong trinh,
     * neu chon 3 thi thoat, cac lua chon khac bao loi va hien thi lai menu
     * Dau vao: args cac tham so dong lenh (khong su dung)
     * Dau ra: ko co
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Ebank ebank = new Ebank();

        while (true) {
            System.out.println("-------Login Program-------");
            System.out.println("1. Vietnamese");
            System.out.println("2. English");
            System.out.println("3. Exit");
            System.out.print("Please choose one option: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    ebank.setLocale(new Locale("vi", "VN"));
                    // Thuc hien dang nhap voi tieng viet
                    login(ebank, scanner);
                    return;
                case "2":
                    ebank.setLocale(new Locale("en", "US"));
                    // Thuc hien dang nhap voi tieng anh
                    login(ebank, scanner);
                    return;
                case "3":
                    // Thoat chuong trinh
                    System.out.println("Goodbye.");
                    System.exit(0);
                default:
                    System.out.println("Invalid option. Please enter 1, 2 or 3.");
            }
        }
    }

    /* Ham login
     * Muc dich: Thuc hien cac buoc kiem tra thong tin dang nhap
     * Cach thuc: Lan luot nhap va kiem tra so tai khoan, mat khau va captcha,
     * neu du lieu khong hop le thi in thong bao loi va yeu cau nhap lai buoc do,
     * captcha duoc sinh mot lan va giu nguyen khi nhap lai; khi hop le thi in thong bao thanh cong
     * Dau vao: ebank doi tuong xu ly ngon ngu va kiem tra du lieu, scanner doi tuong doc du lieu
     * Dau ra: ko co
     */
    public static void login(Ebank ebank, Scanner scanner) {

        /* 1. NHAP TAI KHOAN
         * Muc dich: Cho phep nguoi dung nhap stk vao chuong trinh
         * Cach thuc: Su dung scanner de nguoi nhap stk 
         * sau do goi ham ebank.chackAccountNumber() de kiem tra. Neu stk hop le, chuyen sang buoc tiep theo
         * Neu sai thi thong bao loi bang ngon ngu da chon va yeu cau nhap lai
         */
        while (true) {
            System.out.print(ebank.getMessage("enterAccount"));
            String acc = scanner.nextLine();
            
            if (ebank.checkAccountNumber(acc)) {
                break; // dau vao hop le, chuyen sang buoc tiep theo
            } else {
                System.out.println(ebank.getMessage("enterAccountErr"));
            }
        }

        /* 2. NHAP MAT KHAU
         * Muc dich: Cho phep nguoi dung nhap mat khau vao chuong trinh
         * Cach thuc: Su dung scanner de nguoi nhap stk 
         * sau do goi ham ebank.checkPassword() de kiem tra. Neu mat khau hop le, chuyen sang buoc tiep theo
         * Neu sai thi thong bao loi bang ngon ngu da chon va yeu cau nhap lai
         */
        while (true) {
            System.out.print(ebank.getMessage("enterPassword"));
            String pass = scanner.nextLine();

            if (ebank.checkPassword(pass)) {
                break; // dau vao hop le, chuyen sang buoc tiep theo
            } else {
                System.out.println(ebank.getMessage("enterPasswordErr"));
            }
        }

        /* 3. NHAP CAPTCHA
         * Muc dich: Sinh ma captcha, nguoi dung nhap vao ma roi chuong trinh kiem tra
         * Cach thuc: Sinh 1 ma captcha 5 ky tu bang ebank.generateCatpcha, luu vao bien
         * Sau do hien thi ma captcha de nguoi nhap vao
         * Su dung ham ebank.checkCaptcha de kiem tra. Neu dau vao gom 1 hoac toan bo ky tu cua ma captcha, 
         * thong bao dang nhap thanh cong. Neu ko, thong bao loi theo ngon ngu da chon va yeu cau nhap lai
         */
        String generatedCaptcha = ebank.generateCaptcha();
        System.out.println("Captcha: " + generatedCaptcha);
        while (true) {
            System.out.print(ebank.getMessage("enterCaptcha"));
            String inputCaptcha = scanner.nextLine();

            if (ebank.checkCaptcha(inputCaptcha, generatedCaptcha)) {
                System.out.println(ebank.getMessage("loginSuccess"));
                break; // Dang nhap thanh cong, thoat khoi ham login
            } else {
                System.out.println(ebank.getMessage("enterCaptchaErr"));
            }
        }
    }
}