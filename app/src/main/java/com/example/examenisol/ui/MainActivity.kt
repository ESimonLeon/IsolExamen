package com.example.examenisol.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.examenisol.databinding.ActivityMainBinding
import com.example.examenisol.retrofit.model.ProductResults
import com.example.examenisol.ui.adapter.ProductsListAdapter
import com.example.examenisol.ui.ext.loadAppBarListener
import com.example.examenisol.ui.ext.setAdapterListListener
import com.example.examenisol.ui.ext.validateServiceResult
import com.example.examenisol.utils.loadOnApplyWindowInsets
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    private val viewModel by viewModels<MainViewModel>()

    var productsListArray: ArrayList<ProductResults.ProductResultsRecords> = arrayListOf()

    val productsListAdapter: ProductsListAdapter by lazy {
        ProductsListAdapter(productsListArray)
    }

    var pQuerySearch: String? = null
    var pQuerySortPrice: Int? = null
    var pQueryPage: Int = 1
    var pTotalPages: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadOnApplyWindowInsets(binding.main)

        loadAppBarListener()

        setAdapterListListener()

        createObservers()

        sendSearchProducts()
    }

    fun sendSearchProducts() =
        viewModel.getSearchProducts(pQueryPage, pQuerySearch, pQuerySortPrice)

    private fun createObservers() {
        viewModel.serviceResult.observe(this) {
            validateServiceResult(it)
        }
    }

}