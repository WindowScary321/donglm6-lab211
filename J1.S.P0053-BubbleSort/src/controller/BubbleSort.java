/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

/**
 * @author pc
 */
public class BubbleSort {

    /* Ham sort
     * Muc dich: Sap xep mang so nguyen theo thu tu tang dan bang thuat toan Bubble Sort
     * Cach thuc: Duyet nhieu luot tu dau mang den cuoi phan chua sap xep
     * So sanh tung cap lien ke va doi cho neu phan tu truoc lon hon phan tu sau
     * Sau moi luot duyet, phan tu lon nhat trong phan chua sap xep dua ve cuoi day, ko can xet lai o luot sau
     * Bien swapped dung de theo doi viec doi cho; neu mot luot ko xay ra lan doi nao => mang da duoc sap xep va thuat toan dung som
     * Thuat toan dung sau toi da n - 1 luot duyet vi khi do tat ca phan tu da o dung vi tri
     * Dau vao: array mang so nguyen can sap xep
     * Dau ra: ko co (sap xep truc tiep tren mang dau vao)
     */
    public void sort(int[] array) {
        // Neu mang rong hoac chi co 1 phan tu thi khong can sap xep
        if (array == null || array.length <= 1) {
            return;
        }

        int n = array.length;
        // Duyet toi da n - 1 luot
        // Sau moi luot i, phan tu lon nhat da ve cuoi day nen khong can xet lai i phan tu o cuoi
        for (int i = 0; i < n - 1; i++) {
            // Dat lai co o dau moi luot, chi check trong luot hien tai
            boolean swapped = false;

            // So sanh tung cap phan tu lien ke trong phan chua sap xep
            for (int j = 0; j < n - 1 - i; j++) {
                // Neu phan tu dung truoc lon hon phan tu dung sau thi doi cho
                if (array[j] > array[j + 1]) {
                    int temp = array[j]; // Luu ben trai vao bien temp
                    // Thuc hien do vi tri
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    swapped = true; // Danh dau da xay ra doi cho trong luot nay
                }
            }

            // Neu ko co lan doi cho thi mang da duoc sap xep, thuat toan dung som
            if (!swapped) {
                break;
            }
        }
    }
}
