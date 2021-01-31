import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

class TestCode{
    public static void main(String[] args){
        //create studentSystem object
        StudentSystem studentSystem = new StudentSystem();    

        //create one undergraduate student and one postgraduate student
        Address home = new Address(26, "Random street", "City of monkeys", "Tasmania", 1234, "Australia");
        Student student1 = new Graduate("James", "14/08/2000", home, 423628825, 100000, "Masters in electrical engineering", "Winter, 2023", "Bachelor of Medical Science", "03/12/2019"); 
        Student student2 = new Undergraduate("Sean", "30/09/1998", home, 42782627, 200000, "Bachelor of Computer Science", "Spring, 2023");

        //add the two student objects to the student arraylist;
        studentSystem.addStudent(student1);
        studentSystem.addStudent(student2);

        //student search method to search and print two students info
        Scanner input = new Scanner(System.in);
        System.out.println("Enter a student ID to search for: ");
        studentSystem.search(Integer.parseInt(input.nextLine()));

        System.out.println("Enter a student ID to search for: ");
        studentSystem.search(Integer.parseInt(input.nextLine()));

        /*use a for loop to go through the student arraylist and 
        update the undergraduate student’s expected completion 
        session to "Spring 2022", and update the postgraduate student’s
        previous degree to "Bachelor of Math"(using instanceof and 
        downcasting)*/
        
        //need to convert arraylist to normal array
        Student[] students = studentSystem.getStudentList();

        for(Student student : students){
            if(student instanceof Undergraduate){
                //downcasting
                Undergraduate undergrad = (Undergraduate)student;
                undergrad.setExpectedCompletionSession("Spring 2022");
            } 
            else if(student instanceof Graduate){
                //downcasting
                Graduate grad = (Graduate)student;
                grad.setPreviousDegree("Bachelor of Math");
            }
        }

        /*use another for loop to print the updated information of 
        the two students*/
        System.out.println();
        System.out.println("After changes have been made:");

        for(Student student : students){
            System.out.println();
            System.out.println(student);
        }
    }
}

class Address{
    //datafields
    private int streetNumber;
    private String streetName;
    private String city;
    private String state;
    private int postCode;
    private String country;

    //constructors
    /*should always have default constructor especially in case of 
    future inheritance*/
    public Address(){
        //all datafields need to be initialised otherwise error occurs
        this.streetNumber = 0;
        this.streetName = "x";
        this.city = "x";
        this.state = "x";
        this.postCode = 0;
        this.country = "x";
    }

    public Address(int streetNumber, String streetName, 
                    String city, String state, int postCode, 
                    String country){
        this.streetNumber = streetNumber;
        this.streetName = streetName;
        this.city = city;
        this.state = state;
        this.postCode = postCode;
        this.country = country;
    }

    //methods
    public int getStreetNumber(){
        return streetNumber;
    }

    public String getStreetName(){
        return streetName;
    }

    public String getCity(){
        return city;
    }

    public String getState(){
        return state; 
    }

    public int getPostCode(){
        return postCode;
    }

    public String getCountry(){
        return country;
    }

    public void setStreetNumber(int streetNumber){
        this.streetNumber = streetNumber;
    }

    public void setStreetName(String streetName){
        this.streetName = streetName;
    }

    public void setCity(String city){
        this.city = city;
    }

    public void setState(String state){
        this.state = state;
    }

    public void setPostCode(int postCode){
        this.postCode = postCode;
    }

    public void setCountry(String country){
        this.country = country;
    }

    @Override
    public String toString(){
        return Integer.toString(getStreetNumber()) + " " + getStreetName() + ", " + getCity() + ", " + getState() + ", " + getCountry() + ", " + getPostCode();
    }
}

class StudentSystem{
    //datafields
    private ArrayList<Student> students;

    //constructor
    public StudentSystem(){
        this.students = new ArrayList<Student>();
    }

    //methods
    public void search(int STUDENTID){
        //loop through students find a match and print
        boolean studentFound = false;

        for(int i = 0; i < students.size(); i++){
            Student student = students.get(i);
            if(student.getStudentID() == STUDENTID){
                studentFound = true;
                System.out.println();
                System.out.println("Student found:");
                System.out.println(student);
            }
        }
        if(!studentFound){
            System.out.println();
            System.out.println("No student found.");
        }
    }

    public void addStudent(Student student){
        students.add(student);
    }

    public void removeStudent(Student student){
        students.remove(student);
    }

    public Student[] getStudentList(){ 
        //using list interface 
        List<Student> studentsList = students;
        Student[] studentsArray = new Student[studentsList.size()];
        studentsArray = studentsList.toArray(studentsArray);
        return studentsArray;
    }
}

