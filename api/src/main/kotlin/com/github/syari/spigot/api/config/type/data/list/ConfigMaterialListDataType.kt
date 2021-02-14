package com.github.syari.spigot.api.config.type.data.list

import com.github.syari.spigot.api.config.CustomConfig
import com.github.syari.spigot.api.config.type.ConfigDataType
import org.bukkit.Material

/**
 * [ConfigDataType.MaterialList]
 * @since 1.3.0
 */
object ConfigMaterialListDataType : ConfigDataType<List<Material>> {
    /**
     * データ型の名前
     * @since 1.3.0
     */
    override val typeName = "List<Material>"

    /**
     * @param config [CustomConfig]
     * @param path コンフィグパス
     * @param notFoundError 存在しないデータの場合にエラーを出す
     * @since 1.3.0
     */
    override fun get(
        config: CustomConfig,
        path: String,
        notFoundError: Boolean
    ): List<Material> {
        return mutableListOf<Material>().apply {
            config.get(path, ConfigDataType.StringList, notFoundError)?.forEach {
                val material = Material.getMaterial(it.toUpperCase()) ?: return@forEach config.nullError("$path.$it", "Material")
                add(material)
            }
        }
    }

    /**
     * @param config [CustomConfig]
     * @param path コンフィグパス
     * @param value 設定する値
     * @since 1.3.0
     */
    override fun set(
        config: CustomConfig,
        path: String,
        value: List<Material>?
    ) {
        config.set(path, ConfigDataType.StringList, value?.map(Material::name))
    }
}
