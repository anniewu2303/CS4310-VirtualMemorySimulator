/*
 Author:        Joshua Chen
                Annie Wu 
				
 Assignment:    Program 2 
 Class:         CS 4310 - Operating Systems 
 Instructor:    Dominick Atanasio 
 Date:          24 March 2019 
 
 
Your program will accept as a command line argument the test file path to a text file populated with virtual memory
addresses (in hex and also provided with this project) that are used to simulate memory accesses called by a process.

- For each test file that you run, you should use an original copy of the page files that are available with the project. 
- You do not want altered page files of a previous runs to be used. 
- Keep the originals in a safe place and overwrite the working set for each simulation. 
- This should be done programmatically from within your simulator, not manually. 
- You should use a file copy facility of your chosen language, not an operating system call. 
- You cannot assume that the operating system of the grader is the same as yours.

*/

import java.io.*;

public class VMSimDriver {
    public static void main(String[] args) {
        
        Scanner scan = new Scanner(System.in);
        
        System.out.println("Enter location of your input file: ");
        String file = scan.nextLine();
        scan.close();

        
        //get file
        
        
        PageTable pt = new PageTable();
        pt.start(); //initialize page table
        
        TLB tlb = new TLB();
        tlb.start(); //initialize TLB
        
        Clock clock = new Clock();
        clock.start(); //initialize clock algorithm
        
        CSV csv = new CSV();
        csv.createOutputFile(file + ".csv"); //create csv output file
        
        CPU cpu = new CPU();
        cpu.readFile(file); //read test file
        
        csv.output(); //output to csv file
    }
}
