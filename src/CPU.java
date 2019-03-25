/**
 * @author: Joshua Chen, Annie Wu
 * @date Mar 24, 2019
 * <p>
 * Assignment:  Project 2 - Virtual Memory Simulator
 * Class:       CS 4310 - Operating Systems
 * Instructor:  Dominick Atanasio
 * <p>
 * Central Processing Unit
 * <p>
 * The CPU processes the testFilePath and hands the processes to the MMU to read or write.
 * - If the address is preceded by a zero, then the MMU is told to read from the address.
 * - If the address is preceded by a one, then the MMU is told to replace the value at the address with a newValue.
 * <p>
 * Hardware Clock Interrupt:
 * After CPU Processes 10 Instructions:
 * Tell OS to unset r-bits of all page table entries (virtual memory).
 * <p>
 * When page has been *written back to the disk*:
 * CPU should reset d-bits of all page table entries (virtual memory).
 */

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class CPU {
    private MMU mmu;
    private CSV csv;
    private OS os;
    private int counter;

    public CPU(String pageFilesPath) { this.mmu = new MMU(pageFilesPath); }

    /**
     * Processes File.
     * Determines if the Process is a Read of Write.
     *
     * @param testFilePath
     * @throws IOException
     */
    public void processFile(String testFilePath) throws IOException {
        File file = new File(testFilePath);
        Scanner testFile = new Scanner(file);
        //PrintWriter log = new PrintWriter("log.txt", "UTF-8");

        //split file name and extension
        String fileName = file.getName().split("\\.")[0] + ".csv";
        System.out.println("Output file: " + fileName);
//        csv.initializeCSV(fileName);

        counter = 0;
        while (testFile.hasNextLine()) {
            int rw = Integer.parseInt(testFile.nextLine());
            String address = testFile.nextLine();

//            csv.rw(rw);
//            csv.address(address);

            switch (rw) {
                // Read
                case 0:
                    if (counter == 10) {
                        os.resetAllRef();
                    }

                    mmu.read(address);
                    //log.printf("Read --  %s.pg Index (%s/%d): %d\n", address.substring(0, 2), address.substring(2, 4), pageIndex, currValue);
                    break;
                // Write
                case 1:
                    if (counter == 10) {
                        os.resetAllRef();
                    }

                    int newValue = Integer.parseInt(testFile.nextLine());
                    mmu.write(address, newValue);
                    //log.printf("Write --  %s.pg Index (%s/%d): (%d --> %d)\n", address.substring(0, 2), address.substring(2, 4), pageIndex, currValue, newValue);
                    break;
                default:
                    System.out.println("Problem With File Format");
            }
//            counter++;
//            csv.output();
        }
    }
}
