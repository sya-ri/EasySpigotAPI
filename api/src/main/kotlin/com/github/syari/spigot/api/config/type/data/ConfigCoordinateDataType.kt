package com.github.syari.spigot.api.config.type.data

import com.github.syari.spigot.api.config.CustomConfig
import com.github.syari.spigot.api.config.type.ConfigDataType
import com.github.syari.spigot.api.util.world.Coordinate

/**
 * [ConfigDataType.Coordinate]
 * @since 1.4.0
 */
object ConfigCoordinateDataType : ConfigDataType<Coordinate> {
    /**
     * データ型の名前
     * @since 1.4.0
     */
    override val typeName = "Coordinate"

    /**
     * @param config [CustomConfig]
     * @param path コンフィグパス
     * @param notFoundError 存在しないデータの場合にエラーを出す
     * @since 1.4.0
     */
    override fun get(
        config: CustomConfig,
        path: String,
        notFoundError: Boolean
    ): Coordinate? {
        val line = config.get(path, ConfigDataType.String, notFoundError) ?: return null
        return stringToCoordinate(config, path, line)
    }

    /**
     * @param config [CustomConfig]
     * @param path コンフィグパス
     * @param value 設定する値
     * @since 1.4.0
     */
    override fun set(
        config: CustomConfig,
        path: String,
        value: Coordinate?
    ) {
        config.set(path, ConfigDataType.String, value?.let(::coordinateToString))
    }

    /**
     * [String] を [Coordinate] に変換します
     * @since 1.4.0
     */
    fun stringToCoordinate(config: CustomConfig, path: String, value: String): Coordinate? {
        val element = value.split(",\\s*".toRegex())
        when (val size = element.size) {
            3, 5 -> {
                try {
                    val x = element[0].toDouble()
                    val y = element[1].toDouble()
                    val z = element[2].toDouble()
                    if (size == 3) return Coordinate(x, y, z)
                    val yaw = element[3].toFloat()
                    val pitch = element[4].toFloat()
                    return Coordinate(x, y, z, yaw, pitch)
                } catch (ex: NumberFormatException) {
                    config.formatMismatchError(path)
                    return null
                }
            }
            else -> {
                config.formatMismatchError(path)
                return null
            }
        }
    }

    /**
     * [Coordinate] を [String] に変換します
     * @since 1.4.0
     */
    fun coordinateToString(value: Coordinate): String {
        return if (value.yaw == 0F && value.pitch == 0F) {
            "${value.x}, ${value.y}, ${value.z}"
        } else {
            "${value.x}, ${value.y}, ${value.z}, ${value.yaw}, ${value.pitch}"
        }
    }
}
