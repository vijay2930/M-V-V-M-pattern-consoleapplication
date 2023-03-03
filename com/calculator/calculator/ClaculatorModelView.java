package com.calculator.calculator;

import com.calculator.dto.Expression;
import com.calculator.repository.Repository;
import com.calculator.util.Compute;
import com.calculator.util.Read;

import java.util.List;
import java.util.Stack;

public class CalculatorModelView {
    private CalculatorView calculatorView;
    private Repository repository;
    public CalculatorModelView(CalculatorView calculatorView) {
        this.calculatorView =calculatorView;
        repository=Repository.getInstance();
    }

    public void getMyHistory(String userName, String date) {
        List<Expression> expressionList=repository.getMyHistory(userName,date);
        if(expressionList==null){
            calculatorView.getMyHistoryFailed("No match found for the given date");
        }else {
            calculatorView.getMyHistorySuccessfully(expressionList);
        }
    }

    public void getResult(String userName, String exp) {
        this.computeFunctions(userName,exp);
    }

    private void computeFunctions(String userName, String exp) {
        evaluate(userName,exp);
    }
    private String compute(String s) {
        String res="";
        int i=s.length()-1;
        while (s.length()>0 && i>-1){
            if(i-4>=0 && s.substring(i-4,i).equals("sqrt")){
                s=
                        s.substring(0,i-4)+ Compute.sqrt(Read.Double(s.substring(i+1,s.indexOf(')',i))))+s.substring(s.indexOf(')',i)+1);
                i=s.length()-1;
            }
            else if(i-3>=0 && s.substring(i-3,i).equals("log")){
                s=
                        s.substring(0,i-3)+Compute.log(Read.Double(s.substring(i+1,s.indexOf(')',i))))+s.substring(s.indexOf(')',i)+1);
                i=s.length()-1;
            }else if(i-3>=0 && s.substring(i-3,i).equals("sin")){
                s=
                        s.substring(0,i-3)+Compute.sine(Read.Double(s.substring(i+1,s.indexOf(')',i))))+s.substring(s.indexOf(')',i)+1);
                i=s.length()-1;
            }else if(i-3>=0 && s.substring(i-3,i).equals("cos")){
                s=
                        s.substring(0,i-3)+Compute.cosine(Read.Double(s.substring(i+1,s.indexOf(')',i))))+s.substring(s.indexOf(')',i)+1);
                i=s.length()-1;
            }else if(i-3>=0 && s.substring(i-3,i).equals("tan")){
                s=
                        s.substring(0,i-3)+Compute.tangent(Read.Double(s.substring(i+1,s.indexOf(')',i))))+s.substring(s.indexOf(')',i)+1);
                i=s.length()-1;
            } else if (i-4>=0 && s.substring(i-4,i).equals("asin")) {
                s=
                        s.substring(0,i-4)+Compute.arcSine(Read.Double(s.substring(i+1,s.indexOf(')',i))))+s.substring(s.indexOf(')',i)+1);
            } else if (i-4>=0 && s.substring(i-4,i).equals("acos")) {
                s=
                        s.substring(0,i-4)+Compute.arcCosine(Read.Double(s.substring(i+1,s.indexOf(')',i))))+s.substring(s.indexOf(')',i)+1);
            }else if (i-4>=0 && s.substring(i-4,i).equals("atan")) {
                s=
                        s.substring(0,i-4)+Compute.arcTangent(Read.Double(s.substring(i+1,s.indexOf(')',i))))+s.substring(s.indexOf(')',i)+1);
            }
            i--;
        }
        return s;
    }

    private static boolean isOperator(char c) {
        return c=='+'||c=='/'||c=='-'||c=='*'||c=='%'||c=='^';
    }

    public void evaluate(String username,String expression) {
        Stack<Double> operands = new Stack<>();
        Stack<Character> operators = new Stack<>();
        expression=compute(expression);
        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            if(isNotAValidSymbol(c)){
                repository.addExpression(username,expression,"Invalid Expression");
                calculatorView.getResultFailed("InValid Expression.");
            }
            if (Character.isDigit(c)) {
                String temp= "";
                double operand=0;
                while ((i < expression.length() && Character.isDigit(expression.charAt(i))) || (i< expression.length() && expression.charAt(i) == '.'))
                {
                    temp+=expression.charAt(i);
                    i++;
                }
                operand=Read.Double(temp);
                i--;
                operands.push(operand);
            } else
            if (isOperator(c)) {
                while (!operators.isEmpty() && precedence(operators.peek()) >= precedence(c)) {
                    evaluateTop(operands, operators);
                }
                operators.push(c);
            } else
            if (c == '(') {
                operators.push(c);
            } else
            if (c == ')') {
                while (!operators.isEmpty() && operators.peek() != '(') {
                    evaluateTop(operands, operators);
                }
                operators.pop();
            }
        }

        while (!operators.isEmpty()) {
            evaluateTop(operands, operators);
        }
        String res=Read.str(operands.pop());
        repository.addExpression(username,expression,res);
        calculatorView.getResultSuccessfully(res);
    }

    private boolean isNotAValidSymbol(char c) {
        return c=='&'||c=='_'||c=='#'||c=='@'||c=='!'||c=='`'||c=='~'||c=='$'||c=='<'||c=='>'||c=='?'||c=='\\'||c=='|';
    }

    private void evaluateTop(Stack<Double> operands, Stack<Character> operators) {
        char op = operators.pop();
        double b = operands.pop();
        double a = operands.pop();
        double result = applyOperator(op, a, b);
        operands.push(result);
    }

    private double applyOperator(char op,double a, double b) {
        switch (op) {
            case '+':
                return Compute.addition(a,b);
            case '-':
                return Compute.subtraction(a,b);
            case '*':
                return Compute.multiplication(a,b);
            case '/':
                return Compute.division(a,b);
            case '%':
                return Compute.modulus(a,b);
            case '^':
                return Compute.pow(a,b);
            default:
                throw new IllegalArgumentException("Invalid operator: " + op);
        }
    }

    private double precedence(char op) {
        switch (op) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
            case '%':
                return 2;
            case '^':
                return 3;
            case '(':
            case ')':
                return 0;
            default:
                throw new IllegalArgumentException("Invalid operator: " + op);
        }
    }
}
