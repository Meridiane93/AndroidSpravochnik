package com.example.spravochnic

import android.content.res.TypedArray
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.main_content.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    var adapter: MyAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        nav_view.setNavigationItemSelectedListener(this)

        val list = ArrayList<ListItem>()

        list.addAll(fillArras(resources.getStringArray(R.array.fish),
            resources.getStringArray(R.array.fish_content),
            getImageId(R.array.fish_image_array)))

        rcView.hasFixedSize()
        rcView.layoutManager = LinearLayoutManager(this)
        adapter = MyAdapter(list,this)
        rcView.adapter = adapter
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.id_fish -> {
                adapter?.updateAdapter(fillArras(resources.getStringArray(R.array.fish),
                    resources.getStringArray(R.array.fish_content),
                    getImageId(R.array.fish_image_array)))
                Toast.makeText(this, "Выбраны Рыбы", Toast.LENGTH_SHORT).show()
            }
            R.id.id_ptici ->{
                adapter?.updateAdapter(fillArras(resources.getStringArray(R.array.ptici),
                    resources.getStringArray(R.array.ptici_content),
                    getImageId(R.array.ptici_image_array)))
                Toast.makeText(this, "Выбраны Птицы", Toast.LENGTH_SHORT).show()
            }
            R.id.id_svini -> {
                adapter?.updateAdapter(fillArras(resources.getStringArray(R.array.svini),
                    resources.getStringArray(R.array.svini_content),
                    getImageId(R.array.svini_image_array)))
                Toast.makeText(this, "Выбраны Свиньи", Toast.LENGTH_SHORT).show()
            }
            R.id.id_ej -> {
                adapter?.updateAdapter(fillArras(resources.getStringArray(R.array.ej),
                    resources.getStringArray(R.array.ej_content),
                    getImageId(R.array.ej_image_array)))
                Toast.makeText(this, "Выбраны Собаки", Toast.LENGTH_SHORT).show()
            }
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    fun fillArras(titleArray:Array<String>,contentArray:Array<String>,imageArray:IntArray):List<ListItem>{  // заполняет массив
        val listItemArray = ArrayList<ListItem>()
        for (n in titleArray.indices){
            val listitem = ListItem(imageArray[n],titleArray[n],contentArray[n])
            listItemArray.add(listitem) }
        return listItemArray
    }

    fun getImageId(imageArrayId:Int):IntArray{ // расшифровка id картинок
        val tArray: TypedArray = resources.obtainTypedArray(imageArrayId)
        val count = tArray.length()
        val ids = IntArray(count)
        for (i in ids.indices) ids[i] = tArray.getResourceId(i,0)
        tArray.recycle()
        return ids
    }
}
