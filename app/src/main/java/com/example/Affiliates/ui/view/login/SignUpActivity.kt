package com.example.Affiliates.ui.view.login

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewTreeObserver.OnGlobalLayoutListener
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.Affiliates.R
import com.example.Affiliates.data.User
import com.example.Affiliates.databinding.ActivitySignupBinding
import com.example.Affiliates.ui.view.login.server.AuthService
import com.example.Affiliates.ui.view.login.server.Result
import com.example.Affiliates.ui.view.main.MainActivity
import com.example.Affiliates.util.saveJwt
import com.google.android.material.snackbar.Snackbar
import java.util.regex.Pattern


class SignUpActivity: AppCompatActivity(), SignUpView, CheckView {
    private var errorMsg: String? = null
    private var studentID = true
    private var nickName = true

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

        binding.backBtn.setOnClickListener {
            finish()
        }

        RemoveErrorMsg()
        // 추후 리팩토링 (파라미터로 넘겨주기)
        checkStudentID()
        checkNickname()
    }

    private fun changeBtnColor() {
        with(binding) {
            if(signupPasswordCheckEt.text.toString().isNotEmpty() && signupStudentIdEt.text.toString().isNotEmpty() &&
                signupNicknameEt.text.toString().isNotEmpty() && signupPasswordEt.text.toString().isNotEmpty()) {
                signupSignupBtn.setBackgroundColor(R.drawable.border_10dp_orange_active)
            }
            else{
                signupSignupBtn.setBackgroundColor(R.drawable.border_10dp_orange_trans_active)
            }
        }
    }

    private fun closeKeyboard() {
        var view = this.currentFocus
        if(view != null)
        {
            val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
            view.clearFocus() // 포커스 제거
        }
    }


    private fun checkStudentID() {
        binding.signupStudentIdDoubleCheckBtn.setOnClickListener {
            closeKeyboard()
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

    private fun checkNickname() {
        binding.signupNicknameDoubleCheckBtn.setOnClickListener {
            closeKeyboard()
            var id = binding.signupNicknameEt.text.toString()
            if (id.isEmpty()) {
                binding.signupNicknameLayout.error = "닉네임을 입력해 주세요."
                RemoveFocus()
            }
            else {
                val authService = AuthService()
                authService.setCheckView(this)
                authService.checkNickname(binding.signupNicknameEt.text.toString())
                Log.d("TEST", binding.signupNicknameEt.text.toString())
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
                    binding.signupNicknameDoubleCheckBtn.visibility = View.VISIBLE
                    nickName = true
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
            } else if (nickName) {
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

    override fun onCheckSuccess(code: Int, result: Boolean, item: String) {
        when(item){
            "studentNum" -> {
                studentID = result
                if(studentID) {
                    Snackbar.make(binding.root, "사용 중인 학번입니다.", Snackbar.LENGTH_SHORT).show()
                }
                else {
                    binding.signupStudentIdDoubleCheckBtn.visibility = View.GONE
                    Snackbar.make(binding.root, "사용 가능한 학번입니다.", Snackbar.LENGTH_SHORT).show()
                }
                binding.signupStudentIdLayout.isErrorEnabled = false
            }
            "nickName" -> {
                nickName = result
                if(nickName) {
                    Snackbar.make(binding.root, "사용 중인 닉네임입니다.", Snackbar.LENGTH_SHORT).show()
                }
                else {
                    binding.signupNicknameDoubleCheckBtn.visibility = View.GONE
                    Snackbar.make(binding.root, "사용 가능한 닉네임입니다.", Snackbar.LENGTH_SHORT).show()
                }
                binding.signupStudentIdLayout.isErrorEnabled = false
            }
        }
        RemoveFocus()
    }

    override fun onCheckFailure(Code: Int, message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_SHORT).show()
    }
}