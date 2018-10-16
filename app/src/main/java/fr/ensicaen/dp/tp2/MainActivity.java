package fr.ensicaen.dp.tp2;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listView ;
    private List<Post> listeArticles ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.liste) ;


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //              Toast.makeText(MainActivity.this, "Numéro " + i + " " + l,
                //                      Toast.LENGTH_LONG).show();
                Intent myIntent = new Intent(view.getContext(), ArticleActivity.class);
                myIntent.putExtra("titre", listeArticles.get(i).getTitle());
                myIntent.putExtra("body", listeArticles.get(i).getBody());
                startActivity(myIntent);
            }
        });

        new DownloadTask().execute("http://jsonplaceholder.typicode.com/posts?userId=1") ;
    }
    void affiche(List<Post> liste)
    {
        listeArticles = liste ;
        List<String> listeTitres = new ArrayList<> () ; ;

        if (liste != null)
        {
            for (Post item : liste) listeTitres.add (item.getTitle ()) ;
        }
        ArrayAdapter<String> listeTitresAdapter = new ArrayAdapter<> (this,android.R.layout.simple_list_item_1,listeTitres) ;
        listView.setAdapter(listeTitresAdapter);
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
