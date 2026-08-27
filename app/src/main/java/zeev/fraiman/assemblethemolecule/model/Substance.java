package zeev.fraiman.assemblethemolecule.model;

public class Substance {
    private int id;
    private String formula;
    private String name;
    private String className;
    private String description;
    private String interestingFact;
    private int difficulty;

    public Substance() {}

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getFormula() { return formula; }
    public void setFormula(String formula) { this.formula = formula; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getClassName() { return className; }
    public void setClassName(String className) { this.className = className; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getInterestingFact() { return interestingFact; }
    public void setInterestingFact(String interestingFact) { this.interestingFact = interestingFact; }

    public int getDifficulty() { return difficulty; }
    public void setDifficulty(int difficulty) { this.difficulty = difficulty; }
}
