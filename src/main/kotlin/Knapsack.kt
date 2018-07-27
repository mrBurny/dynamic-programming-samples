import kotlin.math.cos
import kotlin.math.max

/**
 * Yeah, this is so popular task about a backpack, weights and costs.
 */
class Knapsack {

  companion object {

    /**
     * Computes the highest possible price iteratively. This method allows repetitions.
     * @param maxWeight maximal possible weight
     * @param items array of tuples (cost, weight) of items
     * @return the highest price
     */
    fun knapsackBottomUp(maxWeight: Int, items: Array<Pair<Int, Int>>): Int {
      val knapsacks = IntArray(maxWeight + 1) { 0 }
      for (w in 0 until (knapsacks.size + 1)) {
        for (j in items.indices) {
          val (cost, weight) = items[j]
          if (weight < w) {
            knapsacks[w - 1] = max(knapsacks[w - 1], knapsacks[w - 1 - weight] + cost)
          }
        }
      }
      return knapsacks.last()
    }

    /**
     * Computes the highest possible price iteratively. This method doesn't allow repetitions.
     * @param maxWeight maximal possible weight
     * @param items array of tuples (cost, weight) of items
     * @return the highest price
     */
    fun knapsackWithoutRepetitionsBottomUp(maxWeight: Int, items: Array<Pair<Int, Int>>): Int {
      val knapsacks = Array(maxWeight + 1) { IntArray(items.size + 1) { 0 } }
      for (i in 1..items.size) {
        for (w in 1..maxWeight) {
          knapsacks[w][i] = knapsacks[w][i - 1]
          val (cost, weight) = items[i - 1]
          if (weight <= w) {
            knapsacks[w][i] = max(knapsacks[w][i], knapsacks[w - weight][i - 1] + cost)
          }
        }
      }

      return knapsacks.last().last()
    }
  }
}