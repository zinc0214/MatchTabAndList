package zinc0214.matchtabandlist.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.SCROLL_STATE_DRAGGING
import androidx.recyclerview.widget.RecyclerView.SCROLL_STATE_IDLE
import kotlinx.android.synthetic.main.activity_main.*
import zinc0214.matchtabandlist.R
import zinc0214.matchtabandlist.data.ItemData
import zinc0214.matchtabandlist.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var itemDataList: List<ItemData>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = MainViewModel()

        itemDataList = viewModel.getItemData(applicationContext)
        setUpView()
    }

    private fun setUpView() {
        setTabLayout()
        setRecyclerView()
        matchTabAndScrollPosition()
    }

    private fun setTabLayout() {
        itemDataList.forEach {
            binding.apply {
                tabLayout.addTab(tabLayout.newTab().setText(it.title))
            }
        }
    }

    private fun setRecyclerView() {
        binding.recyclerView.adapter = RecyclerViewAdapter(itemDataList)
    }

    private fun matchTabAndScrollPosition() {
        val layoutManager = recyclerView.layoutManager as LinearLayoutManager
        var currentTabPosition = 0
        var changedByScroll = false
        var changedByTab = false
        var isPositionMatched = false
        val itemLastIndex = itemDataList.lastIndex

        binding.apply {

            recyclerView.addOnScrollListener {
                val firstPosition = layoutManager.findFirstVisibleItemPosition()
                val lastPosition = layoutManager.findLastVisibleItemPosition()
                val matchPosition = when {
                    changedByTab -> {
                        currentTabPosition
                    }
                    lastPosition == itemLastIndex -> {
                        lastPosition
                    }
                    else -> firstPosition
                }

                // If selected on the tab, the tab should not move even if it is scrolled.
                if (changedByScroll && !isPositionMatched) {
                    tabLayout.setScrollPosition(matchPosition, 0f, true)
                }
                if (isPositionMatched) {
                    changedByTab = false
                }
                isPositionMatched =
                    matchPosition == currentTabPosition && matchPosition == firstPosition
            }

        }


        recyclerView.addOnScrollStateChanged {
            // Tabs should not be moved in situations where the user automatically scrolls by selecting 'Tabs'
            if (it == SCROLL_STATE_DRAGGING && !changedByTab) {
                changedByScroll = true
            }
            // If the tab and the scroll still do not fit after the scroll is finished,
            // try to fit the scrolled position.
            if (it == SCROLL_STATE_IDLE && !isPositionMatched) {
                changedByScroll = true
                changedByTab = false
            }
        }

        // The recyclerView should move to the position of the selected tab.
        tabLayout.addOnTabSelectedListener {
            changedByScroll = false
            changedByTab = true
            currentTabPosition = tabLayout.selectedTabPosition
            recyclerView.smoothScrollToPosition(currentTabPosition)
        }
    }
}

