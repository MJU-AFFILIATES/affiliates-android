package com.example.Affiliates.ui.view.store.dialog

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.widget.Toast
import com.example.Affiliates.data.CreateReview
import com.example.Affiliates.databinding.DialogRegisterReviewBinding
import com.google.android.material.snackbar.Snackbar

class RegisterReviewDialog(
    storeId: Int, storeName: String, context: Context, private val okCallback: (Boolean) -> Unit,): Dialog(context), CreateReviewView {

    private lateinit var binding: DialogRegisterReviewBinding
    private var storeId = storeId
    private var storeName = storeName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 만들어놓은 dialog_profile.xml 뷰를 띄운다.
        binding = DialogRegisterReviewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        context.dialogResize(this@RegisterReviewDialog, 0.9f, 0.6f)
//        window?.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT)
        initViews()
        createReview()
    }

    private fun createReview() {
        binding.dialogRegisterReviewBtn.setOnClickListener {
            val reviewService = CreateReviewService()
            reviewService.setReviewView(this@RegisterReviewDialog)
            reviewService.createReview(getReview())
        }
    }

    private fun getReview(): CreateReview {
        with(binding) {
            val rate = dialogReviewRatingRb.rating.toInt()
            val review = dialogReviewInputEt.text.toString()
            return CreateReview(review, rate, storeId)
        }
    }

    private fun initViews() = with(binding) {
        dialogReviewTitleTv.text = "$storeName 어떠셨나요?"
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    override fun onReviewSuccess(code: Int, result: String) {
        Toast.makeText(context, "리뷰 작성을 완료했습니다.", Toast.LENGTH_SHORT).show()
        okCallback(true)
        dismiss()
    }

    override fun onReviewFailure(code: Int, message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_SHORT).show()
    }
}