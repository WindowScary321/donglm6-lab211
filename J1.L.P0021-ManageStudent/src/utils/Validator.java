package utils;

import java.util.Scanner;

/* Cac ham nhap lieu dung chung, luon doc ca dong de tranh loi troi lenh */
public final class Validator {
    private static final Scanner SCANNER = new Scanner(System.in, "UTF-8");
    private static final String STUDENT_ID_REGEX = "[Ss]\\d+"; // Dau mssv phai co chu S/s, phia sau la 1 hoac nhieu so
    // Bien chua thong bao loi nhap sai dinh dang mssv
    private static final String STUDENT_ID_ERROR =
            "ID must start with S or s followed by at least one digit (e.g. S1).";

    private Validator() {
    }

    /* Ham getString
     * Muc dich: Nhap mot chuoi khong rong va dung dinh dang yeu cau
     * Cach thuc: Cat khoang trang hai dau, kiem tra rong va regex; sai thi nhap lai
     * Dau vao: messageInfo loi nhac, messageError thong bao loi, regex dinh dang
     * Dau ra: chuoi hop le da loai bo khoang trang hai dau
     */
    public static String getString(String messageInfo, String messageError, String regex) {
        while (true) {
            System.out.print(messageInfo);
            String value = SCANNER.nextLine().trim();
            if (!value.isEmpty() && value.matches(regex)) {
                return value;
            }
            System.out.println(messageError);
        }
    }

    /* Ham checkStudentId
     * Muc dich: Kiem tra ID theo quy uoc S/s va it nhat mot chu so theo sau
     * Cach thuc: Tu choi null, chuoi rong; cat khoang trang hai dau va kiem tra
     * toan bo chuoi bang regex [Ss]\\d+, khong tu them S vao ID nhap sai
     * Dau vao: id ma sinh vien can kiem tra
     * Dau ra: ID hop le da trim; nem IllegalArgumentException neu sai dinh dang
     */
    public static String checkStudentId(String id) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("ID must not be empty.");
        }
        String value = id.trim();
        if (!value.matches(STUDENT_ID_REGEX)) {
            throw new IllegalArgumentException(STUDENT_ID_ERROR);
        }
        return value;
    }

    /* Ham getStudentId
     * Muc dich: Nhap ID hop le, dung chung cho tao, tim va cap nhat sinh vien
     * Cach thuc: Doc ca dong, goi checkStudentId, neu sai thi in loi va nhap lai
     * ngay tai truong ID, khong chuyen sang nhap ten hay hoc ky
     * Dau vao: messageInfo loi nhac nhap ID
     * Dau ra: ID dung dinh dang S/s va chu so, da cat khoang trang hai dau
     */
    public static String getStudentId(String messageInfo) {
        while (true) {
            System.out.print(messageInfo);
            try {
                return checkStudentId(SCANNER.nextLine());
            } catch (IllegalArgumentException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    /* Ham getInt
     * Muc dich: Nhap so nguyen trong khoang [min, max]
     * Cach thuc: Dung Integer.parseInt, bat loi dinh dang va kiem tra pham vi
     * chi tra ve khi hop le, khong bat loi het du lieu de Main xu ly EOF
     * Dau vao: loi nhac, hai thong bao loi, min va max
     * Dau ra: so nguyen hop le
     */
    public static int getInt(String messageInfo, String messageErrorOutOfRange,
            String messageErrorInvalidNumber, int min, int max) {
        while (true) {
            System.out.print(messageInfo);
            try {
                int number = Integer.parseInt(SCANNER.nextLine().trim());
                if (number >= min && number <= max) {
                    return number;
                }
                System.out.println(messageErrorOutOfRange);
            } catch (NumberFormatException ex) {
                System.out.println(messageErrorInvalidNumber);
            }
        }
    }
}
