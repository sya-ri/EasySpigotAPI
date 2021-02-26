package com.github.syari.spigot.api.util.world

import org.bukkit.Location
import org.bukkit.World
import org.bukkit.util.Vector

/**
 * 領域を表す。
 * @since 1.4.0
 */
class Region(
    val world: World,
    pos1: Vector,
    pos2: Vector
) {
    /**
     * 領域の片方の頂点
     * @since 1.4.0
     */
    val pos1: Vector

    /**
     * 領域の片方の頂点
     * @since 1.4.0
     */
    val pos2: Vector

    init {
        val rangeX = range(pos1.x, pos2.x)
        val rangeY = range(pos1.y, pos2.y)
        val rangeZ = range(pos1.z, pos2.z)
        this.pos1 = Vector(rangeX.first, rangeY.first, rangeZ.first)
        this.pos2 = Vector(rangeX.second, rangeY.second, rangeZ.second)
    }

    /**
     * 領域内の座標かどうかを取得する。
     * @since 1.4.0
     */
    fun inRegion(o: Location): Boolean {
        if (o.world != world) return false
        if (o.x !in pos1.x..pos2.x) return false
        if (o.y !in pos1.y..pos2.y) return false
        if (o.z !in pos1.z..pos2.z) return false
        return true
    }

    companion object {
        /**
         * 値の小さい方を `first` に、大きい方を `second` に入れた範囲に変換する。
         * @since 1.4.0
         */
        private fun range(v1: Double, v2: Double): Pair<Double, Double> {
            return if (v1 < v2) {
                v1 to v2
            } else {
                v2 to v1
            }
        }
    }
}
