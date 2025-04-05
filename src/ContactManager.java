import java.io.*;
import java.util.*;


public class ContactManager implements IContact {
    public ContactManager() {
    }

    ArrayList<ContactMember> contactList = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);

    File file = new File("contacts.txt");

    public void WriteToFile(String data) {
        try {
            FileWriter fileWriter = new FileWriter(file, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.append(data);
            bufferedWriter.newLine();
            bufferedWriter.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public void ReadToFile() {
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    @Override
    public void displayContact() {
        ReadToFile();
    }


    @Override
    public void addContact() {
        System.out.println("---Nhập thông tin---");
        System.out.println("Nhập tên liên hệ: ");
        String name = scanner.nextLine();
        System.out.println("Nhập số điện thoại: ");
        String phone = scanner.nextLine();
        System.out.println("Nhập email: ");
        String email = scanner.nextLine();
        System.out.println("Nhập giới tính(nam/nữ): ");
        String gender = scanner.nextLine();
        System.out.println("Nhập địa chỉ:");
        String address = scanner.nextLine();
        System.out.println("Nhập nhóm danh bạ");
        String groupName = scanner.nextLine();

        Regex regex = new Regex(phone, email, gender);
        if (!regex.Test(phone, email, gender)) {
            System.out.println("Vui lòng nhập lại thông tin");
            System.out.println("Thông tin phải thỏa mãn:");
            System.out.println("số điện thoại đủ 10 số, email đúng định dạng 'tennguoidung@tenmien' , giới tính chỉ có thể là 'nam' hoặc 'nữ'");
            return;
        }
        ContactMember contact = new ContactMember(name, phone, email, gender, address, groupName);
        contactList.add(contact);
        WriteToFile(contact.toString());
        System.out.println("Đã thêm một liên hệ mới vào danh bạ");
    }

    @Override
    public void searchContact() {
        System.out.println("Nhập tên liên hệ bạn muốn tìm kiếm: ");
        String name = scanner.nextLine();
        boolean found = false;
        for (ContactMember contact : contactList) {
            if (contact.getName().contains(name)) {
                System.out.println(contact);
                found = true;
            }
        }
        if (found == false || name == null) {
            System.out.println("Không tìm thấy liên hệ ");

        }
    }

    @Override
    public void deleteContact() {
        System.out.println("Nhập tên liên hệ bạn muốn xóa: ");
        String name = scanner.nextLine().trim();
        boolean found = false;

        for (ContactMember contact : contactList) {
            if (contact.getName().contains(name)) {
                System.out.println(contact);
                found = true;
            }
        }

        if (!found || name.isEmpty()) {
            System.out.println("Không tìm thấy liên hệ.");
            return;
        }


        System.out.println("Bạn có chắc chắn muốn xóa " + name + " không?");
        System.out.println("1. Có");
        System.out.println("2. Không");


        int choice;
        while (true) {
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                scanner.nextLine();
                break;
            } else {
                System.out.println("Vui lòng nhập số 1 hoặc 2.");
                scanner.nextLine();
            }
        }


        if (choice == 1) {
            Iterator<ContactMember> iterator = contactList.iterator();
            while (iterator.hasNext()) {
                ContactMember contact = iterator.next();
                if (contact.getName().equalsIgnoreCase(name)) {
                    iterator.remove();
                    updateToFile();
                    System.out.println("Đã xóa thành công một liên hệ.");
                    break;
                }
            }
        } else {
            System.out.println("Hủy thao tác xóa.");
        }
    }

    public void updateContact() {
        System.out.println("Nhập tên liên hệ bạn cần sửa:");
        String oldContact = scanner.nextLine().trim();
        boolean found = false;

        Iterator<ContactMember> iterator = contactList.iterator();
        while (iterator.hasNext()) {
            ContactMember contact = iterator.next();
            if (contact.getName().contains(oldContact)) {
                System.out.println(contact);
                found = true;

                System.out.println("---Nhập thông tin thay đổi (bỏ trống nếu không thay đổi)---");
                System.out.print("Tên liên hệ: ");
                String name = scanner.nextLine();
                System.out.print("Số điện thoại: ");
                String phone = scanner.nextLine();
                System.out.print("Email: ");
                String email = scanner.nextLine();
                System.out.print("Giới tính (nam/nữ): ");
                String gender = scanner.nextLine();
                System.out.print("Địa chỉ: ");
                String address = scanner.nextLine();
                System.out.println("Nhóm danh bạ: ");
                String groupName = scanner.nextLine();


                contact.setName(name.trim().isEmpty() ? contact.getName() : name);
                contact.setPhone(phone.trim().isEmpty() ? contact.getPhone() : phone);
                contact.setEmail(email.trim().isEmpty() ? contact.getEmail() : email);
                contact.setGender(gender.trim().isEmpty() ? contact.getGender() : gender);
                contact.setAddress(address.trim().isEmpty() ? contact.getAddress() : address);
                contact.setGroupName(groupName.trim().isEmpty() ? contact.getGroupName() : groupName);


                updateToFile();
                break;
            }
        }

        if (!found) {
            System.out.println("Không tìm thấy liên hệ.");
        }
    }

    private void updateToFile() {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, false))) {
            for (ContactMember contact : contactList) {
                bufferedWriter.write(contact.toString());
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            System.out.println("Lỗi khi ghi file: " + e.getMessage());
        }
    }
}


