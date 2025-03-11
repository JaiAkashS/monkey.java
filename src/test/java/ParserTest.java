package src.test.java;




import org.junit.Test;

import src.main.java.com.monkeyjava.ast.Ast;
import src.main.java.com.monkeyjava.lexer.Lexer;
import src.main.java.com.monkeyjava.parser.Parser;
import src.main.java.com.monkeyjava.token.Token;

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
            // !FIXME :the test does not work properly- the conversion here causes a bug  
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
        System.err.println(String.format("Token %s Name %",s.TokenLiteral(),letstmt.Name));
        return true;
    }
}
