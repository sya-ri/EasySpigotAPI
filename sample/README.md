# Sample

## [Command](command)
コマンドを簡単に作成することができます。`plugin.yml` にコマンドを書く必要はありません。

```kotlin
plugin.command("teleport") {
    description = "テレポート系の処理を行う"
    aliases = listOf("tp", "sample-teleport")

    // タブ補完の設定を行える
    tab {
        // 引数が何も入力されていない場合の補完候補
        argument {
            add("here")
            addAll(onlinePlayerNames)
        }

        // 最初の引数が here の場合の補完候補
        argument("here **") {
            addAll(onlinePlayerNames)
        }
    }

    // 実行時の処理を設定できる
    execute {
        // ...
    }
}
```

## Component
TextComponent 関連の便利な関数・クラスを追加します。

```kotlin
/**
 * [TextComponent] のビルダークラス
 */
class TextComponentBuilder {
    /**
     * 末尾に文字列を挿入する。
     * @param text 文字列
     * @param hover ホバーイベント
     * @param click クリックイベント
     */
    fun append(
        text: String,
        hover: HoverEvent? = null,
        click: ClickEvent? = null
    ): TextComponent

    /**
     * 末尾に改行を挿入する。
     */
    fun appendLine(): TextComponent

    /**
     * 末尾に文字列を挿入し、改行する。
     * @param text 文字列
     * @param hover ホバーイベント
     * @param click クリックイベント
     */
    fun appendLine(
        text: String,
        hover: HoverEvent? = null,
        click: ClickEvent? = null
    ): TextComponent

    /**
     * [TextComponent] に変換する。
     */
    fun build(): TextComponent
}

/**
 * @see TextComponentBuilder
 */
inline fun buildTextComponent(action: TextComponentBuilder.() -> Unit): TextComponent

/**
 * [ClickEvent.Action.RUN_COMMAND] の [ClickEvent]
 */
fun clickRunCommand(command: String): ClickEvent

/**
 * [ClickEvent.Action.SUGGEST_COMMAND] の [ClickEvent]
 */
fun clickTypeText(text: String): ClickEvent

/**
 * [ClickEvent.Action.OPEN_URL] の [ClickEvent]
 */
fun clickOpenURL(url: String): ClickEvent

/**
 * [ClickEvent.Action.COPY_TO_CLIPBOARD] の [ClickEvent]
 */
fun clickCopyToClipboard(text: String): ClickEvent

/**
 * [HoverEvent.Action.SHOW_TEXT] の [HoverEvent]
 */
fun hoverText(text: String): HoverEvent

/**
 * [HoverEvent.Action.SHOW_ITEM] の [HoverEvent]
 */
fun hoverItem(itemStack: ItemStack): HoverEvent
```

## [Config](config)
コンフィグを簡単に読み込むことができます。

```kotlin
plugin.config(sender, "config.yml") {
    val message: String? = get("message", ConfigDataType.String)
    // ...
}

// 複数ファイルにまたがったコンフィグも扱えます。
plugin.configDirectory(sender, "mobs") {
    // id が指定されていなければファイル名を使用します。
    val id: String = get("id", ConfigDataType.String, file.nameWithoutExtension)
    section("attribute")?.forEach { name ->
        val level = get("attribute.$name.level", ConfigDataType.Int, 1)
        // ...
    }
    // ...
}
```

## [Event / Register](event-register)
イベントを簡単に定義・登録することができます。

```kotlin
// event<>{} で定義できる
object EventListener : EventRegister {
    override fun Events.register() {
        // 入退出メッセージを変更する
        event<PlayerJoinEvent> {
            it.joinMessage = ">> Join ${it.player.displayName}"
        }
        event<PlayerQuitEvent> {
            it.quitMessage = ">> Quit ${it.player.displayName}"
        }

        // プレイヤーに対するダメージをキャンセルする
        cancelEventIf<EntityDamageEvent> {
            it.entity is Player
        }

        // OP 以外の移動をキャンセルする
        cancelEventIfNot<PlayerMoveEvent> {
            it.player.isOp
        }
    }
}
```

## Item
Item 関連の便利な関数を追加します。

