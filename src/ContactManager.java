import java.io.*;
import java.util.*;


public class ContactManager implements IContact {
    public ContactManager() {
    }

    LinkedHashMap<String, ArrayList<ContactMember>> nameLists = new LinkedHashMap<>(5);
    ArrayList<ContactMember> ListFamilyContact = new ArrayList<>();
    ArrayList<ContactMember> ListWorkContact = new ArrayList<>();
    ArrayList<ContactMember> ListFriendContact = new ArrayList<>();
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
    public void addContact() {
        nameLists.put("Gia đình", ListFamilyContact);
        nameLists.put("Bạn bè", ListFriendContact);
        nameLists.put("Công việc", ListWorkContact);
        Set<String> keys = nameLists.keySet();
        String[] keyArray = keys.toArray(new String[5]);



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

        Regex regex = new Regex(phone, email, gender);
        if(!regex.Test(phone,email,gender)){
            System.out.println("Vui lòng nhập lại thông tin");
            System.out.println("Thông tin phải thỏa mãn:");
            System.out.println("số điện thoại đủ 10 số, email đúng định dạng 'tennguoidung@tenmien' , giới tính chỉ có thể là 'nam' hoặc 'nữ'");
            return;
        }


        int choice;
        System.out.println("Chọn nhóm danh bạ:");
        System.out.println("0. Tạo nhóm mới");
        int count = 0;
        for (int i = 0; i < keyArray.length; i++) {
            if (keyArray[i] != null) {
                System.out.println(i + 1 + ". " + keyArray[i]);
                count++;
            }
        }
        choice = scanner.nextInt();
        scanner.nextLine();
        if (Add(choice, count, keyArray, name, phone, email, gender, address)) return;
        System.out.println("Đã thêm một liên hệ mới vào danh bạ");
    }

