package com.test.myapplication.list

import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.domain.entity.Entity
import com.test.myapplication.Const
import com.test.myapplication.R
import com.test.myapplication.base.BaseFragment
import com.test.myapplication.databinding.ListFragmentBinding
import com.test.myapplication.utils.Utils
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListViewFragment : BaseFragment<ListFragmentBinding>(), EntityClickListener, DeleteItemListener {

    private val viewModel : ListViewModel by viewModels()

    private val listAdapter = ListAdapter()

    override fun setUpViews() {
        super.setUpViews()
        binding?.listView?.layoutManager = LinearLayoutManager(requireContext())
        listAdapter.setItemClickListener(this)
        binding?.listView?.adapter = listAdapter

        binding?.resetBtn?.setOnClickListener {
            viewModel.resetData()
        }

        val density = resources.displayMetrics.density
        val iconUrl = Const.URL + Utils.getScreenDensity(density)
        listAdapter.setIconUrl(iconUrl)
    }

    override fun observeData() {
        super.observeData()
        viewModel.state.observe(viewLifecycleOwner) { list ->
            listAdapter.updateItems(list)
        }
    }

    override fun getLayoutResId(): Int {
        return R.layout.list_fragment
    }

    override fun onItemClicked(entity: Entity) {
        val bundle = bundleOf("pkDevice" to entity.pk_device.toString(),
            "title" to entity.title,
            "editMode" to false
            )
        findNavController().navigate(R.id.list_to_fragment, bundle)
    }

    override fun onEditClicked(entity: Entity) {
        val bundle = bundleOf("pkDevice" to entity.pk_device.toString(),
            "title" to entity.title,
            "editMode" to true
        )
        findNavController().navigate(R.id.list_to_fragment, bundle)
    }

    override fun onLongClick(entity: Entity) {
        DeleteDialog(entity, this).show(childFragmentManager, "Dialog")
    }

    override fun onResume() {
        super.onResume()
        viewModel.fetchDbData()
    }

    override fun deleteItem(entity: Entity) {
        viewModel.deleteItem(entity)
    }
}