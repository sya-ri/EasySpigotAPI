package com.github.syari.spigot.api.event

import org.bukkit.plugin.java.JavaPlugin

/**
 * 継承することでイベントの登録を行える。
 * @since 1.0.0
 */
interface EventRegister {
    /**
     * この関数内でイベントの登録をする。
     * @since 1.0.0
     */
    fun Events.register()

    /**
     * @see EventRegister.registerEvents
     */
    companion object {
        /**
         * [JavaPlugin.onEnable] 内で呼び出すことでイベントの登録をする。
         * ```
         * override fun onEnable() {
         *     registerEvents(...)
         * }
         * ```
         * @since 1.0.0
         */
        fun JavaPlugin.registerEvents(vararg events: EventRegister) {
            val listener = Events(this)
            events.forEach {
                it.run {
                    listener.register()
                }
            }
        }
    }
}
