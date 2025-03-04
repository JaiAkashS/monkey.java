package src.main.java.com.monkeyjava.repl;

import java.util.Scanner;

import src.main.java.com.monkeyjava.token.Token;
import src.main.java.com.monkeyjava.lexer.Lexer;

public class Repl {
    final static String PROMPT = ">>";
    
    public static void start(){
        Scanner sc = new Scanner(System.in);
        while(true){
            System.out.println(PROMPT);
            String scanned = sc.nextLine();
            if(scanned.length()==0){
                return;
            }
            Lexer l = new Lexer(scanned);
            for(Token tok = l.NextToken();tok.Type!=Token.EOF;tok = l.NextToken()){
                System.out.println(String.format("%s : %s",tok.Literal,tok.Type));
            }
            break;
        }
        sc.close();
    }
}
