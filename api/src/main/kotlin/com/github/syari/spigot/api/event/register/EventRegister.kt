package com.github.syari.spigot.api.event.register

import org.bukkit.plugin.java.JavaPlugin

interface EventRegister {
    /**
     * この関数内でイベントの登録をする
     */
    fun Events.register()

    companion object {
        /**
         * [JavaPlugin.onEnable] 内で呼び出すことでイベントの登録をする
         * ```
         * override fun onEnable() {
         *     registerEvents(...)
         * }
         * ```
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
