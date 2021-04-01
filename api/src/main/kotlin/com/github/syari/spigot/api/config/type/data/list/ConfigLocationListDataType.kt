package com.github.syari.spigot.api.config.type.data.list

import com.github.syari.spigot.api.config.CustomConfig
import com.github.syari.spigot.api.config.type.ConfigDataType
import com.github.syari.spigot.api.config.type.data.ConfigLocationDataType.locationToString
import com.github.syari.spigot.api.config.type.data.ConfigLocationDataType.stringToLocation
import org.bukkit.Location

/**
 * ワールド座標のリスト。
 * @see ConfigDataType.LocationList
 * @since 1.3.0
 */
object ConfigLocationListDataType : ConfigDataType<List<Location>> {
    /**
     * データ型の名前。
     * @since 1.3.0
     */
    override val typeName = "Location"

    /**
     * コンフィグから値を取得する。
     * @param config [CustomConfig]
     * @param path コンフィグパス
     * @param notFoundError 存在しないデータの場合にエラーを出す
     * @return 取得した値
     * @since 1.3.0
     */
    override fun get(
        config: CustomConfig,
        path: String,
        notFoundError: Boolean
    ): List<Location> {
        return config.get(path, ConfigDataType.StringList, notFoundError)?.mapNotNull {
            stringToLocation(config, "$path.$it", it) ?: config.nullError("$path.$it", typeName).run { null }
        }.orEmpty()
    }

    /**
     * コンフィグの値を変更する。
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
        config.set(path, ConfigDataType.StringList, value?.map(::locationToString))
    }
}
