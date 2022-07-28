package net.Luo_Tian_WY.sw_undertakethesky.commands;

import net.Luo_Tian_WY.sw_undertakethesky.blocks.BlockSettingMethod;
import net.Luo_Tian_WY.sw_undertakethesky.blocks.FloweringBlock;
import net.Luo_Tian_WY.sw_undertakethesky.blocks.ModSaplingBlock;
import net.Luo_Tian_WY.sw_undertakethesky.sapling.PrunusPersicaSaplingGenerator;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.block.*;
import net.minecraft.client.color.world.BiomeColors;
import net.minecraft.item.BlockItem;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DataPool;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.AcaciaFoliagePlacer;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.stateprovider.WeightedBlockStateProvider;
import net.minecraft.world.gen.trunk.ForkingTrunkPlacer;

import java.util.List;

public class StatementsAndRegisters {
    /**
    注册桃木原木
     */
    public static final PillarBlock PRUNUS_PERSICA_LOG = (PillarBlock) registerBlockAndBlockItem(
            "prunus_persica_log",
            new PillarBlock(
            FabricBlockSettings.
                    of(Material.WOOD).
                    strength(2.0f).
                    mapColor(MapColor.GRAY).
                    sounds(BlockSoundGroup.WOOD)
            )
    );
    /**
     * 注册桃木木板
     */
    public static final Block PRUNUS_PERSICA_PLANKS = registerBlockAndBlockItem(
            "prunus_persica_planks",
            new Block(
                    FabricBlockSettings
                            .of(Material.WOOD)
                            .strength(2.0f)
                            .mapColor(MapColor.GRAY)
                            .sounds(BlockSoundGroup.WOOD)
            )
    );
    /**
     * 注册桃木台阶
     */
    public static final SlabBlock PRUNUS_PERSICA_SLAB = (SlabBlock) registerBlockAndBlockItem(
            "prunus_persica_slab",
            new SlabBlock(
                    AbstractBlock.Settings.of(Material.WOOD)
                            .strength(2.0F, 3.0F)
                            .sounds(BlockSoundGroup.WOOD)
            )
    );
    /**
     * 注册桃木楼梯
     */
    public static final StairsBlock PRUNUS_PERSICA_STAIRS = (StairsBlock) registerBlockAndBlockItem(
            "prunus_persica_stairs",
            new StairsBlock(
                    PRUNUS_PERSICA_PLANKS.getDefaultState(),
                    FabricBlockSettings.copy(PRUNUS_PERSICA_PLANKS)
            )
    );
    /**
     * 注册桃木栅栏
     */
    public static final FenceBlock PRUNUS_PERSICA_FENCE = (FenceBlock) registerBlockAndBlockItem(
            "prunus_persica_fence",
            new FenceBlock(
                    AbstractBlock.Settings.of(
                            Material.WOOD)
                            .strength(2.0F, 3.0F)
                            .sounds(BlockSoundGroup.WOOD)
            )
    );
    /**
     * 注册桃木栅栏门
     */
    public static final FenceGateBlock PRUNUS_PERSICA_FENCE_GATE = (FenceGateBlock) registerBlockAndBlockItem(
            "prunus_persica_fence_gate",
            new FenceGateBlock(
                    AbstractBlock.Settings.of(
                            Material.WOOD)
                            .strength(2.0F, 3.0F)
                            .sounds(BlockSoundGroup.WOOD)
            )
    );
    /**
     * 注册桃木活板门
     */
    public static final TrapdoorBlock PRUNUS_PERSICA_TRAPDOOR = (TrapdoorBlock) registerBlockAndBlockItem(
            "prunus_persica_trapdoor",
            new TrapdoorBlock(
                    AbstractBlock.Settings.of(Material.WOOD)
                            .strength(3.0F)
                            .sounds(BlockSoundGroup.WOOD)
                            .nonOpaque()
                            .allowsSpawning(BlockSettingMethod::never)
            )
    );
    /**
     * 注册桃木门
     */
    public static final DoorBlock PRUNUS_PERSICA_DOOR = (DoorBlock) registerBlockAndBlockItem(
            "prunus_persica_door",
            new DoorBlock(
                    FabricBlockSettings.of(Material.WOOD)
                            .strength(3.0F)
                            .sounds(BlockSoundGroup.WOOD)
                            .nonOpaque()
            )
    );
    /**
     * 注册桃树树叶
     */
    public static final LeavesBlock PRUNUS_PERSICA_LEAVES = (LeavesBlock) registerBlockAndBlockItem(
            "prunus_persica_leaves",
            new LeavesBlock(
                    AbstractBlock.
                            Settings.
                            of(Material.LEAVES).
                            strength(0.2f).
                            ticksRandomly().
                            sounds(BlockSoundGroup.GRASS).
                            mapColor(MapColor.GREEN).
                            allowsSpawning(BlockSettingMethod::LeavesSpawning).
                            suffocates(BlockSettingMethod::never).
                            blockVision(BlockSettingMethod::never).
                            nonOpaque()
            )
    );
    /**
     * 注册开花的桃树树叶
     */
    public static final FloweringBlock FLOWERING_PRUNUS_PERSICA_LEAVES = (FloweringBlock) registerBlockAndBlockItem(
            "flowering_prunus_persica_leaves",
            new FloweringBlock(
                    AbstractBlock.Settings.of(Material.LEAVES).
                            strength(0.2f).
                            ticksRandomly().
                            sounds(BlockSoundGroup.GRASS).
                            mapColor(MapColor.GREEN).
                            allowsSpawning(BlockSettingMethod::LeavesSpawning).
                            suffocates(BlockSettingMethod::never).
                            blockVision(BlockSettingMethod::never).
                            nonOpaque()
            )
    );
    /**
     * 注册桃树树苗
     */
    public static final ModSaplingBlock PRUNUS_PERSICA_SAPLING = (ModSaplingBlock) registerBlockAndBlockItem(
            "prunus_persica_sapling",
            new ModSaplingBlock(
                    new PrunusPersicaSaplingGenerator(),
                    FabricBlockSettings
                            .copy(Blocks.OAK_SAPLING)
            )
    );
    /**
     * 注册桃子
     */
    public static final Item PEACH = registerItem(
            "peach",
            new Item(
                    new FabricItemSettings().food(
                            new FoodComponent.Builder()
                                    .hunger(4)
                                    .alwaysEdible()
                                    .build()
                    )
                            .group(ItemGroup.SW_GROUP)
            )
    );
    /**
     * 注册桃花
     */
    public static final Item PEACH_BLOSSOM = registerItem(
            "peach_blossom",
            new Item(
                    new FabricItemSettings()
                            .group(ItemGroup.SW_GROUP)
            )
    );

