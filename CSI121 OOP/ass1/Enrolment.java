/*------------------------------------------------------  
My name: Sean Overton
My student number: 6421490
My course code: CSIT121  
My email address: so412@uowmail.edu.au
Assignment number: 1
-------------------------------------------------------*/
import java.util.Scanner;
import java.util.ArrayList;

class Enrolment{
    public static void main(String[] args){
        //Create Subject objects 
        Subject CSIT111 = new Subject("CSIT111", "Programming Fundamentals", 6);
        Subject CSIT113 = new Subject("CSIT113", "Problem solving", 6);
        Subject CSIT114 = new Subject("CSIT114", "System Analysis", 6);
        Subject CSIT115 = new Subject("CSIT115", "Data Management and Security", 6);
        Subject CSIT121 = new Subject("CSIT121", "Object Oriented Design and Programming", 6);
        Subject CSIT127 = new Subject("CSIT127", "Networks and Communications", 6);
        Subject CSIT128 = new Subject("CSIT128", "Introduction to Web Technology", 6);
        Subject CSCI235 = new Subject("CSCI235", "Database Systems", 6);
        Subject CSCI251 = new Subject("CSCI251", "Advanced Programming", 6);
        Subject CSIT214 = new Subject("CSIT214", "IT Project Management", 6);
        Subject MATH221 = new Subject("MATH221", "Mathematics for Computer Science", 6);
        Subject CSCI203 = new Subject("CSCI203", "Data Structures and Algorithms", 6);
        Subject CSIT226 = new Subject("CSIT226", "Human Computer Interaction", 6);
        Subject CSIT314 = new Subject("CSIT314", "Software Development Methodologies", 6);
        Subject CSIT321 = new Subject("CSIT321", "Project", 12);
        Subject CSCI317 = new Subject("CSCI317", "Database Performance Tuning", 6);
        Subject INFO411 = new Subject("INFO411", "Data Mining and Knowledge Discovery", 6);
        Subject CSCI316 = new Subject("CSCI316", "Big Data Mining Techniques and Implementation", 6);
        Subject ISIT312 = new Subject("ISIT312", "Big Data Management", 6);
        Subject CSCI301 = new Subject("CSCI301", "Contemporary Topics in Security", 6);
        Subject CSCI262 = new Subject("CSCI262", "System Security", 6);
        Subject CSCI369 = new Subject("CSCI369", "Ethical Hacking", 6);
        Subject CSIT302 = new Subject("CSIT302", "Cybersecurity", 6);
        Subject CSCI361 = new Subject("CSCI361", "Cryptography and Secure Applications", 6);
        Subject CSCI368 = new Subject("CSCI368", "Network Security", 6);
        Subject CSCI376 = new Subject("CSCI376", "Multicore and GPU Programming", 6);
        Subject CSCI236 = new Subject("CSCI236", "3D Modelling and Animation", 6);
        Subject CSCI336 = new Subject("CSCI336", "Interactive Computer Graphics", 6);
        Subject CSCI366 = new Subject("CSCI366", "Mobile Multimedia", 6);
        Subject CSCI356 = new Subject("CSCI356", "Game Engine Essentials", 6);
        Subject CSCI334 = new Subject("CSCI334", "Software Design", 6);
        Subject ISIT219 = new Subject("ISIT219", "Knowledge and Information Engineering", 6);
        Subject CSCI318 = new Subject("CSCI318", "Software Engineering Practices and Principles", 6);
        Subject ISIT315 = new Subject("ISIT315", "Semantic Web", 6);

        //Create Majors and add subjects to majors: 
        Subject[] bigDataSubjects = {CSCI317, INFO411, CSCI316, ISIT312};
        Major bigData = new Major("Big Data", bigDataSubjects);

        Subject[] cyberSecSubjects = {CSCI301, CSCI262, CSCI369, CSIT302};
        Major cyberSec = new Major("Cyber Security", cyberSecSubjects);

        Subject[] sysSecSubjects = {CSCI361, CSCI368, CSCI262, CSCI376};
        Major sysSecurity = new Major("Digital System Security", sysSecSubjects);

        Subject[] softwareEngSubjects = {CSCI334, ISIT219, CSCI318, ISIT315};
        Major softwareEng = new Major("Software Engineering", softwareEngSubjects);
        
        Subject[] gameDevSubjects = {CSCI236, CSCI336, CSCI356, CSCI366, 
        CSCI376};
        Major gameDev = new Major("Game and Mobile Development", gameDevSubjects);

        //Create Course object
        Course compSci = new Course("Bachelor of Computer Science");
            
            //add all majors to course
        Major[] majorList = {bigData, cyberSec, sysSecurity, softwareEng, 
        gameDev};
        compSci.changeMajors(majorList);

            //add core subjects to Course
        Subject[] coreSubjects = {CSIT111, CSIT113, CSIT114, 
        CSIT115, CSIT121, CSIT127, CSIT128, CSCI235, CSCI251, CSIT214, 
        MATH221, CSCI203, CSIT226, CSIT314, CSIT321};
        compSci.changeSubjects(coreSubjects, "core");

            //add elective subjects to course
        Subject[] elecSubjects = {CSCI317, INFO411, CSCI316, ISIT312, CSCI301,
        CSCI262, CSCI369, CSIT302, CSCI361, CSCI368, CSCI376, CSCI236, CSCI336,
        CSCI356, CSCI366, CSCI334, CSCI318, ISIT219, ISIT315};
        compSci.changeSubjects(elecSubjects, "elective");

        compSci.setMinimumCreditPoints(144);

        //i) to display the entire course structure to the student;
        compSci.printData(); 
        
        /*ii) to ask the student to input the personal information 
        (by using the Scanner); */
        System.out.println("The following information is required to complete student enrolment:");
        //used to gather input from user/student
        Scanner input = new Scanner(System.in);
        System.out.print("Please enter your full name:");
        String sName = input.nextLine();

        System.out.print("Please enter your student number:");
        int sNumber = Integer.parseInt(input.nextLine());

        System.out.print("Please enter your gender:");
        String sGender = input.nextLine();

        System.out.print("Please enter your date of birth (14/08/2000):");
        String sDOB = input.nextLine();

        //using above gathered information student object created
        Student newStudent = new Student(sName, sDOB, sGender, sNumber);
        
        //automatically enrol student in course
        newStudent.setCourse(compSci);

        //iii) to automatically enrol the student to all core subjects;
        for(int i = 0; i < compSci.getSubjectList("core").length; i++){
            //adds subject to core subjects of student object
            newStudent.addSubject(compSci.getSubjectList("core")[i], "core");
        }

        /*iv) to ask the student to select a major and automatically 
        enrol the student to all subjects of the major; */
        System.out.println("Thanks for your information.");
        System.out.println("In order to complete your enrolment, please select a major from the list:");
        System.out.println();
        System.out.println("1. Big Data");
        System.out.println("2. Cyber Security");
        System.out.println("3. Digital System Security");
        System.out.println("4. Game and Mobile Development");
        System.out.println("5. Software Engineering");
        System.out.println();
        System.out.print("Please input the index number before the major:");
        
        String majorChoice = input.nextLine();

        System.out.println("You enrolled into:");
        switch(majorChoice){
            case "1":
                newStudent.setMajor(bigData);
                bigData.printData();
                break;
            case "2":
                newStudent.setMajor(cyberSec);
                cyberSec.printData();
                break;
            case "3":
                newStudent.setMajor(sysSecurity);
                sysSecurity.printData();
                break;
            case "4":
                newStudent.setMajor(softwareEng);
                softwareEng.printData();
                break;
            case "5":
                newStudent.setMajor(gameDev);
                gameDev.printData();
                break;
            default:
                newStudent.setMajor(softwareEng);
                System.out.println("Default enrolment:");
                softwareEng.printData();
        }

        /*v) to ask the student to select sufficient elective 
        subjects for reaching the minimal credit points requirement 
        of the course, i.e., 144 credit points; */
        System.out.println();
        System.out.println("In order to complete the enrolment, please select elective subjects from the list.");
        System.out.println();

        //calculates how many electives required left
        int electiveCount = newStudent.howManyElectivesLeft();

        /*default case: it is outside of elective subjects because it
        is a core subject. Therefore shouldn't cause any issues.*/
        Subject subjectChoiceSub = CSIT111;

        compSci.printElectives();

        do{
            //prompt for subject selection
            System.out.printf("Please select %s more elective subjects\n", electiveCount);
            String subjectChoiceStr = input.nextLine();
            String[] subjects = subjectChoiceStr.split(", ");

            //uses each subject from input
            for(int a = 0; a < subjects.length; a++){
                //locates subject object from string choice
                for(int i = 0; i < elecSubjects.length; i++){
                    if(elecSubjects[i].getCode().equals(subjects[a])){
                        //assigned actual subject
                        subjectChoiceSub = elecSubjects[i];
                    }
                }
                //if it wasn't valid input
                if(subjectChoiceSub == CSIT111){
                    continue;
                }

                //if subject already enrolled
                if(newStudent.isStudentAlreadyEnrolled(subjectChoiceSub)){
                    continue;
                }
                else{
                    //add subject to elective subjects
                    newStudent.addSubject(subjectChoiceSub, "elective");
                    electiveCount -= 1;
                }
            }

        //checks if course requirement is met
        }while(newStudent.howManyElectivesLeft() > 0);

        /*vi) once the student enrols sufficient subjects 
        to make up at least 144 credit points, the enrolment is 
        completed. */
        // vii) to display the student’s final enrolment record.
        newStudent.printData();
    }
}

