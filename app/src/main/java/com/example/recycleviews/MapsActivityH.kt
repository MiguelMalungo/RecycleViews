package com.example.recycleviews

import PlacesInfoAdapter
import android.content.Intent
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions

import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase

class MapsActivityH : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private var latitude: Double = 0.0
    private var longitude: Double = 0.0

    lateinit var db: FirebaseFirestore

    val postList = mutableListOf<Posts>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps_h)
        val extras = intent.extras

        getSupportActionBar()?.setTitle("Click the Markers for more Info");
        getSupportActionBar()?.setBackgroundDrawable(ColorDrawable(getResources().getColor(R.color.black)));



        if (extras != null) {
            latitude = extras.getDouble("latitude", 0.0)
            longitude = extras.getDouble("longitude", 0.0)
        }


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        val adapter = PlacesInfoAdapter(this)
        mMap.setInfoWindowAdapter(adapter)

        createPlaces()


        db = Firebase.firestore

        val docRef = db.collection("PostsH")



        docRef.addSnapshotListener { snapshot, e ->
            if (snapshot != null) {
                postList.clear()
                Log.d("!!!", "foundsnapshots")
                Log.d("!!!", "${snapshot.documents}")
                for (docuument in snapshot.documents) {
                    val post = docuument.toObject(Posts::class.java)
                    if (post != null) {
                        postList.add(post)

                        Log.d("!!!", "singlepost $post")
                    }
                }
            }

            //createMarker()

            //}

            for (place in postList) {

                val latLng = LatLng(place.location.latitude, place.location.longitude)
                val marker = mMap.addMarker(
                    MarkerOptions().position(latLng).title(place.name).snippet(place.description)
                )
                marker?.tag = place
                mMap.setOnMapClickListener {
                    Log.d("!!!", "onMapReady: " + place.toString())
                }

            }

        }
    }


        fun createMarker() {
            var place = LatLng(latitude, longitude)

            mMap.addMarker(
                MarkerOptions()
                    .position(place)
                    .title("")
                    .snippet("")

            )

        }

        fun createPlaces() {

            val postList = listOf<Posts>()
            //val placeList = listOf<Posts>()


            for (place in postList) {
                val latLng = LatLng(place.location.latitude, place.location.longitude)
                val marker = mMap.addMarker(MarkerOptions().position(latLng))
                marker?.tag = place
                DataManagerH.postList
            }


        }
    }




//data class PlaceInfo(val name: String, val info: String, val postion: LatLng)

