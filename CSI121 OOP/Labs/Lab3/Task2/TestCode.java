class TestCode{
    public static void main(String[] args) throws CloneNotSupportedException{
        //create PHD object and Undergraduate object
        PostCode postcode = new PostCode("Figtree", "Wollongong", "NSW", 2527);
        Address address = new Address("47", "Georgia Street", postcode, "Australia");
        PHD phdStudent = new PHD("89764523", "Jake", "Rake", 
    address, 42678123, "8793223", "Bachelor of Computer Science", "12/12/2015",
    "Masters in Electrical Engineering", "12/12/2018");
        UnderGrad undergradStudent = new UnderGrad("1234567", "John", "Smith", address, 
        42356728, "12345687", true, 93.3);

        System.out.println("PHD Student: ");
        System.out.println(phdStudent);
        System.out.println();
        System.out.println("Undegrad Student: ");
        System.out.println(undergradStudent);

        //test deep clone methods of each
        Student phdStudentClone = phdStudent.clone();
        Student undergradStudentClone = undergradStudent.clone();
        
        System.out.println();
        System.out.println("New clones:");
        System.out.println("PHD Student: ");
        System.out.println(phdStudentClone);
        System.out.println();
        System.out.println("Undegrad Student: ");
        System.out.println(undergradStudentClone);

        //making changes to original objects
        address.setStreetNumber("798");
        address.setStreetName("Melon Drive");
        address.setCountry("New Zealand");
        postcode.setPostCode(1234);

        //printing original object with changes vs new clone with no changes
        System.out.println();
        System.out.println("Original object vs clone after changes to address object:");
        System.out.println("PHD Student: ");
        System.out.println(phdStudent);
        System.out.println();
        System.out.println("PHD Student clone: ");
        System.out.println(phdStudentClone);
        System.out.println();
        System.out.println("Undegrad Student: ");
        System.out.println(undergradStudent);
        System.out.println();
        System.out.println("Undergrad clone: ");
        System.out.println(undergradStudentClone);

        System.out.println();
        System.out.println("That is, the clone has been deep cloned so \nchanges to original address and postcode objects do not \naffect deep clone.");
    }
}

interface UNIAccountInfo{
    //empty methods
    public String getFirstName();
    public String getLastName();
    public String getContactInfo();
    public String getID();
}

class Alumni implements UNIAccountInfo{
    //data-fields
    private String ID;
    private String firstName;
    private String lastName;
    private String email;

    //constructors
    public Alumni(){
        this.ID = " ";
        this.firstName = " ";
        this.lastName = " ";
        this.email = " ";
    }

    public Alumni(String ID, String firstName, String lastName, String email){
        this.ID = ID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }
    
    //methods
    public String getFirstName(){
        return firstName;
    }

    public String getLastName(){
        return lastName;
    }

    public String getContactInfo(){
        return "Email: " + email;
    }

    public String getID(){
        return ID;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void setID(String ID){
        this.ID = ID;
    }

    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    public void setLastName(String lastName){
        this.lastName = lastName;
    }

    @Override
    public Alumni clone() throws CloneNotSupportedException{
        return (Alumni)super.clone();
    }

    @Override
    public String toString(){
        return String.format("ID: %s \n First name: %s \n Last name: %s \n %s", getID(), getFirstName(), getLastName(), getContactInfo());
    }
}

class Address implements Cloneable{
    //datafields
    private String street_number;
    private String street_name;
    private PostCode postcode;
    private String country;

    //default constructor
    public Address(){
        this.street_number = "0";
        this.street_name = " ";
        this.postcode = new PostCode();
        this.country = " ";
    }

    //parameterised constructor
    public Address(String street_number, String street_name, PostCode postcode, String country){
        this.street_number = street_number;
        this.street_name = street_name;
        this.postcode = postcode;
        this.country = country;
    }

    public String getStreetNumber(){
        return street_number;
    }

    public String getStreetName(){
        return street_name;
    }

    public PostCode getPostCode(){
        return postcode;
    }

    public String getCountry(){
        return country;
    }

    public void setStreetNumber(String street_number){
        this.street_number = street_number;
    }

    public void setStreetName(String street_name){
        this.street_name = street_name;
    }

    public void setPostCode(PostCode postCode){
        this.postcode = postCode;
    }

    public void setCountry(String country){
        this.country = country;
    }

    //deep clone
    @Override
    public Address clone() throws CloneNotSupportedException{
        //shallow clone
        Address newAddressObj = (Address) super.clone();

        //making it a deep clone by cloning objects within object Address
        newAddressObj.setPostCode((PostCode)this.getPostCode().clone());

        return newAddressObj;
    }

    //methods
    @Override
    public String toString(){
        return String.format("Address: %s %s, %s, %s", getStreetNumber(), 
        getStreetName(), getPostCode(), getCountry());
    }
}

class PostCode implements Cloneable{
    //datafields
    private String suburb;
    private String city;
    private String state;
    private int postcode;
    
    //default constructor
    public PostCode(){
        this.suburb = " ";
        this.city = " ";
        this.state = " ";
        this.postcode = 0;
    }

    //parameterised constructor
    public PostCode(String suburb, String city, String state, int postcode){
        this.suburb = suburb;
        this.city = city;
        this.state = state;
        this.postcode = postcode;
    }
    
    //methods
    public String getSuburb(){
        return suburb;
    }

    public String getCity(){
        return city;
    }

    public String getState(){
        return state;
    }

    public int getPostCode(){
        return postcode;
    }

    public void setSuburb(String suburb){
        this.suburb = suburb;
    }

    public void setCity(String city){
        this.city = city;
    }

    public void setState(String state){
        this.state = state;
    }

    public void setPostCode(int postcode){
        this.postcode = postcode;
    }

