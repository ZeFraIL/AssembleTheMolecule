# Substance

## General info
A "Model" class representing a chemical substance (molecule or compound).

## Variables table
| Name | Type | Description |
|---|---|---|
| id | int | Database ID. |
| formula | String | Chemical formula (e.g., "H2O"). |
| name | String | Common name (e.g., "Water"). |
| className | String | Chemical class (e.g., "Oxide"). |
| difficulty | int | Difficulty level for gameplay. |

## Methods detail
- Getter and Setter methods for all variables.

## Lifecycle
- N/A (Standard Java object).

## UI
- N/A (Data model).

## Inter-component interaction
- Extensively used across `GameActivity`, `SubstanceListActivity`, and `DrawingActivity`.

## General logic
Holds all the details about a specific chemical substance.

## Simplified explanation
A folder that contains everything we know about a specific substance: its name, its formula, and what group it belongs to.
