import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class LISTest {
  private val input = intArrayOf(7, 2, 1, 3, 8, 4, 9, 1, 2, 6, 5, 9, 3, 8, 1)
  private val inputLengthLIS = intArrayOf(1, 1, 1, 2, 3, 3, 4, 1, 2, 4, 4, 5, 3, 5, 1)

  @Test
  fun shouldReturnProperLength() {
    val length = LIS.bottomUp(input)
    val expected = 5

    assertEquals(expected, length)
  }

  @Test
  fun shouldReturnProperSubsequence() {
    val subsequence = LIS.bottomUpSubSequence(input)
    val expectedSubsequence = intArrayOf(2, 3, 4, 6, 9)

    assertEquals(subsequence.size, expectedSubsequence.size)

    for (i in subsequence.indices) {
      assertEquals(expectedSubsequence[i], subsequence[i])
    }
  }

  @Test
  fun shouldRestoreSubsequenceProperly() {
    val subsequence = LIS.restoreSubsequence(input, inputLengthLIS)
    val expectedRestoredSubsequence = intArrayOf(1, 3, 4, 5, 8)

    assertEquals(subsequence.size, expectedRestoredSubsequence.size)

    for (i in subsequence.indices) {
      assertEquals(expectedRestoredSubsequence[i], subsequence[i])
    }
  }
}