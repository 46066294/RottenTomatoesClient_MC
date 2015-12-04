package poblenou.rottentomatoesclient;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

import poblenou.rottentomatoesclient.pojo.Result;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    GridView gridPelis;

    private MovieAdapter adapter;
    private ArrayList<Result> results;

    public MainActivityFragment() {
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_pelis_fragment, menu);
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        gridPelis = (GridView) rootView.findViewById(R.id.gridPelis);
        results = new ArrayList<>();
        adapter = new MovieAdapter(getContext(), 0, results);
        gridPelis.setAdapter(adapter);


        RetroFit x = new RetroFit();
        x.mostrarPopulares(adapter);
        x.mostrarTopRated(adapter);
        return rootView;
    }
}