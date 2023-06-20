package com.example.joblogger.screens.createjob

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.CheckedTextView
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.example.joblogger.baseclasses.BaseFragment
import com.example.joblogger.databinding.FragmentCreateJobBinding
import com.example.joblogger.uimodels.JobUiStatus
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreateJobFragment : BaseFragment<FragmentCreateJobBinding>(
    FragmentCreateJobBinding::inflate
) {
    private val viewModel: CreateJobViewModel by viewModels()
    override fun FragmentCreateJobBinding.initUI() {
        createJobFAB.setOnClickListener {
            viewModel.jobToCreate = viewModel.jobToCreate.copy(
                companyName = companyNameInputText.input.text.toString(),
                status = statusPickerSpinner.selectedItem as JobUiStatus
            )
            viewModel.save()
            it.findNavController().navigateUp()
        }

        context?.let { context ->
            val adapter = object : ArrayAdapter<JobUiStatus>(
                context,
                android.R.layout.simple_spinner_item,
                JobUiStatus.values()
            ) {
                override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
                    var text = convertView as? CheckedTextView
                    if (text == null) {
                        text = LayoutInflater
                            .from(getContext())
                            .inflate(
                                android.R.layout.simple_spinner_dropdown_item,
                                parent,
                                false
                            ) as CheckedTextView
                    }

                    getItem(position)?.let { text.setText(it.title) }
                    return text
                }

                override fun getDropDownView(
                    position: Int,
                    convertView: View?,
                    parent: ViewGroup
                ): View {
                    var textView: CheckedTextView? = convertView as? CheckedTextView
                    if (textView == null) {
                        textView = LayoutInflater
                            .from(getContext())
                            .inflate(
                                android.R.layout.simple_spinner_dropdown_item,
                                parent,
                                false
                            ) as CheckedTextView
                    }

                    getItem(position)?.let { textView.setText(it.title) }
                    return textView
                }
            }
            adapter.apply {
                statusPickerSpinner.adapter = this
            }
        }
    }

    override fun initObservers() {

    }
}