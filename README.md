# CS4310-VirtualMemorySimulator

You will be prompted to input a path to files simulating processes.
(These files can be in a directory, all that is needed is the path).
(ie. test_files/test_1.txt)

There may only be one string per line.

if the process is a read, then it starts with 0 (then endline).
The line following the read (0) should be the address to read from.

if the process is a write, then it must start with a 1 (then endline).
The line following the write (1) should be the address to write to.
One additional line is required, the value to write to the address.

Once a proper file is instantiated, the program will create a csv file 
    to display what has occurred in the virtual memory simulator in the format:
    
    Address| r/w | value | soft | hard | hit | evicted_pg# | dirty_evicted_page
    
