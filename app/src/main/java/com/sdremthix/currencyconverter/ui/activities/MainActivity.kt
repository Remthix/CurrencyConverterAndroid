package com.sdremthix.currencyconverter.ui.activities

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.sdremthix.currencyconverter.R
import com.sdremthix.currencyconverter.application.AppStart
import com.sdremthix.currencyconverter.databinding.ActivityMainBinding
import com.sdremthix.currencyconverter.viewmodels.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

/**
 * The main/home view component.
 */
class MainActivity : BaseActivity() {


    @Inject
    lateinit var mMainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        (applicationContext as AppStart).applicationComponent.bind(this@MainActivity)

        binding.viewModel = mMainViewModel
        binding.lifecycleOwner = this@MainActivity

        initMainViewComponents()

    }

    override fun onDestroy() {
        mMainViewModel.onDestroy()
        super.onDestroy()
    }

    private fun initMainViewComponents() {
        mMainViewModel.onBind()
        val currencyAdapter: ArrayAdapter<String> =
            ArrayAdapter<String>(this@MainActivity, R.layout.currency_item)
        currencyAdapter.addAll(mMainViewModel.currencyList)
        spinner_currency.apply {
            adapter = currencyAdapter
            onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                }

            }
        }
    }
}