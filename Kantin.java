import java.util.Scanner;

public class Kantin {
    private static final String[][] users = {
        {"inventaris", "pass_inventaris", "Inventaris"},
        {"kasir", "pass_kasir", "Kasir"},
        {"owner", "pass_owner", "Owner"}
    };
    private static final String[] members = {"Yatno", "Yanti", "Budi"};
    private static final float memberDiskon = 5;
    private static final int MAX_ATTEMPTS = 3;
    private static final double CHARGE_CASHLESS = 1000;

    static String[][] menuItems = {
        {"Nasi Goreng", "Mie Goreng", "Ayam Bakar"},
        {"Es Teh", "Es Jeruk", "Kopi"}
    };

    static double[][] menuPrices = {
        {10000, 8000, 12000},
        {3000, 3500, 4000}
    };

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int userIndex = authenticateUser(input);

        switch (userIndex) {
            case 0:
                
                break;
            case 1:
                orderProcess(input, false);
                break;
        
            default:
                break;
        }

        input.close();
    }

    public static void orderProcess(Scanner input, boolean exit) {
        if (exit) {
            System.exit(0);
        }

        String jenisPembayaran = "";
        System.out.print("\nMasukkan nama pelanggan: ");
        String pelanggan = input.nextLine();

        double charge = 0;
        double total = 0;

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Makanan");
            System.out.println("2. Minuman");
            System.out.println("3. Selesai Belanja\n");
            int pilihan = input.nextInt();

            if (pilihan == 1 || pilihan == 2) {
                int categoryIndex = pilihan - 1; // Adjusting for array index

                System.out.println("Pilih menu:");
                for (int i = 0; i < menuItems[categoryIndex].length; i++) {
                    System.out.println(i + 1 + ". " + menuItems[categoryIndex][i] + " - Rp" + menuPrices[categoryIndex][i]);
                }

                int itemChoice = input.nextInt();
                if (itemChoice >= 1 && itemChoice <= menuItems[categoryIndex].length) {
                    double harga = menuPrices[categoryIndex][itemChoice - 1];
                    System.out.print("Masukkan Diskon (%): ");
                    double diskon = input.nextFloat() / 100;
                    System.out.print("Masukkan PPN (%): ");
                    double ppn = input.nextFloat() / 100;

                    double hargaDiskon = harga * diskon;
                    double totalItem = (harga - hargaDiskon) * (1 + ppn);
                    total += totalItem;

                    System.out.println("Total harga " + menuItems[categoryIndex][itemChoice - 1] + ": " + String.format("%.2f", totalItem));
                    System.out.println("Tambah Menu: (y/n)");
                    if (input.next().equalsIgnoreCase("n")) {
                        break;
                    }
                } else {
                    System.out.println("Pilihan menu tidak valid.");
                }
            } else if (pilihan == 3) {
                break;
            } else {
                System.out.println("Pilihan tidak valid.");
            }
        }

        if (isMember(pelanggan)) {
            System.out.println("\nTotal: " + String.format("%.2f", total));
            System.out.println("Berhasil mendapatkan member diskon (%): "+ memberDiskon);
            total -= total * memberDiskon / 100;
        }

        System.out.println("Total transaksi yang harus dibayar " + String.format("%.2f", total));

        System.out.println("\nMenu Jenis Pembayaran:");
        System.out.println("1. Cash");
        System.out.println("2. Cashless");

        int pilihPembayaran = input.nextInt();
        if (pilihPembayaran == 2) {
            System.out.println("Pilih Metode Pembayaran Cashless:");
            System.out.println("1. QRIS");
            System.out.println("2. Transfer Bank");

            int metodePembayaran = input.nextInt();
            if (metodePembayaran == 1 || metodePembayaran == 2) {
                jenisPembayaran = (metodePembayaran == 1) ? "QRIS" : "Transfer Bank";
                charge = CHARGE_CASHLESS;
            } else {
                System.out.println("Pilihan metode pembayaran cashless tidak valid.");
            }
        } else if (pilihPembayaran != 1) {
            System.out.println("Pilihan jenis pembayaran tidak valid.");
        }

        System.out.println("Metode Pembayaran: " + jenisPembayaran);
        System.out.printf("Jumlah yang harus dibayarkan: %.2f + Charge: %.2f\n", total, charge);

        double bayar = 0, kembali = 0;

        if (pilihPembayaran == 1) {
            while (true) {
                System.out.print("Bayar: ");
                bayar = input.nextDouble();
                if (bayar >= total + charge) {
                    kembali = bayar - (total + charge);
                    break;
                } else {
                    System.out.println("Pembayaran kurang dari total transaksi");
                }
            }
        }

        System.out.printf("Kembali: %.2f", kembali);

        System.out.println("\n1. Transaksi");
        System.out.println("2. Exit");
        int order = input.nextInt();
        orderProcess(input, order == 1 ? false : true);
    }
    
    private static int authenticateUser(Scanner input) {
        int userIndex = -1;
        for (int attempts = 1; attempts <= MAX_ATTEMPTS; attempts++) {
            System.out.print("Masukkan username: ");
            String enteredUsername = input.nextLine();
            System.out.print("Masukkan password: ");
            String enteredPassword = input.nextLine();
            
            for (int i = 0; i < users.length; i++) {
                if (users[i][0].equals(enteredUsername) && users[i][1].equals(enteredPassword)) {
                    userIndex = i;
                }
            }
            
            if (userIndex != -1) {
                String role = users[userIndex][2];
                System.out.println("Login berhasil. Selamat datang, " + role + ".");
                break;
            } else {
                System.out.print("\nUsername atau password salah. ");
                if (attempts == 3) {
                    System.out.println("Anda telah melebihi batas percobaan. Aplikasi keluar.");
                    System.exit(0);
                }
                System.out.println("Coba lagi.\n");
            }
        }

        return userIndex;
    }

    public static boolean isMember(String customerName) {
        for (String member : members) {
            if (member.equalsIgnoreCase(customerName)) {
                return true;
            }
        }
        return false;
    }
}