class Course{
    private String name;
    private Subject[] electiveSubjectList;
    private Subject[] coreSubjectList;
    private Major[] majorList;
    private int minimumCreditPoints;
    
    //constructor with parameters
    public Course(String name){
        this.name = name;
    }
    
    //public methods
    public String getName(){
        return name;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public Subject[] getSubjectList(String type){
        if(type.equals("elective")){
            return electiveSubjectList;
        }
        else {
            return coreSubjectList;
        }
    } 
    
    public void changeSubjects(Subject[] subjectList, String type){
        /*same method can be used for both core and elective 
        subject changes with this if/else statement*/
        if(type.equals("core")){
            this.coreSubjectList = subjectList;
        }
        else if(type.equals("elective")){
            this.electiveSubjectList = subjectList;
        }
    }
    
    public void changeMajors(Major[] majorList){
        this.majorList = majorList;
    }

    public void setMinimumCreditPoints(int number){
        this.minimumCreditPoints = number;
    }

    public int getMinimumCreditPoints(){
        return minimumCreditPoints;
    }

    //display all electives
    public void printElectives(){
        System.out.println("Elective Subjects:");
        for(int i = 0; i < electiveSubjectList.length; i++){
            System.out.println(electiveSubjectList[i]);
        }
        System.out.println();
    }

    //allows current course information to be printed
    public void printData(){
        System.out.println("Welcome to enrol the " + name + " course.");
        System.out.println("The course structure is as follows:");
        System.out.println("--------------------------");
        System.out.printf("Course: %s\n", name);
        System.out.println();
        
        //core subjects are all printed by calling there own toString methods
        System.out.println("Core Subjects:");
        for(int i = 0; i < coreSubjectList.length; i++){
            System.out.println(coreSubjectList[i]);
        }

        System.out.println();
        
        //majors are all printed by calling there own toString methods
        for(int i = 0; i < majorList.length; i++){
            System.out.print("Major ");
            majorList[i].printData();
            System.out.println();
        }

        System.out.println();

        //elective subjects are all printed by calling there own toString methods
        System.out.println("Elective Subjects:");
        for(int i = 0; i < electiveSubjectList.length; i++){
            System.out.println(electiveSubjectList[i]);
        }
        System.out.println();
        System.out.println("--------------------------");
    }
}
  
class Subject{
    //data-fields
    private String name;
    private String code;
    private int creditPoint;
    
