package com.example.myhotels.ui.hotelDetailScreen.utils

import android.graphics.Bitmap
import com.squareup.picasso.Transformation

private const val X_SIZE = 4
private const val Y_SIZE = 4
private const val CROP_BITMAP = "crop_bitmap"

class CropBitmapTransformation: Transformation {
    override fun transform(source: Bitmap?): Bitmap? {
        val result = source?.let { Bitmap.createBitmap(it, X_SIZE, Y_SIZE, source.width - 8, source.height - 8) }
        if (result != source) {
            source?.recycle()
        }
        return result
    }

    override fun key(): String {
   return CROP_BITMAP
    }


}