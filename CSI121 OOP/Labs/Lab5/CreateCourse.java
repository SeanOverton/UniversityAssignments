/*------------------------------------------------------
My name: Sean Overton
My student number: 6421490
My course code: CSIT121
My email address: so412@uowmail.edu.au
Lab: #5 
-------------------------------------------------------*/

import java.util.ArrayList;
import java.util.Scanner;
import java.util.InputMismatchException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.NoSuchElementException;
import java.io.*;
import java.util.Formatter;
import java.util.FormatterClosedException;
import java.lang.SecurityException;


class CreateCourse{
    private static ObjectOutputStream output;

    public static void main(String[] args){
        /*i)) to create all subjects, majors, and courses based on the
         Bachelor of Computer Science*/
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
        Course compSci = new Course("Bachelor of Computer Science", 144);
            
            //add all majors to course
        Major[] majorList = {bigData, cyberSec, sysSecurity, softwareEng, 
        gameDev};
        compSci.addMajors(majorList);

            //add core subjects to Course
        Subject[] coreSubjects = {CSIT111, CSIT113, CSIT114, 
        CSIT115, CSIT121, CSIT127, CSIT128, CSCI235, CSCI251, CSIT214, 
        MATH221, CSCI203, CSIT226, CSIT314, CSIT321};
        compSci.addCores(coreSubjects);

            //add elective subjects to course
        Subject[] elecSubjects = {CSCI317, INFO411, CSCI316, ISIT312, CSCI301,
        CSCI262, CSCI369, CSIT302, CSCI361, CSCI368, CSCI376, CSCI236, CSCI336,
        CSCI356, CSCI366, CSCI334, CSCI318, ISIT219, ISIT315};
        compSci.addElectives(elecSubjects);

        //for bachelor course
        openFile("bcs.ser");
        addRecords(compSci);
        closeFile();

        //create masters course structure
        Subject CSCI814 = new Subject("CSCI814", "IT Porject Management", 6);
        Subject CSCI851 = new Subject("CSCI851", "Advanced Programming", 6);
        Subject CSCI803 = new Subject("CSCI803", "Algorithms and Data structures", 6);
        Subject CSCI835 = new Subject("CSCI835", "Database Systems", 6);
        Subject CSCI862 = new Subject("CSCI862", "System Security", 6);
        Subject CSIT826 = new Subject("CSIT826", "Human Computer Interaction", 6);
        Subject MTS9302 = new Subject("MTS9302", "Corporate Network Management", 6);
        Subject ISIT925 = new Subject("ISIT925", "Strategic Network Design", 6);
        Subject CSIT940 = new Subject("CSIT940", "Research Methodology", 6);
        Subject CSCI920 = new Subject("CSCI920", "Contemporary Topics in Computer Science", 6);
        Subject CSCI992 = new Subject("CSCI992", "Professional Project", 12);
        Subject CSCI964 = new Subject("CSCI964", "Computational IntellisGenderce", 6);
        Subject CSCI924 = new Subject("CSCI924", "Reasoning and Learning", 6);
        Subject CSCI944 = new Subject("CSCI944", "Perception and Planning", 6);
        Subject CSCI933 = new Subject("CSCI933", "Machine Learning Algorithms and Applications", 6);
        Subject CSCI935 = new Subject("CSCI935", "Computer Vision Algorithms and Systems", 6);
        Subject CSCI946 = new Subject("CSCI946", "Big Data Analytics", 6);
        Subject CSCI968 = new Subject("CSCI968", "Advanced Network Security", 6);
        Subject INFO912 = new Subject("INFO912", "Mathematics for cryptography", 6);
        Subject CSCI971 = new Subject("CSCI971", "Advanced Computer Security", 6);
        Subject CSCI910 = new Subject("CSCI910", "Software Requirements, Specifications and Formal Methods", 6);
        Subject CSCI926 = new Subject("CSCI926", "Software Testing and Analysis", 6);
        Subject CSCI927 = new Subject("CSCI927", "Service-orientated software engineering", 6);
        Subject ECTE903 = new Subject("ECTE903", "Image and Video Processing", 6);
        Subject INFO911 = new Subject("INFO911", "Data mining and Knowledge Discovery", 6);
        Subject INFO913 = new Subject("INFO913", "Information Theory", 6);

        Major intSys = new Major("Intelligent System");
        Subject[] intSysCores = {CSCI964, CSCI924, CSCI944};
        intSys.addMCores(intSysCores);

        Major macLearBD = new Major("Machine Learning and Big Data");
        Subject[] macLearBDCores = {CSCI964, CSCI924, CSCI944};
        macLearBD.addMCores(macLearBDCores);

        Major netInfoSec = new Major("Network and Information Security");
        Subject[] netInfoSecCores = {CSCI968, INFO912, CSCI971};
        netInfoSec.addMCores(netInfoSecCores);

        Major softEngM = new Major("Software Engineering");
        Subject[] softEngMCores = {CSCI964, CSCI924, CSCI944};
        softEngM.addMCores(softEngMCores);

        Major[] MCSMajors = {softEngM, intSys, netInfoSec, macLearBD};

        Subject[] cCoresM = {CSCI814, CSCI851, CSCI803, CSCI835, MTS9302, CSIT940, 
        CSCI920, CSCI992};

        Subject[] cElesM = {CSCI862, CSIT826, ISIT925, CSCI964, CSCI924,
        CSCI944, CSCI933, CSCI935, CSCI946, CSCI968, INFO912, CSCI971,
        CSCI910, CSCI926, CSCI927, ECTE903, INFO911, INFO913};

        Course mcs = new Course("Master of Computer Science", 96);
        mcs.addCores(cCoresM);
        mcs.addElectives(cElesM);
        mcs.addMajors(MCSMajors);

        //for master course
        openFile("mcs.ser");
        addRecords(mcs);
        closeFile();        
    }

