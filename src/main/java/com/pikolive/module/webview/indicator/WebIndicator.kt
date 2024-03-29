package com.pikolive.module.webview.indicator

import android.animation.*
import android.animation.ValueAnimator.AnimatorUpdateListener
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.animation.DecelerateInterpolator
import android.view.animation.LinearInterpolator
import com.internet.boy.androidbase.kutils.dip2px

/**
 * Creator: ED
 * Date: 2020/11/16 3:49 PM
 * Mail: salahayo3192@gmail.com
 *
 * **/
class WebIndicator @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : BaseIndicatorView(context, attrs, defStyleAttr), BaseIndicatorSpec {

    /**
     * 进度条颜色
     */
    private var mColor = 0

    /**
     * 进度条的画笔
     */
    private var mPaint: Paint = Paint()

    /**
     * 进度条动画
     */
    private var mAnimator: Animator? = null

    /**
     * 控件的宽度
     */
    private var mTargetWidth = 0

    /**
     * 当前匀速动画最大的时长
     */
    private var mCurrentMaxUniformSpeedDuration = MAX_UNIFORM_SPEED_DURATION

    /**
     * 当前加速后减速动画最大时长
     */
    private var mCurrentMaxDecelerateSpeedDuration = MAX_DECELERATE_SPEED_DURATION

    /**
     * 结束动画时长
     */
    private var mCurrentDoEndAnimationDuration = DO_END_ANIMATION_DURATION

    /**
     * 当前进度条的状态
     */
    private var indicatorStatus = 0
    private var mCurrentProgress = 0f

    /**
     * 默认的高度
     */
    var mWebIndicatorDefaultHeight = 3


    init {
        mPaint = Paint()
        mColor = Color.parseColor("#1aad19")
        mPaint.isAntiAlias = true
        mPaint.color = mColor
        mPaint.isDither = true
        mPaint.strokeCap = Paint.Cap.SQUARE
        mTargetWidth = context.resources.displayMetrics.widthPixels
        mWebIndicatorDefaultHeight = context.dip2px(3)
    }

    fun setColor(color: Int) {
        mColor = color
        mPaint.color = color
    }

    fun setColor(color: String?) {
        this.setColor(Color.parseColor(color))
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val wMode = MeasureSpec.getMode(widthMeasureSpec)
        var w = MeasureSpec.getSize(widthMeasureSpec)
        val hMode = MeasureSpec.getMode(heightMeasureSpec)
        var h = MeasureSpec.getSize(heightMeasureSpec)
        if (wMode == MeasureSpec.AT_MOST) {
            w =
                if (w <= context.resources.displayMetrics.widthPixels)
                    w
                else
                    context.resources.displayMetrics.widthPixels
        }
        if (hMode == MeasureSpec.AT_MOST) {
            h = mWebIndicatorDefaultHeight
        }
        this.setMeasuredDimension(w, h)
    }

    override fun onDraw(canvas: Canvas?) {}
    override fun dispatchDraw(canvas: Canvas) {
        canvas.drawRect(
            0f,
            0f,
            mCurrentProgress / 100 * java.lang.Float.valueOf(this.width.toFloat()),
            this.height.toFloat(),
            mPaint
        )
    }

    override fun show() {
        if (visibility == View.GONE) {
            this.visibility = View.VISIBLE
            mCurrentProgress = 0f
            startAnim(false)
        }
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        mTargetWidth = measuredWidth
        val screenWidth: Int = context.resources.displayMetrics.widthPixels
        if (mTargetWidth >= screenWidth) {
            mCurrentMaxDecelerateSpeedDuration = MAX_DECELERATE_SPEED_DURATION
            mCurrentMaxUniformSpeedDuration = MAX_UNIFORM_SPEED_DURATION
            mCurrentDoEndAnimationDuration = MAX_DECELERATE_SPEED_DURATION
        } else {
            //取比值
            val rate = mTargetWidth / java.lang.Float.valueOf(screenWidth.toFloat())
            mCurrentMaxUniformSpeedDuration = (MAX_UNIFORM_SPEED_DURATION * rate).toInt()
            mCurrentMaxDecelerateSpeedDuration = (MAX_DECELERATE_SPEED_DURATION * rate).toInt()
            mCurrentDoEndAnimationDuration = (DO_END_ANIMATION_DURATION * rate).toInt()
        }
        Log.d("WebProgress", "CURRENT_MAX_UNIFORM_SPEED_DURATION$mCurrentMaxUniformSpeedDuration")
    }

