import com.charleskorn.kaml.Yaml
import data.Club
import data.ClubFile

object ClubLoader {

    private val yaml = Yaml.default

    fun loadGermanFirstDivision(): List<Club> {

        val inputStream =
            object {}::class.java.getResourceAsStream(
                "/data/clubs/germany_first_division.yaml"
            ) ?: error("Could not find YAML file.")

        val text =
            inputStream.bufferedReader().use { it.readText() }

        val clubFile =
            yaml.decodeFromString(
                ClubFile.serializer(),
                text
            )

        return clubFile.clubs
    }
}