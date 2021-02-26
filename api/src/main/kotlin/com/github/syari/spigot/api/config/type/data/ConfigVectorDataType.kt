package com.github.syari.spigot.api.config.type.data

import com.github.syari.spigot.api.config.CustomConfig
import com.github.syari.spigot.api.config.type.ConfigDataType
import org.bukkit.util.Vector

/**
 * [ConfigDataType.Vector]
 * @since 1.4.0
 */
object ConfigVectorDataType : ConfigDataType<Vector> {
    /**
     * データ型の名前
     * @since 1.4.0
     */
    override val typeName = "Vector"

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
    ): Vector? {
        val line = config.get(path, ConfigDataType.String, notFoundError) ?: return null
        return stringToVector(config, path, line)
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
        value: Vector?
    ) {
        if (value != null) {
            config.set(path, ConfigDataType.String, vectorToString(value))
        } else {
            config.setUnsafe(path, null)
        }
    }

    /**
     * [String] を [Vector] に変換します
     * @since 1.4.0
     */
    internal fun stringToVector(config: CustomConfig, path: String, value: String): Vector? {
        val element = value.split(",\\s*".toRegex())
        when (element.size) {
            3 -> {
                return try {
                    val x = element[0].toDouble()
                    val y = element[1].toDouble()
                    val z = element[2].toDouble()
                    Vector(x, y, z)
                } catch (ex: NumberFormatException) {
                    config.formatMismatchError(path)
                    null
                }
            }
            else -> {
                config.formatMismatchError(path)
                return null
            }
        }
    }

    /**
     * [Vector] を [String] に変換します
     * @since 1.4.0
     */
    internal fun vectorToString(value: Vector): String {
        return "${value.x}, ${value.y}, ${value.z}"
    }
}
