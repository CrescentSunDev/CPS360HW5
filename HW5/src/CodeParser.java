// HashCreator.java
// Contributor(s):
// Meagan Callahan - created outline of class and class file
// Desc: this class takes in a line of code and parses it into the correct sections,
// comp, dest, and jump for c-instructions or addr for A-instructions. These sections can
// then be accessed and translated

public class CodeParser {
    private String comp, dest = "null", jump = "null", addr;

// parses a line of code, represented by a String s, into the correct sections and returns true
// if the parse is successful and false if it cannot be parsed
    public boolean parse(String s) {
        s = s.trim();


        if(!s.isEmpty()){
            if(s.charAt(0) != '/' && s.charAt(1) != '/' ){

                return true;
            }
        }
        return false; // returns false if the line of code is whitespace or a comment
    }

    private void parseJump(String s) {
        //parses the jump instr into jump and comp values
    }

}
