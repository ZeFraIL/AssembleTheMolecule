📱 Android App Documentation (Level 10/10)
________________________________________
🧾 General Information
Project Name:
AssembleTheMolecule (Chemical Constructor)
Author(s):
Zeev Fraiman
Date:
August 27, 2026
Language:
Java
Development Environment:
Android Studio
Android Version (minSdk / targetSdk):
28 / 37
________________________________________
🎯 Project Goal
•	The application solves the problem of learning chemical formulas in an interactive and engaging way.
•	This task is important because traditional chemistry learning can be abstract; visualizing and "building" molecules helps retention.
•	Target Audience: Students, chemistry enthusiasts, and educators.
________________________________________
📌 Application Requirements
Functional Requirements
•	Assembling chemical formulas from individual elements.
•	3D visualization of molecular models using MolView integration.
•	Database of substances categorized by difficulty and type.
•	Free Laboratory mode for experimentation.
Non-functional Requirements
•	Performance: Smooth 3D rendering and fast database queries.
•	Usability: Intuitive interface with a clear "Element Palette".
•	Reliability: Proper handling of chemical formula normalization and error states.
________________________________________
🧠 General Architecture
•	Chosen Approach:
–	Activity-based Architecture (MVC style)

•	Why it was chosen:
Simple and effective for a game-oriented app with distinct screens and direct hardware/UI interactions.

•	System Main Components:
- Activities: Manage UI states and user interactions.
- SQLite Database: Persistent storage for chemical data and progress.
- WebView: For high-quality 3D molecule rendering.
- Utilities: Specialized logic for formula parsing and normalization.
________________________________________
🧩 UML Diagram
[MainActivity] –> [GameActivity] / [SubstanceListActivity] / [DrawingActivity]
[GameActivity] –> [HelperDB] / [FormulaUtils]
[SubstanceListActivity] –> [SubstanceAdapter] –> [MoleculeModelActivity]
[MoleculeModelActivity] –> [WebView/MolView API]
________________________________________
Package Analysis:
- `zeev.fraiman.assemblethemolecule`: Core activities.
- `database`: SQL management.
- `model`: POJO classes for Elements and Substances.
- `adapter`: RecyclerView logic.
- `utils`: Business logic helpers.
This structure helps in scaling by separating data concerns from UI logic.
________________________________________
🧩 Detailed Class Description
📌 Class: MainActivity
Role: Entry point and Navigation Hub.
Responsibility: Routing the user to different game modes or settings.
Main Methods:
- `onCreate()` — Initializes buttons and click listeners.
- `btnPlay.setOnClickListener()` — Starts the game mode.

📌 Class: GameActivity
Role: Core Gameplay Engine.
Responsibility: Managing the formula construction process and validation.
Main Methods:
- `onElementClick()` — Adds element to formula.
- `checkFormula()` — Validates assembly against the database.

📌 Class: HelperDB
Role: Persistence Layer.
Responsibility: Seeding data from JSON assets and managing SQLite operations.
________________________________________
🔄 App Workflow Diagram
Scenario: User wants to see a 3D model.
1. User opens "Substances".
2. Clicks on "Molecule Model" for a specific substance.
3. Substance name/formula passed via Intent to `MoleculeModelActivity`.
4. Activity queries MolView via WebView to render the interactive 3D model.
________________________________________
🎨 UI/UX Analysis
•	The interface is designed with large buttons and a grid layout for ease of use on mobile devices.
•	Principles Used:
–	Simplicity: Clear "palette" of elements.
–	Logicity: Top-to-bottom workflow (Task -> Workspace -> Controls).
–	Accessibility: Clear fonts and high-contrast element buttons.
•	Future Improvements: Dark mode support and haptic feedback.
________________________________________
⚙️ Thread Management
•	Used:
- Handler / Looper: For timed UI updates (loading screens).
- WebView internal threading: For 3D rendering.
•	Why: To maintain UI responsiveness during heavy rendering or data loading.
•	Prevention:
–	ANR: Long-running database operations are minimal; WebView handles complex rendering asynchronously.
–	Memory Leaks: Careful context management in adapters and static maps.
________________________________________
💾 Data Management
•	Data is stored in an SQLite database, initialized from JSON assets.
•	Why: Allows fast querying, ordering, and future offline progress tracking.
•	Ensuring:
–	Integrity: Relational structure for elements and substances.
–	Correctness: Formula normalization before comparison.
________________________________________
🌐 Networking
•	Requests are performed via WebView to MolView.org.
•	Error Handling: Loading placeholders and timeout handling.
•	Offline: Basic gameplay works offline using the local database; 3D models require internet.
________________________________________
🔐 Security (Basic Level)
•	No sensitive user data (passwords/PII) is currently collected.
•	Data Protection: Standard Android application sandboxing.
________________________________________
🧪 Testing
•	Types of Tests:
–	Unit Tests: Verification of formula normalization logic in `FormulaUtils`.
–	UI Tests: Manual verification of the molecule assembly flow.
•	Checks: Correct recognition of `H2O`, `CO2`, etc.
________________________________________
🐞 Error Handling
•	Input errors (invalid formulas) are handled via Toast messages.
•	Database errors are logged for debugging.
________________________________________
⚡ Performance
•	Optimization: Hardware acceleration for WebView.
•	Bottlenecks: Complex 3D rendering on low-end devices.
________________________________________
🚀 Extension Possibilities
•	Multi-step chemical reactions.
•	Gamification: Leaderboards and more complex achievements.
•	AR support for viewing molecules in the real world.
