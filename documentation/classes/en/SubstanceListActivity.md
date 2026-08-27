# SubstanceListActivity

## General info
A screen that displays a list of all chemical substances available in the app's database. It acts as a "chemical dictionary".

## Variables table
| Name | Type | Description |
|---|---|---|
| rvSubstances | RecyclerView | Scrollable list to show substances. |
| helperDB | HelperDB | Database access. |

## Methods detail
| Name | Parameters | Return | Logic |
|---|---|---|---|
| loadSubstances | None | void | Queries the database for all substances and sets up the adapter. |

## Lifecycle
- `onCreate`: Loads the list immediately.

## UI
- `activity_substance_list.xml`: A simple list view.

## Inter-component interaction
- Uses `SubstanceAdapter`.
- Opens `MoleculeModelActivity` via the adapter.

## General logic
Fetches all rows from the `substances` table and presents them using a list. Each item allows the user to view its 3D model.

## Simplified explanation
Think of this as a library of chemicals. You can scroll through all the substances in the app and see their formulas and names.
