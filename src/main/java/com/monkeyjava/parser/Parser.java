package src.main.java.com.monkeyjava.parser;

import src.main.java.com.monkeyjava.token.*;
import src.main.java.com.monkeyjava.lexer.*;

import org.junit.Test.None;

import src.main.java.com.monkeyjava.ast.*;


class Parser{
    Lexer l;
    Token curToken;
    Token peekToken;
    
    public Parser (Lexer l){
        this.l = l;
        this.nextToken();
        this.nextToken();
    }
    public void nextToken(){
        this.curToken = this.peekToken;
        this.peekToken = this.l.NextToken();
    }
    public Ast.Program ParseProgram(){
        
    }
}