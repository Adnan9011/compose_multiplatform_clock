package com.adnan.cmpclock

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.unit.dp
import com.adnan.cmpclock.util.Time
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

@Composable
fun AnalogClock() {
    Box(
        modifier = Modifier.size(350.dp),
        contentAlignment = Alignment.Center
    ) {
        var time by remember {
            mutableStateOf(
                Time(
                    hour = 0f,
                    minute = 0f,
                    second = 0f
                )
            )
        }

        DisposableEffect(key1 = 0) {

            val job = CoroutineScope(Dispatchers.IO).launch {
                while (true) {

                    val currentMoment: Instant = Clock.System.now()
                    val calendar = currentMoment.toLocalDateTime(TimeZone.currentSystemDefault())

                    val hour = calendar.hour
                    val minute = calendar.minute
                    val second = calendar.second

                    time = Time(
                        hour = ((hour + (minute / 60f)) * 6f * 5),
                        minute = minute * 6f,
                        second = second * 6f
                    )

                    delay(1000)
                }
            }

            onDispose {
                job.cancel()
            }
        }

        Canvas(modifier = Modifier.size(250.dp)) {

            for (angle in 0..60) {
                rotate(angle * 6f) {
                    if (angle % 5 == 0) {
                        drawLine(
                            color = Color.Black,
                            start = Offset(size.width / 2, 0f),
                            end = Offset(size.width / 2, 40f),
                            strokeWidth = 4f
                        )
                    } else {
                        drawLine(
                            color = Color.Black,
                            start = Offset(size.width / 2, 15f),
                            end = Offset(size.width / 2, 25f),
                            strokeWidth = 4f
                        )
                    }
                }
            }

            rotate(time.hour) {
                drawLine(
                    color = Color.Black,
                    start = Offset(size.width / 2, size.width / 2),
                    end = Offset(size.width / 2, 200f),
                    strokeWidth = 14f
                )
            }

            rotate(time.minute) {
                drawLine(
                    color = Color.Black,
                    start = Offset(size.width / 2, size.width / 2 + 40),
                    end = Offset(size.width / 2, 75f),
                    strokeWidth = 10f
                )
            }

            rotate(time.second) {
                drawLine(
                    color = Color.Red,
                    start = Offset(size.width / 2, size.width / 2),
                    end = Offset(size.width / 2, 75f),
                    strokeWidth = 6f
                )
            }

            drawCircle(
                color = Color.Black,
                radius = 20f
            )
        }
    }
}