    //constructor with parameters
    public Subject(String code, String name, int creditPoint){
        this.name = name;
        this.code = code;
        this.creditPoint = creditPoint;
    }
    
    public String getName(){
        return name;
    }
    
    public void setName(String name){
        this.name = name;
    }

    public String getCode(){
        return code;
    }
    
    public int getCreditPoints(){
        return creditPoint;
    }
    
    public void setCreditPoint(int creditPoint){
        this.creditPoint = creditPoint;
    }

    public String toString(){
        String printOut = code + " (" + name + ", " + Integer.toString(creditPoint) + "pt)";
        return printOut;
    }
}

class Major{
    private String name;
    private Subject[] subjectList;
    private int creditPoints;

    //constructor with 1 parameter
    public Major(String name, Subject[] subjectList){
        this.name = name;
        this.subjectList = subjectList;
    }

    public String getName(){
        return name;
    }
     
    public void setName(String name){
        this.name = name;
    }

    public int getCreditPoints(){
        for(int i = 0; i < subjectList.length; i++){
            creditPoints += subjectList[i].getCreditPoints();
        }
        return creditPoints;
    }

    //predicate method
    public boolean contains(Subject name){
        for(int i = 0; i < subjectList.length; i++){
            if(subjectList[i] == name){
                return true;
            }
        }
        return false;
    }

