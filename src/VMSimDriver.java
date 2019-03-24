/**
 * @author: Joshua Chen
 * Annie Wu
 * <p>
 * Assignment:    Program 2
 * Class:         CS 4310 - Operating Systems
 * Instructor:    Dominick Atanasio
 * Date:          24 March 2019
 * <p>
 * <p>
 * Your program will accept as a command line argument the test file path to a text file populated with virtual memory
 * addresses (in hex and also provided with this project) that are used to simulate memory accesses called by a process.
 * <p>
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
    public static void main(String[] args) throws IOException {
        String pageFilesPath = "page_files";
        createWorkingSet(pageFilesPath);

        CPU cpu = new CPU(pageFilesPath);

        // ie. test_files/test_1.txt
        Scanner scan = new Scanner(System.in);
        System.out.print("Test File Path: ");
        String testFilePath = scan.next();
        cpu.processFile(testFilePath);
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
                String line = file.nextLine();
                output.println(line);
            }
            output.close();
        }
    }
}
