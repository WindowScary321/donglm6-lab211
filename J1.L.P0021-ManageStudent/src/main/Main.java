package main;

import controller.Controller;
import java.util.NoSuchElementException;
import utils.Validator;

public class Main {
    /* Ham main
     * Muc dich: Chay chuong trinh va dieu huong menu quan ly sinh vien
     * Cach thuc: Dung mot Controller de giu du lieu, lap menu va goi chuc nang;
     * loi nghiep vu duoc thong bao roi quay lai menu, Exit hoac EOF thi ket thuc
     * Dau vao: args cac tham so dong lenh (khong su dung)
     * Dau ra: giao dien console, khong luu du lieu sau khi thoat
     */
    public static void main(String[] args) {
        Controller control = new Controller();
        try {
            while (true) {
                var choice = Validator.getInt("""
                                              
                                              WELCOME TO STUDENT MANAGEMENT
                                              1. Create
                                              2. Find and Sort
                                              3. Update/Delete
                                              4. Report
                                              5. Exit
                                              Enter your choice: """,
                        "Please enter number 1 to 5.", "Choice must be an integer.", 1, 5);
                try {
                    switch (choice) {
                        case 1 -> control.createStudent();
                        case 2 -> control.findAndSort();
                        case 3 -> control.updateOrDelete();
                        case 4 -> control.report();
                        case 5 -> {
                            System.out.println("Goodbye!");
                            return;
                        }
                        default -> {
                        }
                    }
                } catch (IllegalArgumentException | IllegalStateException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        } catch (NoSuchElementException ex) {
            // Ket thuc gon khi console dong hoac nguoi dung nhan Ctrl+D
            System.out.println("\nInput closed.");
        }
    }
}
