import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class EDTest {
  private val first = " EDITING"
  private val second = " DISTANCE"

  private val expected = 5

  @Test
  fun shouldComputeEditingDistanceRecursivelyProperly() {
    val editingDistance = ED.editingDistanceTopDown(first, second)

    assertEquals(expected, editingDistance)
  }

  @Test
  fun shouldComputeEditingDistanceIterativelyProperly() {
    val editingDistance = ED.editingDistanceBottomUp(first, second)

    assertEquals(expected, editingDistance)
  }
}