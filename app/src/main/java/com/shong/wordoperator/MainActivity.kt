package com.shong.wordoperator

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.shong.wordoperator.basic.BasicActivity
import com.shong.wordoperator.databinding.ActivityMainBinding
import com.shong.wordoperator.libsample.LibraryActivity

class MainActivity : AppCompatActivity() {
    companion object{
        val nameList = listOf(
            "김철수",
            "고고고",
            "권순홍",
            "민성홍",
            "강효재",
            "이이이",
            "박박박",
            "한한한",
            "서서서",
            "갈가가",
            "마마마",
            "마라다",
            "신라면",
            "성서성",
            "성시경",
            "히히히",
            "조조조",
            "핫핳핳",
            "가갸겨",
            "라디오",
            "놀래미",
            "나진"
        )
    }
    private val TAG = this::class.java.simpleName + "_sHong"
    private val binding : ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.run {
            goBasicButton.setOnClickListener {
                startActivity(Intent(this@MainActivity, BasicActivity::class.java))
            }
            goLibraryButton.setOnClickListener {
                startActivity(Intent(this@MainActivity, LibraryActivity::class.java))
            }
        }
    }

}