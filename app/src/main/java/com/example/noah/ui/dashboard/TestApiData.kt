package com.example.noah.ui.dashboard

import android.util.Log
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserException
import org.xmlpull.v1.XmlPullParserFactory
import java.io.IOException
import java.net.MalformedURLException
import java.net.URL


class TestApiData {
    var apiUrl =
        "http://apis.data.go.kr/1360000/NwpModelInfoService/getLdapsUnisArea"
    var apiKey = "SdolYQCjCO7v7%2FnMMtiR9Czt9gwK4jYM43iNiXSfPuZ2GW5ho%2BcOTOp2ASZUg8fUItSmpCoK1swFDJQ2M2MlMw%3D%3D"

    fun getData(): ArrayList<TestData>? {
        //return data와 관련된 부분
        val dataArr = ArrayList<TestData>()

        //네트워킹 작업은 메인스레드에서 처리하면 안된다. 따로 스레드를 만들어 처리하자
        val t: Thread = object : Thread() {
            override fun run() {
                try {

                    //url과 관련된 부분
                    val fullurl = "$apiUrl?serviceKey=$apiKey&returnType=XML"
                    val url = URL(fullurl)
                    val `is` = url.openStream()

                    //xmlParser 생성
                    val xmlFactory = XmlPullParserFactory.newInstance()
                    val parser = xmlFactory.newPullParser()
                    parser.setInput(`is`, "utf-8")

                    //xml과 관련된 변수들
                    var bValue = false
                    var bLat = false
                    var bLong = false
                    var value: String? = ""
                    var latitude: String? = ""
                    var longitude: String? = ""

                    //본격적으로 파싱
                    while (parser.eventType != XmlPullParser.END_DOCUMENT) {
                        var type = parser.eventType
                        val data = TestData()

                        //태그 확인
                        if (type == XmlPullParser.START_TAG) {
                            if (parser.name == "col") {
                                if (parser.getAttributeValue(0) == "위치명")
                                    bValue = true
                                else if (parser.getAttributeValue(0) == "위도")
                                    bLat = true
                                else if (parser.getAttributeValue(0) == "경도")
                                    bLong = true
                            }
                        } else if (type == XmlPullParser.TEXT) {
                            if (bValue) {
                                value = parser.text
                                bValue = false
                            } else if (bLat) {
                                latitude = parser.text
                                bLat = false
                            } else if (bLong) {
                                longitude = parser.text
                                bLong = false
                            }
                            if (value != null) {
                                Log.d("DataPar", value)
                            }
                        }
                        //내용 다 읽었으면 데이터 추가
                        else if (type == XmlPullParser.END_TAG && parser.name == "item") {
                            if (value != null) {
                                data.value = value.toDouble()
                            }
                            data.latitude = java.lang.Double.valueOf(latitude)
                            data.longitude = java.lang.Double.valueOf(longitude)
                            dataArr.add(data)
                            Log.d("DataPar", dataArr.toString())
                        }
                        type = parser.next()
                    }
                } catch (e: MalformedURLException) {
                    e.printStackTrace()
                } catch (e: XmlPullParserException) {
                    e.printStackTrace()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        }
        try {
            t.start()
            t.join()
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
        return dataArr
    }
}