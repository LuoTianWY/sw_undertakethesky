package net.Luo_Tian_WY.sw_undertakethesky.sapling;

//import net.Luo_Tian_WY.sw_undertakethesky.worldgen.SWUSConfiguredFeatures;
import net.Luo_Tian_WY.sw_undertakethesky.commands.StatementsAndRegisters;
import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.feature.ConfiguredFeature;

import javax.annotation.Nullable;

public class PrunusPersicaSaplingGenerator extends SaplingGenerator {
    public PrunusPersicaSaplingGenerator(){
    }

    @Nullable
    @Override
    protected RegistryEntry<? extends ConfiguredFeature<?, ?>> getTreeFeature(Random random, boolean bees) {
        return StatementsAndRegisters.PRUNUS_PERSICA_TREE;
    }
}