```kotlin
/**
 * 表示名が存在するか取得する。
 * @see ItemMeta.hasDisplayName
 */
fun ItemStack.hasDisplayName(): Boolean

/**
 * 表示名。取得する時は [hasDisplayName] が真である場合のみにする。
 * @see ItemMeta.getDisplayName
 * @see ItemMeta.setDisplayName
 */
var ItemStack.displayName: String?

/**
 * 説明文が存在するか取得する。
 * @see ItemMeta.hasLore
 */
fun ItemStack.hasLore(): Boolean

/**
 * 説明文。取得する時は [hasLore] が真である場合でのみにする。
 * @see ItemMeta.getLore
 * @see ItemMeta.setLore
 */
var ItemStack.lore: List<String>

/**
 * アイテムの説明文を変更する。
 */
inline fun ItemStack.editLore(action: MutableList<String>.() -> Unit)

/**
 * カスタムモデルデータが設定されているか取得する。
 * @see ItemMeta.hasCustomModelData
 */
fun ItemStack.hasCustomModelData(): Boolean

/**
 * カスタムモデルデータ。取得する時は [hasCustomModelData] が真である場合でのみにする。
 * @see ItemMeta.getCustomModelData
 * @see ItemMeta.setCustomModelData
 */
var ItemStack.customModelData: Int?

/**
 * エンチャントが存在するか取得する。
 * @see ItemMeta.hasEnchants
 */
fun ItemStack.hasEnchants(): Boolean

/**
 * 特定のエンチャントが存在するか取得する。
 * @see ItemMeta.hasEnchant
 */
fun ItemStack.hasEnchant(enchant: Enchantment): Boolean

/**
 * 特定のエンチャントのレベルを取得する。
 * @see ItemMeta.getEnchantLevel
 */
fun ItemStack.getEnchantLevel(enchant: Enchantment): Int

/**
 * 全てのエンチャントを取得する。
 * @see ItemMeta.getEnchants
 */
var ItemStack.enchants: Map<Enchantment, Int>

/**
 * エンチャントを追加する。
 * @see ItemMeta.addEnchant
 */
fun ItemStack.addEnchant(enchant: Enchantment, level: Int, ignoreLevelRestriction: Boolean = true)

/**
 * エンチャントを削除する。
 * @see ItemMeta.removeEnchant
 */
fun ItemStack.removeEnchant(enchant: Enchantment)

/**
 * エンチャントが競合するかどうかを取得する。
 * @see ItemMeta.hasConflictingEnchant
 */
fun ItemStack.hasConflictingEnchant(enchant: Enchantment): Boolean

/**
 * アイテムフラグを追加する。
 * @see ItemMeta.addItemFlags
 */
fun ItemStack.addItemFlags(vararg itemFlags: ItemFlag)

/**
 * アイテムフラグを削除する。
 * @see ItemMeta.removeItemFlags
 */
fun ItemStack.removeItemFlags(vararg itemFlags: ItemFlag)

/**
 * アイテムフラグ。
 * @see ItemMeta.getItemFlags
 */
var ItemStack.itemFlags: Set<ItemFlag>

/**
 * 特定のアイテムフラグが存在するか取得する。
 * @see ItemMeta.hasItemFlag
 */
fun ItemStack.hasItemFlag(flag: ItemFlag): Boolean

/**
 * 耐久無限。
 */
var ItemStack.isUnbreakable: Boolean

/**
 * [AttributeModifier] が存在するか取得する。
 * @see ItemMeta.hasAttributeModifiers
 */
fun ItemStack.hasAttributeModifiers(): Boolean

/**
 * 全ての装備箇所で影響する [AttributeModifier] の一覧を取得する。
 * @see ItemMeta.getAttributeModifiers
 * @see ItemMeta.setAttributeModifiers
 */
var ItemStack.attributeModifiers: Multimap<Attribute, AttributeModifier>

/**
 * 特定の装備箇所で影響する [AttributeModifier] の一覧を取得する。
 * @see ItemMeta.getAttributeModifiers
 */
fun ItemStack.getAttributeModifiers(slot: EquipmentSlot): Multimap<Attribute, AttributeModifier>

/**
 * [Attribute] に関する [AttributeModifier] の一覧を取得する。
 * @see ItemMeta.getAttributeModifiers
 */
fun ItemStack.getAttributeModifiers(attribute: Attribute): Collection<AttributeModifier>

/**
 * [AttributeModifier] を追加する。
 * @see ItemMeta.addAttributeModifier
 */
fun ItemStack.addAttributeModifier(attribute: Attribute, modifier: AttributeModifier): Boolean

/**
 * [AttributeModifier] を削除する。
 * @see ItemMeta.removeAttributeModifier
 */
fun ItemStack.removeAttributeModifier(attribute: Attribute): Boolean

/**
 * [AttributeModifier] を削除する。
 * @see ItemMeta.removeAttributeModifier
 */
fun ItemStack.removeAttributeModifier(slot: EquipmentSlot): Boolean

/**
 * [AttributeModifier] を削除する。
 * @see ItemMeta.removeAttributeModifier
 */
fun ItemStack.removeAttributeModifier(attribute: Attribute, modifier: AttributeModifier): Boolean

/**
 * [ItemMeta] に対して変更を加える。
 */
inline fun ItemStack.editItemMeta(action: ItemMeta.() -> Unit)

/**
 * [ItemMeta] に対して変更を加える。
 */
inline fun <reified T : ItemMeta> ItemStack.editItemMeta(action: T.() -> Unit)

/**
 * NBT タグを取得する。
 */
val ItemStack.nbtTag: String
```

