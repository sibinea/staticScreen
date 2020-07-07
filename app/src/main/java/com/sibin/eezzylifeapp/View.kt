package com.sibin.eezzylifeapp

import android.content.Context
import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import androidx.annotation.LayoutRes
import com.bumptech.glide.Glide
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.load.Transformation
import com.bumptech.glide.load.resource.bitmap.FitCenter
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions

val DEFAULT_TRANSITION = DrawableTransitionOptions.withCrossFade()
const val DEFAULT_PLACEHOLDER = R.mipmap.ic_launcher

val DEFAULT_TRANSFORMATION = listOf(
    FitCenter()
)

fun View.isVisible() = this.visibility == View.VISIBLE

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.invisible() {
    this.visibility = View.GONE
}

fun ViewGroup.inflate(@LayoutRes layoutRes: Int): View =
    LayoutInflater.from(context).inflate(layoutRes, this, false)

fun ImageView.loadFromUrl(
    url: String,
    transition: DrawableTransitionOptions = DEFAULT_TRANSITION,
    transformation: List<Transformation<Bitmap>> = DEFAULT_TRANSFORMATION,
    error: Int = DEFAULT_PLACEHOLDER
) {
    val multi = MultiTransformation(
        transformation
    )
    Glide.with(this.context.applicationContext)
        .load(url)
        .apply(RequestOptions.bitmapTransform(multi))
        .error(error)
        .transition(transition)
        .into(this)
}
