package com.github.syari.spigot.api.config.def

import com.github.syari.spigot.api.config.CustomConfig
import java.io.File

/**
 * デフォルト設定として [CustomConfig] に対して処理を実行する。
 * @since 2.2.1
 */
class DefaultConfigFile(val file: File) : DefaultConfig {
    /**
     * デフォルト設定を適用する。
     * @since 2.2.1
     */
    override fun set(config: CustomConfig) {
        file.inputStream().let(::DefaultConfigStream).set(config)
    }
}
