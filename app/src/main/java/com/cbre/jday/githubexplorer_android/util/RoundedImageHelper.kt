package com.cbre.jday.githubexplorer_android.util.auth

import android.graphics.*


class RoundedImageHelper {

    companion object {
        fun roundedCornerBitmap(bitmap: Bitmap, pixels: Int) : Bitmap {
            var output = Bitmap.createBitmap(bitmap.width, bitmap.height, Bitmap.Config.ARGB_8888)
            var canvas = Canvas(output)

            val color = Color.parseColor("#424242")
            val paint = Paint()
            val rect = Rect(0, 0, bitmap.width, bitmap.height)
            val rectF = RectF(rect)
            val roundPx = pixels as Float

            paint.isAntiAlias = true
            canvas.drawARGB(0, 0, 0, 0)
            paint.color = color
            canvas.drawRoundRect(rectF, roundPx, roundPx, paint)

            paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
            canvas.drawBitmap(bitmap, rect, rect, paint)

            return output
        }
    }
}