package com.example.android.kotlinrv

import android.app.AlertDialog
import android.content.DialogInterface
import android.graphics.Color
import android.os.Bundle
import android.text.InputType
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.kotlinrv.recyclerView.AdapterRV
import com.example.android.kotlinrv.recyclerView.NoteDataClass
import com.example.android.kotlinrv.recyclerView.RecyclerInterface
import kotlinx.coroutines.*
import java.util.*


class MainActivity : AppCompatActivity() {

    var string =""
    private lateinit var arrayList: ArrayList<NoteDataClass>
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapterRV: AdapterRV
    private lateinit var button: Button
    var counter = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        arrayList = ArrayList()
        addData()
        recyclerViewSetUp()
        onClickListeners()
    }

    private fun onClickListeners() {
        button = findViewById(R.id.add_button)
        button.setOnClickListener {
            val color = colorGenerator()
            arrayList.add(NoteDataClass("شكرا " + counter, "شكرا  " + counter, color))
            counter++
            recyclerUpdated()
        }
    }


    private fun colorGenerator(): Int {
        val rnd = Random()
        return Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))
    }


    private fun recyclerUpdated() {
        adapterRV.notifyDataSetChanged()
    }

    private fun recyclerViewSetUp() {
        recyclerView = findViewById(R.id.RV)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapterRV = AdapterRV(arrayList)
        recyclerView.adapter = adapterRV
        adapterRV.setOnClicked(object : RecyclerInterface {
            override fun onClick(position: Int) {
                val dataObject = arrayList[position]
                Toast.makeText(this@MainActivity, dataObject.title + "", Toast.LENGTH_SHORT).show()
            }

        })
    }

    private fun addData() {
            repeat(100) {
                val color = colorGenerator()
                arrayList.add(NoteDataClass("شكرا" + counter, "شكرا رقم " + counter, color))
                counter++
            }
    }


    override fun onBackPressed() {


        val dialogName: AlertDialog.Builder = AlertDialog.Builder(this)
        dialogName.setTitle("Exit")

        val EditTxtName = EditText(this)
        EditTxtName.inputType = InputType.TYPE_CLASS_PHONE
        dialogName.setView(EditTxtName)

        dialogName.setPositiveButton("ok",
            DialogInterface.OnClickListener { dialogInterface, i ->
                string = EditTxtName.text.toString()
                super.onBackPressed()
                dialogInterface.cancel()
            })

        dialogName.setNegativeButton("cancel",
            DialogInterface.OnClickListener { dialogInterface, i -> dialogInterface.cancel() })
        dialogName.show()

    }
}
