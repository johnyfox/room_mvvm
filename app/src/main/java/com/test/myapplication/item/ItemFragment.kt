package com.test.myapplication.item

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.test.myapplication.R
import com.test.myapplication.base.BaseFragment
import com.test.myapplication.databinding.ItemFragmentBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ItemFragment : BaseFragment<ItemFragmentBinding>() {

    private val viewModel : ItemViewModel by viewModels()

    override fun setUpViews() {
        super.setUpViews()
        val pkDevice = arguments?.getString("pkDevice") ?: ""
        val title = arguments?.getString("title") ?: ""
        val isEditMode = arguments?.getBoolean("editMode") ?: false

        viewModel.findItemByPkId(pkDevice.toLong())

        if (isEditMode) {
            binding?.editMode?.visibility = View.VISIBLE
            binding?.editTitle?.requestFocus()
            showKeyboard()
            binding?.title?.visibility = View.GONE
        } else {
            binding?.editMode?.visibility = View.GONE
            binding?.title?.text = title
        }
    }

    override fun observeData() {
        super.observeData()
        viewModel.state.observe(viewLifecycleOwner) { state ->
            //Go back to list
            if (state.wasItemSaved) {
                findNavController().popBackStack()
            }
            state?.item?.let {
                binding?.description?.text = it.toString()
            }
        }
    }

    private fun showKeyboard() {
        val imm = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
        imm?.showSoftInput(binding?.editTitle, InputMethodManager.SHOW_IMPLICIT)
    }

    private fun hideKeyboard() {
        val imm = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
        imm?.hideSoftInputFromWindow(binding?.editTitle?.windowToken, 0)
    }

    override fun onPause() {
        super.onPause()
        hideKeyboard()
    }

    override fun observeView() {
        super.observeView()
        binding?.saveBtn?.setOnClickListener {
            viewModel.updateItemName(binding?.editTitle?.text.toString())
        }
    }

    override fun getLayoutResId(): Int {
        return R.layout.item_fragment
    }
}