package org.teamvoided.dusks_biomes.world.level.levelgen.config

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.core.HolderSet
import net.minecraft.core.RegistryCodecs
import net.minecraft.core.registries.Registries
import net.minecraft.tags.BlockTags
import net.minecraft.util.valueproviders.FloatProvider
import net.minecraft.util.valueproviders.IntProvider
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider

data class DirectionalBlockPileFeatureConfig(
    val radius: IntProvider,
    val height: IntProvider,
    val blockInRadiusChance: FloatProvider,
    val blockChance: FloatProvider,
    val blockstate: BlockStateProvider,
    val canReplace: HolderSet<Block>,
) : FeatureConfiguration {

    companion object {
        val CODEC: Codec<DirectionalBlockPileFeatureConfig> = RecordCodecBuilder.create { instance ->
            instance.group(
                IntProvider.codec(1, 15).fieldOf("radius").forGetter { it.radius },
                IntProvider.codec(1, 15).fieldOf("height").forGetter { it.height },
                FloatProvider.codec(-1f, 1f).fieldOf("block_in_radius_chance").forGetter { it.blockInRadiusChance },
                FloatProvider.codec(0f, 1f).fieldOf("block_chance").forGetter { it.blockChance },
                BlockStateProvider.CODEC.fieldOf("blockstate").forGetter { it.blockstate },
                RegistryCodecs.homogeneousList(Registries.BLOCK).fieldOf("can_replace")
                    .forGetter { it.canReplace },
            ).apply(instance, ::DirectionalBlockPileFeatureConfig)
        }
    }
}