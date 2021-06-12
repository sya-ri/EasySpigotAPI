package com.github.syari.spigot.api.event

import org.bukkit.plugin.java.JavaPlugin

/**
 * `Events.() -> Unit`
 * @since 2.3.3
 */
typealias EventsAction = Events.() -> Unit

/**
 * イベントの登録を行う。
 * @param action イベント一覧
 * @since 2.2.4
 */
fun JavaPlugin.events(action: EventsAction) {
    Events(this).action()
}
