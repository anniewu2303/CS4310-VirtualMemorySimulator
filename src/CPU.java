/**
 * Author:        Joshua Chen
 *                Annie Wu 
 * 
 * Assignment:    Program 2
 * Class:         CS 4310 - Operating Systems
 * Instructor:    Dominick Atanasio
 * Date:          24 March 2019
 * 
 * 
 * - Your program will accept as a command line argument the test file path to a text file populated with virtual memory
 * addresses (in hex and also provided with this project) that are used to simulate memory accesses called by a process.
 * 
 * - Your CPU will read them, hand them to the MMU for fetching or writing.
 *      - If the address is preceded by a zero, then the MMU should only read value.
 *      - If the address is preceded by a one, then the address will be followed by an integer value that needs to be
 *      written to physical memory.
 * 
 */


import java.io.*;
import java.util.*;

public class CPU {

    private MMU mmu = new MMU();
    private CSV csv = new CSV();
    private OS os = new OS();
    private int counter;
       
    public void readFile() throws Exception {
        
        Scanner scan = new Scanner(System.in);

        String pageFilesPath = "page_files";
        createWorkingSet(pageFilesPath);
        
        // Physical Memory:
        // 2^12 - 2^8 = 2^4 = 16 Pages
        // Each pages contains 12 bits addressable width
        PhysicalMemory ram = new PhysicalMemory(16, 4096);

        // Virtual Memory:
        // 2^16 - 2^8 = 2^8 = 256 Page Table Entries
        PageTable table = new PageTable(256);

        // TLB:
        TLB tlb = new TLB(16);

        // ie. test_files/test_1.txt
        System.out.print("Test File Path: ");
        String testFilePath = scan.next();
        File f = new File(testFilePath);
        Scanner testFile = new Scanner(f);

        String splitBySlash = f.getName().split("/")[0];
        String splitByDot = f.getName().split("\\.")[0];

        csv.initializeCSV(splitByDot + ".csv");
        
        PrintWriter log = new PrintWriter("log.txt", "UTF-8");

        counter = 0;
        while (testFile.hasNextLine()) {
            int rw = Integer.parseInt(testFile.nextLine());
            String address = testFile.nextLine();

            String pageFileName, pageFrame;
            Scanner page;
            int pageIndex, currValue = 0, newValue;

            switch (rw) {
                // Read
                case 0:
                    //OS resets r-bit in PageTable and TLB every 10 instructions 
                    if (counter == 10) {
                        os.resetRef(table, tlb);
                        counter = 0;
                    }
                    
                    pageFrame = address.substring(0, 2);
                    pageIndex = Integer.parseInt(address.substring(2), 16); // Convert hex String to Int

                    pageFileName = pageFilesPath + "_working_set/" + pageFrame;
                    page = new Scanner(new File(pageFileName + ".pg"));

                    for (int i = 0; page.hasNextLine(); i++) {
                        if (i == pageIndex) {
                            currValue = Integer.parseInt(page.nextLine());
                            break;
                        }
                        page.nextLine();
                    }
                    log.printf("Read --  %s.pg Index (%s/%d): %d\n",
                            address.substring(0, 2), address.substring(2, 4), pageIndex, currValue);
                    
                    break;
                // Write
                case 1:
                    //OS resets r-bit in PageTable and TLB every 10 instructions 
                    if (counter == 10) {
                        os.resetRef(table, tlb);
                        counter = 0;
                    }
                    
                    pageFrame = address.substring(0, 2);
                    pageIndex = Integer.parseInt(address.substring(2), 16); // Convert hex String to Int
                    newValue = Integer.parseInt(testFile.nextLine());

                    pageFileName = pageFilesPath + "_working_set/" + pageFrame;

                    BufferedReader reader = new BufferedReader(new FileReader(pageFileName + ".pg"));
                    StringBuffer strBuffer = new StringBuffer();
                    String line;
                    int lineNum = 0;
                    while ((line = reader.readLine()) != null) {
                        // Replace Line with New Value
                        if (lineNum == pageIndex - 1) {
                            currValue = Integer.parseInt(line);
                            strBuffer.append(newValue + "\n");
                        } else {
                            strBuffer.append(line + "\n");
                        }
                        lineNum += 1;
                    }
                    String inputStr = strBuffer.toString();
                    reader.close();

                    FileOutputStream writeToFile = new FileOutputStream(pageFileName + ".pg");
                    writeToFile.write(inputStr.getBytes());
                    writeToFile.close();

                    log.printf("Write --  %s.pg Index (%s/%d): (%d --> %d)\n",
                            address.substring(0, 2), address.substring(2, 4), pageIndex, currValue, newValue);
                    break;
                default:
                    System.out.println("Problem With File Format");
            }
            csv.output();
            counter++;
        }
    }

    /**
     * Create Working Set Directory with all path files copied
     *
     * @param path
     * @throws IOException
     */
    private static void createWorkingSet(String path) throws IOException {
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
