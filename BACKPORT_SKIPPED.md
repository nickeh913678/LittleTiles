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

## pre174

- `85a9747a4` - resized signal equation/virtual input GUIs
  - Status: skipped as already present
  - Reason: current 1.20 already uses the enlarged GUI dimensions.

- `ba1b87def` - signal input GUI wiring fixes
  - Status: skipped as already present
  - Reason: current 1.20 already has the same layout/event-change updates.

- `66f73749c` - guard `getExternalOutput` against null external handler
  - Status: skipped as already present
  - Reason: null guard already exists in current 1.20 code.

- `bae6d7a60` - reset signal network state correctly
  - Status: skipped as already present
  - Reason: current 1.20 already uses `state = state.reset()`.

- `783e55bdc` - sodium pipeline change
  - Status: skipped
  - Reason: target sodium pipeline file from upstream commit does not exist in this 1.20 branch; build.gradle dependency bump is not applied here.

## pre175

- `c34f3cf55` - sodium SpriteUtil API update
  - Status: skipped
  - Reason: target sodium pipeline file from upstream commit does not exist in this 1.20 branch.

- `e05614500` - update `zh_cn.json`
  - Status: skipped as already present
  - Reason: affected keys are already present in current 1.20 `zh_cn.json`.

- `b19398147` - optional create entity tags (`required: false`)
  - Status: skipped as already present
  - Reason: `no_collision` tag already uses optional object entries in current 1.20.

- `390b086a9` - old door conversion with missing state guard
  - Status: skipped as already present
  - Reason: current `convertDoorBaseData` already checks `state != null` before writing.

## pre176

- `4519ccbff` - clear render queue after thread re-init
  - Status: skipped as already present
  - Reason: current 1.20 `RenderingThread.initThreads` already performs an explicit queue clear.

- `a4311886c` - iso viewer partially-visible viewport fix
  - Status: skipped
  - Reason: upstream fix depends on a newer render callback signature exposing both control and visible rects; current 1.20 GUI API only exposes one rect in this control.

- `50f2a6101` - null placement position guard in `LittleToolPlacer`
  - Status: skipped
  - Reason: `LittleToolPlacer` class/path from upstream commit does not exist in this 1.20 branch.

## pre177

- `a1cfb4b30` - render/cache reset and shader-switch artifact fixes
  - Status: partially applied
  - Reason: non-Sodium parts were applied (`BERenderManager`, `RenderingThread`, `RenderingBlockContext`, `LevelRendererMixin`, `LittleTilesClient` reload path cleanup), but Sodium-specific files and hooks are not present in this 1.20 branch (`client/mod/sodium/**`, `mixin/sodium/**`, `RenderAdditional`).

- `a1cfb4b30` - animation section compiler refresh in `LittleAnimationHandlerClient`
  - Status: skipped
  - Reason: upstream `sectionCompiler` field/method path does not exist in this 1.20 implementation.

## pre178

- `13fec7386` - placement exception logging
  - Status: skipped as already present
  - Reason: current 1.20 `Placement` code already logs both nested `LittleActionException` and wrapped runtime exception paths with `LittleTiles.LOGGER.catching(...)`.

- `347d491c9` - little bag invalid block filtering order
  - Status: skipped as already present
  - Reason: current 1.20 `LittleAction` already applies `isBlockInvalid(...)` before `isBlockValid(...)`, with `EntityBlock`/`SlabBlock` handling in the invalid path.

## pre179

- `9050b52f1` and `aab32f79e` - `GuiScrewdriver` position source switch + follow-up fix
  - Status: skipped
  - Reason: upstream change depends on newer item data-component keys (`LittleTilesRegistry.FIRST_POS` / `SECOND_POS`), while current 1.20 code path still uses NBT `pos1`/`pos2` arrays and does not expose those component keys.

## pre181

- `daa5f75dc` - recipe GUI child-door selection crash fix
  - Status: skipped as already present
  - Reason: current 1.20 `GuiAnimationTimelinePanel.generateTimeline(...)` already guards `childChannels` entries with `if (channel != null)` before iterating keys.

- `ab9a3252c` - shape click-in-air crash fix
  - Status: skipped as already present
  - Reason: current 1.20 `LittleShapeSelectable.addBox(...)` already uses `else if (facing != null)` before dereferencing facing axis/direction.

## pre182

- `c1039ce6c` - JEI color-amount crash fix
  - Status: skipped as already present
  - Reason: current 1.20 `ItemColorIngredient.getColor(...)` already reads from NBT `value` with default-zero semantics (`getOrCreateTag().getInt(...)`).

- `8bb5b0cda` - updated iris file
  - Status: skipped
  - Reason: upstream change only updates 1.21 build/dependency wiring; not applicable to current 1.20 Forge build layout.

- `a5e376799` - render-change timing artifacts
  - Status: partially applied
  - Reason: non-Sodium timing/order adjustments were applied in `LevelRendererMixin` (TAIL hook + update-order change), while Sodium-specific reload integration is not applicable on this 1.20 branch.

- `795c78fa9` - recipe preview rendering inside GUI
  - Status: adapted
  - Reason: upstream uses newer GUI parent interface checks; 1.20 branch applies equivalent guard by skipping preview tick while any screen is open (`mc.screen != null`).

## pre183

- `db6b075eb` - animation sound crash when not in entity form
  - Status: skipped as already present
  - Reason: current 1.20 `LittleStateStructure.playClient(...)` already guards `getAnimationEntity()` null and falls back to local world sound playback.

