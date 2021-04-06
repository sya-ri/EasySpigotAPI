package com.github.syari.spigot.api.item

import com.github.syari.spigot.api.UnsupportedMinecraftVersion
import com.github.syari.spigot.api.nms.NMSItemStackWrapper
import com.github.syari.spigot.api.string.toColor
import com.google.common.collect.ImmutableMultimap
import com.google.common.collect.Multimap
import org.bukkit.Material
import org.bukkit.attribute.Attribute
import org.bukkit.attribute.AttributeModifier
import org.bukkit.enchantments.Enchantment
import org.bukkit.inventory.EquipmentSlot
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta
import com.github.syari.spigot.api.item.displayName as eDisplayName
import com.github.syari.spigot.api.item.lore as eLore

/**
 * 表示名が存在するか取得する。
 * @return 存在するか
 * @see ItemMeta.hasDisplayName
 * @since 1.5.0
 */
fun ItemStack.hasDisplayName(): Boolean = itemMeta?.hasDisplayName() ?: false

/**
 * 表示名。取得する時は [hasDisplayName] が真である場合のみにする。
 * @see ItemMeta.getDisplayName
 * @see ItemMeta.setDisplayName
 * @since 1.5.0
 */
var ItemStack.displayName: String?
    get() = itemMeta?.displayName
    set(value) {
        editItemMeta {
            setDisplayName(value?.toColor())
        }
    }

/**
 * 説明文が存在するか取得する。
 * @return 存在するか
 * @see ItemMeta.hasLore
 * @since 1.5.0
 */
fun ItemStack.hasLore(): Boolean = itemMeta?.hasLore() ?: false

/**
 * 説明文。取得する時は [hasLore] が真である場合でのみにする。
 * @see ItemMeta.getLore
 * @see ItemMeta.setLore
 * @since 1.5.0
 */
var ItemStack.lore: List<String>
    get() = itemMeta?.lore.orEmpty()
    set(value) {
        editItemMeta {
            lore = value.map(String::toColor)
        }
    }

/**
 * アイテムの説明文を変更する。
 * @param action 変更する処理
 * @since 1.5.0
 */
inline fun ItemStack.editLore(action: MutableList<String>.() -> Unit) {
    eLore = eLore.toMutableList().apply(action)
}

/**
 * カスタムモデルデータが設定されているか取得する。
 * @return 設定されているか
 * @see ItemMeta.hasCustomModelData
 * @since 1.5.0
 */
@UnsupportedMinecraftVersion(8, 9, 10, 11, 12, 13)
fun ItemStack.hasCustomModelData(): Boolean = itemMeta?.hasCustomModelData() ?: false

/**
 * カスタムモデルデータ。取得する時は [hasCustomModelData] が真である場合でのみにする。
 * @see ItemMeta.getCustomModelData
 * @see ItemMeta.setCustomModelData
 * @since 1.5.0
 */
@UnsupportedMinecraftVersion(8, 9, 10, 11, 12, 13)
var ItemStack.customModelData: Int?
    get() = itemMeta?.customModelData
    set(value) {
        editItemMeta {
            setCustomModelData(value)
        }
    }

/**
 * エンチャントが存在するか取得する。
 * @return 存在するか
 * @see ItemMeta.hasEnchants
 * @since 1.5.0
 */
fun ItemStack.hasEnchants(): Boolean = itemMeta?.hasEnchants() ?: false

/**
 * 特定のエンチャントが存在するか取得する。
 * @return 存在するか
 * @see ItemMeta.hasEnchant
 * @since 1.5.0
 */
fun ItemStack.hasEnchant(enchant: Enchantment): Boolean = itemMeta?.hasEnchant(enchant) ?: false

/**
 * 特定のエンチャントのレベルを取得する。
 * @param enchant 取得するエンチャント
 * @return 取得したエンチャントレベル
 * @see ItemMeta.getEnchantLevel
 * @since 1.5.0
 */
fun ItemStack.getEnchantLevel(enchant: Enchantment): Int = itemMeta?.getEnchantLevel(enchant) ?: 0

/**
 * 全てのエンチャントを取得する。
 * @see ItemMeta.getEnchants
 * @since 1.5.0
 */
var ItemStack.enchants: Map<Enchantment, Int>
    get() = itemMeta?.enchants.orEmpty()
    set(value) {
        editItemMeta {
            enchants.forEach { (enchant, _) ->
                removeEnchant(enchant)
            }
            value.forEach { (enchant, level) ->
                addEnchant(enchant, level, true)
            }
        }
    }

