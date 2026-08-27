# SubstanceAdapter

## General info
A bridge between the list of substances and the `RecyclerView`. It displays substance details and provides a button to view the 3D model.

## Variables table
| Name | Type | Description |
|---|---|---|
| substances | List<Substance> | Data source. |

## Methods detail
| Name | Parameters | Return | Logic |
|---|---|---|---|
| onBindViewHolder | SubstanceViewHolder, int | void | Formats the formula and name, and sets up the "3D Model" button. |

## Lifecycle
- N/A (Adapter managed by RecyclerView).

## UI
- `item_substance.xml`: Defines the layout for a substance entry in the list.

## Inter-component interaction
- Used by `SubstanceListActivity`.
- Launches `MoleculeModelActivity`.

## General logic
It displays each substance's info and attaches a click listener to the "3D Model" button to launch the viewer with the correct data.

## Simplified explanation
A manager that shows you the name and formula of each substance in the dictionary and lets you open the 3D viewer.
