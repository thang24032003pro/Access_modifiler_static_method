/**
 * HỆ THỐNG NGÂN HÀNG - BUGBANK (ĐÃ SỬA LỖI)
 * Đã phân định rạch ròi biên giới giữa static và instance.
 */
class BankAccount {
    // === INSTANCE VARIABLES (mỗi đối tượng có bản sao riêng trên Heap) ===
    String ownerName;
    double balance; // ĐÃ SỬA BUG 1: Xóa static -> mỗi tài khoản có số dư riêng

    // === STATIC VARIABLES (dùng chung cho toàn bộ Class, lưu trên Metaspace) ===
    static double interestRate = 0.05; // ĐÃ SỬA BUG 2: Thêm static -> chỉ 1 bản sao duy nhất
    public static int totalAccounts = 0; // Đếm tổng số tài khoản đang hoạt động

    // === STATIC CONSTANT ===
    public static final String BANK_NAME = "BugBank"; // Hằng số cấu hình, không thể sửa đổi

    // Constructor
    public BankAccount(String ownerName) {
        this.ownerName = ownerName;
        this.balance = 0;
        totalAccounts++; // Tự động tăng khi tạo tài khoản mới
    }

    // Instance method - truy cập trạng thái riêng của từng đối tượng
    public void deposit(double amount) {
        this.balance += amount; // Chỉ cộng vào số dư của đối tượng hiện tại
    }

    // Instance method - hiển thị thông tin tài khoản
    public void showAccountInfo() {
        System.out.println("Chủ TK: " + this.ownerName
                + " | Số dư: " + this.balance
                + " | Lãi suất: " + interestRate);
    }

    // Static method - cập nhật lãi suất chung cho toàn bộ hệ thống
    public static void updateInterestRate(double newRate) {
        interestRate = newRate;
        System.out.println("[HỆ THỐNG] Lãi suất mới đã cập nhật: " + newRate);
    }

    // Getters and Setters
    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public double getBalance() {
        return balance;
    }

    public static double getInterestRate() {
        return interestRate;
    }
}

public class BankAccountSystem {
    public static void main(String[] args) {
        System.out.println("=== HỆ THỐNG NGÂN HÀNG " + BankAccount.BANK_NAME + " (ĐÃ SỬA LỖI) ===\n");

        // --- KIỂM THỬ 1: TÍNH CÔ LẬP SỐ DƯ ---
        System.out.println("--- KIỂM THỬ 1: TÍNH CÔ LẬP SỐ DƯ TÀI KHOẢN ---");
        BankAccount acc1 = new BankAccount("Alice");
        BankAccount acc2 = new BankAccount("Bob");

        acc1.deposit(1000);
        System.out.println("Alice nạp 1000:");
        acc1.showAccountInfo();

        acc2.deposit(500); // Bob nạp tiền
        System.out.println("Bob nạp 500:");
        acc2.showAccountInfo();

        // Chứng minh: Số dư Alice KHÔNG bị ảnh hưởng bởi Bob
        System.out.println("Kiểm tra lại Alice sau khi Bob nạp tiền:");
        acc1.showAccountInfo();

        // --- KIỂM THỬ 2: CẬP NHẬT LÃI SUẤT ĐỒNG BỘ ---
        System.out.println("\n--- KIỂM THỬ 2: CẬP NHẬT LÃI SUẤT HỆ THỐNG ---");
        System.out.println("Lãi suất hiện tại: " + BankAccount.getInterestRate());
        BankAccount.updateInterestRate(0.07);
        System.out.println("Lãi suất acc1: " + BankAccount.getInterestRate());
        System.out.println("Lãi suất acc2: " + BankAccount.getInterestRate());
        System.out.println("=> Cả hai đều cập nhật đồng bộ vì interestRate là static!");

        // --- KIỂM THỬ 3: ĐẾM TỔNG SỐ TÀI KHOẢN ---
        System.out.println("\n--- KIỂM THỬ 3: TỔNG SỐ TÀI KHOẢN ---");
        BankAccount acc3 = new BankAccount("Charlie");
        System.out.println("Tổng số tài khoản đã tạo: " + BankAccount.totalAccounts);
        System.out.println("=> totalAccounts là static, tự động tăng trong Constructor.");
    }
}
