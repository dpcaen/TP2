package fr.ensicaen.dp.tp2;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity {

    private ListView listView ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.liste) ;

        new DownloadTask().execute("http://jsonplaceholder.typicode.com/posts?userId=1") ;
    }
    void affiche(List<Post> liste)
    {
        PostAdapter articlesAdapter = new PostAdapter (this, (ArrayList<Post>) liste) ;
        listView.setAdapter(articlesAdapter);
    }


    private class DownloadTask extends AsyncTask<String,Void,List<Post>> {

        private String url ;

        @Override
        protected List<Post> doInBackground(String... strings) {
            String chaine = null ;
            try {
                chaine = HttpUtils.downloadUrl(strings [0]);
            } catch (IOException e)
            {
                Log.d ("Erreur",e.getMessage()) ;
            }
            List<Post> liste = JsonUtils.parsePosts(chaine) ;

            return liste;
        }

        @Override
        protected void onPostExecute(List<Post> liste) {
            affiche (liste) ;
        }
    }
}
