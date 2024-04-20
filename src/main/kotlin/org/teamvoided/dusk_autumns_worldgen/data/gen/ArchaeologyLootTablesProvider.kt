package org.teamvoided.dusk_autumns_worldgen.data.gen

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableProvider
import net.minecraft.entity.effect.StatusEffects
import net.minecraft.item.Item
import net.minecraft.item.Items
import net.minecraft.loot.LootPool
import net.minecraft.loot.LootTable
import net.minecraft.loot.context.LootContextTypes.ARCHAEOLOGY
import net.minecraft.loot.entry.ItemEntry
import net.minecraft.loot.function.SetStewEffectLootFunction
import net.minecraft.loot.provider.number.ConstantLootNumberProvider
import net.minecraft.registry.HolderLookup
import net.minecraft.registry.RegistryKey
import org.teamvoided.dusk_autumns_worldgen.data.DuskLootTables
import org.teamvoided.dusk_autumns_worldgen.util.Utils.uniformNum
import java.util.concurrent.CompletableFuture
import java.util.function.BiConsumer

class ArchaeologyLootTablesProvider(o: FabricDataOutput, r: CompletableFuture<HolderLookup.Provider>) :
    SimpleFabricLootTableProvider(o, r, ARCHAEOLOGY) {

    //ArchaeologyLootTableGenerator <- Vanilla
    override fun generate(provider: HolderLookup.Provider, gen: BiConsumer<RegistryKey<LootTable>, LootTable.Builder>) {

        //desert_well_archaeology
        gen.accept(
            DuskLootTables.COOL_ARCHAEOLOGY, LootTable.builder().pool(
                LootPool.builder().rolls(ConstantLootNumberProvider.create(1.0f))
                    .with(item(Items.ARMS_UP_POTTERY_SHERD).weight(2))
                    .with(item(Items.BREWER_POTTERY_SHERD).weight(2))
                    .with(item(Items.BRICK))
                    .with(item(Items.EMERALD))
                    .with(item(Items.STICK))
                    .with(
                        item(Items.SUSPICIOUS_STEW)
                            .apply(
                                SetStewEffectLootFunction.builder()
                                    .withEffect(StatusEffects.NIGHT_VISION, uniformNum(7, 10))
                                    .withEffect(StatusEffects.JUMP_BOOST, uniformNum(7, 10))
                                    .withEffect(StatusEffects.WEAKNESS, uniformNum(6.0, 8))
                                    .withEffect(StatusEffects.BLINDNESS, uniformNum(5, 7))
                                    .withEffect(StatusEffects.POISON, uniformNum(10, 20))
                                    .withEffect(StatusEffects.SATURATION, uniformNum(7, 10))
                            )
                    )
            )
        )
    }

    companion object {
        private fun item(item: Item) = ItemEntry.builder(item)
    }
}