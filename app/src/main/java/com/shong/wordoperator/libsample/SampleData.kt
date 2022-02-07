package com.shong.wordoperator.libsample

import com.shong.wordoperator.R
import com.thedeanda.lorem.LoremIpsum

val SAMPLE_DATA_TEXT = LoremIpsum.getInstance()
    .getWords(200)
    .split(" ")
    .distinct()
    .sorted()
    .map { ListItem.DataItem(it) }

val SAMPLE_DATA_TEXT_AND_HEADERS =
    listOf(ListItem.HeaderItem(
        "Favorites",
        R.drawable.indicator_favorites,
        showInFastScroll = true
    )) +
        LoremIpsum.getInstance()
            .getWords(15)
            .split(" ")
            .distinct()
            .map { ListItem.DataItem(it, showInFastScroll = false) } +
        listOf(ListItem.HeaderItem(
            "All",
            R.drawable.indicator_words,
            showInFastScroll = true
        )) +
        LoremIpsum.getInstance()
            .getWords(200)
            .split(" ")
            .distinct()
            .sorted()
            .map { ListItem.DataItem(it) }

sealed class ListItem(val showInFastScroll: Boolean = true) {

  class HeaderItem(
      val title: String,
      val iconRes: Int,
      showInFastScroll: Boolean
  ) : ListItem(showInFastScroll)

  class DataItem(
      val title: String,
      showInFastScroll: Boolean = true
  ) : ListItem(showInFastScroll)

}
