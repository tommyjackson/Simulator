package com.tjackapps.simulator.extensions

inline fun <T> List<T>.nestedForEach(block: (T, T) -> Unit) {
    for (i in this.indices) {
        for (j in i + 1 until this.size) {
            block(this[i], this[j])
        }
    }
}

inline fun <T> List<T>.nestedForEachFirstIndexed(block: (Int, Int, T, T) -> Unit) {
    for (i in this.indices) {
        for (j in i + 1 until this.size) {
            block(i, j, this[i], this[j])
        }
    }
}