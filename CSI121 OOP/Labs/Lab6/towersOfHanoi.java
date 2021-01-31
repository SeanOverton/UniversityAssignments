import java.util.ArrayList;
import java.util.Scanner;

class towersOfHanoi{
    public static void main(String[] args){
        //recursion must be used
        Scanner input = new Scanner(System.in); 
        System.out.print("Input: ");
        int n = input.nextInt();// Number of disks 
        
        System.out.print("Output: ");
        towerOfHanoi(n, "A", "C", "B");
        
    }

    //the recursive method used to solve problem
    public static void towerOfHanoi(int n, String from_rod, String to_rod, String aux_rod){
        //base case
        if (n == 1) { 
            System.out.println("Move disk 1 from rod " +  from_rod + " to rod " + to_rod); 
            return; 
        } 

        //recursive case
        towerOfHanoi(n-1, from_rod, aux_rod, to_rod); //called until n==1

        //this only prints when above line finally reaches n == 1
        System.out.println("Move disk " + n + " from rod " +  from_rod + " to rod " + to_rod); 
        towerOfHanoi(n-1, aux_rod, to_rod, from_rod); 
    }
}
