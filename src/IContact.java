public interface IContact {
    // Thêm một liên hệ mới
    void addContact();

    // Xóa một liên hệ dựa trên ID hoặc tên
    void deleteContact();

    // Tìm kiếm liên hệ theo tên
    void searchContact();

    // Hiển thị tất cả các liên hệ
    void displayContact();

    // Cập nhật thông tin liên hệ
    void updateContact();
}
