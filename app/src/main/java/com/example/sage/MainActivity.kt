package com.example.sage

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import kotlinx.coroutines.delay
import okhttp3.OkHttpClient
import okhttp3.Request
import kotlin.concurrent.thread

class MainActivity : ComponentActivity() {

    private val channelId = "greenhouse_alert"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        createNotificationChannel()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                1
            )
        }

        setContent {
            GreenhouseScreen(this)
        }

        connectToEC2()
    }

    private fun createNotificationChannel() {

        val manager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val channel = NotificationChannel(
            channelId,
            "Greenhouse Alerts",
            NotificationManager.IMPORTANCE_HIGH
        )

        manager.createNotificationChannel(channel)
    }

    fun showNotification(title: String, message: String) {

        val builder = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle(title)
            .setContentText(message)
            .setPriority(NotificationCompat.PRIORITY_HIGH)

        val manager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        manager.notify(System.currentTimeMillis().toInt(), builder.build())
    }

    private fun connectToEC2() {

        thread {

            try {

                val client = OkHttpClient()

                val request = Request.Builder()
                    .url("http://ec2-3-92-147-245.compute-1.amazonaws.com/")
                    .build()

                val response = client.newCall(request).execute()

                if (response.isSuccessful) {

                    showNotification(
                        "☁ Server Connected",
                        "EC2 connection successful"
                    )
                }

            } catch (_: Exception) {

                showNotification(
                    "⚠ Server Error",
                    "Could not connect to EC2"
                )
            }
        }
    }
}

@Composable
fun GreenhouseScreen(activity: MainActivity) {

    var data by remember {
        mutableStateOf(DataSimulator.generateData())
    }

    LaunchedEffect(true) {

        while (true) {


            delay(3000)

            data = DataSimulator.generateData()

            data.forEach {

                if (it.anomaly) {

                    activity.showNotification(
                        "⚠ Alert in ${it.section}",
                        "Abnormal temperature or humidity"
                    )
                }
            }
        }
    }

    val backgroundGradient = Brush.verticalGradient(
        listOf(
            Color(0xFF0F2027),
            Color(0xFF203A43),
            Color(0xFF2C5364)
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundGradient)
            .padding(16.dp)
    ) {

        Text(
            text = "🌿 SAGE Smart Greenhouse",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Live Environmental Monitoring",
            fontSize = 16.sp,
            color = Color.LightGray
        )

        Spacer(modifier = Modifier.height(20.dp))

        LazyColumn {

            items(data) { section ->

                val cardGradient =
                    if (section.anomaly)
                        Brush.horizontalGradient(
                            listOf(Color(0xFFFF512F), Color(0xFFDD2476))
                        )
                    else
                        Brush.horizontalGradient(
                            listOf(Color(0xFF11998E), Color(0xFF38EF7D))
                        )

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp),

                    shape = RoundedCornerShape(20.dp),

                    elevation = CardDefaults.cardElevation(12.dp)
                ) {

                    Column(
                        modifier = Modifier
                            .background(cardGradient)
                            .padding(20.dp)
                    ) {

                        Text(
                            text = section.section,
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        Text(
                            text = "🌡 Temperature: ${section.temperature} °C",
                            fontSize = 18.sp,
                            color = Color.White
                        )

                        Text(
                            text = "💧 Humidity: ${section.humidity} %",
                            fontSize = 18.sp,
                            color = Color.White
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text =
                                if (section.anomaly)
                                    "⚠ STATUS: ALERT"
                                else
                                    "✅ STATUS: NORMAL",

                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }
                }
            }
        }
    }
}