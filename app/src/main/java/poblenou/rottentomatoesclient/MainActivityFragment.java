package poblenou.rottentomatoesclient;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
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
    private RetroFit x = new RetroFit();

    public MainActivityFragment() {
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_pelis_fragment, menu);
    }

    @Override
    public void onStart() {
        super.onStart();
        refresh();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

    }

    public void refresh() { // El mètode refresh gestiona les preferencies. Segons les preferencies, cridarà al mètode popular o al mètode toprated

        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(getContext()); // necesario para referenciar y leer la configuración del programa

        if (settings.getString("ListaPeliculas", "0").equals("0")) {
            x.mostrarPopulares(adapter);
        } else if (settings.getString("ListaPeliculas", "1").equals("1")) {
            x.mostrarTopRated(adapter);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        gridPelis = (GridView) rootView.findViewById(R.id.gridPelis);
        results = new ArrayList<>();
        adapter = new MovieAdapter(getContext(), 0, results);
        gridPelis.setAdapter(adapter);

        return rootView;
    }
}