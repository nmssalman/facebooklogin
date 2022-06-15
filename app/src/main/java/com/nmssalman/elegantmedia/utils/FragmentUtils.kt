package com.nmssalman.elegantmedia.utils

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.icu.text.CaseMap
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.bumptech.glide.Glide
import com.nmssalman.elegantmedia.MainActivity
import com.nmssalman.elegantmedia.R
import de.hdodenhof.circleimageview.CircleImageView

class FragmentUtils {


    companion object {
        private lateinit var supportFragment: FragmentManager
        fun Initialize(support: FragmentManager) {
            this.supportFragment = support
        }

        fun navigateToFragment(newfragment: Fragment) {
            val fragmentTransaction = supportFragment.beginTransaction()
            fragmentTransaction.replace(R.id.fragment_container, newfragment)
            fragmentTransaction.commit()
        }

        fun DisplayCustomDialog(context: Context, title: String, message: String) {
            AlertDialog.Builder(context)
                .setTitle(R.string.app_name)
                .setMessage(message)
                .setPositiveButton(R.string.dialog_ok) { dialog, _ ->
                    dialog.dismiss()
                }
                .setIcon(R.drawable.com_facebook_button_icon)
                .setCancelable(false)
                .show()
        }

        fun GlideToImageView(context: Context, path: String, imageView: ImageView) {
            Glide.with(context).load(path)
                .error(context.getDrawable(R.drawable.ic_baseline_image_not_supported_24))
                .into(imageView)
        }

        fun setOptionMenu(activity: Activity) {
            if ((activity as MainActivity).supportActionBar != null) {
                val actionBar = (activity).supportActionBar
                actionBar!!.setDisplayHomeAsUpEnabled(true)
                actionBar.setHomeButtonEnabled(true)
            }
        }

        fun setActionBarTitle(title: String, activity: Activity) {
            (activity as AppCompatActivity).supportActionBar?.title = title
        }

        fun visibleActionBar(activity: Activity) {
            (activity as AppCompatActivity?)!!.supportActionBar!!.show()
        }

        fun hideActionBar(activity: Activity)
        {
            (activity as AppCompatActivity?)!!.supportActionBar!!.hide()
        }

    }
}