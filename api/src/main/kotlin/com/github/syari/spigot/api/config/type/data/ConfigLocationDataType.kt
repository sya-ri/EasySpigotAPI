package com.github.syari.spigot.api.config.type.data

import com.github.syari.spigot.api.config.CustomConfig
import com.github.syari.spigot.api.config.type.ConfigDataType
import org.bukkit.Bukkit
import org.bukkit.Location

/**
 * [ConfigDataType.Location]
 * @since 1.3.0
 */
object ConfigLocationDataType : ConfigDataType<Location> {
    /**
     * データ型の名前
     * @since 1.3.0
     */
    override val typeName = "Location"

    /**
     * @param config [CustomConfig]
     * @param path コンフィグパス
     * @param notFoundError 存在しないデータの場合にエラーを出す
     * @since 1.3.0
     */
    override fun get(
        config: CustomConfig,
        path: String,
        notFoundError: Boolean
    ): Location? {
        return config.get(path, ConfigDataType.String, notFoundError)?.let {
            stringToLocation(config, path, it) ?: config.nullError(path, typeName).run { null }
        }
    }

    /**
     * @param config [CustomConfig]
     * @param path コンフィグパス
     * @param value 設定する値
     * @since 1.3.0
     */
    override fun set(
        config: CustomConfig,
        path: String,
        value: Location?
    ) {
        config.set(path, ConfigDataType.String, value?.let(::locationToString))
    }

    /**
     * [String] を [Location] に変換します
     * @since 1.3.0
     */
    fun stringToLocation(config: CustomConfig, path: String, value: String): Location? {
        val element = value.split(",\\s*".toRegex())
        when (val size = element.size) {
            4, 6 -> {
                val world = Bukkit.getWorld(element[0])
                if (world == null) {
                    config.nullError(path, "World(${element[0]})")
                    return null
                }
                try {
                    val x = element[1].toDouble()
                    val y = element[2].toDouble()
                    val z = element[3].toDouble()
                    if (size == 4) return Location(world, x, y, z)
                    val yaw = element[4].toFloat()
                    val pitch = element[5].toFloat()
                    return Location(world, x, y, z, yaw, pitch)
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
     * [Location] を [String] に変換します
     * @since 1.3.0
     */
    fun locationToString(value: Location): String {
        return if (value.yaw == 0F && value.pitch == 0F) {
            "${value.world?.name}, ${value.x}, ${value.y}, ${value.z}"
        } else {
            "${value.world?.name}, ${value.x}, ${value.y}, ${value.z}, ${value.yaw}, ${value.pitch}"
        }
    }
}
