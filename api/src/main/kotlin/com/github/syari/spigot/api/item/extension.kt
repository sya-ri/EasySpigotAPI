package com.github.syari.spigot.api.item

import com.github.syari.spigot.api.nms.NMSItemStackWrapper
import com.github.syari.spigot.api.string.toColor
import com.google.common.collect.ImmutableMultimap
import com.google.common.collect.Multimap
import org.bukkit.attribute.Attribute
import org.bukkit.attribute.AttributeModifier
import org.bukkit.enchantments.Enchantment
import org.bukkit.inventory.EquipmentSlot
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta

/**
 * 表示名が存在するか取得する。
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
 * @since 1.5.0
 */
inline fun ItemStack.editLore(action: MutableList<String>.() -> Unit) {
    lore = lore.toMutableList().apply(action).map(String::toColor)
}

/**
 * カスタムモデルデータが設定されているか取得する。
 * @see ItemMeta.hasCustomModelData
 * @since 1.5.0
 */
fun ItemStack.hasCustomModelData(): Boolean = itemMeta?.hasCustomModelData() ?: false

/**
 * カスタムモデルデータ。取得する時は [hasCustomModelData] が真である場合でのみにする。
 * @see ItemMeta.getCustomModelData
 * @see ItemMeta.setCustomModelData
 * @since 1.5.0
 */
var ItemStack.customModelData: Int?
    get() = itemMeta?.customModelData
    set(value) {
        editItemMeta {
            setCustomModelData(value)
        }
    }

/**
 * エンチャントが存在するか取得する。
 * @see ItemMeta.hasEnchants
 * @since 1.5.0
 */
fun ItemStack.hasEnchants(): Boolean = itemMeta?.hasEnchants() ?: false

/**
 * 特定のエンチャントが存在するか取得する。
 * @see ItemMeta.hasEnchant
 * @since 1.5.0
 */
fun ItemStack.hasEnchant(enchant: Enchantment): Boolean = itemMeta?.hasEnchant(enchant) ?: false

/**
 * 特定のエンチャントのレベルを取得する。
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
 * @see ItemMeta.hasConflictingEnchant
 * @since 1.5.0
 */
fun ItemStack.hasConflictingEnchant(enchant: Enchantment): Boolean = itemMeta?.hasConflictingEnchant(enchant) ?: false

/**
 * アイテムフラグを追加する。
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
 * @see ItemMeta.hasItemFlag
 * @since 1.5.0
 */
fun ItemStack.hasItemFlag(flag: ItemFlag): Boolean = itemMeta?.hasItemFlag(flag) ?: false

/**
 * 耐久無限。
 * @since 1.5.0
 */
var ItemStack.isUnbreakable: Boolean
    get() = itemMeta?.isUnbreakable ?: false
    set(value) {
        editItemMeta {
            isUnbreakable = value
        }
    }

/**
 * [AttributeModifier] が存在するか取得する。
 * @see ItemMeta.hasAttributeModifiers
 * @since 1.5.0
 */
fun ItemStack.hasAttributeModifiers(): Boolean = itemMeta?.hasAttributeModifiers() ?: false

/**
 * 全ての装備箇所で影響する [AttributeModifier] の一覧を取得する。
 * @see ItemMeta.getAttributeModifiers
 * @see ItemMeta.setAttributeModifiers
 * @since 1.5.0
 */
var ItemStack.attributeModifiers: Multimap<Attribute, AttributeModifier>
    get() = itemMeta?.attributeModifiers ?: ImmutableMultimap.of()
    set(value) {
        editItemMeta {
            attributeModifiers = value
        }
    }

/**
 * 特定の装備箇所で影響する [AttributeModifier] の一覧を取得する。
 * @see ItemMeta.getAttributeModifiers
 * @since 1.5.0
 */
fun ItemStack.getAttributeModifiers(slot: EquipmentSlot): Multimap<Attribute, AttributeModifier> {
    return itemMeta?.getAttributeModifiers(slot) ?: ImmutableMultimap.of()
}

/**
 * [Attribute] に関する [AttributeModifier] の一覧を取得する。
 * @see ItemMeta.getAttributeModifiers
 * @since 1.5.0
 */
fun ItemStack.getAttributeModifiers(attribute: Attribute): Collection<AttributeModifier> {
    return itemMeta?.getAttributeModifiers(attribute).orEmpty()
}

/**
 * [AttributeModifier] を追加する。
 * @see ItemMeta.addAttributeModifier
 * @since 1.5.0
 */
fun ItemStack.addAttributeModifier(attribute: Attribute, modifier: AttributeModifier): Boolean {
    return itemMeta?.addAttributeModifier(attribute, modifier) ?: false
}

/**
 * [AttributeModifier] を削除する。
 * @see ItemMeta.removeAttributeModifier
 * @since 1.5.0
 */
fun ItemStack.removeAttributeModifier(attribute: Attribute): Boolean {
    return itemMeta?.removeAttributeModifier(attribute) ?: false
}

/**
 * [AttributeModifier] を削除する。
 * @see ItemMeta.removeAttributeModifier
 * @since 1.5.0
 */
fun ItemStack.removeAttributeModifier(slot: EquipmentSlot): Boolean {
    return itemMeta?.removeAttributeModifier(slot) ?: false
}

/**
 * [AttributeModifier] を削除する。
 * @see ItemMeta.removeAttributeModifier
 * @since 1.5.0
 */
fun ItemStack.removeAttributeModifier(attribute: Attribute, modifier: AttributeModifier): Boolean {
    return itemMeta?.removeAttributeModifier(attribute, modifier) ?: false
}

/**
 * [ItemMeta] に対して変更を加える。
 * @since 1.5.0
 */
inline fun ItemStack.editItemMeta(action: ItemMeta.() -> Unit) {
    itemMeta = itemMeta?.apply(action)
}

/**
 * [ItemMeta] に対して変更を加える。
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
