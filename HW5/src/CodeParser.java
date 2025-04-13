// HashCreator.java
// Contributor(s):
// Meagan Callahan - created outline of class and class file
//Justin Deines - added inline comment removal
// Desc: this class takes in a line of code and parses it into the correct sections,
// comp, dest, and jump for c-instructions or addr for A-instructions. These sections can
// then be accessed and translated

public class CodeParser {
    private String comp, dest, jump, addr;

    public CodeParser () {
        comp = "";
        dest = "";
        jump = "";
        addr = "";
    }

// parses a line of code, represented by a String s, into the correct sections and returns true
// if the parse is successful and false if it cannot be parsed
    public boolean parse(String s) {
        s = s.split("//")[0]; //remove inline comments
        s = s.trim();

        if(!s.isEmpty()){//checks if whitespace
            if(s.charAt(0) != '/' && s.charAt(1) != '/' ){ //checks if comment
                if(s.contains("@")){ // check if A-instruction
                    addr = s.substring(s.indexOf('@') + 1);
                } else { // else, C-instruction
                    if(s.contains("=")) {
                        String[] cParts = s.split("=");
                        //remove inline comments
                        dest = cParts[0].trim();
                        if(cParts[1].contains(";")){ //if jump command, parse jump
                            parseJump(cParts[1]);
                        } else {
                            comp = cParts[1].trim();
                        }
                    } else if(s.contains("+") || s.contains("-")) { //if addition/subtraction, comp = s
                        comp = s.trim();
                    } else if(s.contains(";")) {
                        parseJump(s);
                    } else {
                        jump = s.trim();
                    }
                    if (jump.isEmpty()) {
                        jump = "null";
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
        comp = jumpPart[0].trim();
        dest = "null";
        jump =  jumpPart[1].trim();
    }

    //getters for use by HackAssembler class
    public String getComp() {return comp;}

    public String getDest() {return dest;}

    public String getJump() {return jump;}
}
