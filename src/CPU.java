/**
 * @author:     Joshua Chen, Annie Wu
 * @date        Mar 24, 2019
 *
 * Assignment:  Project 2 - Virtual Memory Simulator
 * Class:       CS 4310 - Operating Systems
 * Instructor:  Dominick Atanasio
 *
 * Central Processing Unit
 *
 * The CPU processes the testFilePath and hands the processes to the MMU to read or write.
 * - If the address is preceded by a zero, then the MMU is told to read from the address.
 * - If the address is preceded by a one, then the MMU is told to replace the value at the address with a newValue.
 */

import java.io.*;
import java.util.Scanner;

public class CPU {
    private MMU mmu;

    public CPU(String pageFilesPath) {
        this.mmu = new MMU(pageFilesPath);
    }

    /**
     * Processes File.
     * Determines if the Process is a Read of Write.
     * @param testFilePath
     * @throws IOException
     */
    public void processFile(String testFilePath) throws IOException {
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
