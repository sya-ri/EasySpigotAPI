package com.github.syari.spigot.api.config.type.data

import com.github.syari.spigot.api.config.CustomConfig
import com.github.syari.spigot.api.config.type.ConfigDataType
import org.bukkit.util.Vector

/**
 * ベクトル。X, Y, Z の値を持つ。
 * @see ConfigDataType.Vector
 * @since 1.4.0
 */
object ConfigVectorDataType : ConfigDataType<Vector> {
    /**
     * データ型の名前。
     * @since 1.4.0
     */
    override val typeName = "Vector"

    /**
     * コンフィグから値を取得する。
     * @param config [CustomConfig]
     * @param path コンフィグパス
     * @param notFoundError 存在しないデータの場合にエラーを出す
     * @return 取得した値
     * @since 1.4.0
     */
    override fun get(
        config: CustomConfig,
        path: String,
        notFoundError: Boolean
    ): Vector? {
        val line = config.get(path, ConfigDataType.String, notFoundError) ?: return null
        return stringToVector(config, path, line) ?: config.nullError(path, typeName).run { null }
    }

    /**
     * コンフィグの値を変更する。
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
        config.set(path, ConfigDataType.String, value?.let(::vectorToString))
    }

    /**
     * [String] を [Vector] に変換する。
     * @since 1.4.0
     */
    fun stringToVector(config: CustomConfig, path: String, value: String): Vector? {
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
     * [Vector] を [String] に変換する。
     * @since 1.4.0
     */
    fun vectorToString(value: Vector): String {
        return "${value.x}, ${value.y}, ${value.z}"
    }
}
