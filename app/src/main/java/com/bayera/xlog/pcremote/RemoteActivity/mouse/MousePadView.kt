package com.bayera.xlog.pcremote.RemoteActivity.mouse

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class MousePadView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    lateinit var fragment: MouseFragment // this will be set outside of this class by the parent fragment
    lateinit var canvas: Canvas
    lateinit var bitmap: Bitmap
    var bitmapPaint = Paint(Paint.DITHER_FLAG)
    var lastX = 0f; var lastY = 0f

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888)
        canvas = Canvas(bitmap)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawBitmap(bitmap, 0f, 0f, bitmapPaint)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        parent.requestDisallowInterceptTouchEvent(true) // parent - can't touch this!
        var eventX = event.x; var eventY = event.y
        when (event.action) {
            MotionEvent.ACTION_DOWN -> touchStart(eventX, eventY)
            MotionEvent.ACTION_MOVE -> touchMove(eventX, eventY)
            MotionEvent.ACTION_UP -> touchUp()
        }
        invalidate()
        return true
    }

    fun touchStart(x: Float, y: Float) {
        lastX = x; lastY = y
    }

    fun touchMove(x: Float, y: Float) {
        fragment.mousePadMovement(x - lastX, y - lastY)
        lastX = x; lastY = y
    }

    fun touchUp() {
        parent.requestDisallowInterceptTouchEvent(false) // allow parent to get touch events again
    }

}