/**
 *
 * @author jason
 */
public class Department {
    
    private String name;
    private Employee managerName;

    public Department(String name, Employee managerName) {
        this.name = name;
        this.managerName = managerName;
    }

    public String getName() {
        return name;
    }

    public Employee getManagerName() {
        return managerName;
    }

    public void setManagerName(Employee managerName) {
        this.managerName = managerName;
    }    
    
}
