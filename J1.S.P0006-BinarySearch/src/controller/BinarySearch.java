package controller;

import java.util.ArrayList;
import java.util.List;

public class BinarySearch {

    /* Ham search - Thuat toan tim kiem nhi phan (Binary Search)
     *
     * Muc dich:
     * - Tim kiem tat ca cac vi tri (index) cua so can tim trong mang da sap xep tang dan
     * - Neu so can tim xuat hien nhieu lan, lay day du tat ca cac vi tri do
     * - Neu khong tim thay trong mang, tra ve danh sach rong
     *
     * Thuat toan chi tiet:
     * 1. Su dung Binary Search de tim mot vi tri bang dung gia tri can tim:
     *    - Dung hai bien left (dau mang) va right (cuoi mang)
     *    - Vong lap while (left <= right) chia doi pham vi tim kiem:
     *      + Tinh vi tri o giua: mid = left + (right - left) / 2
     *      + Luu chi so mid vao middleIndexes de in tung buoc (step trace)
     *      + So sanh array[mid] voi searchValue:
     *        * Neu array[mid] < searchValue: bo nua trai, left = mid + 1
     *        * Neu array[mid] > searchValue: bo nua phai, right = mid - 1
     * 2. Khi tim thay array[mid] == searchValue:
     *    - Do mang da sap xep tang dan, tat ca cac phan tu trung nhau chac chan nam lien tiep nhau
     *    - Quet sang trai tu vi tri mid de tim phan tu trung dau tien
     *    - Quet sang phai tu vi tri mid de tim phan tu trung cuoi cung
     *    - Them toan bo cac chi so tu dau den cuoi nay vao danh sach ket qua
     * 3. Neu khong tim thay sau khi duyet het, tra ve danh sach rong
     *
     * Dau vao: array mang so nguyen da sap xep tang dan
     *          searchValue gia tri so nguyen can tim
     *          middleIndexes danh sach de luu lai cac vi tri mid qua tung buoc tim kiem
     * Dau ra: danh sach chua tat ca cac index tim dc, hoac danh sach rong neu ko tim thay
     */
    public List<Integer> search(int[] array, int searchValue, List<Integer> middleIndexes) {
        // Xoa lich su cu trong danh sach truoc khi thuc hien tim kiem
        middleIndexes.clear();
        List<Integer> foundIndexes = new ArrayList<>();

        if (array == null || array.length == 0) {
            return foundIndexes;
        }

        // 1. Xac dinh pham vi tim kiem ban dau: tu dau mang (0) den cuoi mang (array.length - 1)
        int left = 0;
        int right = array.length - 1;

        // 2. Vong lap tim kiem nhi phan chia doi mang
        while (left <= right) {
            // Tinh vi tri phan tu o giua (mid)
            int mid = left + (right - left) / 2;

            // Luu lai vi tri mid dang xet de hien thi tung buoc
            middleIndexes.add(mid);

            // So sanh phan tu o giua voi so can tim
            if (array[mid] == searchValue) {
                // Tim thay mot vi tri mid
                // Do mang da sap xep tang dan, cac phan tu trung nhau nam lien ke nhau quanh mid:
                // Tim moc bat dau cua chuoi cac so trung nhau (quet sang trai)
                int start = mid;
                while (start > 0 && array[start - 1] == searchValue) {
                    start--;
                }

                // Tim moc ket thuc cua chuoi cac so trung nhau (quet sang phai)
                int end = mid;
                while (end < array.length - 1 && array[end + 1] == searchValue) {
                    end++;
                }

                // Them tat ca cac vi tri trung nhau vao danh sach ket qua
                for (int i = start; i <= end; i++) {
                    foundIndexes.add(i);
                }
                return foundIndexes;
            } else if (array[mid] < searchValue) {
                // So can tim lon hon phan tu o giua, tim tiep o nua ben phai
                left = mid + 1;
            } else {
                // So can tim nho hon phan tu o giua, tim tiep o nua ben trai
                right = mid - 1;
            }
        }

        // Tra ve ket qua, ke ca danh sach rong neu ko tim thay
        return foundIndexes;
    }

    /* Ham displaySearchSteps
     * Muc dich: In chi tiet tung buoc thuat toan Binary Search hoat dong de nguoi dung theo doi
     * Cach thuc: Duyet qua danh sach middleIndexes chua cac chi so mid da ghi nhan trong qua trinh tim kiem,
     * so sanh phan tu tai chi so mid voi gia tri can tim de xac dinh toan tu so sanh (<, >, ==) chi dung de format,
     * in thong tin buoc va goi ham printArrayStep de in toan bo mang voi phan tu tai mid duoc danh dau [],
     * neu khong tim thay (found la false) thi in them buoc searched value is absent khong danh dau phan tu nao
     * Dau vao: array mang so nguyen da sap xep, searchValue gia tri can tim,
     *          middleIndexes danh sach cac chi so mid qua tung buoc, found co tim thay gia tri trong mang hay khong
     * Dau ra: ko co
     */
    public void displaySearchSteps(int[] array, int searchValue, List<Integer> middleIndexes, boolean found) {
        int stepCount = middleIndexes.size();
        // Duyet qua cac chi so middle element da duoc ghi nhan trong qua trinh tim kiem
        for (int i = 0; i < stepCount; i++) {
            int mid = middleIndexes.get(i);
            int midValue = array[mid];
            // Xac dinh toan tu so sanh giua middle element va searchValue de hien thi
            String operator;
            if (midValue < searchValue) {
                operator = " < ";
            } else if (midValue > searchValue) {
                operator = " > ";
            } else {
                operator = " == ";
            }
            System.out.print("Step " + (i + 1) + " (middle element is " + midValue + operator + searchValue + "): ");
            printArrayStep(array, mid);
        }

        // Neu khong tim thay phan tu trong mang, in them buoc thong bao searched value is absent
        if (!found) {
            System.out.print("Step " + (stepCount + 1) + " (searched value is absent): ");
            printArrayStep(array, -1);
        }
    }

    /* Ham printArrayStep
     * Muc dich: In toan bo mang trong tung buoc tim kiem, danh dau duy nhat phan tu tai vi tri mid
     * Cach thuc: Duyet qua tung phan tu trong mang; neu vi tri hien tai bang mid thi bao quanh bang ngoac vuong [],
     * neu mid bang -1 thi khong danh dau phan tu nao; cac so cach nhau dung mot dau cach, khong co dau cach thua;
     * neu mang rong hoac null thi hien thi []
     * Dau vao: array mang so nguyen can hien thi, mid chi so phan tu o giua (-1 neu khong danh dau)
     * Dau ra: ko co
     */
    private void printArrayStep(int[] array, int mid) {
        if (array == null || array.length == 0) {
            System.out.println("[]");
            return;
        }
        StringBuilder sb = new StringBuilder();
        // Duyet qua tung vi tri cua mang de ghep chuoi hien thi
        for (int i = 0; i < array.length; i++) {
            if (i > 0) {
                sb.append(" ");
            }
            if (i == mid) {
                sb.append("[").append(array[i]).append("]");
            } else {
                sb.append(array[i]);
            }
        }
        System.out.println(sb.toString());
    }
}