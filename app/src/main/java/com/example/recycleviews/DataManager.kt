package com.example.recycleviews

import com.google.android.gms.maps.model.LatLng

object DataManager {
    val vanList = mutableListOf<Van>()

    init {
        createMockData()

    }

    fun createMockData(){
        vanList.add(Van("´JUIX´", "VW T5", R.drawable.img_0956))
        vanList.add(Van("´HUNTER´", "VW T4 LONG", R.drawable.img_0229 ))
        vanList.add(Van("´VANILLA´", "MERCEDES VITO CDI",   R.drawable.img_1003))
        vanList.add(Van("´MISS´", "MERCEDES VITO",  R.drawable.img_3768))

    }
//LatLng(0.0, 0.0)

}