package com.example.recycleviews
import android.content.Intent
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.google.android.gms.maps.model.LatLng
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.GeoPoint
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase

const val POST_POSITION_KEY = "POST_POSITION"
const val POSITION_NOT_SET = -1

class CreateNewPost : AppCompatActivity() {

    lateinit var nameEditText: EditText
    lateinit var descriptionEditText: EditText
    lateinit var locationEditTextLat: EditText
    lateinit var locationEditTextLng: EditText

    lateinit var db: FirebaseFirestore


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_new_post)

        getSupportActionBar()?.setTitle("Where were you?");
        getSupportActionBar()?.setBackgroundDrawable(ColorDrawable(getResources().getColor(R.color.black)));

        db = Firebase.firestore

        nameEditText = findViewById(R.id.nameEditText)
        descriptionEditText = findViewById(R.id.descriptionEditText)
        locationEditTextLat = findViewById(R.id.locationTextLatd)
        locationEditTextLng = findViewById(R.id.locationTextLngt)


        val postPosition = intent.getIntExtra(POST_POSITION_KEY, POSITION_NOT_SET)

        val saveButton = findViewById<Button>(R.id.saveButton)
        if (postPosition != POSITION_NOT_SET) {  // edit post
            displayPost(postPosition)
           // saveButton.setOnClickListener {
                //editPost(postPosition)
            //}

        } else {                                    // create post
            saveButton.setOnClickListener {
                addNewPost()
                savePost()
            }
        }
    }

    fun displayPost(position : Int) {
        val post = DataManagerH.postList[position]

        nameEditText.setText(post.name)
        descriptionEditText.setText(post.description)
        locationEditTextLat.setText(post.location.latitude.toString())
        locationEditTextLng.setText(post.location.longitude.toString())

    }

       /* fun editPost(position: Int) {
        DataManagerH.postList[position].name = nameEditText.text.toString()
        DataManagerH.postList[position].description = descriptionEditText.text.toString()
        DataManagerH.postList[position].location= positionEditText.

        finish()
    }*/

    fun addNewPost() {
        val name = nameEditText.text.toString()
        val description = descriptionEditText.text.toString()
        var latitude= locationEditTextLat.text.toString().toDouble()
        var longitude= locationEditTextLng.text.toString().toDouble()
        var location = GeoPoint (latitude, longitude)

        val post = Posts(name, description, location)


        val intent = Intent ( this, MapsActivityH::class.java)
        intent.putExtra("latitude",post.location.latitude)
        intent.putExtra("longitude",post.location.longitude)
        startActivity(intent)

        DataManagerH.postList.add(post)

        finish()
    }

    fun savePost() {
        val post = Posts(name = nameEditText.text.toString(), description = descriptionEditText.text.toString(),
            location = GeoPoint( locationEditTextLat.text.toString().toDouble(), locationEditTextLng.text.toString().toDouble()))
        nameEditText.setText(post.name)
        descriptionEditText.setText(post.description)
        locationEditTextLat.setText(post.location.latitude.toString())
        locationEditTextLng.setText(post.location.longitude.toString())
        db.collection("PostsH").add(post)

    }
}


