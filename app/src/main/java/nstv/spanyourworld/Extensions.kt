package nstv.spanyourworld

import android.content.Context
import android.widget.Toast

/**
 * Created by Nicole Terc on 6/6/18.
 */

fun Context?.toast(text: String) {
    Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
}