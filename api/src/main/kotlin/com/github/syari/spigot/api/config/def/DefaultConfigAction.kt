package com.github.syari.spigot.api.config.def

import com.github.syari.spigot.api.config.CustomConfig

/**
 * デフォルト設定として [CustomConfig] に対して処理を実行する。
 * @param action 実行する処理
 * @since 1.7.0
 */
class DefaultConfigAction(val action: CustomConfig.() -> Unit) : DefaultConfig {
    /**
     * デフォルト設定を適用する。
     * @param config 適用するコンフィグ
     * @since 1.7.0
     */
    override fun set(config: CustomConfig) {
        config.apply(action).save()
    }
}
