package sample;

public class ProPlayer extends Player {
    int age;
    int contractYears;
    int salary;



    public ProPlayer(String name, int rating, String position, String team, int number, int age) {
        super(name, rating, position, team, number, "PRO");
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getContractYears() {
        return contractYears;
    }

    public void setContractYears(int contractYears) {
        this.contractYears = contractYears;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }
}
