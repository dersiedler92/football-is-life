import java.io.BufferedInputStream
import javax.sound.sampled.AudioSystem
import javax.sound.sampled.Clip

object BgmPlayer {

    private var clip: Clip? = null

    fun playLoop(resourcePath: String) {

        stop()

        val inputStream =
            object {}::class.java.getResourceAsStream(resourcePath)
                ?: error("Could not find audio file: $resourcePath")

        val bufferedInputStream =
            BufferedInputStream(inputStream)

        val audioInputStream =
            AudioSystem.getAudioInputStream(bufferedInputStream)

        clip = AudioSystem.getClip().apply {
            open(audioInputStream)
            loop(Clip.LOOP_CONTINUOUSLY)
            start()
        }
    }

    fun stop() {
        clip?.stop()
        clip?.close()
        clip = null
    }
}