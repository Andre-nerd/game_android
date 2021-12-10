package com.example.parallax_views.data

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager


class SourceSensorsData(context: Context, val getVector: (value: SensorData) -> Unit) {
    var isInitialized = false

    // матрица поворота начального положения устройства в пространстве
    // массив размера 16 нужен для хранения значений матрицы 4х4
    val initialValues = FloatArray(16)

    // буфер для матрицы поворота
    val rotationMatrix = FloatArray(16)

    // буфер вычисления для изменения угла
    val angleChange = FloatArray(16)

    // инициализируем сенсоры перемещения девайса
    private val sensorManager: SensorManager? = context.getSystemService(Context.SENSOR_SERVICE)
            as? SensorManager

    private val rotationVectorSensor: Sensor? =
        sensorManager?.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR)

    fun getDataOfRotation() {

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
                convertAngleChange()
                // отправляем вектор движения в коллбэк
                getVector(SensorData(angleChange[2], angleChange[1], angleChange[0]))
            }

            override fun onAccuracyChanged(
                sensor: Sensor?,
                accuracy: Int
            ) {
            }
        }, rotationVectorSensor, SensorManager.SENSOR_DELAY_NORMAL)
    }

    private fun convertAngleChange() {
        angleChange.forEachIndexed { index, value ->
            val fraction = (value / Math.PI)
                .coerceIn(-1.0, 1.0)
                .toFloat()
            angleChange[index] = fraction
        }
    }
}

