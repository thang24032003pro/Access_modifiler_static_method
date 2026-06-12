/**
 * Lớp Student với các thuộc tính private và các phương thức public
 * để thay đổi giá trị thuộc tính.
 */
public class Student {
    // Hai thuộc tính private với giá trị mặc định
    private String name = "John";
    private String classes = "C02";

    // Hàm tạo không có tham số
    public Student() {
    }

    // Phương thức public để thay đổi name
    public void setName(String name) {
        this.name = name;
    }

    // Phương thức public để thay đổi classes
    public void setClasses(String classes) {
        this.classes = classes;
    }

    // Phương thức hiển thị thông tin (để kiểm tra kết quả)
    public void display() {
        System.out.println("Name: " + name + ", Class: " + classes);
    }
}
