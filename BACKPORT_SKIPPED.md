# Backport Skipped / Partial Items (1.21 -> 1.20)

This file tracks upstream commits that were intentionally skipped, partially applied, or treated as already present during the 1.21 -> 1.20 backport.

## pre166

- `58d93a6e9` - mark mode fix
  - Status: skipped
  - Reason: `LittleToolShaper` / related 1.21 structure not present in 1.20, and 1.20 mark handling path already behaves correctly.

## pre167

- `260a60b3d` - immediate broadcast after transition placement
  - Status: partially applied
  - Reason: call sites were applied, but `PlacementResult.broadcastChangesImmediately` was stubbed in 1.20 because `ChunkMap.getVisibleChunkIfPresent(long)` is `protected` in Forge 1.20 and not safely accessible from this code path.

## pre169

- `f3a52c167` - PillarShapeConfig tooltip crash fix
  - Status: skipped
  - Reason: `PillarShapeConfig` class does not exist in 1.20 branch.

- `8d70423d2` - LittleToolPlacer null-worker crash fix
  - Status: skipped
  - Reason: `LittleToolPlacer` class/path from upstream commit does not exist in 1.20 branch.

- `235949331` - signal mode description includes length
  - Status: skipped as already present
  - Reason: equivalent `description(int configuredDelay)` behavior is already present in current 1.20 code.

## pre170

- `293032b13` - timeline markers for advanced door
  - Status: skipped
  - Reason: depends on GUI-layer/API differences (including upstream Fabric-side annotations and newer timeline behavior) that are not a direct/safe drop-in for 1.20 dependencies.

- `31c197d74` - animation selection behind blocks
  - Status: skipped as already present
  - Reason: 1.20 already uses the corrected distance comparison logic.

- `818448a73` - door removal/child connection refresh
  - Status: skipped as already present
  - Reason: 1.20 already contains the relevant connection-refresh pattern (`getStructureUncached`, `checkConnection`, `removeStructureSameLevelWithoutCheck`).

- `d378bdc2e` - tool still held after switching worlds
  - Status: partially applicable / mostly already present
  - Reason: `LevelHandlersClient` unload timing is already correct in 1.20; the upstream `PreviewRenderer.tools = null` part is not directly applicable because that field/path is not present in this 1.20 implementation.

## pre171

- No skipped items at time of writing.
  - `c576c2bf2` and `c9e612e65` were already present in current 1.20 code.
  - `b9128ba9b` translation key already present.

## pre172

- `e0a757396` - invalid signal component break handling
  - Status: skipped as already present
  - Reason: null-check before `connection.findNetwork()` is already in current 1.20 code.

- `6b876bcfb` - signal GUI parse exception logging + rendering cell fix
  - Status: partially skipped as already present
  - Reason: parse-exception logging (`LittleTiles.LOGGER.catching(e)`) already present; no additional local change needed for that part.

- `0f1973d67` - fallback component in signal dialog
  - Status: skipped as already present
  - Reason: fallback `SignalComponentType.INVALID` component return path already exists in current 1.20 code.

- `3b82d7b56` and `8a99376a5` - `LittleToolShaper` crash fixes
  - Status: skipped
  - Reason: `LittleToolShaper` class/path from upstream commit does not exist in 1.20 branch.

- `51430b5f6` - add `zh_cn.json`
  - Status: skipped as already present
  - Reason: `src/main/resources/assets/littletiles/lang/zh_cn.json` already exists in this 1.20 branch.

## pre173

- `4036887d1` - missing structure exception in old converter
  - Status: skipped as already present
  - Reason: `LittleMissingStructureException` and throw site are already present in current 1.20 code.

- `97f6e19b5` - `LittleGroup.copy` should copy structure data safely
  - Status: skipped as already present
  - Reason: current 1.20 already uses `structure != null ? structure.copy() : null` in both relevant copy paths.

- `36c44ed6b` - placer grid reset fix in `LittleToolPlacer`
  - Status: skipped
  - Reason: `LittleToolPlacer` class/path from upstream commit does not exist in 1.20 branch.
