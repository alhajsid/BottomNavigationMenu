package com.example.bottomnavigationmenu

import android.content.res.Resources
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView.OnScrollListener.SCROLL_STATE_IDLE
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import kotlin.math.roundToInt


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textView = findViewById<TextView>(R.id.textView)

        val res: Resources = resources

        val contactsDrawable = ResourcesCompat.getDrawable(res, R.drawable.ic_baseline_add_circle_24, null)
        val tabModel1 = TabModal("Icon1 title", contactsDrawable!!)

        val myMessagesDrawable = ResourcesCompat.getDrawable(res, R.drawable.ic_baseline_add_location_24, null)
        val tabModel2 = TabModal("Icon2 title", myMessagesDrawable!!)

        val profileDrawable = ResourcesCompat.getDrawable(res, R.drawable.ic_baseline_self_improvement_24, null)
        val tabModel3 = TabModal("Icon3 title", profileDrawable!!)

        val barcodeDrawable = ResourcesCompat.getDrawable(res, R.drawable.ic_baseline_album_24, null)
        val tabModel4 = TabModal("Icon4 title", barcodeDrawable!!)

        val issueTrackerDrawable = ResourcesCompat.getDrawable(res, R.drawable.ic_baseline_android_24, null)
        val tabModel5 = TabModal("Icon5 title", issueTrackerDrawable!!)

        val tabModals: ArrayList<TabModal> = ArrayList()

        tabModals.add(tabModel1)
        tabModals.add(tabModel2)
        tabModals.add(tabModel3)
        tabModals.add(tabModel4)
        tabModals.add(tabModel5)

        val scrollTabsRecyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        val tabsLayoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.HORIZONTAL, false)

        scrollTabsRecyclerView.layoutManager = tabsLayoutManager

        val scrollTabsAdaptor = ScrollTabsAdaptor(tabModals, object : ScrollTabsAdaptor.OnItemClickListener {

                override fun onViewClick(tabModal: TabModal?) {

                    tabsLayoutManager.smoothScrollToPosition(scrollTabsRecyclerView, null, tabModals.indexOf(tabModal))

                }

            })

        scrollTabsRecyclerView.adapter = scrollTabsAdaptor
        val snapHelper: SnapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(scrollTabsRecyclerView)
        val lastFooterPosition = 2

        scrollTabsRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                val view: View? = snapHelper.findSnapView(tabsLayoutManager)
                val position = tabsLayoutManager.getPosition(view!!)
                if (newState == SCROLL_STATE_IDLE && lastFooterPosition == position) {
                    when (position) {
                        0 -> {
                        }
                        1 -> {
                        }
                        2 -> {
                        }
                        3 -> {
                        }
                        4 -> {
                        }
                    }
                }
            }


            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {

                super.onScrolled(recyclerView, dx, dy)
                val snapView: View? = snapHelper.findSnapView(tabsLayoutManager)
                val position = tabsLayoutManager.getPosition(snapView!!)
                textView.post { textView.text = tabModals[position].title }

                for (tabModel in tabModals) {

                    val tabsIndex = tabModals.indexOf(tabModel)

                    val view: View? = tabsLayoutManager.getChildAt(tabsIndex)

                    if (view != null) {
                        if (snapView === view) {
                            setRecyclerViewItemDecoration(view, R.drawable.greenround, position, 70, 70, 1.0f)
                        } else if (tabsIndex == position - 1 || tabsIndex == position + 1) {
                            setRecyclerViewItemDecoration(view, R.drawable.whiteround, position, 45, 45, 0.8f)
                        } else {
                            setRecyclerViewItemDecoration(view, R.drawable.whiteround, position, 30, 30, 0.6f)
                        }
                    }

                }
            }
        })

        scrollTabsRecyclerView.scrollToPosition(lastFooterPosition)
    }

    private fun setRecyclerViewItemDecoration(itemView: View, drawable: Int, position: Int, width: Int, height: Int, alpha: Float) {
        itemView.post {
            itemView.requestLayout()
            val res: Resources = resources
            val drawable1 = ResourcesCompat.getDrawable(res, drawable, null)
            itemView.background = drawable1
            val layoutParams: ViewGroup.LayoutParams = itemView.layoutParams
            layoutParams.width = dpToPx(width)
            layoutParams.height = dpToPx(height)
            itemView.alpha = alpha
            itemView.layoutParams = layoutParams
        }
    }

    private fun dpToPx(dp: Int): Int {
        val density = resources.displayMetrics.density
        return (dp.toFloat() * density).roundToInt()
    }
}