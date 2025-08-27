/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ziptestdemo;

/**
 *
 * @author 6324569
 */
public class ZipCode {
    public int Zip = 0;
    
    public ZipCode(int num) {
        String zip = "" + num;
        
        if (zip.length() > 5) {
            System.out.println(zip + " zip code is more than 5 digits");
        }
        
        Zip = Integer.parseInt(zip);
    }

    public ZipCode(String str) {
        Zip = parseBarCode(str);
    }
    
    private int parseBarCode(String barCode) {
        if (barCode.length() < 27) {
            System.out.println("Error: bar code must be in multiples of 5-binary digits");
            return 0;
        }
        if (barCode.charAt(0) != '1' || barCode.charAt(barCode.length() - 1) != '1') {
            System.out.println("Error: bar code missing a 1 at start or end");
            return 0;
        }
        
        for (int i = 0; i < barCode.length(); i++) {
            if (barCode.charAt(i) != '1' && barCode.charAt(i) != '0') {
                System.out.println("Error: " + barCode.charAt(i) + " must be '0' or '1'");
            }
        }
        
        String zip = "";
        
        for (int i = 0; i < 5; i++) {
            String temp = "";
            
            for (int j = 0; j < 5; j++) {
                temp = temp + barCode.charAt(5 * i + j + 1);
            }
            
            String num = switch(temp) {
                case "11000" -> "0";
                case "00011" -> "1";
                case "00101" -> "2";
                case "00110" -> "3";
                case "01001" -> "4";
                case "01010" -> "5";
                case "01100" -> "6";
                case "10001" -> "7";
                case "10010" -> "8";
                case "10100" -> "9";
                default -> "A";
            };
            
            if (num.equals("A")) {
                System.out.println(temp + " has invalid sequence in the bar code");
                return 0;
            }
            
            zip += num;
            
        }
        
        return Integer.parseInt(zip);
    }
    
    public String GetBarCode() {
        String zip = "" + Zip;
        
        if (zip.length() > 5) {
            zip = zip.substring(zip.length() - 5, zip.length());
        } else if (zip.length() < 5) {
            int store = zip.length();
            
            for (int i = 0; i < 5 - store; i++) {
                zip = "0" + zip;
            }
        }
        
        String barCode = "";
        
        for (Character c : zip.toCharArray()) {
            barCode += switch(c) {
                case '0' -> "11000";
                case '1' -> "00011";
                case '2' -> "00101";
                case '3' -> "00110";
                case '4' -> "01001";
                case '5' -> "01010";
                case '6' -> "01100";
                case '7' -> "10001";
                case '8' -> "10010";
                case '9' -> "10100";
                default -> "";
            };
        }
        
        barCode += "1";
        barCode = 1 + barCode;
        
        return barCode;
    }
}
