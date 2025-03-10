package src.main.java.com.monkeyjava.ast;


import java.util.ArrayList;
import src.main.java.com.monkeyjava.token.*;



public class Ast {
        
    public interface Node{
        abstract String TokenLiteral();
    }

    public interface Statement extends Node {
        abstract void statementNode();
    }

    public interface Expression extends Node {
        abstract void expressionNode();
    }

    public class Program {
        public ArrayList<Statement> Statements = new ArrayList<>();
        public String TokenLiteral(){
            if(this.Statements.size()>0){
                return this.Statements.get(0).TokenLiteral();
            }else{
                return "";
            }
        }
    }

    public class Identifier{
        public Token Token;
        public String Value;
        public Identifier(Token Token,String Value){
            this.Token = Token;
            this.Value = Value;
        }
        public void statementNode(){    
        }
        public String TokenLiteral(){
            return this.Token.Literal;
        }
    }

    public class LetStatement{
        public Token Token;
        public Identifier Name;
        public Expression Value;

        public LetStatement(Token tok){
            Token = tok;
        }
        public void statementNode(){    
        }
        public String TokenLiteral(){
            return this.Token.Literal;
        }
    }



}






