package src.main.java.com.monkeyjava.ast;


import src.main.java.com.monkeyjava.token.*;



public class Ast {
        
    interface Node{
        abstract String TokenLiteral();
    }

    interface Statement extends Node {
        abstract void statementNode();
    }

    interface Expression extends Node {
        abstract void expressionNode();
    }

    public class Program {
        Statement[] Statements;
        public String TokenLiteral(){
            if(this.Statements.length>0){
                return this.Statements[0].TokenLiteral();
            }else{
                return "";
            }
        }
    }

    class Identifier{
        Token Token;
        String Value;
        public void statementNode(){    
        }
        public String TokenLiteral(){
            return this.Token.Literal;
        }
    }

    class LetStatement{
        Token Token;
        Identifier Name;
        Expression Value;


        public void statementNode(){    
        }
        public String TokenLiteral(){
            return this.Token.Literal;
        }
}



}






