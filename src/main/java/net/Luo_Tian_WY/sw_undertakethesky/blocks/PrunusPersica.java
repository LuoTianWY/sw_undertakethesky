package net.Luo_Tian_WY.sw_undertakethesky.blocks;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.entity.EntityType;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;

public class PrunusPersica extends Blocks{
    //定义桃木系列方块
    public static final Block PRUNUS_PERSICA_LOG = new PillarBlock(
            FabricBlockSettings.
            of(Material.WOOD).
                    strength(2.0f).
                    mapColor(MapColor.GRAY).
                    sounds(BlockSoundGroup.WOOD)
    );//桃木原木

    public static final Block PRUNUS_PERSICA_LEAVES = new LeavesBlock(
            AbstractBlock.
                    Settings.
                    of(Material.LEAVES).
                    strength(0.2f).
                    ticksRandomly().
                    sounds(BlockSoundGroup.GRASS).
                    mapColor(MapColor.GREEN).
                    allowsSpawning(PrunusPersica::LeavesSpawning).
                    suffocates(PrunusPersica::never).
                    blockVision(PrunusPersica::never).
                    nonOpaque()
    );//桃木树叶

    private static Boolean LeavesSpawning(BlockState state, BlockView world, BlockPos pos, EntityType<?> type){
        return type == EntityType.PARROT || type == EntityType.OCELOT;
    }

    private static boolean never(BlockState state, BlockView world, BlockPos pos){
        return false;
    }
}