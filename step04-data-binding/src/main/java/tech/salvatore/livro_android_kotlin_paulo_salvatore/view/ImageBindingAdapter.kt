@file:Suppress("unused")

package tech.salvatore.livro_android_kotlin_paulo_salvatore.view

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

object ImageBindingAdapter {
    @JvmStatic
    @BindingAdapter("imageUrl")
    fun loadImage(view: ImageView, url: String?) {
        if (!url.isNullOrEmpty()) {
            Glide.with(view).load(url).into(view)
        }
    }
}
