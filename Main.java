package calc;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println(calc(sc.nextLine()));
        sc.close();
    }

    public static String calc(String input) throws Exception {
        int result = 0;
        int num1 = 0;
        int num2 = 0;
        String[] array = input.replaceAll(" ", "").split("[+-/*]");

        if (array.length > 2) {
            throw new Exception("Формат математической операции не удовлетворяет заданию - два целых операнда и один оператор (+, -, /, *)");
        }
        if (!(input.contains("+") || input.contains("-") || input.contains("*") || input.contains("/"))) {
            throw new Exception("Строка не является математической операцией");
        }

        if (RomanNumerals.isRoman(array[0]) && RomanNumerals.isRoman(array[1])) {
            num1 = (RomanNumerals.toArab(array[0]));
            num2 = (RomanNumerals.toArab(array[1]));
            if (input.contains("+")) {
                result = num1 + num2;
                return RomanNumerals.romArr[result];
            } else if (input.contains("-")) {
                result = num1 - num2;
                if (result < 1) {
                    throw new Exception("В римской системе нет отрицательных чисел");
                }
                return RomanNumerals.romArr[result];
            } else if (input.contains("*")) {
                result = num1 * num2;
                return RomanNumerals.romArr[result];
            } else if (input.contains("/")) {
                result = num1 / num2;
                return RomanNumerals.romArr[result];
            }
        } else if (!RomanNumerals.isRoman(array[0]) && !RomanNumerals.isRoman(array[1])) {
            num1 = Integer.parseInt(array[0]);
            num2 = Integer.parseInt(array[1]);
            if (num1 < 1 || num2 < 1) {
                throw new Exception("Вы ввели число меньше 1");
            }
            if (num1 > 10 || num2 > 10) {
                throw new Exception("Вы ввели число больше 10");
            }
            if (input.contains("+")) {
                result = num1 + num2;
            } else if (input.contains("-")) {
                result = num1 - num2;
            } else if (input.contains("*")) {
                result = num1 * num2;
            } else if (input.contains("/")) {
                result = num1 / num2;
            }
        } else {
            throw new Exception("Используются одновременно разные системы счисления");
        }
        return Integer.toString(result);
    }
}

class RomanNumerals {
    static String[] romArr = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI",
            "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX", "XXI", "XXII", "XXIII", "XXIV",
            "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI",
            "XXXVII", "XXXVIII", "XXXIX", "XL", "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII",
            "XLIX", "L", "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX", "LXI", "LXII",
            "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX", "LXXI", "LXXII", "LXXIII", "LXXIV",
            "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX", "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV",
            "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC", "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII",
            "XCVIII", "XCIX", "C"
    };

    public static boolean isRoman(String value) {
        for (String s : romArr) {
            if (value.equals(s)) {
                return true;
            }
        }
        return false;
    }

    public static int toArab(String rom) {
        for (int i = 0; i < romArr.length; i++) {
            if (rom.equals(romArr[i])) {
                return i;
            }
        }
        return 0;
    }
}