package com.example.Affiliates.ui.view.store.dialog

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import com.example.Affiliates.databinding.DialogRegisterReviewBinding

class RegisterReviewDialog (
    context: Context, private val okCallback: (String) -> Unit,
) : Dialog(context) { // 뷰를 띄워야하므로 Dialog 클래스는 context를 인자로 받는다.

    private lateinit var binding: DialogRegisterReviewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 만들어놓은 dialog_profile.xml 뷰를 띄운다.
        binding = DialogRegisterReviewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        context.dialogResize(this@RegisterReviewDialog, 0.9f, 0.6f)
//        window?.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT)
        initViews()

        getReviews()
    }

    private fun getReviews() {
        with(binding) {
            val rate = dialogReviewRatingRb.numStars
            val review = dialogReviewInputEt.text.toString()
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
}