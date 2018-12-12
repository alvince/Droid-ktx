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

import android.content.Context
import android.widget.Toast
import me.alvince.android.droidktx.core.string

/**
 * Show toast-normal with string res
 */
fun Context.toast(textRes: Int, duration: Int = Toast.LENGTH_SHORT) {
    string(textRes).also { toast(it, duration) }
}

/**
 * Show toast-normal with text specified
 */
fun Context.toast(text: String, duration: Int = Toast.LENGTH_SHORT) {
    if (text.isEmpty()) {
        return
    }
    Toast.makeText(this, text, duration).show()
}
