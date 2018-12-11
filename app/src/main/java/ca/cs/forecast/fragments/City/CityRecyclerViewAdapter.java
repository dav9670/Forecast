package ca.cs.forecast.fragments.City;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import ca.cs.forecast.R;
import ca.cs.forecast.fragments.City.CityFragment.OnListFragmentInteractionListener;
import ca.cs.forecast.model.City;

/**
 * {@link RecyclerView.Adapter} that can display a {@link City} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 */
public class CityRecyclerViewAdapter extends RecyclerView.Adapter<CityRecyclerViewAdapter.ViewHolder> {

    private List<City> mValues;
    private final OnItemClickListener mListener;

    public CityRecyclerViewAdapter(List<City> items, OnItemClickListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_city, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mcityNameTextView.setText(mValues.get(position).getName());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onItemClick(holder.mItem);
                }
            }
        });
    }

    public interface OnItemClickListener {
        void onItemClick(City city);
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mcityNameTextView;
        public City mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mcityNameTextView = view.findViewById(R.id.city_name_textView);
        }
    }

    /**
     * Set values of recycleView and notify the change
     *
     * @param cities    List of cities
     */
    public void setValues(List<City> cities){
        mValues = cities;
        notifyDataSetChanged();
    }
}
