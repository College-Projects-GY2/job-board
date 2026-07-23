public class Job {
    private String title;
    private int salary;
    private boolean isFullTime;
    private boolean isOpen;
    private Company company; // הרכבה: אובייקט שלם של חברה
    private User publisher;  // הרכבה: אובייקט שלם של משתמש

    public Job(String title, int salary, boolean isFullTime, Company company, User publisher) {
        this.title = title;
        this.salary = salary;
        this.isFullTime = isFullTime;
        this.isOpen = true; // משרה חדשה נפתחת תמיד כפעילה
        this.company = company;
        this.publisher = publisher;
    }

    @Override
    public String toString() {
        String jobType = this.isFullTime ? "Full-time" : "Part-time";
        String jobStatus = this.isOpen ? "פתוחה" : "סגורה";

        return "=== פרטי משרה ===\n" +
                "תפקיד: " + title + "\n" +
                "שכר חודשי: ₪" + salary + "\n" +
                "היקף משרה: " + jobType + "\n" +
                "סטטוס: " + jobStatus + "\n" +
                "חברה: [" + company.toString() + "]\n" +
                "פורסם על ידי: [" + publisher.getUsername() + "]\n" +
                "=================";
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public boolean isFullTime() {
        return isFullTime;
    }

    public void setFullTime(boolean fullTime) {
        this.isFullTime = fullTime;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        this.isOpen = open;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public User getPublisher() {
        return publisher;
    }

    public void setPublisher(User publisher) {
        this.publisher = publisher;
    }
}