## NMS
NMSを使うための関数・クラスを追加します。

```kotlin

/**
 * NMS のバージョン。
 */
val NMS_VERSION = Bukkit.getServer()::class.java.`package`.name.substring(23)

/**
 * NMS のクラスを取得する。`%s` が [NMS_VERSION] に置き換わる。
 */
fun getNMSClass(className: String): Class<*> = Class.forName(className.format(NMS_VERSION))


/**
 * NMS を扱う為の基底クラス。
 */
abstract class NMSWrapper

/**
 * `org.bukkit.craftbukkit.%s.inventory.CraftItemStack` を扱う。
 */
class CraftItemStackWrapper

/**
 * `net.minecraft.server.%s.NBTTagCompound` を扱う。
 */
class NBTTagCompoundWrapper

/**
 * `net.minecraft.server.%s.ItemStack` を扱う。
 */
class NMSItemStackWrapper
```

## [Scheduler](scheduler)
スケジューラを簡単に使うことができます。

```kotlin
// 10秒後に実行されます
runTaskLater(10 * 20) {
    // async: false ... サーバーに同期した処理です
    server.broadcastMessage("プラグインが有効になってから10秒経ちました")
}

// 30秒毎に実行されます
runTaskTimer(30 * 20, async = true) {
    // async: true ... サーバーに同期していない処理です
    server.onlinePlayers.forEach {
        it.giveExpLevels(1)
    }
}
```

## [String](string)
String 関連の便利な関数を追加します。

```kotlin
/**
 * [ChatColor.translateAlternateColorCodes] を実行する。`&` を文字コードとして認識する。
 */
fun String.toColor(): String

/**
 * [ChatColor.stripColor] を実行する。
 */
fun String.toUncolor(): String
```

## [UUID](uuid)
UUID 関連の便利な関数・クラスを追加します。

```kotlin
/**
 * [UUID.fromString] を実行する
 */
fun uuid(name: String): UUID

/**
 * [uuid] を実行するが、例外が発生した場合は null を返す
 */
fun uuidOrNull(name: String): UUID?

/**
 * [Entity] が持つ [UUID] を操作しやすくしたクラス
 *
 * 変数に保存する際には [Entity] ではなく [UUID] で保存することを推奨する
 */
data class UUIDEntity(val uniqueId: UUID)

/**
 * [Player] が持つ [UUID] を操作しやすくしたクラス
 *
 * 変数に保存する際には [Player] や [OfflinePlayer] ではなく [UUID] で保存することを推奨する
 */
data class UUIDPlayer(val uniqueId: UUID)
```

例として UUIDPlayer を使用した処理を紹介しています。

```kotlin
object PlayerJoinChecker : EventRegister {
    /**
     * [UUIDPlayer] を使うと再ログインしても同一プレイヤーとして認識される
     *
     * 2回目のログイン以降は true と出力される
     */
    private val useUuidPlayer = mutableSetOf<UUIDPlayer>()

    /**
     * [Player] を使うと再ログインすると別のインスタンスになる
     *
     * 2回目のログイン以降も false と出力される
     */
    private val usePlayer = mutableSetOf<Player>()
    
    // ... 省略
}
```

## World
World 関連の便利なクラスを追加します。

```kotlin
/**
 * ワールドを考慮せず、座標情報のみを保持できるクラス。
 */
data class Coordinate(
    var x: Double,
    var y: Double,
    var z: Double,
    var yaw: Float = 0F,
    var pitch: Float = 0F
) : ConfigurationSerializable
```

```kotlin
/**
 * 領域を表す。
 */
class Region(
    val world: World,
    pos1: Vector,
    pos2: Vector
)
```

## Plugin Using EasySpigotAPI
EasySpigotAPI を使用してプラグインを製作した場合は以下に追加してください。

- [SecondStoryServer/SS-Plugins](https://github.com/SecondStoryServer/SS-Plugins)
- [sya-ri/TalkWithNearPlayer](https://github.com/sya-ri/TalkWithNearPlayer)
- [sya-ri/WorldBorderTester](https://github.com/sya-ri/WorldBorderTester)

[<< 戻る](../README.md)