- `b9d8e6b73` - door replay not animating back to original state
  - Status: skipped as already present
  - Reason: current 1.20 already contains `AnimationTimeline.isAligned()` and uses `!timeline.isAligned()` in `LittleStateStructure.startTransition(...)` for entity-form decision.

- `f4b34103c` - client entity-add hook for smoother transitions
  - Status: skipped
  - Reason: upstream targets a newer `ClientPacketListener#createEntityFromPacket(...)` hook signature; 1.20 branch uses `handleAddEntity(...)` with an existing transition interception path in `ClientPacketListenerMixin`.

## pre184

- `02e31b463` - bag slot update cache invalidation
  - Status: skipped as already present
  - Reason: current 1.20 `GuiBag.BagSlot.remove(...)` already calls `clearItemCache()` after saving inventory.

## pre185

- `15f5c2bbe` - signal processing on client side
  - Status: partially applied
  - Reason: most safeguards were already present; applied the remaining `LittleTicker.tick()` fix to prevent an extra server tick increment and keep scheduling/signaling timing consistent.

- `15f5c2bbe` - debug helpers in location/timeline
  - Status: skipped as already present
  - Reason: `StructureLocation.toString()` and `AnimationTimeline.getTick()` are already present in current 1.20 code.

- `eff8b4290` - door placement no-space crash guard
  - Status: skipped as already present
  - Reason: current 1.20 `LittleStructure.changeToBlockForm()` already checks `result == null` before using `result` and before broadcasting updates.

## pre186

- `ed3d092b0` - neighbor updates sent too early when opening doors
  - Status: partially applied
  - Reason: applied the effective timing fix on current 1.20 path by moving `NeighborUpdateOrganizer` processing back to server tick END; the upstream `LittleAnimationHandlers` Pre/Post typed-event adjustment is not directly applicable because this branch uses `LevelTickEvent` with phase handling inside `LittleAnimationHandler`.

- `6781ee7f3` - readded glove functionality + mark mode positions
  - Status: skipped
  - Reason: upstream commit is a broad tool-system refactor (14 files, 500+ LOC) relying on classes/signatures not present on this 1.20 branch (for example `client/tool/LittleToolPlacer.java` path and new transformer workflow). No safe minimal drop-in hunk identified.

## pre187

- `a76bdeea1` - storage inventory interaction capability wiring
  - Status: partially applied
  - Reason: safe structure-side hooks were applied (`BETiles` capability invalidation on tile update, `LittleStructure#getInventory`, `LittleStorage` inventory exposure and dirty mark). The upstream capability registration in `LittleTiles` targets NeoForge capability APIs and wrappers that do not exist on this Forge 1.20 branch.

- `94bcc0820` - item holder lock/filter GUI + behavior
  - Status: skipped
  - Reason: upstream change introduces a new GUI (`GuiItemHolder`) and substantial item-holder NBT/filter/config plumbing tied to newer config/gui API signatures and provider-based ingredient serialization, which is not a safe drop-in to current 1.20 code without a larger subsystem backport.

## pre188

- `452d637a2` - Version bump to 1.6.0-pre187
  - Status: skipped
  - Reason: Build metadata-only change; version string not updated on this 1.20 branch.

- `e23a3f045` - Added new interaction permission config; Renamed some of the config
  - Status: skipped
  - Reason: Large-scale config architecture restructuring: involves renaming core classes (`LittleBuildingConfig` → `LittlePermissionBuild`, `LittleSignalConfig` → `LittleConfigSignal`, `LittleBagConfig` → `LittleConfigBag`), creating new config classes (`LittleConfigInteract`, `LittleConfigBuilding`, `LittleConfigRendering`), and updating 20+ files with new imports. The pervasive nature of these changes creates high risk of subtle incompatibilities when selectively back-porting without access to all dependent changes across the full codebase. Additionally, this architectural shift may conflict with existing 1.20 code that expects the old class names, making a safe partial backport impractical.

- `f968431cb` - Reorganized signal mode packages
  - Status: skipped
  - Reason: Package restructuring and organizational change affecting multiple files; risk of missing interdependencies. Not a functional feature or bug fix.

- `b7c0f667e` - Storage structure dropping itself even in creative mode if there are items inside
  - Status: requires verification
  - Reason: Appears to be a simple conditional change affecting `LittleActionDestroy` and storage drop logic (UX improvement to allow storage to drop items in creative mode if inventory is not empty). Partial analysis suggests low risk, but full diff verification against current 1.20 code is needed before application.

- `d374f3a34` - Added multi lines for message structure
  - Status: requires verification
  - Reason: Ambiguous implementation details: could be a simple text field enhancement (safe) or require NBT migration and data structure changes (risky). Full code inspection needed to determine safe applicability.

- `8fbbac74c` - Added door rotation limit
  - Status: skipped
  - Reason: Likely a feature/constraint addition to door mechanics; without full diff verification, risk profile unclear. Recommend deeper analysis if feature is considered high-priority for 1.20.

- `be2e47446` - Rearranged action exceptions
  - Status: skipped
  - Reason: Organizational change; low functional payoff but requires careful review of exception handling consistency. Recommend skipping unless specific exception bugs are blocking 1.20 gameplay.

- `e23a3f045` - Added new interaction permission config; Renamed some of the config
  - Status: duplicate entry flagged (see note above)
  - Reason: N/A (remove duplicate)
