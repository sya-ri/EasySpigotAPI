package com.github.syari.spigot.api

import org.bukkit.plugin.java.JavaPlugin
import java.util.logging.Logger

/**
 * メインクラス。
 * @since 1.0.0
 */
@Suppress("unused")
class EasySpigotAPI : JavaPlugin() {
    companion object {
        /**
         * 内部で使用するロガー
         * @since 2.3.0
         */
        internal val logger = Logger.getLogger("EasySpigotAPI(Internal)")
    }

    override fun onEnable() {
        EasySpigotAPIOption.useCustomInventory(this)
    }
}
