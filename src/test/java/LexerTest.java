package src.test.java;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;


import src.main.java.com.monkeyjava.lexer.Lexer;
import src.main.java.com.monkeyjava.token.Token;

public class LexerTest {
    @Test
    public void TestNextToken(){
        String input = "let five = 5;\n" +
                       "let ten = 10;\n" +
                       "let add = fn(x, y) {\n" +
                       "    x + y;\n" +
                       "};\n" +
                       "let result = add(five, ten);\n";
        List<ExpectToken> tokens = new ArrayList<>();

        tokens.add(new ExpectToken(Token.LET, "let"));
        tokens.add(new ExpectToken(Token.IDENT, "five"));
        tokens.add(new ExpectToken(Token.ASSIGN, "="));
        tokens.add(new ExpectToken(Token.INT, "5"));
        tokens.add(new ExpectToken(Token.SEMICOLON, ";"));
        tokens.add(new ExpectToken(Token.LET, "let"));
        tokens.add(new ExpectToken(Token.IDENT, "ten"));
        tokens.add(new ExpectToken(Token.ASSIGN, "="));
        tokens.add(new ExpectToken(Token.INT, "10"));
        tokens.add(new ExpectToken(Token.SEMICOLON, ";"));
        tokens.add(new ExpectToken(Token.LET, "let"));
        tokens.add(new ExpectToken(Token.IDENT, "add"));
        tokens.add(new ExpectToken(Token.ASSIGN, "="));
        tokens.add(new ExpectToken(Token.FUNCTION, "fn"));
        tokens.add(new ExpectToken(Token.LPAREN, "("));
        tokens.add(new ExpectToken(Token.IDENT, "x"));
        tokens.add(new ExpectToken(Token.COMMA, ","));
        tokens.add(new ExpectToken(Token.IDENT, "y"));
        tokens.add(new ExpectToken(Token.RPAREN, ")"));
        tokens.add(new ExpectToken(Token.LBRACE, "{"));
        tokens.add(new ExpectToken(Token.IDENT, "x"));
        tokens.add(new ExpectToken(Token.PLUS, "+"));
        tokens.add(new ExpectToken(Token.IDENT, "y"));
        tokens.add(new ExpectToken(Token.SEMICOLON, ";"));
        tokens.add(new ExpectToken(Token.RBRACE, "}"));
        tokens.add(new ExpectToken(Token.SEMICOLON, ";"));
        tokens.add(new ExpectToken(Token.LET, "let"));
        tokens.add(new ExpectToken(Token.IDENT, "result"));
        tokens.add(new ExpectToken(Token.ASSIGN, "="));
        tokens.add(new ExpectToken(Token.IDENT, "add"));
        tokens.add(new ExpectToken(Token.LPAREN, "("));
        tokens.add(new ExpectToken(Token.IDENT, "five"));
        tokens.add(new ExpectToken(Token.COMMA, ","));
        tokens.add(new ExpectToken(Token.IDENT, "ten"));
        tokens.add(new ExpectToken(Token.RPAREN, ")"));
        tokens.add(new ExpectToken(Token.SEMICOLON, ";"));
        tokens.add(new ExpectToken(Token.EOF, ""));
        Lexer l = new Lexer(input);
        // for(int i = 0;i<input.length();i++){
        //     Token t = l.NextToken();
        //     Assertions.assertEquals(tokens.get(t.Type),t.Type);
        //     Assertions.assertEquals(tokens.get(t.Type),t.Literal);
        // }
        for (ExpectToken expectedToken : tokens) {
            Token t = l.NextToken();
            System.err.println("Expected: " + expectedToken.Type + " -> " + expectedToken.Literal);
            System.err.println("Got: " + t.Type + " -> " + t.Literal);
    
            Assertions.assertEquals(expectedToken.Type, t.Type);
            Assertions.assertEquals(expectedToken.Literal, t.Literal);
        }
    }
}
