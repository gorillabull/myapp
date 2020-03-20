/*
* Copyright (C) 2014 The Android Open Source Project
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*      http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

package com.example.android.recyclerview;

import com.example.android.common.activities.activity2;
import com.example.android.common.logger.Log;

import com.nightonke.boommenu.BoomButtons.ButtonPlaceEnum;
import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.nightonke.boommenu.BoomButtons.SimpleCircleButton;
import com.nightonke.boommenu.BoomMenuButton;
import com.nightonke.boommenu.ButtonEnum;
import com.nightonke.boommenu.Piece.PiecePlaceEnum;

import android.content.Context;
import android.content.Intent;

import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Provide views to RecyclerView with data from mDataSet.
 */
public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {
    private static final String TAG = "CustomAdapter";

    private String[] mDataSet;
    private List<Object> items;

    private final Context baseCont;
    // BEGIN_INCLUDE(recyclerViewSampleViewHolder)
    /**
     * Provide a reference to the type of views that you are using (custom ViewHolder)
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView;
        private  final ImageView imageView;
        private final BoomMenuButton BmB1;

        public ViewHolder(View v) {
            super(v);
            // Define click listener for the ViewHolder's View.
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(TAG, "Element " + getAdapterPosition() + " clicked.");
                }
            });
            textView = (TextView) v.findViewById(R.id.textView);

            imageView = (ImageView) v.findViewById(R.id.image_id);

            BmB1 = (BoomMenuButton)v.findViewById(R.id.bmb1);
        }

        public TextView getTextView() {
            return textView;
        }
        public ImageView getImageView(){ return imageView;}

        public BoomMenuButton getBmB1() {
            return BmB1;
        }
    }
    // END_INCLUDE(recyclerViewSampleViewHolder)
    public static class ViewHolder1 extends  RecyclerView.ViewHolder{
        /*
        public final replaceme
        public final replaec
        public final replacee
         */
        private TextView textView;

        public ViewHolder1(View v ){
            super(v);
            textView = (TextView) v.findViewById(R.id.textViewTitle);
            /*Define some event
              v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(TAG, "Element " + getAdapterPosition() + " clicked.");
                }
            });
             */

            /*Set the public final vars to something

                        textView = (TextView) v.findViewById(R.id.textView);

            imageView = (ImageView) v.findViewById(R.id.image_id);

            BmB1 = (BoomMenuButton)v.findViewById(R.id.bmb1);
             */
        }

        //optional have some public acess functions nah.

    }
    /**
     * Initialize the dataset of the Adapter.
     *
     * @param dataSet String[] containing the data to populate views to be used by RecyclerView.
     */
    public CustomAdapter(Context baseContext , String[] dataSet, List<Object> items) {
        baseCont= baseContext;
        mDataSet = dataSet;
        this.items  =  items;
    }


    // BEGIN_INCLUDE(recyclerViewOnCreateViewHolder)
    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view.
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.text_row_item, viewGroup, false);
        switch (viewType){
            case 1:
                /*
                View v1 = inflater.inflate(R.layout.layout_viewholder1, viewGroup, false);
              viewHolder = new ViewHolder1(v1);
              break;
                 */
            case 2:
                /*
              View v2 = inflater.inflate(R.layout.layout_viewholder2, viewGroup, false);
              viewHolder = new ViewHolder2(v2);
              break;
                 */
                default:

        }

        return new ViewHolder(v);
    }
    // END_INCLUDE(recyclerViewOnCreateViewHolder)

    // BEGIN_INCLUDE(recyclerViewOnBindViewHolder)
    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {
        Log.d(TAG, "Element " + position + " set.");

        // Get element from your dataset at this position and replace the contents of the view
        // with that element
        //viewHolder.getTextView().setText(mDataSet[position]);
       // viewHolder.getImageView().setImageResource(R.drawable.ic_launcher);

        //setup Boom button.
        switch (viewHolder.getItemViewType()){
            case 1:
                /*
              ViewHolder1 vh1 = (ViewHolder1) viewHolder;
              configureViewHolder1(vh1, position);
              break;
                 */
            case 2:
                break;
            default:
                break;
        }
        viewHolder.getBmB1().setButtonEnum(ButtonEnum.SimpleCircle);
        viewHolder.getBmB1().setPiecePlaceEnum(PiecePlaceEnum.DOT_1);
        viewHolder.getBmB1().setButtonPlaceEnum(ButtonPlaceEnum.SC_1);

        viewHolder.getBmB1().setInList(true);
        viewHolder.getBmB1().clearBuilders();

        for(int i=0; i<viewHolder.getBmB1().getButtonPlaceEnum().buttonNumber();i++){
            SimpleCircleButton.Builder builder =new SimpleCircleButton.Builder().normalImageRes(
                    R.drawable.shape_oval_normal
            );
            builder.listener(new OnBMClickListener() {
                @Override
                public void onBoomButtonClick(int index) {
                   Intent nn = new Intent(baseCont , activity2.class) ; //viewHolder.getBmB1().getContext() 11
                    viewHolder.getBmB1().getContext().startActivity(nn);

                    //Toast.makeText(viewHolder.getBmB1().getContext(), "Clicked " + index, Toast.LENGTH_SHORT).show();

                }
            });
            /*
            viewHolder.getBmB1().addBuilder(new SimpleCircleButton.Builder().normalImageRes(
                    R.drawable.shape_oval_normal
            ));;
             */
            viewHolder.getBmB1().addBuilder(builder);
        }



    }
    private void configureDefaultViewHolder(ViewHolder1 vh, int pos){

    }
    // END_INCLUDE(recyclerViewOnBindViewHolder)

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataSet.length;
    }
    @Override
    public int getItemViewType(int pos){
        if(items.get(pos) instanceof ViewHolder){
            return 1;
        }else if (items.get(pos) instanceof ViewHolder1){
            return 2;
        }
        return -1;
    }

}
