# 🃏 Java Card Game Engine

A **modular, extensible card game engine** written in Java, designed with a clean separation between **engine**, **game logic**, and **game flow**.  
The project currently includes a playable implementation of **Higher Card Wins** and is built to support future card games, UI layers, and multiplayer features.

---

## ✨ Project Vision

This project is not a single hardcoded card game.  
It is a **foundation** for building multiple card-based games with:

- Scalable architecture
- Clear object ownership
- UI-independent logic
- Reusable rules

The goal is to model **what exists**, **what is allowed**, and **how the game progresses**, rather than tying logic to presentation.

---

## 🚀 Future Applications

This engine is designed to grow. Planned extensions include:

### 🎮 Additional Game Modes
- Blackjack
- War
- Poker-style comparisons
- Custom rule-based games

### 🧠 Smarter Players
- AI players
- Strategy-based decision making

### 👥 Real Player Interaction
- Turn-based player input
- Card selection and validation

### 🌐 Multiplayer & Networking
- Player vs Player games
- Online lobbies
- Matchmaking

### 💬 Chat System
- In-game messaging
- Multiplayer communication

### 🖥️ UI Layer
- JavaFX or web-based UI
- Animations and visual feedback
- Event-driven rendering

### 📦 Advanced Features
- Event system for UI synchronization
- Save/load game state
- Replay system
- Game statistics and analytics

---

## 🧠 Design Philosophy

This project follows core software design principles:

- **Encapsulation** – Internal state is protected
- **Intent-based APIs** – Objects are told what to do, not how
- **Single Responsibility Principle**
- **Composition over inheritance**
- **Extensibility over premature optimization**

The engine is built to survive change.

---


---

## 🧱 Architecture Overview

The engine is divided into well-defined layers:


Each layer depends only on the layers below it, making the system easy to extend and maintain.

---

## 🧩 Engine Components

### Card
- Represents a single playing card
- Defined by `Suit` and `Rank`
- Immutable

### Deck
- Holds and manages cards
- Supports shuffling and drawing
- Does not contain game rules

### Hand
- Stores cards owned by a player
- Enforces hand size limits
- Does not handle game logic or UI

### Player
- Owns a `Hand`
- Receives and plays cards via intent-based methods
- Tracks score
- Does not contain rule logic

---

## 🧠 Game Logic

### RoundRule (Interface)

Defines how a winner is determined for a single round.


This abstraction allows different game modes to be added without changing game flow.

### HigherCardRule

- Implements `RoundRule`
- Compares card ranks
- Highest-ranked card wins the round

---

## 🔁 Game Flow

### GameState

Controls the lifecycle of the game:

- `SETUP`
- `IN_PROGRESS`
- `FINISHED`

### Game

The `Game` class is the central coordinator. It:

- Initializes players and deck
- Controls game state
- Runs rounds
- Applies rules
- Updates scores
- Determines the final winner

The `Game` class controls **when** things happen, not **how** winners are decided.

---

## ▶️ Current Game Mode: Higher Card Wins

### Rules

- Each round, every player draws one card
- Cards are immediately played
- The player with the highest-ranked card wins the round
- Played cards are discarded
- The game ends when the deck is empty
- The player with the most round wins is declared the winner

This game mode serves as a **reference implementation** for future card games.

---

## 🧪 Running the Game

The project currently runs via a simple console-based `Main` class.

Example output:


The console output is temporary and exists only to verify game logic.

---


## 📌 Project Status

- ✅ Version 1 complete
- 🧪 Logic fully functional
- 🔧 Designed for extensibility
- 🎨 UI planned for future versions

---

## 🙌 Final Note

This project is both a playable card game and a learning-focused engine that demonstrates **real-world system design**.  
It serves as a strong foundation for experimentation, expansion, and advanced features.

---
