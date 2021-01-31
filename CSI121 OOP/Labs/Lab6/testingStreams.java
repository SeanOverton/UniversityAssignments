import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.Collectors;
import java.util.TreeMap;


//both of these need checking
class testingStreams{
    public static void main(String[] args){
        //question 1
        int[] values1={12,2,9,8,4,65,7,4,2,66,88,11,33,44,55}; 

        //method 1
        System.out.printf("Question 1- Average of array: %.2f\n", 
            IntStream.of(values1).average().getAsDouble());
        
        //question 2
        Integer[] values2 = {12,2,9,8,4,65,7,4,2,66,88,11,33,44,55}; 

        System.out.println("Question 2 - Output:");
        Arrays.stream(values2)
            .collect(Collectors.toSet())
            .forEach(e -> System.out.println(e));       
    }
}