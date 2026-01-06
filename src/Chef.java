public class Chef extends Staff {

    private String specialization;

    public Chef(int staffId, String name, double salary,
                int experienceYears, String specialization) {
        super(staffId, name, salary, experienceYears); // FIRST line
        this.specialization = specialization;
    }
    public String getSpecialization() {
        return specialization;
    }
    @Override
    public void work() {
        System.out.println("Chef " + name +
                " is cooking " + specialization + " dishes.");
    }
    @Override
    public String getRole() {
        return "Chef";
    }
    public void cookDish(String dish) {
        System.out.println("Chef " + name + " prepares: " + dish);
    }
    public boolean isMasterChef() {
        return experienceYears >= 10;
    }
    @Override
    public String toString() {
        return super.toString() + " | Specialization: " + specialization;
    }
}
