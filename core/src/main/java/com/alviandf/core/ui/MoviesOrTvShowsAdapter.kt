package com.alviandf.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alviandf.core.R
import com.alviandf.core.domain.model.MovieOrTvShow
import com.alviandf.core.utils.Const
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_row_data.view.imgPoster
import kotlinx.android.synthetic.main.item_row_data.view.tvName
import java.util.ArrayList

class MoviesOrTvShowsAdapter : RecyclerView.Adapter<MoviesOrTvShowsAdapter.ListViewHolder>() {

    private var listData = ArrayList<MovieOrTvShow>()
    var onItemClick: ((MovieOrTvShow) -> Unit)? = null

    fun setData(newListData: List<MovieOrTvShow>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_row_data, parent, false))

    override fun getItemCount() = listData.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(data: MovieOrTvShow) {
            with(itemView) {
                Glide.with(context)
                    .load(Const.URL_POSTER + data.poster_path)
                    .into(imgPoster)
                tvName.text = if (data.title.isNotEmpty()) data.title else data.name
                setOnClickListener {
                    onItemClick?.invoke(data)
                }
            }
        }
    }
}