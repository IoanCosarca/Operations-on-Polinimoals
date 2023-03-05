import javax.swing.*;
import java.awt.event.*;

public class CalcPolinomController implements ActionListener, MouseListener {
    protected CalcPolinomView view;
    protected CalcPolinomModel model;
    private static int enFPolin = 1;
    private static int enSPolin = 0;

    public void Start()
    {
        CalcPolinomView view = new CalcPolinomView();
        this.view = view;
        view.fPolin.addMouseListener(this);
        view.sPolin.addMouseListener(this);
        for (JButton b : view.functions) {
            b.addActionListener(this);
        }
        for (JButton b : view.numbers) {
            b.addActionListener(this);
        }
        for (JButton b : view.elempolin) {
            b.addActionListener(this);
        }
        model = new CalcPolinomModel();
    }

    public static void main(String[] args) {
        CalcPolinomController controller = new CalcPolinomController();
        controller.Start();
    }

    public void actionPerformed(ActionEvent e)
    {
        int l1 = view.fPolin.getText().length(), l2 = view.sPolin.getText().length();

        for (JButton b : view.numbers) {
            if (e.getSource() == b)
                view.WritePolinoms(enFPolin, enSPolin, b.getText());
        }

        for (JButton b : view.elempolin) {
            if (e.getSource() == b && view.elempolin.indexOf(b) != 7) {   // nu delete
                view.WritePolinoms(enFPolin, enSPolin, b.getText());
            }
        }

        if (e.getSource() == view.elempolin.get(7)) {    //delete
            if (enFPolin == 1 && l1 > 0) {
                view.fPolin.setText(view.fPolin.getText().substring(0, l1 - 1));
            }
            else if (enSPolin == 1 && l2 > 0) {
                view.sPolin.setText(view.sPolin.getText().substring(0, l2 - 1));
            }
        }

        for (JButton b : view.functions) {
            if (e.getSource() == b) {
                CalcResult(e);
            }
        }
    }

    private void CalcResult(ActionEvent e)
    {
        try
        {
            if (e.getSource() == view.functions.get(0)) {   //Multiply
                model.Multiply(view.fPolin.getText(), view.sPolin.getText());
            }
            if (e.getSource() == view.functions.get(1)) {   //Subtract
                model.Add(view.fPolin.getText(), view.sPolin.getText(), -1);
            }
            if (e.getSource() == view.functions.get(2)) {   //Divide
                model.Divide(view.fPolin.getText(), view.sPolin.getText());
            }
            if (e.getSource() == view.functions.get(3)) {   //Derivative
                model.Derivative(view.fPolin.getText());
            }
            if (e.getSource() == view.functions.get(4)) {   //Addition
                model.Add(view.fPolin.getText(), view.sPolin.getText(), 1);
            }
            if (e.getSource() == view.functions.get(5))     //Integration
            {
                model.Integration(view.fPolin.getText());
                view.result.setText(view.result.getText() + "+C");
            }

            model.WriteResult(view.result);
        }
        catch (Exception ex) {
            view.result.setText("Polinoame Invalide");
            System.out.println(ex.toString());
        }
    }

    public void mouseClicked(MouseEvent e)
    {
        if (e.getSource() == view.fPolin) {
            enFPolin = 1;
            enSPolin = 0;
        }

        if (e.getSource() == view.sPolin) {
            enSPolin = 1;
            enFPolin = 0;
        }
    }

    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
}
