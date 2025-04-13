//Assignment: HW5, Hack assembler
//Group members: Justin Deines and Meagan Callahan
//Pseudocode left in comments to show work

import java.util.Scanner;

public class HackAssembler {
    //symbol table object
    HashCreator symbolTable;
    //int current line, describes current line in file I/O
    int currentLine;
    //parser object
    CodeParser parser;

    //constructor, set symbol table and current line to 0
    public void HackAssembler(){
        //symbol table init
        symbolTable = new HashCreator();
        //current line = 0
        currentLine = 0;
        //parser init
        parser = new CodeParser();
    }

    //first pass method
    public void pass1(String filename){
        //open file
                
        //read line by line
        //if line is not empty or a comment
        //if line is an A-instruction, add to symbol table
        //if line is a C-instruction, parse and add to symbol table
        //increment current line
        //close file
    }

    //second pass/translate method
    public void pass2(){
        //open file
        //read line by line
        //if line is not empty or a comment
        //if line is an A-instruction, translate to binary and write to output file
        //if line is a C-instruction, parse and translate to binary and write to output file
        //increment current line
        //close file
    }

    //binary padding method (not sure if necessary but i feel it would be smart to add just in case)
    public String padBinary(String unpadded){
        String padded = "";



        return padded + unpadded;
    }
    public static void main(String[] args) {
        //call parser with filename from args[0]
        String filename = args[0];
        //initiate assembler
        HackAssembler assembler = new HackAssembler();
        //call first pass
        assembler.pass1();
        //call translate pass
        assembler.pass2();


    }
}