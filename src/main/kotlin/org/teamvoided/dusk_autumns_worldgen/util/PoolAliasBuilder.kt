package org.teamvoided.dusk_autumns_worldgen.util

@Suppress("unused")
object PoolAliasBuilder {

    fun aliases(string: String): String = "trial_chambers/spawner/$string"
//    val one: C_noatlymd = PoolAlias.method_54506(
//        DataPool.builder<List<PoolAlias>>().method_54453(
//            listOf(
//                PoolAlias.method_54508(aliases("contents/ranged"), aliases("ranged/skeleton")),
//                PoolAlias.method_54508(aliases("contents/slow_ranged"), aliases("slow_ranged/skeleton"))
//            )
//        ).method_54453(
//            listOf(
//                PoolAlias.method_54508(aliases("contents/ranged"), aliases("ranged/stray")),
//                PoolAlias.method_54508(aliases("contents/slow_ranged"), aliases("slow_ranged/stray"))
//            )
//        ).method_54453(
//            listOf(
//                PoolAlias.method_54508(
//                    aliases("contents/ranged"),
//                    aliases("ranged/poison_skeleton")
//                ),
//                PoolAlias.method_54508(
//                    aliases("contents/slow_ranged"),
//                    aliases("slow_ranged/poison_skeleton")
//                )
//            )
//        ).build()
//    )
//    val alaiases = listOf(
//        one,
//        PoolAlias.method_54507(
//            aliases("contents/melee"),
//            DataPool.builder<String>().method_54453(aliases("melee/zombie"))
//                .method_54453(aliases("melee/husk"))
//                .method_54453(aliases("melee/slime")).build()
//        ),
//        PoolAlias.method_54507(
//            aliases("contents/small_melee"),
//            DataPool.builder<String>().method_54453(aliases("small_melee/spider"))
//                .method_54453(aliases("small_melee/cave_spider"))
//                .method_54453(aliases("small_melee/silverfish"))
//                .method_54453(aliases("small_melee/baby_zombie")).build()
//        ),
//    )
}