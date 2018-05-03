//Jacob Nave
//10/12/2017
import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;

public class Differentiate
{
    public static void main(String[] args)
    {
        Equation equ = new Equation("x^2 + 2(2x^2 + 3x - 2)^7 + x");
        Equation equ2 = new Equation("x^2 + 3(x^2 + 3x - 2)^2 + x");
        /*for(int rep = 0; rep < equ.getStringTerms().size(); rep++)
        {
            System.out.println(equ.getStringTerms().get(rep));
        }*/
        
        //System.out.println(equ);
        //System.out.println(differentiateEquation(equ));
        //System.out.println(equ2);
        //System.out.println(differentiateEquation(equ2));
        //System.out.println(equ.getSigns());
        //System.out.println(differentiateTerm(equ.getTerms().get(0)));
        //System.out.println(differentiateTerm(equ.getTerms().get(1)));
        //System.out.println(differentiateTerm(equ.getTerms().get(2)));
        //System.out.println(differentiateEquation(equ));
        
        
        //Application Start
        JFrame frame = new JFrame("Differentiation");
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GridBagConstraints c = new GridBagConstraints();
        frame.setLayout(new GridBagLayout());
        
        JTextField text = new JTextField(20);
        JLabel label = new JLabel("Derivative: ");
        Button button = new Button(text, label);
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        frame.add(text, c);
        c.gridx = 1;
        frame.add(button, c);
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 3;
        frame.add(label, c);
        
        frame.setSize(500, 200);
        frame.setVisible(true);
    }
    
    public static String differentiateEquation(Equation equ)
    {
        int currentIndex = 0;
        String signs = equ.getSigns();
        ArrayList<Terms> terms = equ.getTerms();
        String derivative = "";
        for(int rep = 0; rep < signs.length(); rep++)
        {
            derivative += differentiateTerm(terms.get(rep)) + " " + signs.substring(rep, rep +1) + " ";
        }
        derivative += differentiateTerm(terms.get(terms.size() - 1));
        //create string out of derivative of equation
        
        return derivative;
    }
    
    
    //differentiates a single term/quantity
    //NOTE put power rule after quotient and multiplication 
    /*
     * order to check
     * power rule (working)
     * quotient (working w/ normal division. Not w/ compound fractions)
     * multiplication (to do) 
     * parentheses (working)
     * x (working)
     * constant (working)
     */
    public static String differentiateTerm(Terms term)
    {
        String stringTerm = term.toString();
        String derivative = "";
        //power
        if(isToPower(term))
        {
            String coefficient = term.getCoefficient();
            int spot = 0;
            while(isNumber(stringTerm.substring(spot, spot + 1)))
            {
                coefficient += stringTerm.substring(spot, spot + 1);
                spot++;
            }
            
            //NOTE: Check if changing derivative start to spot will fix coefficient issue
            if(stringTerm.substring(stringTerm.lastIndexOf("^") + 1).equals("2"))
            {
                //check whether or not to multiply by derivative of inner (if inner = 1 don't show multiplication)
                if(differentiateTerm(new Quantity(stringTerm.substring(spot, stringTerm.lastIndexOf("^")))).equals("1"))
                {
                    if(!coefficient.equals(""))
                    {
                        int newIntCoeff = Integer.parseInt(coefficient) * Integer.parseInt(stringTerm.substring(stringTerm.lastIndexOf("^") + 1));
                        //change coefficient to string and add it to the front of the derivative (replace coefficient from exponent)
                        String tempDer = newIntCoeff + stringTerm.substring(spot, stringTerm.lastIndexOf("^"));
                        derivative = tempDer;
                    }
                    else
                    {
                        String tempDer = stringTerm.substring(stringTerm.lastIndexOf("^") + 1) + stringTerm.substring(spot, stringTerm.lastIndexOf("^"));
                        derivative = tempDer;
                    }
                }
                else
                {
                    if(!coefficient.equals(""))
                    {
                        int newIntCoeff = Integer.parseInt(coefficient) * Integer.parseInt(stringTerm.substring(stringTerm.lastIndexOf("^") + 1));
                        String tempDer = newIntCoeff + stringTerm.substring(spot, stringTerm.lastIndexOf("^")) + "*" + differentiateTerm(new Quantity(stringTerm.substring(spot, stringTerm.lastIndexOf("^"))));
                        derivative = tempDer;
                    }
                    else
                    {
                        String tempDer = stringTerm.substring(stringTerm.lastIndexOf("^") + 1) + stringTerm.substring(spot, stringTerm.lastIndexOf("^")) + "*" + differentiateTerm(new Quantity(stringTerm.substring(spot, stringTerm.lastIndexOf("^"))));
                        derivative = tempDer;
                
                    }
                }
            }
            else
            {
                if(differentiateTerm(new Quantity(stringTerm.substring(spot, stringTerm.lastIndexOf("^")))).equals("1"))
                {
                    if(!coefficient.equals(""))
                    {
                        int newIntCoeff = Integer.parseInt(coefficient) * Integer.parseInt(stringTerm.substring(stringTerm.lastIndexOf("^") + 1));
                        String tempDer = newIntCoeff + stringTerm.substring(spot, stringTerm.lastIndexOf("^")) + "^" + (Integer.valueOf(stringTerm.substring(stringTerm.lastIndexOf("^") + 1)) - 1);
                        derivative = tempDer;
                    }
                    else
                    {
                        String tempDer = stringTerm.substring(stringTerm.lastIndexOf("^") + 1) + stringTerm.substring(spot, stringTerm.lastIndexOf("^")) + "^" + (Integer.valueOf(stringTerm.substring(stringTerm.lastIndexOf("^") + 1)) - 1);
                        derivative = tempDer;
                
                    }
                    
                }
                else
                {
                    if(!coefficient.equals(""))
                    {
                        int newIntCoeff = Integer.parseInt(coefficient) * Integer.parseInt(stringTerm.substring(stringTerm.lastIndexOf("^") + 1));
                        String tempDer = newIntCoeff + stringTerm.substring(spot, stringTerm.lastIndexOf("^")) + "^" + (Integer.valueOf(stringTerm.substring(stringTerm.lastIndexOf("^") + 1)) - 1) + "*" + differentiateTerm(new Quantity(stringTerm.substring(spot, stringTerm.lastIndexOf("^"))));
                        derivative = tempDer;
                    }
                    else
                    {
                        String tempDer = stringTerm.substring(stringTerm.lastIndexOf("^") + 1) + stringTerm.substring(spot, stringTerm.lastIndexOf("^")) + "^" + (Integer.valueOf(stringTerm.substring(stringTerm.lastIndexOf("^") + 1)) - 1) + "*" + differentiateTerm(new Quantity(stringTerm.substring(spot, stringTerm.lastIndexOf("^"))));
                        derivative = tempDer;
                
                    }
                }
                
            }
        }
        else if(stringTerm.contains("/"))
        {
            derivative += "( (" + trimPar(stringTerm.substring(stringTerm.indexOf("/") + 1)) + ")*(" + differentiateEquation(new Equation(trimPar(stringTerm.substring(0, stringTerm.indexOf("/"))))) + ")" + " - (" + trimPar(stringTerm.substring(0, stringTerm.indexOf("/"))) + ")*(" + 
                differentiateEquation(new Equation(trimPar(stringTerm.substring(stringTerm.indexOf("/") + 1)))) + ") )" + " / ( (" + trimPar(stringTerm.substring(stringTerm.indexOf("/") + 1)) + ")^2 )";
        }
        //parentheses
        else if(stringTerm.contains("("))
        {
            derivative += term.getCoefficient();
            derivative += "(";
            derivative += differentiateEquation(new Equation(stringTerm.substring(1, stringTerm.length() - 1)));
            /*derivative += differentiateTerm(term.getTerms().get(0)) + " ";
            for(int rep = 1; rep < term.getTerms().size() - 1; rep++)
            {
                derivative += (term.getSigns().substring(rep - 1, rep) + " " + differentiateTerm(term.getTerms().get(rep)));
            }
            derivative += " " + term.getSigns().substring(term.getSigns().length() - 1) + " " + differentiateTerm(term.getTerms().get(term.getTerms().size() - 1));
            */
            derivative += ")";
        }
        //x
        else if(stringTerm.contains("x"))
        {
            if(stringTerm.equals("x"))
            {
                derivative = "1"; 
            }
            else
            {
                derivative = stringTerm.substring(0, stringTerm.indexOf("x"));
            }
        }
        else if(isNumber(stringTerm))
        {
            derivative += "0";
        }
        else
        {
            derivative = stringTerm + "`";
        }
        return derivative;
    }
    
