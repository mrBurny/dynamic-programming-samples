/**
 * Class with utility methods to compute Editing Distance.
 */
class ED {

  companion object {
    fun editingDistanceBottomUp(first: String, second: String): Int {
      val lengthsED = Array(first.length) { _ -> IntArray(second.length) { _ -> Int.MAX_VALUE } }
      for (i in first.indices) {
        lengthsED[i][0] = i
      }
      for (j in second.indices) {
        lengthsED[0][j] = j
      }

      for (i in 1 until first.length) {
        for (j in 1 until second.length) {
          val diff = if (first[i] != second[j]) 1 else 0
          lengthsED[i][j] = minOf(lengthsED[i - 1][j] + 1, lengthsED[i][j - 1] + 1, lengthsED[i - 1][j - 1] + diff)
        }
      }

      return lengthsED.last().last()
    }

    fun editingDistanceTopDown(first: String, second: String): Int {
      val lengthsED = Array(first.length) { _ -> IntArray(second.length) { _ -> Int.MAX_VALUE } }

      val i = first.length - 1
      val j = second.length - 1
      return editingDistanceRecursiveCall(first, second, lengthsED, i, j)
    }

    private fun editingDistanceRecursiveCall(first: String, second: String, lengthsED: Array<IntArray>, i: Int, j: Int): Int {
      if (lengthsED[i][j] == Int.MAX_VALUE) {
        if (i == 0) {
          lengthsED[i][j] = j
        } else {
          if (j == 0) {
            lengthsED[i][j] = i
          } else {
            val insert = editingDistanceRecursiveCall(first, second, lengthsED, i, j - 1) + 1
            val delete = editingDistanceRecursiveCall(first, second, lengthsED, i - 1, j) + 1
            val diff = if (first[i] != second[j]) 1 else 0
            val update = editingDistanceRecursiveCall(first, second, lengthsED, i - 1, j - 1) + diff
            lengthsED[i][j] = minOf(insert, update, delete)
          }
        }
      }

      return lengthsED[i][j]
    }
  }
}