package com.example.recycleviews

import android.location.Location

import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.GeoPoint
import com.google.type.LatLng


class Posts(
    var name:String?="",
    var description:String?="",
    var location: GeoPoint= GeoPoint(0.0,0.0)
    )



