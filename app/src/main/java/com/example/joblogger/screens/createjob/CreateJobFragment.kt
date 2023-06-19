package com.example.joblogger.screens.createjob

import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import com.example.joblogger.baseclasses.BaseFragment
import com.example.joblogger.databinding.FragmentCreateJobBinding


class CreateJobFragment : BaseFragment<FragmentCreateJobBinding>(
    FragmentCreateJobBinding::inflate
) {
    override fun FragmentCreateJobBinding.initUI() {
        companyNameInputText.input.doOnTextChanged { text, _, _, _ ->
            Toast.makeText(
                context?.applicationContext,
                "text in edit text is: $text",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun initObservers() {

    }
}