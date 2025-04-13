//Assignment: HW5, Hack assembler
//Group members: Justin Deines and Meagan Callahan
//Pseudocode left in comments to show work
//Jusin Deines - Wrote this class


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Scanner;

public class HackAssembler {
    //hash table object
    HashCreator h;

    //int current line, describes current line in file I/O
    int lineCount;

    //constructor, set symbol table and current line to 0
    public HackAssembler(){
        //Hash Creator init
        h = new HashCreator();
        //current line = 0
        lineCount = 0;
    }

    //first pass method
    public void pass1(String filename){
        //open file
        try {
            Scanner sc = new Scanner(filename);

            while (sc.hasNextLine()){
                CodeParser p = new CodeParser();
                String line = sc.nextLine();

                //if line is not empty or a comment
                if(p.parse(line)){
                    lineCount += 1;
                    //if line is symbol, add to symbol table and decrement line count
                    if (line.trim().charAt(0) == '('){
                        String symbol = line.trim().substring(line.indexOf("(") + 1, line.indexOf(")"));
                        if (!h.containsSymbol(symbol)) {
                            h.addSymbol(symbol, Integer.toString(lineCount));
                        }
                        lineCount -= 1;
                    }
                }
            }
            sc.close();
        } catch (Exception e) {
            System.out.println("Error opening file in pass1: " + e.getMessage());

        }
    }

    //second pass/translate method
    public void pass2(String filename, String outfilename) {
        try (PrintWriter out = new PrintWriter(outfilename)) { // Use try-with-resources
            lineCount = 0;
            Scanner sc = new Scanner(new BufferedReader(new FileReader(filename)));

            while (sc.hasNextLine()) {
                CodeParser p = new CodeParser();
                String line = sc.nextLine().trim();

                if (p.parse(line) && !line.startsWith("(")) {
                    if (line.startsWith("@")) { // A-instruction
                        String addr = line.substring(1);
                        String binary;
                        if (addr.matches("\\d+")) {
                            binary = padBinary(Integer.toBinaryString(Integer.parseInt(addr)));
                        } else {
                            // Handle variables not in the symbol table
                            if (!h.containsSymbol(addr)) {
                                // Assign next available variable address (starting at 16)
                                int varAddress = h.getNextVariableAddress();
                                h.addSymbol(addr, Integer.toBinaryString(varAddress));
                            }
                            String symbolValue = h.getSymbol(addr);
                            binary = padBinary(symbolValue);
                        }
                        out.println(binary);
                    } else { // C-instruction
                        String comp = h.getComp(p.getComp());
                        String dest = h.getDest(p.getDest());
                        String jump = h.getJump(p.getJump());
                        String binary = padBinary("111" + comp + dest + jump);
                        out.println(binary);
                    }
                }
                lineCount++;
            }
            out.close();
        } catch (Exception e) {
            System.out.println("Error opening file in pass2: " + e.getMessage());
        }
    }

    //binary padding method (not sure if necessary but i feel it would be smart to add just
    // in case instructions are not 16 bits long)
    public String padBinary(String unpadded) {
        // Pad the binary string to 16 bits
        while (unpadded.length() < 16) {
            unpadded = "0" + unpadded;
        }
        return unpadded;
    }
    public static void main(String[] args) {
        //call parser with filename from args[0]
        String filename = args[0].substring(0, args[0].indexOf("."));
        String outfilename = filename + ".hack";
        //initiate assembler
        HackAssembler assembler = new HackAssembler();
        //call first pass
        assembler.pass1(filename + ".asm");
        //call translate pass
        assembler.pass2(filename + ".asm", outfilename);


    }
}