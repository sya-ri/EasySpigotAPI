package com.github.syari.spigot.api.config.def

import com.github.syari.spigot.api.config.CustomConfig
import org.bukkit.plugin.java.JavaPlugin

/**
 * デフォルト設定としてリソースファイルを [CustomConfig] にコピーする。
 * @param plugin 参照するリソースファイルが存在するプラグイン
 * @param fileName リソースファイルのパス
 * @since 2.2.1
 */
class DefaultConfigResource(val plugin: JavaPlugin, val fileName: String) : DefaultConfig {
    /**
     * デフォルト設定を適用する。
     * @param config 適用するコンフィグ
     * @since 2.2.1
     */
    override fun set(config: CustomConfig) {
        plugin.getResource(fileName)?.let(::DefaultConfigStream)?.set(config)
    }
}
