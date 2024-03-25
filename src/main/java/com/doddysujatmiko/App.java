package com.doddysujatmiko;

import com.doddysujatmiko.entities.Dish;
import com.doddysujatmiko.entities.Transaction;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    private final Scanner input;

    private boolean shouldEnd = false;

    private String menu;

    private String view;

    private String customer = "";

    private final Dish[] dishes = {
            new Dish("Nasi Goreng", 15000),
            new Dish("Mie Goreng", 13000),
            new Dish("Nasi Ayam", 10000),
            new Dish("Es Teh", 4000),
            new Dish("Es Jeruk", 5000),
            new Dish("Sambal bawang")
    };

    private final List<Transaction> transactions = new ArrayList<>();

    public App(Scanner input) {
        this.input = input;
        drawMenu();
    }

    private void drawMenu() {
        StringBuilder builder = new StringBuilder();

        builder.append("Silahkan pilih makanan:\n");
        for(int i = 0; i< dishes.length; i++) {
            builder
                    .append((i + 1))
                    .append(". ")
                    .append(dishes[i].getName())
                    .append("\t|\t")
                    .append(dishes[i].getPrice())
                    .append('\n');
        }

        menu = builder.toString();
    }

    private void render() {
        System.out.print(view);
    }

    private void order(int x) {
        Transaction exist = null;
        for (Transaction o: transactions){
            if (o.getDish().equals(dishes[x])){
                exist = o;
                break;
            }
        }

        if(exist != null) {
            System.out.println("Anda memilih menu yang sama, input jumlah untuk mengupdate!, input 0 untuk menghapus!");
        }
        view = "==============================\n" +
                "Berapa Pesanan Anda\n" +
                "==============================\n" +
                dishes[x].getName() +
                "\t|\t" +
                dishes[x].getPrice() +
                "\n(input 0 untuk kembali)\n" +
                "qty => ";
        render();

        int amount = input.nextInt();

        if(exist != null) {
            if(amount < 1) {
                transactions.remove(exist);
                return;
            }
            transactions.set(transactions.indexOf(exist), new Transaction(dishes[x], amount));
            return;
        }

        if(amount < 1) return;
        transactions.add(new Transaction(dishes[x], amount));
    }

    private void pay() {
        int pilihan;
        int totalHarga = 0;
        view = "==============================\n" +
                "Konfirmasi dan Pembayaran\n" +
                "==============================\n";

        view += "Nama pelanggan = " + customer + "\n";

        StringBuilder builder = new StringBuilder();
        for(int i = 0; i< transactions.size(); i++) {
            Transaction temp = transactions.get(i);
            totalHarga += temp.getTotalPrice();
            builder
                    .append((i + 1))
                    .append(". ")
                    .append(temp.getDish().getName())
                    .append("\t|\t")
                    .append(temp.getAmount())
                    .append("\t|\t")
                    .append(temp.getTotalPrice())
                    .append('\n');
        }

        view += builder.toString();
        view += "-------------------------------+\n" +
                "Total\t|\t" + totalHarga + "\n";
        view += "1. Konfirmasi dan bayar\n" +
                "2. Kembali ke menu utama\n" +
                "0. Keluar Aplikasi" +
                "=> ";
        render();
        pilihan = input.nextInt();
        if(pilihan == 1) {
            System.out.print("Nama file?");
            input.nextLine();
            String fileName = input.nextLine();
            saveStringAsTxt(view, fileName + ".txt");
            transactions.clear();
            customer = "";
        } else if (pilihan == 0) {
            shouldEnd = true;
        }
    }

    public static void saveStringAsTxt(String content, String filePath) {
        try {
            FileWriter writer = new FileWriter(filePath);
            writer.write(content);
            writer.close();
            System.out.println("Struk pembayaran berhasil disimpan.");
        } catch (IOException e) {
            System.err.println("An error occurred while saving the string to file: " + e.getMessage());
        }
    }

    public void run() {
        int pilihan;
        String header = "==============================\n" +
                "Selamat Datang di BinarFud\n" +
                "==============================\n";
        String navChoices = "99. Pesan dan bayar\n" +
                "0. Keluar Aplikasi\n";
        while (!shouldEnd) {
            if(customer.isEmpty()) {
                System.out.print("Masukkan nama pelanggan => ");
                customer = input.nextLine();
            }

            view = header + menu + navChoices + "=> ";
            render();
            pilihan = input.nextInt();
            if(pilihan == 0) {
                shouldEnd = true;
            } else if (pilihan == 99) {
                pay();
            } else if(pilihan > 0 && pilihan <= dishes.length){
                order(pilihan-1);
            } else {
                System.out.print("Input salah tekan Enter!");
                input.nextLine();
                input.nextLine();
            }
        }
    }
}
