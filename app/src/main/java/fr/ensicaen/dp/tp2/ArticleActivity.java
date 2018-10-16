package fr.ensicaen.dp.tp2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ArticleActivity extends AppCompatActivity {

    private TextView titreView ;
    private TextView bodyView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);

        titreView = (TextView) findViewById(R.id.titreView) ;
        bodyView  = (TextView) findViewById(R.id.bodyView) ;

        Bundle bundle = getIntent ().getExtras() ;
        titreView.setText(bundle.getString("titre"));
        bodyView.setText(bundle.getString("body"));

    }
}
