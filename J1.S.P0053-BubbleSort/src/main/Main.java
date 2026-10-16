/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package main;

import controller.BubbleSort;
import controller.Validator;

/**
 * @author pc
 */
public class Main {

    public static void main(String[] args) {
        // 0. Khoi tao cac doi tuong can thiet
        Validator validator = new Validator();
        BubbleSort bubbleSort = new BubbleSort();

        // 1. Goi Validator de nhap kich thuoc mang la so nguyen duong
        int size = validator.getArraySize();
        // 2. Tao mang co size phan tu ngau nhien trong khoang tu -size den size
        int[] array = validator.generateRandomArray(size);
        // 3. In mang ban dau truoc khi goi ham sap xep
        validator.displayArray(array, "Unsorted array: ");

        // 4. Goi BubbleSort de sap xep truc tiep tren mang da tao
        bubbleSort.sort(array);
        // 5. In lai cung mang do sau khi cac phan tu da duoc sap xep tang dan
        validator.displayArray(array, "Sorted array: ");
    }
}
