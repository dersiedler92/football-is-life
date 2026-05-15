import data.World

object GameCreator {

    fun createNewGame(): World {
        val seed = System.currentTimeMillis()

        return WorldGenerator(seed).generate()
    }

    fun createNewGame(seed: Long): World {
        return WorldGenerator(seed).generate()
    }
}