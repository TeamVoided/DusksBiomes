package org.teamvoided.dusk_autumns_worldgen.datagen

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableProvider
import net.minecraft.item.Items
import net.minecraft.loot.LootPool
import net.minecraft.loot.LootTable
import net.minecraft.loot.context.LootContextTypes
import net.minecraft.loot.entry.ItemEntry
import net.minecraft.loot.function.EnchantWithLevelsLootFunction
import net.minecraft.loot.function.SetCountLootFunction
import net.minecraft.loot.provider.number.ConstantLootNumberProvider
import net.minecraft.loot.provider.number.UniformLootNumberProvider
import net.minecraft.util.Identifier
import org.teamvoided.dusk_autumns_worldgen.data.DuskLootTables
import org.teamvoided.dusk_autumns_worldgen.util.Utils.uniformNum
import java.util.function.BiConsumer

class ChestLootTablesProvider(output: FabricDataOutput) :
    SimpleFabricLootTableProvider(output, LootContextTypes.CHEST) {
    override fun generate(gen: BiConsumer<Identifier, LootTable.Builder>) {
        gen.accept(
            DuskLootTables.VILLAGE_SWAMP_HOUSE_CHEST,
            LootTable.builder().pool(
                LootPool.builder().rolls(uniformNum(3, 8))
                    .with(
                        ItemEntry.builder(Items.GOLD_NUGGET)
                            .apply(SetCountLootFunction.builder(uniformNum(1, 3)))
                    )
                    .with(
                        ItemEntry.builder(Items.SUGAR_CANE).weight(3)
                            .apply(SetCountLootFunction.builder(uniformNum(1, 3)))
                    )
                    .with(
                        ItemEntry.builder(Items.BLUE_ORCHID).weight(2)
                            .apply(SetCountLootFunction.builder(uniformNum(1, 3)))
                    )
                    .with(
                        ItemEntry.builder(Items.LIGHT_BLUE_DYE).weight(3)
                            .apply(SetCountLootFunction.builder(uniformNum(2, 5)))
                    )
                    .with(
                        ItemEntry.builder(Items.CLAY_BALL).weight(4)
                            .apply(SetCountLootFunction.builder(uniformNum(2, 4)))
                    )
                    .with(ItemEntry.builder(Items.CLAY))
                    .with(
                        ItemEntry.builder(Items.BREAD).weight(10)
                            .apply(SetCountLootFunction.builder(uniformNum(1, 4)))
                    )
                    .with(
                        ItemEntry.builder(Items.LILY_PAD).weight(6)
                            .apply(SetCountLootFunction.builder(uniformNum(1, 2)))
                    )
                    .with(ItemEntry.builder(Items.MOSS_BLOCK))
                    .with(ItemEntry.builder(Items.BOOK))
                    .with(
                        ItemEntry.builder(Items.EMERALD).weight(2)
                            .apply(SetCountLootFunction.builder(uniformNum(1, 4)))
                    )
                    .with(ItemEntry.builder(Items.OAK_CHEST_BOAT))
                    .with(
                        ItemEntry.builder(Items.OAK_SAPLING).weight(4)
                            .apply(SetCountLootFunction.builder(uniformNum(1, 2)))
                    )
                    .with(
                        ItemEntry.builder(Items.DARK_OAK_SAPLING).weight(1)
                            .apply(SetCountLootFunction.builder(uniformNum(1, 4)))
                    )
            )
        )
        gen.accept(
            DuskLootTables.VILLAGE_MANGROVE_SWAMP_HOUSE_CHEST,
            LootTable.builder().pool(
                LootPool.builder().rolls(uniformNum(3, 8))
                    .with(
                        ItemEntry.builder(Items.GOLD_NUGGET)
                            .apply(SetCountLootFunction.builder(uniformNum(1, 3)))
                    )
                    .with(
                        ItemEntry.builder(Items.SUGAR_CANE).weight(3)
                            .apply(SetCountLootFunction.builder(uniformNum(1, 3)))
                    )
                    .with(
                        ItemEntry.builder(Items.BLUE_ORCHID).weight(2)
                            .apply(SetCountLootFunction.builder(uniformNum(1, 3)))
                    )
                    .with(
                        ItemEntry.builder(Items.LIGHT_BLUE_DYE).weight(3)
                            .apply(SetCountLootFunction.builder(uniformNum(2, 5)))
                    )
                    .with(
                        ItemEntry.builder(Items.CLAY_BALL).weight(4)
                            .apply(SetCountLootFunction.builder(uniformNum(2, 4)))
                    )
                    .with(ItemEntry.builder(Items.CLAY))
                    .with(
                        ItemEntry.builder(Items.BREAD).weight(10)
                            .apply(SetCountLootFunction.builder(uniformNum(1, 4)))
                    )
                    .with(
                        ItemEntry.builder(Items.LILY_PAD).weight(6)
                            .apply(SetCountLootFunction.builder(uniformNum(1, 2)))
                    )
                    .with(ItemEntry.builder(Items.MOSS_BLOCK))
                    .with(ItemEntry.builder(Items.BOOK))
                    .with(
                        ItemEntry.builder(Items.EMERALD).weight(2)
                            .apply(SetCountLootFunction.builder(uniformNum(1, 4)))
                    )
                    .with(ItemEntry.builder(Items.MANGROVE_CHEST_BOAT))
                    .with(
                        ItemEntry.builder(Items.MANGROVE_PROPAGULE).weight(5)
                            .apply(SetCountLootFunction.builder(uniformNum(1, 2)))
                    )
            )
        )
        gen.accept( // eStrongholdLibraryLootTable
            DuskLootTables.COOL_CHEST,
            LootTable.builder().pool(
                LootPool.builder().rolls(uniformNum(2, 10))
                    .with(
                        ItemEntry.builder(Items.BOOK).weight(20)
                            .apply(SetCountLootFunction.builder(uniformNum(1, 3)))
                    )
                    .with(
                        ItemEntry.builder(Items.PAPER).weight(20)
                            .apply(SetCountLootFunction.builder(uniformNum(2, 7)))
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

    }

}