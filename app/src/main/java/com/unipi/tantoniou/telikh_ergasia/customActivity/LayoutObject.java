package com.unipi.tantoniou.telikh_ergasia.customActivity;


//this class is for mapping ids in the same layout
public class LayoutObject {
    private int drawerLayoutId;
    private int itemId;

    private Class activityClass;
    public LayoutObject() {
    }

    public LayoutObject(int itemId,int drawerLayoutId, Class activityClass) {
        this.drawerLayoutId = drawerLayoutId;
        this.itemId = itemId;
        this.activityClass = activityClass;
    }

    public int getDrawerLayoutId() {
        return drawerLayoutId;
    }

    public void setDrawerLayoutId(int drawerLayoutId) {
        this.drawerLayoutId = drawerLayoutId;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public Class getActivityClass() {
        return activityClass;
    }

    public void setActivityClass(Class activityClass) {
        this.activityClass = activityClass;
    }

    
}
