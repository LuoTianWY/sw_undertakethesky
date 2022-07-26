package net.Luo_Tian_WY.sw_undertakethesky.commands;

import net.Luo_Tian_WY.sw_undertakethesky.blocks.PrunusPersica;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.client.color.world.BiomeColors;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.AcaciaFoliagePlacer;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.trunk.ForkingTrunkPlacer;

import java.util.List;

import static net.Luo_Tian_WY.sw_undertakethesky.blocks.PrunusPersica.*;

public class Registers{
    /**
    * 桃树生成
    * 进行 ConfiguredFeatures 配置，用于确定单株植株生成配置
    * 变量定义放置在初始化方法外用以方便 PrunusPersicaSaplingGenerator 调用

    * 调用 ForkingTrunkPlacer 用以生成分叉的树干，其中 i 为树干基础高度，当 j, k 均为 0 时，则保证树干最高一定为 i 格
    * j, k 赋予树干在基础高度之外的两个随机偏移量，以控制生成随机高度的树木

    * 调用 AcaciaFoliagePlacer 进行树叶层生成，因为桃树与金合欢树有相似的生成需求
    * 注意：调用 AcaciaFoliagePlacer 与 BlobFoliagePlacer 不同点明显出现在：在单个树叶集群中，不同层数的树叶会出现不一样的生成半径
    * 例：该树的树叶集群共高 2 格，Acacia 调用后，顶层要比底层窄，而 Blob 调用后，顶层与底层一样宽
    * IntProvider 将 Int 型整数转换为 IntProvider 型传入方法中。首个参数为树叶层生成基础半径，第二个参数为基础半径之上的随机偏移量，以控制生成随机宽度的树叶层

    * 调用 TwoLayersFeatureSize。推测该方法用于不同高度树叶集群的宽度控制（待试验）
    */
    public static RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> PRUNUS_PERSICA_TREE = ConfiguredFeatures.register(
            "prunus_persica_tree",
            Feature.TREE,
            (new TreeFeatureConfig.Builder(
                    BlockStateProvider.of(PrunusPersica.PRUNUS_PERSICA_LOG),
                    new ForkingTrunkPlacer(5, 1, 0),
                    BlockStateProvider.of(PrunusPersica.PRUNUS_PERSICA_LEAVES),
                    new AcaciaFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0)),
                    new TwoLayersFeatureSize(2, 0, 1)))
                    .ignoreVines()
                    .build()
    );
    /**
     * 进行 PlacedFeature 配置
     * wouldSurvive 方法寻找可以保证树苗存活的方块位置，检查当前位置是否合法
     * RandomFeatureConfig 执行生成（具体功能待测试）
     * 最终由 Placed 实现在世界中的放置，VegetationPlacedFeatures 修饰方法保证每次生成树木棵数为 1，并且具有 0.1 的几率生成附加 0 棵树
     */
    RegistryEntry<PlacedFeature> PRUNUS_PERSICA_CHECKED = PlacedFeatures.register(
            "prunus_persica_checked",
            PRUNUS_PERSICA_TREE,
            PlacedFeatures.wouldSurvive(PrunusPersica.PRUNUS_PERSICA_SAPLING)
    );
    RegistryEntry<ConfiguredFeature<RandomFeatureConfig, ?>> PRUNUS_PERSICA_SPAWN = ConfiguredFeatures.register(
            "prunus_persica_spawn",
            Feature.RANDOM_SELECTOR,
            new RandomFeatureConfig(
                    List.of(
                            new RandomFeatureEntry(PRUNUS_PERSICA_CHECKED, 1.0f)
                    ),
                    PRUNUS_PERSICA_CHECKED
            )
    );
    RegistryEntry<PlacedFeature> PRUNUS_PERSICA_PLACED = PlacedFeatures.register(
            "prunus_persica_placed",
            PRUNUS_PERSICA_SPAWN,
            VegetationPlacedFeatures.modifiers(
                    PlacedFeatures.createCountExtraModifier(1, 0.1f, 0)
            )
    );

    /**
     * 初始化方法，以下注册物品
     */
    public Registers(){
        Registry.register(
                Registry.BLOCK,
                new Identifier("sw_undertakethesky", "prunus_persica_log"),
                PRUNUS_PERSICA_LOG
        );//注册桃木原木
        Registry.register(
                Registry.ITEM,
                new Identifier("sw_undertakethesky", "prunus_persica_log"),
                new BlockItem(
                        PRUNUS_PERSICA_LOG,
                        new Item.Settings().group(ItemGroup.SW_GROUP)
                )
        );//注册桃木原木的物品形式

        Registry.register(
                Registry.BLOCK,
                new Identifier("sw_undertakethesky", "prunus_persica_leaves"),
                PRUNUS_PERSICA_LEAVES
        );//注册桃树树叶
        Registry.register(
                Registry.ITEM,
                new Identifier("sw_undertakethesky", "prunus_persica_leaves"),
                new BlockItem(
                        PRUNUS_PERSICA_LEAVES,
                        new Item.Settings().group(ItemGroup.SW_GROUP)
                )
        );//注册桃树树叶的物品形式

        Registry.register(
                Registry.BLOCK,
                new Identifier("sw_undertakethesky", "prunus_persica_sapling"),
                PRUNUS_PERSICA_SAPLING
        );//注册桃树树苗
        Registry.register(
                Registry.ITEM,
                new Identifier("sw_undertakethesky", "prunus_persica_sapling"),
                new BlockItem(
                        PRUNUS_PERSICA_SAPLING,
                        new Item.Settings().group(ItemGroup.SW_GROUP)
                )
        );//注册桃树树苗的物品形式

        //以下进行燃料注册
        FuelRegistry.INSTANCE.add(PRUNUS_PERSICA_LOG.asItem(), 300);

        //以下进行 ColorProvider 注册，用以实现 ColorMap 渲染
        ColorProviderRegistry.ITEM.register((((stack, tintIndex) -> 0x48b518)), PRUNUS_PERSICA_LEAVES);//注册物品栏中桃树树叶的颜色，硬编码为与橡树树叶相同
        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> {
            assert world != null;//断言，world 不能为 null，否则可能出现意料之外的错误
            return BiomeColors.getFoliageColor(world, pos);//获取当前 Biome 在 Foliage 色度图上的值并返回
        }, PRUNUS_PERSICA_LEAVES);//注册作为方块摆放时的桃树树叶颜色，调用 FoliageColor（树叶用） 根据 Biome 渲染

        //以下设置可以生成桃树的生物群系
        BiomeModifications.addFeature(
                BiomeSelectors.foundInOverworld(),//主世界所有可以生成树的生物群系都可以生成桃树
                GenerationStep.Feature.VEGETAL_DECORATION,//标识符，地表装饰地物，包含树木
                PRUNUS_PERSICA_PLACED.getKey().get()
        );
    }
}
