package com.github.syari.spigot.api.config.def

import com.github.syari.spigot.api.config.CustomConfig
import org.bukkit.plugin.java.JavaPlugin

/**
 * デフォルト設定として [CustomConfig] に対して処理を実行する。
 * @since 2.2.1
 */
class DefaultConfigResource(val plugin: JavaPlugin, val fileName: String) : DefaultConfig {
    /**
     * デフォルト設定を適用する。
     * @since 2.2.1
     */
    override fun set(config: CustomConfig) {
        plugin.getResource(fileName)?.use { input ->
            config.file.outputStream().use { output ->
                input.copyTo(output)
            }
        }
    }
}
