package com.example.android.recyclerview;

import android.content.Intent;
import android.os.Bundle;

import com.example.android.common.activities.SampleActivityBase;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 *Class contains all the information about every single champ to be displayed.
 * As this app will contain a relatively large amount of information.
 */



public  class ChampBase {
    /**
     * <p>
     *     Main fields to be displayed
     *      icons will contain images of the champs.
     *      Hp and dps are more info to be displayed.
     * </p>
     */
    public Integer iconId;
    public Integer hp;
    public Integer dps;

    public int family; //cybernetic, space pirate star guardian etc
    public int subFamily; //mana reaver, infiltrator, brawler


    /**
     * Unique id of the champion inside the app.
     */
    public Integer internalId;

    public static ArrayList<Integer> icons = new ArrayList<>();

    public static ArrayList<ChampBase> created = new ArrayList<>();

static{
    created = new ArrayList<>(50);
}
    public ChampBase(){
        icons = new ArrayList<>();


        //begin...
        /*0*/ 		icons.add(R.drawable.tft3_ahri_mobile_);
        /*1*/ 		icons.add(R.drawable.tft3_annie_mobile_);
        /*2 */		icons.add(R.drawable.tft3_ashe_mobile_);
        /*3 */		icons.add(R.drawable.tft3_aurelionsol_mobile_);
        /*4 */		icons.add(R.drawable.tft3_blitzcrank_mobile_);
        /*5 */		icons.add(R.drawable.tft3_caitlyn_mobile_);
        /*6 */		icons.add(R.drawable.tft3_chogath_mobile_);
        /*7 */		icons.add(R.drawable.tft3_darius_mobile_);
        /*8 */		icons.add(R.drawable.tft3_ekko_mobile_);
        /*9 */		icons.add(R.drawable.tft3_ezreal_mobile_);
        /*10*/		icons.add(R.drawable.tft3_fiora_mobile_);
        /*11*/		icons.add(R.drawable.tft3_fizz_mobile_);
        /*12*/		icons.add(R.drawable.tft3_gangplank_mobile_);
        /*13*/		icons.add(R.drawable.tft3_graves_mobile_);
        /*14*/		icons.add(R.drawable.tft3_irelia_mobile_);
        /*15*/		icons.add(R.drawable.tft3_jarvan_mobile_);
        /*16*/		icons.add(R.drawable.tft3_jayce_mobile_);
        /*17*/		icons.add(R.drawable.tft3_jhin_mobile_);
        /*18*/		icons.add(R.drawable.tft3_jinx_mobile_);
        /*19*/		icons.add(R.drawable.tft3_khazix_mobile_);
        /*20*/		icons.add(R.drawable.tft3_kaisa_mobile_);
        /*21*/		icons.add(R.drawable.tft3_karma_mobile_);
        /*22*/		icons.add(R.drawable.tft3_kassadin_mobile_);
        /*23*/		icons.add(R.drawable.tft3_kayle_mobile_);
        /*24*/		icons.add(R.drawable.tft3_lucian_mobile_);
        /*25*/		icons.add(R.drawable.tft3_leona_mobile_);
        /*26*/		icons.add(R.drawable.tft3_lulu_mobile_);
        /*27*/		icons.add(R.drawable.tft3_lux_mobile_);
        /*28*/		icons.add(R.drawable.tft3_masteryi_mobile_);
        /*29*/		icons.add(R.drawable.tft3_malphite_mobile_);
        /*30*/		icons.add(R.drawable.tft3_missfortune_mobile_);
        /*31*/		icons.add(R.drawable.tft3_mordekaiser_mobile_);
        /*32*/		icons.add(R.drawable.tft3_neeko_mobile_);
        /*33*/		icons.add(R.drawable.tft3_poppy_mobile_);
        /*34*/		icons.add(R.drawable.tft3_rakan_mobile_);
        /*35*/		icons.add(R.drawable.tft3_rumble_mobile_);
        /*36*/		icons.add(R.drawable.tft3_shaco_mobile_);
        /*37*/		icons.add(R.drawable.tft3_shen_mobile_);
        /*38*/		icons.add(R.drawable.tft3_sona_mobile_);
        /*39*/		icons.add(R.drawable.tft3_soraka_mobile_);
        /*40*/		icons.add(R.drawable.tft3_syndra_mobile_);
        /*41*/		icons.add(R.drawable.tft3_thresh_mobile_);
        /*42*/		icons.add(R.drawable.tft3_twistedfate_mobile_);
        /*43*/		icons.add(R.drawable.tft3_velkoz_mobile_);
        /*44*/		icons.add(R.drawable.tft3_vi_mobile_);
        /*45*/		icons.add(R.drawable.tft3_wukong_mobile_);
        /*46*/		icons.add(R.drawable.tft3_xayah_mobile_);
        /*47*/		icons.add(R.drawable.tft3_xinzhao_mobile_);
        /*48*/		icons.add(R.drawable.tft3_yasuo_mobile_);
        /*49*/		icons.add(R.drawable.tft3_ziggs_mobile_);
        /*50*/		icons.add(R.drawable.tft3_zoey_mobile_);



    }

    public ChampBase(int iconId, int hp, int dps, int internalId){
        this.iconId = iconId;
        this.hp =hp;
        this.dps=dps;
        this.internalId=internalId;
        this.family =15;
        this.subFamily=21;

    }

    public static void add(ChampBase o){
        created.add(o);

    }

    public static int getIcon(int index){
        if (index<icons.size()){
            return icons.get(index);
        }
        return icons.get(0);
    }

    /**
     * Returns the champion's rarity: white green blue purple or orange on a scale of 0-4
     * @return
     */
    public int getRarity(){
        if (this.hp == 123){
            return 0;
        }
        if (this.hp==234){
            return 1;
        }
        if (this.hp==456){
            return 2;
        }
        if (this.hp==678){
            return 3;
        }
        return 4;
    }
}

