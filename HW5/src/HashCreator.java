// HashCreator.java
// Contributor(s):
// Meagan Callahan - created outline of class and class file,
// Justin Deines - minor bugfixing
// Desc: this class takes in the values of comp.txt, dest.txt, jump.txt, and symbols.txt and
// creates a hashmap containing the values for easier translating

import java.io.FileReader;
import java.util.Hashtable;
import java.util.Scanner;

public class HashCreator {
    //private scanner used in initialize(String filename) method
    private Scanner sc;
    //universal hashtables
    private final Hashtable<String, String> comp = new Hashtable<>(), dest = new Hashtable<>(), jump = new Hashtable<>(), symbol = new Hashtable<>();
    private int nextVariable = 16;

    public HashCreator() {
        //calls method to scan tables and fill hashtables
        initialize("comp.txt");
        initialize("dest.txt");
        initialize("jump.txt");
        initialize("symbols.txt");
    }

    // takes in a variable file name and identifies whether the file is the comp table,
    // dest table, jump table, or symbol table provided in the text files
    private void initialize(String fileName) {
        try (Scanner sc = new Scanner(new FileReader(fileName))) {
            String line;
            while (sc.hasNextLine()) {
                line = sc.nextLine().trim();
                if (line.isEmpty() || !line.contains("_")) {
                    continue;
                }
                String[] pair = line.split("_");
                if (pair.length == 2) {
                    if (fileName.contains("comp.txt")) {
                        comp.put(pair[0], pair[1]);
                    } else if (fileName.contains("dest.txt")) {
                        dest.put(pair[0], pair[1]);
                    } else if (fileName.contains("jump.txt")) {
                        jump.put(pair[0], pair[1]);
                    } else if (fileName.contains("symbols.txt")) {
                        symbol.put(pair[0], pair[1]);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error initializing " + fileName + ": " + e.getMessage());
        }
    }
    //adds variable to symbol table
    public void addComp(String key, String val) {
        comp.put(key, val);
    }

    public void addDest(String key, String val){
        dest.put(key, val);
    }

    public void addJump(String key, String val) {
        jump.put(key, val);
    }

    public void addSymbol(String key, String val) {
        symbol.put(key, val);
    }

    //getters to allow access to translations from hashtables
    public boolean containsSymbol(String key) {
        return symbol.containsKey(key);
    }

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

    public int getNextVariableAddress() {
        return nextVariable++;
    }
}

