import java.util.regex.*;

public class Monom implements Comparable<Monom> {
    private String term;
    private String coeficient;
    private double coefval = 0;
    private int power = 0;

    public Monom (String term)
    {
        this.term = term;
        String PATTERNP = "[^X^*]+";
        Pattern patternp = Pattern.compile(PATTERNP);
        Matcher matcherp = patternp.matcher(term);
        if (term.charAt(0) == 'X') {
            coefval = 1;
        }
        while (matcherp.find())
        {
            if (coefval != 0) {
                power = Integer.parseInt(matcherp.group());
            }
            else
            {
                coeficient = matcherp.group();
                try {
                    FormCoef(coeficient);
                }
                catch (StringIndexOutOfBoundsException e) {
                    System.out.println("Invalid polynomial");
                    coefval = 0;
                }
            }
        }
        if (term.charAt(term.length() - 1) != '^' && term.charAt(term.length() - 1) == 'X') {
            power = 1;
        }
    }

    private void FormCoef(String coeficient) throws StringIndexOutOfBoundsException {
        if (coeficient.equals("+")) {
            coefval = 1;
        } else if (coeficient.equals("-")) {
            coefval = -1;
        } else {
            String a = "", b = "", PATTERNC = "[^\\/]+";
            Pattern patternc = Pattern.compile(PATTERNC);
            Matcher matcherc = patternc.matcher(coeficient);
            while (matcherc.find()) {
                if (!a.equals("")) {
                    b = matcherc.group();
                } else {
                    a = matcherc.group();
                }
            }
            if (a.charAt(0) == '-') {
                coefval = Double.parseDouble(a.substring(1, a.length())) * -1;
            } else coefval = Double.parseDouble(a);
            if (!b.equals("")) {
                if (b.charAt(0) == '-') {
                    coefval = coefval / Double.parseDouble(b.substring(1, b.length())) * -1;
                } else coefval = coefval / Double.parseDouble(b);
            }
        }
    }

    public void setCoef(int i) {
        coefval = coefval * i;
    }

    public double getCoef() {
        return coefval;
    }

    public int getPower() {
        return power;
    }

    public Monom (double coefval, int power)
    {
        this.coefval = coefval;
        this.power = power;
        setTerm(coefval, power);
    }

    public void setTerm(double coefval, int power)
    {
        coeficient = String.format("%.2f", coefval);
        if (power == 0) {
            term = coeficient;
        }
        else
        {
            if (coefval == -1) {
                term = "-X";
            }
            else if (coefval == 1) {
                term = "X";
            }
            else term = coeficient + "*X";

            if (power != 1) {
                term += "^" + power;
            }
            else term += "";
        }
    }

    public String getTerm() {
        return term;
    }

    public int compareTo(Monom m) {
        return Integer.toString(m.power).compareTo(Integer.toString(power));
    }
}