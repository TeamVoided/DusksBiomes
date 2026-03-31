package org.teamvoided.dusks_biomes.datagen.assets

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider
import net.minecraft.core.HolderLookup
import net.minecraft.data.PackOutput
import net.minecraft.resources.Identifier
import org.teamvoided.dusks_biomes.init.DuskBiomes
import java.nio.file.Path
import java.util.concurrent.CompletableFuture

class EnglishTranslationProvider(o: FabricDataOutput, r: CompletableFuture<HolderLookup.Provider>) :
    FabricLanguageProvider(o, r) {

    override fun generateTranslations(lookup: HolderLookup.Provider, gen: TranslationBuilder) {
        DuskBiomes.DUSK_BIOMES.forEach {
            val id = it.identifier()
            gen.add(id.toLanguageKey("biome"), genLang(id))
        }
    }

    private fun genLang(identifier: Identifier): String =
        identifier.path.split("_").joinToString(" ") { it.replaceFirstChar(Char::uppercaseChar) }

    override fun getLangFilePath(code: String): Path {
        return dataOutput
            .createPathProvider(PackOutput.Target.RESOURCE_PACK, "lang")
            .json(Identifier.fromNamespaceAndPath(dataOutput.modId, code))
    }
}