abstract class Student{
    //datafields
    private String name;
    private final String DOB;
    private Address address;
    private double mobileNumber;
    private final int STUDENTID;

    //constructors
    public Student(){
        this.name = "x";
        this.DOB = "x";
        this.address = new Address();
        this.mobileNumber = 0;
        this.STUDENTID = 0;
    }

    public Student(String name, String DOB, Address address, double mobileNumber, int STUDENTID){
        this.name = name;
        this.DOB = DOB;
        this.address = address;
        this.mobileNumber = mobileNumber;
        this.STUDENTID = STUDENTID;
    }

    //methods
    public String getName(){
        return name;
    }

    public String getDOB(){
        return DOB;
    }

    public Address getAddress(){
        return address;
    }

    public double getMobileNumber(){
        return mobileNumber;
    }

    public int getStudentID(){
        return STUDENTID;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setAddress(Address address){
        this.address = address;
    }

    public void setMobileNumber(double number){
        this.mobileNumber = number;
    }
}

class Undergraduate extends Student{
    //datafields
    private String currentCourse;
    private String expectedCompletionSession;

    //constructors
    public Undergraduate(){
        super();
        this.currentCourse = "x";
        this.expectedCompletionSession ="x";
    }

    public Undergraduate(String name, String DOB, Address address, double mobileNumber, int STUDENTID, String currentCourse, String expectedCompletionSession){
        super(name, DOB, address, mobileNumber, STUDENTID);
        this.currentCourse = currentCourse;
        this.expectedCompletionSession = expectedCompletionSession;
    }

    //methods
    public String getCurrentCourse(){
        return currentCourse;
    }

    public String getExpectedCompletionSession(){
        return expectedCompletionSession;
    }

    public void setCurrentCourse(String currentCourse){
        this.currentCourse = currentCourse;
    }

    public void setExpectedCompletionSession(String expectedCompletionSession){
        this.expectedCompletionSession = expectedCompletionSession;
    }

    @Override
    public String toString(){
        return String.format("%s enrolled in %s. \n DOB: %s \n Mobile: %.0f \n Address: %s \n Student ID: %s \n Expected Completion session: %s", 
        super.getName(), getCurrentCourse(), super.getDOB(), super.getMobileNumber(), super.getAddress(), super.getStudentID(), getExpectedCompletionSession());
    }
}

class Graduate extends Student{
    //datafields
    private String currentCourse;
    private String expectedCompletionSession;
    private String previousDegree;
    private String dateOfCompletionForUndergrad;

    //constructors
    public Graduate(){
        super();
        this.currentCourse = "x";
        this.expectedCompletionSession = "x";
        this.previousDegree = "x";
        this.dateOfCompletionForUndergrad = "x";
    }

    public Graduate(String name, String DOB, Address address, double mobileNumber, int STUDENTID, String currentCourse, String expectedCompletionSession, String previousDegree, String dateOfCompletionForUndergrad){
        super(name, DOB, address, mobileNumber, STUDENTID);
        this.currentCourse = currentCourse;
        this.expectedCompletionSession = currentCourse;
        this.previousDegree = previousDegree;
        this.dateOfCompletionForUndergrad = dateOfCompletionForUndergrad;
    }

    //methods
    public String getCurrentCourse(){
        return currentCourse;
    }

    public String getExpectedCompletionSession(){
        return expectedCompletionSession;
    }

    public void setCurrentCourse(String currentCourse){
        this.currentCourse = currentCourse;
    }

    public void setExpectedCompletionSession(String expectedCompletionSession){
        this.expectedCompletionSession = expectedCompletionSession;
    }

    public String getPreviousDegree(){
        return previousDegree;
    }

    public String getDateOfCompletionForUndergrad(){
        return dateOfCompletionForUndergrad;
    }

    public void setPreviousDegree(String previousDegree){
        this.previousDegree = previousDegree;
    }

    public void setDateOfCompletionForUndegrad(String dateOfCompletionForUndergrad){
        this.dateOfCompletionForUndergrad = dateOfCompletionForUndergrad;
    }

    @Override
    public String toString(){
        return String.format("%s currently enrolled in %s. \n DOB: %s \n Mobile: %.0f \n Address: %s \n Student ID: %s \n Expected Completion session: %s \n Undergraduate degree: %s (completed on %s)", 
        super.getName(), getCurrentCourse(), super.getDOB(), 
        super.getMobileNumber(), super.getAddress(), 
        super.getStudentID(), getExpectedCompletionSession(), 
        getPreviousDegree(), getDateOfCompletionForUndergrad());
    }
}