    //implementing cloneable interface
    @Override
    public PostCode clone() throws CloneNotSupportedException{
        return (PostCode) super.clone();
    }

    @Override
    public String toString(){
        return Integer.toString(getPostCode());
    }
}

abstract class Student implements UNIAccountInfo, Cloneable{
    //datafields
    private String stuID;
    private String firstName;
    private String lastName;
    private Address address;
    private double mobileNo;
    private String identificationNo;
    
    //default constructor 
    public Student(){
        this.stuID = " ";
        this.firstName = " ";
        this.lastName = " ";
        this.address = new Address();
        this.mobileNo = 0;
        this.identificationNo = "0";
    }

    //paramterised constructor
    public Student(String stuID, String firstName, String lastName, Address address, double mobileNo, String identificationNo){
        this.stuID = stuID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.mobileNo = mobileNo;
        this.identificationNo = identificationNo;
    }

    //methods
    //UNIAccountInfo implementation
    public String getFirstName(){
        return firstName;
    }

    public String getLastName(){
        return lastName;
    }

    public String getContactInfo(){
        return String.format("Mobile number: %.0f", getMobileNumber());
    }

    public String getID(){
        return "Student ID: " + getStudentID() + "\nIdentification Number: " + getIDNo();
    }

    //Cloneable implementation
    @Override
    public Student clone() throws CloneNotSupportedException {
        Student studentClone = (Student) super.clone();
        studentClone.setAddress((Address)this.getAddress().clone());
        return studentClone;
    }

    //get/set methods
    public String getStudentID(){
        return stuID;
    }

    public Address getAddress(){
        return address;
    }

    public double getMobileNumber(){
        return mobileNo;
    }

    public void setStuID(String stuID){
        this.stuID = stuID;
    }

    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    public void setLastName(String lastName){
        this.lastName = lastName;
    }

    public void setAddress(Address address){
        this.address = address;
    }

    public void setMobileNo(double mobileNo){
        this.mobileNo = mobileNo;
    }

    public void setIDNo(String identificationNo){
        this.identificationNo = identificationNo;
    }

    public String getIDNo(){
        return identificationNo;
    }

    @Override
    public String toString(){
        return String.format("Student ID: %s \n%s %s \n%s \n%s",
         getStudentID(), getFirstName(), getLastName(), 
         getAddress(), getContactInfo());
    }
}

class PostGrad extends Student{
    //datafields
    //should date be a Data object
    private String BSDegree;
    private String BSCompletionDate;

    //dedault constructor
    public PostGrad(){
        super();
        this.BSDegree = " ";
        this.BSCompletionDate = " ";
    }

    //parameterised constructor
    public PostGrad(String stuID, String firstName, String lastName, 
    Address address, double mobileNo, String identificationNo, 
    String BSDegree, String BSCompletionDate){
        super(stuID, firstName, lastName, address, mobileNo, identificationNo);
        this.BSDegree = BSDegree;
        this.BSCompletionDate = BSCompletionDate;
    }

    public String getBSCompletionDate(){
        return BSCompletionDate;
    }

    public String getBSDegree(){
        return BSDegree;
    }

    public void setBSCompletionDate(String BSCompletionDate){
        this.BSCompletionDate = BSCompletionDate;
    }

    public void setBSDegree(String BSDegree){
        this.BSDegree = BSDegree;
    }

    public String toString(){
        return super.toString() + "\n" 
        + String.format("Bachelor degree: %s\nCompletion Date: %s", 
        getBSDegree(), getBSCompletionDate());
    }
}

class UnderGrad extends Student{
    //datafields
    private boolean domestic;
    private double scoreATAR;

    //default constructor
    public UnderGrad(){
        super();
        this.domestic = true;
        this.scoreATAR = 0;
    }

    //parameterised constructor
    public UnderGrad(String stuID, String firstName, String lastName, 
    Address address, double mobileNo, String identificationNo, 
    boolean domestic, double scoreATAR){
        super(stuID, firstName, lastName, address, mobileNo, identificationNo);
        this.domestic = domestic;
        this.scoreATAR = scoreATAR;
    }

    public boolean getDomestic(){
        return domestic;
    }

    public double getScoreATAR(){
        return scoreATAR;
    }

    public void setDomestic(boolean domestic){
        this.domestic = domestic;
    }

    public void setScoreATAR(double atar){
        this.scoreATAR = atar;
    }

    public String toString(){
        return super.toString() + "\n" 
        + String.format("ATAR: %s\nDomestic: %b", 
        getScoreATAR(), getDomestic());
    }
}

class PHD extends PostGrad{
    //datafields
    private String MSDegree;
    private String MSCompletionDate;

    //default constructor
    public PHD(){
        super();
        this.MSDegree = " ";
        this.MSCompletionDate = " ";
    }

    //paramterised constructor
    public PHD(String stuID, String firstName, String lastName, 
    Address address, double mobileNo, String identificationNo, 
    String BSDegree, String BSCompletionDate,
    String MSDegree, String MSCompletionDate){
        super(stuID, firstName, lastName, address, mobileNo, identificationNo, BSDegree, BSCompletionDate);
        this.MSDegree = MSDegree;
        this.MSCompletionDate = MSCompletionDate;
    }

    //methods
    public String getMSDegree(){
        return MSDegree;
    }

    public String getMSCompletionDate(){
        return MSCompletionDate;
    }

    public void setMSDegree(String MSDegree){
        this.MSDegree = MSDegree;
    }

    public void setMSCompletionDate(String MSCompletionDate){
        this.MSCompletionDate = MSCompletionDate;
    }

    public String toString(){
        return super.toString() + "\n" 
        + String.format("MS Degree: %s \nCompletion date: %s", 
        getMSDegree(), getMSCompletionDate());
    }
}
