package com.mirkowu.funny.ui.gank

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.mirkowu.funny.R
import com.zzhoujay.richtext.RichText
import kotlinx.android.synthetic.main.activity_pre_view.*




class PreviewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pre_view)
        /*** 完美自适应屏幕 **/
        val sb = StringBuilder()

        sb.append("<body><h3><span style=\\\"color: rgb(255, 0, 0);\\\"><span style=\\\"background-color: inherit;\\\">144期：【最强王者】<\\/span><\\/span>看这里，看这里，看这里=平<span style=\\\"color: rgb(255, 0, 0);\\\">特一肖\\/独平一码<\\/span>=重要的事情我说三遍，你输钱的时代已经过去了，紧跟着我，带你过个好年 <\\/h3><\\/body>")
             .append("<html>")
                .append("<head>")
                .append("<meta charset=\\\"utf-8\\\">")
                .append("<meta id=\\\"viewport\\\" name=\\\"viewport\\\" content=\\\"width=device-width*0.9,initial-scale=1.0,maximum-scale=1.0,user-scalable=false\\\" />")
                .append("<meta name=\\\"apple-mobile-web-app-capable\\\" content=\\\"yes\\\" />")
                .append("<meta name=\\\"apple-mobile-web-app-status-bar-style\\\" content=\\\"black\\\" />")
                .append("<meta name=\\\"black\\\" name=\\\"apple-mobile-web-app-status-bar-style\\\" />")

       // tvContent.text=Html.fromHtml(sb.toString())

        RichText.from(sb.toString().replace("span","div")/*.replace("color:","color=")*/)

//            .urlClick(OnUrlClickListener { url ->
//                if (url.startsWith("code://")) {
//
//                    Toast.makeText(this@MainActivity, url.replaceFirst("code://".toRegex(), ""), Toast.LENGTH_SHORT)
//                        .show()
//
//                    return@OnUrlClickListener true
//
//                }
//
//                false
//            })

            .into(tvContent)
    }


}