    private fun setProgress(progress: Float) {
        if (visibility == View.GONE) {
            visibility = View.VISIBLE
        }
        if (progress < 95f) {
            return
        }
        if (indicatorStatus != FINISH) {
            startAnim(true)
        }
    }

    override fun hide() {
        indicatorStatus = FINISH
    }

    private fun startAnim(isFinished: Boolean) {
        val v: Float = if (isFinished) 100F else 95.toFloat()
        if (mAnimator != null && mAnimator!!.isStarted) {
            mAnimator!!.cancel()
        }
        mCurrentProgress = if (mCurrentProgress == 0f) 0.00000001f else mCurrentProgress
        if (!isFinished) {
            val animatorSet = AnimatorSet()
            val p1 = v * 0.60f
            val animator = ValueAnimator.ofFloat(mCurrentProgress, p1)
            val animator0 = ValueAnimator.ofFloat(p1, v)
            val residue = 1f - mCurrentProgress / 100 - 0.05f
            val duration = (residue * mCurrentMaxUniformSpeedDuration).toLong()
            val duration6 = (duration * 0.6f).toLong()
            val duration4 = (duration * 0.4f).toLong()
            animator.interpolator = LinearInterpolator()
            animator.duration = duration4
            animator.addUpdateListener(mAnimatorUpdateListener)
            animator0.interpolator = LinearInterpolator()
            animator0.duration = duration6
            animator0.addUpdateListener(mAnimatorUpdateListener)
            animatorSet.play(animator0).after(animator)
            animatorSet.start()
            mAnimator = animatorSet
        } else {
            var segment95Animator: ValueAnimator? = null
            if (mCurrentProgress < 95f) {
                segment95Animator = ValueAnimator.ofFloat(mCurrentProgress, 95f)
                val residue = 1f - mCurrentProgress / 100f - 0.05f
                segment95Animator.duration = (residue * mCurrentMaxDecelerateSpeedDuration).toLong()
                segment95Animator.interpolator = DecelerateInterpolator()
                segment95Animator.addUpdateListener(mAnimatorUpdateListener)
            }
            val mObjectAnimator = ObjectAnimator.ofFloat(this, "alpha", 1f, 0f)
            mObjectAnimator.duration = mCurrentDoEndAnimationDuration.toLong()
            val mValueAnimatorEnd = ValueAnimator.ofFloat(95f, 100f)
            mValueAnimatorEnd.duration = mCurrentDoEndAnimationDuration.toLong()
            mValueAnimatorEnd.addUpdateListener(mAnimatorUpdateListener)
            var animatorSet = AnimatorSet()
            animatorSet.playTogether(mObjectAnimator, mValueAnimatorEnd)
            if (segment95Animator != null) {
                val animatorSet0 = AnimatorSet()
                animatorSet0.play(animatorSet).after(segment95Animator)
                animatorSet = animatorSet0
            }
            animatorSet.addListener(mAnimatorListenerAdapter)
            animatorSet.start()
            mAnimator = animatorSet
        }
        indicatorStatus = STARTED
    }

    private val mAnimatorUpdateListener =
        AnimatorUpdateListener { animation ->
            val t = animation.animatedValue as Float
            mCurrentProgress = t
            this@WebIndicator.invalidate()
        }
    private val mAnimatorListenerAdapter: AnimatorListenerAdapter =
        object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                doEnd()
            }
        }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        /**
         * animator cause leak , if not cancel;
         */
        if (mAnimator != null && mAnimator!!.isStarted) {
            mAnimator!!.cancel()
            mAnimator = null
        }
    }

    private fun doEnd() {
        if (indicatorStatus == FINISH && mCurrentProgress == 100f) {
            visibility = View.GONE
            mCurrentProgress = 0f
            this.alpha = 1f
        }
        indicatorStatus = UN_START
    }

    override fun reset() {
        mCurrentProgress = 0f
        if (mAnimator != null && mAnimator!!.isStarted) {
            mAnimator!!.cancel()
        }
    }

    override fun setProgress(newProgress: Int) {
        setProgress(java.lang.Float.valueOf(newProgress.toFloat()))
    }


    companion object {
        /**
         * 默认匀速动画最大的时长
         */
        const val MAX_UNIFORM_SPEED_DURATION = 8 * 1000

        /**
         * 默认加速后减速动画最大时长
         */
        const val MAX_DECELERATE_SPEED_DURATION = 450

        /**
         * 结束动画时长 ， Fade out 。
         */
        const val DO_END_ANIMATION_DURATION = 600
        const val UN_START = 0
        const val STARTED = 1
        const val FINISH = 2
    }
}
