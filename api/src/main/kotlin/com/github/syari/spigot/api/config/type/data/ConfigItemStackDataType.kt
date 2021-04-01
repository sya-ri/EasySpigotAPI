package com.github.syari.spigot.api.config.type.data

import com.github.syari.spigot.api.config.CustomConfig
import com.github.syari.spigot.api.config.converter.ConfigItemConverter
import com.github.syari.spigot.api.config.type.ConfigDataType
import org.bukkit.inventory.ItemStack

/**
 * アイテムスタック。[ConfigItemConverter] を使用して保存・読込を行う。
 * @see ConfigDataType.ItemStack
 * @since 1.7.0
 */
class ConfigItemStackDataType(private val itemConverter: ConfigItemConverter) : ConfigDataType<ItemStack> {
    override val typeName = "ItemStack"

    /**
     * コンフィグから値を取得する。
     * @param config [CustomConfig]
     * @param path コンフィグパス
     * @param notFoundError 存在しないデータの場合にエラーを出す
     * @return 取得した値
     * @since 1.7.0
     */
    override fun get(
        config: CustomConfig,
        path: String,
        notFoundError: Boolean
    ): ItemStack? {
        val line = config.get(path, ConfigDataType.String, notFoundError) ?: return config.nullError(path, "String").run { null }
        return itemConverter.stringToItem(config, path, line)
    }

    /**
     * コンフィグの値を変更する。
     * @param config [CustomConfig]
     * @param path コンフィグパス
     * @param value 設定する値
     * @since 1.7.0
     */
    override fun set(
        config: CustomConfig,
        path: String,
        value: ItemStack?
    ) {
        config.set(path, ConfigDataType.String, value?.let(itemConverter::itemToString))
    }
}
