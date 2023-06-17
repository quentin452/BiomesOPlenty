/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.common.block;

import biomesoplenty.init.ModParticles;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.ParticleUtils;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraftforge.common.PlantType;

public class SnowblossomLeavesBlock extends LeavesBlock
{
    public SnowblossomLeavesBlock(BlockBehaviour.Properties p_273704_)
    {
        super(p_273704_);
    }

    @Override
    public void animateTick(BlockState p_272714_, Level p_272837_, BlockPos p_273218_, RandomSource p_273360_)
    {
        super.animateTick(p_272714_, p_272837_, p_273218_, p_273360_);
        if (p_273360_.nextInt(10) == 0)
        {
            BlockPos blockpos = p_273218_.below();
            BlockState blockstate = p_272837_.getBlockState(blockpos);
            if (!isFaceFull(blockstate.getCollisionShape(p_272837_, blockpos), Direction.UP))
            {
                ParticleUtils.spawnParticleBelow(p_272837_, p_273218_, p_273360_, ModParticles.SNOWBLOSSOM_LEAVES.get());
            }
        }
    }
}