class Student {
    // Hai thuộc tính private với giá trị mặc định
    private String name = "John";
    private String classes = "C02";

    // Hàm tạo không có tham số
    public Student() {}

    // Thay đổi access modifier thành PRIVATE
    private void setName(String name) {
        this.name = name;
    }

    // Thay đổi access modifier thành PRIVATE
    private void setClasses(String classes) {
        this.classes = classes;
    }

    // Phương thức hiển thị thông tin
    public void display() {
        System.out.println("Name: " + name + ", Class: " + classes);
    }
}

public class TestPrivate {
    public static void main(String[] args) {
        // Tạo đối tượng Student
        Student student = new Student();

        // Hiển thị giá trị mặc định
        System.out.print("Gia tri mac dinh: ");
        student.display();

        // Truy cập phương thức setName (private) -> LỖI BIÊN DỊCH!
        student.setName("Alice");

        // Truy cập phương thức setClasses (private) -> LỖI BIÊN DỊCH!
        student.setClasses("A01");

        // Hiển thị sau khi thay đổi
        System.out.print("Sau khi thay doi: ");
        student.display();
    }
}
