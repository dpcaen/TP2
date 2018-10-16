package fr.ensicaen.dp.tp2;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class JsonUtils {
    private static JsonUtils instance = null; private Gson gson;
    private JsonUtils() {
        gson = new GsonBuilder().create(); }
    public static JsonUtils getInstance() { if (instance == null) {
        instance = new JsonUtils(); }
        return instance; }
    public static List<Post> parsePosts(String json) throws JsonSyntaxException { Type listType = new TypeToken<List<Post>>(){}.getType();
        return getInstance().gson.fromJson(json, listType);
    } }