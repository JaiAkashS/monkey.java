import static org.junit.Assert.fail;

import org.junit.Test;

import com.monkeyjava.ast.Ast;
import com.monkeyjava.token.Token;
import com.monkeyjava.token.Token.TokenType;

public class AstTest {
    @Test
    public void TestString(){
        Ast.Program program = new Ast().new Program();
        program.Statements.add(new Ast().new LetStatement(new Token(TokenType.LET,"let"),new Ast().new Identifier(new Token(TokenType.IDENT,"myVar"),"myVar"),(Ast.Expression)new Ast().new Identifier(new Token(TokenType.IDENT,"anotherVar"),"anotherVar")));
        if(!program.string().equals("let myVar = anotherVar;")){
            System.err.println(String.format("program.String() wrong.got= %s ",program.string()));
            fail();
        }
    }
}
