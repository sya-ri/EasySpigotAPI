package com.github.syari.spigot.api.event

import org.bukkit.event.Cancellable
import org.bukkit.event.Event
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.plugin.java.JavaPlugin

/**
 * [EventRegister.register] で使われている。イベントの定義を行える。
 * @param plugin 登録先のプラグイン
 * @since 1.0.0
 */
class Events internal constructor(val plugin: JavaPlugin) : Listener {
    /**
     * イベントを定義する。
     * @param T イベント
     * @param priority 優先度 default: NORMAL
     * @param ignoreCancelled キャンセルされていたら実行しない default: false
     * @param action 実行する処理
     * @since 1.0.0
     */
    inline fun <reified T : Event> event(
        priority: EventPriority = EventPriority.NORMAL,
        ignoreCancelled: Boolean = false,
        crossinline action: (T) -> Unit
    ) {
        plugin.server.pluginManager.registerEvent(
            T::class.java,
            this,
            priority,
            { _, event ->
                (event as? T)?.let(action)
            },
            plugin,
            ignoreCancelled
        )
    }

    /**
     * 条件に一致した時に特定のイベントをキャンセルする。
     * @param T イベント
     * @param priority 優先度 default: NORMAL
     * @param action 条件
     * @since 1.0.0
     */
    inline fun <reified T> cancelEventIf(
        priority: EventPriority = EventPriority.NORMAL,
        crossinline action: (T) -> Boolean
    ) where T : Event, T : Cancellable {
        event<T>(priority, true) {
            it.isCancelled = action(it)
        }
    }

    /**
     * 条件に一致しなかった時に特定のイベントをキャンセルする。
     * @param T イベント
     * @param priority 優先度 default: NORMAL
     * @param action 条件
     * @since 1.0.0
     */
    inline fun <reified T> cancelEventIfNot(
        priority: EventPriority = EventPriority.NORMAL,
        crossinline action: (T) -> Boolean
    ) where T : Event, T : Cancellable {
        cancelEventIf<T>(priority) {
            action(it).not()
        }
    }
}
