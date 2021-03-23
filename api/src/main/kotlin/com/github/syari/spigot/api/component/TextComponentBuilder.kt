package com.github.syari.spigot.api.component

import net.md_5.bungee.api.chat.ClickEvent
import net.md_5.bungee.api.chat.HoverEvent
import net.md_5.bungee.api.chat.TextComponent

/**
 * [TextComponent] のビルダークラス
 * @since 1.6.0
 */
class TextComponentBuilder {
    private val components = mutableListOf<TextComponent>()

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
    ) = append(textComponent(text, hover, click))

    /**
     * 末尾に [TextComponent] を挿入する。
     * @param component
     * @since 2.2.2
     */
    fun append(component: TextComponent) = apply {
        components.add(component)
    }

    /**
     * 末尾に改行を挿入する。
     * @since 1.6.0
     */
    fun appendLine() = append(NewLine)

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
     * 末尾に [TextComponent] を挿入し、改行する。
     * @param component
     * @since 2.2.2
     */
    fun appendLine(component: TextComponent) = append(component).appendLine()

    /**
     * [TextComponent] に変換する。
     * @since 1.6.0
     */
    fun build() = TextComponent().apply {
        components.forEach(::addExtra)
    }

    companion object {
        val NewLine = TextComponent(System.lineSeparator())
    }
}
