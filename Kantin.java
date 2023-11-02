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
        double harga, totalItem, total = 0, hargaDiskon = 0, totalDiskon = 0, totalPpn = 0, charge = 1000;
        
        String[] makanan = {"Nasi Goreng", "Mie Goreng", "Ayam Bakar"};
        double[] hargaMakanan = {10000, 8000, 12000};

        String[] minuman = {"Es Teh", "Es Jeruk", "Kopi"};
        double[] hargaMinuman = {3000, 3500, 4000};

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Makanan");
            System.out.println("2. Minuman");
            System.out.println("3. Selesai Belanja");
            int pilihan = input.nextInt();

            if (pilihan == 1) {
                System.out.println("Pilih makanan:");
                for (int i = 0; i < makanan.length; i++) {
                    System.out.println(i + 1 + ". " + makanan[i] + " - Rp" + hargaMakanan[i]);
                }
                int foodChoice = input.nextInt();
                if (foodChoice >= 1 && foodChoice <= makanan.length) {
                    harga = hargaMakanan[foodChoice - 1];
                    System.out.print("Masukkan Diskon (%): ");
                    diskon = input.nextFloat() / 100;
                    System.out.print("Masukkan PPN (%): ");
                    ppn = input.nextFloat() / 100;

                    hargaDiskon = harga * diskon;
                    totalDiskon = harga - hargaDiskon;
                    totalPpn = totalDiskon * ppn;
                    totalItem = totalDiskon + totalPpn;
                    total += totalItem;

                    System.out.println("Total harga " + makanan[foodChoice - 1] + ": " + String.format("%.2f", totalItem));
                    System.out.println("Tambah Menu: (y/n)");
                    if (input.next().equalsIgnoreCase("n")) {
                        break;
                    }
                } else {
                    System.out.println("Pilihan makanan tidak valid.");
                }
            } else if (pilihan == 2) {
                System.out.println("Pilih minuman:");
                for (int i = 0; i < minuman.length; i++) {
                    System.out.println(i + 1 + ". " + minuman[i] + " - Rp" + hargaMinuman[i]);
                }
                int drinkChoice = input.nextInt();
                if (drinkChoice >= 1 && drinkChoice <= minuman.length) {
                    harga = hargaMinuman[drinkChoice - 1];
                    System.out.print("Masukkan Diskon (%): ");
                    diskon = input.nextFloat() / 100;
                    System.out.print("Masukkan PPN (%): ");
                    ppn = input.nextFloat() / 100;

                    hargaDiskon = harga * diskon;
                    totalDiskon = harga - hargaDiskon;
                    totalPpn = totalDiskon * ppn;
                    totalItem = totalDiskon + totalPpn;
                    total += totalItem;

                    System.out.println("Total harga " + minuman[drinkChoice - 1] + ": " + String.format("%.2f", totalItem));
                    System.out.println("Tambah Menu: (y/n)");
                    if (input.next().equalsIgnoreCase("n")) {
                        break;
                    }
                } else {
                    System.out.println("Pilihan minuman tidak valid.");
                }
            } else if (pilihan == 3) {
                break;
            } else {
                System.out.println("Pilihan tidak valid.");
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
        System.out.printf("Jumlah yang harus dibayarkan: %.2f \n", total);

        double bayar = 0, kembali = 0;

        if (pilihPembayaran == 1) {
            while (true) {
                System.out.print("Bayar: ");
                bayar = input.nextDouble();
                if (bayar >= total) {
                    kembali = bayar - total;
                    break;
                } else {
                    System.out.println("Pembayaran kurang dari total transaksi");
                }
            }
        } else {
            bayar = total;
            System.out.printf("Biaya Layanan: %.2f \n", charge);
            System.out.printf("Bayar: %.2f \n", bayar);
        }

        System.out.printf("Kembali: %.2f", kembali);

        input.close();
    }
}

// 1. Jenis Pembayaran, 2. Voucher, 3. PO, 4. Charge for cashless payment, 5. Bayar & Kembali