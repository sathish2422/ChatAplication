package com.example.chataplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.google.firebase.auth.FirebaseAuth

class MessageAdapter(val context: Context,val messageList: ArrayList<Message>):
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val ITEM_RECEIVE = 1
    val ITEM_SEND = 2

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        if (viewType == 1){
            //inflater receive

            val view:View= LayoutInflater.from(context).inflate(R.layout.receive,parent,false)
            return ReceiveViewHolder(view)

        }
        else{
            //inflater send
            val view:View= LayoutInflater.from(context).inflate(R.layout.send,parent,false)
            return SentViewHolder(view)
        }

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val currentMessage = messageList[position]


        if (holder.javaClass==SentViewHolder::class.java){
            //do the stuff for send view holder


            val viewHolder=holder as SentViewHolder

            holder.sendmessage.text = currentMessage.message

        }
        else{
            //do stuff for receive view holder
            val viewHolder = holder as ReceiveViewHolder

            holder.receivemessage.text = currentMessage.message
        }


    }

    override fun getItemViewType(position: Int): Int {
        val currentMessage = messageList[position]

        if (FirebaseAuth.getInstance().currentUser?.uid.equals(currentMessage.senderId)){
            return ITEM_SEND
        }
        else{
            return ITEM_RECEIVE
        }
    }

    override fun getItemCount(): Int {
        return messageList.size
    }
    class SentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val sendmessage = itemView.findViewById<TextView>(R.id.txt_sendMessage)

    }
    class ReceiveViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val receivemessage = itemView.findViewById<TextView>(R.id.txt_receiveMessage)

    }

}