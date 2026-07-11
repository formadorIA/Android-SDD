# Android SDD Base Standards

## Purpose

This document defines the mandatory engineering standards for every Android project using Android-SDD.

Every AI agent must follow these rules before proposing, designing or implementing any change.

---

# General Principles

- Simplicity over cleverness.
- Small incremental changes.
- One task = one objective.
- Prefer composition over inheritance.
- Avoid unnecessary abstractions.
- Never modify unrelated files.

---

# Specification First

Never implement code without:

Proposal

↓

Design

↓

Approved Tasks

---

# Task Size

Tasks must be atomic.

A task should be completable in less than 30 minutes.

If a task modifies multiple unrelated components, split it.

---

# Kotlin

Use idiomatic Kotlin.

Avoid Java-style code.

Prefer immutable data.

Use data classes whenever possible.

---

# Jetpack Compose

Prefer stateless composables.

State lives inside ViewModels.

Composable functions should be small.

---

# Architecture

MVVM

Repository Pattern

Use Cases

Dependency Injection

Single Responsibility

---

# Testing

Business logic must be testable.

ViewModels should be unit tested.

Repositories should be mockable.

---

# Documentation

Every architectural decision should be documented.

Update documentation when behavior changes.

---

# AI Rules

Never invent requirements.

Never assume business rules.

If information is missing, ask.

Prefer clarification over hallucination.

Never generate code outside the approved task.

---

# Definition of Done

Proposal approved

Design approved

Tasks approved

Implementation completed

Tests passing

Documentation updated

Review completed