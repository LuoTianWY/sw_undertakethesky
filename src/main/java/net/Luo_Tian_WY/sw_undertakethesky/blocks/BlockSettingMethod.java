package net.Luo_Tian_WY.sw_undertakethesky.blocks;

import net.minecraft.block.*;
import net.minecraft.entity.EntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;

public class BlockSettingMethod extends Blocks{
    //该方法确定允许在树叶上生成的生物种类，此处沿用原版设定，即鹦鹉与豹猫
    public static Boolean LeavesSpawning(BlockState state, BlockView world, BlockPos pos, EntityType<?> type){
        return type == EntityType.PARROT || type == EntityType.OCELOT;
    }

    //目的只是返回一个 false
    public static boolean never(BlockState state, BlockView world, BlockPos pos){
        return false;
    }
}