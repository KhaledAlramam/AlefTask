package com.khaled.aleftask.view

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.khaled.aleftask.R
import com.khaled.aleftask.data.model.Product
import com.khaled.aleftask.databinding.ActivityMainBinding
import com.khaled.aleftask.util.OnSortChanged
import com.khaled.aleftask.util.Resource
import dagger.hilt.android.AndroidEntryPoint
import android.graphics.drawable.ColorDrawable
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.khaled.aleftask.customview.CustomSortDialog


@AndroidEntryPoint
class MainActivity : AppCompatActivity(), OnSortChanged {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: ProductViewModel by viewModels()
    private val productAdapter = ProductAdapter()
    var inMemoryProducts: List<Product>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        initUi()
        getProducts()
    }

    private fun initUi() {
        binding.apply {
            productRv.adapter = productAdapter
            productRv.layoutManager =
                LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
            productAdapter.registerAdapterDataObserver(object : RecyclerView.AdapterDataObserver() {
                override fun onChanged() {
                    productRv.scrollToPosition(0)
                }

                override fun onItemRangeRemoved(positionStart: Int, itemCount: Int) {
                    productRv.scrollToPosition(0)
                }

                override fun onItemRangeMoved(fromPosition: Int, toPosition: Int, itemCount: Int) {
                    productRv.scrollToPosition(0)
                }

                override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
                    productRv.scrollToPosition(0)
                }

                override fun onItemRangeChanged(positionStart: Int, itemCount: Int) {
                    productRv.scrollToPosition(0)
                }

                override fun onItemRangeChanged(positionStart: Int, itemCount: Int, payload: Any?) {
                    productRv.scrollToPosition(0)
                }
            })
            sortButton.setOnClickListener {
                val customDialog = CustomSortDialog(this@MainActivity, this@MainActivity)
                customDialog.show()
            }
            searchEt.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun afterTextChanged(p0: Editable?) {
                    filterAdapter(p0.toString())
                }
            })
        }
    }

    private fun filterAdapter(query: String) {
        if (query.isBlank()) {
            productAdapter.submitList(inMemoryProducts)
            return
        }
        productAdapter.submitList(inMemoryProducts?.filter { it.title.contains(query, true) })
    }

    private fun getProducts() {
        viewModel.getFirstPageProducts().observe(this) {
            when (it) {
                is Resource.Error -> {
                    hideLoading()
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                }
                Resource.Loading -> {
                    showLoading()
                }
                is Resource.Success -> {
                    inMemoryProducts = it.data
                    productAdapter.submitList(inMemoryProducts)
                    hideLoading()

                }
            }
        }
    }

    private fun hideLoading() {
        binding.progressBar.isVisible = false
    }

    private fun showLoading() {
        binding.progressBar.isVisible = true
    }

    override fun onClick(byName: Boolean) {
        if (byName) {
            productAdapter.submitList(productAdapter.currentList.sortedBy { it.title })
        } else {
            productAdapter.submitList(productAdapter.currentList.sortedBy { it.prices.firstOrNull()?.price })
        }
    }
}