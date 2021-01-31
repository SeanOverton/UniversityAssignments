/*------------------------------------------------------
My name: Sean Overton
My student number: 6421490
My course code: CSIT121
My email address: so412@uowmail.edu.au
Assignment number: 3
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
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.JButton;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import java.util.List;


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

    public void clearMajor(){
        //resets credit
        this.totalCredit -= this.eMajor.getCredit();

        //removes them all
        this.eMajor = null;
    }

    public void clearElectives(){
        //resets credit
        for(int i = 0; i < eElectives.size(); i++){
            this.totalCredit -= this.eElectives.get(i).getCredit();
        }
        //removes them all
        this.eElectives.clear();
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
        // + "\n";
        // ArrayList<Subject> subjects = getMCores();
        
        // for(int i = 0; i < subjects.size(); i++ ){
        //     printOut += subjects.get(i) + "\n";
        // }

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

class SEFrame extends JFrame{
    private BorderLayout layout;
    private TopPanel topPanel;
    private CentrePanel centrePanel;
    private BottomPanel bottomPanel;
    private Course bachCourse;
    private Course mastCourse;
    private static ObjectInputStream fileInput; 
    private static Formatter output;
    private Record record;
    private static ArrayList<Student> students = new ArrayList<Student>();

    public static void main(String[] args){
        SwingUtilities.invokeLater(new Runnable() {  //Note 1
            public void run() {
                SEFrame mainFrame = new SEFrame();
                mainFrame.setVisible(true); 
            }
        }); 
    }

    public SEFrame(){
        super("Student Enrolment System");

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);

        layout = new BorderLayout(5, 5);
        setLayout(layout);

        //deserialise bachelor course object
        openFile("bcs.ser");
        bachCourse = readRecords();
        closeFile();

        //deserialise master course object
        openFile("mcs.ser");
        mastCourse = readRecords();
        closeFile(); 

        //creates panels
        centrePanel = new CentrePanel();
        topPanel = new TopPanel();
        bottomPanel = new BottomPanel();

        //adds panels to frame
        add(topPanel, BorderLayout.NORTH);
        add(centrePanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    private class TopPanel extends JPanel{
        private final GridLayout layout;
        private InputPanel sNameInput;
        private InputPanel sNumInput;
        private InputPanel sDOBInput;
        private GenderPanel sGenInput;

        public TopPanel(){
            layout = new GridLayout(3, 2);
            setLayout(layout);

            //create buttons
            JButton bachButton = new JButton("Bachelor of Computer Science Course Structure");
            JButton mastButton = new JButton("Master of Computer Science Course Structure");

            //create and add handlers to button
            CourseButtonHandler bachButtonHandler = new CourseButtonHandler(bachCourse);
            CourseButtonHandler mastButtonHandler = new CourseButtonHandler(mastCourse);
            bachButton.addActionListener(bachButtonHandler);
            mastButton.addActionListener(mastButtonHandler);

            //initialise the datafield input panels
            sNameInput = new InputPanel("Student Name:");
            sNumInput = new InputPanel("Student Number:");
            sGenInput = new GenderPanel();
            sDOBInput = new InputPanel("DOB:");

            //add components to the panel for display
            add(sNameInput);
            add(sNumInput);
            add(sGenInput);
            add(sDOBInput);
            add(bachButton);
            add(mastButton);
        }

        //button handler that allows for display of courses
        private class CourseButtonHandler implements ActionListener{
            private Course course;
            
            public CourseButtonHandler(Course course){
                this.course = course;
            }

            @Override
            public void actionPerformed(ActionEvent event){
                //display course structure in lists
                //somehow needs access to "centrePanel.setCourse(this.course)"
                centrePanel.setCourse(this.course);
                record = new Record(this.course.getCName());

                // //iii) to automatically enrol the student to all core subjects;
                record.enrolCores(this.course.getCores());
                
                //updates displayed credit points
                centrePanel.updateCreditPoints();
            }
        }

        public String[] getInputs(){
            String sName = sNameInput.getValue();
            String sNum = sNumInput.getValue();
            String sGen = sGenInput.getValue();
            String sDOB = sDOBInput.getValue();

            String[] inputs = new String[]{sName, sNum, sGen, sDOB};
            return inputs;
        }

        public void reset(){
            sNameInput.reset();
            sNumInput.reset();
            sDOBInput.reset();
        }
    }

    private class BottomPanel extends JPanel{
        private final FlowLayout layout;

        public BottomPanel(){
            layout = new FlowLayout();
            setLayout(layout);
            
            //create enrol button
            JButton enrolButton = new JButton("Enrol");
            enrolButton.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent event){
                    enrol();
                }
            });

            //create reset button
            JButton resetButton = new JButton("Reset");
            resetButton.addActionListener(new ActionListener(){
                
                @Override
                public void actionPerformed(ActionEvent event){
                    reset();
                }

            });

            add(enrolButton);
            add(resetButton);
        }
    }

    private class CentrePanel extends JPanel{
        private final GridLayout layout;
        private JLabel cName = new JLabel("Course Name:");
        private JLabel totalCredit = new JLabel("Total Credit:");
        private DefaultListModel<Subject> cores = new DefaultListModel<Subject>();
        private DefaultListModel<Major> majors = new DefaultListModel<Major>();
        private DefaultListModel<Subject> majorSubs = new DefaultListModel<Subject>();
        private DefaultListModel<Subject> electives = new DefaultListModel<Subject>();
        private JList<DefaultListModel> majorList;
        private JList<DefaultListModel> coreList;
        private JList<DefaultListModel> electiveChoice;
        private JList<DefaultListModel> majorSubsList;
        private JLabel enrolledCredit = new JLabel("Enrolled credit:");
        private Course course; 
        private MajorSelectionListener majorSelectionListener = new MajorSelectionListener();

        //this is alright because I have looked at what is warning and am happy to use this decorator
        @SuppressWarnings("unchecked")
        public CentrePanel(){
            layout = new GridLayout(11, 1);
            setLayout(layout);
            
            add(cName);
            add(totalCredit);
            add(new JLabel("Core subjects:"));

            //sets up core list with scroll bar and model
            coreList = new JList(cores);
            add(new JScrollPane(coreList));

            add(new JLabel("Major: "));

            majorList = new JList(majors);
            majorList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            
            majorList.addListSelectionListener(majorSelectionListener);

            add(new JScrollPane(majorList));

            add(new JLabel("Major subjects:"));

            majorSubsList = new JList(majorSubs);
            
            add(new JScrollPane(majorSubsList));

            add(new JLabel("Elective subjects:"));
            
            //sets up elective list with multiple selections
            electiveChoice = new JList(electives);
            electiveChoice.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
            electiveChoice.addListSelectionListener(
                new ListSelectionListener(){
                    
                    @Override
                    public void valueChanged(ListSelectionEvent event){
                            getRecord().clearElectives();
                            List subjects = electiveChoice.getSelectedValuesList();
                            //adds electives to record
                            for(int i = 0; i < subjects.size(); i++){
                                //cast object to subject
                                Subject subject = (Subject)subjects.get(i);
                                if(!getRecord().isEnrolled(subject)){
                                    //enrols elective
                                    getRecord().enrolElective(subject);
                                }    
                            } 

                            //updates credit points that are displayed
                            centrePanel.updateCreditPoints();
                       
                        return;
                    }
                }
            );
            add(new JScrollPane(electiveChoice));

            add(enrolledCredit);
        }

        //create listselectionlistener with ability to turn off/on
        private class MajorSelectionListener implements ListSelectionListener{

            private boolean active = true;
                
            public void setActive(boolean active){
                this.active = active;
            }

            //what the actual task is on action performed
            protected void doPerformAction(ListSelectionEvent event){
                int num = majorList.getSelectedIndex();
                            
                //updates major subjects lists depending on what was selected
                centrePanel.setMajorSubs(centrePanel.getCourse().getMajors().get(num));
                            
                //removes old major and adds new major to record
                getRecord().clearMajor();
                getRecord().enrolMajor(centrePanel.getCourse().getMajors().get(num));
                            
                //updates electives that are displayed
                centrePanel.updateElectives();

                //updates credit points that are displayed
                centrePanel.updateCreditPoints();                    
            }

            @Override
            public final void valueChanged(ListSelectionEvent event){
                if(this.active){
                    doPerformAction(event);
                }
            }
        }

        //this method used when button is pressed to show course structure
        public void setCourse(Course course){
            this.course = course;

            this.cName.setText("Course Name: " + course.getCName());
            this.totalCredit.setText("Total Credit: " + course.getCCredit());

            int i = 0;
            this.cores.removeAllElements();
            this.majors.removeAllElements();
            this.electives.removeAllElements();

            //updates JList model which should automatically update whats displayed
            for(i = 0; i < course.getCores().size(); i++){
                this.cores.addElement(course.getCores().get(i));
            }

            for(i = 0; i < course.getMajors().size(); i++){
                this.majors.addElement(course.getMajors().get(i));
            }

            for(i = 0; i < course.getElectives().size(); i++){
                this.electives.addElement(course.getElectives().get(i));
            }
        }

        public Course getCourse(){
            return this.course;
        }

        public void setMajorSubs(Major major){
            //clear displayed subjects first
            this.majorSubs.removeAllElements();

            //update with new ones otherwise just adds to the list
            ArrayList<Subject> majors = major.getMCores(); 

            for(int i = 0; i < majors.size(); i++){
                this.majorSubs.addElement(majors.get(i));
            }
        }

        public void updateElectives(){
            //resets
            electives.removeAllElements();

            //updates electives after major is selected
            for(int i = 0; i < course.getElectives().size(); i++){
                if(!record.isEnrolled(course.getElectives().get(i))){
                    electives.addElement(course.getElectives().get(i));
                }
            }
        }

        //required so errors with action threads can be avoided
        private void reset(){
            cName.setText("Course Name:");
            totalCredit.setText("Total Credit:");
            cores.removeAllElements();
            electives.removeAllElements(); 
            majorSubs.removeAllElements();  
            
            /*need to turn off this jlist's action listener
            before we remove elements otherwise action is 
            continually called and bugs occur*/ 
            majorSelectionListener.setActive(false);

            majors.removeAllElements();       
            course = null; 
            
            /*need to turn the list selection listener back on after 
            the removals complete*/
            majorSelectionListener.setActive(true);

            enrolledCredit.setText("Enrolled Credit:");
        }

        public void updateCreditPoints(){
            this.enrolledCredit.setText("Enrolled Credit: " + getRecord().getTotalCredit());
        }
    }

    private class InputPanel extends JPanel{
        private final FlowLayout layout;
        JTextField input;

        public InputPanel(){
            this("default");
        }

        public InputPanel(String type){
            layout = new FlowLayout();
            setLayout(layout);

            input = new JTextField(25);
            add(new JLabel(type));
            add(input);
        }    

        public String getValue(){
            return this.input.getText();
        }  

        public void reset(){
            input.setText("");
        }
    } 

    private class GenderPanel extends JPanel{
        //datafields 
        private final FlowLayout layout;
        private final ButtonGroup buttonGroup;
        JRadioButton[] buttons;

        public GenderPanel(){
            layout = new FlowLayout();
            add(new JLabel("Gender: "));
            buttonGroup = new ButtonGroup();
            JRadioButton maleButton = new JRadioButton("male");
            JRadioButton femaleButton = new JRadioButton("female");
            
            //maintins array of options
            buttons = new JRadioButton[]{maleButton, femaleButton};

            //adds buttons to the actual panel for display
            add(maleButton);
            add(femaleButton);

            //adds buttons to button group
            buttonGroup.add(maleButton);
            buttonGroup.add(femaleButton);  
        }   

        //returns the selected radio button
        public String getValue(){
            String selected = "";

            //loops through array of button and returns text if selected
            for(JRadioButton button : buttons){
                if(button.isSelected()){
                    selected = button.getText();
                }
            }

            return selected;
        }               
    }

    public Record getRecord(){
            return this.record;
    }

    public void reset(){
        centrePanel.reset();
        topPanel.reset();

        /*clears previous record info because otherwise might not
        be displayed but could still exist on enrolment*/
        record = new Record("default");

        JOptionPane.showMessageDialog(SEFrame.this, String.format(
            "Reset was successful!"
            ));
    }

    public void enrol(){
        boolean valid = false;

        //used to gather input from user/student with validation checks
        Scanner input = new Scanner(System.in);
        String sName = "default";
        String sGender = "default";
        String sDOB = "default";
        int sNumber = 0;
        String number = "";

        try{
            sName = topPanel.getInputs()[0];

            if(sName.equals("")){
                throw new InputMismatchException();
            }
            valid = true;
        }
        catch(InputMismatchException inputMismatchException){  
            JOptionPane.showMessageDialog(SEFrame.this, String.format(
                        "Blank Input not accepted. Please enter valid full name."
                    ), "Invalid Name!", JOptionPane.ERROR_MESSAGE);
            return;    
        }
        catch(Exception e){
            //catches all other unforseen exceptions
            JOptionPane.showMessageDialog(SEFrame.this, String.format(
                        "Error thrown %s.\nPlease enter valid full name.",
                        e
                    ), "Invalid Name!", JOptionPane.ERROR_MESSAGE); 
            return;
        }

        try{
            number = topPanel.getInputs()[1];
            sNumber = Integer.parseInt(number);

            if(sNumber < 10000 || sNumber >=100000){
                throw new InputOutOfRange("10000-99999");
            }
            valid = true;
        }       
        catch(NumberFormatException e){
            if(number.equals("")){
                JOptionPane.showMessageDialog(SEFrame.this, String.format(
                        "Blank Input not accepted. Please enter valid integer."
                    ), "Invalid Student Number!", JOptionPane.ERROR_MESSAGE);
            }
            else{
                JOptionPane.showMessageDialog(SEFrame.this, String.format(
                        "Invalid datatype. \nPlease enter valid integer input."
                    ), "Invalid Student Number!", JOptionPane.ERROR_MESSAGE);
            }
            return;
        }
        catch(InputMismatchException inputMismatchException){
            if(inputMismatchException.getMessage() != null){
                JOptionPane.showMessageDialog(SEFrame.this, String.format(
                    inputMismatchException.getMessage() + "\n Please enter valid student number."
                    ), "Invalid Student Number!", JOptionPane.ERROR_MESSAGE);                   
            }
            else{
                JOptionPane.showMessageDialog(SEFrame.this, String.format(
                    "Invalid type.\n Please enter valid student number."
                    ), "Invalid Student Number!", JOptionPane.ERROR_MESSAGE);   
            }  
            return;     
        }
        catch(InputOutOfRange inputOutOfRange){
            JOptionPane.showMessageDialog(SEFrame.this, String.format(
                    inputOutOfRange.getMessage() + "\nPlease enter valid student number."
                    ), "Invalid Student Number!", JOptionPane.ERROR_MESSAGE); 
            return;
        }
        catch(Exception e){
            //catches all other unforseen exceptions
            JOptionPane.showMessageDialog(SEFrame.this, String.format(
                    "Error thrown: %s\nPlease enter valid student number.", e
                    ), "Invalid Student Number!", JOptionPane.ERROR_MESSAGE); 
            return;
        }

        try{
            sGender = topPanel.getInputs()[2];
                
            if(sGender.equals("")){
                throw new InputMismatchException("Blank input not accepted.");
            }
            else{
                //asertion used to enforce input constraints
                sGender = sGender.toLowerCase();
            }  
            valid = true; 
        }
        catch(InputMismatchException inputMismatchException){
            if(inputMismatchException.getMessage() != null){
                JOptionPane.showMessageDialog(SEFrame.this, String.format(
                "%s\nPlease enter valid gender.", inputMismatchException.getMessage()
                ), "Invalid Gender!", JOptionPane.ERROR_MESSAGE);                   
            }
            else{
                JOptionPane.showMessageDialog(SEFrame.this, String.format(
                "Please enter valid gender."
                ), "Invalid Gender!", JOptionPane.ERROR_MESSAGE);
            }  
            return;        
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(SEFrame.this, String.format(
            "Error thrown: %s\nPlease enter valid gender.", e
            ), "Invalid Gender!", JOptionPane.ERROR_MESSAGE); 
            return;
        }

        try{
            sDOB = topPanel.getInputs()[3];
                
            if(sDOB.equals("")){
                throw new InputMismatchException("Blank input not accepted.");
            }
            String[] subjects = sDOB.split("/");
                
            boolean isValid = false;
            int y = Integer.parseInt(subjects[2]);
            int m = Integer.parseInt(subjects[1]);
                
            /*arrayList contains method requires integer object instead of 
            primitive type int*/
            Integer intM = new Integer(m);
            int d = Integer.parseInt(subjects[0]);

            ArrayList<Integer> monthArray31 = new ArrayList<Integer>();
            monthArray31.add(1);
            monthArray31.add(3);
            monthArray31.add(5);
            monthArray31.add(7);
            monthArray31.add(8);
            monthArray31.add(10);
            monthArray31.add(12);
            ArrayList<Integer> monthArray30 = new ArrayList<Integer>();
            monthArray30.add(4);
            monthArray30.add(6);
            monthArray30.add(9);
            monthArray30.add(11);
                
            boolean isLeapYear = false;

            //checks whether the year is a leap year
            if (y % 4 != 0) 
                isLeapYear = false; 
                // not divisible by 4
            else if (y % 100 != 0) 
                isLeapYear = true; 
                // not divisible by 100 
            else if (y % 400 != 0) 
                isLeapYear = false; 
                // not divisible by 400 
            else 
                isLeapYear = true; 

            //checks if leap year feb has valid day
            if (isLeapYear && m == 2 && d <= 29 && d > 0 && y > 999){
                isValid = true;
            }
            else if (d > 0 && y > 999){
                //otherwise checks if any month has valid day
                if (monthArray31.contains(intM) && d <= 31){
                    isValid = true;          
                }
                else if (monthArray30.contains(intM) && d <= 30){
                    isValid = true;
                }
                else if (m == 2 && d <= 28){
                    isValid = true;
                }                           
            }

            if(!isValid){
                throw new InputMismatchException("The date entered has invalid numbers.");
            }
            valid = true;
        }
        catch(InputMismatchException inputMismatchException){
            if(inputMismatchException.getMessage() != null){
                JOptionPane.showMessageDialog(SEFrame.this, String.format(
                "%s\nPlease enter a valid date(dd/mm/yyyy).", inputMismatchException.getMessage()
                ), "Invalid Date of Birth!", JOptionPane.ERROR_MESSAGE);                    
            }
            else{
                JOptionPane.showMessageDialog(SEFrame.this, String.format(
                "Invalid datatype. \nPlease enter a valid date(dd/mm/yyyy)."
                ), "Invalid Date of Birth!", JOptionPane.ERROR_MESSAGE);
            }  
            return;        
        }
        catch(ArrayIndexOutOfBoundsException e){
            JOptionPane.showMessageDialog(SEFrame.this, String.format(
            "Invalid date format. Must be (dd/mm/yyyy).\nPlease enter a valid date(dd/mm/yyyy)."
            ), "Invalid Date of Birth!", JOptionPane.ERROR_MESSAGE);
            return;
        }
        catch(NumberFormatException numberFormatException){
            JOptionPane.showMessageDialog(SEFrame.this, String.format(
            "Invalid number format. Must be (dd/mm/yyyy).\nPlease enter a valid date(dd/mm/yyyy)."
            ), "Invalid Date of Birth!", JOptionPane.ERROR_MESSAGE);
            return;
        }
        catch(Exception e){
            //catches all other unforseen exceptions
            JOptionPane.showMessageDialog(SEFrame.this, String.format(
            "Error thrown: %s\nPlease enter a valid date(dd/mm/yyyy).", e
            ), "Invalid Date of Birth!", JOptionPane.ERROR_MESSAGE); 
            return;          
        }

        //using above gathered information student object created
        Student newStudent1 = new Undergraduate(sName, sDOB, sNumber, sGender, "Spring/2023");
        if(getRecord().howManyElectivesLeft(centrePanel.getCourse()) < 1){
            //adds record to student and adds student to ArrayList
            getRecord().setStatus(Record.Status.ACTIVE);
            newStudent1.addRecord(getRecord()); 
            students.add(newStudent1);  
                    
            //saves in text file
            openTextFile(topPanel.getInputs()[0], getRecord().getCourseName());
            saveRecords();
            closeTextFile();

            JOptionPane.showMessageDialog(SEFrame.this, String.format(
            "Enrolment was successful. Text file generated."
            ));
        }
        else{
            JOptionPane.showMessageDialog(SEFrame.this, String.format(
            "Not enough electives have been selected.\n Please enrol in at least %d more subjects",
            getRecord().howManyElectivesLeft(centrePanel.getCourse())
            ), "Credit points too low!", JOptionPane.ERROR_MESSAGE);   
        }
    }

    public static void openFile(String fileName){
        try{
            fileInput = new ObjectInputStream(Files.newInputStream(Paths.get(fileName)));
        }
        catch(IOException ioException){
            System.err.println("Error opening file. Terminating.");
            System.exit(1); //terminates program?
        }
    }

    public static void closeFile(){
        if(output != null){
            output.close();
        }
    }

    public static Course readRecords(){
        try{
            //reads object from file and casts as course
            Course course = (Course)fileInput.readObject(); 

            return course;
        }
        catch(EOFException eofException){
            System.err.printf("%No more Records%n");
        }
        catch(ClassNotFoundException classNotFoundException){
            System.err.println("Invalid object type. Terminating.");
        }
        catch(IOException ioException){
            System.err.println("Error reading file. Terminating.");
        }

        return null;
    }

    public static void openTextFile(String sName, String cName){
        try{  
            output = new Formatter(sName + "_" + cName + ".txt");
        }
        catch(SecurityException e){
            System.err.println("Write Permission Denied. Terminating.");
            System.exit(1); //terminates 
        }
        catch(FileNotFoundException e){
            System.err.println("Error opening file. Terminating.");
            System.exit(1); //terminates 
        }
    }

    public static void saveRecords(){
        try{
            for(int i = 0; i < students.size(); i++){
                //saves strings to text file
                
                Student student = students.get(i);
                /*g) to use the instanceof and down casting in the for loop above to show the bachelor
                completion time for the Postgraduate student (calling the specific getBachelorCompletion()
                method defined in the Postgraduate class).*/
                if(student instanceof Undergraduate){
                    output.format(student.toString());
                }
                else if(student instanceof Postgraduate){
                    output.format(student.toString() + "\n");

                    //downcasting so we can call specific method from subclass
                    Postgraduate gradStudent = (Postgraduate) student;
                    
                    output.format("\nBachelor Degree was received (using downcasting): " + gradStudent.getBachelorCompletion());
                }
            }
        }
        catch(FormatterClosedException e){
            System.err.println("Formatter closed. Terminating.");
        }
    }

    public static void closeTextFile(){
        if(output != null){
            output.close();
        }
    }
}