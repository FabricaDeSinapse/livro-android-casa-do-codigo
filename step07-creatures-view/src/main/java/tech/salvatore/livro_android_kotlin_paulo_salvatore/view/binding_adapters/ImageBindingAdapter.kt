@file:Suppress("unused")

package tech.salvatore.livro_android_kotlin_paulo_salvatore.view.binding_adapters

import android.content.res.ColorStateList
import android.graphics.PorterDuff
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.core.widget.ImageViewCompat
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import tech.salvatore.livro_android_kotlin_paulo_salvatore.R

object ImageBindingAdapter {
    @JvmStatic
    @BindingAdapter("imageUrl")
    fun loadImage(view: ImageView, url: String?) {
        if (!url.isNullOrEmpty()) {
            Glide.with(view).load(url).into(view)
        }
    }

    @JvmStatic
    @BindingAdapter("enableSilhouette")
    fun enableSilhouette(view: ImageView, value: Boolean) = with(view) {
        ImageViewCompat.setImageTintMode(
            this,
            if (value) PorterDuff.Mode.MULTIPLY else PorterDuff.Mode.DST
        )

        if (value) {
            val black = ContextCompat.getColor(context, R.color.black)

            ImageViewCompat.setImageTintList(this, ColorStateList.valueOf(black))
        }
    }
}
