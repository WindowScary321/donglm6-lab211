/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package controller;

import java.util.ArrayList;
import java.util.List;

/**
 * @author pc
 */
public class LinearSearch {

    /* Ham search
     * Muc dich: Tim tat ca cac vi tri cua so can tim bang thuat toan Linear Search
     * Cach thuc: Duyet tu dau den cuoi mang, khong dung lai o lan tim thay dau tien
     * Ham tra ve ket qua sau khi duyet qua toan bo mang
     * Dau vao: array mang so nguyen, searchValue so can tim
     * Dau ra: danh sach index tim thay, danh sach rong neu khong tim thay
     *
     */
    public List<Integer> search(int[] array, int searchValue) {
        List<Integer> indexes = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
            // Neu phan tu bang so can tim, luu index sau do tiep tuc duyet
            if (array[i] == searchValue) {
                indexes.add(i);
            }
        }
        return indexes; // Chi tra ve ket qua khi da duyet het mang
    }
}
