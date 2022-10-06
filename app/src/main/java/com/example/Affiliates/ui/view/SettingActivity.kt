package com.example.Affiliates.ui.view

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.Affiliates.databinding.ActivitySettingBinding
import com.example.Affiliates.ui.view.login.LoginActivity
import com.example.Affiliates.ui.view.myReview.MyreviewActivity
import com.example.Affiliates.util.ApplicationClass
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SettingActivity: AppCompatActivity() {
    private val binding: ActivitySettingBinding by lazy {
        ActivitySettingBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.settingReviewTv.setOnClickListener {
            startActivity(Intent(this, MyreviewActivity::class.java))
        }

        binding.settingBackIv.setOnClickListener {
            finish()
        }

        logout()
        withdrawal()
    }
    private fun logout() {
        binding.settingLogoutTv.setOnClickListener {
            var dialog = AlertDialog.Builder(this)
            dialog.setTitle("로그아웃 하시겠습니까?")
            dialog.setMessage("서비스를 이용해주셔서 감사합니다.")

            fun toast() {
                Toast.makeText(this, "로그아웃 되었습니다.", Toast.LENGTH_SHORT).show()
                ApplicationClass.mSharedPreferences.edit().clear()
                    .commit()// sharedpreferences에 저장되어있는 토큰 제거
                val intent = Intent(this, LoginActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                startActivity(intent)
            }

            var dialogLister = DialogInterface.OnClickListener { p0, p1 ->
                when (p1) {
                    DialogInterface.BUTTON_POSITIVE -> toast()
                }
            }
            dialog.setPositiveButton("확인", dialogLister)
            dialog.setNegativeButton("닫기", null)
            dialog.show()
        }
    }

    private fun withdrawal() {
        binding.settingWithdrawalTv.setOnClickListener {
            var dialog = AlertDialog.Builder(this)
            dialog.setTitle("회원탈퇴를 하시겠습니까?")
            dialog.setMessage("탈퇴 시 본인 계정의 모든 기록이 삭제됩니다.")

//            fun datadelete(){
//                val deleteRetrofit = ApplicationClass.retrofit.create(WithdrawalApi::class.java)
//                deleteRetrofit.deleteuser().enqueue(object : Callback<Withdrawal> {
//                    override fun onResponse(call: Call<Withdrawal>, response: Response<Withdrawal>) {
//                        if (response.isSuccessful) {
//                            Log.d("성공", response.body().toString())
//                        } else {
//                            Log.d("실패", response.body().toString())
//                        }
//                    }
//                    override fun onFailure(call: Call<Withdrawal>, t: Throwable) {
//                    }
//                })
//            }
//
//            fun toast() {
//                Toast.makeText(this, "회원탈퇴 되었습니다.", Toast.LENGTH_SHORT).show()
//                val intent = Intent(this, LoginActivity::class.java)
//                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
//                startActivity(intent)
//            }
//
            var dialogLister = DialogInterface.OnClickListener { p0, p1 ->
                if (DialogInterface.BUTTON_POSITIVE == p1) {
//                    datadelete()
//                    toast()
                }
            }
            dialog.setPositiveButton("확인", dialogLister)
            dialog.setNegativeButton("닫기", null)
            dialog.show()
        }
    }
}