package com.github.syari.spigot.api.config.def

import com.github.syari.spigot.api.config.CustomConfig
import java.io.InputStream

/**
 * デフォルト設定としてストリームを [CustomConfig] にコピーする。
 * @since 2.2.1
 */
class DefaultConfigStream(val inputStream: InputStream) : DefaultConfig {
    /**
     * デフォルト設定を適用する。
     * @param config 適用するコンフィグ
     * @since 2.2.1
     */
    override fun set(config: CustomConfig) {
        inputStream.use { input ->
            config.file.outputStream().use { output ->
                input.copyTo(output)
            }
        }
        config.reload()
    }
}
