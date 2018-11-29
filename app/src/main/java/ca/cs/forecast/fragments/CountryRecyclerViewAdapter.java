package ca.cs.forecast.fragments;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import ca.cs.forecast.ForecastApp;
import ca.cs.forecast.R;
import ca.cs.forecast.activities.MainActivity;
import ca.cs.forecast.fragments.CountryFragment.OnListFragmentInteractionListener;
import ca.cs.forecast.model.Country;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Country} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 */
public class CountryRecyclerViewAdapter extends RecyclerView.Adapter<CountryRecyclerViewAdapter.ViewHolder> {

    private List<Country> mValues;
    private final OnItemClickListener mListener;
    private Context mContext;

    public CountryRecyclerViewAdapter(List<Country> items, OnItemClickListener listener, Context context, CountryFragment fragment) {
        mValues = items;
        mListener = listener;
        mContext = context;
        fragment.setOnMenuItemClickListener(new CountryFragment.OnMenuItemClickListener() {
            @Override
            public void sortByName() {
                mValues = ForecastApp.get().getDB().getCountryDao().getAllByName();
                notifyDataSetChanged();
            }

            @Override
            public void sortByContinent() {
                mValues = ForecastApp.get().getDB().getCountryDao().getAllByContinent();
                notifyDataSetChanged();
            }

            @Override
            public void sortByPopulation() {
                mValues = ForecastApp.get().getDB().getCountryDao().getAllByPopulation();
                notifyDataSetChanged();
            }
        });
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

        String url = "http://www.geognos.com/api/en/countries/flag/*code.png";
        url = url.replace("*code", mValues.get(position).getCode());
        Picasso.with(mContext).load(url).placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(holder.imageView);
        holder.countryNameTextView.setText(mValues.get(position).getName());
        holder.continentNameTextView.setText(mValues.get(position).getContinent());
        holder.populationSizeTextView.setText(Long.toString(mValues.get(position).getPopulation()));

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

    public interface OnItemClickListener{
        void onItemClick(Country country);
    }
}
