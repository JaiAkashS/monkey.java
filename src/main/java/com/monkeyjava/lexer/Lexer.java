package src.main.java.com.monkeyjava.lexer;

import src.main.java.com.monkeyjava.token.Token;

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
                    tok = new Token(Token.EQ,input.substring(position,readPosition+1));
                    this.readChar();
                }else{
                    tok = new Token(Token.ASSIGN,ch);
                }
                break;
            case '+':
                tok = new Token(Token.PLUS,ch);
                break;
            case '-':
                tok = new Token(Token.MINUS,ch);
                break;
            case '!':
                if((this.peek()).equals("=")){
                    tok = new Token(Token.NOT_EQ,input.substring(position,readPosition+1));
                    this.readChar();
                }else{
                    tok = new Token(Token.BANG,ch);
                }
                break;
            case '/':
                tok = new Token(Token.SLASH,ch);
                break;
            case '*':
                tok = new Token(Token.ASTERISK,ch);
                break;
            case '<':
                tok = new Token(Token.LT,ch);
                break;
            case '>':
                tok = new Token(Token.GT,ch);
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
                    tok.Type = Token.LookupIdent(tok.Literal);
                    return tok;
                }
                else if(Character.isDigit(ch)){
                    tok = new Token();
                    tok.Type = Token.INT;
                    tok.Literal = this.readNumber();
                    return tok;
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


