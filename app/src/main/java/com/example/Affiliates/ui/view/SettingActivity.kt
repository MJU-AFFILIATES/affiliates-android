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
import com.example.Affiliates.ui.view.main.MainInterface
import com.example.Affiliates.ui.view.myReview.MyreviewActivity
import com.example.Affiliates.ui.view.mypage.MypageInterface
import com.example.Affiliates.ui.view.mypage.ProfileModel
import com.example.Affiliates.ui.view.mypage.UpdateProfileActivity
import com.example.Affiliates.ui.view.store.StoreModel
import com.example.Affiliates.util.ApplicationClass
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SettingActivity: AppCompatActivity() {
    private val binding: ActivitySettingBinding by lazy {
        ActivitySettingBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.profileLayout.setOnClickListener {
            startActivity(Intent(this, UpdateProfileActivity::class.java))
        }

        binding.settingReviewTv.setOnClickListener {
            startActivity(Intent(this, MyreviewActivity::class.java))
        }

        binding.settingBackIv.setOnClickListener {
            finish()
        }

        getUserProfileFromAPI()
        logout()
        withdrawal()
    }

    private fun getUserProfileFromAPI() {
        ApplicationClass.retrofit.create(MypageInterface::class.java).also {
            it.getUserProfile().enqueue(object : Callback<ProfileModel> {
                    override fun onFailure(call: Call<ProfileModel>, t: Throwable) {
                        Log.d("getUserProfileFromAPI", "GET ERROR_2: " + t.message)
                    }
                    override fun onResponse(call: Call<ProfileModel>, response: Response<ProfileModel>) {
                        if (response.isSuccessful.not()) {
                            Log.d("getUserProfileFromAPI", "GET ERROR_1")
                            return
                        }
                        response.body()?.let { dto ->
                            Log.d("getUserProfileFromAPI", "GET SUCCESS")
                            binding.settigId.text = dto.result.studentNum.toString()
                            binding.settingName.text = dto.result.nickName
                        }
                    }
                })
        }
    }

    private fun logout() {
        binding.settingLogoutTv.setOnClickListener {
            var dialog = AlertDialog.Builder(this)
            dialog.setTitle("???????????? ???????????????????")
            dialog.setMessage("???????????? ?????????????????? ???????????????.")

            fun toast() {
                Toast.makeText(this, "???????????? ???????????????.", Toast.LENGTH_SHORT).show()
                ApplicationClass.mSharedPreferences.edit().clear()
                    .commit()// sharedpreferences??? ?????????????????? ?????? ??????
                val intent = Intent(this, LoginActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                startActivity(intent)
            }

            var dialogLister = DialogInterface.OnClickListener { p0, p1 ->
                when (p1) {
                    DialogInterface.BUTTON_POSITIVE -> toast()
                }
            }
            dialog.setPositiveButton("??????", dialogLister)
            dialog.setNegativeButton("??????", null)
            dialog.show()
        }
    }

    private fun withdrawal() {
        binding.settingWithdrawalTv.setOnClickListener {
            var dialog = AlertDialog.Builder(this)
            dialog.setTitle("??????????????? ???????????????????")
            dialog.setMessage("?????? ??? ?????? ????????? ?????? ????????? ???????????????.")

//            fun datadelete(){
//                val deleteRetrofit = ApplicationClass.retrofit.create(WithdrawalApi::class.java)
//                deleteRetrofit.deleteuser().enqueue(object : Callback<Withdrawal> {
//                    override fun onResponse(call: Call<Withdrawal>, response: Response<Withdrawal>) {
//                        if (response.isSuccessful) {
//                            Log.d("??????", response.body().toString())
//                        } else {
//                            Log.d("??????", response.body().toString())
//                        }
//                    }
//                    override fun onFailure(call: Call<Withdrawal>, t: Throwable) {
//                    }
//                })
//            }
//
//            fun toast() {
//                Toast.makeText(this, "???????????? ???????????????.", Toast.LENGTH_SHORT).show()
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
            dialog.setPositiveButton("??????", dialogLister)
            dialog.setNegativeButton("??????", null)
            dialog.show()
        }
    }
}