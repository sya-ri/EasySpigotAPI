package com.github.syari.spigot.api.config.type.data

import com.github.syari.spigot.api.config.CustomConfig
import com.github.syari.spigot.api.config.type.ConfigDataType
import org.bukkit.Bukkit
import org.bukkit.World

/**
 * ワールド。
 * @see ConfigDataType.World
 * @since 1.3.0
 */
object ConfigWorldDataType : ConfigDataType<World> {
    /**
     * データ型の名前。
     * @since 1.3.0
     */
    override val typeName = "World"

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
    ): World? {
        return config.get(path, ConfigDataType.String, notFoundError)?.let {
            Bukkit.getWorld(it) ?: config.nullError(path, typeName).run { null }
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
        value: World?
    ) {
        config.set(path, ConfigDataType.String, value?.name)
    }
}
