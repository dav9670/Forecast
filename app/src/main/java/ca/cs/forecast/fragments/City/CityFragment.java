package ca.cs.forecast.fragments.City;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SearchView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import ca.cs.forecast.ForecastApp;
import ca.cs.forecast.R;
import ca.cs.forecast.activities.MainActivity;
import ca.cs.forecast.data.CityViewModel;
import ca.cs.forecast.data.CountryViewModel;
import ca.cs.forecast.model.City;
import ca.cs.forecast.model.Country;
import ca.cs.forecast.utils.Constants;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class CityFragment extends Fragment implements CityViewModel.Callback {

    private static final String ARG_COLUMN_COUNT = "column-count";
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;
    private Country mCountry = null;
    private CityRecyclerViewAdapter recyclerViewAdapter;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public CityFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mCountry = ViewModelProviders.of(getActivity()).get(CountryViewModel.class).getSelectedItem();

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_city_list, container, false);

        SearchView searchView = view.findViewById(R.id.searchView);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                CityViewModel cityViewModel = ViewModelProviders.of((FragmentActivity) getContext()).get(CityViewModel.class);
                cityViewModel.setCountryCode(mCountry.getCode());

                if(newText.length() > 0){
                    cityViewModel.setSearchMode(CityViewModel.SEARCH_MODE.NAME);
                    cityViewModel.setSearchText(newText);
                }else{
                    cityViewModel.setSearchMode(CityViewModel.SEARCH_MODE.NONE);
                    cityViewModel.setSearchText("");
                }

                cityViewModel.setCallback(CityFragment.this);
                return false;
            }

        });

        // Set the adapter
        Context context = view.getContext();
        RecyclerView recyclerView = view.findViewById(R.id.listCity);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        recyclerViewAdapter = new CityRecyclerViewAdapter(new ArrayList<>(), city -> mListener.onCityListFragmentInteraction(city));
        recyclerView.setAdapter(recyclerViewAdapter);

        if(savedInstanceState == null){
            searchView.setQuery("", false);
        }


        if (mCountry != null) {
            ((MainActivity) getActivity()).getToolbarTitle().setText(getString(R.string.cities) + " - " + mCountry.getName());

            ImageView imageView = ((MainActivity) getActivity()).getToolbarImageView();
            String url = Constants.BASE_FLAG_URL;
            url = url.replace(Constants.WILD_CARD, mCountry.getCode());
            Picasso.with(getContext()).load(url).placeholder(R.mipmap.ic_launcher)
                    .error(R.mipmap.ic_launcher)
                    .into(imageView);
        } else {
            ((MainActivity) getActivity()).getToolbarTitle().setText(R.string.cities);
        }
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * Show the cities when they are receive
     *
     * @param  cities  list of cities
     */
    @Override
    public void onCityReceive(List<City> cities) {
        recyclerViewAdapter.setValues(cities);
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        void onCityListFragmentInteraction(City item);
    }
}
