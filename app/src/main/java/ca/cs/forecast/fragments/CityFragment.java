package ca.cs.forecast.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;

import ca.cs.forecast.R;
import ca.cs.forecast.activities.MainActivity;
import ca.cs.forecast.fragments.dummy.DummyContent;
import ca.cs.forecast.fragments.dummy.DummyContent.DummyItem;
import ca.cs.forecast.model.Country;

import java.util.List;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class CityFragment extends Fragment {

    private static final String ARG_COLUMN_COUNT = "column-count";
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;
    private Country mCountry = null;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public CityFragment() {
    }

    public void setCountry(Country country) {
        mCountry = country;
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static CityFragment newInstance(int columnCount) {
        CityFragment fragment = new CityFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
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
        View view = inflater.inflate(R.layout.fragment_city_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            recyclerView.setAdapter(new CityRecyclerViewAdapter(DummyContent.ITEMS, mListener));
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
        if(mCountry != null){
            getActivity().setTitle(getString(R.string.cities) + " - " + mCountry.getName());
        }else{
            getActivity().setTitle(R.string.cities);
        }
        Menu menu = ((MainActivity)getActivity()).mMenu;
        if(menu != null){
            menu.findItem(R.id.action_country_menu_name).setVisible(false);
            menu.findItem(R.id.action_country_menu_continent).setVisible(false);
            menu.findItem(R.id.action_country_menu_population).setVisible(false);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        getActivity().setTitle(R.string.country);
        Menu menu = ((MainActivity)getActivity()).mMenu;
        if(menu != null){
            menu.findItem(R.id.action_country_menu_name).setVisible(true);
            menu.findItem(R.id.action_country_menu_continent).setVisible(true);
            menu.findItem(R.id.action_country_menu_population).setVisible(true);
        }
        mListener = null;
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
        void onCityListFragmentInteraction(DummyItem item);
    }
}
