# UUID

## [PlayerJoinChecker.kt](src/main/kotlin/com/github/syari/spigot/api/sample/uuid/PlayerJoinChecker.kt)

`UUID`, `UUIDPlayer` を使わないと同じプレイヤーがログインしても、別のプレイヤーとして扱われてしまう。
そのため、変数として格納する時は必ずそれらを使用する。
以下の例では、`Player` を用いた時に正常に動作しないことを確認している。

```kotlin
object PlayerJoinChecker {
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

    fun register() {
        events {
            event<PlayerJoinEvent> {
                val player = it.player
                val uuidPlayer = UUIDPlayer.from(player)
                player.sendMessage(
                    """
                UUIDPlayer を使用: ${useUuidPlayer.contains(uuidPlayer)} (${useUuidPlayer.size})
                Player を使用: ${usePlayer.contains(player)} (${usePlayer.size})
                """.trimIndent()
                )
                useUuidPlayer.add(uuidPlayer)
                usePlayer.add(player)
            }
        }
    }
}
```

[<< 戻る](../README.md)