# ♟️ Chess-naga

![Status: WIP](https://img.shields.io/badge/status-work_in_progress-orange?style=for-the-badge)
![Stage: Alpha](https://img.shields.io/badge/stage-alpha-red?style=for-the-badge)

> [!WARNING]
> **Active Development / Work in Progress** — Architecture and features are actively evolving.

---

## 🌟 Overview

**Chess-naga** is a highly customizable, engine-driven Chess game built in Java. Designed beyond standard 8x8 rules, Chess-naga provides a flexible framework that allows configuring board dimensions, customizing piece rules and visuals, supporting multi-player setups, and building unique game mode presets.

---

## ✨ Key Features

### 🔲 Customizable Board & Layout
- **Flexible Dimensions:** Configure custom row and column counts per board layout/preset (supports non-standard and asymmetric grids).
- **Responsive Visual Scaling:** Automatically calculates optimal tile sizes and centers the board according to canvas dimensions without pixel distortion or gaps.

### 👥 Multi-Player Support
- **Up to 4 Players:** Support for up to 4 concurrent players on a single board layout.
- **Configurable Forward Directions:** Define custom move vectors and forward orientations for each player side (North, South, East, West).

### 🧩 Custom Pieces & Mechanics
- **Custom Visuals:** Support for custom sprite sets and piece textures.
- **Custom Movement & Rules:** Define non-traditional movement paths, step rules, jump behavior, and special capture conditions.

### ♟️ Custom Piece Layouts
- **Starting Formations:** Design initial piece arrangements per player side and orientation.
- **Directional Alignment:** Align piece vectors to match each player's assigned forward direction.

### 🎮 Game Modes & Presets
- **Classic Chess:** Standard 8x8 setup with traditional rules out of the box.
- **Preset System:** Easily define, save, and load custom game variants and experimental modes.

---

## 🚀 Getting Started

### Prerequisites
- **Java Development Kit (JDK):** Version 8 or higher.
- **Engine Library:** Download `naga-engine.jar` from the [java-naga-2d-engine](https://github.com/KarlJosvah/java-naga-2d-engine.git) releases and place it inside the `lib/` directory.

### Dependency Setup (Maven Central Dependencies)
Since `.jar` files in `lib/` are excluded via `.gitignore`, run the following `curl` commands to download the required Maven Central dependencies into `lib/`:

```bash
# Download Batik & XMLGraphics dependencies into lib/ (--create-dirs creates lib if missing)
curl --create-dirs -Lo lib/batik-all-1.19.jar https://repo1.maven.org/maven2/org/apache/xmlgraphics/batik-all/1.19/batik-all-1.19.jar
curl --create-dirs -Lo lib/xml-apis-1.4.01.jar https://repo1.maven.org/maven2/xml-apis/xml-apis/1.4.01/xml-apis-1.4.01.jar
curl --create-dirs -Lo lib/xml-apis-ext-1.3.04.jar https://repo1.maven.org/maven2/xml-apis/xml-apis-ext/1.3.04/xml-apis-ext-1.3.04.jar
curl --create-dirs -Lo lib/xmlgraphics-commons-2.11.jar https://repo1.maven.org/maven2/org/apache/xmlgraphics/xmlgraphics-commons/2.11/xmlgraphics-commons-2.11.jar
```

### Running the Application

Once dependencies are placed in `lib/`, run the project using the build/run script or commands derived from `run.bat.example`:

1. Copy `run.bat.example` to `run.bat`:
   ```cmd
   copy run.bat.example run.bat
   ```

2. Execute the script:
   ```cmd
   run.bat
   ```

---

## 🛠️ Project Structure

```text
Chess-naga/
├── lib/                    # External library dependencies (.jar) - excluded via .gitignore
├── src/
│   ├── core/               # Game state management (PlayState, etc.)
│   ├── game/
│   │   └── chess/
│   │       ├── entity/     # Board, Tile, Piece entity definitions
│   │       └── helpers/    # Layout builders and rect math
│   └── tools/              # Utility helpers
├── run.bat.example         # Template script for compiling and running
└── README.md
```
