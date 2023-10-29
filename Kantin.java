import java.util.Scanner;

public class Kantin {
    
    private static final String SECRET_PIN = "123456";
    private static final int MAX_ATTEMPTS = 3;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        for (int attempts = 1; attempts <= MAX_ATTEMPTS; attempts++) {
            System.out.print("Masukkan PIN: ");
            String enteredPin = input.nextLine();

            if (enteredPin.equals(SECRET_PIN)) {
                System.out.println("PIN benar. Akses diberikan.");
                break;
            } else {
                System.out.print("\nPIN salah. ");
                if (attempts == 3) {
                    System.exit(0);
                }
                System.out.print("Coba lagi.\n");
            }
        }
        
        String namaBarang, jenisPembayaran;
        float diskon, ppn;
        double harga, total = 0, hargaDiskon = 0, totalDiskon = 0, totalPpn = 0;
        
        while (true) {
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
            total += totalDiskon + totalPpn;

            System.out.println("Tambah Barang: (y/n)");
            if (input.next().equalsIgnoreCase("n")) {
                break;
            }
        }


        System.out.println("Total transaksi anda " + String.format("%.2f", total));

        System.out.println("Pilih Jenis Pembayaran: ");
        System.out.println("1. Cash");
        System.out.println("2. Cashless");
        int pilihPembayaran = input.nextInt();
        if (pilihPembayaran == 1) {
            jenisPembayaran = "Tunai";
        } else {
            System.out.println("Pilih Metode Pembayaran Cashless: ");
            System.out.println("1. QRIS");
            System.out.println("2. Transfer Bank");
            int metodePembayaran = input.nextInt();
            if (metodePembayaran == 1) {
                jenisPembayaran = "QRIS";
            } else {
                jenisPembayaran = "Transfer Bank";
            }
        }
        System.out.println("Metode Pembayaran : "+jenisPembayaran);

        input.close();
    }
}

// 1. Jenis Pembayaran, 2. Voucher, 3. PO, 4. Charge for cashless payment, 5. Bayar & Kembali