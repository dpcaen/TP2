package fr.ensicaen.dp.tp2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class PostAdapter extends ArrayAdapter<Post> {

    private ArrayList<Post> articles ;

    public PostAdapter (Context context, ArrayList<Post> articles)
    {
        super (context,0,articles) ;
        this.articles = articles ;
    }

    @Override
    public int getCount ()
    {
        return articles.size() ;
    }

    public View getView (int position, View convertView, ViewGroup parent) {
        Post article = getItem (position) ;

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate (R.layout.activity_article, parent, false) ;
        }

        TextView title = (TextView) convertView.findViewById(R.id.titreView) ;
        TextView body = (TextView) convertView.findViewById(R.id.bodyView) ;
        title.setText(article.getTitle());
        body.setText(article.getBody());

        return convertView ;
    }
}
