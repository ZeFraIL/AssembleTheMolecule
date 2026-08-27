# DrawingView

## General info
A custom UI component that acts as a canvas. Users can draw lines (bonds) and place chemical symbols on it.

## Variables table
| Name | Type | Description |
|---|---|---|
| drawPaint | Paint | Settings for drawing lines. |
| textPaint | Paint | Settings for drawing element symbols. |
| drawPath | Path | Current line being drawn. |
| elementTexts | List<ElementText> | List of symbols placed on the canvas. |
| currentSelectedElement | String | The symbol to place next. |

## Methods detail
| Name | Parameters | Return | Logic |
|---|---|---|---|
| onDraw | Canvas | void | Draws the background bitmap, current path, and all placed symbols. |
| onTouchEvent | MotionEvent | boolean | Handles finger movement to draw lines or tap to place elements. |
| clearCanvas | None | void | Resets the drawing. |
| getBitmap | None | Bitmap | Captures the entire canvas as an image for AI analysis. |

## Lifecycle
- N/A (Part of the Activity UI).

## UI
- Used in `activity_drawing.xml`.

## Inter-component interaction
- Interacts with `DrawingActivity` to receive selected elements.

## General logic
If an element is selected from the palette, the next tap places that symbol. If no element is selected, finger movement draws lines like a pen.

## Simplified explanation
A digital whiteboard where you can draw chemical bonds with your finger and tap to put atoms in place.
