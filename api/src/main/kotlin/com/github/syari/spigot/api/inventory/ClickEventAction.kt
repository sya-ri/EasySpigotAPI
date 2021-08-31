package com.github.syari.spigot.api.inventory

import org.bukkit.event.inventory.ClickType
import org.bukkit.event.inventory.InventoryClickEvent
import java.util.Objects

/**
 * アイテムをクリックした時に処理を実行する。
 * @param slots アイテムの場所
 * @param types クリックの種類
 * @param action 実行処理
 * @since 2.3.0
 */
class ClickEventAction internal constructor(
    private val slots: Iterable<Int>,
    private val types: Array<out ClickType>?,
    private val action: (InventoryClickEvent) -> Unit
) {
    /**
     * アイテムの場所とクリックの種類が一致していれば処理を実行する。
     * @param slot アイテムの場所
     * @param type クリックの種類
     * @param event クリックイベント
     * @since 2.3.0
     */
    internal operator fun invoke(
        slot: Int,
        type: ClickType,
        event: InventoryClickEvent
    ) {
        if (slots.contains(slot) && (types?.contains(type) != false)) {
            action(event)
        }
    }

    override fun hashCode(): Int {
        return Objects.hash(slots, types)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ClickEventAction) return false
        if (slots != other.slots) return false
        if (types != null) {
            if (other.types == null) return false
            return types.contentEquals(other.types)
        } else {
            return other.types == null
        }
    }

    /**
     * インベントリスロットに対して処理を定義する。
     * @param slots アイテムの場所
     * @since 2.3.0
     */
    class Builder internal constructor(
        private val slots: Iterable<Int>
    ) {
        private val events = mutableSetOf<ClickEventAction>()

        /**
         * クリックの種類が一致した時に実行する。
         * @param clickType クリックタイプ
         * @param action 実行する処理
         * @since 2.3.0
         */
        fun onClick(
            vararg clickType: ClickType,
            action: (InventoryClickEvent) -> Unit
        ) {
            events.add(ClickEventAction(slots, clickType, action))
        }

        /**
         * アイテムがクリックされた時に必ず実行する。
         * @param action 実行する処理
         * @since 2.3.0
         */
        fun onClick(
            action: (InventoryClickEvent) -> Unit
        ) {
            events.add(ClickEventAction(slots, null, action))
        }

        /**
         * [ClickEventAction] を生成する。
         * @return [ClickEventAction]
         * @since 2.3.0
         */
        internal fun build() = events.toSet()
    }
}
