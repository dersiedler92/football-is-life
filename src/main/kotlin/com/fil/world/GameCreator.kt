package com.fil.world

import com.fil.generation.WorldGenerator

object GameCreator {

    fun createNewGame(): World {
        val seed = System.currentTimeMillis()

        return WorldGenerator(seed).generate()
    }

    fun createNewGame(seed: Long): World {
        return WorldGenerator(seed).generate()
    }
}