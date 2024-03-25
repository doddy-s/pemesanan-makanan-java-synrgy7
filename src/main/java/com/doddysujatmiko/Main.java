package com.doddysujatmiko;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        App app = new App(input);
        app.run();
        input.close();
    }
}