    public static void openFile(String fileName){
        try{
            output = new ObjectOutputStream(Files.newOutputStream(Paths.get(fileName)));
        }
        catch(IOException ioException){
            System.err.println("Error opening file. Terminating.");
            System.exit(1); //terminates program?
        }
    }

    public static void addRecords(Course course){
        try{
            //for loop here to add all data
            output.writeObject(course);
        }
        catch(IOException ioException){
            System.err.println("Error writing to file. Terminating.");
        }
    }

    public static void closeFile(){
        try{
            if(output != null){
                output.close();
            }
        }
        catch(IOException ioException){
            System.err.println("Error closing file. Terminating.");
        }
    }
}

interface Enrolment{
    //abstract by default 
    public void addRecord(Record record);
    public void deleteRecord(int index);
    public Record getRecord(int index);
    public ArrayList<Record> getRecords();
    public void setRecords(ArrayList<Record> records);
}

class Subject implements Serializable{
    //dataprivate fields
    private String sName;
    private String code;
    private int credit;
    
    //default constructor
    public Subject(){
        this(" ", " ", 0);
    }

    //constructor with parameters
    public Subject(String code, String name, int creditPoint){
        this.sName = name;
        this.code = code;
        this.credit = creditPoint;
    }

    //methods
    public String getName(){
        return sName;
    }

    public String getCode(){
        return code;
    }
    
    public int getCredit(){
        return credit;
    }

    public boolean isSame(Subject subject){
        if (subject.getCode().equals(this.getCode())){
            return true;
        }
        else{
            return false;
        }
    }

    //implementing clone interface
    public Subject clone() throws CloneNotSupportedException{
        return (Subject) super.clone();
    }

    public String toString(){
        String printOut = getCode() +  " (" +  getName() + ", " + Integer.toString(getCredit()) + "pt)";
        return printOut;
    }
}

abstract class Student implements Enrolment, Cloneable{
    //datafields
    private String stName;
    private final String DOB;
    private ArrayList<Record> records;
    private final int STNUM;
    private String sGender;

