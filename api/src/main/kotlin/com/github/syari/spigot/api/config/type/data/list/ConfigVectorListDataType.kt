package com.github.syari.spigot.api.config.type.data.list

import com.github.syari.spigot.api.config.CustomConfig
import com.github.syari.spigot.api.config.type.ConfigDataType
import com.github.syari.spigot.api.config.type.data.ConfigVectorDataType.stringToVector
import com.github.syari.spigot.api.config.type.data.ConfigVectorDataType.vectorToString
import org.bukkit.util.Vector

/**
 * [ConfigDataType.VectorList]
 * @since 1.4.0
 */
object ConfigVectorListDataType : ConfigDataType<List<Vector>> {
    /**
     * データ型の名前。
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
    ): List<Vector> {
        return config.get(path, ConfigDataType.StringList, notFoundError)?.mapNotNull {
            stringToVector(config, "$path.$it", it) ?: config.nullError("$path.$it", typeName).run { null }
        }.orEmpty()
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
        value: List<Vector>?
    ) {
        config.set(path, ConfigDataType.StringList, value?.map(::vectorToString))
    }
}
