package com.example.careerpathmate.screens.createstep

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.careerpathmate.baseclasses.BaseFragment
import com.example.careerpathmate.customviews.SpinnerGenericAdapter
import com.example.careerpathmate.databinding.FragmentCreateJobStepBinding
import com.example.careerpathmate.uimodels.StepLocationUi
import com.example.careerpathmate.uimodels.StepStatusUi
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Calendar

@AndroidEntryPoint
class CreateJobStepFragment : BaseFragment<FragmentCreateJobStepBinding>(
    FragmentCreateJobStepBinding::inflate
) {

    private val args: CreateJobStepFragmentArgs by navArgs()
    private val viewModel: CreateJobStepViewModel by viewModels()
    override fun FragmentCreateJobStepBinding.initUI() {

        dateTextView.setOnClickListener {
            val c = viewModel.newStep.date

            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = context?.let { context ->
                DatePickerDialog(
                    context,
                    { _, year, monthOfYear, dayOfMonth ->
                        val cal = Calendar.getInstance().apply {
                            set(year, monthOfYear, dayOfMonth)
                        }
                        viewModel.setStepDate(cal)
                        val dat = (dayOfMonth.toString() + "-" + (monthOfYear + 1) + "-" + year)
                        dateTextView.text = dat
                    },

                    year,
                    month,
                    day
                )
            }
            datePickerDialog?.show()
        }
        context?.let { context ->
            stepStatusSpinner.setAdapter(
                SpinnerGenericAdapter(
                    context,
                    StepStatusUi.values()
                ) {
                    context.getString(it.title)
                }
            )

            stepLocationSpinner.setAdapter(
                SpinnerGenericAdapter(
                    context,
                    StepLocationUi.values()
                ) {
                    context.getString(it.title)
                }
            )
        }
        createStepFab.setOnClickListener {
            viewModel.newStep = viewModel.newStep.copy(
                name = stepDescriptionInput.input.text.toString(),
                status = stepStatusSpinner.selectedItem as StepStatusUi,
                location = stepLocationSpinner.selectedItem as StepLocationUi
            )
            viewModel.saveStep()
            it.findNavController().navigateUp()
        }
    }

    override fun initObservers() {
    }

    override fun initArguments() {
        viewModel.setJobId(args.jobId)
        viewModel.setStepId(args.stepId) {
            updateUi()
        }
    }

    private fun updateUi() {
        viewLifecycleOwner.lifecycleScope.launch(Dispatchers.Main) {
            binding?.apply {
                stepDescriptionInput.input.setText(viewModel.newStep.name)
                stepLocationSpinner.setSelection(viewModel.newStep.location)
                stepStatusSpinner.setSelection(viewModel.newStep.status)
                dateTextView.text = viewModel.newStep.date.let {
                    val year = it.get(Calendar.YEAR)
                    val month = it.get(Calendar.MONTH)
                    val day = it.get(Calendar.DAY_OF_MONTH)

                    "$day-${month + 1}-$year"
                }
            }
        }
    }
}