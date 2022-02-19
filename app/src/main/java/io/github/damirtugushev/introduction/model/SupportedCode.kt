package io.github.damirtugushev.introduction.model

data class SupportedCode(val code: String, val name: String) {
    enum class State {
        Saved,
        Deleted,
    }
}
