// HashCreator.java
// Contributor(s):
// Meagan Callahan - created outline of class and class file,
// Desc: this class takes in the values of comp.txt, dest.txt, jump.txt, and symbols.txt and
// creates a hashmap containing the values for easier translating

import java.util.Hashtable;
import java.util.Scanner;

public class HashCreator {
    //private scanner used in initialize(String filename) method
    private Scanner sc;
    //universal hashtables
    private final Hashtable<String, String> comp = new Hashtable<>(), dest = new Hashtable<>(), jump = new Hashtable<>(), symbol = new Hashtable<>();

    public HashCreator() {
        //calls method to scan tables and fill hashtables
        initialize("HW/comp.txt");
        initialize("HW/dest.txt");
        initialize("HW/jump.txt");
        initialize("HW/symbol.txt");
    }

    // takes in a variable file name and identifies whether the file is the comp table,
    // dest table, jump table, or symbol table provided in the text files
    private void initialize(String fileName) {
        String line;
        sc = new Scanner(fileName);

        //checks which file is being initialized and fills the respective hashtable
        while(sc.hasNextLine()){
            // properly formats line
            line = sc.nextLine();
            line = line.trim();
            String[] pair = line.split("_");
            if(fileName.contains("comp.txt")) {
                comp.put(pair[0], pair[1]);
            } else if(fileName.contains("dest.txt")){
                dest.put(pair[0], pair[1]);
            } else if(fileName.contains("jump.txt")){
                jump.put(pair[0], pair[1]);
            } else if(fileName.contains("symbol.txt")){
                symbol.put(pair[0], pair[1]);
            }
        }

    }

    public void addVariable(String key, String val) {
        comp.put(key, val);
    }

    //getters to allow access to translations from hashtables
    public String getComp(String key) {
        return comp.get(key);
    }

    public String getDest(String key) {
        return dest.get(key);
    }

    public String getJump(String key) {
        return jump.get(key);
    }

    public String getSymbol(String key) {
        return symbol.get(key);
    }
}
