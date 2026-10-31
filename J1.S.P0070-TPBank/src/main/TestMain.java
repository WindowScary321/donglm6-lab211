/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import java.util.Scanner;
import controller.Ebank;

/**
 *
 * @author pc
 */
public class TestMain {
    public static void main(String[] args) {
        while (true){
        Scanner scanner = new Scanner(System.in);
        Ebank ebank = new Ebank();
        String captcha = ebank.generateCaptcha();
        System.out.println("Captcha: " + captcha);
        
        String input = scanner.nextLine();
        System.out.println(ebank.checkCaptcha(input, captcha));
        }
        
    }
    
}
