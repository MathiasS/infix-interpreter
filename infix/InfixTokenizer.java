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
		// to implement right-associativity exponents needs to be in brackets
		for(int i = 0; i < term.length(); i++){
			String character = term.substring(i,i+1);
			if(character.equals("^")){
				term = term.substring(0, i+1) + "(" + term.substring(i+1) + ")";
				i+=2;
			}
		}
		// split term on "()*/+-^"
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
				if ((i == 0 && isOperator(chars[i+1])) || chars[i+1] == '('){
					tokens.add("-1");
					tokens.add("*");
				} else if (isOperator(chars[i-1])){
					buffer += chars[i];
				} else {
					tokenFiller();
					tokens.add("-");
				}
				break;
			case '^':
				tokenFiller();
				tokens.add("^");
				break;
			default:
				buffer += chars[i];
				break;
			}
		}
	}
	
	private boolean isOperator(char c){
		if (c == '+' || c == '-' || c == '*' || c == '/' || c == '(' || c == '^') return true;
		return false;
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
