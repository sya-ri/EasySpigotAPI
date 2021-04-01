package com.github.syari.spigot.api.config.type.data.list

import com.github.syari.spigot.api.config.CustomConfig
import com.github.syari.spigot.api.config.type.ConfigDataType
import org.bukkit.Bukkit
import org.bukkit.World

/**
 * ワールドのリスト。
 * @see ConfigDataType.WorldList
 * @since 1.3.0
 */
object ConfigWorldListDataType : ConfigDataType<List<World>> {
    /**
     * データ型の名前。
     * @since 1.3.0
     */
    override val typeName = "World"

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
    ): List<World> {
        return config.get(path, ConfigDataType.StringList, notFoundError)?.mapNotNull {
            Bukkit.getWorld(it) ?: config.nullError("$path.$it", typeName).run { null }
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
        value: List<World>?
    ) {
        config.set(path, ConfigDataType.StringList, value?.map(World::getName))
    }
}
