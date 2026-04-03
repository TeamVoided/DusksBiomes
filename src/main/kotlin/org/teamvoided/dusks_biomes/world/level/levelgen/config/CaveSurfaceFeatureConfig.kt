package org.teamvoided.dusks_biomes.world.level.levelgen.config

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.core.HolderSet
import net.minecraft.core.RegistryCodecs
import net.minecraft.core.registries.Registries
import net.minecraft.tags.BlockTags
import net.minecraft.util.valueproviders.ConstantFloat
import net.minecraft.util.valueproviders.ConstantInt
import net.minecraft.util.valueproviders.FloatProvider
import net.minecraft.util.valueproviders.IntProvider
import net.minecraft.util.valueproviders.UniformFloat
import net.minecraft.util.valueproviders.UniformInt
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider

data class CaveSurfaceFeatureConfig(
    val blockstate: BlockStateProvider,
    val canReplace: HolderSet<Block>,
    val radius: IntProvider = UniformInt.of(2, 4),
    val blockChanceCenter: FloatProvider = UniformFloat.of(0.7f, 1f),
    val blockChanceEdge: FloatProvider = UniformFloat.of(0f, 0.3f)
) : FeatureConfiguration {

    companion object {
        val CODEC: Codec<CaveSurfaceFeatureConfig> = RecordCodecBuilder.create { instance ->
            instance.group(
                BlockStateProvider.CODEC.fieldOf("blockstate").forGetter { it.blockstate },
                RegistryCodecs.homogeneousList(Registries.BLOCK).fieldOf("can_replace")
                    .forGetter { it.canReplace },
                IntProvider.codec(1, 15).fieldOf("radius").forGetter { it.radius },
                FloatProvider.codec(-1f, 1f).fieldOf("block_chance_center").forGetter { it.blockChanceCenter },
                FloatProvider.codec(-1f, 1f).fieldOf("block_chance_edge").forGetter { it.blockChanceEdge },
            ).apply(instance, ::CaveSurfaceFeatureConfig)
        }
    }
}