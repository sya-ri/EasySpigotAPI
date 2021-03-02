package com.github.syari.spigot.api.config.type.data.list

import com.github.syari.spigot.api.config.CustomConfig
import com.github.syari.spigot.api.config.type.ConfigDataType
import org.bukkit.enchantments.Enchantment

/**
 * [ConfigDataType.EnchantmentByNameList]
 * @since 1.5.1
 */
object ConfigEnchantmentByNameListDataType : ConfigDataType<List<Enchantment>> {
    /**
     * データ型の名前
     * @since 1.5.1
     */
    override val typeName = "List<Enchantment(Name)>"

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
            @Suppress("DEPRECATION")
            Enchantment.getByName(it.toUpperCase()) ?: config.nullError("$path.$it", typeName).run { null }
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
        @Suppress("DEPRECATION")
        config.set(path, ConfigDataType.StringList, value?.map(Enchantment::getName))
    }
}
