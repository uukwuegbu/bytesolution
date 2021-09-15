package com.ugogineering.android.bytesolution

import android.content.res.Resources
import android.os.Build
import android.text.Html
import android.text.Spanned
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ugogineering.android.bytesolution.model.EmployeeList
import com.ugogineering.android.bytesolution.model.EmployeeResultList

/**
 * Takes a list of Employees and converts and formats it into one string for display.
 *
 * For display in a TextView, we have to supply one string, and styles are per TextView, not
 * applicable per word. So, we build a formatted string using HTML. This is handy, but we will
 * learn a better way of displaying this data in a future lesson.
 *
 * @param   nights - List of all SleepNights in the database.
 * @param   resources - Resources object for all the resources defined for our app.
 *
 * @return  Spanned - An interface for text that has formatting attached to it.
 *           See: https://developer.android.com/reference/android/text/Spanned
 */
fun formatEmployeeResultList(employees: List<EmployeeList.EmployeeResult?>?): Spanned {
    val sb = StringBuilder()
    sb.apply {
        append("<h3>Employee List</h3>")
        employees?.forEach {
            append("<br>")
            append("<b>ID: </b>")
            append("\t${it?.id}<br>")
            append("<b>First Name: </b>")
            append("\t${it?.firstname}<br>")
            append("<b>Last Name: </b>")
            append("\t${it?.lastname}<br>")
            append("<b>Gender: </b>")
            append("\t${it?.gender}<br>")
            append("<b>DOB: </b>")
            append("\t${it?.dob}<br>")
            append("<b>Address: </b>")
            append("\t${it?.address}<br>")
            append("<b>Country: </b>")
            append("\t${it?.country}<br>")
            append("<b>Designation: </b>")
            append("\t${it?.designation}<br><br>")
        }
    }
    // fromHtml is deprecated for target API without a flag, but since our minSDK is 19, we
    // can't use the newer version, which requires minSDK of 24
    //https://developer.android.com/reference/android/text/Html#fromHtml(java.lang.String,%20int)
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        return Html.fromHtml(sb.toString(), Html.FROM_HTML_MODE_LEGACY)
    } else {
        @Suppress("DEPRECATION")
        return Html.fromHtml(sb.toString())
    }
}
class TextItemViewHolder(val textView: TextView): RecyclerView.ViewHolder(textView)