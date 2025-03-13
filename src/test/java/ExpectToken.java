package src.test.java;

import src.main.java.com.monkeyjava.token.Token.TokenType;

public class ExpectToken {
    TokenType Type;
    String Literal;

    protected ExpectToken(TokenType type, String literal) {
        this.Type = type;
        this.Literal = literal;
    }
}
