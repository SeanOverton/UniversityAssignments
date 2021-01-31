import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

class CarEngine {
	private String engineName;
	private int cylinderVolumn; // example 2.4L
	private int numberOfCylinder;
	private String fuelType;
	
	public CarEngine(String engineName, int cylinderVolumn, int numberOfCylinder, String fuelType) {
		this.engineName = engineName;
		this.cylinderVolumn = cylinderVolumn;
		this.numberOfCylinder = numberOfCylinder;
		this.fuelType = fuelType;
	}
	
	public String getEngineName() {
		return engineName;
	}
	
	public void setEngineName(String engineName) {
		this.engineName = engineName;
	}

	public int getCylinderVolumn() {
		return cylinderVolumn;
	}

	public void setCylinderVolumn(int cylinderVolumn) {
		this.cylinderVolumn = cylinderVolumn;
	}

	public int getNumberOfCylinder() {
		return numberOfCylinder;
	}

	public void setNumberOfCylinder(int numberOfCylinder) {
		this.numberOfCylinder = numberOfCylinder;
	}

	public String getFuelType() {
		return fuelType;
	}

	public void setFuelType(String fuelType) {
		this.fuelType = fuelType;
	}

    @Override
    public String toString() {
        return "CarEngine{" + "engineName=" + engineName + ", cylinderVolumn=" + cylinderVolumn + ", numberOfCylinder=" + numberOfCylinder + ", fuelType=" + fuelType + '}';
    }
}

class testing {
	public static void main(String[] args){
		//creates 5 car engine objects
		CarEngine a0 = new CarEngine("Honda GT",2500,6,"98");
		CarEngine a1 = new CarEngine("Hyundai XTX",2100,6,"98");
		CarEngine a2 = new CarEngine("Holden TT",1200,6,"98");
		CarEngine a3 = new CarEngine("Toyota TX",900,6,"98");
		CarEngine a4 = new CarEngine("Tesla GX",0,0,"Electricity");

		//creates hash map and stores all car engine objects
		Map<String,CarEngine> ma = new HashMap<>();
		ma.put(a0.getEngineName(),a0);
		ma.put(a1.getEngineName(),a1);
		ma.put(a2.getEngineName(),a2);
		ma.put(a3.getEngineName(),a3);
		ma.put(a4.getEngineName(),a4);

		// ma.values().stream() convert a hashmap to a stream.

// 		Write the code to search the object by the engine name (using Lambda and Stream)
		System.out.print("Enter engine name to search for: ");
		Scanner input = new Scanner(System.in);
		String engineStr = input.nextLine();
		//loop through hashmap keys and find a match then getEngine instance and print
		System.out.println("Matched engines below: ");
		ma.values().stream().filter(x -> x.getEngineName().equals(engineStr)).forEach(System.out::println);

//		 Write the code to search for the engine that the cylinder volume between 1000 cc. to 2400 cc. and
// 		display them to the console. (using Lambda and Stream)
		System.out.println();
		System.out.println("Engines with a cylinder volume between 1000cc-2400cc: ");
		ma.values().stream().filter(x -> x.getCylinderVolumn() > 1000).filter(x -> x.getCylinderVolumn() < 2400).forEach(System.out::println);
	}
}