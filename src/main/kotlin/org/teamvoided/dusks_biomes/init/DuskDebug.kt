package org.teamvoided.dusks_biomes.init

import com.mojang.brigadier.suggestion.Suggestions
import com.mojang.brigadier.suggestion.SuggestionsBuilder
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback
import net.fabricmc.loader.api.FabricLoader
import net.minecraft.commands.Commands.literal
import java.util.concurrent.CompletableFuture

object DuskDebug {
    fun init() {
        if (FabricLoader.getInstance().isDevelopmentEnvironment)
        CommandRegistrationCallback.EVENT.register { dispatch, _, _ ->
            val root = literal("dusk").build()
            dispatch.root.addChild(root)

            val test = literal("test").executes { println("test"); 0 }.build()
            root.addChild(test)
        }
    }

    // (ender) stolen from CW
    fun SuggestionsBuilder.listSuggestions(list: Iterable<String>?)
            : CompletableFuture<Suggestions> {
        val query = this.remainingLowerCase.trim().lowercase()
        list?.filter { filterByQuery(it.trim().lowercase(), query) }?.forEach(this::suggest)
        return this.buildFuture()
    }

    fun filterByQuery(entry: String, query: String): Boolean {
        if (query.contains(":") && entry.contains(":")) {
            val splitQuery = query.split(":")
            val queryN = splitQuery[0].trim()
            val queryP = splitQuery.drop(1).joinToString("").trim()
            val splitEntry = entry.split(":")
            val entryN = splitEntry[0]
            val entryP = splitEntry.drop(1).joinToString("")

            var namespaceMatches = true
            if (!queryN.isEmpty()) {
                namespaceMatches = entryN.contains(queryN)
            }
            return namespaceMatches && entryP.contains(queryP)
        }
        return entry.contains(query)
    }
}



