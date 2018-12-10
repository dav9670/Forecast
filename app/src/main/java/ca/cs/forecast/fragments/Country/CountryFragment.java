package ca.cs.forecast.fragments.Country;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import ca.cs.forecast.R;
import ca.cs.forecast.activities.MainActivity;
import ca.cs.forecast.data.CountryViewModel;
import ca.cs.forecast.model.Country;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class CountryFragment extends Fragment {

    private static final String ARG_COLUMN_COUNT = "column-count";
    private int mColumnCount = 1;
    private OnMenuItemClickListener mMenuListener;
    private OnListFragmentInteractionListener mListener;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public CountryFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_country_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            CountryViewModel countryViewModel = ViewModelProviders.of(getActivity()).get(CountryViewModel.class);
            List<Country> countryList = countryViewModel.getItemList();
            if(countryList.get(0).getCode() != "ALLCITY")
                countryList.add(0,new Country(0, "ALLCITY", getString(R.string.allCities), null, 0));
            recyclerView.setAdapter(new CountryRecyclerViewAdapter(countryList, new CountryRecyclerViewAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(Country country) {
                    mListener.onCountryListFragmentInteraction(country);
                }
            }, getContext(), this));
        }

        setHasOptionsMenu(true);

        ((MainActivity) getActivity()).getToolbarTitle().setText(R.string.country);
        ((MainActivity) getActivity()).getToolbarImageView().setImageIcon(null);
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

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.country_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_country_menu_name) {
            mMenuListener.sortByName();
            return true;
        }
        if (id == R.id.action_country_menu_continent) {
            mMenuListener.sortByContinent();
            return true;
        }
        if (id == R.id.action_country_menu_population) {
            mMenuListener.sortByPopulation();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void setOnMenuItemClickListener(OnMenuItemClickListener listener) {
        mMenuListener = listener;
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
        void onCountryListFragmentInteraction(Country country);
    }

    /**
     * This interface is used to sort the data contained inside of the adapter
     */
    public interface OnMenuItemClickListener {
        void sortByName();

        void sortByContinent();

        void sortByPopulation();
    }
}
