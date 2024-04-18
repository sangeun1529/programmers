package level.two

/**
 *
 * @author kkomac
 * @date 4/17/24
 */

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/70129
 */
class Solution0001 {
    fun solution(s: String): List<Int> {
        var convertCount = 0
        var removeZero = 0

        var convertee = s
        do {
            convertCount++
            removeZero += convertee.asSequence().filter { it == '0' }.count()
            convertee = convert(convertee)
        } while (convertee != "1")

        return listOf(convertCount, removeZero)
    }

    fun convert(s: String) = s.asSequence().filter { it == '1' }.count().toString(2)

}

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/43165
 */
class Solution0002 {

    fun solution(numbers: IntArray, target: Int): Int {
        var answer = 0

        fun dfs(index: Int, result: Int) {
            if (index == numbers.size) {
                if (result == target) {
                    answer += 1
                }
            } else {
                dfs(index + 1, result + numbers[index])
                dfs(index + 1, result - numbers[index])
            }
        }

        dfs(0, 0)
        return answer
    }
}


/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/87946
 */
class Solution0003 {
    var answer = 0
    fun solution(k: Int, dungeons: Array<IntArray>): Int {
        dfs(dungeons, k, 0, Array(dungeons.size) { false })
        return answer
    }

    fun dfs(map: Array<IntArray>, tired: Int, count: Int, isVisited: Array<Boolean>) {
        if (count > answer) answer = count

        for ((idx, value) in map.withIndex()) {
            if (!isVisited[idx] && value[0] <= tired) {
                isVisited[idx] = true
                dfs(map, tired - value[1], count + 1, isVisited)
                isVisited[idx] = false
            }
        }
    }

    /**
     * 적은 수 우선 탐색
     */
    fun solution2(k: Int, dungeons: Array<IntArray>) = recurse(k, dungeons, 0)

    private fun recurse(k: Int, dungeons: Array<IntArray>, count: Int): Int {
        return dungeons.filter { dungeon -> dungeon[0] <= k }.minByOrNull { dungeon ->
            dungeon[1]
        }?.let { dungeon ->
            recurse(k - dungeon[1], dungeons.filterNot { it.contentEquals(dungeon) }.toTypedArray(), count + 1)
        } ?: count

    }

    private inline fun <T, R : Comparable<R>> Iterable<T>.minByOrNull(selector: (T) -> R): T? {
        val iterator = iterator()
        if (!iterator.hasNext()) return null
        var minElem = iterator.next()
        if (!iterator.hasNext()) return minElem
        var minValue = selector(minElem)
        do {
            val e = iterator.next()
            val v = selector(e)
            if (minValue > v) {
                minElem = e
                minValue = v
            }
        } while (iterator.hasNext())
        return minElem
    }
}