package src.main.java.com.monkeyjava.token;

import java.util.HashMap;

public class Token{
    public String Type;
    public String Literal;
    public static final String ILLEGAL = "ILLEGAL",
                               // Identifiers + literals
                               IDENT = "IDENT", // add, foobar, x, y, ...
                               INT = "INT", // 1343456
                               // Operators
                               ASSIGN = "=",
                               PLUS = "+",
                               // Delimiters
                               COMMA = ",",
                               SEMICOLON = ";",
                               LPAREN = "(",
                               RPAREN = ")",
                               LBRACE = "{",
                               RBRACE = "}",
                               // Keywords
                               FUNCTION = "FUNCTION",
                               EOF ="EOF",
                               LET = "LET";
    HashMap<String,String> keyword = new HashMap<String,String>();
    keyword.put("fn",FUNCTION);

    
    public Token(){
        this.Type  = "";
        this.Literal = ""; 
    }
    public Token(String Type,String Literal){
        this.Type=Type;
        this.Literal=Literal; 
    }
    public Token(String Type,Character Literal){
        this.Type=Type;
        this.Literal= Character.toString(Literal); 
    }

}
