package controller;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Validator {

    /*
     * Khai bao cac doi tuong dung chung cho nhap du lieu va sinh so ngau nhien
     */
    private final Scanner scanner;
    private final Random random;

    /*
     * Khoi tao Scanner va Random khi doi tuong Validator duoc tao
     */
    public Validator() {
        scanner = new Scanner(System.in);
        random = new Random();
    }

    /* Ham getInt
     * Muc dich: Nhap mot so nguyen tu ban phim va yeu cau nhap lai neu dau vao khong hop le
     * Cach thuc: Hien thi thong bao, doc mot dong tu ban phim bang nextLine va loai bo khoang trang o hai dau,
     * neu chuoi rong thi thong bao loi va yeu cau nhap lai,
     * su dung Integer.parseInt de chuyen doi sang so nguyen,
     * neu gap NumberFormatException thi thong bao loi va tiep tuc yeu cau nhap lai;
     * khong bat NoSuchElementException de ngoai le EOF duoc truyen len ham main xu ly
     * Dau vao: message chuoi thong bao yeu cau nguoi dung nhap du lieu
     * Dau ra: so nguyen hop le trong pham vi cua kieu int
     */
    public int getInt(String message) {
        while (true) {
            System.out.print(message);
            String input = scanner.nextLine().trim();
            // Neu dau vao rong, bao loi va yeu cau nhap lai
            if (input.isEmpty()) {
                System.out.println("Input must not be empty. Please enter again");
                continue;
            }
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                // Neu dau vao khong phai so nguyen, bao loi va yeu cau nhap lai
                System.out.println("Invalid input. Please enter again");
            }
        }
    }

    /* Ham getArraySize
     * Muc dich: Nhap so luong phan tu cua mang la so nguyen duong
     * Cach thuc: Su dung ham getInt de nhap so nguyen voi thong bao Enter number of array: ,
     * kiem tra neu gia tri lon hon 0 thi tra ve ket qua,
     * neu gia tri nho hon hoac bang 0 thi bao loi va yeu cau nhap lai
     * Dau vao: ko co
     * Dau ra: so nguyen duong n thoa man dieu kien n > 0
     */
    public int getArraySize() {
        while (true) {
            int size = getInt("Enter number of array: ");
            // Chi chap nhan kich thuoc mang lon hon 0
            if (size > 0) {
                return size;
            }
            System.out.println("Number of array must be greater than 0. Please enter again");
        }
    }

    /* Ham generateRandomArray
     * Muc dich: Sinh mang gom size phan tu ngau nhien trong doan [-size, size] gom ca hai dau
     * Cach thuc: Khoi tao mang so nguyen co kich thuoc bang size,
     * su dung random.nextLong(-(long) size, (long) size + 1) de tranh tran so nguyen,
     * sau do ep kieu ve int va gan vao tung vi tri phan tu cua mang
     * Dau vao: size so luong phan tu cua mang (so nguyen duong)
     * Dau ra: mang so nguyen chua cac gia tri ngau nhien trong khoang [-size, size]
     */
    public int[] generateRandomArray(int size) {
        int[] array = new int[size];
        // Duyet qua tung vi tri cua mang de gan gia tri ngau nhien
        for (int i = 0; i < size; i++) {
            array[i] = (int) random.nextLong(-(long) size, (long) size + 1);
        }
        return array;
    }

    /* Ham displayArray
     * Muc dich: Hien thi toan bo mang ra console kem theo thong bao
     * Cach thuc: Su dung Arrays.toString de chuyen doi mang thanh chuoi va in kem message
     * Dau vao: array mang so nguyen can hien thi, message thong bao di kem
     * Dau ra: ko co
     */
    public void displayArray(int[] array, String message) {
        System.out.println(message + Arrays.toString(array));
    }

    /* Ham displaySearchResult
     * Muc dich: In toan bo cac vi tri (index) tim thay hoac thong bao khong ton tai trong mang
     * Cach thuc: Neu danh sach indexes rong thi in thong bao Not found searchValue in the array,
     * nguoc lai in Found searchValue at index: va duyet qua toan bo danh sach cac index tim duoc,
     * phan tach bang dau phay va dau cach giong voi bai mau LinearSearch
     * Dau vao: searchValue gia tri can tim, indexes danh sach cac vi tri tim thay
     * Dau ra: ko co
     */
    public void displaySearchResult(int searchValue, List<Integer> indexes) {
        // Kiem tra danh sach ket qua tim kiem tra ve tu thuat toan
        if (indexes == null || indexes.isEmpty()) {
            System.out.println("Not found " + searchValue + " in the array");
        } else {
            System.out.print("Found " + searchValue + " at index: ");
            for (int i = 0; i < indexes.size(); i++) {
                System.out.print(indexes.get(i));
                if (i < indexes.size() - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println();
        }
    }
}