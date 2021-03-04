package demo.com.weatherapp.util

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.huyhuynh.mypokedex.R
import com.huyhuynh.mypokedex.data.adapter.PokemonAdapter
import com.huyhuynh.mypokedex.data.model.Pokemon
import com.huyhuynh.mypokedex.screen.utils.InternetUtils
import com.huyhuynh.mypokedex.screen.utils.Utils
import demo.com.weatherapp.MainApplication
import java.util.*

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

    @BindingAdapter("loadImagePokedex")
    @JvmStatic
    fun loadImagePokedex(imageView: ImageView, url: String) {
        if (url == null){
            Glide.with(imageView.context).load("https://assets.pokemon.com/assets/cms2/img/pokedex/full/001.png").into(imageView)
        } else {
            Glide.with(imageView.context).load(url).into(imageView)
        }
    }

    @BindingAdapter("loadImagePokedemon")
    @JvmStatic
    fun loadImagePokedemon(imageView: ImageView, url: String?) {
        if (InternetUtils.isNetworkAvailable(MainApplication.getContextInstance())){
            if (url == null){
                Glide.with(imageView.context).load("https://assets.pokemon.com/assets/cms2/img/pokedex/full/001.png").into(imageView)
            } else {
                Glide.with(imageView.context).load(url).into(imageView)
            }
        } else {
            Glide.with(imageView.context).load(R.drawable.ex001).into(imageView)
        }
    }

}