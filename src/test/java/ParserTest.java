package src.test.java;

import static org.junit.Assert.fail;

import java.util.ArrayList;

import org.junit.Test;
import src.main.java.com.monkeyjava.ast.Ast;
import src.main.java.com.monkeyjava.lexer.Lexer;
import src.main.java.com.monkeyjava.parser.Parser;
import src.main.java.com.monkeyjava.token.Token;

public class ParserTest {
    @Test
    public void TestLetStatements(){
        String input = "let x  5;\n" + 
                        "let y = 10;\n" + 
                        "let foobar = 838383;";
        Lexer l = new Lexer(input);
        Parser p = new Parser(l);
        Ast.Program program;
        program = p.ParseProgram();
        checkParserErrors(p);
        if(program == null){
            System.err.println("ParseProgram() returned null");            
        }
        if(program.Statements.size() != 3){
            System.err.println("program.Statements does not contain 3 statements got " + program.Statements.size());
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
        if(!s.TokenLiteral().equals("let")){
            System.err.println("s.TokenLiteral is not 'let' got: "+ s.TokenLiteral());
            return false;
        }
        Ast.LetStatement letstmt;
        if(s instanceof Ast.LetStatement){ 
            letstmt = (Ast.LetStatement)s;
        }else {
            System.err.println("s not ast.LetStatement got:" + s);
            letstmt = null;
            return false;
        }
        
        if(!letstmt.Name.TokenLiteral().equals(name)){
            System.err.println("s.Name not " + name +" got "+ letstmt.Name);
            return false;
        }
        return true;
    }
    public void checkParserErrors(Parser p) {
        ArrayList<String> errors = p.Errors();
        if(errors.size() == 0){
            System.err.println("Empty errors");
            return;
        }        
        System.err.println(String.format("Parser has %d errors",errors.size()));
        for (String msg : errors) {
            System.err.println(String.format("Parser error : %s", msg));
        }
        fail();
    }
    @Test
    public void TestReturnStatements(){
        String input = "return 5;\n" + 
                        "return 10;\n" +
                        "return 993322;";
        Lexer l = new Lexer(input);
        Parser p = new Parser(l);
        Ast.Program program = p.ParseProgram();
        checkParserErrors(p);
        if(program.Statements.size() != 3){
            System.err.println(String.format("praogram.Statment does not contain 3 statements. got %d",program.Statements.size()));
        }
        for (Ast.Statement stmt : program.Statements) {
            Ast.ReturnStatement rstmt;
            if(stmt instanceof Ast.ReturnStatement){
                rstmt = (Ast.ReturnStatement)stmt;
                continue;
            }else{
                rstmt = null;
            }
            if(rstmt == null || !rstmt.TokenLiteral().equals("return")){
                System.err.println(String.format("returnStmt.TokenLiteral not 'return' or null,got %s",rstmt.TokenLiteral()));
            }
        }
    } 



}
