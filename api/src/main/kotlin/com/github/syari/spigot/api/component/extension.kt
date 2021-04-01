package com.github.syari.spigot.api.component

import com.github.syari.spigot.api.UnsupportedMinecraftVersion
import com.github.syari.spigot.api.item.nbtTag
import com.github.syari.spigot.api.string.toColor
import net.md_5.bungee.api.chat.BaseComponent
import net.md_5.bungee.api.chat.ClickEvent
import net.md_5.bungee.api.chat.HoverEvent
import net.md_5.bungee.api.chat.ItemTag
import net.md_5.bungee.api.chat.KeybindComponent
import net.md_5.bungee.api.chat.TextComponent
import net.md_5.bungee.api.chat.TranslatableComponent
import net.md_5.bungee.api.chat.hover.content.Item
import net.md_5.bungee.api.chat.hover.content.Text
import org.bukkit.Material
import org.bukkit.inventory.ItemStack

typealias BungeeChatColor = net.md_5.bungee.api.ChatColor

/**
 * @see TextComponentBuilder
 * @since 1.6.0
 */
inline fun buildTextComponent(action: TextComponentBuilder.() -> Unit) = TextComponentBuilder().apply(action).build()

/**
 * @param hover [HoverEvent] default: null
 * @param click [ClickEvent] default: null
 * @since 2.2.3
 */
fun <T : BaseComponent> T.with(
    hover: HoverEvent? = null,
    click: ClickEvent? = null,
) = apply {
    hoverEvent = hover
    clickEvent = click
}

/**
 * フォーマットを変更する。
 * @param color 文字色
 * @param bold 太字
 * @param italic 斜体
 * @param underline 下線
 * @param strike 取り消し線
 * @param obfuscate 難読化
 * @return [T]
 * @since 2.2.3
 */
fun <T : BaseComponent> T.with(
    color: BungeeChatColor? = getColor(),
    bold: Boolean = isBold,
    italic: Boolean = isItalic,
    underline: Boolean = isUnderlined,
    strike: Boolean = isStrikethrough,
    obfuscate: Boolean = isObfuscated,
) = apply {
    setColor(color)
    isBold = bold
    isItalic = italic
    isUnderlined = underline
    isStrikethrough = strike
    isObfuscated = obfuscate
}

/**
 * @param text 文字列
 * @param hover [HoverEvent] default: null
 * @param click [ClickEvent] default: null
 * @since 2.2.2
 */
fun textComponent(
    text: String,
    hover: HoverEvent? = null,
    click: ClickEvent? = null,
) = TextComponent(text.toColor()).with(hover, click)

/**
 * [TranslatableComponent] を生成する
 * @param translate 翻訳キー
 * @param hover [HoverEvent] default: null
 * @param click [ClickEvent] default: null
 * @since 2.2.3
 */
fun translateComponent(
    translate: String,
    hover: HoverEvent? = null,
    click: ClickEvent? = null,
) = TranslatableComponent(translate).with(hover, click)

/**
 * [Material] から [TranslatableComponent] を生成する
 * @param material 翻訳するアイテム
 * @param hover [HoverEvent] default: null
 * @param click [ClickEvent] default: null
 * @since 2.2.3
 */
@UnsupportedMinecraftVersion(8, 9, 10, 11, 12)
fun translateComponent(
    material: Material,
    hover: HoverEvent? = null,
    click: ClickEvent? = null,
) = translateComponent((if (material.isBlock) "block" else "item") + ".minecraft." + material.key.key, hover, click)

/**
 * [KeybindComponent] を生成する
 * @param identifier 識別子 [net.md_5.bungee.api.chat.Keybinds]
 * @param hover [HoverEvent] default: null
 * @param click [ClickEvent] default: null
 * @since 2.2.3
 */
@UnsupportedMinecraftVersion(8, 9, 10, 11)
fun keybindComponent(
    identifier: String,
    hover: HoverEvent? = null,
    click: ClickEvent? = null,
) = KeybindComponent(identifier).with(hover, click)

/**
 * [ClickEvent.Action.RUN_COMMAND] の [ClickEvent]
 * @since 1.6.0
 */
fun clickRunCommand(command: String) = ClickEvent(ClickEvent.Action.RUN_COMMAND, command)

/**
 * [ClickEvent.Action.SUGGEST_COMMAND] の [ClickEvent]
 * @since 1.6.0
 */
fun clickTypeText(text: String) = ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, text)

/**
 * [ClickEvent.Action.OPEN_URL] の [ClickEvent]
 * @since 1.6.0
 */
fun clickOpenURL(url: String) = ClickEvent(ClickEvent.Action.OPEN_URL, url)

/**
 * [ClickEvent.Action.COPY_TO_CLIPBOARD] の [ClickEvent]
 * @since 1.6.0
 */
@UnsupportedMinecraftVersion(8, 9, 10, 11, 12, 13, 14)
fun clickCopyToClipboard(text: String) = ClickEvent(ClickEvent.Action.COPY_TO_CLIPBOARD, text)

/**
 * [HoverEvent.Action.SHOW_TEXT] の [HoverEvent]
 * @since 1.6.0
 */
@UnsupportedMinecraftVersion(8, 9, 10, 11, 12, 13, 14, 15)
fun hoverText(text: String) = HoverEvent(HoverEvent.Action.SHOW_TEXT, Text(text.toColor()))

/**
 * [HoverEvent.Action.SHOW_ITEM] の [HoverEvent]
 * @since 1.8.0
 */
@UnsupportedMinecraftVersion(8, 9, 10, 11, 12, 13, 14, 15)
fun hoverItem(itemStack: ItemStack) = HoverEvent(HoverEvent.Action.SHOW_ITEM, Item(itemStack.type.key.key, 1, ItemTag.ofNbt(itemStack.nbtTag)))
