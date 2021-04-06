package com.github.syari.spigot.api.message

import com.github.syari.spigot.api.UnsupportedMinecraftVersion
import com.github.syari.spigot.api.string.toColor
import net.md_5.bungee.api.ChatMessageType
import net.md_5.bungee.api.chat.TextComponent
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

/**
 * チャット欄に色付きメッセージを送信する。
 * @param message 送信するメッセージ
 * @since 2.3.1
 */
fun CommandSender.sendChatMessage(message: String) {
    sendMessage(message.toColor())
}

/**
 * アクションバーに色付きメッセージを送信する。
 * @param message 送信するメッセージ
 * @since 2.3.1
 */
@UnsupportedMinecraftVersion(8)
fun Player.sendActionMessage(message: String) {
    spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent(message.toColor()))
}

/**
 * タイトルに色付きメッセージを送信する。
 * @param title タイトル default: null
 * @param subtitle サブタイトル default: null
 * @param fadeIn フェードイン default: 10
 * @param stay 表示時間 default: 70
 * @param fadeOut フェードアウト default: 20
 * @since 2.3.1
 */
@UnsupportedMinecraftVersion(8, 9, 10)
fun Player.sendTitleMessage(
    title: String? = null,
    subtitle: String? = null,
    fadeIn: Int = 10,
    stay: Int = 70,
    fadeOut: Int = 20
) {
    sendTitle(title?.toColor(), subtitle?.toColor(), fadeIn, stay, fadeOut)
}
