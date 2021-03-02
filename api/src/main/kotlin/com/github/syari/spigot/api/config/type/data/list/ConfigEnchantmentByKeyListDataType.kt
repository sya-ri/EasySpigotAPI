package com.github.syari.spigot.api.config.type.data.list

import com.github.syari.spigot.api.config.CustomConfig
import com.github.syari.spigot.api.config.type.ConfigDataType
import org.bukkit.NamespacedKey
import org.bukkit.enchantments.Enchantment

/**
 * [ConfigDataType.EnchantmentByKeyList]
 * @since 1.5.1
 */
object ConfigEnchantmentByKeyListDataType : ConfigDataType<List<Enchantment>> {
    /**
     * データ型の名前
     * @since 1.5.1
     */
    override val typeName = "List<Enchantment(Key)>"

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
            Enchantment.getByKey(NamespacedKey.minecraft(it.toLowerCase())) ?: config.nullError("$path.$it", typeName).run { null }
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
        config.set(path, ConfigDataType.StringList, value?.map { it.key.key })
    }
}
