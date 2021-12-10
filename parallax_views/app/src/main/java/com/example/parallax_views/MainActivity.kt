package com.example.parallax_views

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewPropertyAnimator
import android.view.animation.DecelerateInterpolator
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.parallax_views.data.SensorData
import com.example.parallax_views.data.SourceSensorsData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


/** Этот код написан на основании статьи https://habr.com/ru/company/dododev/blog/584498/
 * Большая благодарность за интересную фичю автору keymusicman
 **/


class MainActivity : AppCompatActivity() {

    private val sourceSensorsData: SourceSensorsData by lazy {
        SourceSensorsData(this,::getVector)
    }

    // views которые будут смещаться в зависимости от движения девайса
    private val hammer: View by lazy { findViewById<ImageView>(R.id.hammerImage) }
    private val yellowBall: View by lazy { findViewById<ImageView>(R.id.ballImage) }
    private val textRotate: View by lazy { findViewById<TextView>(R.id.textView) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sourceSensorsData.getDataOfRotation()
    }

    private fun rotateViews(sensorData: SensorData) {
        val maxRotate = 300
        hammer.animate {
            translationX(-sensorData.dx * maxRotate)
            translationY(sensorData.dy * maxRotate)
        }
        yellowBall.animate {
            translationX(sensorData.dx * maxRotate)
            translationY(-sensorData.dy * maxRotate)
        }
        textRotate.animate {
            translationX(-sensorData.dx * maxRotate/2)
            translationY(sensorData.dy * maxRotate/2)
        }
    }

    private fun View.animate(builder: ViewPropertyAnimator.() -> Unit) {
        animate()
            .apply {
                duration = 300
                interpolator = DecelerateInterpolator()
                builder()
            }
            .start()
    }

    private fun getVector(value: SensorData){
         rotateViews(value)
    }
}

