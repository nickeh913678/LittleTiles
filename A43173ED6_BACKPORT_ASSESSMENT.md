# Backport Analysis: Commit a43173ed6
## "Added placeTransformableBoxes config option"

**Upstream**: 1.21 (CreativeMD/LittleTiles)  
**Target**: 1.20.1 (LittleTiles mod)  
**Commit Date**: December 5, 2025  
**Analysis Date**: April 6, 2026

---

## 1. FILES MODIFIED (13 Total)

| File | Type | Changes | LOC |
|------|------|---------|-----|
| **LittleAction.java** | Core Logic | Config check + import add | +11/-4 |
| **LittleActionBoxes.java** | Exception Update | Constructor signature | +1/-1 |
| **LittleActionColorBoxes.java** | Exception Update | Constructor signature | +1/-1 |
| **AreaTooLarge.java** | Exception | Constructor refactor | +1/-2 |
| **GridTooHighException.java** | Exception | Constructor refactor | +1/-2 |
| **NotAllowedToConvertBlockException.java** | Exception | Constructor refactor | +1/-2 |
| **NotAllowedToEditException.java** | Exception | Constructor refactor | +1/-2 |
| **NotAllowedToPlaceColorException.java** | Exception | Constructor refactor | +1/-2 |
| **NotAllowedToPlaceException.java** | Exception | Constructor refactor | +1/-2 |
| **NotAllowedToPlaceTransformableException.java** | NEW | New exception class | +9 |
| **LittlePermissionBuild.java** | NEW | New config wrapper | +9 |
| **Placement.java** | Exception Update | Constructor call | +1/-1 |
| **AreaSelectionMode.java** | Exception Update | Constructor call | +1/-1 |

**Summary**: +33/-20 net LOC, but +2 NEW FILES

---

## 2. CORE CHANGE: LittleAction.java

### New Config Check (Lines ~316-320)
```java
if (!LittleTiles.CONFIG.build.get(player).placeTransformableBoxes)
    for (LittleBox box : tile)
        if (box instanceof LittleTransformableBox)
            throw new NotAllowedToPlaceTransformableException();
```

**Location**: In `isAllowedToPlacePreview(Player player, LittleTile tile)` method

**Logic Flow**:
1. Check if player's permission config FORBIDS transformable boxes
2. If forbidden, iterate through all boxes in the tile
3. If ANY box is a `LittleTransformableBox`, throw exception
4. Otherwise, allow placement

### Additional Changes in LittleAction.java
- **Import Added**: `team.creative.littletiles.common.math.box.LittleTransformableBox`
- **Import Changed**: Adds `NotAllowedToPlaceTransformableException`
- **Exception Calls Refactored**: All exception constructors now omit `Player player` parameter

---

## 3. DOES LittlePermissionBuild EXIST IN 1.20?

### ❌ **DOES NOT EXIST**

**In 1.20 (`f:\LT3`)**:
- ❌ LittlePermissionBuild.java - **NOT FOUND**
- ✅ LittleBuildingConfig.java - **EXISTS** (verified in workspace)

**Architecture Difference**:

**1.20 (Current)**:
```
LittleTilesConfig
├── Permission<LittleBuildingConfig> build
│   ├── LittleBuildingConfig (nested) 
│   │   ├── harvestLevelBlock
│   │   ├── editBlockLimit
│   │   ├── placeBlockLimit
│   │   ├── blueprintSizeLimit
│   │   └── gridLimit
```

**1.21 (New Architecture in This Commit)**:
```
LittleTilesConfig
├── Permission<LittlePermissionBuild> build
│   ├── LittlePermissionBuild (new class)
│   │   ├── [inherited LittleBuildingConfig fields]
│   │   └── placeTransformableBoxes (NEW)
```

**What This Means**:
- 1.21 refactored the permission system to use a new `LittlePermissionBuild` wrapper class
- 1.20 still uses `LittleBuildingConfig` directly within `Permission<T>`
- The new exception constructors expect `LittlePermissionBuild` not `Player`
- This is a **major architectural change**, not just a config addition

---

## 4. NEW EXCEPTION: NotAllowedToPlaceTransformableException

### Code Structure
```java
package team.creative.littletiles.common.action.exception;

public class NotAllowedToPlaceTransformableException extends LittleActionException {
    
    public NotAllowedToPlaceTransformableException() {
        super("exception.permission.place.transformable");
    }
}
```

### Characteristics
- ✅ **Simple**: Only 9 lines, no parameters
- ✅ **Self-contained**: No config object needed
- ✅ **Language key**: `"exception.permission.place.transformable"`
- ❌ **Requires**: Translation string in language files

**Status in 1.20**: Does not exist (easy to add)

---

## 5. TRANSFORMABLE BOX SUPPORT IN 1.20

### ✅ Type System Exists
- **LittleTransformableBox.java** - EXISTS (verified)
- **LittleGroup.transformable()** - EXISTS (verified)
  ```java
  public boolean transformable() {
      for (LittleGroup child : children.all())
          if (!child.transformable())
              return false;
      return true;
  }
  ```

### ✅ Rendering Support
- **LittleRenderBoxTransformable.java** - EXISTS (verified)

