package org.materials.materials;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.Item;
import net.minecraft.network.chat.Component;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.world.level.block.Block;

import java.util.List;

public class TipsBlockItem extends BlockItem
{
    String toolTipInfo;

    public TipsBlockItem(Block block, Properties properties)
    {
        super(block, properties);
        this.toolTipInfo = "No additional information available. ";
    }

    public TipsBlockItem(Block block, Properties properties, String toolTipInfo)
    {
        super(block, properties);
        this.toolTipInfo = toolTipInfo;
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltip, TooltipFlag flag)
    {
        if (Screen.hasAltDown())
        {
            tooltip.add(Component.translatable(this.toolTipInfo).withStyle(ChatFormatting.GRAY));
        }
        else
        {
            tooltip.add(Component.translatable("More_information").withStyle(ChatFormatting.YELLOW));
        }
    }
}