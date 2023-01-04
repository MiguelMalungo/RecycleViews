import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.example.recycleviews.Posts
import com.example.recycleviews.R
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.Marker

class PlacesInfoAdapter(val context : Context) : GoogleMap.InfoWindowAdapter {

    val layoutInflater = LayoutInflater.from(context)

    override fun getInfoContents(p0: Marker): View? {
        return null
    }

    override fun getInfoWindow(marker : Marker): View? {
        val infoWindow = layoutInflater.inflate(R.layout.info_window, null)

        val imageView = infoWindow.findViewById<ImageView>(R.id.imageView)
        val titleView = infoWindow.findViewById<TextView>(R.id.nameTextView)
        val infoView = infoWindow.findViewById<TextView>(R.id.infoTextView)
        val latitude = infoWindow.findViewById<TextView>(R.id.latitudeTextView)
        val longitude = infoWindow.findViewById<TextView>(R.id.longitudeTextView)



        titleView.text = marker.title
        infoView.text = marker.snippet
        latitude.text = marker.position.latitude.toString()
        longitude.text = marker.position.longitude.toString()

        //if (place != null) {
            if (titleView.text == "JUIX") {
                imageView.setImageResource(R.drawable.img_0956)
            } else if (titleView.text == "HUNTER") {
                imageView.setImageResource(R.drawable.img_0229)
            } else if (titleView.text == "VANILLA") {
                imageView.setImageResource(R.drawable.img_1003)
            } else if (titleView.text == "MISS") {
                imageView.setImageResource(R.drawable.img_3768)
            }
        //}

            return infoWindow
        }


}