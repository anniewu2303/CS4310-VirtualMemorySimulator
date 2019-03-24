/**
 * @author:     Joshua Chen, Annie Wu
 * @date        Mar 24, 2019
 *
 * Assignment:  Project 2 - Virtual Memory Simulator
 * Class:       CS 4310 - Operating Systems
 * Instructor:  Dominick Atanasio
 *
 * Accepts a command line argument for a test file path to a text file populated with virtual memory address.
 * Ex. test_files/test_1.txt
 *
 * Page Files Acting as the Physical Memory Addresses Will Have a Working Set Copy Created.
 * This way no altered page files of a previous run is used.
 *
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
     * Create Working Set Directory with all path files copied.
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
