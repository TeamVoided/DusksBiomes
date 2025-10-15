package org.teamvoided.dusks_biomes.data.gen

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider
import net.minecraft.advancements.Advancement
import net.minecraft.advancements.AdvancementHolder
import net.minecraft.advancements.AdvancementRewards
import net.minecraft.advancements.AdvancementType
import net.minecraft.core.HolderLookup
import net.minecraft.network.chat.Component
import net.minecraft.world.item.Items
import org.teamvoided.dusks_biomes.DusksBiomesMod.id
import org.teamvoided.dusks_biomes.DusksBiomesMod.mc
import org.teamvoided.dusks_biomes.init.DuskBiomes
import org.teamvoided.dusks_biomes.mixin.VanillaAdventureAdvancementsAccessor.db_invokeAddBiomes
import java.util.concurrent.CompletableFuture
import java.util.function.Consumer

class AdvancementsProvider(o: FabricDataOutput, r: CompletableFuture<HolderLookup.Provider>) :
    FabricAdvancementProvider(o, r) {
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
        DuskBiomes.DARK_GROVE,
        DuskBiomes.PALE_GROVE,
        DuskBiomes.SNOWY_CHERRY_GROVE,
//Keep these off because they might not stay, but keep them in because they look cool :) also it took forever to get their surface rules right and I don't want to remove that
//        DuskBiomes.FROZEN_BADLANDS,
//        DuskBiomes.FROZEN_WOODED_BADLANDS,
//        DuskBiomes.FROZEN_ERODED_BADLANDS,
        DuskBiomes.FROZEN_MANGROVE_SWAMP,
        DuskBiomes.WARM_RIVER,
        DuskBiomes.RED_DESERT,
        DuskBiomes.RED_WARM_RIVER,
        DuskBiomes.RED_WARM_OCEAN,
        DuskBiomes.RED_LUKEWARM_OCEAN,
        DuskBiomes.DEEP_RED_LUKEWARM_OCEAN,
        DuskBiomes.RED_BEACH,
        DuskBiomes.SNOWY_RED_BEACH,
        DuskBiomes.SNOWY_STONY_SHORE,
        DuskBiomes.MUSHROOM_GROVE,
        DuskBiomes.ERODED_MUSHROOM_ISLAND,
        DuskBiomes.MUSHROOM_CAVES,
        DuskBiomes.FROZEN_CAVERNS,
        DuskBiomes.SAND_CAVES,
        DuskBiomes.RED_SAND_CAVES,
        DuskBiomes.GRAVEL_CAVES
    )

    @Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
    private val adventuringTime = AdvancementHolder(mc("adventure/adventuring_time"), null)
    override fun generateAdvancement(provider: HolderLookup.Provider, c: Consumer<AdvancementHolder>) {
        db_invokeAddBiomes(Advancement.Builder.advancement(), provider, biomes)
            .display(
                Items.IRON_BOOTS,
                Component.literal("Strange Lands"),
                Component.literal("Visit all the biomes added by Dusks Biomes!"),
                null,
                AdvancementType.CHALLENGE,
                true,
                true,
                false
            )
            .rewards(AdvancementRewards.Builder.experience(500))
            .parent(adventuringTime)
            .save(c, id("adventure/strange_lands").toString())
    }
}
