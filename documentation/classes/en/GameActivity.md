# GameActivity

## General info
The core gameplay activity where users assemble chemical formulas. It supports two modes: "Game" (assemble a specific substance) and "Lab" (experiment with elements to see what they form).

## Variables table
| Name | Type | Description |
|---|---|---|
| tvTask | TextView | Displays current objective. |
| tvSubstanceName | TextView | Shows the name of the target or found substance. |
| tvFormulaDisplay | TextView | Shows the formula as it's being built. |
| rvElements | RecyclerView | Scrollable list of chemical elements. |
| helperDB | HelperDB | Database helper for loading elements and substances. |
| currentFormulaAtoms | List<SelectedAtom> | Stores the atoms added by the user. |
| mode | String | "game" or "lab". |
| targetSubstance | Substance | The substance the user needs to assemble in game mode. |

## Methods detail
| Name | Parameters | Return | Logic |
|---|---|---|---|
| initViews | None | void | Finds UI components and sets up click listeners for action buttons. |
| loadElements | None | void | Fetches all elements from DB and displays them in the RecyclerView. |
| setupGame | None | void | Configures the UI based on whether the mode is "game" or "lab". |
| changeIndex | int delta | void | Increases or decreases the subscript index of the last added atom. |
| updateDisplay | None | void | Updates the formula display with subscripts using `FormulaUtils`. |
| checkFormula | None | void | Validates the assembled formula against the target (game) or searches for it in DB (lab). |

## Lifecycle
- `onCreate`: Initializes data, UI, and loads elements.

## UI
- `activity_game.xml`: Features an element palette, action buttons (Index+, Index-, Backspace, Clear, Check), and displays for task and formula.

## Inter-component interaction
- Uses `HelperDB` for data.
- Uses `ElementAdapter` to display elements.
- Uses `FormulaUtils` for formatting and normalization.

## General logic
Users tap elements from the list to add them to the formula. They can adjust indices (e.g., H₂ instead of H) and use brackets. "Check" determines if the formula matches the target or exists in the database.

## Simplified explanation
This is where the magic happens! You can pick atoms from a list to build molecules. In 'Game' mode, the app gives you a name, and you try to build it. In 'Lab' mode, you can mix anything and see if it's a real substance!
