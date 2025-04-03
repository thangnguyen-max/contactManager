public class Regex {
    private String phone;
    private String email;
    private String gender;

    public Regex(String phone, String email, String gender) {
        this.phone = phone;
        this.email = email;
        this.gender = gender;
    }


    public boolean Test (String phone,String email,String gender) {
        return phone.matches("\\d{10}") && email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$") &&gender.matches("(?i)nam|nữ");
    }
}

