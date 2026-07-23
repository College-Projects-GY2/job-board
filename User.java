public class User {
    private String username;
    private String password;
    private String email;
    private String phone;
    private boolean isRecruiter;

    public User(String username, String password, String email, String phone, boolean isRecruiter) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.isRecruiter = isRecruiter;
    }

    public boolean checkPassword(String inputPassword) {
        return this.password.equals(inputPassword);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isRecruiter() {
        return isRecruiter;
    }

    public void setRecruiter(boolean recruiter) {
        this.isRecruiter = recruiter;
    }

    @Override
    public String toString() {
        String role = this.isRecruiter ? "מגייס" : "מחפש עבודה";
        return "שם משתמש: " + username +
                ", דוא\"ל: " + email +
                ", טלפון: " + phone +
                ", סוג: " + role;
    }

}

    @Override
    public String toString() {
        String accountType = isRecruiter ? "מגייס" : "מחפש עבודה";
        return "User: [" + username + "] | Email: " + email + " | Phone: " + phone + " | Type: " + accountType;
    }

    // פעולה המאמתת סיסמה מבלי לחשוף אותה החוצה
    public boolean checkPassword(String inputPassword) {
        return this.password.equals(inputPassword);
    }

    // אם עדיין אין לך, תצטרך גם Getter לשם המשתמש כדי למצוא אותו במערך
    public String getUsername() {
        return this.username;
    }
}
