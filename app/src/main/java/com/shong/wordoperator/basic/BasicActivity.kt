package com.shong.wordoperator.basic

import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shong.wordoperator.MainActivity.Companion.nameList
import com.shong.wordoperator.WordOperator
import com.shong.wordoperator.databinding.ActivityBasicBinding

class BasicActivity : AppCompatActivity() {

    private val TAG = this::class.java.simpleName + "_sHong"
    private val binding : ActivityBasicBinding by lazy {
        ActivityBasicBinding.inflate(layoutInflater)
    }
    lateinit var adapter: NameAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val sortedNameList = nameList.sorted()
        Log.d(TAG,"list -> ${sortedNameList}")

        adapter = NameAdapter(this@BasicActivity, sortedNameList)
        binding.run {

            nameRV.adapter = adapter
            nameRV.layoutManager = LinearLayoutManager(this@BasicActivity, RecyclerView.VERTICAL, false)

            val yList = mutableListOf<Float>()
            val strList = mutableListOf<String>()
            val isPostedList = mutableListOf<Boolean>()
            for(i in 0 until 5){
                isPostedList.add(false)
            }

            fun posted(){
                for(ip in isPostedList){
                    if(!ip) return
                }
                yList.add(scrollText1.y)
                yList.add(scrollText2.y)
                yList.add(scrollText3.y)
                yList.add(scrollText4.y)
                yList.add(scrollText5.y)

                strList.add(scrollText1.text.toString())
                strList.add(scrollText2.text.toString())
                strList.add(scrollText3.text.toString())
                strList.add(scrollText4.text.toString())
                strList.add(scrollText5.text.toString())

                sideSearchBarLayout.setOnTouchListener { v, event ->
                    val y = event?.y ?: return@setOnTouchListener true
                    when (event?.action) {
                        MotionEvent.ACTION_DOWN -> {
                            for(i in 1 until yList.size){
                                if(y < yList[i]){
                                    onTextClick(strList[i - 1])
                                    return@setOnTouchListener true
                                }
                            }
                            onTextClick(strList[strList.size - 1])
                        }
                        MotionEvent.ACTION_MOVE -> {
                            for(i in 1 until yList.size){
                                if(y < yList[i]){
                                    onTextClick(strList[i - 1])
                                    return@setOnTouchListener true
                                }
                            }
                            onTextClick(strList[strList.size - 1])
                        }
                        MotionEvent.ACTION_UP -> {
                            for(i in 1 until yList.size){
                                if(y < yList[i]){
                                    onTextClick(strList[i - 1])
                                    return@setOnTouchListener true
                                }
                            }
                            onTextClick(strList[strList.size - 1])
                        }
                    }
                    true
                }
            }
            scrollText1.postDelayed(Runnable { isPostedList[0] = true; posted() }, 100L)
            scrollText2.postDelayed(Runnable { isPostedList[1] = true; posted() }, 100L)
            scrollText3.postDelayed(Runnable { isPostedList[2] = true; posted() }, 100L)
            scrollText4.postDelayed(Runnable { isPostedList[3] = true; posted() }, 100L)
            scrollText5.postDelayed(Runnable { isPostedList[4] = true; posted() }, 100L)

            text1.setOnClickListener { onTextClick(text1.text.toString()) }
            text2.setOnClickListener { onTextClick(text2.text.toString()) }
            text3.setOnClickListener { onTextClick(text3.text.toString()) }
            text4.setOnClickListener { onTextClick(text4.text.toString()) }
            text5.setOnClickListener { onTextClick(text5.text.toString()) }
        }

    }

    fun onTextClick(str: String){
        val position = adapter.getPositionInitalChs(str)
//        binding.nameRV.smoothScrollToPosition(position)
        binding.nameRV.scrollToPosition(position)
        binding.selectedText.text = adapter.nameList[position]
        adapter.setSelectedPosition(position)
    }

    fun keywordTest(){
        val wordOperator = WordOperator()
        val keyword = "반갑습니다. 개발자 sHong입니다. 1234567890!@#$%^&*(){}|:<>?걔걕걖걗걘걙걚걛걜걝걞걟걠걡걢걣걤걥걦걧걨걩걪걫걬걭걮걯"
        val map = mutableMapOf<String, String>()
        for (i in 0 until keyword.length) {
            val k = keyword.substring(i, i + 1)
            map.put(k, wordOperator.getInitial(k).initalWord)
        }
        Log.d(TAG,"${map}")
    }

}