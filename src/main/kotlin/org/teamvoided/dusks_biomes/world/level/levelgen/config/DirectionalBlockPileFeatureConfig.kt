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

data class DirectionalBlockPileFeatureConfig(
    val blockstate: BlockStateProvider,
    val canReplace: HolderSet<Block>,
    val radius: IntProvider = UniformInt.of(2, 4),
    val height: IntProvider = ConstantInt.of(2),
    val blockInRadiusChance: FloatProvider = UniformFloat.of(-0.25f, 1f), //-0.6f 1f
    val blockChance: FloatProvider = ConstantFloat.of(0.03f),
    val walls: Boolean = true,
    val floor: Boolean = true,
    val ceiling: Boolean = true
) : FeatureConfiguration {

    companion object {
        val CODEC: Codec<DirectionalBlockPileFeatureConfig> = RecordCodecBuilder.create { instance ->
            instance.group(
                BlockStateProvider.CODEC.fieldOf("blockstate").forGetter { it.blockstate },
                RegistryCodecs.homogeneousList(Registries.BLOCK).fieldOf("can_replace")
                    .forGetter { it.canReplace },
                IntProvider.codec(1, 15).fieldOf("radius").forGetter { it.radius },
                IntProvider.codec(1, 15).fieldOf("height").forGetter { it.height },
                FloatProvider.codec(-1f, 1f).fieldOf("block_in_radius_chance").forGetter { it.blockInRadiusChance },
                FloatProvider.codec(0f, 1f).fieldOf("block_chance").forGetter { it.blockChance },
                Codec.BOOL.fieldOf("walls").forGetter { it.walls },
                Codec.BOOL.fieldOf("floor").forGetter { it.floor },
                Codec.BOOL.fieldOf("ceiling").forGetter { it.ceiling },
            ).apply(instance, ::DirectionalBlockPileFeatureConfig)
        }
    }
}