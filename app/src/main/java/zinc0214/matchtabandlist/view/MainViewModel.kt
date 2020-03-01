package zinc0214.matchtabandlist.view

import android.content.Context
import androidx.lifecycle.ViewModel
import zinc0214.matchtabandlist.R
import zinc0214.matchtabandlist.data.ItemData

class MainViewModel() : ViewModel() {

    private lateinit var context: Context
    fun getItemData(context: Context): List<ItemData> {
        this.context = context
        return dummyData()
    }

    private fun dummyData(): List<ItemData> {
        val data1 = ItemData(R.string.name1.resToString(), R.string.text1.resToString())
        val data2 = ItemData(R.string.name2.resToString(), R.string.text2.resToString())
        val data3 = ItemData(R.string.name3.resToString(), R.string.text3.resToString())

        return listOf(data1, data2, data3)
    }

    private fun Int.resToString() = context.resources.getString(this)
}