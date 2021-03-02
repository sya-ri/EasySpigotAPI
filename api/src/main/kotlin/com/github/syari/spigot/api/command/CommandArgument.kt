package com.github.syari.spigot.api.command

/**
 * コマンドの引数。
 * @param array 実際の引数
 * @since 1.2.0
 */
class CommandArgument(
    private val array: List<String>
) : List<String> by array {
    /**
     * 小文字・大文字を区別せずに引数の判定を行える。以下の処理を実行しているだけだが、使うことが多いので定義されている。
     * ```
     * getOrNull(index)?.toLowerCase()
     * ```
     * @param index 取得する位置
     * @since 1.2.0
     */
    fun lowerOrNull(index: Int) = getOrNull(index)?.toLowerCase()
}
