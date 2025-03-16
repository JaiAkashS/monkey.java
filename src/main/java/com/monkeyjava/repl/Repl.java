package com.monkeyjava.repl;

import java.util.Scanner;

import com.monkeyjava.token.Token;
import com.monkeyjava.token.Token.TokenType;
import com.monkeyjava.lexer.Lexer;

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
            for(Token tok = l.NextToken();tok.Type!=TokenType.EOF;tok = l.NextToken()){
                System.out.println(String.format("TokenType:%s Literal:%s ",tok.Type.getValue(),tok.Literal));
            }
            break;
        }
        sc.close();
    }
}
