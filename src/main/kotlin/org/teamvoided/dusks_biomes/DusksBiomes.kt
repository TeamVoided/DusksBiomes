package org.teamvoided.dusks_biomes

import net.minecraft.resources.Identifier
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.teamvoided.dusks_biomes.init.DuskDebug
import org.teamvoided.dusks_biomes.init.DuskFeatures
import org.teamvoided.dusks_biomes.init.DuskStructureProcessorTypes
import org.teamvoided.reef.util.isDev


@Suppress("unused")
object DusksBiomes {
    const val MODID = "dusks_biomes"

    @JvmField
    val log: Logger = LoggerFactory.getLogger(DusksBiomes::class.simpleName)

    fun commonInit() {
        log.info("Dusking and Biomeing")

//      For TerraBlender compatibility, it is important the rulesOwner's
//      namespace should be the identical to the namespace of all biomes to which the rules apply.
//        SurfaceGeneration.addOverworldSurfaceRules(mc("rules/overworld"), DuskSurfaceRules.overworld())
        DuskFeatures.init()
        DuskStructureProcessorTypes.init()
        if (isDev()) DuskDebug.init()
    }

    fun id(namespace: String, path: String): Identifier = Identifier.fromNamespaceAndPath(namespace, path)
    fun id(path: String) = id(MODID, path)
    fun mc(path: String): Identifier = Identifier.withDefaultNamespace(path)
}