    public static boolean isToPower(Terms term)
    {
        String stringTerm = term.toString();
        int pCount = 0; //number of parentheses
        for(int rep = 0; rep < stringTerm.length(); rep++)
        {
            if(stringTerm.substring(rep, rep + 1).equals("("))
            {
                pCount++;
            }
            else if(stringTerm.substring(rep, rep + 1).equals(")"))
            {
                pCount--;
            }
            else if(pCount == 0 && stringTerm.substring(rep, rep + 1).equals("^"))
            {
                return true;
            }
        }
        return false;
    }
    
    
    public static boolean isNumber(String num)
    {
        String[] numArray = new String[10];
        for(int rep = 0; rep < numArray.length; rep++)
        {
            numArray[rep] = String.valueOf(rep);
        }
        for(int rep = 0; rep < num.length(); rep++)
        {
            if(!arrayContains(numArray, num.substring(rep, rep + 1)))
            {
                return false;
            }
        }
        return true;
    }
    
    public static boolean arrayContains(String[] array, String str)
    {
        for(int rep = 0; rep < array.length; rep++)
        {
            if(array[rep].equals(str))
            {
                return true;
            }
        }
        return false;
    }
    
    //cuts off the parentheses on each end of a string
    public static String trimPar(String str)
    {
        String newString = str;
        System.out.println(newString);
        newString = newString.trim();
        
        if(newString.substring(0, 1).equals("("))
        {
            newString = newString.substring(1);
        }
        
        if(newString.substring(newString.length() - 1).equals(")"))
        {
            newString = newString.substring(0, newString.length() - 1);
        }
        System.out.println(newString);
        return newString;
    }
}
