package com.huyhuynh.mypokedex.data.paging

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

abstract class PagingScrollListener : RecyclerView.OnScrollListener {
    var gridLayoutManager : GridLayoutManager?=null

    constructor( gridLayoutManager: GridLayoutManager){
        this.gridLayoutManager = gridLayoutManager
    }

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        var visiableItemColumn = gridLayoutManager!!.childCount
        var totalItemCount = gridLayoutManager!!.itemCount
        var firtVisiableItemPosition = gridLayoutManager!!.findFirstVisibleItemPosition()

        if (isLoading()||isLastPage()){
            return
        }
        if (firtVisiableItemPosition>=0 && (visiableItemColumn + firtVisiableItemPosition) >= totalItemCount){
            loadMoreItem()
        }
    }

    abstract fun loadMoreItem()
    abstract fun isLoading():Boolean
    abstract fun isLastPage():Boolean

}