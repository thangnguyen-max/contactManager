public class ContactMember {
    private String GroupName;
    private String name;
    private String phone;
    private String email;
    private String gender;
    private String address;

    public String getGroupName() {
        return GroupName;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getGender() {
        return gender;
    }

    public String getAddress() {
        return address;
    }

    public void setGroupName(String groupName) {
        this.GroupName = groupName;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public ContactMember(String groupName, String name, String phone, String email, String gender, String address) {
        this.GroupName = groupName;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.gender = gender;
        this.address = address;
    }

    @Override
    public String toString() {
        return "nhóm: '" + GroupName + "', " + "tên: '" + name +
                "', Số điện thoại: '" + phone +
                "', email: '" + email +
                "', giới tính: '" + gender +
                "', địa chỉ: '" + address +"'";
    }
}
