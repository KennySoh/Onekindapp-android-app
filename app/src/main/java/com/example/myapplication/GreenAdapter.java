package com.example.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

public class GreenAdapter extends RecyclerView.Adapter<GreenAdapter.NumberViewHolder> {

    private static final String TAG = GreenAdapter.class.getSimpleName();
    final private ListItemClickListener mOnClickListener;

    private ArrayList<String> mTitleSet;
    private ArrayList<String> mBodySet;
    private ArrayList<Long> mTimeStamps;
    ArrayList<byte[]> mImageSet;
    private int mNumberItems;

    public interface ListItemClickListener{
        void onListItemClick(int clickItemIndex);
    }

    /**
     * Constructor for GreenAdapter that accepts a number of items to display and the specification
     * for the ListItemClickListener.
     *
     * @param numberOfItems Number of items to display in list
     */
    public GreenAdapter(int numberOfItems, ArrayList<String> myTitleSet,ArrayList<String> myBodySet, ArrayList<Long> myTimeStamps,ArrayList<byte[]> myImageSet, ListItemClickListener listener) {
        mNumberItems = numberOfItems;
        mTitleSet=myTitleSet;
        mBodySet=myBodySet;
        mTimeStamps=myTimeStamps;
        mImageSet=myImageSet;
        mOnClickListener=listener;
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
    public NumberViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.number_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);
        NumberViewHolder viewHolder = new NumberViewHolder(view);

        return viewHolder;
    }

    /**
     * OnBindViewHolder is called by the RecyclerView to display the data at the specified
     * position. In this method, we update the contents of the ViewHolder to display the correct
     * indices in the list for this particular position, using the "position" argument that is conveniently
     * passed into us.
     *
     * @param holder   The ViewHolder which should be updated to represent the contents of the
     *                 item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(NumberViewHolder holder, int position) {
        Log.d(TAG, "#" + position);
        holder.bind(position);
    }

    /**
     * This method simply returns the number of items to display. It is used behind the scenes
     * to help layout our Views and for animations.
     *
     * @return The number of items available in our forecast
     */
    @Override
    public int getItemCount() {
        return mNumberItems;
    }

    public void updateData(){
        mNumberItems=mTitleSet.size();
        notifyDataSetChanged();
    }

    /**
     * Cache of the children views for a list item.
     */
    class NumberViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        // Will display the position in the list, ie 0 through getItemCount() - 1
        TextView listSubtitle1;
        TextView listSubtitle2;
        TextView listDate1;
        TextView listDate2;
        TextView listDate3;
        ImageView listImageView1;

        private final Map<Integer, String> month_map = new HashMap<Integer, String>(){
            {
                put(0, "Jan");
                put(1, "Feb");
                put(2, "Mar");
                put(3, "Apr");
                put(4, "May");
                put(5, "Jun");
                put(6, "Jul");
                put(7, "Aug");
                put(8, "Sep");
                put(9, "Oct");
                put(10, "Nov");
                put(11, "Dec");

            }
        };
        /**
         * Constructor for our ViewHolder. Within this constructor, we get a reference to our
         * TextViews and set an onClickListener to listen for clicks. Those will be handled in the
         * onClick method below.
         * @param itemView The View that you inflated in
         *                 {@link GreenAdapter#onCreateViewHolder(ViewGroup, int)}
         */
        public NumberViewHolder(View itemView) {
            super(itemView);

            listSubtitle1 = (TextView) itemView.findViewById(R.id.myEntriesText_Subtitle1);
            listSubtitle2 = (TextView) itemView.findViewById(R.id.myEntriesText_Subtitle2);
            listDate1 = (TextView) itemView.findViewById(R.id.myEntriesText_Date1); //Feb
            listDate2 = (TextView) itemView.findViewById(R.id.myEntriesText_Date2); //01 "Date"
            listDate3 = (TextView) itemView.findViewById(R.id.myEntriesText_Date3); //12.30 pm
            listImageView1=(ImageView) itemView.findViewById(R.id.myImageView2);
            itemView.setOnClickListener(this);
        }

        /**
         * A method we wrote for convenience. This method will take an integer as input and
         * use that integer to display the appropriate text within a list item.
         * @param listIndex Position of the item in the list
         */
        void bind(int listIndex) {
            listSubtitle1.setText(mTitleSet.get(listIndex));
            listSubtitle2.setText(mBodySet.get(listIndex));
            byte[] byteArrayExtra=mImageSet.get(listIndex);
            Bitmap bitmap = BitmapFactory.decodeByteArray(byteArrayExtra, 0, byteArrayExtra.length, new BitmapFactory.Options());
            listImageView1.setImageBitmap(bitmap);
            long currentTimeStamp_L=mTimeStamps.get(listIndex);
            Log.i("mAdapter", "Title: "+mTitleSet.size()+", Body: "+mBodySet.size()+", Timestamp: "+mTimeStamps.size());
            Log.i("mAdapter", "Title: "+mTitleSet.get(listIndex)+", Body: "+mBodySet.get(listIndex)+", Timestamp: "+mTimeStamps.get(listIndex));
            Timestamp currentTimeStamp=new Timestamp(currentTimeStamp_L);
            // TimeStamps
            /* https://docs.oracle.com/javase/6/docs/api/java/text/SimpleDateFormat.html*/
            /* https://www.mkyong.com/java/java-date-and-calendar-examples/*/
            Calendar mCalendar = Calendar.getInstance(TimeZone.getTimeZone("Asia/Singapore"));
            mCalendar.setTime(currentTimeStamp);
            int year = mCalendar.get(Calendar.YEAR);
            int month = mCalendar.get(Calendar.MONTH);
            int day = mCalendar.get(Calendar.DAY_OF_MONTH);

            SimpleDateFormat sdf = new SimpleDateFormat("h:mm a"	);
            String time = sdf.format(currentTimeStamp);
            // 12:52 PM

            SimpleDateFormat sdf_day = new SimpleDateFormat("EEE"	);
            String day_name = sdf_day.format(currentTimeStamp);
            //Wed
            listDate1.setText(month_map.get(month));//Feb
            listDate2.setText(String.valueOf(day)); //01 date
            listDate3.setText(time); //12.30pm

        }

        @Override
        public void onClick(View v) {
            int clickedPosition=getAdapterPosition();
            mOnClickListener.onListItemClick(clickedPosition);
        }

    }
}
