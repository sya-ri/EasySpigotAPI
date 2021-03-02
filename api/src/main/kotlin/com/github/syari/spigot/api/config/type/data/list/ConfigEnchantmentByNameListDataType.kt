package com.github.syari.spigot.api.config.type.data.list

import com.github.syari.spigot.api.config.CustomConfig
import com.github.syari.spigot.api.config.type.ConfigDataType
import com.github.syari.spigot.api.config.type.data.ConfigEnchantmentByNameDataType.enchantmentToString
import com.github.syari.spigot.api.config.type.data.ConfigEnchantmentByNameDataType.stringToEnchantment
import org.bukkit.enchantments.Enchantment

/**
 * [ConfigDataType.EnchantmentByNameList]
 * @since 1.5.1
 */
object ConfigEnchantmentByNameListDataType : ConfigDataType<List<Enchantment>> {
    /**
     * データ型の名前。
     * @since 1.5.1
     */
    override val typeName = "Enchantment(Name)"

    /**
     * @param config [CustomConfig]
     * @param path コンフィグパス
     * @param notFoundError 存在しないデータの場合にエラーを出す
     * @since 1.5.1
     */
    override fun get(
        config: CustomConfig,
        path: String,
        notFoundError: Boolean
    ): List<Enchantment> {
        return config.get(path, ConfigDataType.StringList, notFoundError)?.mapNotNull {
            stringToEnchantment(it) ?: config.nullError("$path.$it", typeName).run { null }
        }.orEmpty()
    }

    /**
     * @param config [CustomConfig]
     * @param path コンフィグパス
     * @param value 設定する値
     * @since 1.5.1
     */
    override fun set(
        config: CustomConfig,
        path: String,
        value: List<Enchantment>?
    ) {
        config.set(path, ConfigDataType.StringList, value?.map(::enchantmentToString))
    }
}
