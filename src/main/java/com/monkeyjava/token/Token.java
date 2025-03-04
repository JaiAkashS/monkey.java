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
                               MINUS = "-",
                               BANG = "!",
                               ASTERISK = "*",
                               SLASH = "/",
                               EQ = "==",
                               NOT_EQ = "!=",
                               
                               LT = "<",
                               GT = ">",
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
                               LET = "LET",
                               RETURN = "RETURN",
                               IF = "IF",
                               ELSE = "ELSE",
                               TRUE = "TRUE",
                               FALSE = "FALSE";
    ;
    public static HashMap<String,String> keywords = new HashMap<String,String>();

    private static void initKeyword(){
        keywords.put("fn",Token.FUNCTION);
        keywords.put("let",Token.LET);
        keywords.put("true",Token.TRUE);
        keywords.put("false",Token.FALSE);
        keywords.put("return",Token.RETURN);
        keywords.put("if",Token.IF);
        keywords.put("else",Token.ELSE);
    }
    public Token(){
        this.Type  = "";
        this.Literal = "";
        initKeyword(); 
    }
    public Token(String Type,String Literal){
        this.Type=Type;
        this.Literal=Literal;
        initKeyword(); 
    }
    public Token(String Type,Character Literal){
        this.Type=Type;
        this.Literal= Character.toString(Literal);
        initKeyword(); 
    }
    public static String LookupIdent(String Ident){
        if(keywords.containsKey(Ident)){
            return keywords.get(Ident);
        }
        return Token.IDENT;
    }

}
