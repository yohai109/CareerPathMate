package com.example.careerpathmate.baseclasses

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<T : ViewBinding>(
    private val bindingInflater: (layoutInflater: LayoutInflater, parent: ViewGroup?, attachToParent: Boolean) -> T
) : Fragment() {

    private var _binding: T? = null

    protected val binding: T?
        get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = bindingInflater(layoutInflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.initArguments()
        binding?.initUI()
        initObservers()
    }

    abstract fun T.initUI()

    abstract fun initObservers()

    open fun Bundle.initArguments() {}

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}