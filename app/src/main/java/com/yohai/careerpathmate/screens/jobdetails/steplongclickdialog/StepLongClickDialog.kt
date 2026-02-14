package com.yohai.careerpathmate.screens.jobdetails.steplongclickdialog

import android.os.Bundle
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.yohai.careerpathmate.baseclasses.BaseBottomSheetDialogFragment
import com.yohai.careerpathmate.databinding.DialogStepLongClickBinding
import com.yohai.careerpathmate.uimodels.StepStatusUi
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StepLongClickDialog : BaseBottomSheetDialogFragment<DialogStepLongClickBinding>(
    DialogStepLongClickBinding::inflate
) {
    private val viewModel: StepLongClickViewModel by viewModels()
    private val arg: StepLongClickDialogArgs by navArgs()

    override fun DialogStepLongClickBinding.initUI() {
        when (arg.stepStatus) {
            StepStatusUi.Current -> markAsCurrentAction.isVisible = false
            StepStatusUi.Done -> markAsDoneAction.isVisible = false
            StepStatusUi.Future -> markAsFutureAction.isVisible = false
        }

        markAsCurrentAction.setOnClickListener {
            viewModel.updateStatus(StepStatusUi.Current)
            dismiss()
        }

        markAsDoneAction.setOnClickListener {
            viewModel.updateStatus(StepStatusUi.Done)
            dismiss()
        }

        markAsFutureAction.setOnClickListener {
            viewModel.updateStatus(StepStatusUi.Future)
            dismiss()
        }

        deleteAction.setOnClickListener {
            viewModel.deleteStep()
            dismiss()
        }
    }

    override fun Bundle.initArguments() {
        viewModel.stepId = arg.stepId
    }
}