package com.example.myapplication;

import android.content.Context;
import android.media.Image;
import android.nfc.Tag;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;

public class GreenAdapter_Diary extends RecyclerView.Adapter<GreenAdapter_Diary.NumberViewHolder_Diary> {

    private static final String TAG = GreenAdapter_Diary.class.getSimpleName();
    final private ListItemClickListener mOnClickListener;

    public ArrayList<String> mTitleSet;
    private int mNumberItems;

    //Images for imageview
    public static HashMap<String,Integer> mImagesId=new HashMap<String, Integer>() {
        {
            put("Mint Seeds", R.drawable.image_mint_leave_adjusted);
            put("Tomato", R.drawable.tomato_plants_adjusted);
            put("Basil", R.drawable.basil_leaves_adjusted);
        }
    };

    public interface ListItemClickListener{
        void onListItemClick(int clickItemIndex);
    }

    /**
     * Constructor for GreenAdapter that accepts a number of items to display and the specification
     * for the ListItemClickListener.
     *
     * @param numberOfItems Number of items to display in list
     */
    public GreenAdapter_Diary(int numberOfItems, ArrayList<String> myTitleSet, ListItemClickListener listener) {
        mNumberItems = numberOfItems;
        mTitleSet=myTitleSet;
        mOnClickListener=listener;

    }

    public void updateData(){
        mNumberItems=mTitleSet.size();
        notifyDataSetChanged();
    }

    /**
     *
     * This gets called when each new ViewHolder is created. This happens when the RecyclerView
     * is laid out. Enough ViewHolders will be created to fill the screen and allow for scrolling.
     *
     * @param viewGroup The ViewGroup that these ViewHolders are contained within.
     * @param viewType  If your RecyclerView has more than one type of item (which ours doesn't) you
     *                  can use this viewType integer to provide a different layout. See
     *                  {@link}
     *                  for more details.
     * @return A new NumberViewHolder that holds the View for each list item
     */
    @Override
    public NumberViewHolder_Diary onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.number_list_item_diary;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);
        NumberViewHolder_Diary viewHolder = new NumberViewHolder_Diary(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NumberViewHolder_Diary holder, int position) {
        Log.d(TAG, "#" + position);
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return mNumberItems;
    }

    /**
     * Cache of the children views for a list item.
     */
    class NumberViewHolder_Diary extends RecyclerView.ViewHolder implements View.OnClickListener {

        // Will display the position in the list, ie 0 through getItemCount() - 1
        TextView listSubtitle1;
        ImageView listImageView1;

        /**
         * Constructor for our ViewHolder. Within this constructor, we get a reference to our
         * TextViews and set an onClickListener to listen for clicks. Those will be handled in the
         * onClick method below.
         * @param itemView The View that you inflated in
         *                 {@link GreenAdapter#onCreateViewHolder(ViewGroup, int)}
         */
        public NumberViewHolder_Diary(View itemView) {
            super(itemView);

            listSubtitle1 = (TextView) itemView.findViewById(R.id.myImageViewText);
            listImageView1= (ImageView)itemView.findViewById(R.id.myImageView);
            itemView.setOnClickListener(this);
        }

        /**
         * A method we wrote for convenience. This method will take an integer as input and
         * use that integer to display the appropriate text within a list item.
         * @param listIndex Position of the item in the list
         */
        void bind(int listIndex) {
            listSubtitle1.setText(mTitleSet.get(listIndex));
            listImageView1.setImageResource(mImagesId.get(mTitleSet.get(listIndex)));
        }

        @Override
        public void onClick(View v) {
            int clickedPosition=getAdapterPosition();
            mOnClickListener.onListItemClick(clickedPosition);
        }
    }
}