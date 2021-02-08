# EasySpigotAPI
A library for easy use of the Spigot API.

## サンプルコード

### [Event / Register](sample/README.md#event--register)
イベント定義をラムダ式で書くことができます。

#### Without EasySpigotAPI
```kotlin
@EventHandler
fun on(e: PlayerJoinEvent) {
    e.joinMessage = ">> Join ${e.player.displayName}"
}
```

#### With EasySpigotAPI
```kotlin
event<PlayerJoinEvent> {
    it.joinMessage = ">> Join ${it.player.displayName}"
}
```
