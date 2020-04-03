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

import com.example.android.common.activities.MainActTwo;
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
import androidx.appcompat.widget.AlertDialogLayout;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.reflect.Array;
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
    private Object mainAct;

    private ArrayList<ArrayList<ChampBase>> Iconsets;
    private ArrayList<String> StripTitles ;

    private static int iter =0; //iterate over icons


    private static ArrayList<ChampBase> All;
    // BEGIN_INCLUDE(recyclerViewSampleViewHolder)
    /**
     * Provide a reference to the type of views that you are using (custom ViewHolder)
     */
    public class IconTuple {
        public int iconId;
        public int ID;
        String title ;

        public IconTuple(int iconId, int ID){
            this.iconId = iconId;
            this.ID = ID;
        }
        public IconTuple(ChampBase champsClass){

        }

    }
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
    public static class ViewHolder2 extends  RecyclerView.ViewHolder{

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
    public static class ViewHolder3 extends  RecyclerView.ViewHolder{
        private ImageView [] items = new ImageView[10];
        private TextView textView ;

        public ViewHolder3(View v){
            super(v);
            items[7] = (ImageView)v.findViewById(R.id.image_id4);
            items[6] = (ImageView)v.findViewById(R.id.image_id5);
            items[5] = (ImageView)v.findViewById(R.id.image_id6);
            items[4] = (ImageView)v.findViewById(R.id.image_id7);
            items[3] = (ImageView)v.findViewById(R.id.image_id8);
            items[2] = (ImageView)v.findViewById(R.id.image_id9);
            items[1] = (ImageView)v.findViewById(R.id.image_id10);
            items[0] = (ImageView)v.findViewById(R.id.image_id11);

            textView = (TextView)v.findViewById(R.id.IconSetTitleText);
       }

        public ImageView[] getItems() {
            return items;
        }

        public TextView getTitle() {
            return textView;
        }
    }
    /**
     * Initialize the dataset of the Adapter.
     *
     * @param dataSet String[] containing the data to populate views to be used by RecyclerView.
     */
    public CustomAdapter(Context baseContext , String[] dataSet, ArrayList<Object> items
    ) {
        baseCont= baseContext;
        mDataSet = dataSet;
        this.items  =  items;
    }

    public CustomAdapter(Context baseContext , String[] dataSet, ArrayList<Object> items,
                         Object main
    ) {
        baseCont= baseContext;
        mDataSet = dataSet;
        this.items  =  items;
        this.mainAct=main;

All = new ArrayList<>();

        Iconsets = new ArrayList<>();
        StripTitles = new ArrayList<>();

        //create some champions;
        /*0 */		ChampBase Ahri = new ChampBase(R.drawable.tft3_ahri_mobile_, 234,234,0);
        /*1 */        ChampBase Annie = new ChampBase(R.drawable.tft3_annie_mobile_,234,234,1);
        /*2 */        ChampBase Ashe = new ChampBase(R.drawable.tft3_ashe_mobile_,456,456,2);
        /*3 */        ChampBase Aurelion = new ChampBase(R.drawable.tft3_aurelionsol_mobile_,891,891,3);
        /*4 */        ChampBase Blitzkrank = new ChampBase(R.drawable.tft3_blitzcrank_mobile_,234,234,4);
        /*5 */        ChampBase Caitlin = new ChampBase(R.drawable.tft3_caitlyn_mobile_,123,123,5);
        /*6 */        ChampBase ChoGath = new ChampBase(R.drawable.tft3_chogath_mobile_,678,678,6);
        /*7 */        ChampBase Darius = new ChampBase(R.drawable.tft3_darius_mobile_,234,234,7);
        /*8 */        ChampBase Ekko = new ChampBase(R.drawable.tft3_ekko_mobile_,891,891,8);
        /*9 */        ChampBase Ezreal = new ChampBase(R.drawable.tft3_ezreal_mobile_,456,456,9);
        /*10*/        ChampBase Fiora = new ChampBase(R.drawable.tft3_fiora_mobile_,123,123,10);
        /*11*/        ChampBase Fizz = new ChampBase(R.drawable.tft3_fizz_mobile_,678,678,11);
        /*12*/        ChampBase Gangplank = new ChampBase(R.drawable.tft3_gangplank_mobile_,891,891,12);
        /*13*/        ChampBase Graves = new ChampBase(R.drawable.tft3_graves_mobile_,123,123,13);
        /*14*/        ChampBase Irelia = new ChampBase(R.drawable.tft3_irelia_mobile_,678,678,14);
        /*15*/        ChampBase Jarvan = new ChampBase(R.drawable.tft3_jarvan_mobile_,234,234,15);
        /*16*/        ChampBase Jayce = new ChampBase(R.drawable.tft3_jayce_mobile_,456,456,16);
        /*17*/        ChampBase Jihn = new ChampBase(R.drawable.tft3_jhin_mobile_,678,678,17);
        /*18*/        ChampBase Jinx = new ChampBase(R.drawable.tft3_jinx_mobile_,678,678,18);
        /*19*/        ChampBase Khazix = new ChampBase(R.drawable.tft3_khazix_mobile_,333,333,19);
        /*20*/        ChampBase Kaisa = new ChampBase(R.drawable.tft3_kaisa_mobile_,234,234,20);
        /*21*/        ChampBase Karma = new ChampBase(R.drawable.tft3_karma_mobile_,465,456,21);
        /*22*/        ChampBase Kassadin = new ChampBase(R.drawable.tft3_kassadin_mobile_,456,465,22);
        /*23*/        ChampBase Kayle = new ChampBase(R.drawable.tft3_kayle_mobile_,678,678,23);
        /*24*/        ChampBase Leonna = new ChampBase(R.drawable.tft3_lucian_mobile_,123,123,24);
        /*25*/        ChampBase Lucian = new ChampBase(R.drawable.tft3_leona_mobile_,234,234,25);
        /*26*/        ChampBase Lulu = new ChampBase(R.drawable.tft3_lulu_mobile_,891,891,26);
        /*27*/        ChampBase Lux = new ChampBase(R.drawable.tft3_lux_mobile_,465,456,27);
        /*28*/        ChampBase Masteryi = new ChampBase(R.drawable.tft3_masteryi_mobile_,456,456,29);
        /*29*/        ChampBase Malphite = new ChampBase(R.drawable.tft3_malphite_mobile_,123,123,28);
        /*30*/        ChampBase Missfortune = new ChampBase(R.drawable.tft3_missfortune_mobile_,891,891,30);
        /*31*/        ChampBase Mordekaiser = new ChampBase(R.drawable.tft3_mordekaiser_mobile_,234,234,31);
        /*32*/        ChampBase Neeko = new ChampBase(R.drawable.tft3_neeko_mobile_,465,456,32);
        /*33*/        ChampBase Poppy = new ChampBase(R.drawable.tft3_poppy_mobile_,891,891,33);
        /*34*/        ChampBase Rakan = new ChampBase(R.drawable.tft3_rakan_mobile_,234,234,34);
        /*35*/        ChampBase Rumble = new ChampBase(R.drawable.tft3_rumble_mobile_,345,456,35);
        /*36*/        ChampBase Shaco = new ChampBase(R.drawable.tft3_shaco_mobile_,456,456,36);
        /*37*/        ChampBase Shen = new ChampBase(R.drawable.tft3_shen_mobile_,456,456,37);
        /*38*/        ChampBase Sona = new ChampBase(R.drawable.tft3_sona_mobile_,123,123,38);
        /*39*/        ChampBase Soraka = new ChampBase(R.drawable.tft3_soraka_mobile_,678,678,39);
        /*40*/        ChampBase Syndra = new ChampBase(R.drawable.tft3_syndra_mobile_,465,456,40);
        /*41*/        ChampBase Thresh = new ChampBase(R.drawable.tft3_thresh_mobile_,891,891,41);
        /*42*/        ChampBase TwistedFate = new ChampBase(R.drawable.tft3_twistedfate_mobile_,123,132,42);
        /*43*/        ChampBase Velkoz = new ChampBase(R.drawable.tft3_velkoz_mobile_,678,678,43);
        /*44*/        ChampBase Vi = new ChampBase(R.drawable.tft3_vi_mobile_,456,456,44);
        /*45*/        ChampBase Wukong = new ChampBase(R.drawable.tft3_wukong_mobile_,678,678,45);
        /*46*/        ChampBase Xayah = new ChampBase(R.drawable.tft3_xayah_mobile_,123,123,46);
        /*47*/        ChampBase Xinzhao = new ChampBase(R.drawable.tft3_xinzhao_mobile_,234,234,47);
        /*48*/        ChampBase Yasuo = new ChampBase(R.drawable.tft3_yasuo_mobile_,234,234,48);
        /*49*/        ChampBase Ziggs = new ChampBase(R.drawable.tft3_ziggs_mobile_,123,123,49);
        /*50*/        ChampBase Zoey = new ChampBase(R.drawable.tft3_zoey_mobile_,123,123,50);




        ArrayList<ChampBase> Void = new ArrayList<>();
        ArrayList<ChampBase> Blasters = new ArrayList<>();
        ArrayList<ChampBase> Blademasters = new ArrayList<>();
        ArrayList<ChampBase> Celestials = new ArrayList<>();
        ArrayList<ChampBase> Brawlers = new ArrayList<>();
        ArrayList<ChampBase> Chrono = new ArrayList<>();
        ArrayList<ChampBase> Cybernetic = new ArrayList<>();
        ArrayList<ChampBase> DarkStar = new ArrayList<>();
        ArrayList<ChampBase> Mech = new ArrayList<>();
        ArrayList<ChampBase> Rebel = new ArrayList<>();
        ArrayList<ChampBase> Demolitionist = new ArrayList<>();
        ArrayList<ChampBase> Infiltrator = new ArrayList<>();
        ArrayList<ChampBase> ManaReaver = new ArrayList<>();
        ArrayList<ChampBase> Mystic = new ArrayList<>();
        ArrayList<ChampBase> Protector = new ArrayList<>();
        ArrayList<ChampBase> Sniper = new ArrayList<>();
        ArrayList<ChampBase> Sorcerer = new ArrayList<>();
        ArrayList<ChampBase> StarGuardian = new ArrayList<>();
        ArrayList<ChampBase> SpacePirate = new ArrayList<>();
        ArrayList<ChampBase> Starship = new ArrayList<>();
        ArrayList<ChampBase> Valkyria = new ArrayList<>();
        ArrayList<ChampBase> Vanguard = new ArrayList<>();



        All.add(Ahri );
        All.add(Annie );
        All.add(Ashe );
        All.add(Aurelion );
        All.add(Blitzkrank );
        All.add(Caitlin );
        All.add(ChoGath );
        All.add(Darius );
        All.add(Ekko );
        All.add(Ezreal );
        All.add(Fiora );
        All.add(Fizz );
        All.add(Gangplank );
        All.add(Graves );
        All.add(Irelia );
        All.add(Jarvan );
        All.add(Jayce );
        All.add(Jihn );
        All.add(Jinx );
        All.add(Khazix );
        All.add(Kaisa );
        All.add(Karma );
        All.add(Kassadin );
        All.add(Kayle );
        All.add(Leonna );
        All.add(Lucian );
        All.add(Lulu );
        All.add(Lux );
        All.add(Masteryi );
        All.add(Malphite );
        All.add(Missfortune );
        All.add(Mordekaiser );
        All.add(Neeko );
        All.add(Poppy );
        All.add(Rakan );
        All.add(Rumble );
        All.add(Shaco );
        All.add(Shen );
        All.add(Sona );
        All.add(Soraka );
        All.add(Syndra );
        All.add(Thresh );
        All.add(TwistedFate );
        All.add(Velkoz );
        All.add(Vi );
        All.add(Wukong );
        All.add(Xayah );
        All.add(Xinzhao );
        All.add(Yasuo );
        All.add(Ziggs );
        All.add(Zoey );




        //Void Champions
        Void.add(ChoGath);
        Void.add(Velkoz );
        Void.add(Khazix);

        //Blasters
        Blasters.add(Jinx);
        Blasters.add(Graves);
        Blasters.add(Ezreal);
        Blasters.add(Lucian);
        Blasters.add(Missfortune);

        //Blademasters
        Blademasters.add(Kayle);
        Blademasters.add(Masteryi);
        Blademasters.add(Irelia);
        Blademasters.add(Yasuo);
        Blademasters.add(Shen);
        Blademasters.add(Xayah);
        Blademasters.add(Fiora);

        //Celestials
        Celestials.add(Xayah);
        Celestials.add(Rakan);
        Celestials.add(Jarvan);
        Celestials.add(Ashe);
        Celestials.add(Kassadin);
        Celestials.add(Lulu);

        //Brawlers
        Brawlers.add(Malphite);
        Brawlers.add(Blitzkrank);
        Brawlers.add(Vi);
        Brawlers.add(ChoGath);

        //Chrono
Chrono.add(Caitlin);
Chrono.add(TwistedFate);
Chrono.add(Blitzkrank);
Chrono.add(Shen);
Chrono.add(Ezreal);
Chrono.add(Wukong);
Chrono.add(Thresh);

    //Cybernetic
        Cybernetic.add(Caitlin);
        Cybernetic.add(Leonna);
        Cybernetic.add(Lucian);
        Cybernetic.add(Vi);
        Cybernetic.add(Irelia);
        Cybernetic.add(Ekko);

        //DarkStar
        DarkStar.add(Jarvan);
        DarkStar.add(Mordekaiser);
        DarkStar.add(Karma);
        DarkStar.add(Lux)   ;
        DarkStar.add(Shaco);
        DarkStar.add(Jihn);

        //Mech
        Mech.add(Annie);
        Mech.add(Rumble);
        Mech.add(Fizz);

        //Rebel
        Rebel.add(Malphite);
        Rebel.add(Ziggs);
        Rebel.add(Sona);
        Rebel.add(Yasuo);
        Rebel.add(Masteryi);
        Rebel.add(Jinx);
        Rebel.add(Aurelion);

        //Demolitionist
        Demolitionist.add(Ziggs);
        Demolitionist.add(Rumble);
        Demolitionist.add(Gangplank);

        //Infiltrator
        Infiltrator.add(Khazix);
        Infiltrator.add(Kaisa);
        Infiltrator.add(Shaco);
        Infiltrator.add(Fizz);
        Infiltrator.add(Ekko);

        //ManaReaver
        ManaReaver.add(Darius);
        ManaReaver.add(Kassadin);
        ManaReaver.add(Irelia);
        ManaReaver.add(Thresh);

        //Mystic
        Mystic.add(Sona);
        Mystic.add(Karma);
        Mystic.add(Soraka);
        Mystic.add(Lulu)    ;

        //Protector
        Protector.add(Jarvan);
        Protector.add(Rakan);
        Protector.add(Jarvan);
        Protector.add(Neeko);

        //Sniper
        Sniper.add(Caitlin);
        Sniper.add(Ashe);
        Sniper.add(Jihn);

        //Sorcerer
        Sorcerer.add(TwistedFate);
        Sorcerer.add(Zoey);
        Sorcerer.add(Ahri);
        Sorcerer.add(Annie);
        Sorcerer.add(Lux)   ;
        Sorcerer.add(Syndra);
        Sorcerer.add(Velkoz);

        //StarGuardian
        StarGuardian.add(Poppy);
        StarGuardian.add(Zoey);
        StarGuardian.add(Ahri);
        StarGuardian.add(Neeko);
        StarGuardian.add(Syndra);
        StarGuardian.add(Soraka);

        //SpacePirate
        SpacePirate.add(Graves);
        SpacePirate.add(Darius);
        SpacePirate.add(Jayce);
        SpacePirate.add(Gangplank);

        //Starship
        Starship.add(Aurelion);

        //Valkyria
        Valkyria.add(Kayle);
        Valkyria.add(Kaisa);

        Vanguard.add(Leonna);
        Vanguard.add(Poppy);
        Vanguard.add(Mordekaiser);
        Vanguard.add(Jayce) ;
        Vanguard.add(Wukong);


        //put in matrix to be filled out in RecyclerView later
        Iconsets.add(Void) ;
        Iconsets.add(Blasters) ;
        Iconsets.add(Blademasters) ;
        Iconsets.add(Celestials) ;
        Iconsets.add(Brawlers);
        Iconsets.add(Chrono);
        Iconsets.add(Cybernetic);
        Iconsets.add(DarkStar);
        Iconsets.add(Blasters);
        Iconsets.add(Mech);
        Iconsets.add(Rebel);
        Iconsets.add(Demolitionist);
        Iconsets.add(Infiltrator);
        Iconsets.add(ManaReaver);
        Iconsets.add(Mystic);
        Iconsets.add(Protector);
        Iconsets.add(Sniper);
        Iconsets.add(Sorcerer);
        Iconsets.add(StarGuardian);
        Iconsets.add(SpacePirate);
        Iconsets.add(Starship);


        //Set titles
        StripTitles.add("Void");
        StripTitles.add("Blaster");
        StripTitles.add("Blademasters");


        StripTitles.add("Celestials");
        StripTitles.add("Brawlers");
        StripTitles.add("Cybernetic");
        StripTitles.add("Chrono");
        StripTitles.add("Cybernetic");
        StripTitles.add("DarkStar");
        StripTitles.add("Blaster");
        StripTitles.add("Mech");
        StripTitles.add("Rebel");
        StripTitles.add("Demolitionist");
        StripTitles.add("Infiltrator");
        StripTitles.add("ManaReaver");
        StripTitles.add("Mystic");
        StripTitles.add("Protector");
        StripTitles.add("Sniper");
        StripTitles.add("Sorcerer");
        StripTitles.add("StarGuardian");
        StripTitles.add("SpacePirate");
        StripTitles.add("Starship");

        StripTitles.add("Valkyrie");

        StripTitles.add("Vanguard");


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
            case 4:
                View v4 = inflater.inflate(R.layout.team_builder_icon_set,viewGroup,false)  ;
                viewHolder = new ViewHolder3(v4);
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
            case 4:
                ViewHolder3 vh3 = (ViewHolder3)viewHolder;
                configureViewHolder3(vh3,position);
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
                    Intent nn = new Intent(baseCont, MainActTwo.class);

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

        textView.setTag("Randoom");
        textView.getTag();

    }

    private void configureViewHolder2(ViewHolder2 viewHolder2, int pos){
        TextView title = viewHolder2.getTitle();
        TextView desc = viewHolder2.getDesc();
        ImageView imageView = viewHolder2.getIcon() ;
        title.setText("leos title");
        desc.setText("blablablablalbalblalblalalal llaksdf jlaskdfj lkasdjf lkasjdflka sjdklj");
        imageView.setImageResource(R.drawable.ic_launcher);
    }
    private void configureViewHolder3(ViewHolder3 vh3, int position) {
        //ImageView [] items = vh3.getItems();
        TextView title = vh3.getTitle();

        if (position< StripTitles.size()){
            title.setText(StripTitles.get(position));
        }else   {
            title.setText("Default");
        }

        //clear previous icons
        for (int i=0; i < 8 ;i ++){
            vh3.items[i].setImageBitmap(null);
            vh3.items[i].destroyDrawingCache();

        }
        if (position < Iconsets.size()) { //dont go past the list

            for (int i = 0; i < Iconsets.get(position).size(); i++) {
                int Id = Iconsets.get(position).get(i).iconId;
                int tag =Iconsets.get(position).get(i).internalId;

                vh3.items[i].setImageResource(Id);
                //tag it
                vh3.items[i].setTag(tag); //position * 10 + i

                vh3.items[i].setClickable(true);
                vh3.items[i].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                int x = (Integer) v.getTag();

                        //add this to the board!
                        //update graph data.
                        mainAct = (MainActTwo) mainAct;
                        ((MainActTwo) mainAct).RecyclerViewChampions_OnClick(
                                x,All.get(x) );
                    }
                });
            }


        }
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
            }else if (items.get(pos) instanceof TraitDesc1) {
                return 3;
            }else if(items.get(pos) instanceof  TeamBuilderSet) {
                return 4;
            }
            return 1;
        }
        return -1;
    }
}
