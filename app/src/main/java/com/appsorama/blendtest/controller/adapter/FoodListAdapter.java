package com.appsorama.blendtest.controller.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.appsorama.blendtest.R;
import com.appsorama.blendtest.listener.RecyclerListListener;
import com.appsorama.blendtest.model.FoodModel;
import com.appsorama.blendtest.util.SocialNetworksUtil;
import com.appsorama.blendtest.widget.wrapper.ItemFoodWrapper;

import java.util.ArrayList;

/**
 * Created by Jose Torres in FreakyByte on 19/04/16.
 */
public class FoodListAdapter extends RecyclerView.Adapter<ItemFoodWrapper> {

    public static final String TAG = "OrderListAdapter";
    private ArrayList<FoodModel> aListFood = new ArrayList<>();
    private Activity mActivity;
    private RecyclerListListener mClickListener;

    /**
     * @param context
     */
    public FoodListAdapter(Activity context, RecyclerListListener mClickListener) {
        this.mActivity = context;
        this.mClickListener = mClickListener;
    }

    @Override
    public ItemFoodWrapper onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_food, parent, false);
        ItemFoodWrapper vh = new ItemFoodWrapper(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ItemFoodWrapper viewHolder, final int position) {
        final FoodModel mFood = aListFood.get(position);

        viewHolder.getTxName().setText(mFood.getName());
        viewHolder.getTxtCategory().setText(mFood.getCategory());
        viewHolder.getTxtReceived().setText(mFood.getStock_info().getReceived());
        viewHolder.getTxtQuantity().setText(String.valueOf(mFood.getStock_info().getQuantity()));
        viewHolder.getTxtNotes().setText(mFood.getStock_info().getNotes());

        if (mFood.getStock_info().getNotes().startsWith("http")) {
            viewHolder.getTxtNotes().setTextColor(mActivity.getResources().getColor(R.color.red));
            viewHolder.getTxtNotes().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SocialNetworksUtil.openWebUrl(mActivity, mFood.getStock_info().getNotes());
                }
            });
        } else {
            viewHolder.getTxtNotes().setTextColor(mActivity.getResources().getColor(R.color.black));
            viewHolder.getTxtNotes().setOnClickListener(null);
        }

        viewHolder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mClickListener != null)
                    mClickListener.OnItemClickListener(position);
            }
        });

    }


    @Override
    public int getItemCount() {
        return aListFood.size();
    }

    public ArrayList<FoodModel> getListFood() {
        return aListFood;
    }

    public void setListFood(ArrayList<FoodModel> aListFood) {
        this.aListFood = aListFood;
    }
}
