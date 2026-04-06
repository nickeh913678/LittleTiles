package team.creative.littletiles.common.placement;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import team.creative.littletiles.common.action.LittleAction;
import team.creative.littletiles.common.block.entity.BETiles;
import team.creative.littletiles.common.block.little.tile.LittleTile;
import team.creative.littletiles.common.block.little.tile.group.LittleGroup;
import team.creative.littletiles.common.block.little.tile.parent.ParentCollection;
import team.creative.littletiles.common.grid.LittleGrid;
import team.creative.littletiles.common.ingredient.LittleIngredients;
import team.creative.littletiles.common.math.box.collection.LittleBoxes;
import team.creative.littletiles.common.math.box.collection.LittleBoxesSimple;
import team.creative.littletiles.common.structure.LittleStructure;
import team.creative.littletiles.common.structure.LittleStructureType;

public class PlacementResult {
    
    public final BlockPos pos;
    public final LittleGroup placedPreviews;
    public final LittleBoxes placedBoxes;
    private BlockPos lastPos = null;
    public final List<BETiles> blocks = new ArrayList<>();
    public LittleStructure parentStructure;
    public final LittleIngredients ingredients;
    
    public PlacementResult(BlockPos pos) {
        this.pos = pos;
        this.placedPreviews = new LittleGroup();
        this.placedBoxes = new LittleBoxesSimple(pos, LittleGrid.MIN);
        this.ingredients = new LittleIngredients();
    }
    
    public void addPlacedTile(LittleStructureType type, ParentCollection parent, LittleTile tile) {
        if (lastPos == null || !lastPos.equals(parent.getPos())) {
            lastPos = parent.getPos();
            blocks.add(parent.getBE());
        }
        placedPreviews.add(parent.getGrid(), tile, tile.copy());
        placedBoxes.addBoxes(parent, tile);
        if (type == null || type.tilesCountAsIngredient())
            ingredients.add(LittleAction.getIngredients(parent, tile));
    }
    
    public void broadcastChangesImmediately(ServerLevel level) {
        // ChunkMap.getVisibleChunkIfPresent() is protected in Forge 1.20; skip immediate broadcast
    }
    
}
