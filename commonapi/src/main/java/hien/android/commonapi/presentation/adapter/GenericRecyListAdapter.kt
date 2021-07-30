package hien.android.commonapi.presentation.adapter

import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import java.util.*

open class GenericRecyListAdapter<BaseModel>(
    private var originalList: MutableList<BaseModel> = mutableListOf(),
    private var searchFilter: ITextSearchFilter<BaseModel>? = null, diffCallback: IDiffItemCallback<BaseModel>
) : ListAdapter<BaseModel, RecyclerView.ViewHolder>(MyDiffCallBack(diffCallback)), Filterable {

    private var adapterFilter: AdapterFilter? = null

    fun clear() {
        originalList.clear()
        // Call method submitList to set of update display on recycler view
        submitList(originalList)
    }

    override fun getFilter(): Filter {
        if (adapterFilter == null) {
            adapterFilter = AdapterFilter()
        }
        return adapterFilter!!
    }

    fun updateOriginalList(newList: List<BaseModel>?) {
        if (newList != null) {
            originalList = ArrayList(newList)
        }
    }

    private inner class AdapterFilter : Filter() {
        override fun performFiltering(constraint: CharSequence): FilterResults {
            val filterResults = FilterResults()
            if (constraint.isNotEmpty()) {
                val resultList = ArrayList<BaseModel>()
                for (obj in originalList) {
                    if (shouldBeDisplayed(constraint, obj)) {
                        resultList.add(obj)
                    }
                }
                filterResults.count = resultList.size
                filterResults.values = resultList
            } else {
                filterResults.count = originalList.size
                filterResults.values = originalList
            }
            return filterResults
        }

        /**
         * Notify about filtered list to ui
         *
         * @param constraint text
         * @param results    filtered result
         */
        override fun publishResults(constraint: CharSequence, results: FilterResults) {
            // Call method submitList to set of update display on recycler view
            submitList(results.values as List<BaseModel>)
        }

        /**
         * @param constraint the text used to filter the K objects list
         * @param obj        the K object eligibile for filtering
         * @return true if the object should be filtered, false otherwise
         */
        private fun shouldBeDisplayed(constraint: CharSequence, obj: BaseModel): Boolean {
            return searchFilter == null || searchFilter!!.shouldBeDisplayed(constraint, obj)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    class MyDiffCallBack<BaseModel>(private val diffCallback: IDiffItemCallback<BaseModel>) : DiffUtil.ItemCallback<BaseModel>() {
        override fun areItemsTheSame(oldItem: BaseModel, newItem: BaseModel): Boolean {
            return diffCallback.areItemsTheSame(oldItem, newItem)
        }

        override fun areContentsTheSame(oldItem: BaseModel, newItem: BaseModel): Boolean {
            return diffCallback.areContentsTheSame(oldItem, newItem)
        }
    }

}