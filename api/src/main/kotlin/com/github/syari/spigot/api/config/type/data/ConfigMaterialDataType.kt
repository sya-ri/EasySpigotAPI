package com.github.syari.spigot.api.config.type.data

import com.github.syari.spigot.api.config.CustomConfig
import com.github.syari.spigot.api.config.type.ConfigDataType
import org.bukkit.Material

/**
 * [ConfigDataType.Material]
 * @since 1.3.0
 */
object ConfigMaterialDataType : ConfigDataType<Material> {
    /**
     * データ型の名前
     * @since 1.3.0
     */
    override val typeName = "Material"

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
    ): Material? {
        return config.get(path, ConfigDataType.String, notFoundError)?.toUpperCase()?.let(Material::getMaterial)
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
        value: Material?
    ) {
        config.set(path, ConfigDataType.String, value?.name)
    }
}
