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
    private String pageFilesPath;
    private MMU mmu;

    public CPU(String pageFilesPath) {
        this.mmu = new MMU(pageFilesPath);
        this.pageFilesPath = pageFilesPath;
    }

    public void readFile(String testFilePath) throws IOException {
        Scanner testFile  = new Scanner(new File(testFilePath));
        createWorkingSet(this.pageFilesPath);

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

    /**
     * Create Working Set Directory with all path files copied
     *
     * @param path
     * @throws IOException
     */
    private void createWorkingSet(String path) throws IOException {
        File pathDirectory = new File(path);

        String workSetDirectoryName = path.concat("_working_set");

        // Create Directory if it does not exist
        File directory = new File(workSetDirectoryName);
        if (!directory.exists()) {
            directory.mkdir();
        }

        // Get all files from the path directory
        File[] files = pathDirectory.listFiles();
        for (File f : files) {
            File copyFile = new File(workSetDirectoryName + "/" + f.getName());
            PrintWriter output = new PrintWriter(copyFile);

            // Copy Contents of Original Files to new Files
            Scanner file = new Scanner(new File(path + "/" + f.getName()));
            while (file.hasNextLine()) {
                output.println(file.nextLine());
            }
            output.close();
        }
    }
}
