package com.github.syari.spigot.api.sample.inventory

import com.github.syari.spigot.api.command.command
import com.github.syari.spigot.api.inventory.inventory
import com.github.syari.spigot.api.sample.inventory.Main.Companion.plugin
import com.github.syari.spigot.api.sound.playSound
import org.bukkit.Material
import org.bukkit.Sound
import org.bukkit.entity.Player
import org.bukkit.event.inventory.ClickType

object OpenInventoryCommand {
    fun register() {
        plugin.command("open-inv") {
            execute {
                val player = sender as? Player ?: return@execute
                inventory("&9&lサンプルインベントリ", 4) {
                    item(4, Material.PUFFERFISH) {
                        onClick {
                            player.playSound(Sound.ENTITY_PLAYER_LEVELUP)
                        }
                    }
                    item(22, Material.ORANGE_STAINED_GLASS_PANE, "&a&l◀ &c&l▶") {
                        onClick(ClickType.LEFT) {
                            item(22, Material.LIME_STAINED_GLASS_PANE, "&a&l◀ &c&l▶")
                        }
                        onClick(ClickType.MIDDLE) {
                            item(22, Material.ORANGE_STAINED_GLASS_PANE, "&a&l◀ &c&l▶")
                        }
                        onClick(ClickType.RIGHT) {
                            item(22, Material.RED_STAINED_GLASS_PANE, "&a&l◀ &c&l▶")
                        }
                    }
                }.open(player)
            }
        }
    }
}
