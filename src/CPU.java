import java.io.*;
import java.util.*;


/*
Your program will accept as a command line argument the test file path to a text file populated with virtual memory
addresses (in hex and also provided with this project) that are used to simulate memory accesses called by a process.

Your CPU will read them, hand them to the MMU for fetching or writing. 
 - If the address is preceded by a zero, then the MMU should only read value. 
 - If the address is preceded by a one, then the address will be followed by an integer value that needs to be 
 written to physical memory. 
*/

public class CPU {
    
    public void readFile(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        Scanner scan = new Scanner(file);
        int counter = 0; //counter for number of instructions 
        
        while (scan.hasNextInt()) {
            int rw = scan.nextInt(); //get read/write bit
            CSV.rw(rw); 
            
            if (rw == 0) { //read value
                String address = scan.next();
                CSV.address(address);
		int value = MMU.read(address); 
                CSV.value(value);
                counter++;
            }
            if (rw == 1) { //write value
                String address = scan.next();
                CSV.address(address);
                int value = scan.nextInt();
                CSV.value(value); 
                MMU.write(address, value);
                counter++;
            }
            
            //OS resets r-bits every 10 instructions 
            if (counter >= 10){ 
                OS.resetR();
                counter = 0;
            }
            
            CSV.output(); 
        }
    scan.close();
    }
}
