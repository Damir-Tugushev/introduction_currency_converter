package io.github.damirtugushev.introduction.view

import android.view.View
import androidx.annotation.CheckResult
import com.google.android.material.snackbar.Snackbar

@CheckResult
inline fun snackbarShort(view: View, text: () -> CharSequence): Snackbar =
    Snackbar.make(view, text(), Snackbar.LENGTH_SHORT)
