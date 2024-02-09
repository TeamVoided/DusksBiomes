package org.teamvoided.dusk_autumns_worldgen.worldgen.configured_feature.config

import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.world.gen.feature.FeatureConfig
import net.minecraft.world.gen.stateprovider.BlockStateProvider

class MonsterRoomFeatureConfig(
    val primaryBlock: BlockStateProvider,
    val secondaryBlock: BlockStateProvider,

    ) : FeatureConfig {
    companion object {
        val CODEC =
            RecordCodecBuilder.create { instance: RecordCodecBuilder.Instance<MonsterRoomFeatureConfig> ->
                instance.group(
                    BlockStateProvider.TYPE_CODEC.fieldOf("base_block").forGetter { it.primaryBlock },
                    BlockStateProvider.TYPE_CODEC.fieldOf("secondary_block").forGetter { it.secondaryBlock },
                ).apply(instance, ::MonsterRoomFeatureConfig)
            }
    }

}