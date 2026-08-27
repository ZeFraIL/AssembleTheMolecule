# HelperDB

## General info
A database helper class that manages the SQLite database (`chemistry.db`). It handles table creation and populates initial data from JSON assets.

## Variables table
| Name | Type | Description |
|---|---|---|
| DATABASE_NAME | String | The name of the file on disk. |
| DATABASE_VERSION | int | Used for managing DB updates. |

## Methods detail
| Name | Parameters | Return | Logic |
|---|---|---|---|
| onCreate | SQLiteDatabase db | void | Creates `elements`, `substances`, and `progress` tables. |
| seedData | SQLiteDatabase db | void | Calls methods to load initial data from JSON. |
| seedElements | SQLiteDatabase db | void | Parses `elements.json` from assets and inserts into the table. |
| seedSubstances | SQLiteDatabase db | void | Parses `substances.json` from assets and inserts into the table. |
| loadJSONFromAsset| String fileName | String | Reads a file from the `assets` folder as a string. |

## Lifecycle
- Managed by the Android system. `onCreate` is called when the DB is first accessed.

## UI
- N/A (Data management only).

## Inter-component interaction
- Used by `GameActivity`, `SubstanceListActivity`, and `DrawingActivity` to fetch chemical data.

## General logic
It ensures the app has all the chemical information (elements, names, formulas) ready to use. If the database version increases, it can recreate the tables to update the data.

## Simplified explanation
This class acts like a librarian. It sets up the shelves (tables) and fills them with books (chemical data) so the rest of the app can find what it needs.