    //constructors
    public Student(){
        this("x", "x", "x", 0);
    }

    public Student(String name, String DOB, String sGender, int STUDENTID){
        this.stName = name;
        this.DOB = DOB;
        this.sGender = sGender;
        this.STNUM = STUDENTID;
        this.records = new ArrayList<Record>();
    }

    //methods
    public String getSName(){
        return stName;
    }

    public void setSName(String stName){
        this.stName = stName;
    }

    public String getDOB(){
        return DOB;
    }

    public int getsNumberM(){
        return STNUM;
    }

    public String getsGender(){
        return sGender;
    }

    public void setsGender(String sGender){
        this.sGender = sGender;
    }

    public String toString(){
        return String.format("Student: %s (%s, %s, %s)\n",
            getSName(), Integer.toString(getsNumberM()), getsGender(), getDOB());
    }

    //implementing interface methods
    public void addRecord(Record record){
        records.add(record);
    }

    public void deleteRecord(int index){
        records.remove(index);
    }

    public Record getRecord(int index){
        return records.get(index);
    }

    public ArrayList<Record> getRecords(){
        return records;
    }

    public void setRecords(ArrayList<Record> records){
        this.records = records;
    }

    //implementing cloneabe method
    //this warning is fine and has been checked
    @Override
    @SuppressWarnings("unchecked")
    public Student clone() throws CloneNotSupportedException{
        Student studentClone = (Student) super.clone();
        studentClone.setRecords((ArrayList<Record>)this.getRecords().clone());
        return studentClone;
    }

    //empty abstract methods
    public abstract void setExpectedCompletion(String date);
    public abstract String getExpectedCompletion();
}

class Undergraduate extends Student{
    //datafields
    private String bachelorCompletion;

    //constructors
    public Undergraduate(){
        super();
        this.bachelorCompletion ="x";
    }

    public Undergraduate(String name, String DOB, int STUDENTID, String sGenderder, String expectedCompletionSession){
        super(name, DOB, sGenderder, STUDENTID);
        this.bachelorCompletion = expectedCompletionSession;
    }

    //methods
    public String getExpectedCompletion(){
        return bachelorCompletion;
    }

    public void setExpectedCompletion(String expectedCompletionSession){
        this.bachelorCompletion = expectedCompletionSession;
    }

    @Override
    public String toString(){
        String printOut = super.toString();
        for(int i = 0; i < getRecords().size(); i++){
            Record record = getRecord(i);
            printOut += String.format("\nCourse Name: %s, (%s) \n\n%s",
            record.getCourseName(), record.getStatus(), record);
        }

        return printOut;
    }
}

class Postgraduate extends Student{
    //datafields
    private String bachelorCompletion;
    private String masterCompletion;

    //constructors
    public Postgraduate(){
        super();
        this.bachelorCompletion ="x";
        this.masterCompletion = "x";
    }

    public Postgraduate(String name, String DOB, int STUDENTID, String sGenderder, 
    String bachelorCompletion, String masterCompletion){
        super(name, DOB, sGenderder, STUDENTID);
        this.bachelorCompletion = bachelorCompletion;
        this.masterCompletion = masterCompletion;
    }

    //methods
    public String getExpectedCompletion(){
        return masterCompletion;
    }

    public void setExpectedCompletion(String expectedCompletionSession){
        this.masterCompletion = expectedCompletionSession;
    }

    public String getBachelorCompletion(){
        return bachelorCompletion;
    }

    public void setBachelorCompletion(String bachelorCompletion){
        this.bachelorCompletion = bachelorCompletion;
    }

    @Override
    public String toString(){
        String printOut = super.toString();
        for(int i = 0; i < getRecords().size(); i++){
            Record record = getRecord(i);
            printOut += String.format("\n\nCourse Name: %s, (%s) \n\n%s",
            record.getCourseName(), record.getStatus(), record);
        }

        printOut += "\nExpected Master Graduation: " + this.getExpectedCompletion();
        // printOut += "\n\nBachelor was received: " + getBachelorCompletion();

        return printOut;
    }
}

