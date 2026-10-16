/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package main;

import controller.LinearSearch;
import controller.Validator;
import java.util.List;

/**
 * @author pc
 */
public class Main {
    
    public static void main(String[] args) {
        // 0. Khoi tao cac doi tuong can thiet
        Validator validator = new Validator(); 
        LinearSearch linearSearch = new LinearSearch();
        
        // 1. Goi Validator de nhap kich thuoc mang la so nguyen duong
        int size = validator.getArraySize();
        // 2. Tao mang co size phan tu ngau nhien trong khoang tu -size den size
        int[] array = validator.generateRandomArray(size);
        // 3. In mang truoc khi thuc hien tim kiem
        validator.displayArray(array);
        // 4. Goi Validator de nhap so can tim
        int searchValue = validator.getInt("Enter search value: ");

        // 5. Goi search tu class linearSearch de thuc hien tim kiem
        List<Integer> indexes = linearSearch.search(array, searchValue);
        // 6. In ket qua gom so can tim va index da tim dc 
        validator.displaySearchResult(searchValue, indexes);
    }
}