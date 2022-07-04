package com.example.vmapp.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.vmapp.databinding.PeopleListBinding
import com.example.vmapp.databinding.RoomsListBinding
import com.example.vmapp.model.people.PeopleItem
import com.example.vmapp.model.rooms.RoomsItem
import java.text.DateFormat

const val PEOPLE = 0
const val ROOMS = 1

class RecyclerViewAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var mPeople: MutableList<PeopleItem> = mutableListOf()
    private var mRooms: MutableList<RoomsItem> = mutableListOf()

    inner class PeopleViewHolder(private val binding: PeopleListBinding ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(peopleItem: PeopleItem) {
            Glide.with(binding.ivImage).load(peopleItem.avatar).into(binding.ivImage)
            binding.txtEmail.text = peopleItem.email
            binding.txtName.text = peopleItem.firstName
        }
    }

    inner class RoomViewHolder(private val binding: RoomsListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(rooms: RoomsItem) {
            val occupancy = rooms.is_occupied
            binding.apply {
                tvOcupancy.text = rooms.max_occupancy.toString()
                tvRoomId.text = rooms.id
                tvCreatedAt.text = rooms.created_at
                if(occupancy){
                    tvOccupied.text = "Occupied"
                }else{
                    tvOccupied.text = "UnOccupied"
                }

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):RecyclerView.ViewHolder {

        if (viewType == PEOPLE) {
          return  PeopleViewHolder(
                PeopleListBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }
        if (viewType == ROOMS) {
           return RoomViewHolder(
                RoomsListBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }
       return      PeopleViewHolder(
           PeopleListBinding.inflate(
               LayoutInflater.from(parent.context),
               parent,
               false
           )
       )
    }

    override fun getItemViewType(position: Int): Int {
     if(position <mPeople.size){
         return PEOPLE
     }
        if(position - mPeople.size <mRooms.size){
            return ROOMS
        }
        return -1
    }


    override fun getItemCount(): Int {
        return mPeople.size+mRooms.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        if (holder is PeopleViewHolder) {
            holder.bind(mPeople[position])
        }
        if(holder is RoomViewHolder){
            holder.bind(mRooms[position])
        }
    }


    fun setLists(list: List<PeopleItem>) {
        mPeople.clear()
        mPeople.addAll(list)
        notifyDataSetChanged()
    }

    fun setRoomList(list: List<RoomsItem>) {
        mRooms.clear()
        mRooms.addAll(list)
        notifyDataSetChanged()
    }

}