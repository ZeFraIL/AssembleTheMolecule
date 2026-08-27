# SplashActivity

## General info
The first screen shown when the app opens. It displays an animated logo and handles initial loading delay.

## Variables table
| Name | Type | Description |
|---|---|---|
| N/A | N/A | Only local variables used. |

## Methods detail
| Name | Parameters | Return | Logic |
|---|---|---|---|
| onCreate | Bundle savedInstanceState | void | Hides action bar, sets content view, starts scale animation, and schedules navigation to `MainActivity`. |

## Lifecycle
- `onCreate`: Starts the 3-second animation and a 4.5-second timer to switch activities.

## UI
- `activity_splash.xml`: Displays an `ImageView` with the app icon.

## Inter-component interaction
- Transitions to `MainActivity` after the delay.

## General logic
The activity uses a `ScaleAnimation` to smoothly grow the icon from zero to full size. A `Handler` with a delay is used to ensure the splash screen stays visible for a fixed time before moving to the main menu.

## Simplified explanation
This is the "loading screen". You see the app icon grow smoothly when you open the app, and then you're taken to the main menu.
