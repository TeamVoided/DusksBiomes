package org.teamvoided.dusks_biomes.data.gen

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableProvider
import net.minecraft.item.Item
import net.minecraft.item.Items
import net.minecraft.loot.LootPool
import net.minecraft.loot.LootTable
import net.minecraft.loot.context.LootContextTypes
import net.minecraft.loot.entry.ItemEntry
import net.minecraft.registry.HolderLookup
import net.minecraft.registry.RegistryKey
import org.teamvoided.dusks_biomes.data.DuskLootTables
import org.teamvoided.dusks_biomes.util.Utils.setCount
import org.teamvoided.dusks_biomes.util.Utils.uniformNum
import java.util.concurrent.CompletableFuture
import java.util.function.BiConsumer

@Suppress("MagicNumber")
class ChestLootTablesProvider(o: FabricDataOutput, r: CompletableFuture<HolderLookup.Provider>) :
    SimpleFabricLootTableProvider(o, r, LootContextTypes.CHEST) {
    override fun generate(provider: HolderLookup.Provider, gen: BiConsumer<RegistryKey<LootTable>, LootTable.Builder>) {

        val villageSwampHouseChest = LootPool.builder().rolls(uniformNum(3, 8))
            .with(item(Items.GOLD_NUGGET).apply(setCount(1, 3)))
            .with(item(Items.SUGAR_CANE).weight(3).apply(setCount(1, 3)))
            .with(item(Items.BLUE_ORCHID).weight(2).apply(setCount(1, 3)))
            .with(item(Items.LIGHT_BLUE_DYE).weight(3).apply(setCount(2, 5)))
            .with(item(Items.CLAY_BALL).weight(4).apply(setCount(2, 4)))
            .with(item(Items.CLAY))
            .with(item(Items.BREAD).weight(10).apply(setCount(1, 4)))
            .with(item(Items.LILY_PAD).weight(6).apply(setCount(1, 2)))
            .with(item(Items.MOSS_BLOCK))
            .with(item(Items.BOOK))
            .with(item(Items.EMERALD).weight(2).apply(setCount(1, 4)))

        gen.accept(
            DuskLootTables.VILLAGE_SWAMP_HOUSE_CHEST,
            LootTable.builder().pool(
                villageSwampHouseChest
                    .with(item(Items.OAK_CHEST_BOAT))
                    .with(item(Items.OAK_SAPLING).weight(4).apply(setCount(1, 2)))
                    .with(item(Items.DARK_OAK_SAPLING).weight(1).apply(setCount(1, 4)))
            )
        )
        gen.accept(
            DuskLootTables.VILLAGE_MANGROVE_SWAMP_HOUSE_CHEST,
            LootTable.builder().pool(
                villageSwampHouseChest
                    .with(item(Items.MANGROVE_CHEST_BOAT))
                    .with(item(Items.MANGROVE_PROPAGULE).weight(5).apply(setCount(1, 2)))
            )
        )


        /* // eStrongholdLibraryLootTable
        gen.accept(
            DuskLootTables.COOL_CHEST,
            LootTable.builder().pool(
                LootPool.builder().rolls(uniformNum(2, 10))
                    .with(
                        ItemEntry.builder(Items.BOOK).weight(20)
                            .apply(setCount(1, 3))
                    )
                    .with(
                        ItemEntry.builder(Items.PAPER).weight(20)
                            .apply(setCount(2, 7))
                    )
                    .with(ItemEntry.builder(Items.MAP))
                    .with(ItemEntry.builder(Items.COMPASS))
                    .with(
                        ItemEntry.builder(Items.BOOK).weight(10)
                            .apply(
                                EnchantWithLevelsLootFunction.builder(ConstantLootNumberProvider.create(30.0f))
                                    .allowTreasureEnchantments()
                            )
                    )
            ).pool(
                LootPool.builder().rolls(ConstantLootNumberProvider.create(1.0f))
                    .with(ItemEntry.builder(Items.EYE_ARMOR_TRIM_SMITHING_TEMPLATE).weight(1))
            )
        )
         */

    }

    companion object {
        private fun item(item: Item) = ItemEntry.builder(item)
    }
}
