// Authors: Kary Främling, Jakob Kunz, Li Kunz
public class Model {
    private double anglais;
    private double math;
    private double info;
    private double geo;
    private double option;
    private double coefficientLatin;
    private double coefficientGrec;
    private double coefficientSport;
    private boolean useCoefficients;

    public Model() {
        anglais = 0.0;
        math = 0.0;
        info = 0.0;
        geo = 0.0;
        option = 0.0;
        coefficientLatin = 1.0;
        coefficientGrec = 1.0;
        coefficientSport = 1.0;
        useCoefficients = false;
    }

    public double getAnglais() {
        return anglais;
    }

    public void setAnglais(double anglais) throws NoteOutOfBoundException {
        if (anglais < 0 || anglais > 20) {
            throw new NoteOutOfBoundException();
        }
        this.anglais = anglais;
    }

    public double getMath() {
        return math;
    }

    public void setMath(double math) throws NoteOutOfBoundException {
        if (math < 0 || math > 20) {
            throw new NoteOutOfBoundException();
        }
        this.math = math;
    }

    public double getInfo() {
        return info;
    }

    public void setInfo(double info) throws NoteOutOfBoundException {
        if (info < 0 || info > 20) {
            throw new NoteOutOfBoundException();
        }
        this.info = info;
    }

    public double getGeo() {
        return geo;
    }

    public void setGeo(double geo) throws NoteOutOfBoundException {
        if (geo < 0 || geo > 20) {
            throw new NoteOutOfBoundException();
        }
        this.geo = geo;
    }

    public double getOption() {
        return option;
    }

    public void setOption(double option) throws NoteOutOfBoundException {
        if (option < 0 || option > 20) {
            throw new NoteOutOfBoundException();
        }
        this.option = option;
    }

    public double getCoefficientLatin() {
        return coefficientLatin;
    }

    public void setCoefficientLatin(double coefficientLatin) throws CoeffOutOfBoundException {
        if (coefficientLatin < 0 || coefficientLatin > 3) {
            throw new CoeffOutOfBoundException();
        }
        this.coefficientLatin = coefficientLatin;
    }

    public double getCoefficientGrec() {
        return coefficientGrec;
    }

    public void setCoefficientGrec(double coefficientGrec) throws CoeffOutOfBoundException {
        if (coefficientGrec < 0 || coefficientGrec > 3) {
            throw new CoeffOutOfBoundException();
        }
        this.coefficientGrec = coefficientGrec;
    }

    public double getCoefficientSport() {
        return coefficientSport;
    }
    public void setCoefficientSport(double coefficientSport) throws CoeffOutOfBoundException {
        if (coefficientSport < 0 || coefficientSport > 3) {
            throw new CoeffOutOfBoundException();
        }
        this.coefficientSport = coefficientSport;
    }

    public boolean useCoefficients() {
        return useCoefficients;
    }

    public void setUseCoefficients(boolean useCoefficients) {
        this.useCoefficients = useCoefficients;
    }
}