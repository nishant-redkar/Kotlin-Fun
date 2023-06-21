package com.example.newhomeapp

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapShader
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Shader
import android.os.Bundle
import android.util.Log
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.newhomeapp.GoogleLogin.MainActivity2
import com.example.newhomeapp.Weather.WeatherActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.squareup.picasso.Transformation
import org.json.JSONException
import java.util.Dictionary

class MainActivity : AppCompatActivity() {

    private var bottomNavigationView: BottomNavigationView? = null
    private lateinit var frameLayout: FrameLayout
    private lateinit var mAuth: FirebaseAuth
    private lateinit var cardView: CardView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mAuth = FirebaseAuth.getInstance()
        val user: FirebaseUser? = FirebaseAuth.getInstance().currentUser
        val photoUrl: String? = user?.photoUrl?.toString()
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigationView.setItemIconTintList(null);


        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {

                R.id.navigation_item2 -> {
                    val weatherFragment = WeatherActivity()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.flFragment, weatherFragment)
                        .commit()
                    true
                }

                R.id.navigation_item3 -> {
                    val dictionaryFragment = com.example.newhomeapp.Dictionary.Dictionary()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.flFragment, dictionaryFragment)
                        .commit()
                    true
                }

                R.id.navigation_item4 -> {
                    val calculatorFragment = Calculator()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.flFragment, calculatorFragment)
                        .commit()
                    true
                }

                else -> false
            }
        }


        val imageView2 = findViewById<ImageView>(R.id.imageView2)
        imageView2.setOnClickListener {
            mAuth.signOut()
            Toast.makeText(this@MainActivity, "Logged out", Toast.LENGTH_SHORT).show()
            val i = Intent(this@MainActivity, MainActivity2::class.java)
            startActivity(i)
            finish()
        }



        frameLayout = findViewById(R.id.flFragment)

        fetchWallpaper()

        val imageView: ImageView = findViewById(R.id.imageView)
        Picasso.get()
            .load(photoUrl)
            .transform(CircleTransform())
            .into(imageView)
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun fetchWallpaper() {

        val url =
            "https://api.unsplash.com/photos/random?query=programming&client_id=xqTXG8znx-qYjCRoO-6mOySGTFuVbND3qd-lWJnCMcE"

        val request = JsonObjectRequest(
            Request.Method.GET, url, null,
            { response ->
                try {
                    val urlsObject = response.getJSONObject("urls")
                    val regularUrl = urlsObject.getString("regular")
                    displayWallpaper(regularUrl)
                } catch (e: JSONException) {
                    Log.e(TAG, "JSON parsing error: " + e.message)
                }
            },
            { error ->
                Log.e(TAG, "API request error: " + error.message)
            })

        val queue: RequestQueue = Volley.newRequestQueue(this)
        queue.add(request)
    }

    private fun displayWallpaper(imageUrl: String) {
        var imageView: ImageView? = null

        if (imageView == null) {
            imageView = ImageView(this)
            imageView.layoutParams = FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT
            )
            frameLayout.addView(imageView)
        }

        Picasso.get().load(imageUrl).fit().centerCrop().into(imageView,
            object : Callback {
                override fun onSuccess() {
                    Log.d(TAG, "Image loaded successfully.")
                }

                override fun onError(e: Exception?) {
                    Log.e(TAG, "Failed to load image: " + e?.message)
                }
            })
    }



    companion object {
        private const val TAG = "MainActivity"
    }
    private inner class CircleTransform : Transformation {
        override fun transform(source: Bitmap): Bitmap {
            val size = Math.min(source.width, source.height)
            val x = (source.width - size) / 2
            val y = (source.height - size) / 2
            val squaredBitmap = Bitmap.createBitmap(source, x, y, size, size)
            if (squaredBitmap !== source) {
                source.recycle()
            }
            val bitmap = Bitmap.createBitmap(size, size, source.config)
            val canvas = Canvas(bitmap)
            val paint = Paint()
            val shader = BitmapShader(
                squaredBitmap,
                Shader.TileMode.CLAMP,
                Shader.TileMode.CLAMP
            )
            paint.shader = shader
            paint.isAntiAlias = true
            val radius = size / 2f
            canvas.drawCircle(radius, radius, radius, paint)
            squaredBitmap.recycle()
            return bitmap
        }

        override fun key(): String {
            return "circle"
        }
    }
}


