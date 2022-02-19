package io.github.damirtugushev.introduction.model

data class SupportedCodeWithState(
    val code: SupportedCode,
    val state: SupportedCode.State,
)
