package src.test.java;

import java.util.HashMap;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import src.main.java.com.monkeyjava.lexer.Lexer;
import src.main.java.com.monkeyjava.token.Token;


public class LexerTest {
    @Test
    public void TestNextToken(){
        // String input = "=+(){},;";
        HashMap<String,String> tokens = new HashMap<String,String>();
        // tokens.put(Token.ASSIGN,"="); 
        // tokens.put(Token.PLUS,"+"); 
        // tokens.put(Token.COMMA,","); 
        // tokens.put(Token.SEMICOLON,";"); 
        // tokens.put(Token.LPAREN,"(");   
        // tokens.put(Token.RPAREN,")"); 
        // tokens.put(Token.LBRACE,"{"); 
        // tokens.put(Token.RBRACE,"}"); 
        // tokens.put(Token.EOF,"");
        String input = "let five = 5;\n" +
                       "let ten = 10;\n" +
                       "let add = fn(x, y) {\n" +
                       "    x + y;\n" +
                       "};\n" +
                       "let result = add(five, ten);\n";
        tokens.put(Token.LET, "let");
        tokens.put(Token.IDENT, "five");
        tokens.put(Token.ASSIGN, "=");
        tokens.put(Token.INT, "5");
        tokens.put(Token.SEMICOLON, ";");
        tokens.put(Token.LET, "let");
        tokens.put(Token.IDENT, "ten");
        tokens.put(Token.ASSIGN, "=");
        tokens.put(Token.INT, "10");
        tokens.put(Token.SEMICOLON, ";");
        tokens.put(Token.LET, "let");
        tokens.put(Token.IDENT, "add");
        tokens.put(Token.ASSIGN, "=");
        tokens.put(Token.FUNCTION, "fn");
        tokens.put(Token.LPAREN, "(");
        tokens.put(Token.IDENT, "x");
        tokens.put(Token.COMMA, ",");
        tokens.put(Token.IDENT, "y");
        tokens.put(Token.RPAREN, ")");
        tokens.put(Token.LBRACE, "{");
        tokens.put(Token.IDENT, "x");
        tokens.put(Token.PLUS, "+");
        tokens.put(Token.IDENT, "y");
        tokens.put(Token.SEMICOLON, ";");
        tokens.put(Token.RBRACE, "}");
        tokens.put(Token.SEMICOLON, ";");
        tokens.put(Token.LET, "let");
        tokens.put(Token.IDENT, "result");
        tokens.put(Token.ASSIGN, "=");
        tokens.put(Token.IDENT, "add");
        tokens.put(Token.LPAREN, "(");
        tokens.put(Token.IDENT, "five");
        tokens.put(Token.COMMA, ",");
        tokens.put(Token.IDENT, "ten");
        tokens.put(Token.RPAREN, ")");
        tokens.put(Token.SEMICOLON, ";");
        tokens.put(Token.EOF, "");
        Lexer l = new Lexer(input);
        for(int i = 0;i<input.length();i++){
            Token t = l.NextToken();
            Assertions.assertEquals(tokens.get(t.Type),t.Type);
            Assertions.assertEquals(tokens.get(t.Type),t.Literal);
            
        }

    }
}
