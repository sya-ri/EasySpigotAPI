package com.github.syari.spigot.api.util.component

import com.github.syari.spigot.api.util.string.toColor
import net.md_5.bungee.api.chat.ClickEvent
import net.md_5.bungee.api.chat.HoverEvent
import net.md_5.bungee.api.chat.hover.content.Text

/**
 * @see TextComponentBuilder
 * @since 1.6.0
 */
inline fun buildTextComponent(action: TextComponentBuilder.() -> Unit) = TextComponentBuilder().apply(action).build()

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
fun clickCopyToClipboard(text: String) = ClickEvent(ClickEvent.Action.COPY_TO_CLIPBOARD, text)

/**
 * [HoverEvent.Action.SHOW_TEXT] の [HoverEvent]
 * @since 1.6.0
 */
fun hoverText(text: String) = HoverEvent(HoverEvent.Action.SHOW_TEXT, Text(text.toColor()))
