public class Staff {
    protected int staffId;
    protected String name;
    protected double salary;
    protected int experienceYears;

    public Staff(int staffId, String name, double salary, int experienceYears) {
        this.staffId = staffId;
        this.name = name;
        setSalary(salary);
        setExperienceYears(experienceYears);
    }
    public int getStaffId() { return staffId; }
    public String getName() { return name; }
    public double getSalary() { return salary; }
    public int getExperienceYears() { return experienceYears; }

    public void setSalary(double salary) {
        if (salary >= 0) this.salary = salary;
    }
    public void setExperienceYears(int experienceYears) {
        if (experienceYears >= 0) this.experienceYears = experienceYears;
    }
    public void work() {
        System.out.println(name + " is working.");
    }
    public String getRole() {
        return "Staff Member";
    }
    public boolean isExperienced() {
        return experienceYears >= 5;
    }
    @Override
    public String toString() {
        return "[" + getRole() + "] " + name +
                " (ID: " + staffId +
                ", Salary: " + salary +
                " KZT, Experience: " + experienceYears + " years)";
    }
}
