/**
 * @author: Joshua Chen
 *          Annie Wu
 *
 * Assignment:    Program 2
 * Class:         CS 4310 - Operating Systems
 * Instructor:    Dominick Atanasio
 * Date:          24 March 2019
 *
 *
 * Your program will accept as a command line argument the test file path to a text file populated with virtual memory
 * addresses (in hex and also provided with this project) that are used to simulate memory accesses called by a process.
 *
 * - For each test file that you run, you should use an original copy of the page files that are available with the project.
 * - You do not want altered page files of a previous runs to be used.
 * - Keep the originals in a safe place and overwrite the working set for each simulation.
 * - This should be done programmatically from within your simulator, not manually.
 * - You should use a file copy facility of your chosen language, not an operating system call.
 * - You cannot assume that the operating system of the grader is the same as yours.
 */

import java.io.*;
import java.util.Scanner;

public class VMSimDriver {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        Scanner scan = new Scanner(System.in);

        // Physical Memory:
        // 2^12 - 2^8 = 2^4 = 16 Pages
        // Each pages contains 12 bits addressable width
        PhysicalMemory ram = new PhysicalMemory(16, 4096);

        // Virtual Memory:
        // 2^16 - 2^8 = 2^8 = 256 Page Table Entries
        PageTable pageTable = new PageTable(256);

        // TLB:
        TLB tlb = new TLB(16);

        // ie. test_files/test_1.txt
        System.out.print("Test File Path: ");
        String testFilePath = scan.next();
        Scanner testFile = new Scanner(new File(testFilePath));

        String[] parseFile = testFilePath.split("/");
        String logName = parseFile[parseFile.length - 1] + "_log.txt";
        PrintWriter log = new PrintWriter(logName, "UTF-8");

        while (testFile.hasNextLine()) {
            int rw = Integer.parseInt(testFile.nextLine());
            String address = testFile.nextLine();

            String pageFileName;
            Scanner page;

            String pageFrame;
            int pageIndex;
            int currValue = 0;
            int newValue;

            switch (rw) {
                // Read
                case 0:
                    pageFrame = address.substring(0, 2);
                    pageIndex = Integer.parseInt(address.substring(2), 16);

                    pageFileName = "page_files/" + pageFrame;
                    page = new Scanner(new File(pageFileName + ".pg"));

                    for (int i = 0; page.hasNextLine(); i++) {
                        if (i == pageIndex) {
                            currValue = Integer.parseInt(page.nextLine());
                        }
                        page.nextLine();
                    }

                    log.printf("Read Page Frame %2s, Index %2s: %d\n", pageFrame, pageIndex, currValue);
                    break;
                // Write
                case 1:
                    pageFrame = address.substring(0, 2);
                    pageIndex = Integer.parseInt(address.substring(2), 16);
                    newValue = Integer.parseInt(testFile.nextLine());
            }
        }

    }
}
