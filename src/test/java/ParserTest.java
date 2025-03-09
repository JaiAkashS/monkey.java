package src.test.java;


import javax.lang.model.type.NullType;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import src.main.java.com.monkeyjava.ast.Ast;
import src.main.java.com.monkeyjava.ast.Ast.LetStatement;
import src.main.java.com.monkeyjava.lexer.Lexer;
import src.main.java.com.monkeyjava.parser.Parser;

public class ParserTest {
    @Test
    public void TestLetStatements(){
        String input = "let x = 5;\n" + //
                        "let y = 10;\n" + //
                        "let foobar = 838383;";
        Lexer l = new Lexer(input);
        Parser p = new Parser(l);
        Ast.Program program = p.ParseProgram();

        if(program == null){
            System.err.println("ParseProgram() returned null");            
        }
        if(program.Statements.size() != 3){
            System.err.println("program.Statements does not contain 3 statements got" + program.Statements.size());
        }
        String[] expectIdents = {"x","y","foobar"};
        for(int i =0;i<program.Statements.size();i++){
            Ast.Statement stmt = program.Statements.get(i);
            if(!testLetStatements(stmt,expectIdents[i])){
                return;
            }
        }
    }
    public boolean testLetStatements(Ast.Statement s,String name){
        if(s.TokenLiteral() != "let"){
            System.err.println("s.TokenLiteral is not 'let' got: "+ s.TokenLiteral());
            return false;
        }
        Ast.LetStatement letstmt;
        if(s instanceof Ast.LetStatement){
            letstmt = (Ast.LetStatement) s;
        }else {
            System.err.println("s not ast.LetStatement got:" + s);
            letstmt = null;
            return false;
        }
        
        if(letstmt.Name.TokenLiteral() != name){
            System.err.println("s.Name not " + name +"got"+letstmt.Name);
            return false;
        }
        return true;
    }
}
