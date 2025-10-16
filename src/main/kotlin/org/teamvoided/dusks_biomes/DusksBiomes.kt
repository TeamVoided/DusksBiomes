package org.teamvoided.dusks_biomes

import net.minecraft.resources.ResourceLocation
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.teamvoided.dusks_biomes.init.DuskBiomes
import org.teamvoided.dusks_biomes.init.DuskDebug
import org.teamvoided.dusks_biomes.init.DuskStructureProcessorTypes


@Suppress("unused")
object DusksBiomes {
    const val MODID = "dusks_biomes"

    @JvmField
    val log: Logger = LoggerFactory.getLogger(DusksBiomes::class.simpleName)

    fun commonInit() {
        log.info("Dusking and Biomeing")

        DuskBiomes.init()
        DuskStructureProcessorTypes.init()
        DuskDebug.init()
    }

    fun id(namespace: String, path: String): ResourceLocation = ResourceLocation.fromNamespaceAndPath(namespace, path)
    fun id(path: String) = id(MODID, path)
    fun mc(path: String): ResourceLocation = ResourceLocation.withDefaultNamespace(path)
}
