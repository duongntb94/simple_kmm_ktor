import com.duongntb94.kmmnetwork.models.Song
import kotlinx.serialization.Serializable

@Serializable
data class ResultCollection(
    val resultCount: Long,
    val results: List<Song> = listOf()
) {
    override fun toString(): String {
        return "Result count: ${resultCount}. Results: ${results.joinToString("\n") { t -> t.toString() }}"
    }
}