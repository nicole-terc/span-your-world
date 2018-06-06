package nstv.spanyourworld

import android.graphics.Typeface
import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.method.LinkMovementMethod
import android.text.style.*
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_span.*
import nstv.spanyourworld.span.SuperBulletSpan
import nstv.spanyourworld.span.SuperSpan

class SpanFragment : Fragment() {

    companion object {
        val BULLET_SPAN = 1
        val FOREGROUND_SPAN = 2
        val FOREGROUND_SPAN_EXTENDED = 3
        val FOREGROUND_SPAN_NOT_EXTENDED = 4
        val MULTIPLE_SPANS = 5
        val LEADING_SPAN = 6
        val SUPER_SPAN = 7

        fun newInstance(type: Int): SpanFragment {
            val fragment = SpanFragment()
            fragment.type = type
            return fragment
        }
    }

    var type: Int = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_span, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setSpan(type)
    }

    fun setSpan(type: Int) {
        var text: SpannableStringBuilder? = null

        when (type) {
            BULLET_SPAN -> {
                text = SpannableStringBuilder("Check out those BULLETS!")
                text.setSpan(SuperBulletSpan(40, R.color.colorGray),
                        0, text.length, Spannable.SPAN_POINT_MARK)
            }
            FOREGROUND_SPAN -> {
                text = SpannableStringBuilder("Check out these COLORS! ")
                text.setSpan(ForegroundColorSpan(resources.getColor(R.color.colorAccent)),
                        16, 23, Spannable.SPAN_INCLUSIVE_EXCLUSIVE)
            }
            FOREGROUND_SPAN_EXTENDED -> {
                text = SpannableStringBuilder("Check out these COLORS! ")
                text.setSpan(ForegroundColorSpan(resources.getColor(R.color.colorAccent)),
                        16, 23, Spannable.SPAN_INCLUSIVE_EXCLUSIVE)

                text.insert(23, " [not here]")
                text.insert(16, "[here 2] ")

            }
            FOREGROUND_SPAN_NOT_EXTENDED -> {
                text = SpannableStringBuilder("Check out these COLORS! ")
                text.setSpan(ForegroundColorSpan(resources.getColor(R.color.colorAccent)),
                        16, 23, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

                text.insert(23, " [not here]")
                text.insert(16, "[here 2] ")

            }
            MULTIPLE_SPANS -> {
                text = SpannableStringBuilder("MULTIPLE SPANS from the world!")

                //bold
                text.setSpan(StyleSpan(Typeface.BOLD),
                        0, 14, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

                //strikethrough
                text.setSpan(StrikethroughSpan(),
                        0, 8, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

                //italic
                text.setSpan(StyleSpan(Typeface.ITALIC),
                        15, 19, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

                //bigger
                text.setSpan(RelativeSizeSpan(2.0f),
                        20, 23, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

                //color
                text.setSpan(ForegroundColorSpan(resources.getColor(R.color.colorAccent)),
                        24, 29, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

                //underline
                text.setSpan(UnderlineSpan(),
                        24, 29, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

                //click
                text.setSpan(object : ClickableSpan() {
                    override fun onClick(widget: View) = activity.toast("Text clicked!")
                }, 24, 29, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
                span_text.movementMethod = LinkMovementMethod.getInstance()
            }
            LEADING_SPAN -> {
                text = SpannableStringBuilder("In a land far far away, where no androids wanted to go, bla bla bla leading span appeared!")
                text.setSpan(LeadingMarginSpan.Standard(200, 100),
                        0, text.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

            }
            SUPER_SPAN -> {
                text = SpannableStringBuilder("Get out of the box!")
                text.setSpan(SuperSpan(resources.getColor(R.color.colorAccent), 20), 15, 18, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            }
            else -> {
                text = SpannableStringBuilder("No span here!")
            }
        }


        span_text.text = text
    }
}
