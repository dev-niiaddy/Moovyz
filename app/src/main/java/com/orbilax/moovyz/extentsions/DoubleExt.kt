package com.orbilax.moovyz.extentsions

// MARK: - Double
fun Double.toPercentage(): Int {
    return (this * 10).toInt()
}