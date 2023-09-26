import java.util.Scanner;

public class Kantin {
    
    private static final String SECRET_PIN = "123456";

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Masukkan PIN: ");
        String enteredPin = input.nextLine();

        if (enteredPin.equals(SECRET_PIN)) {
            System.out.println("PIN benar. Akses diberikan.");
        } else {
            System.out.print("\nPIN salah. ");
            System.exit(0);
        }

        input.close();
    }
}