### ✅ Usage in Placement
- **LittleShapeWall.java** - Uses `LittleTransformableBox` (verified)
- **LittleShapeSlice.java** - Uses `LittleTransformableBox` (verified)

**Conclusion**: The type system is fully present; only the permission check is missing.

---

## 6. BREAKING API CHANGES

### ⚠️ Exception Constructor Refactoring

**ALL These Exception Constructors Changed**:
1. `NotAllowedToConvertBlockException`
2. `NotAllowedToEditException`
3. `NotAllowedToPlaceException`
4. `NotAllowedToPlaceColorException`
5. `AreaTooLarge`
6. `GridTooHighException`

**Change Pattern**:
```java
// BEFORE (1.20 expectation)
public NotAllowedToConvertBlockException(Player player, LittleBuildingConfig config) {
    // ...
}

// AFTER (1.21)
public NotAllowedToConvertBlockException(LittlePermissionBuild config) {
    // ...
}
```

**Impact**: 
- Any code calling these exceptions with `(Player player, config)` will break
- Search confirms these are called in multiple places
- Would require refactoring ALL call sites

---

## 7. ANALYSIS SUMMARY

### What's Easy to Backport
1. ✅ `NotAllowedToPlaceTransformableException.java` - NEW exception (9 LOC, no dependencies)
2. ✅ `LittleTransformableBox` check logic - Simple instanceof check
3. ✅ Config option `placeTransformableBoxes` - Can be added directly

### What's Difficult to Backport
1. ❌ **LittlePermissionBuild Architecture** - Entire permission framework refactoring
2. ❌ **Exception Constructor Changes** - Breaking API changes across 6+ exception classes
3. ❌ **Config Storage Location** - Need to decide: add to LittleBuildingConfig or create LittlePermissionBuild?

### Configuration Location Conflict

**Problem**: Commit adds `placeTransformableBoxes` to `LittlePermissionBuild`, but that class doesn't exist in 1.20.

**Option A**: Add to `LittleBuildingConfig` instead
- Pro: Doesn't require new class
- Con: Different from upstream; config lives in different place

**Option B**: Create `LittlePermissionBuild` in 1.20
- Pro: Matches upstream exactly
- Con: Breaking API change for ALL exception constructors
- Con: Affects other parts of system expecting old structure

**Option C**: Skip the commit entirely
- Pro: Avoids architectural disruption
- Con: Users can't control transformable box placement

---

## FINAL ASSESSMENT

### 🔴 VERDICT: **RISKY** → **SKIP**

**Risk Level**: 🔴 **HIGH**

**Reasoning**:
1. **Architecture Mismatch**: Assumes `LittlePermissionBuild` exists (it doesn't)
2. **Breaking API Changes**: Exception constructors change signatures across 6+ classes
3. **Config Location Uncertainty**: Unclear where to put `placeTransformableBoxes`
4. **Pervasive Changes**: Affects exception handling throughout the codebase
5. **Minor Feature**: This is a permission control, not a critical bug fix

**If You Ignore the Warning and Backport Anyway**:
- ⚠️ You will get compilation errors on all exception calls
- ⚠️ You'll need to refactor exception calls across multiple files
- ⚠️ You may introduce runtime bugs if call sites are missed
- ⚠️ The permission framework will be partially implemented

---

## RECOMMENDATION

### Best Path Forward

**DO NOT BACKPORT commit a43173ed6 in its current form.**

### Alternative Approaches

**Option 1: Wait for 1.20.2+ to include permission framework modernization**
- Best long-term approach
- Aligns with upstream architecture
- No manual refactoring needed

**Option 2: Implement minimal version in 1.20**
- Create just `NotAllowedToPlaceTransformableException`
- Add `placeTransformableBoxes` boolean to `LittleBuildingConfig`
- Add config check in `LittleAction.isAllowedToPlacePreview()`
- Do NOT refactor exception constructors
- Risk: Diverges from upstream but avoids breaking changes

**Option 3: Full permission framework upgrade**
- Create `LittlePermissionBuild` wrapper class
- Refactor all exception constructors
- Extensive testing required
- High implementation effort but aligns with 1.21

---

## TRANSLATION REQUIREMENTS

If backporting, add language strings:
```json
"exception.permission.place.transformable": "You are not allowed to place transformable boxes"
```

---

## FILES THAT EXIST IN 1.20 ✅
- [LittleAction.java](src/main/java/team/creative/littletiles/common/action/LittleAction.java)
- LittleActionBoxes.java
- LittleActionColorBoxes.java
- [Placement.java](src/main/java/team/creative/littletiles/common/placement/Placement.java)
- [AreaSelectionMode.java](src/main/java/team/creative/littletiles/common/placement/selection/AreaSelectionMode.java)
- Exception classes (all of them)
- LittleTransformableBox.java
- LittleGroup.java

## FILES THAT DON'T EXIST IN 1.20 ❌
- LittlePermissionBuild.java (NEW in 1.21)
- NotAllowedToPlaceTransformableException.java (NEW in 1.21)

---

**Analysis Completed**: April 6, 2026  
**Analyst**: GitHub Copilot
