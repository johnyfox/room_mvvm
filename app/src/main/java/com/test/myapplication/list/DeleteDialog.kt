package com.test.myapplication.list

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.test.domain.entity.Entity
import com.test.myapplication.R

class DeleteDialog(private val entity: Entity,
                   private val deleteItemListener : DeleteItemListener) : DialogFragment() {

     override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setMessage(getString(R.string.delete_item, entity.title))
                .setPositiveButton(R.string.delete) { dialog, id ->
                    deleteItemListener.deleteItem(entity)
                }
                .setNegativeButton(R.string.no) { dialog, id ->
                    dismiss()
                }
            // Create the AlertDialog object and return it
            builder.create()
            // Create the AlertDialog object and return it
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}