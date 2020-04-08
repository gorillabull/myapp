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

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AlertDialogLayout;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.security.Guard;
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
    private ArrayList<ArrayList<ItemBase>>  item_Iconsets;

    private ArrayList<String> StripTitles ;

    private static int iter =0; //iterate over icons

    private Integer [] champBorderColors;

    private static ArrayList<ChampBase> All;
    private static ArrayList<ItemBase> AllItems;
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
        private ImageView [] items = new ImageView[8];
        private TextView textView ;

        public ViewHolder3(View v){
            super(v);
            items[7] = (ImageView)v.findViewById(R.id.image_id8);
            items[6] = (ImageView)v.findViewById(R.id.image_id7);

            items[5] = (ImageView)v.findViewById(R.id.image_id6);
            items[4] = (ImageView)v.findViewById(R.id.image_id5);
            items[3] = (ImageView)v.findViewById(R.id.image_id4);
            items[2] = (ImageView)v.findViewById(R.id.image_id3);
            items[1] = (ImageView)v.findViewById(R.id.image_id2);
            items[0] = (ImageView)v.findViewById(R.id.image_id1);

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
        item_Iconsets = new ArrayList<>();

        StripTitles = new ArrayList<>();

        champBorderColors = new Integer[5];
        champBorderColors[0]= Color.GRAY;
        champBorderColors[1] = Color.GREEN;
        champBorderColors[2] = Color.BLUE;
        champBorderColors[3]= Color.MAGENTA;
        champBorderColors[4]=Color.YELLOW;

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

    /**For holding items in a recyclerview
     *
     * @param baseCont
     * @param items
     * @param mainAct
     */
    public CustomAdapter(Context baseCont, ArrayList<Object> items, Object mainAct){
        this.baseCont = baseCont;
        mDataSet = new String[10];
        this.items = items;
        this.mainAct = mainAct;

        All = new ArrayList<>();
        Iconsets = new ArrayList<>();
        StripTitles = new ArrayList<>();

        champBorderColors = new Integer[1];
        champBorderColors[0] = Color.YELLOW;
        int iterator =0;

/*0*/        ItemBase BFSword = new ItemBase(R.drawable.bf_sword,123,123,iterator);iterator++;
/*1  */      ItemBase RecurveBow = new ItemBase(R.drawable.steel_stiletto,123,123,iterator); iterator++;
/*2  */     ItemBase ChainVest = new ItemBase(R.drawable.chain_vest,123,123,iterator );iterator++;
/*3  */     ItemBase NegatronCloak = new ItemBase(R.drawable.elementium_woven_mantle,123,123,iterator);iterator++;
/*4  */     ItemBase SpellRod = new ItemBase(R.drawable.needlessly_large_wand,123,123,iterator);iterator++;
/*5  */     ItemBase ManaTear = new ItemBase(R.drawable.tear_of_the_goddess,123,123,iterator);iterator++;
/*6  */     ItemBase GiantBelt = new ItemBase(R.drawable.mmmmmqqqqq1011_mighty_waistband_of_the_reaver,123,123,iterator);iterator++;
/*7  */     ItemBase Spatular = new ItemBase(R.drawable.Spatula,123,123,iterator);iterator++;
/*8  */     ItemBase Gloves = new ItemBase(R.drawable.brawlers_gloves,123,123,iterator);iterator++;

/*9  */     ItemBase InfinityEdge = new ItemBase(R.drawable.infinity_edge,123,123,iterator,8,0);iterator++;
/*10 */     ItemBase GuardianAngel = new ItemBase(R.drawable.guardian_angel,123,123,iterator,0,2);iterator++;
/*11 */     ItemBase Bloodthirster = new ItemBase(R.drawable.the_bloodthirster,123,123,iterator,0,3);iterator++;
/*12 */     ItemBase HexGunblade = new ItemBase(R.drawable.hextech_gunblade,123,123,iterator,0,4);iterator++;
/*13 */     ItemBase Shojin = new ItemBase(R.drawable.gugnir,123,123,iterator,0,5);iterator++;
/*14 */     ItemBase ZekeHerald = new ItemBase(R.drawable.ZekesHerald,123,123,iterator,0,6);iterator++;
/*15 */     ItemBase BladeoftheRunedKing = new ItemBase(R.drawable.blade_of_the_ruined_king,123,123,iterator,0,7);iterator++;
/*16 */     ItemBase RapidFirecannon = new ItemBase(R.drawable.rapid_firecannon,123,123,iterator,1,1);iterator++;
/*17 */     ItemBase Hurricane = new ItemBase(R.drawable.runaans_hurricane,123,123,iterator,1,2);iterator++;
/*18 */     ItemBase Rageblade = new ItemBase(R.drawable.guinsoos_rageblade,123,123,iterator,1,4);iterator++;
/*19 */     ItemBase StatikkShiv = new ItemBase(R.drawable.statikk_shiv,123,123,iterator,1,5);iterator++;
/*20 */     ItemBase ZZrotPortal = new ItemBase(R.drawable.ZzRotPortal,123,123,iterator,1,6);iterator++;
/*21 */     ItemBase InfiltratorTalons = new ItemBase(R.drawable.InfiltratorsTalons,123,123,iterator,1,7);iterator++;
/*22 */     ItemBase SwordBreaker = new ItemBase(R.drawable.SwordBreaker,123,123,iterator,2,3);iterator++;
/*23 */     ItemBase LocketofSolari = new ItemBase(R.drawable.crest_of_the_iron_solari,123,123,iterator,2,4);iterator++;
/*24 */     ItemBase FrozenHeart = new ItemBase(R.drawable.frozen_heart,123,123,iterator,2,5);iterator++;
/*25 */     ItemBase RedBuff = new ItemBase(R.drawable.RedBuff,123,123,iterator,2,6);iterator++;
/*26 */     ItemBase RebelMedal = new ItemBase(R.drawable.RebelMedal,123,123,iterator,2,7);iterator++;
/*27 */     ItemBase DragonClaw = new ItemBase(R.drawable.DragonsClaw,123,123,iterator,3,3);iterator++;
/*28 */     ItemBase IonicSpark = new ItemBase(R.drawable.ionicspark,123,123,iterator,3,4);iterator++;
/*29 */     ItemBase ChaliceofFavor = new ItemBase(R.drawable.ChaliceofFavor,123,123,iterator,3,5);iterator++;
/*30 */     ItemBase Zephyr = new ItemBase(R.drawable.zephyr,123,123,iterator,3,6);iterator++;
/*31 */     ItemBase CelestialOrb = new ItemBase(R.drawable.CelestialOrb,123,123,iterator,3,7);iterator++;
/*32 */     ItemBase Rabadon = new ItemBase(R.drawable.banksys_wizard_hat,123,123,iterator,4,4);iterator++;
/*33 */     ItemBase LudenEcho = new ItemBase(R.drawable.ludens_echo,123,123,iterator,4,5);iterator++;
/*34 */     ItemBase Morellonomicon = new ItemBase(R.drawable.pox_arcana,123,123,iterator,4,6);iterator++;
/*35 */     ItemBase DemolitionistTrait = new ItemBase(R.drawable.DemolitionistsCharge,123,123,iterator,4,7);iterator++;
/*36 */     ItemBase SeraphEmbrace = new ItemBase(R.drawable.seraphs_embrace,123,123,iterator,5,5);iterator++;
/*37 */     ItemBase Redemption = new ItemBase(R.drawable.guardian_angel,123,123,iterator,5,6);iterator++;
/*38 */     ItemBase StarGuardianTrait = new ItemBase(R.drawable.StarGuardiansCharm,123,123,iterator,5,7);iterator++;
/*39 */     ItemBase Warmog = new ItemBase(R.drawable.warmog_the_living_armor,123,123,iterator,6,6);iterator++;
/*40 */     ItemBase ProtectorTrait = new ItemBase(R.drawable.ProtectorsChestguard,123,123,iterator,6,7);iterator++;
/*41 */     ItemBase ForceofNature = new ItemBase(R.drawable.ForceofNature,123,123,iterator,7,7);iterator++;
/*42 */     ItemBase ShroudofStillness = new ItemBase(R.drawable.ShroudofStillness,123,123,iterator,2,8);iterator++;
/*43 */     ItemBase Quicksilver = new ItemBase(R.drawable.Quicksilver,123,123,iterator,3,8);iterator++;
/*44 */     ItemBase SpellCritGauntlets = new ItemBase(R.drawable.JeweledGauntlet,123,123,iterator,4,8);iterator++;
/*45 */     ItemBase HandofJustice = new ItemBase(R.drawable.HandofJustice,123,123,iterator,5,8);iterator++;
/*46 */     ItemBase TrapClaw = new ItemBase(R.drawable.TrapClaw,123,123,iterator,6,8);iterator++;
/*47 */     ItemBase DarkStarHeart = new ItemBase(R.drawable.DarkStarsHeart,123,123,iterator,7,8);iterator++;
/*48 */     ItemBase ThiefGloves = new ItemBase(R.drawable.ThiefsGloves,123,123,iterator,8,8);iterator++;
/*49 */     ItemBase Deathblade = new ItemBase(R.drawable.Deathblade,123,123,iterator,0,0);iterator++;
/*50 */     ItemBase GiantSlayer = new ItemBase(R.drawable.giant_slayer,123,123,iterator,0,1);iterator++;
/*51 */     ItemBase TitanResolve = new ItemBase(R.drawable.TitansResolve,123,123,iterator,1,2);iterator++;
/*52 */     ItemBase BrambleVest = new ItemBase(R.drawable.bramble_vest,123,123,iterator,2,2);iterator++;
/*53 */     ItemBase ArmorPenCrossbow = new ItemBase(R.drawable.guardian_angel,123,123,iterator,1,8);iterator++;

//missing neekos and one more item.


        AllItems.add(BFSword );
        AllItems.add(RecurveBow );
        AllItems.add(ChainVest );
        AllItems.add(NegatronCloak );
        AllItems.add(SpellRod );
        AllItems.add(ManaTear );
        AllItems.add(GiantBelt );
        AllItems.add(Spatular );
        AllItems.add(Gloves );
        AllItems.add(InfinityEdge );
        AllItems.add(GuardianAngel );
        AllItems.add(Bloodthirster );
        AllItems.add(HexGunblade );
        AllItems.add(Shojin );
        AllItems.add(ZekeHerald );
        AllItems.add(BladeoftheRunedKing );
        AllItems.add(RapidFirecannon );
        AllItems.add(Hurricane );
        AllItems.add(Rageblade );
        AllItems.add(StatikkShiv );
        AllItems.add(ZZrotPortal );
        AllItems.add(InfiltratorTalons );
        AllItems.add(SwordBreaker );
        AllItems.add(LocketofSolari );
        AllItems.add(FrozenHeart );
        AllItems.add(RedBuff );
        AllItems.add(RebelMedal );
        AllItems.add(DragonClaw );
        AllItems.add(IonicSpark );
        AllItems.add(ChaliceofFavor );
        AllItems.add(Zephyr );
        AllItems.add(CelestialOrb );
        AllItems.add(Rabadon );
        AllItems.add(LudenEcho );
        AllItems.add(Morellonomicon );
        AllItems.add(DemolitionistTrait );
        AllItems.add(SeraphEmbrace );
        AllItems.add(Redemption );
        AllItems.add(StarGuardianTrait );
        AllItems.add(Warmog );
        AllItems.add(ProtectorTrait );
        AllItems.add(ForceofNature );
        AllItems.add(ShroudofStillness );
        AllItems.add(Quicksilver );
        AllItems.add(SpellCritGauntlets );
        AllItems.add(HandofJustice );
        AllItems.add(TrapClaw );
        AllItems.add(DarkStarHeart );
        AllItems.add(ThiefGloves );
        AllItems.add(Deathblade );
        AllItems.add(GiantSlayer );
        AllItems.add(TitanResolve );
        AllItems.add(BrambleVest );
        AllItems.add(ArmorPenCrossbow );

        //categorize items into great good decent etc..
        ArrayList<ItemBase> great = new ArrayList<>();
        ArrayList<ItemBase> good = new ArrayList<>();
        ArrayList<ItemBase> neutral = new ArrayList<>();
        ArrayList<ItemBase> bad = new ArrayList<>();
        ArrayList<ItemBase> terrible = new ArrayList<>();
        ArrayList<ItemBase> terrible1 = new ArrayList<>();
        ArrayList<ItemBase> terrible2 = new ArrayList<>();

        great.add(Spatular);
        great.add(Gloves);
        great.add(GiantBelt);
        great.add(ManaTear);
        great.add(SpellRod);
        great.add(NegatronCloak);
        great.add(ChainVest);
        great.add(RecurveBow);

        good.add(BFSword);
        good.add(InfinityEdge);
        good.add(GuardianAngel);
        good.add(Bloodthirster);
        good.add(HexGunblade);
        good.add(Shojin);
        good.add(ZekeHerald);
        good.add(BladeoftheRunedKing);
        good.add(RapidFirecannon);

        neutral.add(Hurricane);
        neutral.add(Rageblade);
        neutral.add(StatikkShiv);
        neutral.add(ZZrotPortal);
        neutral.add(InfiltratorTalons);
        neutral.add(SwordBreaker);
        neutral.add(LocketofSolari);
        neutral.add(FrozenHeart);

        bad.add(RedBuff);
        bad.add(DragonClaw);
        bad.add(IonicSpark);
        bad.add(ChaliceofFavor);
        bad.add(Zephyr);
        bad.add(CelestialOrb);
        bad.add(Rabadon);
        bad.add(LudenEcho   );

        terrible.add(Morellonomicon);
        terrible.add(DemolitionistTrait);
        terrible.add(SeraphEmbrace);
        terrible.add(Redemption);
        terrible.add(StarGuardianTrait);
        terrible.add(Warmog);
        terrible.add(ProtectorTrait);
        terrible.add(ForceofNature);

        terrible1.add(ShroudofStillness);
        terrible1.add(Quicksilver);
        terrible1.add(SpellCritGauntlets);
        terrible1.add(HandofJustice);
        terrible1.add(TrapClaw);
        terrible1.add(DarkStarHeart);
        terrible1.add(ThiefGloves);
        terrible1.add(Deathblade);

        terrible2.add(GiantSlayer);
        terrible2.add(TitanResolve);
        terrible2.add(BrambleVest);
        terrible2.add(ArmorPenCrossbow);

        item_Iconsets.add(great);
        item_Iconsets.add(good  );
        item_Iconsets.add(neutral);
        item_Iconsets.add(bad   );
        item_Iconsets.add(terrible);
        item_Iconsets.add(terrible1 );
        item_Iconsets.add(terrible2 );

        StripTitles.add("Base Items");
        StripTitles.add("Good Items");
        StripTitles.add("Decent Items");
        StripTitles.add("Neutral ");
        StripTitles.add("Bad items");
        StripTitles.add("Misc Items 1");
        StripTitles.add("Misc Items 2");



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

            case 5:
                View v5 = inflater.inflate(R.layout.team_builder_icon_set,viewGroup,false);
                viewHolder=new ViewHolder3(v5);
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
                break;
            case 4:
                ViewHolder3 vh3 = (ViewHolder3)viewHolder;
                configureViewHolder3(vh3,position);
                break;
            case 5:
                ViewHolder3 vh5 = (ViewHolder3)viewHolder;
                configureViewHolder5(vh5,position);
                break;
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
        for (int i=0; i < vh3.items.length ;i ++){
            vh3.items[i].setImageBitmap(null);
            vh3.items[i].destroyDrawingCache();
            vh3.items[i].setBackgroundColor(Color.BLACK);

        }
        if (position < Iconsets.size()) { //dont go past the list

            for (int i = 0; i < Iconsets.get(position).size(); i++) {
                int Id = Iconsets.get(position).get(i).iconId;
                int tag =Iconsets.get(position).get(i).internalId;

                vh3.items[i].setImageResource(Id);
                //tag it
                vh3.items[i].setTag(tag); //position * 10 + i

                vh3.items[i].setBackgroundColor(
                        champBorderColors[Iconsets.get(position).get(i).getRarity()]
                );


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

    private void configureViewHolder5(ViewHolder3 vh5, int position) {
        TextView title = vh5.getTitle();
        if (position< StripTitles.size()){

            title.setText(StripTitles.get(position));
        }else   {
            title.setText("Default");
        }

        //clear previous icons
        for (int i=0; i < vh5.items.length ;i ++){
            vh5.items[i].setImageBitmap(null);
            vh5.items[i].destroyDrawingCache();
            vh5.items[i].setBackgroundColor(Color.BLACK);

        }

        if (position < item_Iconsets.size()) { //dont go past the list

            for (int i = 0; i < item_Iconsets.get(position).size(); i++) {
                int Id = item_Iconsets.get(position).get(i).iconId;
                int tag =item_Iconsets.get(position).get(i).internalId;

                vh5.items[i].setImageResource(Id);
                //tag it
                vh5.items[i].setTag(tag); //position * 10 + i

                vh5.items[i].setBackgroundColor(
                        champBorderColors[Iconsets.get(position).get(i).getRarity()]
                );


                vh5.items[i].setClickable(true);
                vh5.items[i].setOnClickListener(new View.OnClickListener() {
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
            }else if(items.get(pos) instanceof ItemBuilderSet ){
                return 5;
            }
            return 1;
        }
        return -1;
    }
}
