package zinc0214.matchtabandlist.view

import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayout


fun TabLayout.addOnTabSelectedListener(block: () -> Unit) =
    this.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
        override fun onTabReselected(p0: TabLayout.Tab?) {

        }

        override fun onTabUnselected(p0: TabLayout.Tab?) {

        }

        override fun onTabSelected(p0: TabLayout.Tab?) {
            block()
        }

    })

fun RecyclerView.addOnScrollListener(block: () -> Unit) =
    this.addOnScrollListener(object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            block()
        }
    })

fun RecyclerView.addOnScrollStateChanged(block: (Int) -> Unit) =
    this.addOnScrollListener(object : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            block(newState)
        }
    })
