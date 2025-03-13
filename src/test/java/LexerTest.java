import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import com.monkeyjava.lexer.Lexer;
import com.monkeyjava.token.Token;

public class LexerTest {
    @Test
    public void TestNextToken(){
        String input = "let five = 5;\n" +
                       "let ten = 10;\n" +
                       "let add != fn(x, y) {\n" +
                       "    x + y;\n" +
                       "};\n" +
                       "let result == add(five, ten);\n" +
                       "!-/*5; \n"+
                       "5 < 10 > 5; \n" +
                       "if (5 < 10) { \n" +
                       "return true; \n" + 
                       "} else {\n" +
                       "return false;\n"+
                       "} \n";
        List<ExpectToken> tokens = new ArrayList<>();

        tokens.add(new ExpectToken(Token.TokenType.LET, "let"));
        tokens.add(new ExpectToken(Token.TokenType.IDENT, "five"));
        tokens.add(new ExpectToken(Token.TokenType.ASSIGN, "="));
        tokens.add(new ExpectToken(Token.TokenType.INT, "5"));
        tokens.add(new ExpectToken(Token.TokenType.SEMICOLON, ";"));
        tokens.add(new ExpectToken(Token.TokenType.LET, "let"));
        tokens.add(new ExpectToken(Token.TokenType.IDENT, "ten"));
        tokens.add(new ExpectToken(Token.TokenType.ASSIGN, "="));
        tokens.add(new ExpectToken(Token.TokenType.INT, "10"));
        tokens.add(new ExpectToken(Token.TokenType.SEMICOLON, ";"));
        tokens.add(new ExpectToken(Token.TokenType.LET, "let"));
        tokens.add(new ExpectToken(Token.TokenType.IDENT, "add"));
        tokens.add(new ExpectToken(Token.TokenType.NOT_EQ, "!="));
        tokens.add(new ExpectToken(Token.TokenType.FUNCTION, "fn"));
        tokens.add(new ExpectToken(Token.TokenType.LPAREN, "("));
        tokens.add(new ExpectToken(Token.TokenType.IDENT, "x"));
        tokens.add(new ExpectToken(Token.TokenType.COMMA, ","));
        tokens.add(new ExpectToken(Token.TokenType.IDENT, "y"));
        tokens.add(new ExpectToken(Token.TokenType.RPAREN, ")"));
        tokens.add(new ExpectToken(Token.TokenType.LBRACE, "{"));
        tokens.add(new ExpectToken(Token.TokenType.IDENT, "x"));
        tokens.add(new ExpectToken(Token.TokenType.PLUS, "+"));
        tokens.add(new ExpectToken(Token.TokenType.IDENT, "y"));
        tokens.add(new ExpectToken(Token.TokenType.SEMICOLON, ";"));
        tokens.add(new ExpectToken(Token.TokenType.RBRACE, "}"));
        tokens.add(new ExpectToken(Token.TokenType.SEMICOLON, ";"));
        tokens.add(new ExpectToken(Token.TokenType.LET, "let"));
        tokens.add(new ExpectToken(Token.TokenType.IDENT, "result"));
        tokens.add(new ExpectToken(Token.TokenType.EQ, "=="));
        tokens.add(new ExpectToken(Token.TokenType.IDENT, "add"));
        tokens.add(new ExpectToken(Token.TokenType.LPAREN, "("));
        tokens.add(new ExpectToken(Token.TokenType.IDENT, "five"));
        tokens.add(new ExpectToken(Token.TokenType.COMMA, ","));
        tokens.add(new ExpectToken(Token.TokenType.IDENT, "ten"));
        tokens.add(new ExpectToken(Token.TokenType.RPAREN, ")"));
        tokens.add(new ExpectToken(Token.TokenType.SEMICOLON, ";"));
        tokens.add(new ExpectToken(Token.TokenType.BANG, "!"));
        tokens.add(new ExpectToken(Token.TokenType.MINUS, "-"));
        tokens.add(new ExpectToken(Token.TokenType.SLASH, "/"));
        tokens.add(new ExpectToken(Token.TokenType.ASTERISK, "*"));
        tokens.add(new ExpectToken(Token.TokenType.INT, "5"));
        tokens.add(new ExpectToken(Token.TokenType.SEMICOLON, ";"));
        tokens.add(new ExpectToken(Token.TokenType.INT, "5"));
        tokens.add(new ExpectToken(Token.TokenType.LT, "<"));
        tokens.add(new ExpectToken(Token.TokenType.INT, "10"));
        tokens.add(new ExpectToken(Token.TokenType.GT, ">"));
        tokens.add(new ExpectToken(Token.TokenType.INT, "5"));
        tokens.add(new ExpectToken(Token.TokenType.SEMICOLON, ";"));
        tokens.add(new ExpectToken(Token.TokenType.IF, "if"));
        tokens.add(new ExpectToken(Token.TokenType.LPAREN, "("));
        tokens.add(new ExpectToken(Token.TokenType.INT, "5"));
        tokens.add(new ExpectToken(Token.TokenType.LT, "<"));
        tokens.add(new ExpectToken(Token.TokenType.INT, "10"));
        tokens.add(new ExpectToken(Token.TokenType.RPAREN, ")"));
        tokens.add(new ExpectToken(Token.TokenType.LBRACE, "{"));
        tokens.add(new ExpectToken(Token.TokenType.RETURN, "return"));
        tokens.add(new ExpectToken(Token.TokenType.TRUE, "true"));
        tokens.add(new ExpectToken(Token.TokenType.SEMICOLON, ";"));
        tokens.add(new ExpectToken(Token.TokenType.RBRACE, "}"));
        tokens.add(new ExpectToken(Token.TokenType.ELSE, "else"));
        tokens.add(new ExpectToken(Token.TokenType.LBRACE, "{"));
        tokens.add(new ExpectToken(Token.TokenType.RETURN, "return"));
        tokens.add(new ExpectToken(Token.TokenType.FALSE, "false"));
        tokens.add(new ExpectToken(Token.TokenType.SEMICOLON, ";"));
        tokens.add(new ExpectToken(Token.TokenType.RBRACE, "}"));
        tokens.add(new ExpectToken(Token.TokenType.EOF, ""));
        Lexer l = new Lexer(input);
        // for(int i = 0;i<input.length();i++){
        //     Token t = l.NextToken();
        //     Assertions.assertEquals(tokens.get(t.Type),t.Type);
        //     Assertions.assertEquals(tokens.get(t.Type),t.Literal);
        // }
        for (ExpectToken expectedToken : tokens) {
            Token t = l.NextToken();
            // System.err.println("Expected: " + expectedToken.Type + " -> " + expectedToken.Literal);
            // System.err.println("Got: " + t.Type + " -> " + t.Literal);
    
            Assertions.assertEquals(expectedToken.Type, t.Type);
            Assertions.assertEquals(expectedToken.Literal, t.Literal);
        }
    }
}
