package basics;

import java.util.Scanner;

public class Input {
    
    public static Scanner scanner = new Scanner(System.in);

    public static String scan(String placeholder){
        Print.out(placeholder);
        String str = scanner.nextLine();
        return  str;
    }
    
}
