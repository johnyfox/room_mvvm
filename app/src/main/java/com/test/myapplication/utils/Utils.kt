package com.test.myapplication.utils

class Utils {
    companion object {
        fun getScreenDensity(density : Float) : String {
            return when(density) {
                2.0f -> "drawable-xhdpi/"
                3.0f -> "drawable-xxhdpi/"
                4.0f -> "drawable-xxxhdpi/"
                else -> "drawable-hdpi/"
            }
        }

        fun getNameOfIcon(platform : String?) : String {
            return when (platform) {
                "Sercomm G450" -> "vera_plus_big.png"
                "Sercomm G550" -> "vera_secure_big.png"
                "MiCasaVerde VeraLite" -> "vera_edge_big.png"
                "Sercomm NA900" -> "vera_edge_big.png"
                "Sercomm NA301" -> "vera_edge_big.png"
                "Sercomm NA930" -> "vera_edge_big.png"
                else -> "vera_edge_big.png"

            }
        }
    }
}