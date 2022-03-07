import javax.swing.*;
import java.util.*;
import java.util.regex.*;

public class CalcPolinomModel {
    private ArrayList <Monom> Polinom1;
    private ArrayList <Monom> Polinom2;
    protected ArrayList <Monom> RPolin;
    private ArrayList <Monom> QPolin;

    CalcPolinomModel()
    {
        Polinom1 = new ArrayList <Monom>();
        Polinom2 = new ArrayList <Monom>();
        RPolin   = new ArrayList <Monom>();
    }

    private void ConstructPolin(String Polin, int i)
    {
        RPolin = new ArrayList<Monom>();
        String PATTERN = "[+-]?[^+-]+";
        Pattern pattern = Pattern.compile(PATTERN);
        Matcher matcher = pattern.matcher(Polin);
        if (i == 1) {
            Polinom1 = new ArrayList<Monom>();
        }
        else {
            Polinom2 = new ArrayList<Monom>();
        }
        while (matcher.find())
        {
            Monom m = new Monom(matcher.group());
            if (i == 1) {
                Polinom1.add(m);
            }
            else {
                Polinom2.add(m);
            }
        }
        Collections.sort(Polinom1);
        Collections.sort(Polinom2);
    }

    public void Add(String fPolin, String sPolin, int i) {
        ConstructPolin(fPolin, 1);
        ConstructPolin(sPolin, 2);
        for (Monom m : Polinom2) {
            m.setCoef(i);
        }
        for (Monom m1 : Polinom1) {
            double coefR = m1.getCoef();
            int powerR = m1.getPower();
            for (Monom m2 : Polinom2) {
                if (m2.getPower() == powerR) {
                    coefR += m2.getCoef();
                }
            }
            if (coefR != 0) {
                Monom mr = new Monom(coefR, powerR);
                RPolin.add(mr);
            }
        }
        for (Monom m2 : Polinom2) {
            double coefR = m2.getCoef();
            int powerR = m2.getPower();
            int ok = 1;
            for (Monom m1 : Polinom1) {
                if (m1.getPower() == powerR) {
                    ok = 0;
                    break;
                }
            }
            if (ok == 1 && coefR != 0) {
                Monom mr = new Monom(coefR, powerR);
                RPolin.add(mr);
            }
        }
    }

    public void Multiply(String fPolin, String sPolin)
    {
        ConstructPolin(fPolin, 1);
        ConstructPolin(sPolin, 2);
        for (Monom m1:Polinom1)
        {
            for (Monom m2:Polinom2)
            {
                double coefR = m1.getCoef() * m2.getCoef();
                int powerR = m1.getPower() + m2.getPower();
                int ok = 1;
                for (Monom mr: RPolin) {
                    if (mr.getPower() == powerR)
                    {
                        Monom mon = new Monom(coefR + mr.getCoef(), powerR);
                        int i = RPolin.indexOf(mr);
                        RPolin.set(i, mon);
                        ok = 0;
                    }
                }
                if (ok == 1 && coefR != 0)
                {
                    Monom mon = new Monom(coefR, powerR);
                    RPolin.add(mon);
                }
            }
        }
    }

    public void Divide(String fPolin, String sPolin)
    {
        ConstructPolin(fPolin, 1);
        ConstructPolin(sPolin, 2);
        QPolin = new ArrayList<Monom>();
        Monom m1 = Polinom1.get(0);
        Monom m2 = Polinom2.get(0);
        if (m1.getPower() > m2.getPower()) {
            DividePolinomials(Polinom1, Polinom2);
        }
        else {
            DividePolinomials(Polinom2, Polinom1);
        }
        RPolin.clear();
        RPolin.addAll(QPolin);
    }

    private void DividePolinomials(ArrayList<Monom> P1, ArrayList<Monom> P2)
    {
        Monom m1 = P1.get(0);
        Monom m2 = P2.get(0);
        double Qcoef = m1.getCoef() / m2.getCoef();
        int Qpower = m1.getPower() - m2.getPower();
        Monom mq = new Monom(Qcoef, Qpower);
        QPolin.add(mq);

        String P2string = toString(P2);
        Multiply(mq.getTerm(), P2string);

        String P1string = toString(P1);
        String Rstring = toString(RPolin);
        Add(P1string, Rstring, -1);

        Monom m0 = new Monom(0, 0);
        RPolin.add(m0);
        if (RPolin.get(0).getPower() >= m2.getPower()) {
            DividePolinomials(RPolin, P2);
        }
    }

    public String toString(ArrayList<Monom> P)
    {
        String pol = "";
        int ok = 1;
        for (Monom m : P)
        {
            if (m.getCoef() != 0)
            {
                m.setTerm(m.getCoef(), m.getPower());
                String x = m.getTerm();
                pol += (ok != 1 && x.charAt(0) != '-') ? "+" + x : x;
                ok = 0;
            }
        }
        return pol;
    }

    public void Derivative(String fPolin)
    {
        ConstructPolin(fPolin, 1);
        for (Monom m:Polinom1)
        {
            double coefR = m.getCoef();
            int powerR = m.getPower();
            if (coefR != 0)
            {
                Monom mr = new Monom(coefR * powerR, powerR - 1);
                RPolin.add(mr);
            }
        }
    }

    public void Integration(String fPolin)
    {
        ConstructPolin(fPolin, 1);
        for (Monom m:Polinom1)
        {
            int powerR = m.getPower() + 1;
            double coefR = m.getCoef();
            if (coefR != 0)
            {
                Monom mr = new Monom(coefR / powerR, powerR);
                RPolin.add(mr);
            }
        }
    }

    public void WriteResult(JTextField result)
    {
        Collections.sort(RPolin);
        String s = "";
        int primul = 1;
        for (Monom m:RPolin)
        {
            if (m.getCoef() != 0)
            {
                String x = m.getTerm();
                s += (primul != 1 && x.charAt(0) != '-') ? "+" + x : x;
                primul = 0;
            }
        }
        result.setText(s);
    }
}
