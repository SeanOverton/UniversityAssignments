import java.util.Scanner;
import java.util.ArrayList;

//do whatever but as long as it tests all public methods
class TestCode{
    public static void main(String[] args){
        //create AlarmManagement object
        AlarmManagement controller = new AlarmManagement();

        //call addAlarmFromView()
        int count = 3;
        while(count > 0){
            controller.addAlarmFromView();
            controller.displayAlarmView();
            count--;
        }  
    }
}

class AlarmView{
    //returns names of alarms?
    //public methods
    public String[] userCreateAnAlarm(){
        //stores user inputs
        String[] userInputs = new String[3];

        //gathers user input
        Scanner input = new Scanner(System.in);

        //prompts for time
        System.out.println("Please enter a time for alarm (eg. 12:30): ");
        String time = input.nextLine();
        String[] times = time.split(":");
        //assigns hours, mins to userInputs
        userInputs[0] = times[0];
        userInputs[1] = times[1];
        
        //prompts for alarm name (optional?)
        System.out.println("Please enter a name for alarm (optional): ");
        String name = input.nextLine();
        
        if(name.equals("")){
            userInputs[2] = null;
        }
        else{
            userInputs[2] = name;
        }
        return userInputs;
    }
    
    public int getAlarmIndex(){
        Scanner input = new Scanner(System.in);

        //prompts for input
        System.out.print("Enter the index (NOTE: starts at zero) of the alarm you would like to view: ");
        
        //gets int input
        int alarmIndex = Integer.parseInt(input.nextLine());  
        
        return alarmIndex;
    }

    //display alarms using Alarm.toString()
    public void displayAlarm(String alarm){
        System.out.println(alarm);
    }
}

//potentially needs more methods added?
class AlarmManagement{
    //private datafields/instance variables
    private ArrayList<Alarm> alarms;
    private AlarmView view;

    //default constructor
    public AlarmManagement(){
        this.alarms = new ArrayList<Alarm>();
        this.view = new AlarmView();
    }

    //public methods
    public void addAlarm(Alarm alarm){
        alarms.add(alarm);
    }

    public Alarm getAlarmAt(int index){
        return alarms.get(index);
    } 

    public void addAlarmFromView(){
        String[] alarmInfo = view.userCreateAnAlarm();
        
        //need to convert to integer before instantiating Alarm object
        int hour = Integer.parseInt(alarmInfo[0]); 
        int min = Integer.parseInt(alarmInfo[1]); 
        
        //creates alarm object
        Alarm newAlarm;
        
        //checks if name attribute exists
        if(alarmInfo[2] != null){
            String name = alarmInfo[2]; 
            newAlarm = new Alarm(name, hour, min);
        }
        else{
            newAlarm = new Alarm(hour, min);
        }

        //adds alarm to the datafield
        addAlarm(newAlarm);
    }

    public void displayAlarmView(){
        int index = view.getAlarmIndex();
        
        Alarm alarm = this.getAlarmAt(index);

        //convert to string
        String strAlarm = alarm.toString();

        view.displayAlarm(strAlarm);
    }
}

class Alarm{
    //private datafields/instance variables
    private String alarmName;
    private int hour;
    private int min;

    //constructors with parameters with validation
    public Alarm(String name, int hour, int min){
        this.alarmName = name;
        if(hour < 24 && hour >= 0){
            //then it is valid and is set
            this.hour = hour;
        }
        if(min < 60 && min >= 0){
            //then it is valid and is set
            this.min = min;
        }
    }
    public Alarm(int hour, int min){
        this("", hour, min);
    }

    //default constructor
    public Alarm(){
        this("", 0, 0);
    }

    //public methods
    //accessor methods
    public String getAlarmName(){
        return alarmName;
    }
    public int getHour(){
        return hour;
    }

    public int getMin(){
        return min;
    }

    //modifier methods
    public void setAlarmName(String name){
        this.alarmName = name;
    }

    //validation checking occurs here too
    public void setHour(int hour){
        if(hour < 24 && hour >= 0){
            //then it is valid and is set
            this.hour = hour;
        }
    }

    public void setMin(int min){
        if(min < 60 && min >= 0){
            //then it is valid and is set
            this.min = min;
        }
    }

    //overrided toString method for printing instance object
    public String toString(){
        return getHour() + ":" + getMin() + "   " + getAlarmName();
    }
}
