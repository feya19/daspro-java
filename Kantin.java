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

    static String[] categoryItems = {"Makanan", "Minuman"};

    static String[][] menuItems = {
        {"Nasi Goreng", "Mie Goreng", "Ayam Bakar"},
        {"Es Teh", "Es Jeruk", "Kopi"}
    };

    static double[][] menuPrices = {
        {10000, 8000, 12000},
        {3000, 3500, 4000}
    };

    static int[][] menuStock = {
        {10, 80, 12},
        {30, 35, 40}
    };

    static int[][] menuStockMutasi = {
        {0, 0, 0},
        {0, 0, 0}
    };
    
    static int[][] stockAdjustments = {
        {0, 0, 0},
        {0, 0, 0}
    };

    static double[][] menuHpp = {
        {8000, 6000, 10000},
        {2500, 3000, 3500}
    };

    static final int maxTransaction = 100;
    static int transactionCount = 0;
    static String[][] transactions = new String[maxTransaction][6];

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("\n--- Selamat datang di Sistem Kantin JTI Polinema ---");
        System.out.println("1. Login");
        System.out.println("2. Close Program");
        System.out.print("Pilihan: ");
        if (input.nextInt() != 1) {
            System.exit(0);
        }
        input.nextLine();
        int userIndex = authenticateUser(input);

        switch (userIndex) {
            case 0:
                inventory(input, false);
                break;
            case 1:
                orderProcess(input, false);
                break;
            case 2:
                report(input, false);
                break;
            default:
                break;
        }

        input.close();
    }

    public static void orderProcess(Scanner input, boolean exit) {
        if (exit) {
            main(null);
        }

        if (maxTransaction == transactionCount) {
            System.out.println("Telah Mencapai Batas Transaksi");
            main(null);
        }

        String jenisPembayaran = "Cash";
        System.out.println("\n-----Transaksi-----");
        System.out.print("Masukkan nama pelanggan: ");
        String pelanggan = input.nextLine();
        String pelangganStatus = isMember(pelanggan) ? "Member" : "Reguler";
        System.out.printf("Pelanggan %s\n", pelangganStatus);

        double hpp = 0;
        double charge = 0;
        double total = 0;
        boolean noProducts = true;

        while (true) {
            System.out.println("\n--- Kategori Menu ---");
            for (int i = 0; i < categoryItems.length; i++) {
                System.out.println(String.format("%d. %s", i + 1, categoryItems[i]));
            }
            System.out.print("Pilihan: ");
            int categoryIndex = input.nextInt() - 1;

            if (categoryItems[categoryIndex] != null) {

                System.out.println("\nPilih menu:");
                for (int i = 0; i < menuItems[categoryIndex].length; i++) {
                    System.out.println(i + 1 + ". " + menuItems[categoryIndex][i] + " - Rp" + menuPrices[categoryIndex][i]);
                }
                System.out.print("Pilihan: ");

                int itemChoice = input.nextInt() - 1;
                if (menuItems[categoryIndex][itemChoice] != null) {
                    int itemStock = menuStock[categoryIndex][itemChoice] - menuStockMutasi[categoryIndex][itemChoice];
                    if (itemStock > 0) {
                        System.out.print("Masukkan Jumlah: ");
                        int jumlah = input.nextInt();
                        if (itemStock < jumlah) {
                            System.out.println("Maaf, stok menu " + menuItems[categoryIndex][itemChoice] + " tidak cukup.");
                            System.out.println("Tambah Menu Lain: (y/n)");
                            if (input.next().equalsIgnoreCase("n")) {
                                input.nextLine();
                                break;
                            }
                            continue;
                        }

                        double harga = menuPrices[categoryIndex][itemChoice];
                        System.out.print("Masukkan Diskon (%): ");
                        double diskon = input.nextFloat() / 100;
                        System.out.print("Masukkan PPN (%): ");
                        double ppn = input.nextFloat() / 100;

                        harga *= jumlah;
                        double hargaDiskon = harga * diskon;
                        double totalItem = (harga - hargaDiskon) * (1 + ppn);
                        total += totalItem;

                        System.out.println("Total harga " + menuItems[categoryIndex][itemChoice] + ": " + String.format("%.2f", totalItem));
                        menuStockMutasi[categoryIndex][itemChoice] += jumlah;
                        hpp += menuHpp[categoryIndex][itemChoice] * jumlah;
                        noProducts = false;
                    } else {
                        System.out.println("Maaf, stok menu " + menuItems[categoryIndex][itemChoice] + " sudah habis.");
                    }
                    
                    System.out.println("Tambah Menu Lain: (y/n)");
                    if (input.next().equalsIgnoreCase("n")) {
                        break;
                    }
                } else {
                    System.out.println("Pilihan menu tidak valid.");
                }
            } else {
                System.out.println("Pilihan tidak valid.");
            }
        }

        if (noProducts) {
            System.out.println("Tidak ada menu yang terpilih");
            orderProcess(input, false);
        }
    
        if (isMember(pelanggan)) {
            System.out.println("\nTotal: " + String.format("%.2f", total));
            System.out.println("Berhasil mendapatkan member diskon (%): "+ memberDiskon);
            total -= total * memberDiskon / 100;
        }

        System.out.println("Total transaksi " + String.format("%.2f", total));

        System.out.println("\nMenu Jenis Pembayaran:");
        System.out.println("1. Cash");
        System.out.println("2. Cashless");

        int pilihPembayaran = input.nextInt();
        if (pilihPembayaran == 2) {
            System.out.println("\nPilih Metode Pembayaran Cashless:");
            System.out.println("1. QRIS");
            System.out.println("2. Transfer Bank");

            int metodePembayaran = input.nextInt();
            jenisPembayaran = metodePembayaran == 1 ? "QRIS" : "Transfer Bank";
            charge = CHARGE_CASHLESS;
        }

        System.out.println("\nMetode Pembayaran: " + jenisPembayaran);
        if (charge > 0) {
            System.out.printf("Charge: %.2f\n", charge);
        }
        System.out.printf("Jumlah yang harus dibayarkan: %.2f\n", total + charge);

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

        System.out.printf("Kembali: %.2f\n", kembali);
        transactions[transactionCount] = new String[]{pelanggan, jenisPembayaran, String.format("%.2f", hpp), String.format("%.2f", total), String.format("%.2f", bayar), String.format("%.2f", kembali)};
        transactionCount++;
        System.out.println("\n--- Menu ---");
        System.out.println("1. Transaksi");
        System.out.println("2. Exit");
        System.out.print("Pilihan: ");
        int order = input.nextInt();
        input.nextLine();
        orderProcess(input, order == 1 ? false : true);
    }
    
    public static void inventory(Scanner input, boolean exit) {
        if (exit) {
           main(null);
        }
    
        while (true) {
            System.out.println("\n--- Menu ---");
            System.out.println("1. Edit Menu Items");
            System.out.println("2. Exit");
            System.out.print("Pilihan: ");

            int choice = input.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("\n--- Pilih Kategori ---");
                    for (int i = 0; i < categoryItems.length; i++) {
                        System.out.println(String.format("%d. %s", i + 1, categoryItems[i]));
                    }
                    System.out.print("Pilihan: ");
                    int categoryIndex = input.nextInt() - 1;
            
                    if (categoryItems[categoryIndex] != null) {
                        System.out.println("\n--- Edit Menu ---");
                        for (int j = 0; j < menuItems[categoryIndex].length; j++) {
                            System.out.println(String.format("%d. %s - Harga: Rp. %.2f - Stock: %d - Hpp: Rp. %.2f", j + 1, menuItems[categoryIndex][j], menuPrices[categoryIndex][j], menuStock[categoryIndex][j], menuHpp[categoryIndex][j]));
                        }
                        System.out.print("Pilih menu: ");
                        int menuItemIndex = input.nextInt() - 1;
                
                        if (menuItems[categoryIndex][menuItemIndex] != null) {
                            System.out.println("\n--- Edit ---");
                            System.out.println("1. Edit nama");
                            System.out.println("2. Edit stok");
                            System.out.println("3. Edit harga");
                            System.out.println("4. Edit hpp");
                            System.out.println("5. Kembali");
                            System.out.print("Pilihan: ");

                            int editOption = input.nextInt();
                            input.nextLine();

                            switch (editOption) {
                                case 1:
                                    // Edit item name
                                    System.out.print("Nama baru: ");
                                    String newName = input.nextLine();
                                    menuItems[categoryIndex][menuItemIndex] = newName;
                                    System.out.println("Nama menu berhasil diubah.");
                                    break;
                                case 2:
                                    // Edit item stock
                                    System.out.print("Penyesuaian stok: ");
                                    int stockAdjustment = input.nextInt();
                                    menuStock[categoryIndex][menuItemIndex] = stockAdjustment;
                                    stockAdjustments[categoryIndex][menuItemIndex] += stockAdjustment * (stockAdjustment > menuStock[categoryIndex][menuItemIndex] ? 1 : -1);
                                    System.out.println("Stok menu berhasil diubah.");
                                    break;
                                case 3:
                                    // Edit item price
                                    System.out.print("Harga baru: ");
                                    double newPrice = input.nextDouble();
                                    menuPrices[categoryIndex][menuItemIndex] = newPrice;
                                    System.out.println("Harga menu berhasil diubah.");
                                    break;
                                case 4:
                                    // Edit item hpp
                                    System.out.print("Hpp baru: ");
                                    double newHpp = input.nextDouble();
                                    menuHpp[categoryIndex][menuItemIndex] = newHpp;
                                    System.out.println("Hpp menu berhasil diubah.");
                                    break;
                                case 5:
                                    // Back to inventory menu
                                    break;
                                default:
                                    System.out.println("Pilihan tidak valid.");
                                    break;
                            }
                        } else {
                            System.out.println("Pilihan kategori tidak valid.");
                        }
                    }
                    break;
                case 2:
                    inventory(input, true);
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
                    break;
            }
        }
    }

    public static void report(Scanner input, boolean exit) {
        if (exit) {
            main(null);
        }
    
        while (true) {
            System.out.println("\n--- Report Menu ---");
            System.out.println("1. Report Penjualan");
            System.out.println("2. Report Penyesuaian");
            System.out.println("3. Report Stok");
            System.out.println("4. Exit");
            System.out.print("Pilihan: ");
    
            int choice = input.nextInt();
    
            switch (choice) {
                case 1:
                    generateReportPenjualan();
                    break;
                case 2:
                    generateReportPenyesuaian();
                    break;
                case 3:
                    generateReportStok();
                    break;
                case 4:
                    report(input, true);
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
                    break;
            }
        }
        }
    
        public static void generateReportPenjualan() {
            System.out.println("\n--- Report Penjualan ---");
            System.out.printf("%-4s|%-20s|%-16s|%-12s|%-10s|%-12s|%-10s|%-10s\n", "No", "Pelanggan", "Jenis Pembayaran", "Hpp", "Total", "Bayar", "Kembali", "Keuntungan");
            
            for (int i = 0; i < transactionCount; i++) {
                boolean pelangganMember = isMember(transactions[i][0]);
                System.out.printf("%-4s|%-20s|%-16s|%-12s|%-10s|%-12s|%-10s|%-10s\n", (i + 1), transactions[i][0] + (pelangganMember ? "(Member)" : ""), transactions[i][1], transactions[i][2], transactions[i][3], transactions[i][4], transactions[i][5], String.format("%.2f", Double.parseDouble(transactions[i][3]) - Double.parseDouble(transactions[i][2])));
            }
        }
    
        public static void generateReportPenyesuaian() {
            System.out.println("\n--- Report Penyesuaian ---");
            System.out.printf("%-4s|%-20s|%-10s|%-12s\n", "No", "Menu", "Perubahan", "Nilai");
        
            for (int i = 0; i < menuItems.length; i++) {
                for (int j = 0; j < menuItems[i].length; j++) {
                    int perubahan = stockAdjustments[i][j];
                    double nilai = perubahan * menuHpp[i][j];
                    System.out.printf("%-4s|%-20s|%-10s|%-12s\n", (i * menuItems[i].length + j + 1), menuItems[i][j], perubahan, nilai);
                }
            }
        }

        public static void generateReportStok() {
            System.out.println("\n--- Report Stok ---");
            System.out.printf("%-4s|%-20s|%-10s\n", "No", "Menu", "Stok");
        
            for (int i = 0; i < menuItems.length; i++) {
                for (int j = 0; j < menuItems[i].length; j++) {
                    int stok = stockAdjustments[i][j] + menuStock[i][j] - menuStockMutasi[i][j];
                    System.out.printf("%-4s|%-20s|%-10s\n", (i * menuItems[i].length + j + 1), menuItems[i][j], stok);
                }
            }
        }
    
    private static int authenticateUser(Scanner input) {
        int userIndex = -1;
        System.out.println("\n-----Login-----");
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