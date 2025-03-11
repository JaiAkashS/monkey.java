package src.main.java.com.monkeyjava.parser;

import src.main.java.com.monkeyjava.token.*;
import src.main.java.com.monkeyjava.lexer.*;

import src.main.java.com.monkeyjava.ast.Ast;


public class Parser{
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
        Ast.Program program = new Ast().new Program();
        Ast.Statement stmt;
        while (!this.curTokenIs(Token.EOF)) {
            stmt = this.parseStatement();
            if(stmt != null){
                program.Statements.add(stmt);
            }
            this.nextToken();
        }
        return program;
    }
    public Ast.Statement parseStatement(){
        switch (this.curToken.Type) {
            case (Token.LET):
                return this.parseLetStatement();
            default:
                return null;

        }
    }
    public Ast.LetStatement parseLetStatement(){
        Ast.LetStatement stmt = new Ast().new LetStatement(this.curToken);
        if(!this.expectPeek(Token.IDENT)){
            return null;
        }
        stmt.Name = new Ast().new Identifier(this.curToken,this.curToken.Literal);
        if(!this.expectPeek(Token.ASSIGN)){
            return null;
        }
        // TODO:We're skipping the expressions until we encouter a semicolon 
        while (!this.curTokenIs(Token.SEMICOLON)) {
            this.nextToken();
        }
        return stmt;
    }

    public boolean curTokenIs(String TokenType){
        return this.curToken.Type == TokenType; 
    }

    public boolean peekTokenIs(String TokenType){
        return this.peekToken.Type == TokenType;
    }

    public boolean expectPeek(String TokenType){
        if(this.peekTokenIs(TokenType)){
            this.nextToken();
            return true;
        }else{
            return false;
        }
    }

}

