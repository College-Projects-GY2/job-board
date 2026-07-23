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
=======
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

    public void start() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        // לולאה חיצונית (תפריט ראשי)
        while (running) {
            System.out.println("\n=== לוח דרושים - תפריט ראשי ===");
            System.out.println("1. הרשמה למערכת (Register)");
            System.out.println("2. התחברות (Login)");
            System.out.println("3. יציאה מהתוכנית (Exit)");
            System.out.println("בחר אפשרות:");

            if (!scanner.hasNextInt()) {
                System.out.println("שגיאה: יש להזין מספר שלם בלבד.");
                scanner.next(); // ניקוי הקלט השגוי
                continue;
            }

            int choice = scanner.nextInt();
            scanner.nextLine(); // ניקוי ה-Buffer

            switch (choice) {
                case 1:
                    // הרשמה למערכת
                    createUser();
                    break;

                case 2:
                    // התחברות למערכת
                    User loggedInUser = login();

                    // אם ההתחברות הצליחה, נכנסים ללולאה הפנימית
                    if (loggedInUser != null) {
                        System.out.println("התחברת בהצלחה! שלום, " + loggedInUser.getUsername());

                        // לולאה פנימית למשתמש מחובר
                        while (loggedInUser != null) {
                            System.out.println("\n=== תפריט משתמש מחובר ===");
                            System.out.println("1. צפייה בכל המשרות");
                            System.out.println("2. חיפוש וסינון משרות");

                            // הצגה דינמית של אפשרויות למגייסים בלבד
                            if (loggedInUser.isRecruiter()) {
                                System.out.println("3. פרסום משרה חדשה");
                                System.out.println("4. צפייה במשרות שלי");
                                System.out.println("5. מחיקת משרה");
                            }

                            System.out.println("6. התנתקות (Logout)");
                            System.out.println("בחר אפשרות:");

                            if (!scanner.hasNextInt()) {
                                System.out.println("שגיאה: יש להזין מספר שלם בלבד.");
                                scanner.next();
                                continue;
                            }

                            int innerChoice = scanner.nextInt();
                            scanner.nextLine(); // ניקוי ה-Buffer

                            switch (innerChoice) {
                                case 1:
                                    printAllJobs();
                                    break;
                                case 2:
                                    searchJobs();
                                    break;
                                case 3:
                                    if (loggedInUser.isRecruiter()) {
                                        addNewJob(loggedInUser);
                                    } else {
                                        System.out.println("שגיאה: פעולה זו מותרת למגייסים בלבד.");
                                    }
                                    break;
                                case 4:
                                    if (loggedInUser.isRecruiter()) {
                                        printUserJobs(loggedInUser);
                                    } else {
                                        System.out.println("שגיאה: פעולה זו מותרת למגייסים בלבד.");
                                    }
                                    break;
                                case 5:
                                    if (loggedInUser.isRecruiter()) {
                                        removeJob(loggedInUser);
                                    } else {
                                        System.out.println("שגיאה: פעולה זו מותרת למגייסים בלבד.");
                                    }
                                    break;
                                case 6:
                                    System.out.println("התנתקת בהצלחה מהחשבון.");
                                    loggedInUser = null; // שבירת הלולאה הפנימית וחזרה לתפריט הראשי
                                    break;
                                default:
                                    System.out.println("בחירה שגויה, אנא נסה שוב.");
                                    break;
                            }
                        }
                    }
                    break;

                case 3:
                    System.out.println("תודה שהשתמשת בלוח הדרושים, להתראות!");
                    running = false;
                    break;

                default:
                    System.out.println("בחירה שגויה, אנא בחר מספר מהתפריט (1-3).");
                    break;
            }
        }
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

    public void addNewJob(User currentUser) {
        // 1. בדיקת הרשאות (Validation) - חסימת מחפשי עבודה
        if (!currentUser.isRecruiter()) {
            System.out.println("רק מגייסים יכולים לפרסם משרות.");
            return;
        }

        // 2. בדיקת מקום במערך
        if (jobCount >= jobs.length) {
            System.out.println("לוח המשרות מלא. לא ניתן לפרסם משרות חדשות כרגע.");
            return;
        }

        // 3. בדיקת מגבלת משרות למגייס (Limit Check)
        int MAX_JOBS_PER_RECRUITER = 10; // ניתן לשנות לפי הצורך שמוגדר במערכת שלך
        if (countJobsByUser(currentUser) >= MAX_JOBS_PER_RECRUITER) {
            System.out.println("חרגת ממכסת המשרות המותרת (מקסימום " + MAX_JOBS_PER_RECRUITER + " משרות).");
            return;
        }

        Scanner scanner = new Scanner(System.in);

        // 4. בחירת חברה (Company Selection) מהלולאה שיצרנו במשימה 2
        Company selectedCompany = null;
        while (selectedCompany == null) {
            System.out.println("הזן את שם החברה עבורה תפורסם המשרה:");
            String inputName = scanner.nextLine();

            selectedCompany = findCompanyByName(inputName);

            if (selectedCompany == null) {
                System.out.println("החברה לא קיימת במערכת, נסה שוב.");
            }
        }

        // 5. קליטת נתונים (Data Collection)
        System.out.println("הזן את כותרת המשרה:");
        String title = scanner.nextLine();

        System.out.println("הזן שכר חודשי:");
        int salary = scanner.nextInt();
        scanner.nextLine(); // ניקוי ה-Buffer אחרי קליטת int

        System.out.println("האם מדובר במשרה מלאה? (הקש true למשרה מלאה או false למשרה חלקית):");
        boolean isFullTime = scanner.nextBoolean();
        scanner.nextLine(); // ניקוי ה-Buffer אחרי קליטת boolean

        // 6. יצירת ושמירת האובייקט (Instantiation & Insertion)
        // שים לב: ודא שסדר הפרמטרים כאן תואם במדויק לבנאי (Constructor) במחלקת Job שלך
        boolean isOpen = true;
        Job newJob = new Job(title, salary, isFullTime, isOpen, selectedCompany, currentUser);

        jobs[jobCount] = newJob; // שמירה במיקום המדויק ללא דריסת נתונים
        jobCount++;              // קידום המונה

        // 7. סיום
        System.out.println("המשרה פורסמה בהצלחה!");
    }

    public void removeJob(User currentUser) {
        // 1. בדיקה מקדימה: האם למשתמש יש בכלל משרות באוויר?
        if (countJobsByUser(currentUser) == 0) {
            System.out.println("אין לך משרות באוויר שניתן למחוק.");
            return;
        }

        // 2. הצגת המשרות הפעילות של המשתמש בלבד לפני קליטת הקלט
        printUserJobs(currentUser);

        Scanner scanner = new Scanner(System.in);

        System.out.println("\n--- מחיקת משרה ---");
        System.out.println("הזן את כותרת המשרה שברצונך למחוק מתוך הרשימה המוצגת:");
        String targetTitle = scanner.nextLine().trim();

        int indexToRemove = -1;

        // 3. חיפוש המשרה במערך הכללי
        for (int i = 0; i < jobCount; i++) {
            if (jobs[i].getTitle().equalsIgnoreCase(targetTitle)) {
                indexToRemove = i;
                break;
            }
        }

        // בדיקה האם המשרה קיימת
        if (indexToRemove == -1) {
            System.out.println("שגיאה: משרה בשם זה לא נמצאה במערכת.");
            return;
        }

        // 4. בדיקת בעלות קריטית (אבטחה) - האם המשתמש המחובר הוא אכן המפרסם?
        if (!jobs[indexToRemove].getPublisher().getUsername().equals(currentUser.getUsername())) {
            System.out.println("שגיאה: אינך מורשה למחוק משרה שפורסמה על ידי משתמש אחר.");
            return;
        }

        // 5. ביצוע הזזת המערך (Array Shift) שמאלה
        for (int i = indexToRemove; i < jobCount - 1; i++) {
            jobs[i] = jobs[i + 1];
        }

        // 6. ניקוי התא האחרון שהתפנה והפחתת המונה
        jobs[jobCount - 1] = null;
        jobCount--;

        // 7. סיום בהצלחה
        System.out.println("המשרה נמחקה מהמערכת.");
    }


    public void promptSearchParameters() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("--- חיפוש משרות מתקדם ---");
        System.out.println("(הקש -999 בכל שלב כדי לדלג על סינון מסוים)");

        // 1. קליטת תחום
        System.out.println("הזן תחום לחיפוש (או -999 לדלג):");
        String searchField = scanner.nextLine().trim();

        // 2. קליטת שכר מינימום
        System.out.println("הזן שכר מינימלי לחיפוש (או -999 לדלג):");
        int minSalary = scanner.nextInt();

        // 3. קליטת שכר מקסימום
        System.out.println("הזן שכר מקסימלי לחיפוש (או -999 לדלג):");
        int maxSalary = scanner.nextInt();
        scanner.nextLine(); // ניקוי ה-Buffer אחרי ה-int

        // 4. קליטת סטטוס משרה
        System.out.println("הקש true למשרות פתוחות בלבד, false לסגורות, או -999 לדלג:");
        String statusInput = scanner.nextLine().trim();

        System.out.println("הנתונים נקלטו בהצלחה ומוכנים ללוגיקת הסינון.");
    }

    public Job[] searchJobs() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("--- חיפוש משרות מתקדם ---");
        System.out.println("(הקש -999 בכל שלב כדי לדלג על סינון מסוים)");

        // שלב א': קליטת פרמטרים (משימה 1)
        System.out.println("הזן תחום לחיפוש (או -999 לדלג):");
        String searchField = scanner.nextLine().trim();

        System.out.println("הזן שכר מינימלי לחיפוש (או -999 לדלג):");
        int minSalary = scanner.nextInt();

        System.out.println("הזן שכר מקסימלי לחיפוש (או -999 לדלג):");
        int maxSalary = scanner.nextInt();
        scanner.nextLine(); // ניקוי Buffer

        System.out.println("הקש true למשרות פתוחות בלבד, false לסגורות, או -999 לדלג:");
        String statusInput = scanner.nextLine().trim();

        // שלב ב': הכנת מערך תוצאות ומונה (משימה 2)
        Job[] results = new Job[jobCount];
        int resCount = 0;

        // שלב ג': לולאת חיפוש וסינון בשיטת AND
        for (int i = 0; i < jobCount; i++) {
            Job currentJob = jobs[i];
            boolean matches = true; // נניח כברירת מחדל שהמשרה מתאימה

            // 1. סינון לפי תחום (אם לא הוזן -999)
            if (!searchField.equals("-999")) {
                // הוסף כאן את התנאי לבדיקת התחום במידת הצורך מול השדה במחלקת Job
            }

            // 2. סינון לפי שכר מינימלי
            if (minSalary != -999 && currentJob.getSalary() < minSalary) {
                matches = false;
            }

            // 3. סינון לפי שכר מקסימלי
            if (maxSalary != -999 && currentJob.getSalary() > maxSalary) {
                matches = false;
            }

            // 4. סינון לפי סטטוס פתוחה/סגורה
            if (!statusInput.equals("-999")) {
                boolean targetStatus = Boolean.parseBoolean(statusInput);
                if (currentJob.isOpen() != targetStatus) {
                    matches = false;
                }
            }

            // אם עברה את כל התנאים בהצלחה -> הוסף למערך התוצאות
            if (matches) {
                results[resCount] = currentJob;
                resCount++;
            }
        }

        // שלב ד': סיום ותצוגה (משימה 3)
        if (resCount == 0) {
            System.out.println("לא נמצאו משרות התואמות את פרמטרי החיפוש שהזנת.");
        } else {
            System.out.println("\nנמצאו " + resCount + " משרות התואמות לחיפוש שלך:");
            for (int i = 0; i < resCount; i++) {
                System.out.println((i + 1) + ". " + results[i]);
                System.out.println("-------------------------");
            }
        }

        return results;
    }

    public void printUserJobs(User currentUser) {
        // 1. הכנה ומעקב האם נמצאו משרות של המשתמש
        boolean hasJobs = false;

        // 2. כותרת תצוגה
        System.out.println("\n--- המשרות שפרסמת ---");

        // 3. לולאת חיפוש שרצה מ-0 ועד jobCount
        for (int i = 0; i < jobCount; i++) {
            // 4. בדיקת שיוך - האם המפרסם של המשרה הנוכחית הוא המשתמש המחובר
            if (jobs[i].getPublisher().getUsername().equals(currentUser.getUsername())) {
                System.out.println((i + 1) + ". " + jobs[i]);
                System.out.println("-------------------------");
                hasJobs = true; // סימון שמצאנו לפחות משרה אחת
            }
        }

        // 5. טיפול במצב שבו אין משרות למשתמש
        if (!hasJobs) {
            System.out.println("טרם פרסמת משרות במערכת.");
        }
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

    private Company findCompanyByName(String companyName) {
        // עוברים על המערך רק עד לכמות החברות בפועל (companyCount)
        for (int i = 0; i < companyCount; i++) {
            // התעלמות מאותיות גדולות/קטנות בעזרת equalsIgnoreCase
            if (companies[i].getName().equalsIgnoreCase(companyName)) {
                return companies[i];
            }
        }
        // אם הלולאה הסתיימה ולא מצאנו כלום
        return null;
    }
}