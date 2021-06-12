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

/**
 * [org.bukkit.ChatColor] と混同しないようにするためのエイリアス
 * @see net.md_5.bungee.api.ChatColor
 * @since 2.2.4
 */
typealias BungeeChatColor = net.md_5.bungee.api.ChatColor

/**
 * `TextComponentBuilder.() -> Unit`
 * @since 2.3.3
 */
typealias TextComponentBuildAction = TextComponentBuilder.() -> Unit

/**
 * [TextComponent] を生成する。
 * @see TextComponentBuilder
 * @param action [TextComponentBuilder] に対して実行する処理
 * @return [TextComponent]
 * @since 1.6.0
 */
inline fun buildTextComponent(action: TextComponentBuildAction) = TextComponentBuilder().apply(action).build()

/**
 * [BaseComponent] の hoverEvent, clickEvent を設定する。
 * @param hover [HoverEvent] default: null
 * @param click [ClickEvent] default: null
 * @return [TextComponentBuilder]
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
 * @since 2.2.4
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
 * 文字列とイベントから [TextComponent] を生成する。
 * @param text 文字列
 * @param hover [HoverEvent] default: null
 * @param click [ClickEvent] default: null
 * @return [TextComponent]
 * @since 2.2.2
 */
fun textComponent(
    text: String,
    hover: HoverEvent? = null,
    click: ClickEvent? = null,
) = TextComponent(text.toColor()).with(hover, click)

/**
 * 翻訳キーとイベントから [TranslatableComponent] を生成する。
 * @param translate 翻訳キー
 * @param hover [HoverEvent] default: null
 * @param click [ClickEvent] default: null
 * @return [TranslatableComponent]
 * @since 2.2.3
 */
fun translateComponent(
    translate: String,
    hover: HoverEvent? = null,
    click: ClickEvent? = null,
) = TranslatableComponent(translate).with(hover, click)

/**
 * [Material] とイベントから [TranslatableComponent] を生成する。
 * @param material 翻訳するアイテム
 * @param hover [HoverEvent] default: null
 * @param click [ClickEvent] default: null
 * @return [TranslatableComponent]
 * @since 2.2.3
 */
@UnsupportedMinecraftVersion(8, 9, 10, 11, 12)
fun translateComponent(
    material: Material,
    hover: HoverEvent? = null,
    click: ClickEvent? = null,
) = translateComponent((if (material.isBlock) "block" else "item") + ".minecraft." + material.key.key, hover, click)

/**
 * キーバインド識別子とイベントから [KeybindComponent] を生成する。
 * @param identifier 識別子 [net.md_5.bungee.api.chat.Keybinds]
 * @param hover [HoverEvent] default: null
 * @param click [ClickEvent] default: null
 * @return [KeybindComponent]
 * @since 2.2.3
 */
@UnsupportedMinecraftVersion(8, 9, 10, 11)
fun keybindComponent(
    identifier: String,
    hover: HoverEvent? = null,
    click: ClickEvent? = null,
) = KeybindComponent(identifier).with(hover, click)

/**
 * クリックした時にコマンドを実行する。
 * @param command 実行するコマンド
 * @return [ClickEvent.Action.RUN_COMMAND] の [ClickEvent]
 * @since 1.6.0
 */
fun clickRunCommand(command: String) = ClickEvent(ClickEvent.Action.RUN_COMMAND, command)

/**
 * クリックした時に文字列を自動入力する。
 * @param text 自動入力する文字列
 * @return [ClickEvent.Action.SUGGEST_COMMAND] の [ClickEvent]
 * @since 1.6.0
 */
fun clickTypeText(text: String) = ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, text)

/**
 * クリックした時にURLを開く。
 * @param url 開くURL
 * @return [ClickEvent.Action.OPEN_URL] の [ClickEvent]
 * @since 1.6.0
 */
fun clickOpenURL(url: String) = ClickEvent(ClickEvent.Action.OPEN_URL, url)

/**
 * クリックした時にクリップボードに文字列をコピーする。
 * @param text コピーする文字列
 * @return [ClickEvent.Action.COPY_TO_CLIPBOARD] の [ClickEvent]
 * @since 1.6.0
 */
@UnsupportedMinecraftVersion(8, 9, 10, 11, 12, 13, 14)
fun clickCopyToClipboard(text: String) = ClickEvent(ClickEvent.Action.COPY_TO_CLIPBOARD, text)

/**
 * ホバーした時に文字列を表示する。
 * @param text 表示する文字列
 * @return [HoverEvent.Action.SHOW_TEXT] の [HoverEvent]
 * @since 1.6.0
 */
@UnsupportedMinecraftVersion(8, 9, 10, 11, 12, 13, 14, 15)
fun hoverText(text: String) = HoverEvent(HoverEvent.Action.SHOW_TEXT, Text(text.toColor()))

/**
 * ホバーした時にアイテムを表示する。
 * @param itemStack 表示するアイテム
 * @return [HoverEvent.Action.SHOW_ITEM] の [HoverEvent]
 * @since 1.8.0
 */
@UnsupportedMinecraftVersion(8, 9, 10, 11, 12, 13, 14, 15)
fun hoverItem(itemStack: ItemStack) = HoverEvent(HoverEvent.Action.SHOW_ITEM, Item(itemStack.type.key.key, 1, ItemTag.ofNbt(itemStack.nbtTag)))
