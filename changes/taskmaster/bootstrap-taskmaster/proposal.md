# Proposal: Bootstrap TaskMaster

## Status

Proposed — awaiting review.

## Project purpose

TaskMaster is a single-user Android application that helps people capture and track personal tasks in one place, without requiring an account or network connection.

## Target users

- Individuals who need a simple, dependable way to track everyday personal tasks on their Android device.
- Users who prefer a lightweight, offline task list over collaboration or enterprise workflow tools.

## User problem

People can lose track of everyday tasks when they rely on memory or unstructured notes. They need a quick, local way to record tasks and see which ones remain to be done.

## Business value

The MVP gives users a reliable offline task list for tracking outstanding personal work. Its initial product outcome is that a user can create, complete, edit, delete, and recover their tasks after leaving the application.

## MVP scope

- Create a personal task with a title.
- View the current list of tasks.
- Mark a task as complete or incomplete.
- Edit a task title.
- Delete a task.
- Preserve task data locally on the device with Room.
- Build the Android user interface with Kotlin and Jetpack Compose.

## Out of scope

- User accounts, sign-in, and cloud synchronization.
- Shared lists, collaboration, or task assignment.
- Reminders, notifications, recurring tasks, and calendar integrations.
- Categories, tags, priorities, attachments, and custom sorting.
- Web, desktop, or iOS clients.
- Importing or exporting tasks.

## Acceptance criteria

1. A user can create a task with a non-empty title.
2. A title containing only whitespace is rejected; leading and trailing whitespace is removed before a task is saved.
3. A user can view all saved tasks and distinguish completed tasks from incomplete tasks.
4. Incomplete tasks are shown before completed tasks; within each group, the most recently created task is shown first.
5. A user can change a task's completion state. Completed tasks remain visible in the completed section and can be marked incomplete again.
6. A user can edit the title of an existing task, subject to the same title validation.
7. A user can delete an existing task.
8. Tasks created or updated by the user remain available after app relaunches and device restarts, using local Room persistence.
9. The behavior is usable without an account or network connection.

## Assumptions

- TaskMaster is a single-user, offline-first Android application for personal task management.
- Each MVP task has only a title and completion state.
- Task data is stored locally in a Room database. Data is expected to survive app relaunches and device restarts; uninstalling the application removes locally stored data.
- Kotlin and Jetpack Compose are the required implementation technologies for the Android application.
- Supported Android versions, visual design, and detailed technical architecture will be defined in later approved artifacts.

## Risks

- The minimal task data model may need revision if future requirements introduce due dates, priorities, categories, or synchronization.
- Local-only storage may create migration and synchronization considerations in future releases.
- Incorrect local-database migrations in future releases could affect saved task data.

## Review request

Confirm the product assumptions, MVP scope, and acceptance criteria before proceeding to the design phase.
