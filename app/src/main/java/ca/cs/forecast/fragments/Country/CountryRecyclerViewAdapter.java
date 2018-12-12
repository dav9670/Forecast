package ca.cs.forecast.fragments.Country;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import ca.cs.forecast.R;
import ca.cs.forecast.data.CountryViewModel;
import ca.cs.forecast.fragments.Country.CountryFragment.OnListFragmentInteractionListener;
import ca.cs.forecast.model.Country;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Country} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 */
public class CountryRecyclerViewAdapter extends RecyclerView.Adapter<CountryRecyclerViewAdapter.ViewHolder> {

    private final OnItemClickListener mListener;
    private List<Country> mValues;
    private Context mContext;

    public CountryRecyclerViewAdapter(List<Country> items, OnItemClickListener listener, Context context, CountryFragment fragment) {
        mValues = items;
        mListener = listener;
        mContext = context;
        fragment.setOnMenuItemClickListener(new CountryFragment.OnMenuItemClickListener() {
            @Override
            public void sortByName() {
                fragment.setSortMode(CountryViewModel.SORT_MODE.NAME);
                CountryViewModel countryViewModel = ViewModelProviders.of((FragmentActivity) mContext).get(CountryViewModel.class);
                countryViewModel.setSortMode(CountryViewModel.SORT_MODE.NAME);
                countryViewModel.setCallback(fragment);
            }

            @Override
            public void sortByContinent() {
                fragment.setSortMode(CountryViewModel.SORT_MODE.CONTINENT);
                CountryViewModel countryViewModel = ViewModelProviders.of((FragmentActivity) mContext).get(CountryViewModel.class);
                countryViewModel.setSortMode(CountryViewModel.SORT_MODE.CONTINENT);
                countryViewModel.setCallback(fragment);
            }

            @Override
            public void sortByPopulation() {
                fragment.setSortMode(CountryViewModel.SORT_MODE.POPULATION);
                CountryViewModel countryViewModel = ViewModelProviders.of((FragmentActivity) mContext).get(CountryViewModel.class);
                countryViewModel.setSortMode(CountryViewModel.SORT_MODE.POPULATION);
                countryViewModel.setCallback(fragment);
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
        if(mValues.get(position).getCode() == "ALLCITY"){
            holder.populationSizeTitleTextView.setVisibility(TextView.INVISIBLE);
            holder.populationSizeTextView.setText("");
        }else{
            holder.populationSizeTitleTextView.setVisibility(TextView.VISIBLE);
            holder.populationSizeTextView.setText(Long.toString(mValues.get(position).getPopulation()));
        }

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

    /**
     * The interface which is called when an item of the recyclerView is clicked
     */
    public interface OnItemClickListener {
        void onItemClick(Country country);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final CircleImageView imageView;
        public final TextView countryNameTextView;
        public final TextView continentNameTextView;
        public final TextView populationSizeTextView;
        public final TextView populationSizeTitleTextView;
        public Country mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            imageView = view.findViewById(R.id.flag_pictureView);
            countryNameTextView = view.findViewById(R.id.country_name_textView);
            continentNameTextView = view.findViewById(R.id.continent_name_textView);
            populationSizeTextView = view.findViewById(R.id.population_size_textView);
            populationSizeTitleTextView = view.findViewById(R.id.population_size_textViewStatic);
        }
    }

    /**
     * Set values of recycleView and notify the change
     *
     * @param countries    List of countries
     */
    public void setValues(List<Country> countries){
        mValues = countries;
        notifyDataSetChanged();
    }
}
