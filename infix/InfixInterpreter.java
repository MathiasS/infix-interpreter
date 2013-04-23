/*
InfixInterpreter.java

Licensed under MIT/X11-License. License text can be found in LICENSE file in root folder.

*/
package infix;

import java.util.Stack;

public class InfixInterpreter {

	public double calc(String term){
		double result = 0.0;
		term = term.replaceAll(" ", "");
		if(!isValidTerm(term)){
			System.out.println("Term invalid");
			return result;
		}
		
		Stack<String> operatorStack = new Stack<String>();
		Stack<Double> valueStack = new Stack<Double>();
		
		String token;
		term = "(" + term + ")";
		InfixTokenizer tokenizer = new InfixTokenizer(term);

		while (tokenizer.hasMoreTokens()){
			token = tokenizer.nextToken();
			Double num = 0.0;
			
			if (token.equals("(")) operatorStack.push(token);
			
			else if (!isOperator(token) && !token.equals(")")) {
			
				num = Double.parseDouble(token);
				valueStack.push(num);
				
			} else if (token.equals(")")){
			
				while (operatorStack.peek().equals("(") == false){
					String operator = (String) operatorStack.pop(); 
					Double val1 = (double) valueStack.pop();
					Double val2 = (double) valueStack.pop();
					num = evaluate(operator, val1, val2);
					valueStack.push(num);	
				}
				operatorStack.pop();
				
			} else if (isOperator(token)) {
			
				while (operatorStack.isEmpty() == false && (getPrio(operatorStack.peek()) >= getPrio(token))){
					String operator = operatorStack.pop();
					double val1 = valueStack.pop();
					double val2 =  valueStack.pop();
					num = evaluate(operator, val1, val2);
					valueStack.push(num);	
				}
				operatorStack.push(token);
			}
		}
		result = (double) valueStack.pop();
		return result;
	}
	
	public double evaluate(String operator, double val1, double val2){
		double result = 0.0;
		if (operator.equals("^")) result = Math.pow(val2, val1);
		if (operator.equals("+")) result = val1+val2;
		if (operator.equals("-")) result = val2-val1;
		if (operator.equals("*")) result = val1*val2;
		if (operator.equals("/")) result = val2/val1;
		return result;
	}
	
	public boolean isOperator(String ch){
		if(ch.equals("+") || ch.equals("-") || ch.equals("*") || ch.equals("/") || ch.equals("^")) return true;
		return false;
	}
	
	public int getPrio(String ch){
		int prio = 0;
		
		if (ch.equals("^"))							prio = 3;
		else if (ch.equals("*") || ch.equals("/"))	prio = 2; 
		else if (ch.equals("+") || ch.equals("-"))	prio = 1;
		else if (ch.equals("(") || ch.equals(")"))	prio = 0;
		
		return prio;
	}
	
	public boolean isValidTerm(String term){
		boolean valid = true;
		
//		//Check for right start
//		if (! (term.matches("^[0-9]") || term.startsWith("(")) ) return false;
//		//check for right end
//		if (! (term.matches("[0-9]$") || term.endsWith(")")) ) return false;
		// number of braces matching
		int tmp = 0;
		for (Character c : term.toCharArray()){
			if (c.equals('(')) tmp++;
			if (c.equals(')')) tmp--;
			
		}
		if (tmp != 0) return false;
		// additional checks ...
		return valid;
	}
}
