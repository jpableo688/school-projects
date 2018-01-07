/*
 *  AUTHOR: Joven Pableo (jpableo@ucsc.edu)
 *
 *
 *  PURPOSE: To analyze all of shakespeare’s compositions using a
 *           hashtable.
 *
 *
 */

import java.util.*;
import java.io.*;

public class HashClient
{
    //Global hashtable to maintain all of words used in shakespeare.txt
    public static Hashtable<String, Integer> words = new Hashtable<String, Integer>();
    
    public static BufferedWriter writer; 

    
    public static void main(String[] args) throws IOException
    {
        //Scanners that take in corresponding .txt files
        Scanner textFile = new Scanner(new File("shakespeare.txt"));
        Scanner inputFile = new Scanner(new File("input.txt"));
       
        //Open the file to write
        writer = new BufferedWriter(new FileWriter("analysis.txt")); 
        buildHashTable(textFile);                
        readInputFile(inputFile);
        
        //Closes the writer
        writer.close();
        
    }
         
    //Method that builds the hash table and parses each word
    public static void buildHashTable(Scanner textFile)
    {
        //Loops through line
        while(textFile.hasNextLine())
        {   
            //Loops through word in line
            while(textFile.hasNext())
            {          
                //Grab word || used to avoid pointer issues
                String nextWord = textFile.next();
                
                //Skips word if its in all CAPS
                if(nextWord == nextWord.toUpperCase())
                    continue;
                
                //Lowercases word
                String lowerWord = nextWord.toLowerCase();
                  
                //Parsing functions
                String lowerWord2 = lowerWord.replaceAll("\\.", "");
                String lowerWord3 = lowerWord2.replaceAll("\\,", "");
                String lowerWord4 = lowerWord3.replaceAll("\\?", "");
                String lowerWord5 = lowerWord4.replaceAll("\\!", "");
                String lowerWord6 = lowerWord5.replaceAll(";", "");
                String lowerWord7 = lowerWord6.replaceAll(":", "");
                String lowerWord8 = lowerWord7.replaceAll("\\[", "");
                String lowerWord9 = lowerWord8.replaceAll("\\]", "");
                String lowerWord10 = lowerWord9.replaceAll("\\(", "");
                String finalWord = lowerWord10.replaceAll("\\)", "");
                    
                //Increments collisions
                if(words.containsKey(finalWord))
                {
                            
                    int count = words.get(finalWord);
                    words.put(finalWord, count + 1);
                }
                    
                //Inserts word into hashtable
                else
                    words.put(finalWord, 1);
                    
            }
            
            //If the next line is empty then close scanner
            if(textFile.nextLine() == null)
                textFile.close();
            
        }             
        //Close scanner
        textFile.close();
    }
    
    //Method that reads input.txt and performs operations
    public static void readInputFile(Scanner inputFile)
    {
        //Loops through each line in input.txt
        while(inputFile.hasNextLine())
        {
            //Keeps the whole line as a String
            String line = inputFile.nextLine();
            
            //Conditional if line is a length/frequency query
            if(line.contains(" "))
            {
                String preLine = line.trim();
                String[] lineArr = preLine.split("\\s+");
                int len = Integer.parseInt(lineArr[0]);
                int freq = Integer.parseInt(lineArr[1]);
                
                querySearch(len, freq);
            }
            
            //Conditional if word query
            else
            {
                keySearch(line);
            }
        }
    }
    
    //Method that searches for the word query
    public static void keySearch(String key)
    {
        if(words.get(key) == null)
            writeCount(0);
        
        else
            writeCount(words.get(key));
    }
    
    //Method that searches for word length/frequency query
    public static void querySearch(int len, int freq)
    {
        //Original hashtable clone
        Hashtable<String, Integer> temp = (Hashtable<String, Integer>)words.clone();
        
        //Loops until frequency query is met
        int i = 0;
        while(i < freq)
        {
            //Iterators to go through hashtable
            Set<String> keys  = temp.keySet();
            Iterator<String> keySetIt = keys.iterator();
            
            //Variables that resets every search
            int max = 0;
            String maxKey = "";
            
            //Loops through whole hashtable looking for biggest value
            while(keySetIt.hasNext())
            {
                //Stores next key(word)
                String itm = keySetIt.next();
                
                //Determines if proper length and if it is the biggest value
                if(itm.length() == len && temp.get(itm) > max)
                {
                    max = temp.get(itm);
                    maxKey = itm;
                }
            }
            
            //If search returns empty
            if(maxKey.equals(""))
            {
                writeWord("Not Found ");
                i++;
            }
            
            else
            {
                writeWord(maxKey + " ");    
                
                //Removes key from temp hashtable to avoid duplicates being found
                temp.remove(maxKey);            
                i++;
            }
        }
        writeLine();
    }
   
    //Method to write ints String to analysis
    public static void writeCount(Integer toWrite)
    {
        try 
        {
            writer.write(toWrite + "\n");
        } 
        catch (IOException e) 
        {
            System.out.println("Error printing");
        }  
    }
    
   //Method to write String to analysis
    public static void writeWord(String toWrite)
    {
        try 
        {
            writer.write(toWrite);
        } 
        catch (IOException e) 
        {
            System.out.println("Error printing");
        }       
    }
    
    //Method to write new Line
    public static void writeLine()
    {
        try 
        {
            writer.write("\n");
        } 
        catch (IOException e) 
        {
            System.out.println("Error printing");
        }  
    }
    
    //Tool to traverse hashtable
    public static void traverseHashTable()
    {
        Hashtable<String, Integer> temp = words;

        Set<String> keys  = temp.keySet();
        Iterator<String> keySetIt = keys.iterator();
        
        while(keySetIt.hasNext())
        {
            String itm = keySetIt.next();
            System.out.println("||" + itm + " " + temp.get(itm) + "|| ");
        }
        
        /*
        for(String itm: words.keySet())
        {
                 //if(count == freq)
                 //    break;          
                 System.out.println("||" + itm + " " + words.get(itm) + "|| ");
                 //System.out.println(count);
                // count++;     
        }   
        */     
    }   
}
