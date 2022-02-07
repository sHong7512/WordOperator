package com.shong.wordoperator.libsample

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSmoothScroller
import com.reddit.indicatorfastscroll.FastScrollItemIndicator
import com.reddit.indicatorfastscroll.FastScrollerView
import com.shong.wordoperator.MainActivity.Companion.nameList
import com.shong.wordoperator.basic.BasicActivity
import com.shong.wordoperator.WordOperator
import com.shong.wordoperator.databinding.ActivityLibraryBinding

// https://github.com/reddit/IndicatorFastScroll 라이브러리
class LibraryActivity : AppCompatActivity() {
    private val TAG = this::class.java.simpleName + "_sHong"
    private val binding : ActivityLibraryBinding by lazy {
        ActivityLibraryBinding.inflate(layoutInflater)
    }

    val wordOperator = WordOperator()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val data = mutableListOf<ListItem>()
        val sortedNameList = nameList.sorted()
        for(str in sortedNameList){
            data.add(ListItem.DataItem(str, showInFastScroll = true))
        }
        data += SAMPLE_DATA_TEXT_AND_HEADERS

        val linearLayoutManager = LinearLayoutManager(this)
        binding.run {
            sampleBasicRecyclerview.apply {
                this.layoutManager = linearLayoutManager
                this.adapter = SampleAdapter(data)
            }

            sampleBasicFastscroller.apply {
                setupWithRecyclerView(
                    sampleBasicRecyclerview,
                    { position ->
                        data[position].takeIf(ListItem::showInFastScroll)?.let { item ->
                            when (item) {
                                is ListItem.HeaderItem -> FastScrollItemIndicator.Icon(item.iconRes)
                                is ListItem.DataItem -> {
                                    //앞 첫글자로 사이드 스크롤 표시
//                                    FastScrollItemIndicator.Text(item.title.substring(0, 1).uppercase())
                                    //초성으로 사이드 스크롤 표시
                                    FastScrollItemIndicator.Text(wordOperator.getInitial(item.title).initalWord)
                                }
                            }
                        }
                    },
                    useDefaultScroller = false,
                    // 보조 스크롤 표시 단어 조절 부분
                    showIndicator = { indicator, indicatorPosition, totalIndicators ->
                        indicatorPosition % 1 == 0
                     }
                )
                val smoothScroller: LinearSmoothScroller = object : LinearSmoothScroller(context) {
                    override fun getVerticalSnapPreference(): Int = SNAP_TO_START
                }
                itemIndicatorSelectedCallbacks += object : FastScrollerView.ItemIndicatorSelectedCallback {
                    override fun onItemIndicatorSelected(
                        indicator: FastScrollItemIndicator,
                        indicatorCenterY: Int,
                        itemPosition: Int
                    ) {
                        sampleBasicRecyclerview.stopScroll()
                        smoothScroller.targetPosition = itemPosition
                        linearLayoutManager.startSmoothScroll(smoothScroller)
                    }
                }
            }

            sampleBasicFastscrollerThumb.apply { setupWithFastScroller(sampleBasicFastscroller) }

        }
    }
}