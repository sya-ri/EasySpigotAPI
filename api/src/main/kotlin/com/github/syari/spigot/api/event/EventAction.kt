package com.github.syari.spigot.api.event

import org.bukkit.event.Cancellable
import org.bukkit.event.Event
import org.bukkit.event.EventPriority

/**
 * 登録のネストをエラーにするためのクラス。
 * @since 2.4.0
 */
object EventAction {
    /**
     * イベントを定義する。
     *
     * 以下のようなコードをエラーにするためのメソッド。
     * ```kotlin
     * plugin.events {
     *     event<PlayerJoinEvent> {
     *         event<PlayerJoinEvent> { // ERROR
     *
     *         }
     *     }
     * }
     * ```
     *
     * @param T イベント
     * @param priority 優先度 default: NORMAL
     * @param ignoreCancelled キャンセルされていたら実行しない default: false
     * @param action 実行する処理
     * @see com.github.syari.spigot.api.event.Events.event
     * @since 2.4.0
     */
    @Suppress("unused", "unused_parameter")
    @Deprecated("event の中に event を使用できません", level = DeprecationLevel.ERROR)
    inline fun <reified T : Event> event(
        priority: EventPriority = EventPriority.NORMAL,
        ignoreCancelled: Boolean = false,
        crossinline action: (T) -> Unit
    ) = Unit

    /**
     * 条件に一致した時に特定のイベントをキャンセルする。
     *
     * 以下のようなコードをエラーにするためのメソッド。
     * ```kotlin
     * plugin.events {
     *     event<PlayerJoinEvent> {
     *         event<PlayerJoinEvent> { // ERROR
     *
     *         }
     *     }
     * }
     * ```
     *
     * @param T イベント
     * @param priority 優先度 default: NORMAL
     * @param action 条件
     * @see com.github.syari.spigot.api.event.Events.cancelEventIf
     * @since 2.4.0
     */
    @Suppress("unused", "unused_parameter")
    @Deprecated("event の中に event を使用できません", level = DeprecationLevel.ERROR)
    inline fun <reified T> cancelEventIf(
        priority: EventPriority = EventPriority.NORMAL,
        crossinline action: EventAction.(T) -> Boolean
    ) where T : Event, T : Cancellable = Unit

    /**
     * 条件に一致しなかった時に特定のイベントをキャンセルする。
     *
     * 以下のようなコードをエラーにするためのメソッド。
     * ```kotlin
     * plugin.events {
     *     event<PlayerJoinEvent> {
     *         event<PlayerJoinEvent> { // ERROR
     *
     *         }
     *     }
     * }
     * ```
     *
     * @param T イベント
     * @param priority 優先度 default: NORMAL
     * @param action 条件
     * @see com.github.syari.spigot.api.event.Events.cancelEventIfNot
     * @since 2.4.0
     */
    @Suppress("unused", "unused_parameter")
    @Deprecated("event の中に event を使用できません", level = DeprecationLevel.ERROR)
    inline fun <reified T> cancelEventIfNot(
        priority: EventPriority = EventPriority.NORMAL,
        crossinline action: EventAction.(T) -> Boolean
    ) where T : Event, T : Cancellable = Unit
}
