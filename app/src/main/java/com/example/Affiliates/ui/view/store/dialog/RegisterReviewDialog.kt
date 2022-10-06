package com.example.Affiliates.ui.view.store.dialog

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import com.example.Affiliates.data.CreateReview
import com.example.Affiliates.databinding.DialogRegisterReviewBinding
import com.example.Affiliates.ui.view.login.server.AuthService
import com.example.Affiliates.ui.view.store.Review

class RegisterReviewDialog (
    context: Context, private val okCallback: (String) -> Unit,
) : Dialog(context), CreateReviewView { // 뷰를 띄워야하므로 Dialog 클래스는 context를 인자로 받는다.

    private lateinit var binding: DialogRegisterReviewBinding

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
//        binding.dialogRegisterReviewBtn.setOnClickListener {
//            val reviewService = CreateReviewService()
//            reviewService.setReviewView(this)
//            reviewService.createReview(getReview())
//        }
    }

    private fun getReview(): CreateReview {
        with(binding) {
            val rate = dialogReviewRatingRb.numStars
            val review = dialogReviewInputEt.text.toString()
            return CreateReview(review, rate, 0)
        }
    }

    private fun initViews() = with(binding) {
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        // Button 클릭에 대한 Callback 처리
        dialogRegisterReviewBtn.setOnClickListener {
            okCallback(dialogReviewInputEt.text.toString())
            dismiss()
        }
    }

    override fun onReviewSuccess(code: Int, result: String) {
        TODO("Not yet implemented")
    }

    override fun onReviewFailure(code: Int, message: String) {
        TODO("Not yet implemented")
    }
}