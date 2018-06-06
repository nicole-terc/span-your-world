package nstv.spanyourworld

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_span.*
import nstv.spanyourworld.SpanFragment.Companion.BULLET_SPAN
import nstv.spanyourworld.SpanFragment.Companion.FOREGROUND_SPAN
import nstv.spanyourworld.SpanFragment.Companion.FOREGROUND_SPAN_EXTENDED
import nstv.spanyourworld.SpanFragment.Companion.FOREGROUND_SPAN_NOT_EXTENDED
import nstv.spanyourworld.SpanFragment.Companion.LEADING_SPAN
import nstv.spanyourworld.SpanFragment.Companion.MULTIPLE_SPANS
import nstv.spanyourworld.SpanFragment.Companion.SUPER_SPAN

class SpanActivity : AppCompatActivity() {

    private var mSectionsPagerAdapter: SectionsPagerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_span)

        setSupportActionBar(toolbar)
        mSectionsPagerAdapter = SectionsPagerAdapter(supportFragmentManager)
        container.adapter = mSectionsPagerAdapter

    }

    /**
     * A [FragmentPagerAdapter] that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    inner class SectionsPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

        val spanList = listOf(
                BULLET_SPAN,
                FOREGROUND_SPAN,
                FOREGROUND_SPAN_EXTENDED,
                FOREGROUND_SPAN_NOT_EXTENDED,
                MULTIPLE_SPANS,
                LEADING_SPAN,
                SUPER_SPAN
        )

        override fun getItem(position: Int): Fragment {
            return SpanFragment.newInstance(spanList[position])
        }

        override fun getCount(): Int {
            // Show 3 total pages.
            return spanList.size
        }
    }

}
