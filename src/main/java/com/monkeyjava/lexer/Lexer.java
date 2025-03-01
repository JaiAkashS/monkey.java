package src.main.java.com.monkeyjava.lexer;

import src.main.java.com.monkeyjava.token.Token;

public class Lexer {
    String input;
    int position,//Current position in int
        readPosition;//current reading position in input (after current char)
    Character ch;
    public Lexer(String input){
        this.input = input;
        this.readChar();
    }
    public void readChar(){
        if(readPosition>=input.length()){
            ch = 0;
        }
        else{
            ch = input.charAt(readPosition);
        }
        position = readPosition;
        readPosition +=1; 
    }
    public String readIdentifier(){
        int pos = this.position;
        while(Character.isAlphabetic(this.ch) || this.ch == '_'){
            this.readChar();
        }
        return this.input.substring(pos, this.position);
    }

    public Token NextToken(){
        Token tok;
        switch (ch) {
            case '=':
                tok = new Token(Token.ASSIGN,ch);
                break;
            case ';':
                tok = new Token(Token.SEMICOLON,ch);
                break;
            case '(':
                tok = new Token(Token.LPAREN,ch);
                break;
            case ')':
                tok = new Token(Token.RPAREN,ch);
                break;
            case ',':
                tok = new Token(Token.COMMA,ch);
                break;
            case '+':
                tok = new Token(Token.PLUS,ch);
                break;
            case '{':
                tok = new Token(Token.LBRACE,ch);
                break;
            case '}':
                tok = new Token(Token.RBRACE,ch);
                break;
            case 0:
                tok = new Token(Token.EOF,"");
                break;
            default:
                if(Character.isAlphabetic(ch) || ch =='_'){
                    tok = new Token();
                    tok.Literal = this.readIdentifier();
                }
                else{

                    System.err.println("Invalid Token!");
                    tok = new Token();
                    tok = new Token(Token.ILLEGAL,ch);
                }
                break;
        }
        this.readChar();
        return tok;
    }
}


