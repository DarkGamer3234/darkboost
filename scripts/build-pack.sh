#!/usr/bin/env bash
set -euo pipefail
MC_VERSION="26.3"
OUT="dist/DarkBoost-\${MC_VERSION}-Fabric"
MODS_DIR="\${OUT}/mods"
rm -rf dist
mkdir -p "\${MODS_DIR}"

download_mod() {
  local slug="$1"
  local label="$2"
  echo "Resolving \${label} for Minecraft \${MC_VERSION} (Fabric)..."
  local version_json
  version_json="$(curl --fail --silent --show-error \
    -H "User-Agent: DarkBoost/0.1.0 (https://github.com/DarkGamer3234/darkboost)" \
    "https://api.modrinth.com/v2/project/\${slug}/version")"
  local file_url
  file_url="$(printf '%s' "\${version_json}" | jq -r --arg mc "\${MC_VERSION}" '
    [.[] | select(.game_versions | index($mc))
         | select(.loaders | index("fabric"))
         | select(.version_type == "release")]
    | first
    | .files[]
    | select(.primary == true)
    | .url // empty
  ')"
  if [[ -z "\${file_url}" ]]; then
    echo "ERROR: No stable Fabric release for \${label} on Minecraft \${MC_VERSION}." >&2
    exit 1
  fi
  local filename
  filename="$(basename "\${file_url}")"
  curl --fail --location --silent --show-error "\${file_url}" -o "\${MODS_DIR}/\${filename}"
  echo "Added \${label}: \${filename}"
}

download_mod "sodium" "Sodium"
download_mod "lithium" "Lithium"
download_mod "ferrite-core" "FerriteCore"
download_mod "immediatelyfast" "ImmediatelyFast"
download_mod "entityculling" "Entity Culling"
download_mod "moreculling" "More Culling"
download_mod "cloth-config" "Cloth Config (More Culling dependency)"
download_mod "dynamic-fps" "Dynamic FPS"
download_mod "badoptimizations" "BadOptimizations"
download_mod "replaymod" "ReplayMod"

cat > "\${OUT}/README.txt" <<'EOF'
DarkBoost - Minecraft 26.3 Fabric performance pack

INSTALL
1. Install Fabric Loader for Minecraft 26.3 (Java 25 required).
2. Open your Minecraft game directory.
3. Copy every .jar from this pack's mods folder into your game's mods folder.
4. Launch the Fabric 26.3 profile.

INCLUDED
- DarkBoost: this project's own client mod.
- Sodium: faster world rendering.
- Lithium: optimised game logic and ticking.
- FerriteCore: reduced memory use.
- ImmediatelyFast: faster GUI, text, particles and other rendering.
- Entity Culling: skips entities and block entities hidden from view.
- More Culling: additional block and model culling.
- Cloth Config: configuration library required by More Culling.
- Dynamic FPS: reduces resource use while Minecraft is inactive.
- BadOptimizations: small client-side waste-reduction optimisations.
- ReplayMod: replay recording and playback; kept separate from DarkBoost.

NOTES
- All included third-party mods remain separate JARs in the mods folder.
- Do not put this client pack on a dedicated server.
- Back up important worlds before changing mod setups.
- Performance varies by hardware, settings and world.
- ReplayMod is included as a separate mod and is not implemented by DarkBoost.
EOF

cp build/libs/darkboost-*.jar "\${MODS_DIR}/"
cd dist
zip -qr "DarkBoost-\${MC_VERSION}-Fabric.zip" "DarkBoost-\${MC_VERSION}-Fabric"
