package com.example.menus

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListPopupWindow
import androidx.databinding.DataBindingUtil
import com.example.menus.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)


        // Создаем Окно со списком (Стандартный код)
        val listPopupWindow = ListPopupWindow(this, null, R.attr.listPopupWindowStyle)

        // Привязываем это Окно к кнопке
        listPopupWindow.anchorView = binding?.popup

        // Создаем Массив - Список
        val items = listOf(getString(R.string.shop), getString(R.string.favorites), getString(R.string.account))
        // Подгружаем шаблон для каждого пункта
        val adapter = ArrayAdapter(this, R.layout.list_item, items)
        listPopupWindow.setAdapter(adapter)

        // Запускаем функционал при тапе по пунктам меню
        listPopupWindow.setOnItemClickListener { parent: AdapterView<*>?, view: View?, position: Int, id: Long ->

            // У каждого пункта списка есть свой индекс, к нему и обращаемся
            binding?.selectedItem?.text = when (position) {
                0 -> getString(R.string.shop)
                1 -> getString(R.string.favorites)
                2 -> getString(R.string.account)

                else -> getString(R.string.notSelected)
            }

            // Закрыть Окно
            listPopupWindow.dismiss()
        }

        // При тапе по кнопке запускаем попап Окно (Без интерфейса)
        binding?.popup?.setOnClickListener(View.OnClickListener {
            listPopupWindow.show()

        })

    }

}