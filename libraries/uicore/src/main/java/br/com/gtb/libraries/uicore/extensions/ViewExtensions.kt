package br.com.gtb.libraries.uicore.extensions

import android.transition.TransitionManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun View.invisible(isTransition: Boolean = false) {
    if (isTransition) {
        (parent as? ViewGroup)?.let { TransitionManager.beginDelayedTransition(it) }
    }
    visibility = View.INVISIBLE
}

fun View.show() {
    (parent as? ViewGroup)?.let { TransitionManager.beginDelayedTransition(it) }
    visibility = View.VISIBLE
}

fun View.hide() {
    (parent as? ViewGroup)?.let { TransitionManager.beginDelayedTransition(it) }
    visibility = View.GONE
}

fun View.getInflater(): LayoutInflater {
    return LayoutInflater.from(context)
}
