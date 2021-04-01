package com.github.syari.spigot.api.world

import org.bukkit.Location
import org.bukkit.World
import org.bukkit.util.NumberConversions
import org.bukkit.util.Vector
import kotlin.math.sqrt

/**
 * ワールドを考慮せず、座標情報のみを保持できるクラス。
 * @since 1.4.0
 */
data class Coordinate(
    var x: Double,
    var y: Double,
    var z: Double,
    var yaw: Float = 0F,
    var pitch: Float = 0F
) {
    /**
     * X 座標の小数点以下を切り捨てた値を取得する。ブロックの座標を示す。
     * @see Location.getBlockX
     * @since 1.4.0
     */
    val blockX
        get() = Location.locToBlock(x)

    /**
     * Y 座標の小数点以下を切り捨てた値を取得する。ブロックの座標を示す。
     * @see Location.getBlockY
     * @since 1.4.0
     */
    val blockY
        get() = Location.locToBlock(y)

    /**
     * Z 座標の小数点以下を切り捨てた値を取得する。ブロックの座標を示す。
     * @see Location.getBlockX
     * @since 1.4.0
     */
    val blockZ
        get() = Location.locToBlock(z)

    /**
     * 別の座標の値を足す。
     * @param vec 足す座標値
     * @return this
     * @see Location.add
     * @since 1.4.0
     */
    fun add(vec: Coordinate): Coordinate {
        x += vec.x
        y += vec.y
        z += vec.z
        return this
    }

    /**
     * 別の座標の値を足す。
     * @param vec 足す座標値
     * @return this
     * @see Location.add
     * @since 1.4.0
     */
    fun add(vec: Vector): Coordinate {
        x += vec.x
        y += vec.y
        z += vec.z
        return this
    }

    /**
     * 別の座標の値を足す。
     * @param x 足すX座標
     * @param y 足すY座標
     * @param z 足すZ座標
     * @return this
     * @see Location.add
     * @since 1.4.0
     */
    fun add(x: Double, y: Double, z: Double): Coordinate {
        this.x += x
        this.y += y
        this.z += z
        return this
    }

    /**
     * 別の座標の値を引く。
     * @param vec 引く座標値
     * @return this
     * @see Location.subtract
     * @since 1.4.0
     */
    fun subtract(vec: Coordinate): Coordinate {
        x -= vec.x
        y -= vec.y
        z -= vec.z
        return this
    }

    /**
     * 別の座標の値を引く。
     * @param vec 引く座標値
     * @return this
     * @see Location.subtract
     * @since 1.4.0
     */
    fun subtract(vec: Vector): Coordinate {
        x -= vec.x
        y -= vec.y
        z -= vec.z
        return this
    }

    /**
     * 別の座標の値を引く。
     * @param x 引くX座標
     * @param y 引くY座標
     * @param z 引くZ座標
     * @return this
     * @see Location.subtract
     * @since 1.4.0
     */
    fun subtract(x: Double, y: Double, z: Double): Coordinate {
        this.x -= x
        this.y -= y
        this.z -= z
        return this
    }

    /**
     * 座標の大きさ `sqrt(x^2 + y^2 + z^2)` を取得する。
     * @see Location.length
     * @since 1.4.0
     */
    val length
        get() = sqrt(lengthSquared)

    /**
     * 座標の大きさの二乗 `x^2 + y^2 + z^2` を取得する。
     * @see Location.lengthSquared
     * @since 1.4.0
     */
    val lengthSquared
        get() = NumberConversions.square(x) + NumberConversions.square(y) + NumberConversions.square(z)

    /**
     * 別の座標との距離を取得する。
     * @param c 距離を取得する片方の座標
     * @return 距離
     * @see Location.distance
     * @since 1.4.0
     */
    fun distance(c: Coordinate) = sqrt(distanceSquared(c))

    /**
     * 別の座標との距離の二乗を取得する。
     * @param c 距離を取得する片方の座標
     * @return 距離の二乗
     * @see Location.distanceSquared
     * @since 1.4.0
     */
    fun distanceSquared(c: Coordinate) = NumberConversions.square(x - c.x) + NumberConversions.square(y - c.y) + NumberConversions.square(z - c.z)

    /**
     * XYZ 座標を定数倍する。
     * @param m 定数倍
     * @return this
     * @see Location.multiply
     * @since 1.4.0
     */
    fun multiply(m: Double): Coordinate {
        x *= m
        y *= m
        z *= m
        return this
    }

    /**
     * XYZ 座標を 0 にする。
     * @return this
     * @see Location.multiply
     * @since 1.4.0
     */
    fun zero(): Coordinate {
        x = 0.0
        y = 0.0
        z = 0.0
        return this
    }

    /**
     * [Vector] に変換する。
     * @return [Vector]
     * @see Location.toVector
     * @since 1.4.0
     */
    fun toVector() = Vector(x, y, z)

    /**
     * ワールドの情報を加えて、[Location] に変換する。
     * @param world ワールド
     * @return [Location]
     * @since 1.4.0
     */
    fun toLocation(world: World?) = Location(world, x, y, z, yaw, pitch)
}
