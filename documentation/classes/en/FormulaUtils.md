# FormulaUtils

## General info
A utility class for processing and formatting chemical formulas.

## Variables table
| Name | Type | Description |
|---|---|---|
| N/A | N/A | No state maintained. |

## Methods detail
| Name | Parameters | Return | Logic |
|---|---|---|---|
| normalize | String formula | String | Removes '1' indices (e.g., "H2O1" -> "H2O"). |
| formatForDisplay | String formula | String | Converts digits to UTF-8 subscripts (e.g., "H2O" -> "H₂O"). |
| getSubscript | char c | char | Maps a digit to its subscript character. |

## Lifecycle
- N/A (Static utility methods).

## UI
- N/A (Helper for UI components).

## Inter-component interaction
- Used by `GameActivity`, `SubstanceAdapter`, and `MoleculeModelActivity`.

## General logic
Uses regular expressions and string manipulation to ensure formulas look "scientific" on the screen and are easy to compare in code.

## Simplified explanation
A helper that knows how to write chemical formulas correctly, like making the numbers small and at the bottom (subscripts).
