package com.github.syari.spigot.api.inventory

import com.github.syari.spigot.api.EasySpigotAPI
import com.github.syari.spigot.api.event.events
import com.github.syari.spigot.api.inventory.InventoryPlayerData.Companion.inventoryPlayerData
import com.github.syari.spigot.api.item.itemStack
import com.github.syari.spigot.api.scheduler.runTaskLater
import com.github.syari.spigot.api.uuid.UUIDPlayer
import org.bukkit.Material
import org.bukkit.entity.HumanEntity
import org.bukkit.entity.Player
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryCloseEvent
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack
import org.bukkit.plugin.java.JavaPlugin

/**
 * インベントリの設定をするクラス
 * @param inventory 対象のインベントリ
 * @param id インベントリの識別子
 * @since 2.3.0
 */
class CustomInventory internal constructor(
    val inventory: Inventory,
    val id: String
) {
    companion object {
        private var availableCustomInventory = false

        /**
         * [CustomInventory] を利用する場合、あらかじめ呼び出しておく必要がある。
         * @param plugin イベントの定義に使うプラグイン
         * @since 2.3.0
         */
        fun onEnable(plugin: JavaPlugin) {
            if (availableCustomInventory) return
            availableCustomInventory = true
            EasySpigotAPI.logger.info("CustomInventory is now available")
            plugin.events {
                event<InventoryClickEvent> {
                    val player = it.whoClicked as? Player ?: return@event
                    val uuidPlayer = UUIDPlayer.from(player)
                    uuidPlayer.inventoryPlayerData?.run {
                        if (cancel) {
                            it.isCancelled = true
                        }
                        if (it.inventory == it.clickedInventory) {
                            clickEvents.toList().forEach { action ->
                                action(it.slot, it.click)
                            }
                        }
                        onClick?.invoke(it)
                    }
                }
                event<InventoryCloseEvent> {
                    val player = it.player as Player
                    val uuidPlayer = UUIDPlayer.from(player)
                    uuidPlayer.inventoryPlayerData?.run {
                        onClose?.invoke(it)
                        uuidPlayer.inventoryPlayerData = null
                        plugin.runTaskLater(5) {
                            player.updateInventory()
                        }
                    }
                }
            }
        }
    }

    init {
        if (availableCustomInventory.not()) {
            throw IllegalStateException("CustomInventory is not available. CustomInventory::onEnable must be called when the server started.")
        }
    }

    private val clickEvents = mutableSetOf<ClickEventAction>()

    /**
     * クリックイベントをキャンセルする。
     * @since 2.3.0
     */
    var isCancel = true

    /**
     * クリックイベントを定義する。
     * @since 2.3.0
     */
    var onClick: ((InventoryClickEvent) -> Unit)? = null

    /**
     * クローズイベントを定義する。
     * @since 2.3.0
     */
    var onClose: ((InventoryCloseEvent) -> Unit)? = null

    /**
     * アイテムを配置する。
     * @param slot アイテムの場所
     * @param item アイテム
     * @param onClick クリック時の処理
     * @since 2.3.0
     */
    fun item(
        slot: Int,
        item: ItemStack,
        onClick: (ClickEventAction.Builder.() -> Unit)? = null
    ) {
        item(listOf(slot), item, onClick)
    }

    /**
     * アイテムを配置する。
     * @param slot アイテムの場所
     * @param item アイテム
     * @param onClick クリック時の処理
     * @since 2.3.0
     */
    fun item(
        slot: Iterable<Int>,
        item: ItemStack,
        onClick: (ClickEventAction.Builder.() -> Unit)? = null
    ) {
        slot.filter { it in 0 until inventory.size }.let {
            it.forEach { i ->
                inventory.setItem(i, item)
            }
            if (onClick != null) {
                clickEvents.addAll(ClickEventAction.Builder(it).apply(onClick).build())
            }
        }
    }

    /**
     * アイテムを配置する。
     * @param slot アイテムの場所
     * @param material アイテムタイプ
     * @param display アイテム名
     * @param lore アイテムの説明文
     * @param onClick クリック時の処理
     * @since 2.3.0
     */
    fun item(
        slot: Int,
        material: Material,
        display: String = "",
        lore: List<String> = listOf(),
        onClick: (ClickEventAction.Builder.() -> Unit)? = null
    ) {
        item(listOf(slot), material, display, lore, onClick)
    }

    /**
     * アイテムを配置する。
     * @param slot アイテムの場所
     * @param material アイテムタイプ
     * @param display アイテム名
     * @param lore アイテムの説明文
     * @param onClick クリック時の処理
     * @since 2.3.0
     */
    fun item(
        slot: Iterable<Int>,
        material: Material,
        display: String = "",
        lore: List<String> = listOf(),
        onClick: (ClickEventAction.Builder.() -> Unit)? = null
    ) {
        item(slot, itemStack(material, display, lore), onClick)
    }

    /**
     * アイテムを配置する。
     * @param slot アイテムの場所
     * @param material アイテムタイプ
     * @param display アイテム名
     * @param lore アイテムの説明文
     * @param onClick クリック時の処理
     * @since 2.3.0
     */
    fun item(
        slot: Int,
        material: Material,
        display: String = "",
        vararg lore: String,
        onClick: (ClickEventAction.Builder.() -> Unit)? = null
    ) {
        item(listOf(slot), material, display, lore.toList(), onClick)
    }

    /**
     * アイテムを配置する。
     * @param slot アイテムの場所
     * @param material アイテムタイプ
     * @param display アイテム名
     * @param lore アイテムの説明文
     * @param onClick クリック時の処理
     * @since 2.3.0
     */
    fun item(
        slot: Iterable<Int>,
        material: Material,
        display: String = "",
        vararg lore: String,
        onClick: (ClickEventAction.Builder.() -> Unit)? = null
    ) {
        item(slot, material, display, lore.toList(), onClick)
    }

    /**
     * プレイヤーにインベントリを開かせる。
     * @param player 対象プレイヤー
     * @since 2.3.0
     */
    fun open(player: Player) {
        player.openInventory(inventory)
        player.inventoryPlayerData = InventoryPlayerData(id, isCancel, onClick, onClose, clickEvents)
    }

    /**
     * プレイヤーのインベントリを閉じる。
     * @param player 対象プレイヤー
     * @since 2.3.2
     */
    fun close(player: Player) {
        player.closeInventory()
    }

    /**
     * このインベントリを開いている全プレイヤーのインベントリを閉じる。
     * @since 2.3.2
     */
    fun closeAll() {
        inventory.viewers.toList().forEach(HumanEntity::closeInventory)
    }
}
