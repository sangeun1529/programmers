package level.two

/**
 *
 * @author kkomac
 * @date 4/17/24
 */
class Solution

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/70129
 */
class Solution0002 {
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

