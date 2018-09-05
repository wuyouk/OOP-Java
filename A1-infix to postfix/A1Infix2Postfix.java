import java.io.*;

public class Infix2Postfix {

	public static void main(String[] args) {
		Infix2Postfix app = new Infix2Postfix();
		app.doConversion();
	}
	
	public void doConversion() {
		// TODO: read infix from input using readInfix(), convert to postfix and print it out
		String[] elem = readInfix();
		String result = "";
		Stack stack = new Stack();
		for (int i = 0; i < elem.length; i++) {
			if (elem[i].charAt(0) == '(') {
				stack.push(elem[i].charAt(0));
			} else if (elem[i].charAt(0) == ')') {
				while (stack.top() != '(')
					result += stack.pop() + " ";
				stack.pop();
			} else if (isSym(elem[i])) {
				if (justPush(stack, elem[i].charAt(0))) {
					stack.push(elem[i].charAt(0));
				} else {
					while (!justPush(stack, elem[i].charAt(0)))
						result += stack.pop() + " ";
					stack.push(elem[i].charAt(0));
				}
			} else {
				result += elem[i] + " ";
			}
		}
		while (stack.top() != ' ') 
			result += stack.pop() + " ";
		System.out.print(result.substring(0, result.length() - 1));
	}
	
	private String [] readInfix() {
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in)); 
		String line;
		try {
			System.out.print("Input infix: ");
			line = reader.readLine();
			return line.split(" ");
		} catch (IOException e) {
			System.err.println("Error reading from input");
		}
		
		// return empty array if failed
		return new String[] { };
	}
	
	private boolean isSym(String str) {
		if (str.length() > 1) return false;
		if (str.charAt(0) >= '0' && str.charAt(0) <= '9') return false;
		return true;
	}
	
	private int changeToNum(char c) {
		if (c == '+' || c == '-') return 1;
		return 2;
	}
	
	private boolean lessThan(char c1, char c2) {
		return changeToNum(c1) < changeToNum(c2);
	}
	
	private boolean justPush(Stack stack, char c) {
		return stack.top() == ' ' || stack.top() == '(' || lessThan(stack.top(), c);
	}

}

class Stack {
	private char[] arr;
	private int index;
	Stack() {
		arr = new char[100];
		index = -1;
	}
	public char top() {
		if (index == -1) return ' ';
		return arr[index];
	}
	public void push(char c) {
		arr[++index] = c;
	}
	public char pop() {
		if (index == -1) return ' ';
		return arr[index--];
	}
}