    public void printData(){
        System.out.println(name);
        for(int i = 0; i < subjectList.length; i++){
            System.out.println("-" + subjectList[i]);
        }
    }
}

class Student{
    //data-fields
    private String name;
    private final String DOB;
    private String sex;
    private int studentNumber;
    private Course course;
    private Major major;
    private ArrayList<Subject> coreSubjects;
    private ArrayList<Subject> elecSubjects;
    private int creditPoints;

    //constructor
    public Student(String name, String DOB, String sex, int studentNumber){
        this.name = name;
        this.DOB = DOB;
        this.sex = sex;
        this.studentNumber = studentNumber;
        this.coreSubjects = new ArrayList<Subject>();
        this.elecSubjects = new ArrayList<Subject>();
    }
    
    //public methods
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
    
    public void setCourse(Course name){
        this.course = name;
    }

    public Course getCourse(){
        return course;
    }

    public void addSubject(Subject name, String type){
        if(type.equals("elective")){
            elecSubjects.add(name);
        }
        else if(type.equals("core")){
            coreSubjects.add(name);
        }
        //updates creditPoints so always up to date
        creditPoints += name.getCreditPoints();
    }

    public void deleteSubject(Subject name, String type){
        if(type.equals("elective")){
            elecSubjects.remove(name);
        }
        else if(type.equals("core")){
            coreSubjects.remove(name);
        }
        //updates creditPoints so always up to date
        creditPoints -= name.getCreditPoints();
    }

    public void setMajor(Major major){
        this.major = major;
        //updates creditPoints so always up to date
        creditPoints += major.getCreditPoints();
    }

    public Major getMajor(){
        return major;
    }

    public int getCreditPoints(){
        return creditPoints;
    }

    //predicate method(just checks truth)
    public boolean isStudentAlreadyEnrolled(Subject name){
        if(elecSubjects.contains(name)){
            return true;
        }
        else if(elecSubjects.contains(name)){
            return true;
        }
        else if(major.contains(name)){
            return true;
        }
        return false;
    }

    /*calculates number of electives left to reach minimum credit 
    points for selected course*/
    public int howManyElectivesLeft(){
        if(creditPoints < course.getMinimumCreditPoints()){
            return ((course.getMinimumCreditPoints() - creditPoints)/6);
        }
        return 0;
    }

    public void printData(){
        System.out.println();
        System.out.println("Congratulations. You have completed enrolment into a " + course.getName() + " course.");
        System.out.println("-------------------------------------------");
        System.out.println();
        System.out.println("Student: " + name + "\t(" + Integer.toString(studentNumber) + ", " + sex + ", " + DOB + ")");
        System.out.println();
        
        //core subjects are all printed by calling there own toString methods
        System.out.println("Cores:");
        for(int i = 0; i < coreSubjects.size(); i++){
            System.out.println(coreSubjects.get(i));
        }

        System.out.println();
        
        //majors are all printed by calling there own toString methods
        System.out.print("Major: ");
        major.printData();

        System.out.println();

        //elective subjects are all printed by calling there own toString methods
        System.out.println("Electives:");
        for(int i = 0; i < elecSubjects.size(); i++){
            System.out.println(elecSubjects.get(i));
        }
        System.out.println();
        System.out.println("--------------------------");
        System.out.println("Total enrolled credit points: " + Integer.toString(creditPoints) + "pt");
    }
}
