import java.util.ArrayList;

class Course{
    private String name;
    private ArrayList<Student> studentList;
    private ArrayList<Subject> subjectList;

    public static void main(String[] args){
        //Create Course object
        Course compSci = new Course("Bachelor of Computer Science");
        
        //Create Subjects objects(2 subjects): 
        Subject prog1 = new Subject("Programming Fundamentals", "CSIT111", "Spring", 2020);
        Subject prog2 = new Subject("Object Oriented Design and Programming", "CSIT121", "Spring", 2020);
        
        //Create Students objects (4 students): 
        Student Amy = new Student("Amy Bell", "01/01/2001", "Female", 100001);
        Student Bob = new Student("Bob Brown", "02/02/2002", "Male", 200001);
        Student Cindy = new Student("Cindy Ma", "03/03/2001", "Female", 100003);
        Student David = new Student("David Hintz", "04/04/2000", "Male", 100004);
        
        //add subjects and students to course
        compSci.addSubject(prog1);
        compSci.addSubject(prog2);
        compSci.enrolStudent(Amy);
        compSci.enrolStudent(Bob);
        compSci.enrolStudent(Cindy);
        compSci.enrolStudent(David);
        
        //add 3 students to 1 subject and 1 to the other
        prog1.enrolStudent(Amy);
        prog1.enrolStudent(Bob);
        prog1.enrolStudent(Cindy);
        prog2.enrolStudent(David);
        
        //testing outputs
        compSci.printData();
        
        //remove Cindy from prog1 and enrol in prog2
        prog1.withdrawStudent(Cindy);
        prog2.enrolStudent(Cindy);
        
        //testing outputs
        compSci.printData();
    }
    
    //constructor with parameters
    public Course(String name){
        this.name = name;
        this.subjectList = new ArrayList<Subject>();
        this.studentList = new ArrayList<Student>();
    }
    
    public String getName(){
        return name;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public void enrolStudent(Student name){
        studentList.add(name);
    }
    
    public void withdrawStudent(Student name){
        studentList.remove(name);
    }
    
    public void addSubject(Subject name){
        subjectList.add(name);
    }
    
    public void removeSubject(Subject name){
        subjectList.remove(name);
    }
    
    //allows current course information to be printed
    public void printData(){
        System.out.println("--------------------------");
        System.out.printf("Course name: %s\n", name);
        System.out.println();
        for(int i = 0; i < subjectList.size(); i++){
            System.out.printf("Subject Name: %s \n", subjectList.get(i));
        }
        System.out.println("--------------------------");
    }
}
  
class Subject{
    //data-fields
    private String name;
    private String code;
    private String session;
    private int year;
    private ArrayList<Student> students;
    
    //constructor with parameters
    public Subject(String name, String code, String session, int year){
        this.name = name;
        this.code = code;
        this.session = session;
        this.year = year;
        this.students = new ArrayList<Student>();
    }
    
    public String getName(){
        return name;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public String getSession(){
        return session;
    }
    
    public void setSession(String session){
        this.session = session;
    }
    
    public int getYear(){
        return year;
    }
    
    public void setYear(int year){
        this.year = year;
    }
    
    public void enrolStudent(Student name){
        //adds student to data-field arraylist
        students.add(name);
    }
    
    public void withdrawStudent(Student name){
        //removes student from data-field arraylist
        students.remove(name);
    }
    
    public String toString(){
        String printOut = name + " (" + code + ", " + session + " " + Integer.toString(year) + ")\nEnrolled Students:\n";
        for(int i = 0; i < students.size(); i++){
                printOut += students.get(i) + "\n";
            }
            printOut +="\n";
        return printOut;
    }
}

class Student{
    //data-fields
    private String name;
    private String DOB;
    private String sex;
    private int studentNumber;
    
    //constructor
    public Student(String name, String DOB, String sex, int studentNumber){
        this.name = name;
        this.DOB = DOB;
        this.sex = sex;
        this.studentNumber = studentNumber;
    }
    
    public String getName(){
        return name;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public String getDOB(){
        return DOB;
    }
    
    public String getstudentNumber(){
        return Integer.toString(studentNumber);
    }
    
    public String toString(){
        return name + "\t(" + Integer.toString(studentNumber) + ")";
    }
}
