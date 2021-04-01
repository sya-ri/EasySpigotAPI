package com.github.syari.spigot.api.config.def

import com.github.syari.spigot.api.config.CustomConfig
import java.io.File

/**
 * デフォルト設定として別のファイルを [CustomConfig] にコピーする。
 * @param file コピー元のファイル
 * @since 2.2.1
 */
class DefaultConfigFile(val file: File) : DefaultConfig {
    /**
     * デフォルト設定を適用する。
     * @param config 適用するコンフィグ
     * @since 2.2.1
     */
    override fun set(config: CustomConfig) {
        file.inputStream().let(::DefaultConfigStream).set(config)
    }
}
