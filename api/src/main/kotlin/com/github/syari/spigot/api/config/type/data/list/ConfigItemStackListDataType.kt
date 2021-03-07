package com.github.syari.spigot.api.config.type.data.list

import com.github.syari.spigot.api.config.CustomConfig
import com.github.syari.spigot.api.config.converter.ConfigItemConverter
import com.github.syari.spigot.api.config.type.ConfigDataType
import org.bukkit.inventory.ItemStack

/**
 * アイテムスタックのリスト。[ConfigItemConverter] を使用して保存・読込を行う。
 * @see ConfigDataType.ItemStackList
 * @since 1.7.0
 */
class ConfigItemStackListDataType(private val itemConverter: ConfigItemConverter) : ConfigDataType<List<ItemStack>> {
    override val typeName = "List<ItemStack>"

    /**
     * @param config [CustomConfig]
     * @param path コンフィグパス
     * @param notFoundError 存在しないデータの場合にエラーを出す
     * @since 1.7.0
     */
    override fun get(
        config: CustomConfig,
        path: String,
        notFoundError: Boolean
    ): List<ItemStack> {
        return mutableListOf<ItemStack>().apply {
            config.get(path, ConfigDataType.StringList, notFoundError)?.forEach {
                itemConverter.stringToItem(config, path, it)?.let(::add)
            }
        }
    }

    /**
     * @param config [CustomConfig]
     * @param path コンフィグパス
     * @param value 設定する値
     * @since 1.7.0
     */
    override fun set(
        config: CustomConfig,
        path: String,
        value: List<ItemStack>?
    ) {
        config.set(path, ConfigDataType.StringList, value?.mapNotNull(itemConverter::itemToString))
    }
}
