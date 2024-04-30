package cote.well

/**
 *
 * @author kkomac
 * @date 4/30/24
 */

class Solution0009 {
    fun solution(num: Int): Int {
        var answer = 0
        var seq = -1
        do {
            seq++
            answer = num
            val numStr = (answer + seq).toString()

            var firstHalf = 1
            var secondHalf = 1

            for (i in 0 until numStr.length / 2) {
                firstHalf *= numStr[i].digitToInt()
                secondHalf *= numStr[i + numStr.length / 2].digitToInt()
            }
        } while (firstHalf != secondHalf)

        return answer + seq
    }
}

class Solution0010 {
    fun solution(a: String, b: String): String = (a.toInt() + b.toInt()).toString()
}

class Solution0011 {
    fun solution(grade: IntArray): Array<Int?> {
        val sortedGrade = grade.sortedDescending()

        val rank = mutableMapOf<Int, Int>()

        var tempRank = 0 // 임시 성적
        var sameScore = 0
        var sameScoreCount = 0

        sortedGrade.forEach rank@{ score ->
            if (sameScore == score) { // 동점자
                sameScoreCount++
                return@rank
            }
            tempRank += 1 + sameScoreCount
            rank[score] = tempRank
            sameScore = score
            sameScoreCount = 0
        }

        return grade.map { rank[it] }.toTypedArray()
    }
}

class Solution0012 {
    fun solution(vote: String): String {
        val voted = vote.toCharArray()
        return if (voted.size <= voted.filter { it == 'C' }.size) // 기권
            "C"
        else{
            val leaderAVoted = voted.filter { it == 'A' }.size
            val leaderBVoted = voted.filter { it == 'B' }.size
            when{
                leaderAVoted > leaderBVoted -> "A"
                leaderAVoted < leaderBVoted -> "B"
                else -> "AB"
            }
        }
    }
}
