# DarkBoost

A client-side Fabric performance mod for Minecraft 26.3.

## Project status

The initial Fabric project scaffold is in place. The next development steps are the in-game settings screen, configurable performance options, and presets.

## Planned features

- Orange-and-black in-game configuration screen
- Performance settings with individually configurable toggles
- Performance presets (for example: Balanced and Maximum FPS)
- Persistent client configuration
- Compatibility testing alongside ReplayMod

## ReplayMod

ReplayMod is a separate mod and will remain installed separately in the `mods` folder. DarkBoost is intended to coexist with it; DarkBoost does not reimplement ReplayMod's recording or replay features.

## Development

- Minecraft: 26.3
- Fabric Loader: 0.19.5
- Fabric API: 0.162.0+26.3
- Java: 25
- Gradle: 9.7.1
- Fabric Loom: 1.19-SNAPSHOT

Run the build with:

```bash
gradle build
```

The GitHub Actions workflow also builds the project on pushes to `main` and pull requests.

## Important

The initial scaffold is not yet a finished performance mod. The GUI and optimisation features are still being implemented and the first CI build has not yet been confirmed.