class Record implements Cloneable{
    //datafields
    private final String CNAME;
    private ArrayList<Subject> eCores;
    private Major eMajor;
    private ArrayList<Subject> eElectives;
    private int totalCredit;
    private Status status;

    public enum Status {ACTIVE, COMPLETE, NA}

    //default constructor
    public Record(){
        this("default");
    }
    
    //parameterised constructor
    public Record(String CNAME){
        this.CNAME = CNAME;
        this.eCores = new ArrayList<Subject>();
        this.eMajor = new Major();
        this.eElectives = new ArrayList<Subject>();
        this.totalCredit = 0;
        this.status = Status.NA;
    }

    //methods
    public void enrolCores(ArrayList<Subject> subjects){
        //maintains up to date credit points
        for(int i=0; i < subjects.size(); i++ ){
            this.totalCredit += subjects.get(i).getCredit();
        }
        
        //assigns core subjects
        this.eCores = subjects;
    }

    public void enrolMajor(Major major){
        //mtaintains up to date credit point count
        this.totalCredit += major.getCredit();
        this.eMajor = major;
    }

    public void enrolElective(Subject subject){
        this.eElectives.add(subject);
        this.totalCredit += subject.getCredit();
    }

    public void enrolElectives(ArrayList<Subject> subjects){
        //maintains up to date credit points
        for(int i=0; i < subjects.size(); i++ ){
            this.totalCredit += subjects.get(i).getCredit();
        }
        
        //assigns core subjects
        this.eCores = subjects;
    }

    public int getTotalCredit(){
        return totalCredit;
    }

    public void setTotalCredit(int credit){
        this.totalCredit = credit;
    }

    /*calculates number of electives left to reach minimum credit 
    points for selected course*/
    public int howManyElectivesLeft(Course course){
        if(getTotalCredit() < course.getCCredit()){
            return ((course.getCCredit() - getTotalCredit())/6);
        }
        return 0;
    }

    //predicate method(just checks truth)
    public boolean isEnrolled(Subject name){
        if(eCores.contains(name)){
            return true;
        }
        else if(eElectives.contains(name)){
            return true;
        }
        else if(eMajor.isIncluded(name)){
            return true;
        }
        return false;
    }

    public Status getStatus(){
        return this.status;
    }

    public void setStatus(Status status){
        this.status = status;
    }
    
    public String toString(){      
        String s = "";  
        
        s+="Cores: \n";
        for(int i = 0; i < eCores.size(); i++)
            s+=eCores.get(i)+"\n";
        
        s+="\n";
        
        s+="Major: " + eMajor;
        
        s+="\n";

        s+="Electives: \n";
        for(int i = 0; i < eElectives.size(); i++)
            s+=eElectives.get(i)+"\n";
        
        s+="-----------------\n";
        
        s+="Total Enrolled Credit: "+ getTotalCredit() +"pt";
        
        return s;
    }

    public String getCourseName(){
        return CNAME;
    }

    //implementing cloneable interface
    @SuppressWarnings("unchecked")
    public Record clone() throws CloneNotSupportedException{
        Record recordClone = (Record) super.clone();
        //clears credit points
        recordClone.setTotalCredit(0);

        //these methods all add relevent credit points 
        recordClone.enrolMajor((Major)this.eMajor.clone());
        recordClone.enrolElectives((ArrayList<Subject>)this.eElectives.clone());
        recordClone.enrolCores((ArrayList<Subject>)this.eCores.clone());

        return recordClone;
    }
}

class Major implements Cloneable, Serializable{
    private String mName;
    private ArrayList<Subject> mCores;
    private int credit = 0;

    //default constructor 
    public Major(){
        this("default");
    }

