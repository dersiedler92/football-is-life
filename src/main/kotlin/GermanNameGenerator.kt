import kotlin.random.Random

object GermanNameGenerator {

    private const val FIRST_NAMES_PATH = "/data/names/firstnames.csv"
    private const val SURNAMES_PATH = "/data/names/german_surnames.txt"

    private val firstNames: List<String> by lazy {
        loadGermanMaleFirstNames()
    }

    private val surnames: List<String> by lazy {
        loadSurnames()
    }

    fun generate(random: Random): String {
        val firstName = firstNames.random(random)
        val surname = surnames.random(random)

        return "$firstName $surname"
    }

    private fun loadGermanMaleFirstNames(): List<String> {
        val lines = readResourceLines(FIRST_NAMES_PATH)

        require(lines.isNotEmpty()) {
            "First names CSV is empty."
        }

        val header = lines.first().split(";")
        val nameIndex = header.indexOf("name")
        val genderIndex = header.indexOf("gender")
        val germanyIndex = header.indexOf("Germany")

        require(nameIndex >= 0) { "CSV does not contain column 'name'." }
        require(genderIndex >= 0) { "CSV does not contain column 'gender'." }
        require(germanyIndex >= 0) { "CSV does not contain column 'Germany'." }

        return lines
            .drop(1)
            .map { it.split(";") }
            .filter { columns ->
                columns.size > germanyIndex &&
                        columns[genderIndex] == "M" &&
                        columns[germanyIndex].isNotBlank()
            }
            .map { columns ->
                columns[nameIndex].trim()
            }
            .filter { it.isNotBlank() }
            .distinct()
    }

    private fun loadSurnames(): List<String> {
        return readResourceLines(SURNAMES_PATH)
            .map { it.trim() }
            .filter { it.isNotBlank() }
            .distinct()
    }

    private fun readResourceLines(path: String): List<String> {
        val inputStream =
            GermanNameGenerator::class.java.getResourceAsStream(path)
                ?: error("Could not find resource: $path")

        return inputStream
            .bufferedReader(Charsets.UTF_8)
            .useLines { it.toList() }
    }
}