# Walkthrough - Chemical Constructor (Finalized)

The application is now fully localized into English and features an advanced 3D interactive molecule viewer.

## Key Accomplishments

### 1. Full Localization (English)
- **UI Strings**: All buttons, labels, and status messages moved to `strings.xml` and translated to English.
- **Scientific Data**: Elements and substances in the database are now stored with English names (e.g., *Hydrogen*, *Sulfuric Acid*).
- **International Standards**: Uses IUPAC naming conventions for better educational value.

### 2. Advanced 3D Molecule Visualization
- **MolView Integration**: Uses the professional MolView engine to render 3D models.
- **Interactive Experience**: Users can rotate, zoom, and explore molecules in 3D.
- **Clean UI**: Custom script hides website menus, showing only the molecule within the app's frame.
- **Reliability**: Optimized for simple inorganic molecules like Water ($H_2O$) and Ammonia ($NH_3$) that often fail on other services.

### 3. Stabilized Networking
- **Direct System Loader**: Bypasses heavy library bottlenecks for faster, more reliable image data fetching.
- **Proxy Support**: Uses `weserv.nl` to ensure high availability and bypass potential network restrictions.

## How to Test
1.  **Main Menu**: Notice all buttons are now in English (*Play Game*, *Free Lab*, *Substances*).
2.  **Substances Dictionary**:
    *   Find **Water** or **Ammonia**.
    *   Tap **Molecule Model**.
    *   Wait for the "AI is rendering..." indicator.
    *   Interact with the 3D model by swiping on the screen.
3.  **Gameplay**:
    *   Try to assemble **Ca(OH)₂**.
    *   Taps: `Ca` -> `(` -> `O` -> `H` -> `)` -> `+` (to set index 2).
    *   Tap **Check**. It should confirm the correct formula in English.

## Technical Improvements
- **Database v3**: Automatically migrates old Russian data to the new English format.
- **WebView Optimization**: Enabled hardware acceleration for smooth 3D rendering on mobile devices.
