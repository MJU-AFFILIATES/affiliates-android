package com.example.Affiliates.ui.view.login

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.Affiliates.data.User
import com.example.Affiliates.databinding.ActivitySignupBinding
import com.example.Affiliates.ui.view.main.MainActivity
import com.getit.getit.ui.login.saveJwt
import com.getit.getit.ui.login.server.AuthService
import com.getit.getit.ui.login.server.Result

class SignUpActivity: AppCompatActivity(), SignUpView {
    private val binding: ActivitySignupBinding by lazy {
        ActivitySignupBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.signupSignupBtn.setOnClickListener {
            signUp()
            finish()
        }
    }

//    private fun getUser(): User {
//        val studentId = binding.signupStudentIdEt.text.toString()
//        val password = binding.signupPasswordEt.text.toString()
//        val nickname = binding.signupNicknameEt.text.toString()
//
//        return User(studentId, password, nickname)
//    }

    private fun initView() {
        with(binding) {
            signupStudentIdEt.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

                override fun afterTextChanged(s: Editable?) {
                    if (s.toString().isEmpty()) {
                        signupStudentIdLayout.error = "공백은 허용하지 않습니다."
                    } else {
                        s.toString().toIntOrNull()?.let {
                            if (s != null) {
                                if (s.length > 4) {
                                    signupStudentIdLayout.error = "4글자가 초과되었습니다."
                                } else {
                                    signupStudentIdLayout.error = null
                                }
                            }
                        } ?: run {
                            signupStudentIdLayout.error = "숫자를 입력하세요."
                        }
                    }
                }
            })
        }
    }


    private fun signUp() {
//        val passwordPattern : Pattern = Pattern.compile("""^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[!@#$%^+\-=])(?=\S+$).*$""")
//        val passwordString : String = binding.signUpPasswordEt.text.toString().trim()
//
//        if (!(passwordPattern.matcher(passwordString).matches())||
//            passwordString.length !in 8..16
//        ){
//            binding.signUpPasswordErrorTv.visibility = View.VISIBLE
//            return
//        }
//
//        if (binding.signUpPasswordEt.text.toString() != binding.signUpPasswordCheckEt.text.toString()) {
//            binding.signUpPasswordCheckErrorTv.visibility = View.VISIBLE
//            return
//        }

        val authService = AuthService()
        authService.setSignUpView(this)

//        authService.signUp(getUser())
    }

    override fun onSignUpSuccess(code: Int, result: Result) {
        saveJwt(result.accessToken, result.refreshToken)
        Toast.makeText(this, "회원가입 성공!", Toast.LENGTH_SHORT).show()
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    override fun onSignUpFailure(code: Int) {
//        when(code){
//            2001 -> {
//                binding.signUpEmailErrorTv.visibility = View.VISIBLE
//            }
//            2000 -> {
//                binding.signUpNicknameErrorTv.visibility = View.VISIBLE
//            }
//            else ->{
//                showToast("일시적인 오류입니다. 나중에 다시 시도해 주세요.")
//            }
//        }
    }

}