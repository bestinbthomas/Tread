package com.treadhill.app.views.customViews

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.google.android.exoplayer2.util.Log
import com.treadhill.app.R
import com.treadhill.app.highOrder.convertDptoPx

/**
 * This this the Slider view that is inflated on the Player and Custom workout
 *
 * @constructor
 *
 *
 * @param context
 * @param attrs
 * @param defStyleAttr
 * @param defStyleRes
 */
class Slider constructor(
    context: Context?,
    attrs: AttributeSet?,
    defStyleAttr: Int,
    defStyleRes: Int
) : View(context, attrs, defStyleAttr, defStyleRes) {

    constructor(context: Context?) : this(context, null, 0, 0)

    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0, 0)
    constructor(
        context: Context?,
        attrs: AttributeSet?,
        defStyleAttr: Int
    ) : this(context, attrs, defStyleAttr, 0)

    private var cutpath = Path()
    private var basepath = Path()
    private var extraPath = Path()
    private var borderPath = Path()
    private val borderpaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    lateinit var sliderBitmap: Bitmap
    private var base: Bitmap? = null
    private val paint = Paint()
    private val xpaint = Paint()
    private var mHeight = convertDptoPx(30)
    private var mWidth: Int = 0
    private var bulge: Boolean = true
    var maxRate = 200
        set(value) {
            field = value
            invalidate()
        }
    var minRate = 70
        set(value) {
            field = value
            invalidate()
        }
    var heartRate: Float = 75f
        set(value) {
            field = value
            invalidate()
        }
    private val heartPos: Float
        get() = (heartRate - 70f) * mWidth / (maxRate - minRate)
    private var zoneStart = 120f
    private var zoneEnd = 180f
    private val startx: Float
        get() = (zoneStart - 70f) * mWidth / (maxRate - minRate)
    private val endx: Float
        get() = (zoneEnd - 70f) * mWidth / (maxRate - minRate)

    private val colors = IntArray(3)
    private val smallHeart: Bitmap


    init {
        colors[0] = Color.GREEN
        colors[1] = Color.YELLOW
        colors[2] = Color.RED
        borderpaint.style = Paint.Style.STROKE
        borderpaint.strokeWidth = 4f
        xpaint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
        val opts = BitmapFactory.Options()
        opts.outHeight = mHeight - 16
        smallHeart = BitmapFactory.decodeResource(resources, R.drawable.ic_heart_small, opts)
        invalidate()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        mHeight = MeasureSpec.getSize(heightMeasureSpec)
        mWidth = MeasureSpec.getSize(widthMeasureSpec)
        setMeasuredDimension(mWidth, mHeight)
        base = Bitmap.createBitmap(mWidth, mHeight, Bitmap.Config.ARGB_8888)
        val basCanvas = Canvas(base!!)
        val grad =
            LinearGradient(0f, 0f, base!!.width.toFloat(), 0f, colors, null, Shader.TileMode.MIRROR)
        val p = Paint()
        p.shader = grad
        basCanvas.drawPaint(p)

        val rad = mHeight / 6f
        val startx = 0f
        val endx = mWidth.toFloat()
        val top = (mHeight / 2f) - rad
        val bottom = (mHeight / 2f) + rad
        Log.e("Slide", "view measured width $mWidth height $mHeight startx $startx endx $endx")
        basepath = Path()
        basepath.arcTo(startx, top, startx + (2 * rad), bottom, 90f, 180f, false)
        basepath.moveTo(startx + rad, top)
        basepath.lineTo(endx - rad, top)
        basepath.arcTo(endx - (2 * rad), top, endx, bottom, 270f, 180f, false)
        basepath.lineTo(startx + rad, bottom)

        setZone(100f, 120f)
    }

    /**
     * set the target zone
     *
     * @param zoneStart value from maxRate*0.5 to maxRate*0.9
     * @param zoneEnd value from maxRate*0.6 to maxRate
     */
    private fun setZone(zoneStart: Float, zoneEnd: Float) {
        this.zoneStart = zoneStart
        this.zoneEnd = zoneEnd
        var rad = mHeight / 2f
        extraPath = Path()
        extraPath.arcTo(RectF(startx, 0f, startx + (2 * rad), mHeight.toFloat()), 90f, 180f)
        extraPath.moveTo(startx + rad, 0f)
        extraPath.lineTo(endx - rad, 0f)
        extraPath.arcTo(RectF(endx - (2 * rad), 0f, endx, mHeight.toFloat()), 270f, 180f)
        extraPath.lineTo(startx + rad, mHeight.toFloat())

        val d = 8f
        rad -= d / 2
        borderPath = Path()
        borderPath.arcTo(
            RectF(startx + d, d, startx + (2 * rad) - d, mHeight.toFloat() - d),
            90f,
            180f
        )
        borderPath.moveTo(startx + rad, d)
        borderPath.lineTo(endx - rad - d, d)
        borderPath.arcTo(
            RectF(endx - (2 * rad) + d, d, endx - d, mHeight.toFloat() - d),
            270f,
            180f
        )
        borderPath.lineTo(startx + rad, mHeight.toFloat() - d)


        cutpath.op(basepath, extraPath, Path.Op.UNION)

        sliderBitmap = Bitmap.createBitmap(mWidth, mHeight, Bitmap.Config.ARGB_8888)
        val sliderCanvas = Canvas(sliderBitmap)
        if (bulge)
            sliderCanvas.drawPath(cutpath, paint)
        else
            sliderCanvas.drawPath(basepath, paint)
        sliderCanvas.drawBitmap(base!!, 0f, 0f, xpaint)

        invalidate()

    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.let { can ->
            //can.drawColor(Color.CYAN)
            //can.drawBitmap(base,0f,0f,paint)
            can.drawBitmap(sliderBitmap, 0f, 0f, paint)
            if (bulge)
                can.drawPath(borderPath, borderpaint)
            can.drawBitmap(smallHeart, heartPos - smallHeart.width, 0f, paint)
        }

    }

    /**
     * hide the target zone
     *
     */
    fun disableBulge() {
        bulge = false
        base?.let { base ->
            sliderBitmap = Bitmap.createBitmap(mWidth, mHeight, base.config)
            val sliderCanvas = Canvas(sliderBitmap)
            sliderCanvas.drawPath(basepath, paint)
            sliderCanvas.drawBitmap(base, 0f, 0f, xpaint)
            invalidate()
        }

    }

    /**
     * move the target zone to given zone
     *
     * @param zone value from 0 to 4
     */
    fun moveSlider(zone: Int) {
        if(zone in 0..4) {
            val start = (50f + (zone * 10f)) * maxRate / 100
            val end = (60f + (zone * 10f)) * maxRate / 100
            setZone(start, end)
        }
    }

}