    /**
     * 注册草木灰
     */
    public static final Item PLANT_ASH = registerItem(
            "plant_ash",
            new Item(
                    new FabricItemSettings()
                            .group(ItemGroup.SW_GROUP)
            )
    );

    /**
     * 仅注册方块方法
     * @param name
     * @param block
     * @return
     */
    private static Block registerBlockOnly(String name, Block block){
        return Registry.register(Registry.BLOCK, new Identifier("sw_undertakethesky", name), block);
    };

    /**
     * 同时注册方块及对应的方块物品方法
     * @param name
     * @param block
     * @return
     */
    private static Block registerBlockAndBlockItem(String name, Block block){
        Registry.register(
                Registry.ITEM,
                new Identifier("sw_undertakethesky", name),
                new BlockItem(
                        block,
                        new Item.Settings().group(ItemGroup.SW_GROUP)));

        return Registry.register(Registry.BLOCK, new Identifier("sw_undertakethesky", name), block);
    };

    /**
     * 注册物品方法
     * @param name
     * @param item
     * @return
     */
    private static Item registerItem(String name, Item item){
        return Registry.register(Registry.ITEM, new Identifier("sw_undertakethesky", name), item);
    };

    /**
     * 初始化方法
     */
    public StatementsAndRegisters(){
        /**
         * 注册燃料
         */
        FuelRegistry.INSTANCE.add(PRUNUS_PERSICA_LOG.asItem(), 300);
        FuelRegistry.INSTANCE.add(PRUNUS_PERSICA_SAPLING.asItem(), 100);
        FuelRegistry.INSTANCE.add(PEACH_BLOSSOM.asItem(), 100);
        FuelRegistry.INSTANCE.add(PRUNUS_PERSICA_PLANKS.asItem(), 300);
        FuelRegistry.INSTANCE.add(PRUNUS_PERSICA_SLAB.asItem(), 300);
        FuelRegistry.INSTANCE.add(PRUNUS_PERSICA_STAIRS.asItem(), 300);
        FuelRegistry.INSTANCE.add(PRUNUS_PERSICA_FENCE.asItem(), 300);
        FuelRegistry.INSTANCE.add(PRUNUS_PERSICA_FENCE_GATE.asItem(), 300);
        FuelRegistry.INSTANCE.add(PRUNUS_PERSICA_TRAPDOOR.asItem(), 300);
        FuelRegistry.INSTANCE.add(PRUNUS_PERSICA_DOOR.asItem(), 200);

        //以下进行 ColorProvider 注册，用以实现 ColorMap 渲染
        //注册桃树树叶
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

    /**
    * 桃树生成
    * 进行 ConfiguredFeatures 配置，用于确定单株植株生成配置
    * 变量定义放置在初始化方法外用以方便 PrunusPersicaSaplingGenerator 调用

    * 调用 ForkingTrunkPlacer 用以生成分叉的树干，其中 i 为树干基础高度，当 j, k 均为 0 时，则保证树干最高一定为 i 格
    * j, k 赋予树干在基础高度之外的两个随机偏移量，以控制生成随机高度的树木
     *
     * 调用 WeightedBlockStateProvider 以实现从数据池中按权抽取方块进行生成
     * 此处参考原版中杜鹃树实现形式，按照分配的权重随机生成普通桃树树叶与开花的桃树树叶

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
                    BlockStateProvider.of(PRUNUS_PERSICA_LOG),
                    new ForkingTrunkPlacer(5, 1, 0),
                    new WeightedBlockStateProvider(
                            DataPool.<BlockState>builder()
                                    .add(PRUNUS_PERSICA_LEAVES.getDefaultState(), 10)
                                    .add(FLOWERING_PRUNUS_PERSICA_LEAVES.getDefaultState(), 1)
                            .build()
                    ),
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
            PlacedFeatures.wouldSurvive(PRUNUS_PERSICA_SAPLING)
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
}
