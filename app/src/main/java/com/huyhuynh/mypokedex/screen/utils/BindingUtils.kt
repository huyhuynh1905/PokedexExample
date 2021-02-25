package demo.com.weatherapp.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.huyhuynh.mypokedex.data.adapter.PokemonAdapter
import com.huyhuynh.mypokedex.data.model.Pokemon

object BindingUtil {
    @BindingAdapter("pokemonItem")
    @JvmStatic
    fun setItemProduct(recyclerView: RecyclerView, items: List<Pokemon>?) {
        val adapter: RecyclerView.Adapter<*>? = recyclerView.adapter
        if (adapter != null && adapter is PokemonAdapter) {
            if (items != null) {
                adapter.setNewData(items)
            }
        }
    }

    @BindingAdapter("imageLoading")
    @JvmStatic
    fun loadImage(imageView: ImageView, url: String) {
        Glide.with(imageView.context).load(url).into(imageView)
    }
}