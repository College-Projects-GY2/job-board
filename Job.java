public class Job {
        private String title;
        private int salary;
        private boolean isFullTime;
        private boolean isOpen;
        private Company company; // הרכבה
        private User publisher;   // הרכבה

        public Job(String title, int salary, boolean isFullTime, Company company, User publisher) {
            this.title = title;
            this.salary = salary;
            this.isFullTime = isFullTime;
            this.isOpen = true; // משרה חדשה נפתחת כברירת מחדל כפעילה
            this.company = company;
            this.publisher = publisher;
        }

        @Override
        public String toString() {
            String jobType = isFullTime ? "Full-time" : "Part-time";
            String jobStatus = isOpen ? "Open" : "Closed";

            return "=== Job Details ===\n" +
                    "Title: " + title + "\n" +
                    "Salary: ₪" + salary + "\n" +
                    "Job Type: " + jobType + "\n" +
                    "Status: " + jobStatus + "\n" +
                    "חברה: [" + company.toString() + "]\n" +
                    "פורסם על ידי: [" + publisher.getUsername() + "]";
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
            isFullTime = fullTime;
        }

        public boolean isOpen() {
            return isOpen;
        }

        public void setOpen(boolean open) {
            isOpen = open;
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
}
