package com.example.sage

import kotlin.random.Random

// Data class representing each greenhouse section
data class SectionData(
    val section: String,
    val temperature: Int,
    val humidity: Int,
    val anomaly: Boolean
)

// Object that simulates greenhouse sensor data
object DataSimulator {

    // Generates data for all 4 sections
    fun generateData(): List<SectionData> {

        return listOf(
            createSection("Section 1"),
            createSection("Section 2"),
            createSection("Section 3"),
            createSection("Section 4")
        )
    }

    // Creates individual section data
    private fun createSection(name: String): SectionData {

        val temperature = Random.nextInt(20, 40)
        val humidity = Random.nextInt(40, 90)

        // anomaly condition
        val anomaly = temperature > 35 || humidity < 45

        return SectionData(
            section = name,
            temperature = temperature,
            humidity = humidity,
            anomaly = anomaly
        )
    }
}