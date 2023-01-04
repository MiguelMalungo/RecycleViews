package com.example.recycleviews

import com.google.android.gms.maps.model.LatLng
import com.google.firebase.firestore.GeoPoint

object DataManagerH {
    val postList = mutableListOf<Posts>()


    init {
        createMockData()

    }

    fun createMockData(){
        postList.add(Posts("", "", GeoPoint(0.0, 0.0)))

    }


}