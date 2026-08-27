# DrawingActivity

## General info
An activity that challenges users to draw a specific molecule's structure. It uses AI to check if the drawing is correct.

## Variables table
| Name | Type | Description |
|---|---|---|
| drawingView | DrawingView | The interactive canvas. |
| aiAssistant | AiAssistant | Gemini AI handler. |
| rvElementPalette | RecyclerView | List of elements to pick for drawing. |

## Methods detail
| Name | Parameters | Return | Logic |
|---|---|---|---|
| setupPalette | None | void | Loads elements into a horizontal scrolling list. |
| analyzeWithAi | None | void | Takes a screenshot of the drawing and sends it to the AI. |

## Lifecycle
- `onCreate`: Sets up the canvas and picks a random molecule to draw.

## UI
- `activity_drawing.xml`: Top info bar, center drawing area, bottom element palette.

## Inter-component interaction
- Uses `AiAssistant` for verification.
- Interacts with `DrawingView` to place atoms.

## General logic
The app gives you a substance name. You pick elements and draw bonds between them. When done, you press "Analyze", and the AI tells you if you got it right.

## Simplified explanation
This is the drawing challenge! You have to draw how atoms are connected in a molecule. Once you finish your sketch, an AI "teacher" looks at it and gives you feedback.
