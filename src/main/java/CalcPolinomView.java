import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class CalcPolinomView {
    private JLabel title;

    private JLabel fName;
    protected JTextField fPolin;
    private JLabel sName;
    protected JTextField sPolin;
    private JLabel rName;
    protected JTextField result;

    private JButton MultiplyP;
    private JButton DivideP;
    private JButton AddP;
    private JButton SubtractP;
    private JButton Derivative;
    private JButton Integration;

    private JButton N1;
    private JButton N2;
    private JButton N3;
    private JButton N4;
    private JButton N5;
    private JButton N6;
    private JButton N7;
    private JButton N8;
    private JButton N9;
    private JButton N0;
    private JButton Add;
    private JButton Subtract;
    private JButton Divide;
    private JButton Multiply;
    private JButton Power;
    private JButton Dot;
    private JButton X;
    private JButton delete;

    protected ArrayList <JButton> functions;
    protected ArrayList <JButton> numbers;
    protected ArrayList <JButton> elempolin;
    Font FontBtn = new Font("", Font.PLAIN, 20);

    CalcPolinomView()
    {
        JFrame content = new JFrame("Calculator");
        content.setSize(550, 650);
        content.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        content.setLocationRelativeTo(null);
        content.setResizable(false);
        content.setTitle("Calculator Polinoame");
        content.setLayout(null);

        title       = new JLabel("Polynomial Calculator");
        fName       = new JLabel("First Polynomial = ");
        fPolin      = new JTextField();
        sName       = new JLabel("Second Polynomial = ");
        sPolin      = new JTextField();
        rName       = new JLabel("Result = ");
        result      = new JTextField();
        MultiplyP   = new JButton("Multiplication");
        DivideP     = new JButton("Divide");
        AddP        = new JButton("Add");
        SubtractP   = new JButton("Subtract");
        Derivative  = new JButton("Derivative");
        Integration = new JButton("Integration");
        N1          = new JButton("1");
        N2          = new JButton("2");
        N3          = new JButton("3");
        N4          = new JButton("4");
        N5          = new JButton("5");
        N6          = new JButton("6");
        N7          = new JButton("7");
        N8          = new JButton("8");
        N9          = new JButton("9");
        N0          = new JButton("0");
        Add         = new JButton("+");
        Subtract    = new JButton("-");
        Divide      = new JButton("/");
        Multiply    = new JButton("*");
        Power       = new JButton("^");
        Dot         = new JButton(".");
        X           = new JButton("X");
        delete      = new JButton("del");

        title.setBounds(80, 10, 380, 50);
        title.setFont(new Font("", Font.BOLD, 35));
        content.add(title);

        JPanel polinoms = new JPanel();
        polinoms.setBounds(5, 60, 525, 100);
        polinoms.setLayout(new GridLayout(3,2));
        fName.setFont(FontBtn);
        polinoms.add(fName);
        fPolin.setFont(FontBtn);
        polinoms.add(fPolin);
        sName.setFont(FontBtn);
        polinoms.add(sName);
        sPolin.setFont(FontBtn);
        polinoms.add(sPolin);
        rName.setFont(FontBtn);
        polinoms.add(rName);
        result.setEditable(false);
        result.setBackground(Color.WHITE);
        result.setFont(FontBtn);
        polinoms.add(result);
        content.add(polinoms);

        functions = new ArrayList <JButton> ();
        functions.add(MultiplyP);
        functions.add(SubtractP);
        functions.add(DivideP);
        functions.add(Derivative);
        functions.add(AddP);
        functions.add(Integration);
        for (JButton b : functions)
        {
            b.setFont(FontBtn);
            b.setFocusable(false);
        }
        JPanel operations = new JPanel();
        operations.setBounds(5,170,525,200);
        operations.setLayout(new GridLayout(3,2));
        for (JButton b : functions) {
            operations.add(b);
        }
        content.add(operations);

        numbers = new ArrayList <JButton> ();
        numbers.add(N1);
        numbers.add(N2);
        numbers.add(N3);
        numbers.add(N4);
        numbers.add(N5);
        numbers.add(N6);
        numbers.add(N7);
        numbers.add(N8);
        numbers.add(N9);
        numbers.add(N0);
        for (JButton b : numbers)
        {
            b.setFont(FontBtn);
            b.setFocusable(false);
        }
        elempolin = new ArrayList <JButton> ();
        elempolin.add(Add);
        elempolin.add(Subtract);
        elempolin.add(Divide);
        elempolin.add(Multiply);
        elempolin.add(Power);
        elempolin.add(Dot);
        elempolin.add(X);
        elempolin.add(delete);
        for (JButton b : elempolin)
        {
            b.setFont(FontBtn);
            b.setFocusable(false);
        }
        JPanel createpolin = new JPanel();
        createpolin.setBounds(5,375,525,230);
        createpolin.setLayout(new GridLayout(1,2));
        content.add(createpolin);
        JPanel createL = new JPanel();
        JPanel createR = new JPanel();
        createL.setLayout(new GridLayout(3,3));
        createR.setLayout(new GridLayout(3,3));
        createpolin.add(createL);
        createpolin.add(createR);
        for (JButton b : numbers) {
            if (numbers.indexOf(b) != 9) {
                createL.add(b);
            }
        }
        createR.add(numbers.get(9));
        for (JButton b : elempolin) {
            createR.add(b);
        }

        content.setVisible(true);
    }

    public void WritePolinoms(int enFPolin, int enSPolin, String i)
    {
        if (enFPolin == 1) {
            fPolin.setText(fPolin.getText()+i);
        }
        if (enSPolin == 1) {
            sPolin.setText(sPolin.getText()+i);
        }
    }
}
