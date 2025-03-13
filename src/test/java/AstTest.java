package src.test.java;

import static org.junit.Assert.fail;

import java.util.ArrayList;

import javax.sql.StatementEventListener;

import org.junit.Test;

import src.main.java.com.monkeyjava.ast.Ast;
import src.main.java.com.monkeyjava.token.Token;

public class AstTest {
    @Test
    public void TestString(){
        Ast.Program program = new Ast().new Program();
        program.Statements.add(new Ast().new LetStatement(new Token(Token.LET,"let"),new Ast().new Identifier(new Token(Token.IDENT,"myVar"),"myVar"),(Ast.Expression)new Ast().new Identifier(new Token(Token.IDENT,"anotherVar"),"anotherVar")));
        if(!program.string().equals("let myVar = anotherVar;")){
            System.err.println(String.format("program.String() wrong.got= %s ",program.string()));
            fail();
        }
    }
}
