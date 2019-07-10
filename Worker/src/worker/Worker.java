package worker;
import java.io.*;

public class Worker implements Serializable{
    int id;
    String name;
    int age;
    int salary;
    String workLocation;
    public int getID(){
        return id;
    }
    public String getName(){
        return name;
    }
    public int getSalary(){
        return salary;
    }
    public int getAge(){
        return age;
    }

    public Worker(int id, String name, int age, int salary, String workLocation) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.salary = salary;
        this.workLocation = workLocation;
    }
    
}
