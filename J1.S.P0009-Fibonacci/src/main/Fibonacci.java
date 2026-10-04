/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

/**
 * @author pc
 */
public class Fibonacci {

    /*
     * Mang luu cac so fibonacci de tinh toan nhanh hon
     */
    private int[] fibo;

    /*
     * Ham tao mang luu ket qua cho 45 so fibonacc
     */
    public Fibonacci() {
        fibo = new int[45];
    }

    /*
     * Tinh so fibonacci tai vi tri index su dung de quy co nho
     * Neu dau vao la 0, 1 tra ve dung 0, 1
     * Neu dau vao >2, thuc hien tinh toan de quy
     * Dau vao: index vi tri can tinh trong day Fibonacci
     * Dau ra: gia tri so Fibonacci tai vi tri index
     */
    public int getFiboNum(int index) {
        if (index == 0 || index == 1) {
            return index;
        }
        // Neu so da tinh xuat hien trong mang, tra ve de giam thoi gian tinh toan
        if (fibo[index] != 0) {
            return fibo[index];
        }
        // Thuc hien goi de quy
        fibo[index] = getFiboNum(index - 1) + getFiboNum(index - 2);
        return fibo[index];
    }

    /*
     * In 45 so fibonacci cung so index 
     * Su dung ham for, goi lan luot ham getFiboNum() 45 lan
     * In ra 45 so dau tien cua day Fibonacci
     * Dau vao: ko co
     * Dau ra: ko co
     */
    public void printFiboSequence(){
        for (int i = 0; i < 45; i++){
            System.out.print(getFiboNum(i) + " (" + (i + 1) + ")");
            if (i < 44) {
                System.out.print(", ");
            }
        }
        System.out.println("");
    }
}
