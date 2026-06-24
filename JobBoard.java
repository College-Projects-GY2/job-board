public class JobBoard {
    private User[] users;
    private Company[] companies;
    private Job[] jobs;

    private int userCount;
    private int companyCount;
    private int jobCount;

    public JobBoard() {
        this.users = new User[100];
        this.companies = new Company[50];
        this.jobs = new Job[200];


        this.userCount = 0;
        this.companyCount = 0;
        this.jobCount = 0;

        loadMockData();
    }

    private void loadMockData() {
        User defaultRecruiter = new User("admin_hr", "Pass123!", "hr@jobboard.com", "0501234567", true);
        users[userCount++] = defaultRecruiter;

        Company google = new Company("Google", "High-Tech", 150000);
        Company intel = new Company("Intel", "Hardware", 120000);
        Company amazon = new Company("Amazon", "E-commerce", 1500000);

        companies[companyCount++] = google;
        companies[companyCount++] = intel;
        companies[companyCount++] = amazon;

        // 3. יצירת 10 משרות התחלתיות והכנסתן למערך (שים לב לקידום המונה jobCount++)
        jobs[jobCount++] = new Job("Java Developer", 25000, true, google, defaultRecruiter);
        jobs[jobCount++] = new Job("Python Intern", 12000, false, google, defaultRecruiter);
        jobs[jobCount++] = new Job("Hardware Engineer", 28000, true, intel, defaultRecruiter);
        jobs[jobCount++] = new Job("QA Automation", 18000, true, intel, defaultRecruiter);
        jobs[jobCount++] = new Job("Cloud Architect", 35000, true, amazon, defaultRecruiter);
        jobs[jobCount++] = new Job("Data Analyst", 22000, true, amazon, defaultRecruiter);
        jobs[jobCount++] = new Job("Frontend Developer", 20000, false, google, defaultRecruiter);
        jobs[jobCount++] = new Job("DevOps Engineer", 30000, true, amazon, defaultRecruiter);
        jobs[jobCount++] = new Job("C++ Programmer", 26000, true, intel, defaultRecruiter);
        jobs[jobCount++] = new Job("Product Manager", 32000, true, google, defaultRecruiter);
    }

    // מתודה לרישום משתמש
    public boolean createUser(User newUser) {
        // TODO: לממש לוגיקת רישום (בדיקת כפילויות, הוספה למערך וקידום מונה)
        return false;
    }

    // מתודה להתחברות
    public User login(String username, String password) {
        // TODO: לממש לוגיקת התחברות (חיפוש במערך ואימות סיסמה)
        return null;
    }

    // מתודה להוספת משרה
    public boolean addNewJob(Job job) {
        // TODO: לממש לוגיקת הוספת משרה למערך וקידום המונה
        return false;
    }

    // מתודה להדפסת כל המשרות
    public void printAllJobs() {
        // TODO: לממש לולאה שעוברת על מערך המשרות ומדפיסה אותן
    }
}