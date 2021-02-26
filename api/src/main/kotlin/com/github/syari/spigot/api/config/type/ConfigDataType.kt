package com.github.syari.spigot.api.config.type

import com.github.syari.spigot.api.config.CustomConfig
import com.github.syari.spigot.api.config.type.data.ConfigBooleanDataType
import com.github.syari.spigot.api.config.type.data.ConfigCoordinateDataType
import com.github.syari.spigot.api.config.type.data.ConfigDateDataType
import com.github.syari.spigot.api.config.type.data.ConfigDoubleDataType
import com.github.syari.spigot.api.config.type.data.ConfigFloatDataType
import com.github.syari.spigot.api.config.type.data.ConfigIntDataType
import com.github.syari.spigot.api.config.type.data.ConfigLocationDataType
import com.github.syari.spigot.api.config.type.data.ConfigLongDataType
import com.github.syari.spigot.api.config.type.data.ConfigMaterialDataType
import com.github.syari.spigot.api.config.type.data.ConfigNumberDataType
import com.github.syari.spigot.api.config.type.data.ConfigStringDataType
import com.github.syari.spigot.api.config.type.data.ConfigUUIDDataType
import com.github.syari.spigot.api.config.type.data.ConfigWorldDataType
import com.github.syari.spigot.api.config.type.data.list.ConfigCoordinateListDataType
import com.github.syari.spigot.api.config.type.data.list.ConfigLocationListDataType
import com.github.syari.spigot.api.config.type.data.list.ConfigMaterialListDataType
import com.github.syari.spigot.api.config.type.data.list.ConfigStringListDataType
import com.github.syari.spigot.api.config.type.data.list.ConfigUUIDListDataType
import com.github.syari.spigot.api.config.type.data.list.ConfigWorldListDataType

/**
 * コンフィグデータタイプ
 * @param T データ型
 * @since 1.3.0
 */
interface ConfigDataType<T> {
    /**
     * データ型の名前
     * @since 1.3.0
     */
    val typeName: String

    /**
     * @param config [CustomConfig]
     * @param path コンフィグパス
     * @param notFoundError 存在しないデータの場合にエラーを出す
     * @since 1.3.0
     */
    fun get(
        config: CustomConfig,
        path: String,
        notFoundError: Boolean
    ): T?

    /**
     * @param config [CustomConfig]
     * @param path コンフィグパス
     * @param notFoundError 存在しないデータの場合にエラーを出す
     * @param default デフォルト値
     * @since 1.3.0
     */
    fun get(
        config: CustomConfig,
        path: String,
        notFoundError: Boolean,
        default: T
    ): T {
        return get(config, path, notFoundError) ?: default
    }

    /**
     * @param config [CustomConfig]
     * @param path コンフィグパス
     * @param value 設定する値
     * @since 1.3.0
     */
    fun set(
        config: CustomConfig,
        path: String,
        value: T?
    )

    /**
     * 定義済みのコンフィグデータタイプの一覧
     * @since 1.3.0
     */
    companion object {
        val Number = ConfigNumberDataType
        val Int = ConfigIntDataType
        val Long = ConfigLongDataType
        val Float = ConfigFloatDataType
        val Double = ConfigDoubleDataType
        val Boolean = ConfigBooleanDataType
        val String = ConfigStringDataType
        val StringList = ConfigStringListDataType
        val Date = ConfigDateDataType
        val Coordinate = ConfigCoordinateDataType
        val CoordinateList = ConfigCoordinateListDataType
        val Location = ConfigLocationDataType
        val LocationList = ConfigLocationListDataType
        val World = ConfigWorldDataType
        val WorldList = ConfigWorldListDataType
        val Material = ConfigMaterialDataType
        val MaterialList = ConfigMaterialListDataType
        val UUID = ConfigUUIDDataType
        val UUIDList = ConfigUUIDListDataType
    }
}
