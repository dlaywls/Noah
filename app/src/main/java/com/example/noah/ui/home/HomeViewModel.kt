package com.example.noah.ui.home

class HomeViewModel{

    /*private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text*/
    var id: String?=" "
    var title:String=""
    var contents:String=""
    constructor(){}

    constructor(id: String?, title: String, contents: String){
        this.id = id
        this.title = title
        this.contents = contents
    }

}
