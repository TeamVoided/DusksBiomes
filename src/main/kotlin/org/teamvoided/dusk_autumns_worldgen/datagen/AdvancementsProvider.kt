package org.teamvoided.dusk_autumns_worldgen.datagen

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider
import net.minecraft.advancement.Advancement
import net.minecraft.advancement.AdvancementHolder
import net.minecraft.advancement.AdvancementRewards
import net.minecraft.data.server.advancement.AdventureAdvancementTabGenerator
import net.minecraft.item.Items
import net.minecraft.text.Text
import net.minecraft.text.component.AdvancementComponent
import net.minecraft.util.Identifier
import org.teamvoided.dusk_autumns_worldgen.DuskAutumnsWorldgen.id
import org.teamvoided.dusk_autumns_worldgen.init.worldgen.DuskBiomes
import java.util.function.Consumer

class AdvancementsProvider(o: FabricDataOutput) : FabricAdvancementProvider(o) {
    val biomes = listOf(
        DuskBiomes.COLD_FOREST,
        DuskBiomes.COLD_PLAINS,
        DuskBiomes.WARM_FOREST,
        DuskBiomes.WARM_PLAINS,
        DuskBiomes.WINDSWEPT_BIRCH_FOREST,
        DuskBiomes.SNOWY_WINDSWEPT_HILLS,
        DuskBiomes.SNOWY_WINDSWEPT_GRAVELLY_HILLS,
        DuskBiomes.SNOWY_WINDSWEPT_FOREST,
        DuskBiomes.SNOWY_OLD_GROWTH_PINE_TAIGA,
        DuskBiomes.SNOWY_OLD_GROWTH_SPRUCE_TAIGA,
        DuskBiomes.SNOWY_CHERRY_GROVE,
        DuskBiomes.FROZEN_MANGROVE_SWAMP,
        DuskBiomes.FROZEN_WINDSWEPT_MANGROVE_SWAMP,
        DuskBiomes.WINDSWEPT_MANGROVE_SWAMP,
        DuskBiomes.OLD_GROWTH_SWAMP,
        DuskBiomes.WARM_RIVER,
        DuskBiomes.RED_WARM_RIVER,
        DuskBiomes.RED_WARM_OCEAN,
        DuskBiomes.RED_LUKEWARM_OCEAN,
        DuskBiomes.DEEP_RED_LUKEWARM_OCEAN,
        DuskBiomes.RED_BEACH,
        DuskBiomes.SNOWY_RED_BEACH,
        DuskBiomes.SNOWY_STONY_SHORE,
        DuskBiomes.RED_DESERT,
        DuskBiomes.MUSHROOM_GROVE,
        DuskBiomes.ERODED_MUSHROOM_ISLAND,
        DuskBiomes.MUSHROOM_CAVES,
        DuskBiomes.FROZEN_CAVERNS,
        DuskBiomes.SAND_CAVES,
        DuskBiomes.RED_SAND_CAVES
    )
    private val adventuringTime = AdvancementHolder(Identifier("adventure/adventuring_time"), null)
    override fun generateAdvancement(c: Consumer<AdvancementHolder>) {

        AdventureAdvancementTabGenerator.appendEnterAllBiomesCriterion(Advancement.Builder.create(), biomes)
            .method_697(
                Items.IRON_BOOTS,
                Text.of("Strange Lands"),
                Text.of("Visit all the biomes added by Dusk Autumns Worldgen!"),
                null,
                AdvancementComponent.CHALLENGE,
                true,
                true,
                false
            )
            .rewards(AdvancementRewards.Builder.experience(500))
            .parent(adventuringTime)
            .build(c, id("adventure/strange_lands").toString())
    }

}