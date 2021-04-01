package com.github.syari.spigot.api.event

import org.bukkit.plugin.java.JavaPlugin

/**
 * イベントの登録を行う。
 * @param action イベント一覧
 * @since 2.2.4
 */
fun JavaPlugin.events(action: Events.() -> Unit) {
    Events(this).action()
}
