package poblenou.rottentomatoesclient;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import poblenou.rottentomatoesclient.pojo.Result;

/**
 * Created by 46066294p on 04/12/15.
 */
public class MovieAdapter extends ArrayAdapter<Result>
{
    final private String POSTER_BASE_URL = "http://image.tmdb.org/t/p/";
    final private String POSTER_SIZE = "w342";

    public MovieAdapter(Context context, int resource, List<Result> objects) {
        super(context, resource, objects);
    }

    public View getView(int position, View convertView, ViewGroup parent)
    {
        Result peliSeleccionada = getItem(position);

        if (convertView == null)
        {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.lvpelis_row, parent, false);
        }

        TextView titulo = (TextView) convertView.findViewById(R.id.TVtitulo);
        ImageView imagen = (ImageView) convertView.findViewById(R.id.IVimage);

        titulo.setText(peliSeleccionada.getTitle());

        Picasso.with(getContext()).load(POSTER_BASE_URL+POSTER_SIZE+peliSeleccionada.getPosterPath()).fit().into(imagen);

        return convertView;
    }

}
