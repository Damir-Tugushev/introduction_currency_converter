package io.github.damirtugushev.introduction.viewmodel

fun Double.round(decimals: Int): Double {
    var multiplier = 1
    repeat(decimals) { multiplier *= 10 }
    return kotlin.math.round(this * multiplier) / multiplier
}
