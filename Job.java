public class Job {
    private String title;
    private int salary;
    private boolean isFullTime;
    private boolean isOpen;
    private Company company;
    private User publisher;

    // --- שדות חדשים למערכת מועמדויות (משימה 1) ---
    private User[] applicants;
    private int applicantCount;

    // הבנאי של המשרה (מותאם ל-5 פרמטרים כפי שסידרנו)
    public Job(String title, int salary, boolean isFullTime, Company company, User publisher) {
        this.title = title;
        this.salary = salary;
        this.isFullTime = isFullTime;
        this.isOpen = true; // משרה נפתחת כברירת מחדל
        this.company = company;
        this.publisher = publisher;

        // אתחול מערך המועמדים (נניח עד 50 מועמדים למשרה)
        this.applicants = new User[50];
        this.applicantCount = 0;
    }

    // --- מתודות חדשות למערכת מועמדויות (משימה 1) ---

    public boolean addApplicant(User user) {
        // 1. בדיקה אם המשתמש כבר קיים במערך (מניעת הגשה כפולה)
        for (int i = 0; i < applicantCount; i++) {
            if (applicants[i].getUsername().equals(user.getUsername())) {
                System.out.println("שגיאה: כבר הגשת מועמדות למשרה זו בעבר.");
                return false;
            }
        }

        // 2. בדיקה אם יש מקום במערך
        if (applicantCount >= applicants.length) {
            System.out.println("שגיאה: המשרה הגיעה למכסת המועמדים המקסימלית.");
            return false;
        }

        // 3. הוספת המועמד וקידום המונה
        applicants[applicantCount] = user;
        applicantCount++;
        return true;
    }

    public User[] getApplicants() {
        return applicants;
    }

    public int getApplicantCount() {
        return applicantCount;
    }

    // --- גטרים רגילים (Getters) ---

    public String getTitle() {
        return title;
    }

    public int getSalary() {
        return salary;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public User getPublisher() {
        return publisher;
    }

    public Company getCompany() {
        return company;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    @Override
    public String toString() {
        String status = isOpen ? "פתוחה" : "סגורה";
        String type = isFullTime ? "מלאה" : "חלקית";
        return "משרה: " + title + " | חברה: " + company.getName() + " | שכר: " + salary + " | סוג: " + type + " | סטטוס: " + status;
    }
}