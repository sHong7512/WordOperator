package com.shong.wordoperator

data class InitialStr(
    val index: Int,
    val initalWord: String
)

class WordOperator {
    private val TAG = this::class.java.simpleName + "_sHong"
    // 초성 : 19자
    val initialChs = arrayOf(
        "ㄱ", "ㄲ", "ㄴ", "ㄷ", "ㄸ",
        "ㄹ", "ㅁ", "ㅂ", "ㅃ", "ㅅ",
        "ㅆ", "ㅇ", "ㅈ", "ㅉ", "ㅊ",
        "ㅋ", "ㅌ", "ㅍ", "ㅎ"
    )
    // 중성 : 21자
    val medialChs = arrayOf(
        "ㅏ", "ㅐ", "ㅑ", "ㅒ", "ㅓ",
        "ㅔ", "ㅕ", "ㅖ", "ㅗ", "ㅘ",
        "ㅙ", "ㅚ", "ㅛ", "ㅜ", "ㅝ",
        "ㅞ", "ㅟ", "ㅠ", "ㅡ", "ㅢ",
        "ㅣ"
    )
    // 종성 : 없는 경우(공백) 포함하여 28자
    val finalChs = arrayOf(
        " ", "ㄱ", "ㄲ", "ㄳ", "ㄴ",
        "ㄵ", "ㄶ", "ㄷ", "ㄹ", "ㄺ",
        "ㄻ", "ㄼ", "ㄽ", "ㄾ", "ㄿ",
        "ㅀ", "ㅁ", "ㅂ", "ㅄ", "ㅅ",
        "ㅆ", "ㅇ", "ㅈ", "ㅊ", "ㅋ",
        "ㅌ", "ㅍ", "ㅎ"
    )

    fun getInitial(str: String): InitialStr {
        if (str.length < 1) return InitialStr(-1, "")

        val chName = str[0]
        return if (chName.code >= 0xAC00 && chName.code <= 0xD7A3) {  // 0xAC00(가) ~ 0xD7A3(힣)
            val uniVal = chName.code - 0xAC00
            val index = uniVal / (21 * 28)
            InitialStr(index, initialChs[index])
        } else {
            InitialStr(-1, "" + chName)
        }
    }

    // 중성 21자
    fun getMedial(str: String): String {
        if (str.length < 1) return ""

        val chName = str[0]
        return if (chName.code >= 0xAC00 && chName.code <= 0xD7A3) {  // 0xAC00(가) ~ 0xD7A3(힣)
            val uniVal = chName.code - 0xAC00
            val index = uniVal % (28 * 21) / 28
            medialChs[index]
        } else {
            "" + chName
        }
    }

    fun getInitialSound(str: String): String {
        if (str.length < 1) return ""

        val chName = str[0]
        return if (chName.code >= 0xAC00 && chName.code <= 0xD7A3) {  // 0xAC00(가) ~ 0xD7A3(힣)
            val uniVal = chName.code - 0xAC00
            val index = uniVal % 28
            finalChs[index]
        } else {
            "" + chName
        }
    }
}