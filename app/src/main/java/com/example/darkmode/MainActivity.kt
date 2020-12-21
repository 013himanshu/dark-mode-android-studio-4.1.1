package com.example.darkmode

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Switch
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.DataBindingUtil
import com.example.darkmode.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
//        setContentView(R.layout.activity_main)

        val sharedPref: SharedPreferences = this.getSharedPreferences("ThemeStatusPrefs", Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPref.edit()

        //checking if user selected dark or light theme last...
        if (sharedPref.getBoolean("ThemeStatusPrefs", true)) {
            binding.switch1.isChecked = true
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            binding.imageView.setImageResource(R.drawable.image_dark)
            binding.textView.text = "Dark Theme"
        }
        else {
            binding.switch1.isChecked = false
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            binding.imageView.setImageResource(R.drawable.image_light)
            binding.textView.text = "Light Theme"
        }

        binding.switch1.setOnCheckedChangeListener{ _, isChecked ->
            if (isChecked) {
                Log.i("Switch", "ON")
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                binding.imageView.setImageResource(R.drawable.image_dark)
                binding.textView.text = "Dark Theme"

                //shared prefrences...
                editor.putBoolean("ThemeStatusPrefs", true)
                editor.apply()
            }
            else {
                Log.i("Switch", "OFF")
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                binding.imageView.setImageResource(R.drawable.image_light)
                binding.textView.text = "Light Theme"

                //shared prefrences...
                editor.putBoolean("ThemeStatusPrefs", false)
                editor.apply()
            }
        }
    }
}