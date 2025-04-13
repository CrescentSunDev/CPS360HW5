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


        if(!s.isEmpty()){//checks if whitespace
            if(s.charAt(0) != '/' && s.charAt(1) != '/' ){ //checks if comment
                if(s.contains("@")){ // check if A-instruction
                    dest = s.substring(s.indexOf('@') + 1);
                } else { // else, C-instruction
                    if(s.contains("=")) {
                        String[] cParts = s.split("=");
                        dest = cParts[0];
                        if(cParts[1].contains(";")){
                            parseJump(cParts[1]);
                        } else {
                            comp = cParts[1].split("/")[0].trim();
                        }
                    } else if(s.contains("+") || s.contains("-")) {
                        comp = s.split("/")[0].trim();
                    } else if(s.contains(";")) {
                        parseJump(s);
                    } else {
                        jump = s.split("/")[0].trim();
                    }
                }
                return true;
            }
        }
        return false; // returns false if the line of code is whitespace or a comment
    }

    private void parseJump(String s) {
        //parses the jump instr into jump and comp values
        String[] jumpPart = s.split(";");
        comp = jumpPart[0];
        jump = jumpPart[1];
    }

    //getters for use by HackAssembler class
    public String getComp() {return comp;}

    public String getDest() {return dest;}

    public String getJump() {return jump;}

    public String getAddr() {return addr;}
}
