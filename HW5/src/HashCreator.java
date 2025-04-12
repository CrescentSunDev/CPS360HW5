// HashCreator.java
// Contributor(s):
// Meagan Callahan - created outline of class and class file
// Desc: this class takes in the values of comp.txt, dest.txt, jump.txt, and symbols.txt and
// creates a hashmap containing the values for easier translating


import java.util.Hashtable;

public class HashCreator {
    //universal hashtables
    private final Hashtable<String, String> comp = new Hashtable<>(), dest = new Hashtable<>(), jump = new Hashtable<>(), symbol = new Hashtable<>();

    public HashCreator() {
        //scans tables and sets up hashtables

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