/**
 * エンチャントを追加する。
 * @param enchant エンチャント
 * @param level エンチャントレベル
 * @param ignoreLevelRestriction エンチャントのレベル制限を無視するか default: true
 * @see ItemMeta.addEnchant
 * @since 1.5.0
 */
fun ItemStack.addEnchant(enchant: Enchantment, level: Int, ignoreLevelRestriction: Boolean = true) {
    editItemMeta {
        addEnchant(enchant, level, ignoreLevelRestriction)
    }
}

/**
 * エンチャントを削除する。
 * @param enchant 削除するエンチャント
 * @see ItemMeta.removeEnchant
 * @since 1.5.0
 */
fun ItemStack.removeEnchant(enchant: Enchantment) {
    editItemMeta {
        removeEnchant(enchant)
    }
}

/**
 * エンチャントが競合するかどうかを取得する。
 * @param enchant エンチャント
 * @return 競合するか
 * @see ItemMeta.hasConflictingEnchant
 * @since 1.5.0
 */
fun ItemStack.hasConflictingEnchant(enchant: Enchantment): Boolean = itemMeta?.hasConflictingEnchant(enchant) ?: false

/**
 * アイテムフラグを追加する。
 * @param itemFlags アイテムフラグ
 * @see ItemMeta.addItemFlags
 * @since 1.5.0
 */
fun ItemStack.addItemFlags(vararg itemFlags: ItemFlag) {
    editItemMeta {
        addItemFlags(*itemFlags)
    }
}

/**
 * アイテムフラグを削除する。
 * @param itemFlags アイテムフラグ
 * @see ItemMeta.removeItemFlags
 * @since 1.5.0
 */
fun ItemStack.removeItemFlags(vararg itemFlags: ItemFlag) {
    editItemMeta {
        removeItemFlags(*itemFlags)
    }
}

/**
 * アイテムフラグ。
 * @see ItemMeta.getItemFlags
 * @since 1.5.0
 */
var ItemStack.itemFlags: Set<ItemFlag>
    get() = itemMeta?.itemFlags.orEmpty()
    set(value) {
        editItemMeta {
            removeItemFlags(*itemFlags.toTypedArray())
            addItemFlags(*value.toTypedArray())
        }
    }

/**
 * 特定のアイテムフラグが存在するか取得する。
 * @param itemFlag アイテムフラグ
 * @return 存在するか
 * @see ItemMeta.hasItemFlag
 * @since 1.5.0
 */
fun ItemStack.hasItemFlag(itemFlag: ItemFlag): Boolean = itemMeta?.hasItemFlag(itemFlag) ?: false

/**
 * 耐久無限。
 * @since 1.5.0
 */
@UnsupportedMinecraftVersion(8, 9, 10)
var ItemStack.isUnbreakable: Boolean
    get() = itemMeta?.isUnbreakable ?: false
    set(value) {
        editItemMeta {
            isUnbreakable = value
        }
    }

/**
 * [AttributeModifier] が存在するか取得する。
 * @return 存在するか
 * @see ItemMeta.hasAttributeModifiers
 * @since 1.5.0
 */
@UnsupportedMinecraftVersion(8, 9, 10, 11, 12)
fun ItemStack.hasAttributeModifiers(): Boolean = itemMeta?.hasAttributeModifiers() ?: false

/**
 * 全ての装備箇所で影響する [AttributeModifier] の一覧を取得する。
 * @see ItemMeta.getAttributeModifiers
 * @see ItemMeta.setAttributeModifiers
 * @since 1.5.0
 */
@UnsupportedMinecraftVersion(8, 9, 10, 11, 12)
var ItemStack.attributeModifiers: Multimap<Attribute, AttributeModifier>
    get() = itemMeta?.attributeModifiers ?: ImmutableMultimap.of()
    set(value) {
        editItemMeta {
            attributeModifiers = value
        }
    }

/**
 * 特定の装備箇所で影響する [AttributeModifier] の一覧を取得する。
 * @param slot 装備欄
 * @return [AttributeModifier] の一覧
 * @see ItemMeta.getAttributeModifiers
 * @since 1.5.0
 */
@UnsupportedMinecraftVersion(8, 9, 10, 11, 12)
fun ItemStack.getAttributeModifiers(slot: EquipmentSlot): Multimap<Attribute, AttributeModifier> {
    return itemMeta?.getAttributeModifiers(slot) ?: ImmutableMultimap.of()
}

