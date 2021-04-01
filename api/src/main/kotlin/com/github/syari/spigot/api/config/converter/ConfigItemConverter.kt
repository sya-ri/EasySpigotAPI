package com.github.syari.spigot.api.config.converter

import com.github.syari.spigot.api.config.CustomConfig
import org.bukkit.inventory.ItemStack

/**
 * [String] と [ItemStack] の変換を行う。
 * @since 1.7.0
 */
interface ConfigItemConverter {
    /**
     * [String] を [ItemStack] に変換する。
     * @param config 文字列の取得に使ったコンフィグ
     * @param path 文字列の取得に使ったパス
     * @param line 変換する文字列
     * @return 変換後のアイテム
     * @since 1.7.0
     */
    fun stringToItem(config: CustomConfig, path: String, line: String): ItemStack?

    /**
     * [ItemStack] を [String] に変換する。
     * @param itemStack 変換するアイテム
     * @return 変換後の文字列
     * @since 1.7.0
     */
    fun itemToString(itemStack: ItemStack): String?
}
