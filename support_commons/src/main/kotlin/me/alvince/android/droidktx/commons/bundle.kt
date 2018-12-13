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

package me.alvince.android.droidktx.commons

import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import java.io.Serializable

fun ofBundle(map: Map<String, *>): Bundle {
    val bundle = Bundle()
    map.asIterable().forEach {
        val v = it.value
        when (v) {
            is Boolean -> bundle.putBoolean(it.key, v)
            is String -> bundle.putString(it.key, v)
            is CharSequence -> bundle.putCharSequence(it.key, v)
            is Char -> bundle.putChar(it.key, v)
            is Byte -> bundle.putByte(it.key, v)
            is Short -> bundle.putShort(it.key, v)
            is Int -> bundle.putInt(it.key, v)
            is Long -> bundle.putLong(it.key, v)
            is Float -> bundle.putFloat(it.key, v)
            is Double -> bundle.putDouble(it.key, v)
            is Serializable -> bundle.putSerializable(it.key, v)
            is Parcelable -> bundle.putParcelable(it.key, v)
            else -> Log.e("Droid-ktx", "No support this value's type of key: ${it.key}")
        }
    }
    return bundle
}

fun Bundle?.printPretty(): String {
    this ?: return ""

    if (isEmpty) return "Empty Bundle @$this"

    val temp = StringBuilder("Bundle@$this {")
    keySet().forEach {
        temp.append("\n\t[ $it ] : ${get(it)}")
    }
    return temp.append("\n}").toString()
}
