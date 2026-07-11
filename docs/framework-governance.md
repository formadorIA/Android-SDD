# Framework Governance

## Purpose

Android-SDD is a reusable engineering framework. This document defines how it evolves without creating unnecessary process.

## Ownership and scope

- `docs/`, `behavior/`, `agents/`, and `templates/` are framework-owned and apply to all projects unless a standard explicitly says otherwise.
- `projects/<project>/knowledge/` is owned by the project and is the canonical source for its requirements and conventions.
- `projects/<project>/docs/decisions/` contains durable project-specific ADRs.
- A project may add guidance only when it does not conflict with framework standards.

## Changing the framework

Framework changes use the same change lifecycle as product changes. A framework change is documented under `changes/framework/<change-name>/`.

Update the affected standard, workflow, agent instruction, or template together with its change artifacts. Avoid unrelated rewrites.

## Decision principles

- Prefer the smallest rule that solves a demonstrated recurring problem.
- Keep YAGNI as a core principle: do not add an artifact, folder, automation, or mandatory process without immediate, clear value.
- Prefer one canonical source of truth; link instead of duplicating content.
- Preserve completed change records and ADRs as historical evidence.

## Compatibility and deprecation

- Changes to required artifacts or lifecycle gates must be documented in a framework change.
- Existing archived changes are historical records and do not need to be rewritten.
- When replacing a standard or template, mark the replacement in the changed document and state how new work should transition.

## Learning loop

Completed changes include a retrospective. Repeated lessons that improve more than one project may be promoted to framework documentation or `learning/` through a framework change.
