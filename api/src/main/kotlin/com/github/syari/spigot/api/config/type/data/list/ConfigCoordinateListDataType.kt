package com.github.syari.spigot.api.config.type.data.list

import com.github.syari.spigot.api.config.CustomConfig
import com.github.syari.spigot.api.config.type.ConfigDataType
import com.github.syari.spigot.api.config.type.data.ConfigCoordinateDataType.coordinateToString
import com.github.syari.spigot.api.config.type.data.ConfigCoordinateDataType.stringToCoordinate
import com.github.syari.spigot.api.world.Coordinate

/**
 * 座標のリスト。
 * @see ConfigDataType.CoordinateList
 * @since 1.4.0
 */
object ConfigCoordinateListDataType : ConfigDataType<List<Coordinate>> {
    /**
     * データ型の名前。
     * @since 1.4.0
     */
    override val typeName = "Coordinate"

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
    ): List<Coordinate> {
        return config.get(path, ConfigDataType.StringList, notFoundError)?.mapNotNull {
            stringToCoordinate(config, "$path.$it", it) ?: config.nullError("$path.$it", typeName).run { null }
        }.orEmpty()
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
        value: List<Coordinate>?
    ) {
        config.set(path, ConfigDataType.StringList, value?.map(::coordinateToString))
    }
}
