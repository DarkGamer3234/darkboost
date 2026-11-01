# DarkBoost

DarkBoost is the project-owned client mod and the name of the Minecraft 26.3 Fabric performance pack.

## Included mods

The GitHub Actions build assembles an installable ZIP containing separate, compatible Fabric mods:

| Mod | What it optimises |
|---|---|
| DarkBoost | This project's own client mod |
| Sodium | Replaces the world renderer for higher FPS |
| Lithium | Optimises game logic, physics and ticking |
| FerriteCore | Reduces memory use |
| ImmediatelyFast | Optimises GUI, text, particles and other rendering |
| Entity Culling | Skips rendering entities and block entities hidden behind walls |
| More Culling | Adds further block/model culling |
| Cloth Config | Configuration library required by More Culling |
| Dynamic FPS | Reduces resource use while the game is inactive |
| BadOptimizations | Removes selected unnecessary client work |
| ReplayMod | Replay recording/playback, installed separately from DarkBoost |

Third-party mods are not merged into DarkBoost's JAR. They are packaged as their own JARs in the ZIP's \`mods\` folder, which is the normal Fabric installation model.

## Install

1. Install Fabric Loader for Minecraft 26.3 and use Java 25.
2. Download the \`DarkBoost-26.3-Fabric.zip\` build artifact from GitHub Actions.
3. Extract it.
4. Copy all JAR files from the extracted \`mods\` folder into your Minecraft \`mods\` folder.
5. Launch your Fabric 26.3 profile.

## Build

\`\`\`bash
gradle build
bash scripts/build-pack.sh
\`\`\`

The GitHub Actions workflow builds the DarkBoost JAR, resolves the latest stable Fabric releases for Minecraft 26.3 from Modrinth, and packages the performance mods into a ZIP. The build fails rather than silently omitting a mod if a compatible stable release cannot be found.

## Requirements

- Minecraft Java Edition 26.3
- Fabric Loader 0.19.5 or newer
- Java 25
- Fabric API (declared as a DarkBoost dependency)

## ReplayMod

ReplayMod is included as a separate mod in the performance pack. DarkBoost does not reimplement replay recording or playback. Keep ReplayMod's own settings and compatibility notes in mind when updating it.

## Notes

- This is a client-side pack. Do not copy it to a dedicated server.
- Performance gains depend on hardware, graphics settings and the world being played.
- Back up important worlds before changing your mod setup.
