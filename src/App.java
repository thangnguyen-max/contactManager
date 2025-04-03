import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        ContactManager cm = new ContactManager();
        while(true) {
            System.out.println("---CHƯƠNG TRÌNH QUẢN LÝ DANH BẠ---");
            System.out.println("1. Xem danh sách");
            System.out.println("2. Thêm mới ");
            System.out.println("3. Sửa");
            System.out.println("4. Xóa");
            System.out.println("5. Tìm kiếm");
            System.out.println("6. Đọc từ file");
            System.out.println("0. Thoát");
            System.out.println("----------------------------------");
            System.out.println("Nhập lựa chọn để thực hiện: ");
            Scanner sc = new Scanner(System.in);
            int choice;
            try{
                choice = sc.nextInt();
            }catch(Exception e){
                System.out.println("Lựa chọn không hợp lệ");
                continue;
            }

            switch (choice) {
                case 0:
                    break;
                case 1:
                    //xem danh sách
                    cm.displayAllContacts();
                    break;
                case 2:
                    // thêm mới
                    cm.addContact();
                    break;
                case 3:
                    //Sửa
                    cm.updateContact();
                    break;
                case 4:
                    //Xóa
                    cm.deleteContact();
                    break;
                case 5:
                    //tìm kiếm
                    cm.searchContact();
                    break;
                case 6:
                    //đọc từ file
                    cm.ReadToFile();
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ!");
            }
        }
    }
}
