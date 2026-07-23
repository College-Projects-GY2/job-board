public class Company {
    private final String name;
    private final String field;
    private final int employeeCount;

    public Company(String name, String field, int employeeCount) {
        this.name = name;
        this.field = field;
        this.employeeCount = employeeCount;
    }

    public String getName() {
        return name;
    }

    public String getField() {
        return field;
    }

    public int getEmployeeCount() {
        return employeeCount;
    }


    @Override
    public String toString() {
        return "חברת " + name + ", תחום: " + field + ", מספר עובדים: " + employeeCount;
    }
}