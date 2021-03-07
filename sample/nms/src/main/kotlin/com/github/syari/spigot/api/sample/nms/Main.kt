package com.github.syari.spigot.api.sample.nms

import com.github.syari.spigot.api.item.displayName
import com.github.syari.spigot.api.item.nbtTag
import com.github.syari.spigot.api.nms.NMS_VERSION
import org.bukkit.Material
import org.bukkit.inventory.ItemStack
import org.bukkit.plugin.java.JavaPlugin

class Main : JavaPlugin() {
    override fun onEnable() {
        logger.info("NMS_Version: $NMS_VERSION")

        ItemStack(Material.LAVA_BUCKET).apply {
            displayName = "&cLava Bucket"
        }.run { logger.info("NBT Tag: $nbtTag") }

        ItemStack(Material.LAVA_BUCKET).run { logger.info("NBT Tag: $nbtTag") }
    }
}
