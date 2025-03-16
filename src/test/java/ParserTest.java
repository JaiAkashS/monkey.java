
import static org.junit.Assert.fail;

import java.util.ArrayList;

import org.junit.Test;
import com.monkeyjava.ast.Ast;
import com.monkeyjava.lexer.Lexer;
import com.monkeyjava.parser.Parser;


public class ParserTest {
    @Test
    public void TestLetStatements(){
        String input = "let x = 5;\n" + 
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
            // System.err.println("Empty errors");
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
            Ast.ReturnStatement rstmt = null;
            if(stmt instanceof Ast.ReturnStatement){
                rstmt = (Ast.ReturnStatement)stmt;
            }else{
                System.err.println(String.format("stmt not ast.returnStatmenet. got = %s", stmt));
            }
            if(!rstmt.TokenLiteral().equals("return")){
                System.err.println(String.format("returnStmt.TokenLiteral not 'return' or null,got %s",rstmt.TokenLiteral()));
            }
        }
    } 
    @Test
    public void TestIdentifierExpression(){
        String input = "foobar;";
        Lexer l = new Lexer(input);
        Parser p = new Parser(l);
        Ast.Program program = p.ParseProgram();

        if(program.Statements.size() != 1 ){
            System.err.println(String.format("program has not enough statements. got = %d",program.Statements.size()));
            fail();
        }
        Ast.ExpressionStatement stmt;
        if(program.Statements.get(0) instanceof Ast.ExpressionStatement){
            stmt = (Ast.ExpressionStatement)program.Statements.get(0);
        }else{
            System.err.println(String.format("program.Statement[0] is not a Ast.ExpressionStatement. got = %s" + program.Statements.get(0).toString()));
            fail();
            return;
        }
        Ast.Identifier ident;
        if(stmt.Expression instanceof Ast.ExpressionStatement){
            ident = (Ast.Identifier)stmt.Expression;
        }else{
            ident = null;
            System.err.println(String.format("exp not Ast.Identifier. got =%s",stmt.Expression));
            fail();
        }
        if(!ident.Value.equals("foobar")){
            System.err.println(String.format("ident.Value not %s. got = %s","foobar",ident.Value));
            fail();
        }
        if (!ident.TokenLiteral().equals("foobar")) {
            System.err.println(String.format("ident.TokenLiteral() not %s.got = %s","foobar",ident.TokenLiteral() ));
            fail();
        }
    }
}
