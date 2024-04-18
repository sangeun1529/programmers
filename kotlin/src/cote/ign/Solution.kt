package cote.ign

/**
 *
 * @author kkomac
 * @date 4/18/24
 */

class Solution0001 {
    fun solution(receivers: Array<String>, employees: Array<Array<String>>): Int {
        var answer = 0
        val targetReceiverSet: MutableSet<String> = mutableSetOf()
        fun removeReceiver(removeTargets: MutableSet<String>) { // 제거 루프 함수
            for (target in removeTargets) {
                if (!receivers.contains(target)) {
                    targetReceiverSet.remove(target)
                    answer++
                }
            }
        }
        receivers.forEach { receiver ->
            if (!targetReceiverSet.contains(receiver)) { // 수신자 목록에 들어가 있지 않은 사원
                employees.forEach employLoop@{ employee ->
                    if (receiver == employee[0]) {
                        val teamEmployees = employees.filter { it[1] == employee[1] }.map { it[0] }.toTypedArray()
                        val intersectSize = receivers.intersect(teamEmployees.toSet()).size //교집합
                        if (intersectSize > 1 + (teamEmployees.size - intersectSize)) {
                            targetReceiverSet.addAll(teamEmployees)
                            answer++
                            removeReceiver(targetReceiverSet)
                        } else {
                            targetReceiverSet.add(receiver)
                            answer++

                        }
                        return@employLoop
                    }
                }
            }
        }

        return answer
    }
}


class Solution0002 {
    fun solution(records: Array<String>, k: Int, date: String): Array<String> {
        val recordsSplit: Array<Array<String>> = records.map { it.split(" ").toTypedArray() }.toTypedArray()
        val targetDates = targetDates(date)

        return recordsSplit.asSequence().filter { targetDates.contains(it[0]) }.groupBy { it[2] }.map { groupedPid ->
            groupedPid.key // pid
            groupedPid.value.count() // 상품을 재 구매한 고객 수
            groupedPid.value.groupBy { it[1] }.filter { it.key.count() > 1 } // 같은 상품을 2번 이상 구매한 고객 수
            val rating = (groupedPid.value.count() / groupedPid.value.groupBy { it[1] }.filter { it.key.count() > 1 }.count() * 100)
            groupedPid.key to rating
        }.sortedByDescending { it.second }.map { it.first }.toTypedArray().ifEmpty {
            arrayOf("no result")
        }
    }

    private fun targetDates(date: String) = mutableListOf<String>().apply {
        val betweenDays = 10
        val dateSplit = date.split("-")
        val year = dateSplit[0]
        val month = dateSplit[1]
        val days = dateSplit[2].toInt()

        repeat(betweenDays) { minusDay ->
            val targetDay = days - minusDay
            add(
                if (targetDay <= 0) "$year-${String.format("%02d", month.toInt() - 1)}-${String.format("%02d", targetDay + 30)}"
                else "$year-$month-${String.format("%02d", targetDay)}"
            )
        }
    }
}

class Solution0003 {
    fun solution(hopeNumber: String, existNumber: Array<String>): Array<String> {
        val replacedHopeNumber = hopeNumber.replace("-", "")
        val replacedExistNumbers = existNumber.map { it.replace("-", "") }.toTypedArray()

        val hopeNumberAvail =
            arrayOf(arrayOf(""), arrayOf(""), arrayOf(""), arrayOf(""), arrayOf(""), arrayOf("")) // 가능한 조합

        replacedHopeNumber.forEachIndexed { index, hopeChar ->
            if (hopeChar != 'X') {
                hopeNumberAvail[index] = arrayOf(hopeChar.toString())
            } else {
                val insertList = mutableListOf("0", "1", "2", "3", "4", "5", "6", "7", "8", "9")
                replacedExistNumbers.forEach { replacedExistNumber ->
                    val targetEle = replacedExistNumber.elementAt(index)
                    if (targetEle == 'X') return@forEachIndexed
                    else insertList.remove(targetEle.toString())
                }
                hopeNumberAvail[index] = insertList.toTypedArray()
            }
        }

        return if (hopeNumberAvail.any { it.isEmpty() } || replacedExistNumbers.contains(hopeNumberAvail.map { it[0] }
                .toString())) arrayOf("-1")
        else printAnswer(hopeNumberAvail)
    }

    private fun printAnswer(hopeNumberAvail: Array<Array<String>>): Array<String> {
        return hopeNumberAvail.fold(listOf("")) { acc, array ->
            acc.flatMap { prefix ->
                array.map { element ->
                    if (prefix.isEmpty()) {
                        element
                    } else {
                        "$prefix-$element"
                    }
                }
            }
        }.toTypedArray()
    }
}