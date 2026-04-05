package org.teamvoided.dusks_biomes.world.level.levelgen.config

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.core.HolderSet
import net.minecraft.core.RegistryCodecs
import net.minecraft.core.registries.Registries
import net.minecraft.tags.BlockTags
import net.minecraft.util.valueproviders.BiasedToBottomInt
import net.minecraft.util.valueproviders.ConstantFloat
import net.minecraft.util.valueproviders.ConstantInt
import net.minecraft.util.valueproviders.FloatProvider
import net.minecraft.util.valueproviders.IntProvider
import net.minecraft.util.valueproviders.UniformFloat
import net.minecraft.util.valueproviders.UniformInt
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider

data class MultifaceFeatureConfig(
    val blockstate: BlockStateProvider,
    val count: IntProvider = BiasedToBottomInt.of(3, 15),
    val radius: IntProvider = ConstantInt.of(7)
) : FeatureConfiguration {

    companion object {
        val CODEC: Codec<MultifaceFeatureConfig> = RecordCodecBuilder.create { instance ->
            instance.group(
                BlockStateProvider.CODEC.fieldOf("blockstate").forGetter { it.blockstate },
                IntProvider.codec(1, 256).fieldOf("count").forGetter { it.count },
                IntProvider.codec(1, 15).fieldOf("radius").forGetter { it.radius },
            ).apply(instance, ::MultifaceFeatureConfig)
        }
    }
}