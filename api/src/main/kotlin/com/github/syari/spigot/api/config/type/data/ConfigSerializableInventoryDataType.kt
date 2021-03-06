@file:Suppress("DEPRECATION")

package com.github.syari.spigot.api.config.type.data

import com.github.syari.spigot.api.config.CustomConfig
import com.github.syari.spigot.api.config.type.ConfigDataType
import org.bukkit.inventory.ItemStack

/**
 * インベントリ。シリアライズを使用して保存・読込を行う。
 * @see ConfigDataType.SerializableInventory
 * @since 1.6.0
 */
object ConfigSerializableInventoryDataType : ConfigDataType<Array<ItemStack?>> {
    /**
     * データ型の名前。
     * @since 1.6.0
     */
    override val typeName = "Inventory(Serialize)"

    /**
     * @param config [CustomConfig]
     * @param path コンフィグパス
     * @param notFoundError 存在しないデータの場合にエラーを出す
     * @since 1.6.0
     */
    override fun get(
        config: CustomConfig,
        path: String,
        notFoundError: Boolean
    ): Array<ItemStack?>? {
        return config.getUnsafe<List<ItemStack?>>(path, typeName, notFoundError)?.toTypedArray()
    }

    /**
     * @param path コンフィグパス
     * @param value 設定する値
     * @since 1.6.0
     */
    override fun set(
        config: CustomConfig,
        path: String,
        value: Array<ItemStack?>?
    ) {
        config.setUnsafe(path, value)
    }
}
