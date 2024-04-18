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