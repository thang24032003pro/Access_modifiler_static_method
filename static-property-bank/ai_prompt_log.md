# Nhật ký Prompt AI - Dự án BugBank
## Core System Engineer: Nguyễn Tiến Thắng

---

### Prompt 1: Hiểu bản chất Class variable vs Instance variable
**Câu hỏi:** "Trong Java, tại sao biến static lại được gọi là Class variable, còn biến thông thường là Instance variable? Khi ta sửa đổi giá trị của một biến static thông qua một đối tượng cụ thể, điều gì xảy ra với các đối tượng khác?"

**Trả lời của AI:** Biến static được gọi là Class variable vì nó thuộc về chính cái lớp (Class), không thuộc về bất kỳ đối tượng nào. Khi JVM nạp class vào bộ nhớ (Class Loading), biến static được khởi tạo một lần duy nhất trên vùng Metaspace. Tất cả các đối tượng đều trỏ đến cùng một ô nhớ đó. Do đó, khi sửa giá trị static qua một đối tượng bất kỳ, mọi đối tượng khác đều nhìn thấy giá trị mới ngay lập tức vì chúng cùng chia sẻ một địa chỉ bộ nhớ.

**Bài học rút ra:** Đây chính là nguyên nhân gây ra BUG 1 của BugBank - khi `balance` bị khai báo static, tất cả tài khoản dùng chung một ô nhớ số dư.

---

### Prompt 2: Phân tích lỗi truy cập static context
**Câu hỏi:** "Tôi định thiết kế một hàm `public static void showAccountInfo()` để in ra tên chủ tài khoản và số dư. Trình biên dịch Java báo lỗi 'Non-static field cannot be referenced from a static context'. Tại sao một static method lại không thể truy cập trực tiếp vào instance variable?"

**Trả lời của AI:** Static method được gọi thông qua tên lớp (VD: `BankAccount.showAccountInfo()`), không cần tạo đối tượng. Vì không có đối tượng cụ thể nào, từ khóa `this` không tồn tại trong static context. Khi viết `this.ownerName`, Java không biết đang nói đến `ownerName` của đối tượng nào vì static method không gắn với bất kỳ instance nào. Do đó, static method chỉ có thể truy cập static variable và gọi static method khác.

**Bài học rút ra:** Hàm `showAccountInfo()` phải là instance method vì nó cần truy cập `this.ownerName` và `this.balance` - những dữ liệu riêng của từng đối tượng.

---

### Prompt 3: Sử dụng final kết hợp static để tạo hằng số
**Câu hỏi:** "Hãy chỉ cho tôi cách dùng từ khóa `final` kết hợp với `static` để tạo ra một hằng số cấu hình hệ thống trong Java, đảm bảo không ai có thể vô tình sửa đổi nó ở chỗ khác."

**Trả lời của AI:** Kết hợp `static final` tạo ra một hằng số cấp lớp, chỉ được gán giá trị một lần duy nhất và không thể thay đổi sau đó. Quy ước đặt tên là viết HOA toàn bộ, các từ cách nhau bằng dấu gạch dưới. Ví dụ: `public static final String BANK_NAME = "BugBank";`. Nếu cố gán lại giá trị, trình biên dịch sẽ báo lỗi ngay lập tức. Biến `static final` được lưu trên Metaspace, tồn tại suốt vòng đời ứng dụng, rất phù hợp cho các giá trị cấu hình cố định.

**Bài học rút ra:** Áp dụng `public static final String BANK_NAME = "BugBank"` cho tên ngân hàng vì nó là hằng số cấu hình, không bao giờ thay đổi.

---

### Prompt 4: Phân tích tối ưu bộ nhớ RAM
**Câu hỏi:** "Hãy đóng vai một chuyên gia tối ưu hóa bộ nhớ, viết cho tôi một đoạn phân tích để so sánh dung lượng RAM tiết kiệm được khi chuyển một biến `double` từ instance sang static đối với hệ thống có 10 triệu đối tượng."

**Trả lời của AI:** Một biến `double` trong Java chiếm 8 byte trên Heap. Với 10 triệu đối tượng:
- **Dạng instance:** 10,000,000 × 8 byte = 80,000,000 byte ≈ **76.3 MB** RAM chỉ cho một biến.
- **Dạng static:** Chỉ cần đúng **8 byte** duy nhất trên Metaspace, bất kể số lượng đối tượng.
- **Tiết kiệm:** 79,999,992 byte ≈ **76.3 MB** cho mỗi biến được chuyển sang static.

Nếu có nhiều biến cấu hình chung bị khai báo sai thành instance, lượng RAM lãng phí sẽ nhân lên theo cấp số nhân, dẫn đến OutOfMemoryError như thảm họa BugBank.

**Bài học rút ra:** Biến `interestRate` là cấu hình chung, khai báo static giúp tiết kiệm ~76MB RAM trên hệ thống 10 triệu tài khoản.

---

### Prompt 5: Cơ chế nạp Class và vòng đời vùng nhớ
**Câu hỏi:** "Trong Java, Metaspace (nơi lưu static) và Heap (nơi lưu instance) khác nhau thế nào về thời điểm sinh ra và mất đi trong vòng đời ứng dụng?"

**Trả lời của AI:** 
- **Metaspace:** Được khởi tạo khi JVM nạp class lần đầu tiên (Class Loading). Biến static sống từ lúc class được nạp cho đến khi JVM tắt hoặc class bị unload. Nó không bị Garbage Collector thu hồi theo cách thông thường.
- **Heap:** Đối tượng được tạo trên Heap mỗi khi gọi `new`. Khi không còn tham chiếu nào trỏ tới, Garbage Collector sẽ tự động thu hồi bộ nhớ. Vòng đời ngắn hơn và linh hoạt hơn Metaspace.

**Bài học rút ra:** Static variable tồn tại suốt đời ứng dụng nên chỉ nên dùng cho dữ liệu thực sự cần dùng chung. Instance variable có vòng đời gắn với đối tượng, phù hợp cho trạng thái riêng tư.
