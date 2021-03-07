package com.github.syari.spigot.api.config.converter

import com.github.syari.spigot.api.config.CustomConfig
import org.bukkit.inventory.ItemStack

/**
 * 文字列と [ItemStack] の変換を行う。
 * @since 1.7.0
 */
interface ConfigItemConverter {
    /**
     * 文字列を [ItemStack] に変換する。
     * @since 1.7.0
     */
    fun stringToItem(config: CustomConfig, path: String, line: String): ItemStack?

    /**
     * [ItemStack] を [String] に変換する。
     * @since 1.7.0
     */
    fun itemToString(itemStack: ItemStack): String?
}
