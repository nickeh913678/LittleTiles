package team.creative.littletiles.common.gui.controls.filter;

import net.minecraft.world.entity.player.Player;
import team.creative.creativecore.common.gui.controls.simple.GuiTextfield;
import team.creative.creativecore.common.util.filter.BiFilter;
import team.creative.littletiles.common.block.little.tile.LittleTile;
import team.creative.littletiles.common.block.little.tile.parent.IParentCollection;
import team.creative.littletiles.common.filter.TileFilters;

public class GuiElementFilterName extends GuiElementFilter {
    
    protected GuiTextfield namebox;
    
    public GuiElementFilterName(Player player, String filter) {
        add(namebox = new GuiTextfield("name", filter != null ? filter : ""));
        namebox.setExpandableX();
    }
    
    @Override
    public BiFilter<IParentCollection, LittleTile> get() {
        String text = namebox.getText().trim();
        if (text.isEmpty())
            return null;
        return TileFilters.name(text);
    }
    
}
