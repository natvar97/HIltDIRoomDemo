package com.indialone.hiltdiroomdemo.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.indialone.hiltdiroomdemo.databinding.ActivityAddNoteBinding
import com.indialone.hiltdiroomdemo.room.NoteEntitiy
import com.indialone.hiltdiroomdemo.viewmodels.NoteViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import javax.inject.Inject

@AndroidEntryPoint
class AddNoteActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityAddNoteBinding

    @Inject
    lateinit var noteViewModel: NoteViewModel

    private var title = ""
    private var description = ""
    private var date = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActivityAddNoteBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        mBinding.btnAdd.setOnClickListener {
            if (validateDetials()) {

                val simpleDateFormat = SimpleDateFormat("dd MMM, yyyy hh:mm")
                date = simpleDateFormat.format(System.currentTimeMillis())

                noteViewModel.insertNote(NoteEntitiy(title = title, description = description, date = date))
                finish()
            }
        }


    }

    private fun validateDetials(): Boolean {
        title = mBinding.etTitle.text.toString().trim { it <= ' ' }
        description = mBinding.etDescription.text.toString().trim { it <= ' ' }
        return when {
            TextUtils.isEmpty(title) -> {
                Toast.makeText(
                    this@AddNoteActivity,
                    "Please provide valid title",
                    Toast.LENGTH_SHORT
                ).show()
                false
            }
            TextUtils.isEmpty(description) -> {
                Toast.makeText(
                    this@AddNoteActivity,
                    "Please provide valid description",
                    Toast.LENGTH_SHORT
                ).show()
                false
            }
            else -> true
        }
    }

}