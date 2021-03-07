package com.github.syari.spigot.api.sample.nms

import com.github.syari.spigot.api.nms.NMS_VERSION
import com.github.syari.spigot.api.nms.getNMSClass
import com.github.syari.spigot.api.util.item.displayName
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

    val ItemStack.nbtTag: String
        get() {
            val craftItemStack = getNMSClass("org.bukkit.craftbukkit.%s.inventory.CraftItemStack")
                .getDeclaredMethod("asNMSCopy", ItemStack::class.java)
                .invoke(null, this)
            val nmsItemStackClass = getNMSClass("net.minecraft.server.%s.ItemStack")
            val nbtTagCompoundClass = getNMSClass("net.minecraft.server.%s.NBTTagCompound")
            val nbtTagCompound = nmsItemStackClass
                .getDeclaredMethod("getTag")
                .invoke(craftItemStack) ?: run {
                val nbtTagCompound = nbtTagCompoundClass.getConstructor().newInstance()
                nmsItemStackClass
                    .getDeclaredMethod("setTag", nbtTagCompoundClass)
                    .invoke(craftItemStack, nbtTagCompound)
                nbtTagCompound
            }
            return nbtTagCompoundClass
                .getDeclaredMethod("toString")
                .invoke(nbtTagCompound) as String
        }
}
