package com.android.restapp

import com.google.gson.annotations.SerializedName

class GsonParse {

    @SerializedName("page")
    var page = Pages()
}

class Pages {

    @SerializedName("cards")
    var cards = ArrayList<Cards>()
}

class Cards {

    @SerializedName("card_type")
    var card_type = "text"

    @SerializedName("card")
    var card = Card()
}

class Card {

    @SerializedName("image")
    var image = Image()

    @SerializedName("value")
    var value = ""

    @SerializedName("attributes")
    var attributes = Attribute()

    @SerializedName("title")
    var title = Title()

    @SerializedName("description")
    var description = Description()
}

class Image {

    @SerializedName("url")
    var url = ""

    @SerializedName("size")
    var size = Size()
}

class Size {

    @SerializedName("width")
    var width = 600

    @SerializedName("height")
    var height = 800
}

class Description {

    @SerializedName("value")
    var value = ""

    @SerializedName("attributes")
    var attributes = Attribute()
}

class Title {
    @SerializedName("value")
    var value = ""

    @SerializedName("attributes")
    var attributes = Attribute()
}

class Attribute {
    @SerializedName("text_color")
    var text_color = ""


    @SerializedName("font")
    val font = Font()
}

class Font {

    @SerializedName("size")
    val size = 12
}