# Change Lifecycle

## Purpose

This document defines the required lifecycle for every change managed by Android-SDD.

## Change location

Each change belongs to one project and uses a lowercase kebab-case name:

```text
changes/<project>/<change-name>/
```

## Required artifacts

Every change directory contains:

| Artifact | Purpose |
| --- | --- |
| `proposal.md` | Problem, scope, requirements, and acceptance criteria. |
| `design.md` | Technical design, affected areas, risks, and test approach. |
| `tasks.md` | Approved, atomic implementation tasks. |
| `verification.md` | Evidence that acceptance criteria and quality checks passed. |
| `archive.md` | Factual completion record and implementation references. |
| `retrospective.md` | What went well, what went wrong, lessons learned, and improvements. |

## Lifecycle states and gates

1. **Draft** — The proposal is being prepared. No implementation is allowed.
2. **Proposed** — The proposal is complete and awaiting approval.
3. **Designed** — The approved proposal has a completed design. The design must be approved before task planning is final.
4. **Planned** — Tasks are approved, atomic, and traceable to the design. Implementation may begin.
5. **In progress** — Only approved tasks are implemented.
6. **Verified** — Acceptance criteria, required tests, documentation, and review evidence are recorded in `verification.md`.
7. **Archived** — `archive.md` and `retrospective.md` are complete and the change is no longer active.

## Approval gates

- Proposal approval is required before design is treated as final.
- Design approval is required before tasks are approved.
- Task approval is required before implementation.
- Verification must be complete before archive.
- Archive requires a completed retrospective.

## Archive rules

- Preserve all six artifacts; do not delete completed change records.
- `archive.md` records what was delivered, implementation locations, verification outcome, and follow-up items.
- `retrospective.md` records process learning, not a duplicate delivery summary.
- A retrospective improvement becomes a future change when it requires work; otherwise record why no action is needed.
- A change may be archived as cancelled only when its archive records the reason and any retained learning.
