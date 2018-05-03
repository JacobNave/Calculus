//Jacob Nave
//11/27/2017
import javax.swing.*;
import java.awt.event.*;

public class Button extends JButton implements ActionListener
{
    private JTextField text;
    private JLabel label;
    
    public Button(JTextField text, JLabel label)
    {
        super("Differentiate");
        this.text = text;
        this.addActionListener(this);
        this.label = label;
    }
    
    public void actionPerformed(ActionEvent e)
    {
        String equ = Differentiate.differentiateEquation(new Equation(text.getText()));
        label.setText("Derivative: " + equ);
    }
}