/**
 * [Attribute] に関する [AttributeModifier] の一覧を取得する。
 * @param attribute 取得する [Attribute] の種類
 * @return [AttributeModifier] の一覧
 * @see ItemMeta.getAttributeModifiers
 * @since 1.5.0
 */
@UnsupportedMinecraftVersion(8, 9, 10, 11, 12)
fun ItemStack.getAttributeModifiers(attribute: Attribute): Collection<AttributeModifier> {
    return itemMeta?.getAttributeModifiers(attribute).orEmpty()
}

/**
 * [AttributeModifier] を追加する。
 * @param attribute 追加する [Attribute] の種類
 * @param modifier 追加する効果
 * @return 追加できたか
 * @see ItemMeta.addAttributeModifier
 * @since 1.5.0
 */
@UnsupportedMinecraftVersion(8, 9, 10, 11, 12)
fun ItemStack.addAttributeModifier(attribute: Attribute, modifier: AttributeModifier): Boolean {
    var result = false
    editItemMeta {
        result = addAttributeModifier(attribute, modifier)
    }
    return result
}

/**
 * [AttributeModifier] を削除する。
 * @param attribute 削除する [Attribute] の種類
 * @return 削除できたか
 * @see ItemMeta.removeAttributeModifier
 * @since 1.5.0
 */
@UnsupportedMinecraftVersion(8, 9, 10, 11, 12)
fun ItemStack.removeAttributeModifier(attribute: Attribute): Boolean {
    var result = false
    editItemMeta {
        result = removeAttributeModifier(attribute)
    }
    return result
}

/**
 * [AttributeModifier] を削除する。
 * @param slot 削除する装備欄
 * @return 削除できたか
 * @see ItemMeta.removeAttributeModifier
 * @since 1.5.0
 */
@UnsupportedMinecraftVersion(8, 9, 10, 11, 12)
fun ItemStack.removeAttributeModifier(slot: EquipmentSlot): Boolean {
    var result = false
    editItemMeta {
        result = removeAttributeModifier(slot)
    }
    return result
}

/**
 * [AttributeModifier] を削除する。
 * @param attribute 削除する [Attribute] の種類
 * @param modifier 削除する効果
 * @see ItemMeta.removeAttributeModifier
 * @since 1.5.0
 */
@UnsupportedMinecraftVersion(8, 9, 10, 11, 12)
fun ItemStack.removeAttributeModifier(attribute: Attribute, modifier: AttributeModifier): Boolean {
    var result = false
    editItemMeta {
        result = removeAttributeModifier(attribute, modifier)
    }
    return result
}

/**
 * [ItemMeta] に対して変更を加える。
 * @param action 変更を加える処理
 * @since 1.5.0
 */
inline fun ItemStack.editItemMeta(action: ItemMeta.() -> Unit) {
    itemMeta = itemMeta?.apply(action)
}

/**
 * [ItemMeta] に対して変更を加える。
 * @param action 変更を加える処理
 * @since 1.5.0
 */
@JvmName("editItemMetaT")
inline fun <reified T : ItemMeta> ItemStack.editItemMeta(action: T.() -> Unit) {
    editItemMeta {
        (this as? T)?.action()
    }
}

/**
 * NBT タグを取得する。
 * @since 1.8.0
 */
val ItemStack.nbtTag: String
    get() = NMSItemStackWrapper(this).getOrCreateTag().toString()

/**
 * [ItemStack] のインスタンスを生成する
 * @param material マテリアル
 * @param displayName 表示名 default: null
 * @param lore 説明文 default: listOf()
 * @return [ItemStack] のインスタンス
 * @since 2.2.1
 */
fun itemStack(
    material: Material,
    displayName: String? = null,
    lore: List<String> = listOf()
) = ItemStack(material).apply {
    eDisplayName = displayName
    eLore = lore
}

/**
 * [ItemStack] のインスタンスを生成する
 * @param material マテリアル
 * @param displayName 表示名 default: null
 * @param lore 説明文 default: listOf()
 * @param action [ItemStack] に対して実行する処理
 * @return [ItemStack] のインスタンス
 * @since 2.2.1
 */
inline fun itemStack(
    material: Material,
    displayName: String? = null,
    lore: List<String> = listOf(),
    action: ItemStack.() -> Unit
) = itemStack(material, displayName, lore).apply(action)
