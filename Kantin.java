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

        String namaBarang;
        float diskon, ppn;
        double harga, total, hargaDiskon, totalDiskon, totalPpn;
        
        System.out.print("Masukkan nama barang : ");
        namaBarang = input.next();
        System.out.print("Masukkan harga barang : ");
        harga = input.nextDouble();
        System.out.print("Masukkan Diskon (%): ");
        diskon = input.nextFloat() / 100;
        System.out.print("Masukkan PPN (%): ");
        ppn = input.nextFloat() / 100;

        hargaDiskon = harga * diskon;
        totalDiskon = harga - hargaDiskon;
        totalPpn = totalDiskon * ppn;
        total = totalDiskon + totalPpn;

        System.out.println("Total transaksi anda " + String.format("%.2f", total));

        input.close();
    }
}