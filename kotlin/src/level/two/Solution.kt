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

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/172927
 */
class Solution0004 {
    fun solution(picks: IntArray, minerals: Array<String>) = if (minerals.size > picks.sum() * 5) {
        minerals.slice(0 until picks.sum() * 5).toList().chunked(5)
    } else {
        minerals.toList().chunked(5)
    }.map { minerals ->
        val mineralsCount = intArrayOf(0, 0, 0)
        minerals.forEach {
            when (it) {
                "diamond" -> mineralsCount[0]++
                "iron" -> mineralsCount[1]++
                "stone" -> mineralsCount[2]++
            }
        }
        mineralsCount
    }.sortedWith(compareByDescending<IntArray> { it[0] }.thenByDescending { it[1] }.thenByDescending { it[2] }).sumOf {
        when {
            picks[0] != 0 -> {
                picks[0] -= 1
                it[0] + it[1] + it[2]
            }

            picks[1] != 0 -> {
                picks[1] -= 1
                it[0] * 5 + it[1] + it[2]
            }

            else -> {
                picks[2] -= 1
                it[0] * 25 + it[1] * 5 + it[2]
            }
        }
    }
}

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/12945
 */
class Solution0005 {
    fun solution(n: Int) = fibonacci(n)

    private fun fibonacciFail(n: Int): Int { // 재귀를 한다면 n^n 의 시간복잡도
        return when (n) {
            0 -> 0
            1 -> 1
            else -> fibonacciFail(n - 1) + fibonacciFail(n - 2)
        }
    }

    private fun fibonacci(n: Int): Int {
        if (n < 2) return n
        val cache = mutableMapOf(1 to 1, 2 to 1)
        (3..n).forEach {
            cache[it] = (cache[it - 1]!! + cache[it - 2]!!) % 1234567
        }

        return cache[n]!!
    }
}

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/42842
 */
class Solution0006 {
    fun solution(brown: Int, yellow: Int): IntArray {
        var answer = intArrayOf()

        val area = brown + yellow
        // 카펫의 꼭지점 개수 4
        // 노란색 가로 * 2 + 노란색세로 *2 + 4 = 총 넓이이므로 브라운의 개수가 결정.
        // 노란색 가로 + 2 = 가로길이
        // 노란색 세로 + 2 = 세로길이
        (1..yellow).forEach { yellowWidth -> // 가로길이를 올려가며 브라운 개수와 같은 넓이를 구함.
            val yellowHeight = yellow / yellowWidth
            if (((yellowWidth * 2) + (yellowHeight * 2) + 4) == brown) {
                val brownWidth = yellowWidth + 2
                val brownHeight = yellowHeight + 2
                if (brownWidth >= brownHeight && brownWidth * brownHeight == area) answer =
                    intArrayOf(brownWidth, brownHeight)

            }
        }

        return answer
    }
}

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/42860
 */
class Solution0007 {
    fun solution(name: String): Int {
        var answer = 0

        val nameArr = name.toCharArray()

        nameArr.forEach { // 상하 조이스틱
            answer += getChangedCountBy(it)
        }

        // 좌우 조이스틱...

        return answer
    }

    private fun getChangedCountBy(target: Char) = if (target > 'M') {
        'Z' - target + 1
    } else {
        target - 'A'
    }
}

/**
 *
 */
class Solution0008 {
    fun solution(sequence: IntArray, k: Int): IntArray {
        sequence.reverse()

        (0..sequence.size - 1).forEach end@{ i ->
            (i..sequence.size - 1).forEach { j ->
                if (getSumBy(sequence, i, j, k)) {
                    return intArrayOf(sequence.size - 1 - j, sequence.size - 1 - i)
                }
            }
        }
        return intArrayOf()
    }

    fun getSumBy(sequence: IntArray, from: Int, to: Int, k: Int) = (from..to).sumOf {
        sequence[it]
    } == k
}
fun main() {
    val a = Solution0008().solution(intArrayOf(1, 52, 36, 77, 4, 1, 1, 24, 25, 4),1)
    println(a)
}