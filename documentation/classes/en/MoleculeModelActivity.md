# MoleculeModelActivity

## General info
Displays an interactive 3D model of a molecule using a WebView and the MolView service.

## Variables table
| Name | Type | Description |
|---|---|---|
| wvMolecule3D | WebView | Container for the 3D viewer. |
| MODEL_IDS | Map<String, String> | Database of PubChem IDs for common substances. |

## Methods detail
| Name | Parameters | Return | Logic |
|---|---|---|---|
| setupWebView | None | void | Configures JavaScript and hides external menus via CSS injection. |
| loadMolViewModel | None | void | Finds the ID for the formula and loads the MolView URL. |

## Lifecycle
- `onCreate`: Starts loading the 3D model based on passed formula.

## UI
- `activity_molecule_model.xml`: Full-screen 3D view with a loading overlay.

## Inter-component interaction
- Receives data from `SubstanceAdapter`.

## General logic
It looks up the substance's PubChem ID in a pre-defined map. If found, it loads a web page that renders the 3D molecule. It uses JavaScript to "clean up" the website so it looks like part of the app.

## Simplified explanation
This screen lets you see what molecules look like in 3D! You can rotate and zoom in on them. It's like a virtual reality view of a molecule.
