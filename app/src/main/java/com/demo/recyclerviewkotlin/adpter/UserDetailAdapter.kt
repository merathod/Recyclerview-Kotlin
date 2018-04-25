package com.demo.recyclerviewkotlin.adpter


import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.demo.recyclerviewkotlin.R
import com.demo.recyclerviewkotlin.communicator.UserItemClickListener
import com.demo.recyclerviewkotlin.model.UserDetail
import kotlinx.android.synthetic.main.item_user_deatil.view.*


class UserDetailAdapter(private val mContext : Context, private val mUserDetailList: ArrayList<UserDetail>,private val mUserItemClickListener: UserItemClickListener) : RecyclerView.Adapter<UserDetailAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent?.context).inflate(R.layout.item_user_deatil,parent,false))
    }

    override fun getItemCount(): Int {
       return mUserDetailList.size
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.tvId?.text = mUserDetailList[position].id.toString()
        holder?.tvName?.text = mUserDetailList[position].name
        holder?.tvAddress?.text = mUserDetailList[position].address
        holder?.llRoot?.setOnClickListener({
                mUserItemClickListener.editUser(position)
        })
    }


    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView){
        val tvId = itemView?.tvId
        val tvName = itemView?.tvName
        val tvAddress = itemView?.tvAddress
        val llRoot = itemView?.llRoot
    }


}