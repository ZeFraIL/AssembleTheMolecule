package zeev.fraiman.assemblethemolecule.utils;

public class FormulaUtils {

    /**
     * Normalizes a chemical formula.
     * Example: Na1Cl1 -> NaCl, H2O1 -> H2O
     */
    public static String normalize(String formula) {
        if (formula == null) return "";
        // 1. Remove all '1' indices that are not part of 10, 11, etc.
        // 2. Trim spaces
        String result = formula.replaceAll("([a-zA-Z()])1(?![0-9])", "$1");
        return result.trim();
    }

    /**
     * Formats formula for display with subscripts (UTF-8).
     * Example: H2O -> H₂O
     */
    public static String formatForDisplay(String formula) {
        if (formula == null) return "";
        StringBuilder sb = new StringBuilder();
        for (char c : formula.toCharArray()) {
            if (Character.isDigit(c)) {
                sb.append(getSubscript(c));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    private static char getSubscript(char c) {
        switch (c) {
            case '0': return '₀';
            case '1': return '₁';
            case '2': return '₂';
            case '3': return '₃';
            case '4': return '₄';
            case '5': return '₅';
            case '6': return '₆';
            case '7': return '₇';
            case '8': return '₈';
            case '9': return '₉';
            default: return c;
        }
    }
}
