package com.github.syari.spigot.api.config.type.data.list

import com.github.syari.spigot.api.config.CustomConfig
import com.github.syari.spigot.api.config.type.ConfigDataType
import com.github.syari.spigot.api.config.type.data.ConfigLocationDataType
import org.bukkit.Location

/**
 * [ConfigDataType.LocationList]
 * @since 1.3.0
 */
object ConfigLocationListDataType : ConfigDataType<List<Location>> {
    /**
     * データ型の名前
     * @since 1.3.0
     */
    override val typeName = "List<Location>"

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
    ): List<Location> {
        return mutableListOf<Location>().apply {
            config.get(path, ConfigDataType.StringList, notFoundError)?.forEach {
                ConfigLocationDataType.stringToLocation(config, "$path.$it", it)?.let(::add)
            }
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
        value: List<Location>?
    ) {
        config.set(path, ConfigDataType.StringList, value?.map(ConfigLocationDataType::locationToString))
    }
}
