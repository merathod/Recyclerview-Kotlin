package com.demo.recyclerviewkotlin.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import com.demo.recyclerviewkotlin.R
import com.demo.recyclerviewkotlin.common.INTENT_IS_ADD
import com.demo.recyclerviewkotlin.common.INTENT_USER
import com.demo.recyclerviewkotlin.model.UserDetail
import kotlinx.android.synthetic.main.activity_add_user_detail.*

class AddUserDetail : BaseActivity(), View.OnClickListener {

    private var isAdd: Boolean = true
    private var mUserDetail : UserDetail? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_user_detail)
        isAdd = intent.getBooleanExtra(INTENT_IS_ADD, true)
        mUserDetail = intent.getParcelableExtra(INTENT_USER)
        initControls()
    }

    private fun initControls() {

        btnSubmit.setOnClickListener(this)
        if(mUserDetail != null) {
            edId.setText(mUserDetail!!.id.toString())
            edName.setText(mUserDetail!!.name)
            edAddress.setText(mUserDetail!!.address)
        }
    }

    override fun onClick(view: View) {
        if (view == btnSubmit) {

            val message: String = checkFields()

            if (!TextUtils.isEmpty(message)) {
                Toast.makeText(this, message, Toast.LENGTH_LONG).show()
            }

            val userDetail = UserDetail(edId.text.toString().toInt(),edName.text.toString(),edAddress.text.toString())
            val iAdd = Intent()
            iAdd.putExtra(INTENT_USER,userDetail)
            setResult(Activity.RESULT_OK,iAdd)
            finish()
        }
    }

    private fun checkFields(): String {
        var message = ""
        when {
            TextUtils.isEmpty(edId.text.toString()) -> message = "Please enter the id."
            TextUtils.isEmpty(edName.text.toString()) -> message = "Please enter the name."
            TextUtils.isEmpty(edAddress.text.toString()) -> message = "Please enter the address."
        }
        return message
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}

