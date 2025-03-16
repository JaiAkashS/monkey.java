package com.monkeyjava.ast;


import java.util.ArrayList;
import com.monkeyjava.token.Token;





public class Ast {
        
    public interface Node{
        abstract String TokenLiteral();
        abstract String string();
    }

    public interface Statement extends Node {
        abstract void statementNode();
    }

    public interface Expression extends Node {
        abstract void expressionNode();
    }

    public class Program {
        public ArrayList<Statement> Statements = new ArrayList<>();
        public Program(){
        }
        public Program(ArrayList<Ast.Statement>Statements){
            this.Statements = Statements;
        }
        
        
        public String TokenLiteral(){
            if(this.Statements.size()>0){
                return this.Statements.get(0).TokenLiteral();
            }else{
                return "";
            }
        }

        public String string(){
            StringBuilder out = new StringBuilder();
            for (Statement stmt : this.Statements) {
                out.append(stmt.string());
            }
            return out.toString();
        }
    }

    public class Identifier implements Expression{
        public Token Token;
        public String Value;
        public Identifier(){
        }
        public Identifier(Token Token,String Value){
            this.Token = Token;
            this.Value = Value;
        }
        public void expressionNode(){    
        }
        public String TokenLiteral(){
            return this.Token.Literal;
        }
        public String string(){
            return this.Value;
        }
    }

    public class LetStatement implements Statement{
        public Token Token;
        public Identifier Name;
        public Expression Value;
        public LetStatement(){
        }
        public LetStatement(Token tok){
            Token = tok;
            Name = new Identifier();
        }
        public LetStatement(Token tok,Identifier Name,Expression Value){
            this.Token = tok;
            this.Name = Name;
            this.Value = Value;
        }
        public void statementNode(){    
        }
        public String TokenLiteral(){
            return this.Token.Literal;
        }
        public String string(){
            StringBuilder out = new StringBuilder();
            out.append(this.TokenLiteral() + " ");
            out.append(this.Name.string() + "");
            out.append(" = ");
            if(this.Value != null){
                out.append(this.Value.string());
            }
            out.append(";");
            return out.toString();
        }
    
    }

    public class ReturnStatement implements Statement{
        public Token Token;
        public Expression ReturnValue;


        public void statementNode(){}
        public String TokenLiteral(){
            return Token.Literal;
        }
        public String string(){
            StringBuilder out = new StringBuilder();
            out.append(this.TokenLiteral() + " ");
            out.append(" = ");
            if(this.ReturnValue != null){
                out.append(this.ReturnValue.string());
            }
            out.append(";");
            return out.toString();
        }
    }

    public class ExpressionStatement implements Statement{
        public Token Token;
        public Expression Expression;
        
        
        public ExpressionStatement(Token curToken){
            this.Token = curToken;
        }
        public void statementNode(){}
        public String TokenLiteral(){
            return Token.Literal;
        }
        public String string(){
            StringBuilder out = new StringBuilder();
            if(this.Expression != null){
                out.append(this.Expression.string());
                return out.toString();
            }
            return "";
        }
        
    }
}






