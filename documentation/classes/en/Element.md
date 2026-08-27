# Element

## General info
A "Model" class representing a single chemical element from the periodic table.

## Variables table
| Name | Type | Description |
|---|---|---|
| id | int | Database ID. |
| symbol | String | Chemical symbol (e.g., "H", "O"). |
| name | String | Full name (e.g., "Hydrogen"). |
| atomicNumber | int | Position in periodic table. |
| atomicMass | double | Atomic mass. |

## Methods detail
- Getter and Setter methods for all variables.

## Lifecycle
- N/A (Standard Java object).

## UI
- N/A (Data model).

## Inter-component interaction
- Passed between activities and used by adapters to display element info.

## General logic
Simple container for data.

## Simplified explanation
A blueprint for an atom. It keeps track of its name, symbol, and its number on the periodic table.
