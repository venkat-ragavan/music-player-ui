package com.vrrv.musicplayer.ui.utillity.extensions

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.google.android.material.internal.ViewUtils.dpToPx

@BindingAdapter("layoutMarginTop")
fun setLayoutMarginTop(view: View, dimen: Float) {
    val layoutParams = view.layoutParams as ViewGroup.MarginLayoutParams
    layoutParams.topMargin = dimen.toInt()
    view.layoutParams = layoutParams
}

@BindingAdapter("layoutMarginBottom")
fun setLayoutMarginBottom(view: View, dimen: Float) {
    val layoutParams = view.layoutParams as ViewGroup.MarginLayoutParams
    layoutParams.bottomMargin = dimen.toInt()
    view.layoutParams = layoutParams
}

@BindingAdapter("image")
fun ImageView.setImageToImageView(@DrawableRes image: Int) {
    Glide.with(this).load(image).into(this)
}

@SuppressLint("RestrictedApi")
fun setPaddingBottom(context: Context, v: View, bottomValue: Int) {
    val bottomPadding = context.let { dpToPx(it, bottomValue).toInt() }
    v.setPadding(v.paddingLeft, v.paddingTop, v.paddingRight, bottomPadding)
}