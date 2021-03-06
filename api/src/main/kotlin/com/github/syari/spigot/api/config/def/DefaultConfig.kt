package com.github.syari.spigot.api.config.def

import com.github.syari.spigot.api.config.CustomConfig

/**
 * デフォルト設定
 * @since 1.7.0
 */
interface DefaultConfig {
    /**
     * デフォルト設定を適用する。
     * @since 1.7.0
     */
    fun set(config: CustomConfig)
}
