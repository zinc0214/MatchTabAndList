package zinc0214.matchtabandlist.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import zinc0214.matchtabandlist.data.ItemData
import zinc0214.matchtabandlist.databinding.ListItemBinding

class RecyclerViewAdapter(private val dataItemList: List<ItemData>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private lateinit var viewHolder: ItemViewHolder

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ListItemBinding.inflate(LayoutInflater.from(parent.context))
        viewHolder = ItemViewHolder(binding)

        return viewHolder
    }

    override fun getItemCount() = dataItemList.size


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        viewHolder.bind(dataItemList[position])
    }

    class ItemViewHolder(val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(itemData: ItemData) {
            binding.data = itemData
        }
    }
}