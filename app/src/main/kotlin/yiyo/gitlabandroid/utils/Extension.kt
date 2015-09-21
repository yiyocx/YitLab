package yiyo.gitlabandroid.utils

import android.app.Activity
import android.support.v4.app.Fragment
import android.widget.Toast

/**
 * Created by yiyo on 3/08/15.
 */
fun <T : Any> T.tag(): String = javaClass.simpleName

fun Activity.toast(message: CharSequence, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, duration).show()
}

fun Fragment.toast(message: CharSequence, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(getActivity(), message, duration).show()
}