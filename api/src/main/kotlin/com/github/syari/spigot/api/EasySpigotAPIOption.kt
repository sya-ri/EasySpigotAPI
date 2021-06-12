package com.github.syari.spigot.api

import com.github.syari.spigot.api.inventory.CustomInventory
import org.bukkit.plugin.java.JavaPlugin

/**
 * EasySpigotAPI の設定
 * @since 2.3.3
 */
object EasySpigotAPIOption {
    /**
     * [CustomInventory] が有効化されているか取得します
     * @since 2.3.3
     */
    internal var isEnableCustomInventory = false
        private set

    /**
     * [CustomInventory] を使用します。
     * @param plugin イベント呼び出しに使うプラグイン
     * @since 2.3.3
     */
    fun useCustomInventory(plugin: JavaPlugin) {
        if (isEnableCustomInventory) return
        isEnableCustomInventory = true
        EasySpigotAPI.logger.info("CustomInventory is now available")
        CustomInventory.onEnableInternal(plugin)
    }
}
