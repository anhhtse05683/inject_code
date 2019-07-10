package worker;
import java.io.*;
public class History implements Serializable{
    String date;
    String status;
    int id;
    String name;
    int age;
    int salary;
    public History(String date, String status, int id, String name, int age, int salary) {
        this.date=date;
        this.status=status;
        this.id=id;
        this.name=name;
        this.age=age;
        this.salary=salary;
    }
    
    @Override
    public String toString(){
        return "W:"+id+"\t"+name+"\t"+age+"\t"+salary+"\t"+status+"\t"+date;
    }
    
}
