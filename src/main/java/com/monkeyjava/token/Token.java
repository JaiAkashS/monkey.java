package com.monkeyjava.token;

import java.util.HashMap;

public class Token {
    public enum TokenType {
        ILLEGAL("ILLEGAL"),
        IDENT("IDENT"),
        INT("INT"),
        ASSIGN("="),
        PLUS("+"),
        MINUS("-"),
        BANG("!"),
        ASTERISK("*"),
        SLASH("/"),
        EQ("=="),
        NOT_EQ("!="),
        LT("<"),
        GT(">"),
        COMMA(","),
        SEMICOLON(";"),
        LPAREN("("),
        RPAREN(")"),
        LBRACE("{"),
        RBRACE("}"),
        FUNCTION("fn"),
        EOF("EOF"),
        LET("let"),
        RETURN("return"),
        IF("if"),
        ELSE("else"),
        TRUE("true"),
        FALSE("false");

        private final String value;

        TokenType(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    public TokenType Type;
    public String Literal;

    public static HashMap<String, TokenType> keywords = new HashMap<>();

    static {
        keywords.put("fn", TokenType.FUNCTION);
        keywords.put("let", TokenType.LET);
        keywords.put("true", TokenType.TRUE);
        keywords.put("false", TokenType.FALSE);
        keywords.put("return", TokenType.RETURN);
        keywords.put("if", TokenType.IF);
        keywords.put("else", TokenType.ELSE);
    }

    public Token() {
        this.Type = TokenType.ILLEGAL;
        this.Literal = "";
    }

    public Token(TokenType Type, String Literal) {
        this.Type = Type;
        this.Literal = Literal;
    }

    public Token(TokenType Type, Character Literal) {
        this.Type = Type;
        this.Literal = Character.toString(Literal);
    }

    public static TokenType LookupIdent(String Ident) {
        return keywords.getOrDefault(Ident, TokenType.IDENT);
    }
}
