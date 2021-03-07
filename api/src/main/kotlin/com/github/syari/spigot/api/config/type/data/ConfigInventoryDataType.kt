package com.github.syari.spigot.api.config.type.data

import com.github.syari.spigot.api.config.CustomConfig
import com.github.syari.spigot.api.config.converter.ConfigItemConverter
import com.github.syari.spigot.api.config.type.ConfigDataType
import com.github.syari.spigot.api.config.type.ConfigSectionType
import org.bukkit.inventory.ItemStack

/**
 * インベントリ。[ConfigItemConverter] を使用して保存・読込を行う。
 * @see ConfigDataType.Inventory
 * @since 1.7.0
 */
class ConfigInventoryDataType(private val itemConverter: ConfigItemConverter) : ConfigDataType<Map<Int, ItemStack>> {
    override val typeName = "Inventory"

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
    ): Map<Int, ItemStack> {
        val itemDataType = ConfigDataType.ItemStack(itemConverter)
        return mutableMapOf<Int, ItemStack>().apply {
            config.section(path, ConfigSectionType.Int, notFoundError)?.forEach { slot ->
                config.get("$path.$slot", itemDataType)?.let { put(slot, it) }
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
        value: Map<Int, ItemStack>?
    ) {
        config.setNull(path)
        value?.forEach { (slot, item) ->
            config.set("$path.$slot", ConfigDataType.String, itemConverter.itemToString(item))
        }
    }
}
