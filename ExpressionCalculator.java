/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actividad3;
import java.text.DecimalFormat;

/**
 *
 * @author e5566291
 */
public class ExpressionCalculator {
    
private static String addSpaces(String exp){

    //Add space padding to operands.
    //https://regex101.com/r/sJ9gM7/73
    exp = exp.replaceAll("(?<=[0-9()])[\\/]", " / ");
    exp = exp.replaceAll("(?<=[0-9()])[\\^]", " ^ ");
    exp = exp.replaceAll("(?<=[0-9()])[\\*]", " * ");
    exp = exp.replaceAll("(?<=[0-9()])[+]", " + "); 
    exp = exp.replaceAll("(?<=[0-9()])[-]", " - ");

    //Keep replacing double spaces with single spaces until your string is properly formatted
    /*while(exp.indexOf("  ") != -1){
        exp = exp.replace("  ", " ");
     }*/
    exp = exp.replaceAll(" {2,}", " ");

       return exp;
}

public static Double evaluate(String expr){

    DecimalFormat df = new DecimalFormat("#.####");

    //Format the expression properly before performing operations
    String expression = addSpaces(expr);

    try {
        //We will evaluate using rule BDMAS, i.e. brackets, division, power, multiplication, addition and
        //subtraction will be processed in following order
        int indexClose = expression.indexOf(")");
        int indexOpen = -1;
        if (indexClose != -1) {
            String substring = expression.substring(0, indexClose);
            indexOpen = substring.lastIndexOf("(");
            substring = substring.substring(indexOpen + 1).trim();
            if(indexOpen != -1 && indexClose != -1) {
                Double result = evaluate(substring);
                expression = expression.substring(0, indexOpen).trim() + " " + result + " " + expression.substring(indexClose + 1).trim();
                return evaluate(expression.trim());
            }
        }

        String operation = "";
        if(expression.indexOf(" / ") != -1){
            operation = "/";
        }else if(expression.indexOf(" ^ ") != -1){
            operation = "^";
        } else if(expression.indexOf(" * ") != -1){
            operation = "*";
        } else if(expression.indexOf(" + ") != -1){
            operation = "+";
        } else if(expression.indexOf(" - ") != -1){ //Avoid negative numbers
            operation = "-";
        } else{
            return Double.parseDouble(expression);
        }

        int index = expression.indexOf(operation);
        if(index != -1){
            indexOpen = expression.lastIndexOf(" ", index - 2);
            indexOpen = (indexOpen == -1)?0:indexOpen;
            indexClose = expression.indexOf(" ", index + 2);
            indexClose = (indexClose == -1)?expression.length():indexClose;
            if(indexOpen != -1 && indexClose != -1) {
                Double lhs = Double.parseDouble(expression.substring(indexOpen, index));
                Double rhs = Double.parseDouble(expression.substring(index + 2, indexClose));
                Double result = null;
                switch (operation){
                    case "/":
                        //Prevent divide by 0 exception.
                        if(rhs == 0){
                            return null;
                        }
                        result = lhs / rhs;
                        break;
                    case "^":
                        result = Math.pow(lhs, rhs);
                        break;
                    case "*":
                        result = lhs * rhs;
                        break;
                    case "-":
                        result = lhs - rhs;
                        break;
                    case "+":
                        result = lhs + rhs;
                        break;
                    default:
                        break;
                }
                if(indexClose == expression.length()){
                    expression = expression.substring(0, indexOpen) + " " + result + " " + expression.substring(indexClose);
                }else{
                    expression = expression.substring(0, indexOpen) + " " + result + " " + expression.substring(indexClose + 1);
                }
                return Double.valueOf(df.format(evaluate(expression.trim())));
            }
        }
    }catch(Exception exp){
        exp.printStackTrace();
    }
    return 0.0;
}
}