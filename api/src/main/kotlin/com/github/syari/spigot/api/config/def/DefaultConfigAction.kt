package com.github.syari.spigot.api.config.def

import com.github.syari.spigot.api.config.CustomConfig

/**
 * デフォルト設定として [CustomConfig] に対して処理を実行する。
 * @since 1.7.0
 */
class DefaultConfigAction(val action: CustomConfig.() -> Unit) : DefaultConfig {
    /**
     * デフォルト設定を適用する。
     * @since 1.7.0
     */
    override fun set(config: CustomConfig) {
        config.apply(action).save()
    }
}
