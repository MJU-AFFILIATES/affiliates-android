package com.example.Affiliates.ui.view.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.Affiliates.data.User
import com.example.Affiliates.databinding.ActivitySignupBinding
import com.example.Affiliates.ui.view.login.server.AuthService
import com.example.Affiliates.ui.view.main.MainActivity
import com.example.Affiliates.util.saveJwt
import com.example.Affiliates.ui.view.login.server.Result
import com.google.android.material.snackbar.Snackbar
import java.util.regex.Pattern

class SignUpActivity: AppCompatActivity(), SignUpView, CheckView {
    private var errorMsg: String? = null
    private var studentID = true
    private var nickname = true

    private val binding: ActivitySignupBinding by lazy {
        ActivitySignupBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.signupSignupBtn.setOnClickListener {
            RemoveFocus()
            signUp()
        }
        RemoveErrorMsg()
        checkStudentID()
//        checkNickname()
    }

    private fun checkStudentID() {
        binding.signupStudentIdDoubleCheckBtn.setOnClickListener {
            var id = binding.signupStudentIdEt.text.toString()
            if (id.isEmpty() || id.length != 8) {
                binding.signupStudentIdLayout.error = "학번 형식을 확인해 주세요."
                RemoveFocus()
            }
            else {
                val authService = AuthService()
                authService.setCheckView(this)
                authService.checkStudent(binding.signupStudentIdEt.text.toString())
                Log.d("TEST", binding.signupStudentIdEt.text.toString())
            }
        }
    }

    private fun RemoveFocus() {
        binding.signupStudentIdEt.clearFocus()
        binding.signupPasswordEt.clearFocus()
        binding.signupNicknameEt.clearFocus()
        binding.signupPasswordCheckEt.clearFocus()
    }

    private fun RemoveErrorMsg() {
        with(binding) {
            // 리팩토링하기
            signupStudentIdEt.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
                if (hasFocus) {
                    signupStudentIdLayout.isErrorEnabled = false
                    binding.signupStudentIdDoubleCheckBtn.visibility = View.VISIBLE
                    studentID = true
                }
            }
            signupNicknameEt.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
                if (hasFocus) {
                    signupNicknameLayout.isErrorEnabled = false
                }
            }
            signupPasswordEt.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
                if (hasFocus) {
                    signupPasswordLayout.isErrorEnabled = false
                }
            }
            signupPasswordCheckEt.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
                if (hasFocus) {
                    signupPasswordCheckLayout.isErrorEnabled = false
                }
            }
        }
    }

    private fun getUser(): User {
        val studentId = binding.signupStudentIdEt.text.toString()
        val password = binding.signupPasswordEt.text.toString()
        val nickname = binding.signupNicknameEt.text.toString()

        return User(studentId, password, nickname)
    }

    private fun validateID(): Boolean {
        with(binding) {
            val value: String = signupStudentIdLayout.editText?.text.toString()

            return if (value.isEmpty()) {
                signupStudentIdLayout.error = "학번을 입력해 주세요."
                false
            } else if (value.length != 8) {
                signupStudentIdLayout.error = "학번 형식을 확인해 주세요."
                false
            } else if (studentID) {
                signupStudentIdLayout.error = "학번 중복확인을 해주세요."
                false
            } else {
                signupStudentIdLayout.error = null
                signupStudentIdLayout.isErrorEnabled = false
                true
            }
        }
    }

    private fun validateNickname(): Boolean {
        with(binding) {
            val value: String = signupNicknameLayout.editText?.text.toString()

            return if (value.isEmpty()) {
                signupNicknameLayout.error = "닉네임을 입력해 주세요."
                false
            } else if (nickname) {
                signupNicknameLayout.error = "닉네임 중복확인을 해주세요."
                false
            } else {
                signupNicknameLayout.error = null
                signupNicknameLayout.isErrorEnabled = false
                true
            }
        }
    }

    private fun validatePassword(): Boolean {
        val passwordPattern : Pattern = Pattern.compile("""^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[!@#$%^+\-=])(?=\S+$).*$""")
        with(binding) {
            val value: String = signupPasswordLayout.editText?.text.toString()

            return if (value.isEmpty()) {
                signupPasswordLayout.error = "비밀번호를 입력해 주세요."
                false
            } else if (!(passwordPattern.matcher(signupPasswordEt.text.toString()).matches())||
                signupPasswordEt.text.toString().length !in 8..16) {
                signupPasswordLayout.error = "비밀번호는 영문, 숫자 특수문자 포함 8 ~ 16글자 이내입니다."
                false
            } else {
                signupPasswordLayout.error = null
                signupPasswordLayout.isErrorEnabled = false
                true
            }
        }
    }

    private fun validateCheckPassword(): Boolean {
        with(binding) {
            val value: String = signupPasswordCheckLayout.editText?.text.toString()

            return if (value.isEmpty()) {
                signupPasswordCheckLayout.error = "비밀번호를 한 번 더 확인해 주세요."
                false
            } else if (signupPasswordCheckEt.text.toString() != signupPasswordEt.text.toString()) {
                signupPasswordCheckLayout.error = "비밀번호가 일치하지 않습니다."
                false
            } else {
                signupPasswordCheckLayout.error = null
                signupPasswordCheckLayout.isErrorEnabled = false
                true
            }
        }
    }

    private fun signUp() {
        if(!validateID()) {
            return
        }
        if(!validateNickname()) {
            return
        }
        if(!validatePassword()) {
            return
        }
        if(!validateCheckPassword()) {
            return
        }
        // SignUp Action
        val authService = AuthService()
        authService.setSignUpView(this@SignUpActivity)
        authService.signUp(getUser())
        finish()
    }

    override fun onSignUpSuccess(code: Int, result: Result) {
        saveJwt(result.accessToken, result.refreshToken)
        Toast.makeText(this, "회원가입 성공!", Toast.LENGTH_SHORT).show()
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    override fun onSignUpFailure(code: Int, message: String) {
        errorMsg = message
        Snackbar.make(binding.root, message, Snackbar.LENGTH_SHORT).show()
    }

    override fun onCheckSuccess(code: Int, result: Boolean) {
        studentID = result
        if(studentID) {
            Snackbar.make(binding.root, "중복된 학번입니다.", Snackbar.LENGTH_SHORT).show()
        }
        else {
            binding.signupStudentIdDoubleCheckBtn.visibility = View.GONE
            Snackbar.make(binding.root, "사용 가능한 학번입니다.", Snackbar.LENGTH_SHORT).show()
        }
        RemoveFocus()
        binding.signupStudentIdLayout.isErrorEnabled = false
    }

    override fun onCheckFailure(Code: Int, message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_SHORT).show()
    }
}