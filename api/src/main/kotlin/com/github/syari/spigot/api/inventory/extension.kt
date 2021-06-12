package com.github.syari.spigot.api.inventory

import com.github.syari.spigot.api.inventory.InventoryPlayerData.Companion.inventoryPlayerData
import com.github.syari.spigot.api.string.toColor
import com.github.syari.spigot.api.uuid.UUIDPlayer
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.event.inventory.InventoryType
import org.bukkit.inventory.Inventory

/**
 * `CustomInventory.() -> Unit`
 */
typealias CustomInventoryAction = CustomInventory.() -> Unit

/**
 * `ClickEventAction.Builder.() -> Unit`
 * @since 2.3.3
 */
typealias ClickEventBuilderAction = ClickEventAction.Builder.() -> Unit

/**
 * [Inventory] から [CustomInventory] を生成する。
 * @param inventory [Inventory]
 * @param id インベントリの識別子 default: inventory.toString()
 * @return [CustomInventory]
 * @since 2.3.0
 */
fun inventory(
    inventory: Inventory,
    id: String = inventory.toString()
) = CustomInventory(inventory, id)

/**
 * [Inventory] から [CustomInventory] を生成する。
 * @param inventory [Inventory]
 * @param id インベントリの識別子 default: inventory.toString()
 * @return [CustomInventory]
 * @since 2.3.0
 */
fun inventory(
    inventory: Inventory,
    id: String = inventory.toString(),
    action: CustomInventory.() -> Unit
) = inventory(inventory, id).apply(action)

/**
 * [InventoryType] から [CustomInventory] を生成する。
 * @param display インベントリのタイトル
 * @param type インベントリの種類
 * @param id インベントリの識別子 default: display
 * @return [CustomInventory]
 * @since 2.3.0
 */
fun inventory(
    display: String,
    type: InventoryType,
    id: String = display
) = inventory(Bukkit.createInventory(null, type, display.toColor()), id)

/**
 * [InventoryType] から [CustomInventory] を生成する。
 * @param display インベントリのタイトル
 * @param type インベントリの種類
 * @param id インベントリの識別子 default: display
 * @param action インベントリに対して実行する処理
 * @return [CustomInventory]
 * @since 2.3.0
 */
fun inventory(
    display: String,
    type: InventoryType,
    id: String = display,
    action: CustomInventoryAction
) = inventory(display, type, id).apply(action)

/**
 * チェストタイプの [CustomInventory] を生成する。
 * @param display インベントリのタイトル
 * @param line インベントリの行数 default: 3
 * @param id インベントリの識別子 default: display
 * @return [CustomInventory]
 * @since 2.3.0
 */
fun inventory(
    display: String,
    line: Int = 3,
    id: String = display
) = inventory(Bukkit.createInventory(null, line * 9, display.toColor()), id)

/**
 * チェストタイプの [CustomInventory] を生成する。
 * @param display インベントリのタイトル
 * @param line インベントリの行数 default: 3
 * @param id インベントリの識別子 default: display
 * @param action インベントリに対して実行する処理
 * @return [CustomInventory]
 * @since 2.3.0
 */
fun inventory(
    display: String,
    line: Int = 3,
    id: String = display,
    action: CustomInventoryAction
) = inventory(display, line, id).apply(action)

/**
 * プレイヤーが開いているインベントリの識別子を取得する。
 * @since 2.3.0
 */
val UUIDPlayer.inventoryId
    get() = inventoryPlayerData?.id

/**
 * プレイヤーが開いているインベントリの識別子を取得する。
 * @since 2.3.0
 */
val Player.inventoryId
    get() = inventoryPlayerData?.id
