@file:Suppress("DEPRECATION")

package com.github.syari.spigot.api.config.type

import com.github.syari.spigot.api.config.CustomConfig
import com.github.syari.spigot.api.config.converter.ConfigItemConverter
import com.github.syari.spigot.api.config.type.data.ConfigBooleanDataType
import com.github.syari.spigot.api.config.type.data.ConfigCoordinateDataType
import com.github.syari.spigot.api.config.type.data.ConfigDateDataType
import com.github.syari.spigot.api.config.type.data.ConfigDoubleDataType
import com.github.syari.spigot.api.config.type.data.ConfigEnchantmentByKeyDataType
import com.github.syari.spigot.api.config.type.data.ConfigEnchantmentByNameDataType
import com.github.syari.spigot.api.config.type.data.ConfigEntityTypeDataType
import com.github.syari.spigot.api.config.type.data.ConfigFloatDataType
import com.github.syari.spigot.api.config.type.data.ConfigIntDataType
import com.github.syari.spigot.api.config.type.data.ConfigInventoryDataType
import com.github.syari.spigot.api.config.type.data.ConfigItemStackDataType
import com.github.syari.spigot.api.config.type.data.ConfigLocationDataType
import com.github.syari.spigot.api.config.type.data.ConfigLongDataType
import com.github.syari.spigot.api.config.type.data.ConfigMaterialDataType
import com.github.syari.spigot.api.config.type.data.ConfigNumberDataType
import com.github.syari.spigot.api.config.type.data.ConfigParticleDataType
import com.github.syari.spigot.api.config.type.data.ConfigPotionEffectTypeDataType
import com.github.syari.spigot.api.config.type.data.ConfigSerializableInventoryDataType
import com.github.syari.spigot.api.config.type.data.ConfigSerializableItemStackDataType
import com.github.syari.spigot.api.config.type.data.ConfigSoundByKeyDataType
import com.github.syari.spigot.api.config.type.data.ConfigSoundByNameDataType
import com.github.syari.spigot.api.config.type.data.ConfigStringDataType
import com.github.syari.spigot.api.config.type.data.ConfigUUIDDataType
import com.github.syari.spigot.api.config.type.data.ConfigVectorDataType
import com.github.syari.spigot.api.config.type.data.ConfigWorldDataType
import com.github.syari.spigot.api.config.type.data.list.ConfigCoordinateListDataType
import com.github.syari.spigot.api.config.type.data.list.ConfigEnchantmentByKeyListDataType
import com.github.syari.spigot.api.config.type.data.list.ConfigEnchantmentByNameListDataType
import com.github.syari.spigot.api.config.type.data.list.ConfigEntityTypeListDataType
import com.github.syari.spigot.api.config.type.data.list.ConfigItemStackListDataType
import com.github.syari.spigot.api.config.type.data.list.ConfigLocationListDataType
import com.github.syari.spigot.api.config.type.data.list.ConfigMaterialListDataType
import com.github.syari.spigot.api.config.type.data.list.ConfigParticleListDataType
import com.github.syari.spigot.api.config.type.data.list.ConfigPotionEffectTypeListDataType
import com.github.syari.spigot.api.config.type.data.list.ConfigSerializableItemStackListDataType
import com.github.syari.spigot.api.config.type.data.list.ConfigSoundByKeyListDataType
import com.github.syari.spigot.api.config.type.data.list.ConfigSoundByNameListDataType
import com.github.syari.spigot.api.config.type.data.list.ConfigStringListDataType
import com.github.syari.spigot.api.config.type.data.list.ConfigUUIDListDataType
import com.github.syari.spigot.api.config.type.data.list.ConfigVectorListDataType
import com.github.syari.spigot.api.config.type.data.list.ConfigWorldListDataType

/**
 * コンフィグデータタイプ
 * @param T データ型
 * @since 1.3.0
 */
interface ConfigDataType<T> {
    /**
     * データ型の名前。
     * @since 1.3.0
     */
    val typeName: String

