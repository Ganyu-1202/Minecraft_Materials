package org.materials.materials;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import javax.annotation.Nonnull;
import java.util.List;

public class TipsItem extends Item
{
    String toolTipInfo;

    public TipsItem(Properties properties)
    {
        super(properties);
        this.toolTipInfo = null;
    }

    public TipsItem(Properties properties, String toolTipInfo)
    {
        super(properties);
        this.toolTipInfo = toolTipInfo;
    }

    @Override
    public void appendHoverText(@Nonnull ItemStack stack, @Nonnull Item.TooltipContext context,
                                @Nonnull List<Component> tooltip, @Nonnull TooltipFlag flag)
    {
        if (this.toolTipInfo != null)
        {
            if (Screen.hasAltDown())
            {
                String raw = Component.translatable(this.toolTipInfo).getString();
                for (String line : raw.split("\n"))
                {
                    tooltip.add(Component.literal(line).withStyle(ChatFormatting.GRAY));
                }
            }
            else
            {
                tooltip.add(Component.translatable("More_information").withStyle(ChatFormatting.YELLOW));
            }
        }
        else
        {
            tooltip.add(Component.translatable("No_information").withStyle(ChatFormatting.GRAY));
        }
    }
}
