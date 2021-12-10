package com.example.parallax_views

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

/** Этот код написан на основании статьи https://habr.com/ru/company/dododev/blog/584498/
 * Большая благодарность за интересные фичи автору keymusicman
**/


class MainActivity : AppCompatActivity() {
    var isInitialized = false

    // матрица поворота начального положения устройства в пространстве
    // массив размера 16 нужен для хранения значений матрицы 4х4
    val initialValues = FloatArray(16)

    // буфер для матрицы поворота
    val rotationMatrix = FloatArray(16)

    // буфер вычисления для изменения угла
    val angleChange = FloatArray(16)

    // инициализируем сенсоры перемещения девайса
    private val sensorManager: SensorManager? by lazy {
        this.getSystemService(Context.SENSOR_SERVICE)
                as? SensorManager
    }
    private val rotationVectorSensor: Sensor? by lazy {
        sensorManager?.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR)
    }

    // views которые будут смещаться в зависимости от движения девайса
    private val hammer: View by lazy { findViewById<ImageView>(R.id.hammerImage) }
    private val yellowBall: View by lazy { findViewById<ImageView>(R.id.ballImage) }
    private val textRotate: View by lazy { findViewById<TextView>(R.id.textView) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getRotation(sensorManager, rotationVectorSensor)
    }

    private fun getRotation(sensorManager: SensorManager?, rotationVectorSensor: Sensor?) {

        if (sensorManager == null || rotationVectorSensor == null) return

        sensorManager.registerListener(object : SensorEventListener {
            override fun onSensorChanged(event: SensorEvent) {
                // получаем вектор поворота устройства в пространстве
                val rotationVector = event.values

                if (!isInitialized) {
                    isInitialized = true
                    // запоминаем матрицу поворота для начального положения устройства
                    SensorManager.getRotationMatrixFromVector(initialValues, rotationVector)
                    return
                }
                // получаем матрицу поворота для текущего положения устройства
                SensorManager.getRotationMatrixFromVector(rotationMatrix, rotationVector)

                // получаем пространственный угол между начальным и текущим положением
                SensorManager.getAngleChange(angleChange, rotationMatrix, initialValues)
                rotateViews()
            }

            override fun onAccuracyChanged(
                sensor: Sensor?,
                accuracy: Int
            ) {
            }
        }, rotationVectorSensor, SensorManager.SENSOR_DELAY_NORMAL)
    }

    private fun rotateViews() {
        val maxRotate = 300
        angleChange.forEachIndexed { index, value ->
            val fraction = (value / Math.PI)
                .coerceIn(-1.0, 1.0)
                .toFloat()
            angleChange[index] = fraction
        }
        hammer.run {
            translationX = -angleChange[2] * maxRotate
            translationY = angleChange[1] * maxRotate
        }
        yellowBall.run {
            translationX = angleChange[2] * maxRotate
            translationY = -angleChange[1] * maxRotate
        }
        textRotate.run {
            translationX = angleChange[0] * maxRotate / 2
            translationY = -angleChange[1] * maxRotate / 2
        }
    }
}

