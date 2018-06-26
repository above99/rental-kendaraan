/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rentalkendaraan;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

/**
 *
 * @author samcro
 */
public class RentalKendaraan {

    /**
     * @param args the command line arguments
     */
    static String driver;
    static String[] daftarMobil    = {"Kijang","Xenia","Alphard","Keluar"};
    static int[] tarifMobil        = {300000,450000,700000};
    static int[] tarifDriver       = {150000, 250000};
    
    static void Menu1() {
        System.out.println("== Aplikasi Rental Mobil ==");
        System.out.println(" Anda mau pilih mobil apa?");
        System.out.println("===========================");
        for (int z = 0; z < daftarMobil.length; z++){
            System.out.println("+\t"+(z+1+". ")+daftarMobil[z]+"\t  +");
        }
        System.out.println("===========================");
    }
    
    static void Menu2() {
        System.out.println("\t\t Anda mau pilih paket apa?");
        System.out.println("=================================================================");
        System.out.println("+\t 1. Dalam Kota \t12 Jam Kerja "+(tarifDriver[0])+"/hari \t+");
        System.out.println("+\t 2. Luar Kota \t12 Jam Kerja "+(tarifDriver[1])+"/hari \t+");
        System.out.println("=================================================================");
    }
    
    public static void main(String[] args) {
        
        //inisialisasi variable
        Scanner input = new Scanner(System.in);
        
        //format rupiah
        DecimalFormat kursIndo = (DecimalFormat) DecimalFormat.getCurrencyInstance();
        DecimalFormatSymbols formatRp = new DecimalFormatSymbols();
        formatRp.setCurrencySymbol("Rp. ");
        formatRp.setMonetaryDecimalSeparator(',');
        formatRp.setGroupingSeparator('.');
        kursIndo.setDecimalFormatSymbols(formatRp);
        
        //program inti
        while (true){
            int pilihan, pilihan2 = 0, durasi, total = 0;
            Menu1();
            pilihan = input.nextInt();
            
            
            if (pilihan == 4){
                break;
            }
            while (true){
                if (pilihan > 4 || pilihan < 1) {
                    
                    System.out.println("Input Salah!!");
                    Menu1();
                    pilihan = input.nextInt();
                }
                else break;
            }
            System.out.println("Anda memilih "+daftarMobil[pilihan-1]);
            
            System.out.println("Tarif "+kursIndo.format(tarifMobil[pilihan-1])+"/hari");
            
            while (true){
                System.out.println("Ingin pakai jasa driver? [Y / T]");
                driver = input.next();

                if (driver.equalsIgnoreCase("y")){
                    while (true){
                        Menu2();
                        pilihan2 = input.nextInt();
                        
                        if (pilihan2 > 0 && pilihan2 < 3){
                            break;
                        }
                        else System.out.println("Input salah!");
                    }
                    System.out.println("Anda memilih paket "+pilihan2);
                    total += ((tarifMobil[pilihan-1] + tarifDriver[pilihan2-1] ) );
                    break;
                    
                } else if (driver.equalsIgnoreCase("t")){

                    total += (tarifMobil[pilihan-1]);
                    break;

                } else {
                    System.out.println("Input harus [Y / T]");
                }
            }
            
            System.out.println("Mau sewa berapa hari? ");
            durasi = input.nextInt();
                    
            //format tanggal
            System.out.println("Input tanggal booking. Ex: [2018-05-30]");
            String booking = input.next();
            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-M-d");
            LocalDate tanggal = LocalDate.parse(booking,dateFormat);
            
            while (true){
                if (tanggal.isBefore(LocalDate.now()) == true){

                    System.out.println("Input harus 'sama' / 'lebih' dari tanggal saat ini");
                    System.out.println("Input tanggal booking Ex: [2018-05-30]");
                    booking = input.next();
                    tanggal = LocalDate.parse(booking,dateFormat);

                }else 
                    break;
            
            }
            
            LocalDate tenggat = tanggal.plus(durasi, ChronoUnit.DAYS);
            
            // print out hasil
            System.out.println("=========================================================");
            if (pilihan2 != 0){
                System.out.println("+   Harga sewa driver \t\t: "+kursIndo.format(durasi*tarifDriver[pilihan2-1])+"\t+");
            }
            System.out.println("+   Harga sewa mobil \t\t: "+kursIndo.format(durasi*tarifMobil[pilihan-1])+"\t+");
            System.out.println("+   Total bayar \t\t: "+kursIndo.format(total*durasi)+"\t+");
            System.out.println("+   Tanggal Pengembalian \t: " + tenggat+"\t\t+");
            System.out.println("=========================================================");
            System.out.println("");
        }
        
    }
    
}
