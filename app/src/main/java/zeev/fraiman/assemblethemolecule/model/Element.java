package zeev.fraiman.assemblethemolecule.model;

public class Element {
    private int id;
    private String symbol;
    private String name;
    private int atomicNumber;
    private double atomicMass;

    public Element() {}

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getSymbol() { return symbol; }
    public void setSymbol(String symbol) { this.symbol = symbol; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getAtomicNumber() { return atomicNumber; }
    public void setAtomicNumber(int atomicNumber) { this.atomicNumber = atomicNumber; }

    public double getAtomicMass() { return atomicMass; }
    public void setAtomicMass(double atomicMass) { this.atomicMass = atomicMass; }
}
