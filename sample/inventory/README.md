# Inventory

## [Main.kt](src/main/kotlin/com/github/syari/spigot/api/sample/inventory/Main.kt)
プラグインが起動した時に `CustomInventory::onEnable` を実行しておかなければ `inventory` を使用することができません。他のプラグインで実行している可能性があっても実行しておくようにしてください。

```kotlin
override fun onEnable() {
    CustomInventory.onEnable(this)
    OpenInventoryCommand.register()
}
```

## [OpenInventoryCommand.kt](src/main/kotlin/com/github/syari/spigot/api/sample/inventory/OpenInventoryCommand.kt)

```kotlin
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
```
