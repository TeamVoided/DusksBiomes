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
import java.util.function.BiConsumer

class ChestLootTablesProvider(output: FabricDataOutput) :
    SimpleFabricLootTableProvider(output, LootContextTypes.CHEST) {
    override fun generate(gen: BiConsumer<Identifier, LootTable.Builder>) {

        gen.accept( // eStrongholdLibraryLootTable
            DuskLootTables.COOL_CHEST,
            LootTable.builder().pool(
                LootPool.builder().rolls(UniformLootNumberProvider.create(2.0f, 10.0f))
                    .with(
                        ItemEntry.builder(Items.BOOK).weight(20)
                            .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 3.0f)))
                    )
                    .with(
                        ItemEntry.builder(Items.PAPER).weight(20)
                            .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0f, 7.0f)))
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