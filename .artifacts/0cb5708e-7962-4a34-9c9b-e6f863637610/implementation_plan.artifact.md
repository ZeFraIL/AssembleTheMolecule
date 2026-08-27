# Implementation Plan - "Chemical Constructor" (Assemble The Molecule)

Educational Android game to learn chemistry by assembling molecular formulas from elements.

## Proposed Changes

### [Database & Data Models]

Establish the foundation for storing elements, substances, and user progress.

#### [NEW] [HelperDB.java](file:///C:/Users/zeevf/AndroidStudioProjects/AssembleTheMolecule/app/src/main/java/zeev/fraiman/assemblethemolecule/database/HelperDB.java)
SQLiteOpenHelper to manage tables: `elements`, `substances`, `progress`, and `achievements`. Includes seeding logic for the initial 15 elements and 40+ substances.

#### [NEW] [Element.java](file:///C:/Users/zeevf/AndroidStudioProjects/AssembleTheMolecule/app/src/main/java/zeev/fraiman/assemblethemolecule/model/Element.java)
Data model for chemical elements.

#### [NEW] [Substance.java](file:///C:/Users/zeevf/AndroidStudioProjects/AssembleTheMolecule/app/src/main/java/zeev/fraiman/assemblethemolecule/model/Substance.java)
Data model for chemical substances.

---

### [Core Game Mechanics]

Implement the formula assembly logic and main gameplay loop.

#### [NEW] [FormulaUtils.java](file:///C:/Users/zeevf/AndroidStudioProjects/AssembleTheMolecule/app/src/main/java/zeev/fraiman/assemblethemolecule/utils/FormulaUtils.java)
Utility for formula normalization (e.g., converting `H2O1` to `H2O`) and canonical comparison.

#### [NEW] [GameActivity.java](file:///C:/Users/zeevf/AndroidStudioProjects/AssembleTheMolecule/app/src/main/java/zeev/fraiman/assemblethemolecule/GameActivity.java)
The main gameplay screen where users assemble formulas.
- Top: Task description.
- Middle: Current workspace (assembled formula).
- Bottom: Element palette (GridLayout) and control buttons (Index +, Index -, Clear, Check).

#### [NEW] [activity_game.xml](file:///C:/Users/zeevf/AndroidStudioProjects/AssembleTheMolecule/app/src/main/res/layout/activity_game.xml)
Layout for the GameActivity.

---

### [UI & Navigation]

Build the menus and reference screens.

#### [MODIFY] [MainActivity.java](file:///C:/Users/zeevf/AndroidStudioProjects/AssembleTheMolecule/app/src/main/java/zeev/fraiman/assemblethemolecule/MainActivity.java)
Transform into the main menu with buttons for "Play", "Free Lab", "Substance List", etc.

#### [MODIFY] [activity_main.xml](file:///C:/Users/zeevf/AndroidStudioProjects/AssembleTheMolecule/app/src/main/res/layout/activity_main.xml)
Main menu layout with consistent styling.

#### [NEW] [SubstanceListActivity.java](file:///C:/Users/zeevf/AndroidStudioProjects/AssembleTheMolecule/app/src/main/java/zeev/fraiman/assemblethemolecule/SubstanceListActivity.java)
RecyclerView-based screen to browse known substances.

#### [NEW] [SubstanceAdapter.java](file:///C:/Users/zeevf/AndroidStudioProjects/AssembleTheMolecule/app/src/main/java/zeev/fraiman/assemblethemolecule/adapter/SubstanceAdapter.java)
Adapter for displaying substances in the list.

#### [NEW] [ResultActivity.java](file:///C:/Users/zeevf/AndroidStudioProjects/AssembleTheMolecule/app/src/main/java/zeev/fraiman/assemblethemolecule/ResultActivity.java)
Feedback screen shown after correct/incorrect answers.

---

### [Advanced Features & Polish]

Adding gamification and accessibility.

#### [NEW] [AchievementsActivity.java](file:///C:/Users/zeevf/AndroidStudioProjects/AssembleTheMolecule/app/src/main/java/zeev/fraiman/assemblethemolecule/AchievementsActivity.java)
Screen to view unlocked achievements.

#### [NEW] [TTSManager.java](file:///C:/Users/zeevf/AndroidStudioProjects/AssembleTheMolecule/app/src/main/java/zeev/fraiman/assemblethemolecule/utils/TTSManager.java)
Helper for Text-To-Speech functionality to read out substance names.

## Verification Plan

### Automated Tests
- Unit tests for `FormulaUtils` to verify normalization logic.
- SQLite tests to ensure correct data seeding and retrieval.

### Manual Verification
- Deploy to emulator/device.
- Verify "Free Lab" mode: assemble `H2O` and check if recognized.
- Verify Task mode: "Assemble Carbon Dioxide" -> check `CO2`.
- Check persistence of progress in the Substance List.
