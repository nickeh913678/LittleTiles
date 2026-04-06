package team.creative.littletiles.common.gui.controls.filter;

import net.minecraft.world.entity.player.Player;
import team.creative.creativecore.common.util.filter.BiFilter;
import team.creative.littletiles.common.block.little.tile.LittleTile;
import team.creative.littletiles.common.block.little.tile.parent.IParentCollection;
import team.creative.littletiles.common.filter.TileFilters;

public class GuiElementFilterMissing extends GuiElementFilter {
    
    public GuiElementFilterMissing(Player player) {
    }
    
    @Override
    public BiFilter<IParentCollection, LittleTile> get() {
        return TileFilters.missing();
    }
    
}
