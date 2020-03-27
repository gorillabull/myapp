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

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Provide views to RecyclerView with data from mDataSet.
 */
public class CustomAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
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

        }
        public TextView getTextView() {
            return textView;
        }
    }
    public class ViewHolder2 extends  RecyclerView.ViewHolder{
        private TextView title;
        private ImageView icon;
        private TextView desc;
        public ViewHolder2(View v){
            super(v);
            title    = (TextView) v.findViewById(R.id.main_trait_title);
            icon = (ImageView)v.findViewById(R.id.main_trait_image);
            desc = (TextView)v.findViewById(R.id.main_trait_description);

        }

        public ImageView getIcon() {
            return icon;
        }

        public TextView getDesc() {
            return desc;
        }

        public TextView getTitle() {
            return title;
        }
    }

    /**
     * Initialize the dataset of the Adapter.
     *
     * @param dataSet String[] containing the data to populate views to be used by RecyclerView.
     */
    public CustomAdapter(Context baseContext , String[] dataSet, ArrayList<Object> items) {
        baseCont= baseContext;
        mDataSet = dataSet;
        this.items  =  items;
    }


    // BEGIN_INCLUDE(recyclerViewOnCreateViewHolder)
    // Create new views (invoked by the layout manager)
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view.
        RecyclerView.ViewHolder viewHolder;
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());

        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.text_row_item, viewGroup, false);
        switch (viewType){
            case 1: //First Recycler View
                View v1 = inflater.inflate(R.layout.text_row_item, viewGroup, false);
              viewHolder = new ViewHolder(v1);
              break;
            case 2: //Recycler View in new Activity.

              View v2 = inflater.inflate(R.layout.titled_spacer, viewGroup, false);
              viewHolder = new ViewHolder1(v2);
              break;
            case 3:
                View v3 = inflater.inflate(R.layout.trait_box_one,viewGroup,false)  ;
                viewHolder = new ViewHolder2(v3);
                break;
            default:
                    View v0 = inflater.inflate(R.layout.text_row_item, viewGroup, false);
                    viewHolder = new ViewHolder(v0);
                    break;
        }

        return viewHolder;
    }


    // END_INCLUDE(recyclerViewOnCreateViewHolder)

    // BEGIN_INCLUDE(recyclerViewOnBindViewHolder)
    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder viewHolder,
                                 final int position) {
        Log.d(TAG, "Element " + position + " set.");

        // Get element from your dataset at this position and replace the contents of the view
        // with that element
        //viewHolder.getTextView().setText(mDataSet[position]);
       // viewHolder.getImageView().setImageResource(R.drawable.ic_launcher);

        //setup Boom button.
        switch (viewHolder.getItemViewType()){
            case 1:

              ViewHolder vh1 = (ViewHolder) viewHolder;
              configureViewHolder(vh1, position);
              break;

            case 2:
                ViewHolder1 vh01 = (ViewHolder1) viewHolder;
                configureViewHolder1(vh01,position);
                break;
            case 3:
                ViewHolder2 vh2 = (ViewHolder2)viewHolder;
                configureViewHolder2(vh2,position);
            case -1:
                return;
            default:
                ViewHolder vh001 = (ViewHolder) viewHolder;
                configureViewHolder(vh001, position);
                break;
        }


        }




    private void configureViewHolder(ViewHolder viewHolder, int pos) {
        final BoomMenuButton boomMenuButton = viewHolder.getBmB1();

        viewHolder.getBmB1().setButtonEnum(ButtonEnum.SimpleCircle);
        viewHolder.getBmB1().setPiecePlaceEnum(PiecePlaceEnum.DOT_1);
        viewHolder.getBmB1().setButtonPlaceEnum(ButtonPlaceEnum.SC_1);

        viewHolder.getBmB1().setInList(true);
        viewHolder.getBmB1().clearBuilders();

        for (int i = 0; i < viewHolder.getBmB1().getButtonPlaceEnum().buttonNumber(); i++) {
            SimpleCircleButton.Builder builder = new SimpleCircleButton.Builder().normalImageRes(
                    R.drawable.shape_oval_normal
            );
            builder.listener(new OnBMClickListener() {
                @Override
                public void onBoomButtonClick(int index) {
                    Intent nn = new Intent(baseCont, activity2.class);

                    boomMenuButton.getContext().startActivity(nn);

                    //Toast.makeText(viewHolder.getBmB1().getContext(), "Clicked " + index, Toast.LENGTH_SHORT).show();

                }
            });
            viewHolder.getBmB1().addBuilder(builder);
        }
        // END_INCLUDE(recyclerViewOnBindViewHolder)
    }

    private void configureViewHolder1(ViewHolder1 viewHolder, int pos){
        TextView textView = viewHolder.getTextView();
        textView.setText("this works.");

    }

    private void configureViewHolder2(ViewHolder2 viewHolder2, int pos){
        TextView title = viewHolder2.getTitle();
        TextView desc = viewHolder2.getDesc();
        ImageView imageView = viewHolder2.getIcon() ;
        title.setText("leos title");
        desc.setText("blablablablalbalblalblalalal llaksdf jlaskdfj lkasdjf lkasjdflka sjdklj");
        imageView.setImageResource(R.drawable.ic_launcher);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return items.size();
    }
    @Override
    public int getItemViewType(int pos) {
        if (pos < items.size()) {
            if (items.get(pos) instanceof String) {
                return 1;
            } else if (items.get(pos) instanceof UserBox) {
                return 2;
            }else if (items.get(pos) instanceof TraitDesc1)
                return 3;

            return 1;
        }
        return -1;
    }
}
