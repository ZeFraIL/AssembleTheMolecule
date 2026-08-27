# MainActivity

## General info
The entry point of the application. It displays the main menu and allows the user to navigate to different game modes and features.

## Variables table
| Name | Type | Description |
|---|---|---|
| N/A | N/A | No class-level variables defined. |

## Methods detail
| Name | Parameters | Return | Logic |
|---|---|---|---|
| onCreate | Bundle savedInstanceState | void | Initializes the UI, sets up edge-to-edge display, and assigns click listeners to menu buttons. |

## Lifecycle
- `onCreate`: Called when the activity is first created. Sets up the menu.

## UI
- `activity_main.xml`: Contains buttons for "Play", "Free Lab", "Dictionary", "Draw", and "Achievements".

## Inter-component interaction
- Launches `GameActivity` with "game" or "lab" mode.
- Launches `SubstanceListActivity`.
- Launches `DrawingActivity`.

## General logic
Upon starting, it presents 5 options. Clicking a button creates an `Intent` to start the corresponding activity.

## Simplified explanation
This is like the home screen of the app. It has buttons to start the game, go to the lab, look up substances, or draw molecules.
