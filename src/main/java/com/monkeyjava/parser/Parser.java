package com.monkeyjava.parser;

import com.monkeyjava.token.*;
import com.monkeyjava.token.Token.TokenType;
import com.monkeyjava.lexer.*;

import java.util.ArrayList;
import java.util.HashMap;



import com.monkeyjava.ast.Ast;



@FunctionalInterface
interface prefixParseFn {
    public Ast.Expression parse();
}


@FunctionalInterface
interface infixParseFn {
    public Ast.Expression parse(Ast.Expression Left);
}


public class Parser{
    Lexer l;
    Token curToken;
    Token peekToken;
    ArrayList<String> errors;

    HashMap<TokenType,prefixParseFn> prefixParseFns;
    HashMap<TokenType,infixParseFn> infixParseFns;



    public Parser (Lexer l){
        this.l = l;
        this.nextToken();
        this.nextToken();
        errors = new ArrayList<String>();
    }
    public void nextToken(){
        this.curToken = this.peekToken;
        this.peekToken = this.l.NextToken();
    }
    public Ast.Program ParseProgram(){
        Ast.Program program = new Ast().new Program();
        Ast.Statement stmt;
        while (!this.curTokenIs(TokenType.EOF)) {
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
            case LET:
                return this.parseLetStatement();        
            case RETURN:
                return this.parseReturnStatement();
            default:
                // return this.parseExpressionStatement();
                return null;
        }
    }
    public Ast.LetStatement parseLetStatement(){
        Ast.LetStatement stmt = new Ast().new LetStatement(this.curToken);
        if(!this.expectPeek(TokenType.IDENT)){
            return null;
        }
        stmt.Name = new Ast().new Identifier(this.curToken,this.curToken.Literal);
        if(!this.expectPeek(TokenType.ASSIGN)){
            return null;
        }
        // TODO:We're skipping the expressions until we encouter a semicolon 
        while (!this.curTokenIs(TokenType.SEMICOLON)) {
            this.nextToken();
        }
        return stmt;
    }

    public Ast.ReturnStatement parseReturnStatement(){
        Ast.ReturnStatement rstmt = new Ast().new ReturnStatement();

        this.nextToken();
        //TODO: we're skipping the expressions until we encounter a semicolon

        while (!this.curTokenIs(TokenType.SEMICOLON)) {
            this.nextToken();
        }
        return rstmt;
    }

    // public Ast.ExpressionStatement parseExpressionStatement(){
    //     Ast.ExpressionStatement stmt = new Ast().new ExpressionStatement(this.curToken);
        
    //     stmt.Expression = this.parseExpression(LOWEST);

    //     if (this.peekTokenIs(TokenType.SEMICOLON)) {
    //         this.nextToken();
    //     }
    //     return stmt;
    // }






    public boolean curTokenIs(TokenType TokenType){
        return this.curToken.Type == TokenType; 
    }

    public boolean peekTokenIs(TokenType TokenType){
        return this.peekToken.Type == TokenType;
    }

    public boolean expectPeek(TokenType TokenType){
        if(this.peekTokenIs(TokenType)){
            this.nextToken();
            return true;
        }else{
            this.peekError(TokenType);
            return false;
        }
    }

    public ArrayList<String> Errors(){
        return this.errors;
    }

    public void peekError(TokenType t){
        String msg = String.format("Expected next token to be %s, got %s instead",t,this.peekToken.Type);
        this.errors.add(msg);
    }


    public void registerPrefix(TokenType Type,prefixParseFn fn){
        this.prefixParseFns.put(Type, fn);
    }

    public void registerInfix(TokenType Type,infixParseFn fn){
        this.infixParseFns.put(Type, fn);
    }
}

