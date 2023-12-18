
import java.util.Stack;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;


public class rpneval 
{
//( 3.14 ( 2 3 + ) ( 1.57 sin ) * )
    public static void main(String[] args) {
        String str;
        char c;
        String nstr = "";  //these variables are needed in order to manipulate the string given by the user
        Stack<Double> stackrpn;
        String[] arr = new String[]{""};
        Stack<String> stack = new Stack<>();
        Scanner myrpn = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Welcome to RPN calculator, please enter rpn expression!:");
        String rpninput = myrpn.nextLine();  // Read user input
        
        boolean hasParenthesis = rpninput.startsWith("(") && rpninput.endsWith(")");
        myrpn.close();
        if (hasParenthesis) {
            rpninput = rpninput.substring(1, rpninput.length() - 1);
        }
        // success (stopped here, and tested to make sure the outer most parenthesis were removed, as they aren't needed)
        arr = rpninput.split("\\s");
        
        for (int i = 0; i < arr.length; i++) {
            arr[i] = arr[i].replace(")", "*");
            arr[i] = arr[i].replace("(", "");// we deal with parenthesis here, in math, parenthesis are for multiplication, so we replace the right parenthesis  with a multiplication sign for later evaluation.
        }

        for (String string : arr) {
            if (!string.isEmpty()) {
                stack.push(string);
            }
        }                                   // this section is for giving our string spaces, I was struggling with the computer throwing exceptions if it ran into a something like '3+', so I went with this approach

        String result = "";
        while (!stack.isEmpty()) {
            result += stack.pop() + " ";

        }

        String finalstring = (reverseStringAroundSpaces(result));
        Double d = (evaluate(finalstring));             // We call functions and print out our final result here
        System.out.println(d);
        
    }
    public static String reverseStringAroundSpaces(String result) {
       
        String[] words = result.split(" ");

        // Reverse the words
        Collections.reverse(Arrays.asList(words));

        // Join the words back into a string
        return String.join(" ", words);
    }
    private static Stack<Double> stack = new Stack<>();

    public static double evaluate(String expression) {
        for (String token : expression.split(" ")) {
            if (token.equals("+")) {
                double operand2 = stack.pop();
                double operand1 = stack.pop();
                stack.push(operand1 + operand2);
            } else if (token.equals("-")) {
                double operand2 = stack.pop();
                double operand1 = stack.pop();
                stack.push(operand1 - operand2);
            } else if (token.equals("*")) {
                double operand2 = stack.pop();
                double operand1 = stack.pop();
                stack.push(operand1 * operand2);  // these are all the operations, when a certain symbol is detected, we will pop two strings (numbers) from the stack, and then produce the operation
            } else if (token.equals("^")) {
                double operand2 = stack.pop();
                double operand1 = stack.pop();
                stack.push(Math.pow(operand2, operand1));
            } else if (token.equals("sin")) {
                double operand3 = stack.pop();
                stack.push(Math.sin(operand3));
            } else if (token.equals("cos")) {
                double operand4 = stack.pop();
                stack.push(Math.cos(operand4)); // our trig functions need only one operand, so we will only pop out one opperand
            } else if (token.equals("tan")) {
                double operand5 = stack.pop();
                stack.push(Math.tan(operand5));
            } else {
                stack.push(Double.parseDouble(token)); // if it's a number, we will return it to the stack.
            }
        }
        return stack.pop();
    }
}

