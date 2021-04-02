package com.github.syari.spigot.api.inventory

import com.github.syari.spigot.api.uuid.UUIDPlayer
import org.bukkit.entity.Player
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryCloseEvent

/**
 * [CustomInventory] を開いているプレイヤーのデータ
 * @param id インベントリの識別子
 * @param cancel クリックイベントをキャンセルするか
 * @param onClick クリック時の処理
 * @param onClose クローズ時の処理
 * @param clickEvents アイテムをクリックした時の処理
 * @since 2.3.0
 */
internal class InventoryPlayerData(
    val id: String,
    val cancel: Boolean,
    val onClick: ((InventoryClickEvent) -> Unit)?,
    val onClose: ((InventoryCloseEvent) -> Unit)?,
    val clickEvents: Set<ClickEventAction>
) {
    companion object {
        private val list = mutableMapOf<UUIDPlayer, InventoryPlayerData>()

        /**
         * インベントリのプレイヤーデータ
         * @since 2.3.0
         */
        var UUIDPlayer.inventoryPlayerData
            get() = list[this]
            internal set(value) {
                if (value != null) {
                    list[this] = value
                } else {
                    list.remove(this)
                }
            }

        /**
         * インベントリのプレイヤーデータ
         * @since 2.3.0
         */
        var Player.inventoryPlayerData
            get() = UUIDPlayer.from(this).inventoryPlayerData
            internal set(value) {
                UUIDPlayer.from(this).inventoryPlayerData = value
            }
    }
}
