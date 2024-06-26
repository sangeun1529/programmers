package level.one

/**
 *
 * @author kkomac
 * @date 4/17/24
 */
/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/77484
 */
class Solution0001 {
    fun solution(lottos: IntArray, win_nums: IntArray): IntArray {
        val answer = IntArray(2) //index 0 : 최고순위 , 1 : 최저순의

        var unkownCount = 0
        var matchCount = 0

        for (lotto in lottos) {
            if (lotto == 0) unkownCount++
            else {
                for (win_num in win_nums) {
                    if (lotto == win_num) {
                        matchCount++
                        break
                    }
                }
            }
        }

        answer[0] = rank(matchCount + unkownCount)
        answer[1] = rank(matchCount)

        return answer
    }

    fun rank(match: Int): Int {
        return when (match) {
            6 -> 1
            5 -> 2
            4 -> 3
            3 -> 4
            2 -> 5
            1, 0 -> 6
            else -> 6
        }
    }
}

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/12925
 */
class Solution0002 {
    fun solution(s: String) = s.toInt()
}


/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/42862
 */
class Solution0003 {
    fun solution(n: Int, lost: IntArray, reserve: IntArray): Int {
        val reserved: MutableList<Int> = reserve.sorted().filterNot { lost.contains(it) }.toMutableList()
        val losted: MutableList<Int> = lost.sorted().filterNot { reserve.contains(it) }.toMutableList()
        var answer = n - losted.size

        for (target in losted) {
            val rest = reserved.firstOrNull { it == target - 1 } ?: reserved.firstOrNull { it == target + 1 }
            rest?.let {
                reserved.remove(it)
                answer++
            }
        }
        return answer
    }
}
