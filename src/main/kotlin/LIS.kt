/**
 * Class with utility methods to compute the Longest Increasing Distance.
 */
class LIS {

  companion object {
    /**
     * Computes the length of the Longest Increasing Subsequence.
     * @param   input the array of integers to compute the length
     * @return  the length
     */
    fun bottomUp(input: IntArray): Int {
      val lengthLIS = IntArray(input.size) { _ -> 1 }
      for (i in input.indices) {
        for (j in 0 until i) {
          if (input[i] < input[j] && lengthLIS[j] + 1 > lengthLIS[i]) {
            lengthLIS[i] = lengthLIS[j] + 1
          }
        }
      }

      return lengthLIS.max()!!
    }

    /**
     * Extracts the Longest Increasing Subsequence.
     * @param   input the array of integers to extract the LIS
     * @return  the longest increasing subsequence
     */
    fun bottomUpSubSequence(input: IntArray): IntArray {
      val lengthLIS = IntArray(input.size) { _ -> 1 }
      val previousElement = IntArray(input.size) { _ -> -1 }
      for (i in input.indices) {
        for (j in 0 until i) {
          if (input[j] < input[i] && lengthLIS[j] + 1 > lengthLIS[i]) {
            lengthLIS[i] = lengthLIS[j] + 1
            previousElement[i] = j
          }
        }
      }

      val (lastIndex, length) = getLengthIndexPair(lengthLIS)

      return restoreSubSequence(length, input, lastIndex, previousElement)
    }

    private fun getLengthIndexPair(lengthLIS: IntArray): Pair<Int, Int> {
      var lastIndex = 0
      var length: Int = Int.MIN_VALUE
      for (i in lengthLIS.indices) {
        if (lengthLIS[i] > length) {
          length = lengthLIS[i]
          lastIndex = i
        }
      }
      return Pair(lastIndex, length)
    }

    private fun restoreSubSequence(length: Int,
                                   input: IntArray,
                                   index: Int,
                                   previousElement: IntArray): IntArray {
      var lastIndex = index
      val subsequence = IntArray(length)
      for (i in subsequence.size - 1 downTo 0) {
        subsequence[i] = input[lastIndex]
        lastIndex = previousElement[lastIndex]
      }
      return subsequence
    }

    /**
     * Restores the Longest Increasing Subsequence.
     * The result may differ from the one provided by [LIS.bottomUpSubSequence] in case if two valid subsequences exist.
     *
     * @param input the array of integers to extract the LIS
     * @param lengthLIS the array of LIS of the corresponding element in input
     * @return extracted subsequence
     */
    fun restoreSubsequence(input: IntArray, lengthLIS: IntArray): IntArray {
      var length = lengthLIS.max()!!
      val subsequence = IntArray(length)

      for (i in (lengthLIS.size - 1) downTo 0) {
        if (length < 0) {
          return subsequence
        }
        if (lengthLIS[i] == length) {
          if (length == subsequence.size) {
            subsequence[length - 1] = input[i]
            length--
          } else {
            if (input[i] < subsequence[length]) {
              subsequence[length - 1] = input[i]
              length--
            }
          }
        }
      }

      return subsequence
    }
  }
}