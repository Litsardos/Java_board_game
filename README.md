# 🎲 Sorry!-like Java Board Game
_A HY-252 “Object-Oriented Programming” course project – Phase B (2023/24)_

This repository hosts the **second milestone (Phase B)** of a Java implementation
of the classic board game **“Sorry!”**.  
Phase A delivered the basic skeleton, UML and reports; Phase B adds the complete
gameplay loop, graphical interface and card logic.

---

## Table of Contents
1. Game Overview  
2. Implemented Features (Phase B)  
3. Project Structure   
4. Screenshots  

---

## 1 · Game Overview
Two players (Red & Yellow) compete to bring both pawns from **START** to
**HOME** by drawing cards from a shared deck.  
Each card dictates how far a pawn can move and may introduce special rules
(e.g. move backwards, swap, split the move).

Design goals  
* **MVC architecture** (Model / View / Controller separation)  
* Intuitive GUI with drag-free, button-driven actions  
* Accurate reproduction of “Sorry!” rules (card effects, collisions, slide
  zones, home stretch restrictions)

---

## 2 · Implemented Features (Phase B)
| Category | Details |
|----------|---------|
| Game Loop | Turn management, card deck (45 cards), reshuffle when empty |
| Pawns & Board | 2 pawns per player, 60-tile circular track, slide tiles, start & home area |
| Card Logic | 1, 2, 3, 4, 5, 7, 8, 10, 11, 12 & Sorry! cards with correct behaviour (split-move, backwards, swap) |
| Collision Rules | Landing on opponent pawn sends it back to START; safe in home stretch |
| GUI | Java Swing window 900×700, board background, cards panel, info box, modal dialogs for multi-choice cards |
| UX | Name entry screen, live turn indicator, remaining-cards counter, win banner |
| Persistence | N/A (single session) |
| Documentation | UML class diagram (`UML_DIAGRAMS.pdf`) and course report (`HY252-Project_2023_Report_Template_PHASE_B.docx`) |

---

## 3 · Project Structure

├─ Controller/ ← turn logic, event listeners
├─ Model/ ← game state (Board, Pawn, Deck, Card)
├─ View/ ← Swing GUI components
├─ Images/ ← png resources (board, pawns, cards)
├─ src/ ← legacy Phase A code (kept for reference)
├─ UML_DIAGRAMS.pdf
└─ HY252-Project_2023_Report_Template_PHASE_B.docx



---

## 4 · Screenshots
| Start Screen | In-Game (move choice) |
|--------------|-----------------------|
| ![start](Images/start_screen.png) | ![move](Images/move_choice.png) |

_More captures are available in the `Images/` folder._

---
