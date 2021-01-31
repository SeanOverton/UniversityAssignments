class TestCode{
    public static void main(String[] args){
        //test all methods of Time class
        Time time1 = new Time();
        System.out.println("Time object 1: " + time1);
        System.out.println();
        
        //test all methods of Alarm subclass
        System.out.println("Two Alarm objects created to test different constructors");
        Alarm alarm1 = new Alarm("Lunch time", 12, 0);
        Alarm alarm2 = new Alarm(12, 0);
        System.out.println("Alarm object 1: " + alarm1);
        System.out.println("Alarm object 2: " + alarm2);
        System.out.println("Tests set alarm name: ");
        alarm1.setAlarmName("Dinner time");
        System.out.println("New name of Alarm object 1: " + alarm1);
        System.out.println();

        //test all methods of Timer class   
        System.out.println("Three Timer objects created to test different constructors");     
        Timer timer1 = new Timer(10, 15, 30);
        Timer timer2 = new Timer(23, 12);
        Timer timer3 = new Timer(12);
        System.out.println("Timer object 1: " + timer1);
        System.out.println("Timer object 2: " + timer2);
        System.out.println("Timer object 3: " + timer3);
        System.out.println("Testing the multiple set timer methods which also tests methods in superclass");
        timer1.setTimer(8, 45, 30);
        System.out.println("Timer object 1: " + timer1);
        timer1.setTimer(10, 30);
        System.out.println("Timer object 1: " + timer1);
        timer1.start();
        timer1.stop();
        timer1.reset();

        System.out.println();
        System.out.println("All methods and constructors have been tested either explicitly or implicitly.");
    }
}

//superclass of timer and alarm
class Time{
    //private data fields/instance variables
    private int hour;
    private int minute;
    private int second;

    //constructors with various parameters and input validation
    public Time(int hour, int minute, int second){
        if(hour >= 0 && hour < 24){
            this.hour = hour;
        }
        if(minute >= 0 && minute < 60){
            this.minute = minute;
        }
        if(second >= 0 && second < 60){
            this.second = second;
        } 
    }

    public Time(int hour, int minute){
        this(hour, minute, 0);
    }

    public Time(int hour){
        this(hour, 0, 0);
    }

    //default constructor called when subclass object instantiated
    public Time(){
        this(0, 0, 0);
    }

    //public methods
    //accessors
    public int getHour(){
        return hour;
    }

    public int getMinute(){
        return minute;
    }

    public int getSecond(){
        return second;
    }

    //modifiers
    public void setHour(int hour){
        if(hour >= 0 && hour < 24){
            this.hour = hour;
        } 
    }

    public void setMinute(int minute){
        if(minute >= 0 && minute < 60){
            this.minute = minute;
        }
    }

    public void setSecond(int second){
        if(second >= 0 && second < 60){
            this.second = second;
        }
    }

    public String toString(){
        return Integer.toString(this.getHour()) + ":" + Integer.toString(this.getMinute()) + ":" + Integer.toString(this.getSecond());
    }
}

//subclasses of Time class
class Alarm extends Time{
    //datafields/instance variables 
    private String alarmName;

    //constructors with parameters
    public Alarm(String name, int hour, int min){
        super(hour, min);
        //uses super class' constructor
        //super should be called first
        this.alarmName = name;
    }

    public Alarm(int hour, int min){
        //calls this class' constructor with 3 parameters
        this("", hour, min);
    }

    //default constructor is not needed N/A as described in design

    //methods
    //accessor
    public String getAlarmName(){
        return alarmName;
    }
    
    //modifer
    public void setAlarmName(String name){
        this.alarmName = name;
    }

    //this overrides Time toString() method
    public String toString(){
        return this.getAlarmName() + " " + super.toString();
    }
}

class Timer extends Time{
    //constructors
    public Timer(int hour, int min, int second){
        super(hour, min, second);
        //calls superclasses constructors
    }

    public Timer(int hour, int min){
        super(hour, min);
    }

    public Timer(int hour){
        super(hour);
    }

    //modifier methods
    public void setTimer(int hour, int min, int sec){
        super.setHour(hour);
        super.setMinute(min);
        super.setSecond(sec);
        //superclass methods already has input validation too
    }
    
    public void setTimer(int hour, int min){
        super.setHour(hour);
        super.setMinute(min);
    }

    //these don't actually need to be implemented
    //can try though
    public void start(){
        System.out.println("Start method is called");
    }

    public void stop(){
        System.out.println("Stop method is called");
    }

    public void reset(){
        System.out.println("Reset method is called");
    }
}