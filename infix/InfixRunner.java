/*
InfixRunner.java

Licensed under MIT/X11-License. License text can be found in LICENSE file in root folder.

*/
package infix;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InfixRunner {

	public static void main(String[] args) {
		String term = "(5)/2*2/(1+4)*(66/11)";
		term = "((3 + 4) * (5 + 2))";

		try {
		     BufferedReader in = new BufferedReader(
		              new InputStreamReader(System.in) );
		     term = in.readLine();
		     System.out.println(term);
		   } catch( IOException ex ) {
		     System.out.println("Konnte nicht vom Prompt lesen.");
		   }
		
		InfixInterpreter interpreter = new InfixInterpreter();
		
		double result = interpreter.calc(term);
		
		System.out.println(result);
		
	}
}
