/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 * @author pc
 */
public class Validator {

    /*
     * Them cac doi tuong can thiet
     */
    private final Scanner scanner;
    private final Random random;

    /*
     * Khoi tao cac doi tuong can thiet khi class dc goi 
     */
    public Validator() {
        scanner = new Scanner(System.in);
        random = new Random();
    }

    /* Ham getInt
     * Muc dich: Nhap so nguyen va yeu cau nhap lai neu dau vao khong hop le
     * Cach thuc: Loai bo khoang trang o dau va cuoi chuoi truoc khi kiem tra
     * Dau vao: string thong bao yeu cau nhap so
     * Dau ra: so nguyen hop le trong pham vi cua kieu int
     */
    public int getInt(String message) {
        while (true) {
            System.out.print(message);
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("Input must not be empty. Please enter again");
                continue;
            }
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter again");
            }
        }
    }

    /* Ham getArraySize
     * Muc dich: Nhap so luong phan tu cua mang
     * Cach thuc: Su dung ham getInt de bat loi co ban
     * Neu n nho hon 0, thong bao loi va yeu cau nhap lai
     * Neu so lon hon 0, return gia tri do
     * Dau vao: ko co
     * Dau ra: so nguyen duong n
     */
    public int getArraySize() {
        while (true) {
            int size = getInt("Enter number of array: ");
            if (size > 0) {
                return size;
            }
            System.out.println("Number of array must be greater than 0. Please enter again");
        }
    }

    /* Ham generateRandomArray
     * Muc dich: Sinh mang co size phan tu ngau nhien trong khoang -size den size
     * Cach thuc: Tao mang rong co tong phan tu bang size
     * Su dung random.nextInt de them tung gia tri vao mang
     * Dau vao: size so nguyen duong da duoc kiem tra
     * Dau ra: mang so nguyen ngau nhien
     */
    public int[] generateRandomArray(int size) {
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(2 * size + 1) - size;
        }
        return array;
    }

    /* Ham displayArray
     * In toan bo mang bang Arrays.toString kem thong bao o ham main
     * Dau vao: array mang can hien thi, message thong bao o ham main
     * Dau ra: ko co
     */
    public void displayArray(int[] array, String message) {
        System.out.println(message + Arrays.toString(array));
    }

}
