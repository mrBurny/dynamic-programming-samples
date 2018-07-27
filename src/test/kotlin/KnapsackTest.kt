import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class KnapsackTest {
  private val items = arrayOf(Pair(30, 6), Pair(14, 3), Pair(16, 4), Pair(9, 2))

  @Test
  fun shouldFillKnapsackOptimally() {
    val cost = Knapsack.knapsackBottomUp(10, items)
    val expected = 48

    assertEquals(expected, cost)
  }

  @Test
  fun shouldFillKnapsackWithoutRepetitionsOptimally() {
    val cost = Knapsack.knapsackWithoutRepetitionsBottomUp(10, items)
    val expected = 46

    assertEquals(expected, cost)
  }
}