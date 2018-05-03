//Jacob Nave
//10/12/2017
import java.util.ArrayList;
 
public class Equation
{
    private ArrayList<Terms> terms;
    private String signs;
    private String equation;
    private ArrayList<String> stringTerms;
    public Equation(String equation)
    {
        terms = new ArrayList<Terms>();
        stringTerms = new ArrayList<String>();
        signs = "";
        this.equation = equation;
        int length = equation.length();
        int pCount = 0; //current number of parentheses
        String currentTerm = "";
        
        //split equation into terms and signs in string form
        for(int rep = 0; rep < length; rep++)
        {
            if(pCount > 0)
            {
                currentTerm += equation.substring(0,1);
                if(equation.substring(0,1).equals("("))
                {
                    pCount++;
                }
                else if(equation.substring(0,1).equals(")"))
                {
                    pCount--;
                }
                equation = equation.substring(1);
            }
            else if(!equation.substring(0,1).equals("+") && !equation.substring(0,1).equals("-"))
            {
                currentTerm += equation.substring(0,1);
                if(equation.substring(0,1).equals("("))
                {
                    pCount++;
                }
                else if(equation.substring(0,1).equals(")"))
                {
                    pCount--;
                }
                equation = equation.substring(1);
            }
            else
            {
                stringTerms.add(currentTerm.trim());
                currentTerm = "";
                signs += equation.substring(0,1);
                equation = equation.substring(1);
            }
        }
        stringTerms.add(currentTerm.trim());

        //put terms into arraylist as terms and quantities
        for(int rep = 0; rep < stringTerms.size(); rep++)
        {
            if(!stringTerms.get(rep).contains("("))
            {
                Term newTerm = new Term(stringTerms.get(rep));
                terms.add(newTerm);
            }
            else
            {
                Quantity newQuantity = new Quantity(stringTerms.get(rep));
                terms.add(newQuantity);
            }
        }
    }
    
    public ArrayList<Terms> getTerms()
    {
        return terms;
    }
    
    public ArrayList<String> getStringTerms()
    {
        return stringTerms;
    }
    
    public String getSigns()
    {
        return signs;
    }
    
    public String toString()
    {
        int length = stringTerms.size();
        String string = "";
        for(int rep = 0; rep < length - 1; rep++)
        {
            string += stringTerms.get(rep) + " " + signs.substring(rep, rep + 1) + " ";
        }
        string += stringTerms.get(length - 1);
        return string;
    }
}
