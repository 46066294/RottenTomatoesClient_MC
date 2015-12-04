package poblenou.rottentomatoesclient;

import android.util.Log;


import java.util.Arrays;
import poblenou.rottentomatoesclient.pojo.Example;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;
import retrofit.http.GET;
import retrofit.http.Query;


//Interfaz con dos metodos uno para hacer la llamada a peliculasPopulares y el otro para peliculasTopRated
interface InterfazMovies {

    @GET("popular")
    Call<Example> peliculasPopulares(
            @Query("api_key") String api_key);

    @GET("top_rated")
    Call<Example> peliculasTopRated(
            @Query("api_key") String api_key);
}

public class RetroFit {

//https://api.themoviedb.org/3/movie/popular?api_key=13bc649b4be786a5459437a47ac059a5     FULL LINK POPULARES
    private final InterfazMovies service;                                    //Constante objeto de la interfaz
    private final String BASE_URL = "https://api.themoviedb.org/3/movie/";          //Constante URL parte que no cambia
    private final String APPID = "13bc649b4be786a5459437a47ac059a5";                //Api Key de la API de Moviedb
    private InterfazMovies servicePopular; // Interfaz para las peliculas populares
    private InterfazMovies serviceTopRated; // Interfaz para las peliculas mejor valoradas

    //Constructor de la clase con el builder
    public RetroFit()
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(InterfazMovies.class);
    }

    //Metodo para mostrar las peliculas populares
    public void mostrarPopulares(final MovieAdapter adapter) {

        //Llamada al servicio Moviedb con el metodo de la interfaz
        Call<Example> llamadaPelicula = service.peliculasPopulares(APPID);

        //Cuando recibe la respuesta la pone en cola
        llamadaPelicula.enqueue(new Callback<Example>() {

            @Override
            public void onResponse(Response<Example> response, Retrofit retrofit) {

                Example film = response.body();

                Log.w("pelis", film.getResults().toString());
                //Limpiamos el adaptador
                adapter.clear();
                //Anadimos todos los resultados al adapter
                adapter.addAll(film.getResults());
            }

            @Override
            public void onFailure(Throwable t) {
                Log.e("Update Films", Arrays.toString(t.getStackTrace()));
            }
        });
    }

    //Metodo para mostrar topRated igual que el anterior solo cambia la llmada al metodo de la interfaz
    public void mostrarTopRated(final MovieAdapter adapter) {

        //Cambia el metodo de la interfaz
        Call<Example> llamadaPelicula = service.peliculasTopRated(APPID);
        llamadaPelicula.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Response<Example> response, Retrofit retrofit) {

                Example film = response.body();
                adapter.clear();
                adapter.addAll(film.getResults());
            }
            @Override
            public void onFailure(Throwable t) {
                Log.e("Update Films", Arrays.toString(t.getStackTrace()));
            }
        });
    }
}