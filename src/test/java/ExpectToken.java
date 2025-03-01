package src.test.java;

public class ExpectToken {
    String Type;
    String Literal;

    protected ExpectToken(String type, String literal) {
        this.Type = type;
        this.Literal = literal;
    }
}
