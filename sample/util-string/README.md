# Util / String

## [ToColorCommand.kt](src/main/kotlin/com/github/syari/spigot/api/sample/util/string/ToColorCommand.kt)
`String::toColor`, `String::toUncolor` の処理を確認している。

```kotlin
plugin.command("to-color") {
    execute {
        val text = args.joinToString(" ")
        sender.sendMessage("toColor: " + text.toColor())
        sender.sendMessage("toUncolor: " + text.toUncolor())
        sender.sendMessage("toColor -> toUncolor: " + text.toColor().toUncolor())
    }
}
```

```
> to-color &atest &6message
[12:11:46 INFO]: toColor: test message
[12:11:46 INFO]: toUncolor: &atest &6message
[12:11:46 INFO]: toColor -> toUncolor: test message
```

`&` をカラーコードとして認識するので、１行目は文字に色がつき、それ以外に色はついていない。
また、`String::toUncolor` は色付き文字から色を無くすだけなので、
`String::toColor` で一度変換しなければ、カラーコードは消えない。

[<< 戻る](../README.md)