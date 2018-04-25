package com.demo.recyclerviewkotlin.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.demo.recyclerviewkotlin.R
import com.demo.recyclerviewkotlin.adpter.UserDetailAdapter
import com.demo.recyclerviewkotlin.common.INTENT_IS_ADD
import com.demo.recyclerviewkotlin.common.INTENT_USER
import com.demo.recyclerviewkotlin.communicator.UserItemClickListener
import com.demo.recyclerviewkotlin.model.UserDetail
import kotlinx.android.synthetic.main.activity_main.*

class UserDetailActivity : BaseActivity(), View.OnClickListener,UserItemClickListener {

    private val RC_ADD = 100
    private val RC_EDIT = 101

    private var mPosition = -1

    private val mUserDetailList = ArrayList<UserDetail>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        btnAddUser.setOnClickListener(this)

        getUserDetailList()

        setAdapterUserDetail()
    }

    private fun setAdapterUserDetail() {
        rvUserDetail.layoutManager = LinearLayoutManager(this)
        rvUserDetail.adapter = UserDetailAdapter(this, mUserDetailList,this)
    }

    override fun editUser(position: Int) {
        mPosition = position
        val iEditUser = Intent(this,AddUserDetail::class.java)
        iEditUser.putExtra(INTENT_IS_ADD,false)
        iEditUser.putExtra(INTENT_USER, mUserDetailList[position])
        startActivityForResult(iEditUser,RC_EDIT)
    }

    private fun getUserDetailList() {

        mUserDetailList.add(UserDetail(1, "Name 1", "Address 1"))
        mUserDetailList.add(UserDetail(2, "Name 2", "Address 2"))
        mUserDetailList.add(UserDetail(3, "Name 3", "Address 3"))
        mUserDetailList.add(UserDetail(4, "Name 4", "Address 4"))

    }

    override fun onClick(view: View) {
        if (view == btnAddUser) {
            val iAddUser = Intent(this, AddUserDetail::class.java)
            iAddUser.putExtra(INTENT_IS_ADD, true)
            startActivityForResult(iAddUser, RC_ADD)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_ADD) {
            if (resultCode == Activity.RESULT_OK) {
                mUserDetailList.add(data!!.getParcelableExtra(INTENT_USER))
                rvUserDetail.adapter.notifyDataSetChanged()
            }
        }else if(requestCode ==RC_EDIT){
            if(resultCode == Activity.RESULT_OK){
                mUserDetailList[mPosition] = data!!.getParcelableExtra(INTENT_USER)
                rvUserDetail.adapter.notifyDataSetChanged()
            }
        }
    }
}