    /**
     * @param config [CustomConfig]
     * @param path コンフィグパス
     * @param notFoundError 存在しないデータの場合にエラーを出す
     * @since 1.3.0
     */
    fun get(
        config: CustomConfig,
        path: String,
        notFoundError: Boolean
    ): T?

    /**
     * @param config [CustomConfig]
     * @param path コンフィグパス
     * @param notFoundError 存在しないデータの場合にエラーを出す
     * @param default デフォルト値
     * @since 1.3.0
     */
    fun get(
        config: CustomConfig,
        path: String,
        notFoundError: Boolean,
        default: T
    ): T {
        return get(config, path, notFoundError) ?: default
    }

    /**
     * @param config [CustomConfig]
     * @param path コンフィグパス
     * @param value 設定する値
     * @since 1.3.0
     */
    fun set(
        config: CustomConfig,
        path: String,
        value: T?
    )

    /**
     * 定義済みのコンフィグデータタイプの一覧。
     * @since 1.3.0
     */
    @Suppress("FunctionName")
    companion object {
        /**
         * 数字。別の数字型に変換して使う。
         * @see ConfigDataType.Int
         * @see ConfigDataType.Long
         * @see ConfigDataType.Float
         * @see ConfigDataType.Double
         * @since 1.3.0
         */
        val Number = ConfigNumberDataType

        /**
         * 整数型。
         * @since 1.3.0
         */
        val Int = ConfigIntDataType

        /**
         * [ConfigDataType.Int] より大きい整数型。
         * @since 1.3.0
         */
        val Long = ConfigLongDataType

        /**
         * 浮動小数型。
         * @since 1.3.0
         */
        val Float = ConfigFloatDataType

        /**
         * [ConfigDataType.Float] よりも精度の高い浮動小数型。
         * @since 1.3.0
         */
        val Double = ConfigDoubleDataType

        /**
         * 真偽型。
         * @since 1.3.0
         */
        val Boolean = ConfigBooleanDataType

        /**
         * 文字列型。
         * @since 1.3.0
         */
        val String = ConfigStringDataType

        /**
         * 文字列型のリスト。
         * @see ConfigDataType.String
         * @since 1.3.0
         */
        val StringList = ConfigStringListDataType

        /**
         * 日付型。
         * @since 1.3.0
         */
        val Date = ConfigDateDataType

        /**
         * ベクトル。X, Y, Z の値を持つ。
         * @since 1.4.0
         */
        val Vector = ConfigVectorDataType

        /**
         * ベクトルのリスト。
         * @see ConfigDataType.Vector
         * @since 1.4.0
         */
        val VectorList = ConfigVectorListDataType

        /**
         * 座標。X, Y, Z, Yaw, Pitch の値を持つ。
         * @since 1.4.0
         */
        val Coordinate = ConfigCoordinateDataType

        /**
         * 座標のリスト。
         * @see ConfigDataType.Coordinate
         * @since 1.4.0
         */
        val CoordinateList = ConfigCoordinateListDataType

        /**
         * ワールド座標。World, X, Y, Z, Yaw, Pitch の値を持つ。
         * @since 1.3.0
         */
        val Location = ConfigLocationDataType

        /**
         * ワールド座標のリスト。
         * @see ConfigDataType.Location
         * @since 1.3.0
         */
        val LocationList = ConfigLocationListDataType

        /**
         * ワールド。
         * @since 1.3.0
         */
        val World = ConfigWorldDataType

        /**
         * ワールドのリスト。
         * @see ConfigDataType.World
         * @since 1.3.0
         */
        val WorldList = ConfigWorldListDataType

        /**
         * アイテムスタック。シリアライズを使用して保存・読込を行う。
         * @since 1.6.0
         */
        val SerializableItemStack = ConfigSerializableItemStackDataType

        /**
         * アイテムスタックのリスト。シリアライズを使用して保存・読込を行う。
         * @see ConfigDataType.SerializableItemStack
         * @since 1.6.0
         */
        val SerializableItemStackList = ConfigSerializableItemStackListDataType

        /**
         * インベントリ。シリアライズを使用して保存・読込を行う。
         * @since 1.6.0
         */
        val SerializableInventory = ConfigSerializableInventoryDataType

        /**
         * マテリアル。
         * @since 1.3.0
         */
        val Material = ConfigMaterialDataType

        /**
         * マテリアルのリスト。
         * @see ConfigDataType.Material
         * @since 1.3.0
         */
        val MaterialList = ConfigMaterialListDataType

        /**
         * エンティティの種類。
         * @since 1.5.0
         */
        val EntityType = ConfigEntityTypeDataType

        /**
         * エンティティの種類のリスト。
         * @see ConfigDataType.EntityType
         * @since 1.5.0
         */
        val EntityTypeList = ConfigEntityTypeListDataType

        /**
         * エンチャント。名前から取得する。
         * @since 1.5.1
         */
        @Deprecated("Enchantment::getByName は非推奨です。", ReplaceWith("EnchantmentByKey"))
        val EnchantmentByName = ConfigEnchantmentByNameDataType

        /**
         * エンチャントのリスト。名前から取得する。
         * @see ConfigDataType.EnchantmentByName
         * @since 1.5.1
         */
        @Deprecated("Enchantment::getByName は非推奨です。", ReplaceWith("EnchantmentByKeyList"))
        val EnchantmentByNameList = ConfigEnchantmentByNameListDataType

        /**
         * エンチャント。固有名から取得する。
         * @since 1.5.1
         */
        val EnchantmentByKey = ConfigEnchantmentByKeyDataType

        /**
         * エンチャントのリスト。固有名から取得する。
         * @see ConfigDataType.EnchantmentByKey
         * @since 1.5.1
         */
        val EnchantmentByKeyList = ConfigEnchantmentByKeyListDataType

        /**
         * ポーションエフェクトの種類。
         * @since 1.5.0
         */
        val PotionEffectType = ConfigPotionEffectTypeDataType

        /**
         * ポーションエフェクトの種類のリスト。
         * @see ConfigDataType.PotionEffectType
         * @since 1.5.0
         */
        val PotionEffectTypeList = ConfigPotionEffectTypeListDataType

        /**
         * サウンドの種類。名前から取得する。
         * @since 1.6.0
         */
        val SoundByName = ConfigSoundByNameDataType

        /**
         * サウンドの種類のリスト。名前から取得する。
         * @see ConfigDataType.SoundByName
         * @since 1.6.0
         */
        val SoundByNameList = ConfigSoundByNameListDataType

        /**
         * サウンドの種類。固有名から取得する。
         * @since 1.6.0
         */
        val SoundByKey = ConfigSoundByKeyDataType

        /**
         * サウンドの種類のリスト。固有名から取得する。
         * @see ConfigDataType.SoundByKey
         * @since 1.6.0
         */
        val SoundByKeyList = ConfigSoundByKeyListDataType

        /**
         * パーティクルの種類。
         * @since 1.5.0
         */
        val Particle = ConfigParticleDataType

        /**
         * パーティクルの種類のリスト。
         * @see ConfigDataType.Particle
         * @since 1.5.0
         */
        val ParticleList = ConfigParticleListDataType

        /**
         * UUID。
         * @since 1.3.4
         */
        val UUID = ConfigUUIDDataType

        /**
         * UUIDのリスト。
         * @see ConfigDataType.UUID
         * @since 1.3.4
         */
        val UUIDList = ConfigUUIDListDataType

        /**
         * アイテムスタック。[ConfigItemConverter] を使用して保存・読込を行う。
         * @since 1.7.0
         */
        fun ItemStack(itemConverter: ConfigItemConverter) = ConfigItemStackDataType(itemConverter)

        /**
         * アイテムスタックのリスト。[ConfigItemConverter] を使用して保存・読込を行う。
         * @see ConfigDataType.ItemStack
         * @since 1.7.0
         */
        fun ItemStackList(itemConverter: ConfigItemConverter) = ConfigItemStackListDataType(itemConverter)

        /**
         * インベントリ。[ConfigItemConverter] を使用して保存・読込を行う。
         * @since 1.7.0
         */
        fun Inventory(itemConverter: ConfigItemConverter) = ConfigInventoryDataType(itemConverter)
    }
}
