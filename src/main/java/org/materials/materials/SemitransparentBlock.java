package org.materials.materials;

import net.minecraft.world.level.block.Block;

import javax.annotation.Nonnull;

public class SemitransparentBlock extends Block
{
    public SemitransparentBlock(Properties properties)
    {
        super(properties
                .noOcclusion()
                .isRedstoneConductor((state, level, pos) -> false)
                .isSuffocating((state, level, pos) -> false)
                .isViewBlocking((state, level, pos) -> false));
    }

    @Override
    public int getLightBlock(@Nonnull net.minecraft.world.level.block.state.BlockState state,
                             @Nonnull net.minecraft.world.level.BlockGetter level,
                             @Nonnull net.minecraft.core.BlockPos pos)
    {
        return 0;
    }

    @Override
    public float getShadeBrightness(@Nonnull net.minecraft.world.level.block.state.BlockState state,
                                    @Nonnull net.minecraft.world.level.BlockGetter level,
                                    @Nonnull net.minecraft.core.BlockPos pos)
    {
        return 1.0F;
    }
}
