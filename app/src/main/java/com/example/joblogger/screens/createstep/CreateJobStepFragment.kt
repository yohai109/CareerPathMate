package com.example.joblogger.screens.createstep

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.joblogger.baseclasses.BaseFragment
import com.example.joblogger.databinding.FragmentCreateJobStepBinding
import dagger.hilt.android.AndroidEntryPoint
import java.util.Calendar

@AndroidEntryPoint
class CreateJobStepFragment : BaseFragment<FragmentCreateJobStepBinding>(
    FragmentCreateJobStepBinding::inflate
) {

    private val args: CreateJobStepFragmentArgs by navArgs()
    private val viewModel: CreateJobStepViewModel by viewModels()
    override fun FragmentCreateJobStepBinding.initUI() {

        dateTextView.setOnClickListener {
            val c = Calendar.getInstance()

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

        createStepFab.setOnClickListener {
            viewModel.newStep = viewModel.newStep.copy(
                name = stepDescriptionInput.input.text.toString()
            )
            viewModel.saveStep()
            it.findNavController().navigateUp()
        }
    }

    override fun initObservers() {
    }

    override fun Bundle.initArguments() {
        viewModel.setJobId(args.jobId)
    }
}