package org.teamvoided.dusks_biomes.datagen.data

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableProvider
import net.minecraft.core.HolderLookup
import net.minecraft.resources.ResourceKey
import net.minecraft.world.item.Item
import net.minecraft.world.item.Items
import net.minecraft.world.level.storage.loot.LootPool
import net.minecraft.world.level.storage.loot.LootTable
import net.minecraft.world.level.storage.loot.entries.LootItem
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets
import org.teamvoided.dusks_biomes.data.DuskLootTables
import org.teamvoided.dusks_biomes.util.Utils.setCount
import org.teamvoided.dusks_biomes.util.Utils.uniformNum
import java.util.concurrent.CompletableFuture
import java.util.function.BiConsumer

class ChestLootTablesProvider(o: FabricDataOutput, r: CompletableFuture<HolderLookup.Provider>) :
    SimpleFabricLootTableProvider(o, r, LootContextParamSets.CHEST) {
    override fun generate(gen: BiConsumer<ResourceKey<LootTable>, LootTable.Builder>) {

        val villageSwampHouseChest: LootPool.Builder = LootPool.lootPool().setRolls(uniformNum(3, 8))
            .add(item(Items.GOLD_NUGGET).apply(setCount(1, 3)))
            .add(item(Items.SUGAR_CANE).setWeight(3).apply(setCount(1, 3)))
            .add(item(Items.BLUE_ORCHID).setWeight(2).apply(setCount(1, 3)))
            .add(item(Items.LIGHT_BLUE_DYE).setWeight(3).apply(setCount(2, 5)))
            .add(item(Items.CLAY_BALL).setWeight(4).apply(setCount(2, 4)))
            .add(item(Items.CLAY))
            .add(item(Items.BREAD).setWeight(10).apply(setCount(1, 4)))
            .add(item(Items.LILY_PAD).setWeight(6).apply(setCount(1, 2)))
            .add(item(Items.MOSS_BLOCK))
            .add(item(Items.BOOK))
            .add(item(Items.EMERALD).setWeight(2).apply(setCount(1, 4)))

        gen.accept(
            DuskLootTables.VILLAGE_SWAMP_HOUSE_CHEST,
            LootTable.lootTable().withPool(
                villageSwampHouseChest
                    .add(item(Items.OAK_CHEST_BOAT))
                    .add(item(Items.OAK_SAPLING).setWeight(4).apply(setCount(1, 2)))
                    .add(item(Items.DARK_OAK_SAPLING).setWeight(1).apply(setCount(1, 4)))
            )
        )
        gen.accept(
            DuskLootTables.VILLAGE_MANGROVE_SWAMP_HOUSE_CHEST,
            LootTable.lootTable().withPool(
                villageSwampHouseChest
                    .add(item(Items.MANGROVE_CHEST_BOAT))
                    .add(item(Items.MANGROVE_PROPAGULE).setWeight(5).apply(setCount(1, 2)))
            )
        )
    }

    companion object {
        private fun item(item: Item) = LootItem.lootTableItem(item)
    }
}
