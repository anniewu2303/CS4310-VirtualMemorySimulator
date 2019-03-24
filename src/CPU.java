/**
 Author:        Joshua Chen
                Annie Wu

 Assignment:    Program 2
 Class:         CS 4310 - Operating Systems
 Instructor:    Dominick Atanasio
 Date:          24 March 2019


- Your program will accept as a command line argument the test file path to a text file populated with virtual memory
addresses (in hex and also provided with this project) that are used to simulate memory accesses called by a process.

- Your CPU will read them, hand them to the MMU for fetching or writing.
    - If the address is preceded by a zero, then the MMU should only read value.
    - If the address is preceded by a one, then the address will be followed by an integer value that needs to be
    written to physical memory.

*/

import java.io.*;
import java.util.*;

public class CPU {
    private MMU mmu;

    public CPU(String pageFilesPath) {
        this.mmu = new MMU(pageFilesPath);
    }

    public void readFile(String testFilePath) throws IOException {
        Scanner testFile  = new Scanner(new File(testFilePath));
        //PrintWriter log = new PrintWriter("log.txt", "UTF-8");

        while (testFile.hasNextLine()) {
            int rw = Integer.parseInt(testFile.nextLine());
            String address = testFile.nextLine();

            switch (rw) {
                // Read
                case 0:
                    mmu.read(address);
                    //log.printf("Read --  %s.pg Index (%s/%d): %d\n", address.substring(0, 2), address.substring(2, 4), pageIndex, currValue);
                    break;
                // Write
                case 1:
                    int newValue = Integer.parseInt(testFile.nextLine());
                    mmu.write(address, newValue);
                    //log.printf("Write --  %s.pg Index (%s/%d): (%d --> %d)\n", address.substring(0, 2), address.substring(2, 4), pageIndex, currValue, newValue);
                    break;
                default:
                    System.out.println("Problem With File Format");
            }
        }
    }
}
