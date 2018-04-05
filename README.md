# shakespeare-text-finder
AUTHOR: Joven Pableo (jpableo688@gmail.com)

shakespeare-text-finder (or Bard for short) is a client program that utilizes the built in hashtable data structure in the java.util package. This program stores all the words in every found work from Shakespeare and puts it into a hashtable. In order to run the Bard program, a text file containing all of Shakespeare’s work must be in the same folder as the program.

Instructions
1. Extract all the files from the repository into a folder
2. Run the make command in Unix (Terminal or any command-line shell, etc)
3. Make a new .txt document named “input”
4. Enter either variation of acceptable input in each line ending the file with no new line character:
	a. Word count - [insert word]   
	b. Word query - [(int) number of characters] [(int) amount of words needed]
5. Run the program by typing 
	java -jar Bard.jar
6. Open analysis.txt file for results

Example:
input.txt contains:
and
clearly
11 1
4 2
7 3
algorithm

analysis.txt contains:
24770
5
remembrance 
that with 
nothing against brother 
0

Files in repository:

README:
Details and instructions of how to use the program

HashClient.java
Java program that stores each word in every work from Shakespeare and puts it into a hashtable which allows users to make a query with minimal wait time.

shakespeare.txt
A published project that contains each found work from Shakespeare

Makefile
Instructions to compile each .java file into a .jar file
