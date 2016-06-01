package com.appsorama.blendtest.widget.wrapper;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.appsorama.blendtest.R;
import com.appsorama.blendtest.ui.textview.AutoFitTxtView;

/**
 * Created by Jose Torres in FreakyByte on 19/04/16.
 */
public class ItemFoodWrapper extends RecyclerView.ViewHolder {

    public View view = null;

    private AutoFitTxtView txt_list_item_name = null;
    private AutoFitTxtView txt_list_item_category = null;
    private AutoFitTxtView txt_list_item_received = null;
    private AutoFitTxtView txt_list_item_quantity = null;
    private AutoFitTxtView txt_list_item_notes = null;


    public ItemFoodWrapper(View base) {
        super(base);
        this.view = base;
    }

    public AutoFitTxtView getTxName() {
        if (txt_list_item_name == null)
            txt_list_item_name = (AutoFitTxtView) view.findViewById(R.id.txt_list_item_name);
        return txt_list_item_name;
    }

    public AutoFitTxtView getTxtCategory() {
        if (txt_list_item_category == null)
            txt_list_item_category = (AutoFitTxtView) view.findViewById(R.id.txt_list_item_category);
        return txt_list_item_category;
    }

    public AutoFitTxtView getTxtReceived() {
        if (txt_list_item_received == null)
            txt_list_item_received = (AutoFitTxtView) view.findViewById(R.id.txt_list_item_received);
        return txt_list_item_received;
    }

    public AutoFitTxtView getTxtQuantity() {
        if (txt_list_item_quantity == null)
            txt_list_item_quantity = (AutoFitTxtView) view.findViewById(R.id.txt_list_item_quantity);
        return txt_list_item_quantity;
    }

    public AutoFitTxtView getTxtNotes() {
        if (txt_list_item_notes == null)
            txt_list_item_notes = (AutoFitTxtView) view.findViewById(R.id.txt_list_item_notes);
        return txt_list_item_notes;
    }

}
