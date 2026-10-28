/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package main;

import controller.BinarySearch;
import controller.BubbleSort;
import controller.Validator;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author pc
 */
public class Main {

    public static void main(String[] args) {
        // 0. Khoi tao cac doi tuong can thiet
        Validator validator = new Validator();
        BubbleSort bubbleSort = new BubbleSort();
        BinarySearch binarySearch = new BinarySearch();

        // 1. Nhap so luong phan tu cua mang la so nguyen duong
        int size = validator.getArraySize();
        // 2. Sinh mang gom size phan tu ngau nhien trong khoang [-size, size]
        int[] array = validator.generateRandomArray(size);

        // 3. Goi BubbleSort de sap xep mang da tao theo thu tu tang dan
        bubbleSort.sort(array);
        // 4. In ngay mang da sap xep
        validator.displayArray(array, "Sorted array: ");
        // 5. Nguoi dung nhap so can tim
        int searchValue = validator.getInt("Enter search value: ");

        // 6. Tao danh sach luu lai cac chi so middle trong qua trinh tim kiem nhi phan
        List<Integer> middleIndexes = new ArrayList<>();
        // 7. Goi search tu BinarySearch de tim kiem va lay toan bo cac vi tri trung nhau
        List<Integer> foundIndexes = binarySearch.search(array, searchValue, middleIndexes);

        // 8. In tung buoc thuat toan hoat dong de nguoi dung theo doi
        binarySearch.displaySearchSteps(array, searchValue, middleIndexes, !foundIndexes.isEmpty());
        // 9. In ket qua, gom gia tri can tim va toan bo index da tim dc hoac thong bao ko ton tai
        validator.displaySearchResult(searchValue, foundIndexes);

    }
}