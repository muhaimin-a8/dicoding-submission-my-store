package com.example.mystore

import com.example.mystore.data.Product
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonDecoder
import org.junit.Test

import org.junit.Assert.*
import java.io.File

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
////        assertEquals(4, 2 + 2)
//        val jsonProduct = File("/home/muhaimin/AndroidStudioProjects/MyStore/app/src/main/java/com/example/mystore/data/data.json").readText()
//        val products = Json.decodeFromString<com.example.mystore.Json>(jsonProduct)
//        products.products.forEach() {
//            println(it.images)
//        }
    }
}

@Serializable
data class DataJson(
    val products: List<Product>,
    val total: Int,
    val skip: Int,
    val limit: Int
)