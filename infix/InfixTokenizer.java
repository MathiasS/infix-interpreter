/*
InfixTokenizer.java

Licensed under MIT/X11-License. License text can be found in LICENSE file in root folder.

*/
package infix;

import java.util.ArrayList;
import java.util.Enumeration;

public class InfixTokenizer implements Enumeration<String> {
	
	private ArrayList<String> 	tokens 	= new ArrayList<String>();
	private String 				buffer 	= "";
	private int					pointer	= 0;
	
	public InfixTokenizer(String term){
		// split term on "()*/+-"
		char[] chars = term.toCharArray();
		for(int i = 0; i < chars.length; i++){
			switch (chars[i]) {
			case '(':
				tokenFiller();
				tokens.add("(");
				break;
			case ')':
				tokenFiller();
				tokens.add(")");
				break;
			case '*':
				tokenFiller();
				tokens.add("*");
				break;
			case '/':
				tokenFiller();
				tokens.add("/");
				break;
			case '+':
				tokenFiller();
				tokens.add("+");
				break;
			case '-':
				tokenFiller();
				tokens.add("-");
				break;
			default:
				buffer += chars[i];
				break;
			}
		}
	}
	
	private void tokenFiller(){
		if (buffer.length() > 0) tokens.add(buffer);
		buffer = "";
	}

	@Override
	public boolean hasMoreElements() {
		if (pointer < tokens.size()) return true;
		return false;
	}
	
	public boolean hasMoreTokens() {
		// kept for compatibility to StringTokenizer
		return hasMoreElements();
	}

	@Override
	public String nextElement() {
		String element = tokens.get(pointer);
		pointer++;
		return element;
	}
	
	public String nextToken() {
		// kept for compatibility to StringTokenizer
		return nextElement();
	}

}