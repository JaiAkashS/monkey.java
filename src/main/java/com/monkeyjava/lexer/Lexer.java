package com.monkeyjava.lexer;

import com.monkeyjava.token.Token;

public class Lexer {
    String input;
    int position,//Current position in int
        readPosition;//current reading position in input (after current char)
    Character ch;
    public Lexer(String input){
        this.input = input;
        this.position = this.readPosition = 0;
        this.readChar();
    }
    public void readChar(){
        if(this.readPosition>=this.input.length()){
            this.ch = 0;
        }
        else{
            this.ch = this.input.charAt(readPosition);
        }
        this.position = this.readPosition;
        this.readPosition +=1; 
    }
    public String readIdentifier(){
        int pos = this.position;
        while(Character.isAlphabetic(this.ch) || this.ch == '_'){
            this.readChar();
        }
        return this.input.substring(pos, this.position);
    }

    public String readNumber(){
        int pos = this.position;
        while(Character.isDigit(this.ch)){
            this.readChar();
        }
        return this.input.substring(pos,this.position);
    }
    
    
    
    
    public void skipWhitepace(){
        while (this.ch.equals(' ') || this.ch.equals('\n') || this.ch.equals('\t')|| this.ch.equals('\r') ) {
            this.readChar();
        }
    }
    public String peek(){
        if(readPosition<input.length()){
            return input.substring(readPosition,readPosition+1);
        }
        return "0";
    }




    public Token NextToken(){
        Token tok;
        
        this.skipWhitepace();

        switch (ch) {
            case '=':
                if((this.peek()).equals("=")){
                    tok = new Token(Token.TokenType.EQ,input.substring(position,readPosition+1));
                    this.readChar();
                }else{
                    tok = new Token(Token.TokenType.ASSIGN,ch);
                }
                break;
            case '+':
                tok = new Token(Token.TokenType.PLUS,ch);
                break;
            case '-':
                tok = new Token(Token.TokenType.MINUS,ch);
                break;
            case '!':
                if((this.peek()).equals("=")){
                    tok = new Token(Token.TokenType.NOT_EQ,input.substring(position,readPosition+1));
                    this.readChar();
                }else{
                    tok = new Token(Token.TokenType.BANG,ch);
                }
                break;
            case '/':
                tok = new Token(Token.TokenType.SLASH,ch);
                break;
            case '*':
                tok = new Token(Token.TokenType.ASTERISK,ch);
                break;
            case '<':
                tok = new Token(Token.TokenType.LT,ch);
                break;
            case '>':
                tok = new Token(Token.TokenType.GT,ch);
                break;
            case ';':
                tok = new Token(Token.TokenType.SEMICOLON,ch);
                break;
            case '(':
                tok = new Token(Token.TokenType.LPAREN,ch);
                break;
            case ')':
                tok = new Token(Token.TokenType.RPAREN,ch);
                break;
            case ',':
                tok = new Token(Token.TokenType.COMMA,ch);
                break;
            case '{':
                tok = new Token(Token.TokenType.LBRACE,ch);
                break;
            case '}':
                tok = new Token(Token.TokenType.RBRACE,ch);
                break;
            case 0:
                tok = new Token(Token.TokenType.EOF,"");
                break;
            default:
                if(Character.isAlphabetic(ch) || ch =='_'){
                    tok = new Token();
                    tok.Literal = this.readIdentifier();
                    tok.Type = Token.LookupIdent(tok.Literal);
                    return tok;
                }
                else if(Character.isDigit(ch)){
                    tok = new Token();
                    tok.Type = Token.TokenType.INT;
                    tok.Literal = this.readNumber();
                    return tok;
                }
                else{
                    System.err.println("Invalid Token!");
                    tok = new Token();
                    tok = new Token(Token.TokenType.ILLEGAL,ch);
                }
                break;
        }
        this.readChar();
        return tok;
    }
}