    @Override
    public void deleteContact() {
        System.out.println("Nhập đúng tên liên hệ bạn muốn xóa: ");
        String name = scanner.nextLine();
        ContactMember contact = Search(name);
        if(contact == null){
            System.out.println("Không tìm thấy liên hệ");
            return;
        }
        System.out.println(contact);
        System.out.println("Bạn có chắc muốn xóa " + name + " không?");
        System.out.println("1. Xóa");
        System.out.println("2. Thoát");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1: {
                for (ArrayList<ContactMember> list : nameLists.values()) {
                    list.removeIf(member -> member.getName().equalsIgnoreCase(name));
                }

                // Cập nhật lại file sau khi xóa
                UpdateContactToFile();

                System.out.println("Xóa liên hệ thành công.");

                System.out.println("Xóa liên hệ thành công");
                break;
            }
            case 2:
                break;
            default:
                System.out.println("Lựa chọn không hợp lệ!");
        }
    }

    @Override
    public void searchContact() {
        System.out.println("Nhập tên liên hệ bạn muốn tìm kiếm: ");
        String name = scanner.nextLine();
        ContactMember contact = Search(name);
        if(contact == null){
            System.out.println("Không tìm thấy liên hệ");
            return;
        }
        System.out.println(contact);
    }

    @Override
    public void displayAllContacts() {
        for (Map.Entry<String, ArrayList<ContactMember>> entry : nameLists.entrySet()) {
            for (ContactMember member : entry.getValue()) {
                System.out.println(member);
            }
        }
    }

    @Override
    public void updateContact() {
        Set<String> keys = nameLists.keySet();
        String[] keyArray = keys.toArray(new String[5]);
        System.out.println("Nhập tên liên hệ bạn muốn cập nhật:");
        String nameSearch = scanner.nextLine();
        ContactMember oldContact = Search(nameSearch);

        if (oldContact == null) {
            System.out.println("Không tìm thấy liên hệ.");
            return;
        }

        System.out.println("Thông tin hiện tại:");
        System.out.println(oldContact);

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


        int choice;
        System.out.println("Chọn nhóm danh bạ:");
        System.out.println("0. Tạo nhóm mới");
        int count = 0;
        for (int i = 0; i < keyArray.length; i++) {
            if (keyArray[i] != null) {
                System.out.println(i + 1 + ". " + keyArray[i]);
                count++;
            }
        }
        choice = scanner.nextInt();
        scanner.nextLine();
        oldContact.setGroupName(keyArray[choice -1]);
        oldContact.setName(name.trim().isEmpty() ? oldContact.getName() : name);
        oldContact.setPhone(phone.trim().isEmpty() ? oldContact.getPhone() : phone);
        oldContact.setEmail(email.trim().isEmpty() ? oldContact.getEmail() : email);
        oldContact.setGender(gender.trim().isEmpty() ? oldContact.getGender() : gender);
        oldContact.setAddress(address.trim().isEmpty() ? oldContact.getAddress() : address);

        for (ArrayList<ContactMember> list : nameLists.values()) {
            list.removeIf(contact -> contact.getName().equalsIgnoreCase(nameSearch));
        }

        nameLists.get(oldContact.getGroupName()).add(oldContact);
        UpdateContactToFile();
        System.out.println("Cập nhật liên hệ thành công.");
    }

    private boolean Add(int choice, int count, String[] keyArray, String name, String phone, String email, String gender, String address) {
        if (choice == 0) {
            if (count >= keyArray.length) {
                System.out.println("Không thể thêm nhóm mới");
                return true;
            } else {
                System.out.println("Nhập tên nhóm:");
                String groupName = scanner.nextLine();
                keyArray[count] = groupName;
                ArrayList<ContactMember> groupNameInput = new ArrayList<>();
                nameLists.put(groupName, groupNameInput);
                ContactMember contact = new ContactMember(groupName, name, phone, email, gender, address);
                groupNameInput.add(contact);
                WriteToFile(contact.toString());
                System.out.println("Đã thêm thành công nhóm mới");
            }

        } else if (choice == 1) {

            FamilyContact contact = new FamilyContact("Gia đình", name, phone, email, gender, address);

            nameLists.get("Gia đình").add(contact);
            WriteToFile(contact.toString());

        } else if (choice == 2) {
            FriendContact contact = new FriendContact("Bạn bè", name, phone, email, gender, address);
            nameLists.get("Bạn bè").add(contact);
            WriteToFile(contact.toString());

        } else if (choice == 3) {
            WorkContact contact = new WorkContact("Công việc", name, phone, email, gender, address);
            nameLists.get("Công việc").add(contact);
            WriteToFile(contact.toString());

        } else if (choice == 4 && count >= 4) {
            ContactMember contact = new ContactMember(keyArray[3], name, phone, email, gender, address);
            nameLists.get(keyArray[3]).add(contact);
            WriteToFile(contact.toString());
        } else if (choice == 5 && count == 5) {

            ContactMember contact = new ContactMember(keyArray[4], name, phone, email, gender, address);
            nameLists.get(keyArray[4]).add(contact);
            WriteToFile(contact.toString());

        } else {
            System.out.println("Lựa chọn không hợp lệ!");
            return true;
        }
        return false;
    }
    private void UpdateContactToFile() {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, false))) {
            for (ArrayList<ContactMember> list : nameLists.values()) {
                for (ContactMember contact : list) {
                    bufferedWriter.write(contact.toString());
                    bufferedWriter.newLine();
                }
            }
        } catch (IOException e) {
            System.out.println("Lỗi khi ghi file: " + e.getMessage());
        }
    }
    public ContactMember Search(String name) {
        if(name == null) {
            return null;
        }
        for (Map.Entry<String, ArrayList<ContactMember>> entry : nameLists.entrySet()) {
            for (ContactMember member : entry.getValue()) {
                if (member.getName().toLowerCase().replace("\\s+", "").contains(name.toLowerCase().replace("\\s+", ""))) {
                    return member;
                }
            }
        }
        return null;
    }
}
