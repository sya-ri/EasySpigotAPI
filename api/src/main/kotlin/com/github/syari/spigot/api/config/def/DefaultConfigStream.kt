package com.github.syari.spigot.api.config.def

import com.github.syari.spigot.api.config.CustomConfig
import java.io.InputStream

/**
 * デフォルト設定として [CustomConfig] に対して処理を実行する。
 * @since 2.2.1
 */
class DefaultConfigStream(val inputStream: InputStream) : DefaultConfig {
    /**
     * デフォルト設定を適用する。
     * @since 2.2.1
     */
    override fun set(config: CustomConfig) {
        inputStream.use { input ->
            config.file.outputStream().use { output ->
                input.copyTo(output)
            }
        }
    }
}
