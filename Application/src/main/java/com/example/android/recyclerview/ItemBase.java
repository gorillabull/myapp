package com.example.android.recyclerview;

public class ItemBase {
    public Integer iconId;
    public Integer hp;
    public Integer dps;
    public Integer internalId;

    /**
     * Ids for items used to build this item if it is built from a recipe.
     */
    public Integer recipe1_InternalId;
    public Integer recipe2_InternalId;

    public ItemBase(int iconId, int hp, int dps, int internalId){
        this.iconId = iconId;
        this.hp =hp;
        this.dps=dps;
        this.internalId=internalId;
    }

    public ItemBase(int iconId, int hp, int dps, int internalId, int id1, int id2){
        this.iconId = iconId;
        this.hp =hp;
        this.dps=dps;
        this.internalId=internalId;
        this.recipe1_InternalId = id1;
        this.recipe2_InternalId= id2;

    }

    public int getRecipe1(){
        return recipe1_InternalId;
    }
    public int getRecipe2(){
        return recipe2_InternalId;
    }

    public int getRarity(){
        return 0;
    }

}
