//jacob nave
//10/13/2017
import java.util.ArrayList;
public class Term implements Terms
{
    private String term;
    
    public Term(String term)
    {
        this.term = term;
    }
    
    public String getTerm()
    {
        return this.term;
    }
    public String toString()
    {
        return term;
    }
    public ArrayList<Terms> getTerms()
    {
        ArrayList<Terms> list = new ArrayList<Terms>();
        list.add(this);
        return list;
    }
    public String getSigns()
    {
        return "";
    }
    public String getCoefficient()
    {
        return "";
    }
}
