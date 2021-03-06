@file:Suppress("DEPRECATION")

package com.github.syari.spigot.api.config.def

import com.github.syari.spigot.api.config.CustomConfig

/**
 * デフォルト設定としてパスと値のマップを利用する。
 * @since 1.7.0
 */
class DefaultConfigMap(val map: Map<String, Any>) : DefaultConfig {
    /**
     * デフォルト設定を適用する。
     * @since 1.7.0
     */
    override fun set(config: CustomConfig) {
        if (map.isEmpty()) {
            map.forEach { (key, value) ->
                config.setUnsafe(key, value)
            }
            config.save()
        }
    }
}
