/*
 * Copyright (c) 2018 alvince
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:

 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.

 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package me.alvince.android.droidktx.core

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.TextView

/**
 * Detach from parent view
 */
fun View.detachParent() = parent?.let { it as? ViewGroup }?.also { it.removeView(this) }

/**
 * Check if current visible to user
 */
fun View.isVisible() = isShown || visibility == View.VISIBLE

/**
 * Toggle [View]'s visibility
 */
fun View.visible(visible: Boolean = true) {
    when (visibility) {
        View.INVISIBLE -> {
            if (visible) visibility = View.VISIBLE
        }
        View.VISIBLE -> {
            if (!visible) visibility = View.INVISIBLE
        }
    }
}

/**
 * Make [View]'s visibility to [View.GONE]
 */
fun View.gone() {
    if (visibility != View.GONE) {
        visibility = View.GONE
    }
}

/**
 * Register click callback via lambda expression
 *
 * @see View.setOnClickListener
 */
fun View.onClick(listener: View.OnClickListener?) = setOnClickListener(listener)

/**
 * Register click callback with delayed consoled
 *
 * @param delayed click delayed in millis seconds
 * @param block click handler expression
 */
fun View.onClick(delayed: Long = 0, block: (View) -> Unit) {
    if (delayed > 0) {
        setOnClickListener(ClickDelayed(delayed, block))
    } else {
        setOnClickListener { block(it) }
    }
}

/**
 * Toggle soft input display
 *
 * @see InputMethodManager.toggleSoftInput
 */
fun View.toggleSoftInput(show: Boolean) {
    (context?.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager)?.also {
        if (show) {
            requestFocus()
            it.showSoftInput(this, InputMethodManager.RESULT_SHOWN)
            it.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, InputMethodManager.HIDE_NOT_ALWAYS)
        } else {
            it.hideSoftInputFromWindow(windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
            clearFocus()
        }
    }
}

/**
 * Get [TextView] text as [String]
 */
fun TextView.text(trim: Boolean = true) = this.text.toString().let { if (trim) it.trim() else it }

/**
 * [View.OnClickListener] wrapper with delayed
 */
internal class ClickDelayed(private val delayed: Long, private val callback: (View) -> Unit) : View.OnClickListener {

    private var lastHandleClicked: Long = 0

    override fun onClick(v: View?) {
        v ?: return

        val curTime = System.currentTimeMillis()
        if (curTime.minus(lastHandleClicked) >= delayed) {
            lastHandleClicked = curTime
            callback.invoke(v)
        }
    }
}
