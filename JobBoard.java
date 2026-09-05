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
                return false;
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
        int dotIndex = email.indexOf('.', atIndex);
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

        while (running) {
            System.out.println("\n=== לוח דרושים - תפריט ראשי ===");
            System.out.println("1. הרשמה למערכת (Register)");
            System.out.println("2. התחברות (Login)");
            System.out.println("3. יציאה מהתוכנית (Exit)");
            System.out.println("בחר אפשרות:");

            if (!scanner.hasNextInt()) {
                System.out.println("שגיאה: יש להזין מספר שלם בלבד.");
                scanner.next();
                continue;
            }

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    createUser();
                    break;

                case 2:
                    User loggedInUser = login();

                    if (loggedInUser != null) {
                        System.out.println("התחברת בהצלחה! שלום, " + loggedInUser.getUsername());

                        while (loggedInUser != null) {
                            System.out.println("\n=== תפריט משתמש מחובר ===");
                            System.out.println("1. צפייה בכל המשרות");
                            System.out.println("2. חיפוש וסינון משרות");

                            if (!loggedInUser.isRecruiter()) {
                                System.out.println("3. הגשת מועמדות למשרה");
                            } else {
                                System.out.println("3. פרסום משרה חדשה");
                                System.out.println("4. צפייה במשרות שלי");
                                System.out.println("5. מחיקת משרה");
                                System.out.println("6. צפייה במועמדים למשרות שלי");
                            }

                            System.out.println("7. התנתקות (Logout)");
                            System.out.println("בחר אפשרות:");

                            if (!scanner.hasNextInt()) {
                                System.out.println("שגיאה: יש להזין מספר שלם בלבד.");
                                scanner.next();
                                continue;
                            }

                            int innerChoice = scanner.nextInt();
                            scanner.nextLine();

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
                                        applyForJob(loggedInUser);
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
                                    if (loggedInUser.isRecruiter()) {
                                        viewMyApplicants(loggedInUser);
                                    } else {
                                        System.out.println("שגיאה: פעולה זו מותרת למגייסים בלבד.");
                                    }
                                    break;
                                case 7:
                                    System.out.println("התנתקת בהצלחה מהחשבון.");
                                    loggedInUser = null;
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

    public void createUser() {
        if (userCount >= users.length) {
            System.out.println("שגיאה: המערכת מלאה, לא ניתן לרשום משתמשים חדשים.");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        String username, password, email, phone;
        boolean isRecruiter = false;

        System.out.println("\n=== הרשמה למערכת ===");

        while (true) {
            System.out.print("הכנס שם משתמש: ");
            username = scanner.nextLine().trim();
            if (isUsernameUnique(username)) {
                break;
            }
            System.out.println("שגיאה: שם המשתמש ריק או שכבר תפוס, נסה שוב.");
        }

        while (true) {
            System.out.print("הכנס סיסמה (חובה ספרה ותו מיוחד מתוך % $ _): ");
            password = scanner.nextLine().trim();
            if (isValidPassword(password)) {
                break;
            }
            System.out.println("שגיאה: הסיסמה חלשה מדי. חובה לכלול לפחות ספרה אחת ותו מיוחד (% $ _).");
        }

        while (true) {
            System.out.print("הכנס כתובת דוא\"ל: ");
            email = scanner.nextLine().trim();
            if (isValidEmail(email)) {
                break;
            }
            System.out.println("שגיאה: כתובת הדוא\"ל אינה תקינה, נסה שוב.");
        }

        while (true) {
            System.out.print("הכנס מספר טלפון (10 ספרות, מתחיל ב-05): ");
            phone = scanner.nextLine().trim();
            if (isValidPhone(phone)) {
                break;
            }
            System.out.println("שגיאה: מספר הטלפון אינו בפורמט התקין, נסה שוב.");
        }

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

        User newUser = new User(username, password, email, phone, isRecruiter);
        users[userCount] = newUser;
        userCount++;

        System.out.println("\nהחשבון נוצר בהצלחה!");
    }

    public void printAllUsers() {
        System.out.println("\n=== רשימת המשתמשים במערכת ===");

        if (userCount == 0) {
            System.out.println("אין כרגע משתמשים רשומים במערכת.");
            return;
        }

        for (int i = 0; i < userCount; i++) {
            System.out.println((i + 1) + ". " + users[i].toString());
        }
        System.out.println("===============================\n");
    }

    public User login() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("--- התחברות למערכת ---");
        System.out.print("הזן שם משתמש: ");
        String inputUsername = scanner.nextLine().trim();
        System.out.print("הזן סיסמה: ");
        String inputPassword = scanner.nextLine().trim();

        for (int i = 0; i < userCount; i++) {
            User currentUser = users[i];
            if (currentUser.getUsername().equals(inputUsername) && currentUser.checkPassword(inputPassword)) {
                System.out.println("התחברת בהצלחה!");
                return currentUser;
            }
        }

        System.out.println("שם משתמש או סיסמה שגויים");
        return null;
    }

    public void addNewJob(User currentUser) {
        if (!currentUser.isRecruiter()) {
            System.out.println("רק מגייסים יכולים לפרסם משרות.");
            return;
        }

        if (jobCount >= jobs.length) {
            System.out.println("לוח המשרות מלא. לא ניתן לפרסם משרות חדשות כרגע.");
            return;
        }

        int MAX_JOBS_PER_RECRUITER = 10;
        if (countJobsByUser(currentUser) >= MAX_JOBS_PER_RECRUITER) {
            System.out.println("חרגת ממכסת המשרות המותרת (מקסימום " + MAX_JOBS_PER_RECRUITER + " משרות).");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        Company selectedCompany = null;

        while (selectedCompany == null) {
            System.out.println("הזן את שם החברה עבורה תפורסם המשרה:");
            String inputName = scanner.nextLine();
            selectedCompany = findCompanyByName(inputName);

            if (selectedCompany == null) {
                System.out.println("החברה לא קיימת במערכת, נסה שוב.");
            }
        }

        System.out.println("הזן את כותרת המשרה:");
        String title = scanner.nextLine();

        System.out.println("הזן שכר חודשי:");
        int salary = scanner.nextInt();
        scanner.nextLine();

        System.out.println("האם מדובר במשרה מלאה? (הקש true למשרה מלאה או false למשרה חלקית):");
        boolean isFullTime = scanner.nextBoolean();
        scanner.nextLine();

        Job newJob = new Job(title, salary, isFullTime, selectedCompany, currentUser);
        jobs[jobCount] = newJob;
        jobCount++;

        System.out.println("המשרה פורסמה בהצלחה!");
    }

    public void removeJob(User currentUser) {
        if (countJobsByUser(currentUser) == 0) {
            System.out.println("אין לך משרות באוויר שניתן למחוק.");
            return;
        }

        printUserJobs(currentUser);
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n--- מחיקת משרה ---");
        System.out.println("הזן את כותרת המשרה שברצונך למחוק מתוך הרשימה המוצגת:");
        String targetTitle = scanner.nextLine().trim();
        int indexToRemove = -1;

        for (int i = 0; i < jobCount; i++) {
            if (jobs[i].getTitle().equalsIgnoreCase(targetTitle)) {
                indexToRemove = i;
                break;
            }
        }

        if (indexToRemove == -1) {
            System.out.println("שגיאה: משרה בשם זה לא נמצאה במערכת.");
            return;
        }

        if (!jobs[indexToRemove].getPublisher().getUsername().equals(currentUser.getUsername())) {
            System.out.println("שגיאה: אינך מורשה למחוק משרה שפורסמה על ידי משתמש אחר.");
            return;
        }

        for (int i = indexToRemove; i < jobCount - 1; i++) {
            jobs[i] = jobs[i + 1];
        }

        jobs[jobCount - 1] = null;
        jobCount--;

        System.out.println("המשרה נמחקה מהמערכת.");
    }

    public void printAllJobs() {
        System.out.println("\n=== כל המשרות במערכת ===");
        if (jobCount == 0) {
            System.out.println("אין כרגע משרות זמינות במערכת.");
            return;
        }
        for (int i = 0; i < jobCount; i++) {
            System.out.println((i + 1) + ". " + jobs[i].toString());
            System.out.println("-------------------------");
        }
    }

    public Job[] searchJobs() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("--- חיפוש משרות מתקדם ---");
        System.out.println("(הקש -999 בכל שלב כדי לדלג על סינון מסוים)");

        System.out.println("הזן תחום לחיפוש (או -999 לדלג):");
        String searchField = scanner.nextLine().trim();

        System.out.println("הזן שכר מינימלי לחיפוש (או -999 לדלג):");
        int minSalary = scanner.nextInt();

        System.out.println("הזן שכר מקסימלי לחיפוש (או -999 לדלג):");
        int maxSalary = scanner.nextInt();
        scanner.nextLine();

        System.out.println("הקש true למשרות פתוחות בלבד, false לסגורות, או -999 לדלג:");
        String statusInput = scanner.nextLine().trim();

        Job[] results = new Job[jobCount];
        int resCount = 0;

        for (int i = 0; i < jobCount; i++) {
            Job currentJob = jobs[i];
            boolean matches = true;

            if (!searchField.equals("-999")) {
                // ניתן לממש סינון לפי תחום במידת הצורך
            }
            if (minSalary != -999 && currentJob.getSalary() < minSalary) {
                matches = false;
            }
            if (maxSalary != -999 && currentJob.getSalary() > maxSalary) {
                matches = false;
            }
            if (!statusInput.equals("-999")) {
                boolean targetStatus = Boolean.parseBoolean(statusInput);
                if (currentJob.isOpen() != targetStatus) {
                    matches = false;
                }
            }

            if (matches) {
                results[resCount] = currentJob;
                resCount++;
            }
        }

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
        boolean hasJobs = false;
        System.out.println("\n--- המשרות שפרסמת ---");

        for (int i = 0; i < jobCount; i++) {
            if (jobs[i].getPublisher().getUsername().equals(currentUser.getUsername())) {
                System.out.println((i + 1) + ". " + jobs[i]);
                System.out.println("-------------------------");
                hasJobs = true;
            }
        }

        if (!hasJobs) {
            System.out.println("טרם פרסמת משרות במערכת.");
        }
    }

    public void applyForJob(User seeker) {
        if (jobCount == 0) {
            System.out.println("אין כרגע משרות זמינות במערכת.");
            return;
        }

        printAllJobs();
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n--- הגשת מועמדות למשרה ---");
        System.out.println("הזן את כותרת המשרה אליה תרצה להגיש מועמדות מתוך הרשימה:");
        String targetTitle = scanner.nextLine().trim();

        Job targetJob = null;
        for (int i = 0; i < jobCount; i++) {
            if (jobs[i].getTitle().equalsIgnoreCase(targetTitle)) {
                targetJob = jobs[i];
                break;
            }
        }

        if (targetJob == null) {
            System.out.println("שגיאה: משרה בשם זה לא נמצאה במערכת.");
            return;
        }
        if (!targetJob.isOpen()) {
            System.out.println("שגיאה: המשרה כרגע סגורה ולא ניתן להגיש אליה מועמדות.");
            return;
        }
        if (targetJob.getPublisher().getUsername().equals(seeker.getUsername())) {
            System.out.println("שגיאה: אינך יכול להגיש מועמדות למשרה שאתה פרסמת בעצמך.");
            return;
        }

        boolean success = targetJob.addApplicant(seeker);
        if (success) {
            System.out.println("מועמדותך למשרה '" + targetTitle + "' הוגשה בהצלחה!");
        }
    }

    public void viewMyApplicants(User recruiter) {
        if (countJobsByUser(recruiter) == 0) {
            System.out.println("טרם פרסמת משרות במערכת ולכן אין לך מועמדים לצפות בהם.");
            return;
        }

        printUserJobs(recruiter);
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n--- צפייה במועמדים ---");
        System.out.println("הזן את כותרת המשרה שעבורה תרצה לראות את המועמדים:");
        String targetTitle = scanner.nextLine().trim();

        Job targetJob = null;
        for (int i = 0; i < jobCount; i++) {
            if (jobs[i].getTitle().equalsIgnoreCase(targetTitle)) {
                if (jobs[i].getPublisher().getUsername().equals(recruiter.getUsername())) {
                    targetJob = jobs[i];
                }
                break;
            }
        }

        if (targetJob == null) {
            System.out.println("שגיאה: המשרה לא נמצאה או שאינך המפרסם של משרה זו.");
            return;
        }

        int applicantCount = targetJob.getApplicantCount();
        User[] applicants = targetJob.getApplicants();

        System.out.println("\n=== רשימת מועמדים למשרה: " + targetJob.getTitle() + " ===");
        if (applicantCount == 0) {
            System.out.println("אין עדיין מועמדים למשרה זו.");
        } else {
            for (int i = 0; i < applicantCount; i++) {
                User applicant = applicants[i];
                System.out.println((i + 1) + ". שם: " + applicant.getUsername() +
                        " | דוא\"ל: " + applicant.getEmail() +
                        " | טלפון: " + applicant.getPhone());
                System.out.println("-------------------------------------------------");
            }
        }
    }

    private int countJobsByUser(User user) {
        int count = 0;
        for (int i = 0; i < jobCount; i++) {
            if (jobs[i].getPublisher().getUsername().equals(user.getUsername())) {
                count++;
            }
        }
        return count;
    }

    private Company findCompanyByName(String companyName) {
        for (int i = 0; i < companyCount; i++) {
            if (companies[i].getName().equalsIgnoreCase(companyName)) {
                return companies[i];
            }
        }
        return null;
    }
}