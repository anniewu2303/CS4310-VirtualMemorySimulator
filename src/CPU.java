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

    public CPU(String pageFilesPath, CSV csv) {
        this.csv = csv;
        this.os = new OS(pageFilesPath, csv);
        this.mmu = new MMU(this.os, pageFilesPath, csv);
    }

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
        int counter = 0;

        while (testFile.hasNextLine()) {
            int rw = Integer.parseInt(testFile.nextLine());
            String address = testFile.nextLine();

            csv.rw(rw);
            csv.address(address);

            switch (rw) {
                // Read
                case 0:
                    if (counter == 10) {
                        os.resetAllRef();
                        counter = 0;
                    }
                    mmu.read(address);
                    break;
                // Write
                case 1:
                    if (counter == 10) {
                        os.resetAllRef();
                        counter = 0;
                    }
                    int newValue = Integer.parseInt(testFile.nextLine());
                    mmu.write(address, newValue);
                    break;
                default:
                    System.out.println("Problem With File Format");
            }
            csv.output();
            counter++;
        }
    }
}
