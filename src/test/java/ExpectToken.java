import com.monkeyjava.token.Token.TokenType;

public class ExpectToken {
    TokenType Type;
    String Literal;

    protected ExpectToken(TokenType type, String literal) {
        this.Type = type;
        this.Literal = literal;
    }
}
