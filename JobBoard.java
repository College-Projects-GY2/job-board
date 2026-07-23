import java.util.Scanner;

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
        User defaultRecruiter = new User("admin_hr", "password123", "hr@jobboard.com", "050-1234567", true);
        users[userCount++] = defaultRecruiter;

        Company google = new Company("Google", "High-Tech", 150000);
        Company intel = new Company("Intel", "Hardware", 120000);
        Company amazon = new Company("Amazon", "E-commerce", 1500000);

        companies[companyCount++] = google;
        companies[companyCount++] = intel;
        companies[companyCount++] = amazon;

        // 3. יצירת 10 משרות התחלתיות והכנסתן למערך (תוך קידום המונה)
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

    // --- מתודות עזר לבדיקת תקינות (Validation Helpers) ---

    private boolean isUsernameUnique(String username) {
        if (username == null || username.trim().isEmpty()) {
            return false;
        }
        for (int i = 0; i < userCount; i++) {
            if (users[i].getUsername().equals(username)) {
                return false; // השם כבר קיים במערכת
            }
        }
        return true;
    }

    private boolean isValidPassword(String password) {
        if (password == null || password.isEmpty()) {
            return false;
        }
        boolean hasDigit = false;
        boolean hasSpecial = false;

        for (char c : password.toCharArray()) {
            if (Character.isDigit(c)) {
                hasDigit = true;
            }
            if (c == '%' || c == '$' || c == '_') {
                hasSpecial = true;
            }
        }
        return hasDigit && hasSpecial;
    }

    private boolean isValidEmail(String email) {
        if (email == null || email.isEmpty()) {
            return false;
        }
        int atIndex = email.indexOf('@');
        if (atIndex == -1) {
            return false;
        }
        int dotIndex = email.indexOf('.', atIndex); // מחפש נקודה שמופיעה רק אחרי ה-@
        return dotIndex > atIndex;
    }

    private boolean isValidPhone(String phone) {
        if (phone == null) {
            return false;
        }
        return phone.matches("05\\d{8}");
    }

    // --- רישום משתמש חדש במערכת אינטראקטיבי (REQ-001) ---
    // --- רישום משתמש חדש במערכת אינטראקטיבי (REQ-001) ---
    public void createUser() {
        // בדיקת מקום במערך
        if (userCount >= users.length) {
            System.out.println("שגיאה: המערכת מלאה, לא ניתן לרשום משתמשים חדשים.");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        String username, password, email, phone;
        boolean isRecruiter = false;

        System.out.println("\n=== הרשמה למערכת ===");

        // קליטת שם משתמש (Loop)
        while (true) {
            System.out.print("הכנס שם משתמש: ");
            username = scanner.nextLine().trim();
            if (isUsernameUnique(username)) {
                break;
            }
            System.out.println("שגיאה: שם המשתמש ריק או שכבר תפוס, נסה שוב.");
        }

        // קליטת סיסמה (Loop)
        while (true) {
            System.out.print("הכנס סיסמה (חובה ספרה ותו מיוחד מתוך % $ _): ");
            password = scanner.nextLine().trim();
            if (isValidPassword(password)) {
                break;
            }
            System.out.println("שגיאה: הסיסמה חלשה מדי. חובה לכלול לפחות ספרה אחת ותו מיוחד (% $ _).");
        }

        // קליטת אימייל (Loop)
        while (true) {
            System.out.print("הכנס כתובת דוא\"ל: ");
            email = scanner.nextLine().trim();
            if (isValidEmail(email)) {
                break;
            }
            System.out.println("שגיאה: כתובת הדוא\"ל אינה תקינה, נסה שוב.");
        }

        // קליטת טלפון (Loop)
        while (true) {
            System.out.print("הכנס מספר טלפון (10 ספרות, מתחיל ב-05): ");
            phone = scanner.nextLine().trim();
            if (isValidPhone(phone)) {
                break;
            }
            System.out.println("שגיאה: מספר הטלפון אינו בפורמט התקין, נסה שוב.");
        }

        // קביעת סוג חשבון
        while (true) {
            System.out.print("הקש 1 אם אתה מגייס, 2 אם אתה מחפש עבודה: ");
            String choice = scanner.nextLine().trim();
            if (choice.equals("1")) {
                isRecruiter = true;
                break;
            } else if (choice.equals("2")) {
                isRecruiter = false;
                break;
            }
            System.out.println("שגיאה: אנא הקש 1 או 2.");
        }

        // יצירת ושמירת האובייקט
        User newUser = new User(username, password, email, phone, isRecruiter);
        users[userCount] = newUser;

        // קידום המונה
        userCount++;

        // סיום
        System.out.println("\nהחשבון נוצר בהצלחה!");
    }

    public void printAllUsers() {
        System.out.println("\n=== רשימת המשתמשים במערכת ===");

        if (userCount == 0) {
            System.out.println("אין כרגע משתמשים רשומים במערכת.");
            return;
        }

        // עוברים בלולאה רק עד userCount, כדי לא לנסות להדפיס תאים ריקים (null)
        for (int i = 0; i < userCount; i++) {
            System.out.println((i + 1) + ". " + users[i].toString());
        }
        System.out.println("===============================\n");
    }

    public User login() {
        Scanner scanner = new Scanner(System.in);

        // --- משימה קודמת: קליטת הנתונים ---
        System.out.println("--- התחברות למערכת ---");

        System.out.print("הזן שם משתמש: ");
        String inputUsername = scanner.nextLine().trim();

        System.out.print("הזן סיסמה: ");
        String inputPassword = scanner.nextLine().trim();

        // --- משימה נוכחית: חיפוש ואימות במערך ---
        for (int i = 0; i < userCount; i++) {
            User currentUser = users[i];

            // בדיקת התאמה גם לשם המשתמש וגם לסיסמה
            if (currentUser.getUsername().equals(inputUsername) && currentUser.checkPassword(inputPassword)) {
                System.out.println("התחברת בהצלחה!");
                return currentUser; // מחזיר את המשתמש ויוצא מהפעולה
            }
        }

        // אם הלולאה הסתיימה ולא הוחזר משתמש - סימן שאין התאמה
        System.out.println("שם משתמש או סיסמה שגויים");
        return null;
    }

    // הוספת משרה חדשה למערכת
    public boolean addNewJob(Job job) {
        // TODO: לממש לוגיקת הוספת משרה וקידום המונה
        return false;
    }

    // הדפסת כל המשרות הקיימות במערכת
    public void printAllJobs() {
        // TODO: לממש לולאה הרצה על מערך המשרות ומדפיסה אותן
    }

    private int countJobsByUser(User user) {
        int count = 0;

        // מעבר רק עד jobCount כדי למנוע קריסת NullPointerException בתאים ריקים
        for (int i = 0; i < jobCount; i++) {
            // השוואה בטוחה בין המחרוזות של שמות המשתמשים
            if (jobs[i].getPublisher().getUsername().equals(user.getUsername())) {
                count++;
            }
        }

        return count;
    }

    // --- Getters זמניים למקרה שנצטרך לבדוק את הגדלים של המערכים מבחוץ ---
    public int getUserCount() { return userCount; }
    public int getCompanyCount() { return companyCount; }
    public int getJobCount() { return jobCount; }
}