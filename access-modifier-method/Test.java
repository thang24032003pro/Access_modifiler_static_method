/**
 * Lớp Test - tạo đối tượng Student và truy cập các phương thức setName, setClasses.
 *
 * === PHẦN 1: Khi setName và setClasses là public ===
 * -> Truy cập bình thường, không lỗi.
 *
 * === PHẦN 2: Thay đổi access modifier thành private ===
 * -> Sẽ gây lỗi biên dịch khi truy cập từ lớp Test (bên ngoài lớp Student).
 * -> Để test phần 2, hãy vào Student.java đổi "public" thành "private" ở setName và setClasses.
 */
public class Test {
    public static void main(String[] args) {
        // ============================================================
        // PHẦN 1: setName và setClasses có access modifier là PUBLIC
        // ============================================================
        System.out.println("=== PHAN 1: setName va setClasses la PUBLIC ===");

        // Tạo đối tượng Student bằng hàm tạo không tham số
        Student student = new Student();

        // Hiển thị giá trị mặc định
        System.out.print("Gia tri mac dinh: ");
        student.display();

        // Truy cập phương thức setName (public) -> OK
        student.setName("Alice");

        // Truy cập phương thức setClasses (public) -> OK
        student.setClasses("A01");

        // Hiển thị sau khi thay đổi
        System.out.print("Sau khi thay doi: ");
        student.display();

        System.out.println();

        // ============================================================
        // PHẦN 2: Thay đổi access modifier thành PRIVATE
        // ============================================================
        // Hướng dẫn: Vào file Student.java, thay đổi:
        //   public void setName(...)   -> private void setName(...)
        //   public void setClasses(...) -> private void setClasses(...)
        //
        // Sau đó biên dịch lại. Kết quả: LỖI BIÊN DỊCH!
        // Lỗi: setName(String) has private access in Student
        // Lỗi: setClasses(String) has private access in Student
        //
        // Giải thích: Khi access modifier là private, các phương thức
        // chỉ có thể được truy cập bên trong chính lớp Student,
        // không thể truy cập từ lớp Test (bên ngoài).
        // ============================================================

        System.out.println("=== PHAN 2: Huong dan ===");
        System.out.println("Vao file Student.java, doi 'public' thanh 'private'");
        System.out.println("o setName va setClasses, roi bien dich lai.");
        System.out.println("Se xuat hien loi: has private access in Student");
    }
}
