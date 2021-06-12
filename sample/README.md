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

- [component/extension.kt](../api/src/main/kotlin/com/github/syari/spigot/api/component/extension.kt)
- [component/TextComponentBuilder.kt](../api/src/main/kotlin/com/github/syari/spigot/api/component/TextComponentBuilder.kt)

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

## [Event](event)
イベントを簡単に定義・登録することができます。

```kotlin
// event<>{} で定義できる
object EventListener {
    override fun register() {
        plugin.events {
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
}
```

## Inventory
インベントリを簡単に扱うことができます。使用する場合は必ず、`EasySpigotAPIOption::useInventory` を実行してください。

```kotlin
class Main : JavaPlugin() {
    override fun onEnable() {
        CustomInventory.onEnable(this)
    }
}

fun open(player: Player) {
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
```

## Item
Item 関連の便利な関数を追加します。

- [item/extension.kt](../api/src/main/kotlin/com/github/syari/spigot/api/item/extension.kt)

## Message
メッセージ関連の便利な関数を追加します。

- [message/extension.kt](../api/src/main/kotlin/com/github/syari/spigot/api/message/extension.kt)

## NMS
NMSを使うための関数・クラスを追加します。

- [nms/CraftItemStackWrapper.kt](../api/src/main/kotlin/com/github/syari/spigot/api/nms/CraftItemStackWrapper.kt)
- [nms/extension.kt](../api/src/main/kotlin/com/github/syari/spigot/api/nms/extension.kt)
- [nms/NBTTagCompoundWrapper.kt](../api/src/main/kotlin/com/github/syari/spigot/api/nms/NBTTagCompoundWrapper.kt)
- [nms/NMSItemStackWrapper.kt](../api/src/main/kotlin/com/github/syari/spigot/api/nms/NMSItemStackWrapper.kt)
- [nms/NMSWrapper.kt](../api/src/main/kotlin/com/github/syari/spigot/api/nms/NMSWrapper.kt)

## Particle
パーティクル関連の便利な関数を追加します。

- [particle/extension.kt](../api/src/main/kotlin/com/github/syari/spigot/api/particle/extension.kt)

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

## Sound
音関連の便利な関数を追加します。

- [sound/extension.kt](../api/src/main/kotlin/com/github/syari/spigot/api/sound/extension.kt)

## [String](string)
String 関連の便利な関数を追加します。


- [string/extension.kt](../api/src/main/kotlin/com/github/syari/spigot/api/string/extension.kt)

## [UUID](uuid)
UUID 関連の便利な関数・クラスを追加します。

- [uuid/extension.kt](../api/src/main/kotlin/com/github/syari/spigot/api/uuid/extension.kt)

## World
World 関連の便利なクラスを追加します。

- [world/Coordinate.kt](../api/src/main/kotlin/com/github/syari/spigot/api/world/Coordinate.kt)
- [world/Region.kt](../api/src/main/kotlin/com/github/syari/spigot/api/world/Region.kt)

## Plugin Using EasySpigotAPI
EasySpigotAPI を使用してプラグインを製作した場合は以下に追加してください。参考にしていただけたらと思います。

<!-- アルファベット順に並べてください -->
- [SecondStoryServer/SS-Plugins](https://github.com/SecondStoryServer/SS-Plugins)
- [sya-ri/CraftKing](https://github.com/sya-ri/CraftKing)
- [sya-ri/imEntity](https://github.com/sya-ri/imEntity)
- [sya-ri/IncreaseOnSee](https://github.com/sya-ri/IncreaseOnSee)
- [sya-ri/LetsGoTogether](https://github.com/sya-ri/LetsGoTogether)
- [sya-ri/RandomCraft](https://github.com/sya-ri/RandomCraft)
- [sya-ri/RandomSpawnMob](https://github.com/sya-ri/RandomSpawnMob)
- [sya-ri/TalkWithNearPlayer](https://github.com/sya-ri/TalkWithNearPlayer)
- [sya-ri/WorldBorderTester](https://github.com/sya-ri/WorldBorderTester)
- [sya-ri/YouAreMyDoll](https://github.com/sya-ri/YouAreMyDoll)

[<< 戻る](../README.md)