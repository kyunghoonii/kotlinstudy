package com.example.demo.util

class CommonUtil {
    object LogBanner {
        private const val TOTAL_WIDTH = 40  // 원하는 전체 길이

        fun banner(title: String): String {
            // 앞뒤 공백을 하나씩 두고, 나머지는 '=' 로 채우기
            val text = " $title "
            val padSize = (TOTAL_WIDTH - text.length) / 2
            val padded = "=".repeat(padSize) + text + "=".repeat(TOTAL_WIDTH - padSize - text.length)
            return padded
        }
    }
}