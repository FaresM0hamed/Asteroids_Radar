package com.example.asteroidradarapp

object NetworkConstants {
    //add your api_key here
    const val API_KEY = "KX2NoqosxjRyy1slTCTFXYNIcynhgqEMlx5nbluL"
    const val BASE_URL = "https://api.nasa.gov/"
    const val HTTP_GET_NEO_FEED_PATH = "neo/rest/v1/feed"
    const val QUERY_API_KEY_PARAM = "api_key"
    const val HTTP_GET_APOD_PATH = "planetary/apod"
}

object DatabaseConstants {
    const val TABLE_NAME = "asteroid"
    const val DATABASE_FILE_NAME = "asteroid.db"
}