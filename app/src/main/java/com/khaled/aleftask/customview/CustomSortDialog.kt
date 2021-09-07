package com.khaled.aleftask.customview

import android.app.Activity
import android.app.Dialog
import android.os.Bundle
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.RadioButton
import com.khaled.aleftask.R
import com.khaled.aleftask.util.OnSortChanged

class CustomSortDialog(activity: Activity, private val listener: OnSortChanged): Dialog(activity) {

    private lateinit var saveButton: Button
    private lateinit var byName: RadioButton
    private lateinit var byPrice: RadioButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_sort);
        saveButton = findViewById(R.id.saveSort);
        byName = findViewById(R.id.sortByName);
        byPrice = findViewById(R.id.sortByPrice);
        saveButton.setOnClickListener {
            listener.onClick(byName.isChecked)
            dismiss()
        }
        window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
    }
}