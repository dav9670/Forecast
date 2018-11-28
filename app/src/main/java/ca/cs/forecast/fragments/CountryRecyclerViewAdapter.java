package ca.cs.forecast.fragments;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import ca.cs.forecast.R;
import ca.cs.forecast.fragments.CountryFragment.OnListFragmentInteractionListener;
import ca.cs.forecast.model.Country;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Country} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 */
public class CountryRecyclerViewAdapter extends RecyclerView.Adapter<CountryRecyclerViewAdapter.ViewHolder> {

    private final List<Country> mValues;
    private final OnListFragmentInteractionListener mListener;

    public CountryRecyclerViewAdapter(List<Country> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_country, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);

        //TODO Set flag picture

        holder.countryNameTextView.setText(mValues.get(position).getName());
        holder.continentNameTextView.setText(mValues.get(position).getContinent());
        holder.populationSizeTextView.setText(Long.toString(mValues.get(position).getPopulation()));

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final CircleImageView imageView;
        public final TextView countryNameTextView;
        public final TextView continentNameTextView;
        public  final TextView populationSizeTextView;
        public Country mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            imageView = view.findViewById(R.id.flag_pictureView);
            countryNameTextView = view.findViewById(R.id.country_name_textView);
            continentNameTextView = view.findViewById(R.id.continent_name_textView);
            populationSizeTextView = view.findViewById(R.id.population_size_textView);
        }
    }
}
