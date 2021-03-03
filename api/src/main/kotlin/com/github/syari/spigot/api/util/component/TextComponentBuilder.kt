package com.github.syari.spigot.api.util.component

import com.github.syari.spigot.api.util.string.toColor
import net.md_5.bungee.api.chat.ClickEvent
import net.md_5.bungee.api.chat.HoverEvent
import net.md_5.bungee.api.chat.TextComponent

/**
 * [TextComponent] のビルダークラス
 * @since 1.6.0
 */
class TextComponentBuilder {
    private val components = mutableListOf<Component>()

    /**
     * 末尾に文字列を挿入する。
     * @param text 文字列
     * @param hover ホバーイベント
     * @param click クリックイベント
     * @since 1.6.0
     */
    fun append(
        text: String,
        hover: HoverEvent? = null,
        click: ClickEvent? = null
    ) = apply {
        components.add(Component(text, hover, click))
    }

    /**
     * 末尾に改行を挿入する。
     * @since 1.6.0
     */
    fun appendLine() = apply {
        components.add(Component.NewLine)
    }

    /**
     * 末尾に文字列を挿入し、改行する。
     * @param text 文字列
     * @param hover ホバーイベント
     * @param click クリックイベント
     * @since 1.6.0
     */
    fun appendLine(
        text: String,
        hover: HoverEvent? = null,
        click: ClickEvent? = null
    ) = append(text, hover, click).appendLine()

    /**
     * [TextComponent] に変換する。
     * @since 1.6.0
     */
    fun build() = TextComponent().apply {
        components.forEach {
            TextComponent(it.text.toColor()).apply {
                hoverEvent = it.hover
                clickEvent = it.click
            }.let(::addExtra)
        }
    }

    private class Component(val text: String, val hover: HoverEvent?, val click: ClickEvent?) {
        companion object {
            val NewLine = Component("\n", null, null)
        }
    }
}