    //constructor with 1 parameter
    public Major(String name){
        this.mName = name;
        this.mCores = new ArrayList<Subject>();
    }

    //constructor with 2 parameters
    public Major(String name, Subject[] subjectList){
        this.mName = name;
        this.mCores = new ArrayList<Subject>();
        addMCores(subjectList);
    }

    public String getMName(){
        return mName;
    }
     
    public void setMName(String name){
        this.mName = name;
    }

    public int getCredit(){
        int credit = 0;

        for(int i = 0; i < mCores.size(); i++ ){
            credit += mCores.get(i).getCredit();
        }

        return credit;
    }

    //predicate method
    public boolean isIncluded(Subject name){
        ArrayList<Subject> subjects = this.getMCores();

        for(int i = 0; i < subjects.size(); i++ ){
            if(subjects.get(i).getName() == name.getName()){
                return true;
            }
        }
        return false;
    }

    public void addMCores(Subject[] subjects){
        for (Subject subject : subjects){
            this.mCores.add(subject);
        }
    }

    public ArrayList<Subject> getMCores(){
        return mCores;
    }

    //implementing clone interface
    @Override
    public Major clone() throws CloneNotSupportedException{
        return (Major) super.clone();
    }

    @Override
    public String toString(){
        String printOut = getMName() + "\n";
        ArrayList<Subject> subjects = getMCores();
        
        for(int i = 0; i < subjects.size(); i++ ){
            printOut += subjects.get(i) + "\n";
        }

        return printOut;
    }
}

class Course implements Serializable{
    private String cName;
    private ArrayList<Subject> cores;
    private ArrayList<Major> majors;
    private ArrayList<Subject> electives;
    private int cCredit;

    public Course(){
        this("default", 0);
    }

    public Course(String cName, int cCredit){
        this.cName = cName;
        this.cores = new ArrayList<Subject>();
        this.majors = new ArrayList<Major>();
        this.electives = new ArrayList<Subject>();
        this.cCredit = cCredit;
    }

    public void addCores(Subject[] subjects){
        for (Subject subject : subjects){
            this.cores.add(subject);
        }
    }

    public void addMajors(Major[] majors){
        for (Major major : majors){
            this.majors.add(major);
        }
    }

    public void addElectives(Subject[] electives){
        for (Subject elective : electives){
            this.electives.add(elective);
        }
    }

    //display all electives
    public void printElectives(){
        System.out.println("Elective Subjects:");
        for(int i = 0; i < getElectives().size(); i++){
            System.out.println(getElectives().get(i));
        }
        System.out.println();
    }

    public ArrayList<Subject> getCores(){
        return cores;
    }

    public ArrayList<Subject> getElectives(){
        return electives;
    }

    public ArrayList<Major> getMajors(){
        return majors;
    }
    
    public String toString(){
        String s = "Course: "+getCName() + "\n";  
        
        s+="Cores: \n";
        for(int i = 0; i < getCores().size(); i++)
            s+=getCores().get(i)+"\n";
        
        s+="\n";
        
        s+="Majors: \n";
        for(int i = 0; i < getMajors().size(); i++){
            s+=getMajors().get(i) + "\n";
        }
        
        s+="\n";

        s+="Electives: \n";
        for(int i = 0; i < getElectives().size(); i++)
            s+=getElectives().get(i)+"\n";
        
        s+="-----------------\n";
        
        s+="Required Total Credit: "+ getCCredit() +"pt";
        
        return s;
    }

    public String getCName(){
        return cName;
    }

    public int getCCredit(){
        return cCredit;
    }
}

class InputOutOfRange extends Exception{
    //datafields
    private String range;

    //parameterised constructor
    public InputOutOfRange(String range){
        this.range = range;
    }

    public String getMessage(){
        if(range != null){
            return String.format("Input out of range. Should be between %s", getRange());
        }
        else{
            return "Input out of range.";
        }
    }

    public String getRange(){
        return this.range;
    }
}