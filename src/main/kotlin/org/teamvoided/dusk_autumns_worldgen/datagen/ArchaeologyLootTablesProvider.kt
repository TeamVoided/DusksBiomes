package org.teamvoided.dusk_autumns_worldgen.datagen

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableProvider
import net.minecraft.entity.effect.StatusEffects
import net.minecraft.item.Items
import net.minecraft.loot.LootPool
import net.minecraft.loot.LootTable
import net.minecraft.loot.context.LootContextTypes
import net.minecraft.loot.entry.ItemEntry
import net.minecraft.loot.function.SetStewEffectLootFunction
import net.minecraft.loot.provider.number.ConstantLootNumberProvider
import net.minecraft.util.Identifier
import org.teamvoided.dusk_autumns_worldgen.data.DuskLootTables
import org.teamvoided.dusk_autumns_worldgen.util.Utils.uniformNum
import java.util.function.BiConsumer

class ArchaeologyLootTablesProvider(output: FabricDataOutput) :
    SimpleFabricLootTableProvider(output, LootContextTypes.ARCHAEOLOGY) {
    override fun generate(gen: BiConsumer<Identifier, LootTable.Builder>) {

        gen.accept( //desert_well_archaeology
            DuskLootTables.COOL_ARCHAEOLOGY, LootTable.builder().pool(
                LootPool.builder().rolls(ConstantLootNumberProvider.create(1.0f))
                    .with(ItemEntry.builder(Items.ARMS_UP_POTTERY_SHERD).weight(2))
                    .with(ItemEntry.builder(Items.BREWER_POTTERY_SHERD).weight(2))
                    .with(ItemEntry.builder(Items.BRICK))
                    .with(ItemEntry.builder(Items.EMERALD))
                    .with(ItemEntry.builder(Items.STICK))
                    .with(
                        ItemEntry.builder(Items.SUSPICIOUS_STEW)
                            .apply(
                                SetStewEffectLootFunction.builder()
                                    .withEffect(StatusEffects.NIGHT_VISION, uniformNum(7, 10))
                                    .withEffect(StatusEffects.JUMP_BOOST, uniformNum(7, 10))
                                    .withEffect(StatusEffects.WEAKNESS, uniformNum(6.0, 8))
                                    .withEffect(StatusEffects.BLINDNESS, uniformNum(5, 7))
                                    .withEffect(StatusEffects.POISON, uniformNum(10, 20))
                                    .withEffect(StatusEffects.SATURATION, uniformNum(7.0, 10.0)) //can use 0.5 if needed
                            )
                    )
            )
        )
    }
}