class BankAccount {
    String ownerName;
    double balance;
    static double interestRate = 0.05;
    public static int totalAccounts = 0;
    public static final String BANK_NAME = "BugBank";

    public BankAccount(String ownerName) {
        this.ownerName = ownerName;
        this.balance = 0;
        totalAccounts++;
    }

    public void deposit(double amount) {
        this.balance += amount;
    }

    public void showAccountInfo() {
        System.out.println("Chủ TK: " + this.ownerName + " | Số dư: " + this.balance + " | Lãi suất: " + interestRate);
    }

    public static void updateInterestRate(double newRate) {
        interestRate = newRate;
        System.out.println("[HỆ THỐNG] Lãi suất mới đã cập nhật: " + newRate);
    }

    public String getOwnerName() { return ownerName; }
    public void setOwnerName(String ownerName) { this.ownerName = ownerName; }
    public double getBalance() { return balance; }
    public static double getInterestRate() { return interestRate; }
}

public class BankAccountSystem {
    public static void main(String[] args) {
        System.out.println("=== HỆ THỐNG NGÂN HÀNG " + BankAccount.BANK_NAME + " ===\n");

        System.out.println("--- KIỂM THỬ 1: TÍNH CÔ LẬP SỐ DƯ ---");
        BankAccount acc1 = new BankAccount("Alice");
        BankAccount acc2 = new BankAccount("Bob");

        acc1.deposit(1000);
        System.out.println("Alice nạp 1000:");
        acc1.showAccountInfo();

        acc2.deposit(500);
        System.out.println("Bob nạp 500:");
        acc2.showAccountInfo();

        System.out.println("Kiểm tra lại Alice:");
        acc1.showAccountInfo();

        System.out.println("\n--- KIỂM THỬ 2: CẬP NHẬT LÃI SUẤT ---");
        System.out.println("Lãi suất hiện tại: " + BankAccount.getInterestRate());
        BankAccount.updateInterestRate(0.07);
        System.out.println("Lãi suất acc1: " + BankAccount.getInterestRate());
        System.out.println("Lãi suất acc2: " + BankAccount.getInterestRate());

        System.out.println("\n--- KIỂM THỬ 3: TỔNG SỐ TÀI KHOẢN ---");
        BankAccount acc3 = new BankAccount("Charlie");
        System.out.println("Tổng số tài khoản: " + BankAccount.totalAccounts);
    }
}
