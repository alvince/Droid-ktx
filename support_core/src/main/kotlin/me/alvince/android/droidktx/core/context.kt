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
import android.content.res.Resources
import android.graphics.Point
import android.graphics.drawable.Drawable
import android.os.Build
import android.util.TypedValue
import android.view.WindowManager

fun Context.string(res: Int, vararg formatArgs: Any): String =
    if (formatArgs.isNotEmpty()) {
        getString(res, formatArgs)
    } else {
        getString(res)
    } ?: ""


fun Context.color(res: Int) =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        getColor(res)
    } else {
        @Suppress("DEPRECATION")
        resources.getColor(res)
    }

fun Context.drawable(res: Int): Drawable? =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        getDrawable(res)
    } else {
        @Suppress("DEPRECATION")
        resources.getDrawable(res)
    }

fun Context.drawableViaAttrs(attrRes: Int): Drawable? {
    val attrsArray = intArrayOf(attrRes)
    val typedArray = obtainStyledAttributes(attrsArray)
    val drawable = typedArray.getDrawable(0)
    typedArray.recycle()
    return drawable
}

/**
 * Convert dimensions in pixel from dip
 */
fun Context?.fromDp(dip: Float): Int =
    (this?.resources ?: Resources.getSystem()).displayMetrics
        .let {
            TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dip, it).toInt()
        }

/**
 * Convert dimensions in pixel from sp
 */
fun Context?.fromSp(sp: Float): Int =
    (this?.resources ?: Resources.getSystem()).displayMetrics
        .let {
            TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, it).toInt()
        }

/**
 * Obtain system-ui action bar height
 */
fun Context.actionBarSize() = let {
    val tv = TypedValue()
    theme.resolveAttribute(android.R.attr.actionBarSize, tv, true)
    TypedValue.complexToDimensionPixelSize(tv.data, resources.displayMetrics)
}

/**
 * Obtain window size
 */
fun Context.getWindowSize(): Point = let {
    val out = Point()
    (getSystemService(Context.WINDOW_SERVICE) as? WindowManager)
        ?.apply {
            defaultDisplay.getSize(out)
        }
    out
}