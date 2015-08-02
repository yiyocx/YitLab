package yiyo.gitlabandroid.utils.extension

import android.app.Fragment

/**
 * Created by yiyo on 1/08/15.
 */
fun <T> T.tag(): String = javaClass.getSimpleName()