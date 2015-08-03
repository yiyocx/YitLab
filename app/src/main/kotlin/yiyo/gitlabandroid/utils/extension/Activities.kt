package yiyo.gitlabandroid.utils.extension

import android.app.Activity
import android.widget.Toast

/**
 * Created by yiyo on 3/08/15.
 */
fun Activity.toast(message: CharSequence, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, duration).show()
}