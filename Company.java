public class Company {
    private String name;
    private String field;
    private int employeeCount;

    public Company(String name, String field, int employeeCount) {
        this.name = name;
        this.field = field;
        this.employeeCount = employeeCount;
    }

    @Override
    public String toString() {
        return "חברת " + name + ", תחום: " + field + ", מספר עובדים: " + employeeCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public int getEmployeeCount() {
        return employeeCount;
    }

    public void setEmployeeCount(int employeeCount) {
        this.employeeCount = employeeCount;
    }
}