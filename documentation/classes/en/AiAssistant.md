# AiAssistant

## General info
Integrates the Gemini AI (Google Generative AI) to analyze hand-drawn chemical structures.

## Variables table
| Name | Type | Description |
|---|---|---|
| API_KEY | String | The key for accessing Google AI services. |
| model | GenerativeModelFutures | The interface to the AI model. |

## Methods detail
| Name | Parameters | Return | Logic |
|---|---|---|---|
| checkStructure | Bitmap, String, String, AiCallback | void | Sends a drawing and the substance name to Gemini. Asks it to verify the structure based on chemical rules. |

## Lifecycle
- N/A (Object created when needed).

## UI
- N/A (Backend logic).

## Inter-component interaction
- Used by `DrawingActivity`. Returns results via a callback.

## General logic
It constructs a prompt for the AI, attaches the image of the user's drawing, and parses the response to see if the AI said "YES" (correct) or "NO" (incorrect).

## Simplified explanation
This is the "smart" part of the app. It takes a picture of what you drew and asks an AI if it's a correct chemical structure or if you made a mistake.
