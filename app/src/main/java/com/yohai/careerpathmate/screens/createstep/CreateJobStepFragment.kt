package com.yohai.careerpathmate.screens.createstep

import android.app.DatePickerDialog
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.yohai.careerpathmate.MainActivity
import com.yohai.careerpathmate.R
import com.yohai.careerpathmate.baseclasses.BaseFragment
import com.yohai.careerpathmate.customviews.SpinnerGenericAdapter
import com.yohai.careerpathmate.databinding.FragmentCreateJobStepBinding
import com.yohai.careerpathmate.uimodels.StepLocationUi
import com.yohai.careerpathmate.uimodels.StepStatusUi
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
        ViewCompat.setOnApplyWindowInsetsListener(root) { _, windowInsets ->
            val insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars())

            fabWrapper.updatePadding(bottom = insets.bottom)
            WindowInsetsCompat.CONSUMED
        }

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
                        val dat = getString(R.string.date_format, dayOfMonth, monthOfYear + 1, year)
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
        val title = if (args.stepId == null) {
            R.string.create_step_title
        } else {
            R.string.edit_step_title
        }
        (activity as? MainActivity)?.setToolbarTitle(title)
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

                    getString(R.string.date_format, day, month + 1, year)
                }
            }
        }
    }
}