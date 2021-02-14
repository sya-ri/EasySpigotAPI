package com.github.syari.spigot.api.sample.config

import com.github.syari.spigot.api.config.config
import com.github.syari.spigot.api.config.configDirectory
import com.github.syari.spigot.api.config.type.ConfigDataType
import com.github.syari.spigot.api.sample.config.Main.Companion.plugin
import org.bukkit.command.CommandSender

object ConfigLoader {
    fun load(sender: CommandSender) {
        // 特定のファイルを読み込みます。固有の設定をコンフィグに格納する時に使います。
        plugin.config(sender, "depth1/depth2/config.yml") {
            val stringValue = get("string_value", ConfigDataType.String)
            plugin.server.broadcastMessage("($filePath) string_value: $stringValue")
        }
        // フォルダの中に存在する全てのファイルを読み込みます。
        plugin.configDirectory(sender, "depth1/depth2/depth3") {
            val intValue = get("int_value", ConfigDataType.Int)
            plugin.server.broadcastMessage("($filePath) int_value: $intValue")
        }
    }
}
