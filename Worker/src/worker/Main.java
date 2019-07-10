package worker;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.*;

public class Main {

    public static final String FILE_SAVE = "worker.txt";
    public static final String FILE_HISTORY = "history.dat";

    public static void main(String[] args) throws Exception {
         System.err.println("Hello World!");
         System.err.print("HELLO");
        //*--------------------------------------*\\
        Scanner sc = new Scanner(System.in);
        List<Worker> worker = new ArrayList<>();
        List<History> history = new ArrayList<>();
        while (true) {
            worker = loadWorker();//load list worker            
            System.out.println("1.Add worker");
            System.out.println("2.Increase salary");
            System.out.println("3.Decrease salary");
            System.out.println("4.Show history");
            System.out.println("5.Exit");
            String userChoice = sc.nextLine();
            switch (userChoice) {
                case "1":
                    addWorker(sc, worker);
                    break;
                case "2":
                    increaseSalary(sc);
                    break;
                case "3":
                    decreaseSalary(sc);
                    break;
                case "4":
                    showHistory();
                    break;
                case "5":
                    System.exit(0);
            }
        }
    }

    //load list all of workers
    public static List<Worker> loadWorker() {
        List<Worker> worker = new ArrayList<>();
        if (checkExist(FILE_SAVE)) {// check exist file before load
            try {
                try (
                        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_SAVE))) {
                    worker = (List<Worker>) ois.readObject();
                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException | ClassNotFoundException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return worker;
    }

    //load list worker salary update
    public static List<History> loadHistory() {
        List<History> history = new ArrayList<>();
        if (checkExist(FILE_HISTORY)) {// check exist file before load
            try {
                try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_HISTORY))) {
                    history = (List<History>) ois.readObject();
                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException | ClassNotFoundException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return history;
    }

    //save list of all worker after add new worker
    public static void saveWorker(List<Worker> worker) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_SAVE));
            oos.writeObject(worker);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //save list of worker updated salary
    public static void saveHistory(List<History> history) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_HISTORY));
            oos.writeObject(history);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static boolean checkExist(String file_path) {
        File fp = new File(file_path);
        return fp.exists();//return result file exist or not
    }

    public static boolean checkCode(String codeID, List<Worker> worker) {
        if (!codeID.matches("\\d+")) {//\\d+ mean match number [0-9]
            System.out.println("Code ID must is number and large than 0");
            return false;
        }
        int id = Integer.parseInt(codeID);//parse codeID from string to int
        //loop will scan all elements of list worker
        for (Worker x : worker) {
            if (id == x.getID()) {//check exist id
                System.out.println("Your inputed id is exist on DB");
                return false;
            }
        }
        return true;
    }

    //checkAge between 18 to 50
    public static boolean checkAge(String age) {
        if (!age.matches("\\d+")) {//\\d+ mean match number [0-9]
            System.out.println("Age must is number and large than 0");
            return false;
        }
        int ageNum = Integer.parseInt(age);
        if (ageNum < 18 || ageNum > 50) {//check age between 18 to 50
            System.out.println("Age must between 18 to 50");
            return false;
        }
        return true;
    }

    public static boolean checkEmptyInput(String input) {
        return input.isEmpty() || input == null;
    }

    public static boolean checkDate(String date) {
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        df.setLenient(false);
        try {
            df.parse(date);
        } catch (ParseException exception) {
            System.out.println("Date Is Not valid format");
            return false;
        }
        return true;
    }

    public static void addWorker(Scanner sc, List<Worker> worker) {
        String id, name, age, salary, workLocation;
        System.out.println("Enter worker ID: ");
        id = sc.nextLine();
        //loop run when id is not valid
        while (!checkCode(id, worker)) {
            id = sc.nextLine();
        }
        int wID = Integer.parseInt(id);
        System.out.println("Enter name: ");
        name = sc.nextLine();
        //loop run when input empty or null
        while (checkEmptyInput(name)) {
            System.out.println("Name not is empty");
            name = sc.nextLine();
        }
        System.out.println("Enter age: ");
        age = sc.nextLine();
        while (!checkAge(age)) {
            age = sc.nextLine();
        }
        int wAge = Integer.parseInt(age);
        System.out.println("Enter salary: ");
        salary = sc.nextLine();
        //loop run when salary input is not valid
        while (!checkSalary(salary)) {
            salary = sc.nextLine();
        }
        int wSalary = Integer.parseInt(salary);

        System.out.println("Enter worker location");
        workLocation = sc.nextLine();
        //loop run when input in-valid
        while (checkEmptyInput(workLocation)) {
            System.out.println("Worker Location not empty");
            workLocation = sc.nextLine();
        }
        worker.add(new Worker(wID, name, wAge, wSalary, workLocation));
        saveWorker(worker);//save new worker to file worker.dat
        System.out.println("Worker infomation have been saved!");
    }

    public static boolean checkSalary(String salary) {
        if (!salary.matches("\\d+")) {//check salary is number or not
            System.out.println("Must number and large than 0");
            return false;
        }
        int money = Integer.parseInt(salary);
        if (money <= 0) {//check salary < 0 
            System.out.println("Must a number > 0");
            return false;
        }
        return true;
    }

    public static void increaseSalary(Scanner sc) {
        List<Worker> worker = loadWorker();
        List<History> history = new ArrayList<>();
        history = loadHistory();
        String id, salary, date, amount;
        int check = 0, total;
        System.out.println("Enter worker ID: ");
        id = sc.nextLine();
        //loop run when id is not valid
        while (!id.matches("\\d+")) {
            System.out.println("ID must is number large than 0");
            id = sc.nextLine();
        }
        int wID = Integer.parseInt(id);
        System.out.println("Enter Amount:");
        amount = sc.nextLine();
        while (!checkSalary(amount)) {
            amount = sc.nextLine();
        }
        int wAmount = Integer.parseInt(amount);
        System.out.println("Enter date");
        date = sc.nextLine();
        while (!checkDate(date)) {
            date = sc.nextLine();
        }
        // loop will scan all elements of worker list
        for (Worker x : worker) {
            if (wID == x.getID()) {
                total = x.getSalary() + wAmount;
                history.add(new History(date, "UP", wID, x.getName(), x.getAge(), total));
                saveHistory(history);
                check = 1;
                break;
            }
        }
        if (check == 1) {
            System.out.println("Increase Salary Successful!");
        } else {
            System.out.println("Can Not found this ID!!!");
        }
    }

    public static void decreaseSalary(Scanner sc) {
        List<Worker> worker = loadWorker();
        List<History> history = new ArrayList<>();
        history = loadHistory();
        String id, name, age, salary, date, amount;
        int check = 0, total;
        System.out.println("Enter worker ID: ");
        id = sc.nextLine();
        //loop run when id is not valid
        while (!id.matches("\\d+")) {
            System.out.println("ID must is number large than 0");
            id = sc.nextLine();
        }
        int wID = Integer.parseInt(id);
        System.out.println("Enter Amount:");
        amount = sc.nextLine();
        while (!checkSalary(amount)) {
            amount = sc.nextLine();
        }
        int wAmount = Integer.parseInt(amount);
        System.out.println("Enter date");
        date = sc.nextLine();
        while (!checkDate(date)) {
            date = sc.nextLine();
        }
        // loop will scan all elements of worker list
        for (Worker x : worker) {
            if (wID == x.getID()) {
                if (wAmount > x.getSalary()) {
                    System.out.println("Amount must large than stock salary");
                    break;
                } else {
                    total = x.getSalary() - wAmount;
                    history.add(new History(date, "DOWN", wID, x.getName(), x.getAge(), total));
                    saveHistory(history);
                    check = 1;
                }
            }
        }
        if (check == 1) {
            System.out.println("Decrease Salary Successful!");
        } else if (check == 0) {
            System.out.println("Can Not found this ID!!!");
        }
    }

    public static void showHistory() {
        if (checkExist(FILE_HISTORY)) {
            List<History> history = new ArrayList<>();
            history = loadHistory();
            System.out.println("ID\tName\tAge\tSalary\tStatus\tDate");
            // loop will scan all elements of history list
            for (History x : history) {
                System.out.println(x.toString());
            }
        } else {
            System.out.println("No data ");
        }
    }

}
