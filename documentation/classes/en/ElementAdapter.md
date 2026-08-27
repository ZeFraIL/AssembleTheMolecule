# ElementAdapter

## General info
A bridge between the list of elements and the `RecyclerView`. It handles how each element button looks and behaves.

## Variables table
| Name | Type | Description |
|---|---|---|
| elements | List<Element> | Data source. |
| listener | OnElementClickListener | Action to perform when an element is clicked. |

## Methods detail
| Name | Parameters | Return | Logic |
|---|---|---|---|
| onBindViewHolder | ElementViewHolder, int | void | Sets the symbol and atomic number on the item's view. |

## Lifecycle
- N/A (Adapter managed by RecyclerView).

## UI
- `item_element.xml`: Defines the look of a single element card.

## Inter-component interaction
- Used by `GameActivity` and `DrawingActivity`.

## General logic
Standard Android adapter pattern. It takes a list of `Element` objects and maps them to visual "cards" that users can interact with.

## Simplified explanation
A manager that takes the list of atoms and turns them into a nice, clickable list on your screen.
