package Library;

public class member {
    private int memberId;
    private String name;
    private String email;
    private String phone;

    public member(String name, String email, String phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public member(int memberId, String name, String email, String phone) {
        this.memberId = memberId;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public int getMemberId() {
        return memberId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Member ID: " + memberId +
                ", Name: '" + name + '\'' +
                ", Email: '" + email + '\''+
                 ", Phone: '" + phone + '\'';
    }
}
