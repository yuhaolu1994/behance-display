package com.example.yuhaolu.behancedisplay.behance;

import android.util.Log;

import com.example.yuhaolu.behancedisplay.model.Comment;
import com.example.yuhaolu.behancedisplay.model.Comment_;
import com.example.yuhaolu.behancedisplay.model.Creatives;
import com.example.yuhaolu.behancedisplay.model.CreativesToFollow;
import com.example.yuhaolu.behancedisplay.model.Project;
import com.example.yuhaolu.behancedisplay.model.ProjectDetail;
import com.example.yuhaolu.behancedisplay.model.Shot;
import com.example.yuhaolu.behancedisplay.model.ShotDetail;
import com.example.yuhaolu.behancedisplay.utils.ModelUtils;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Behance {

    private static final String API_URL = "https://api.behance.net/v2";
    private static final String PROJECTS_END_POINT = API_URL + "/projects";
    private static final String CREATIVE_END_POINT = API_URL + "/creativestofollow";
    private static final String API_KEY = "cSD5E5zX5yi4CUaCPSz9G9CJq50tB3SW";
    private static final TypeToken<Shot> SHOT_LIST_TYPE = new TypeToken<Shot>(){};
    private static final TypeToken<ShotDetail> SHOT_DETAIL_TYPE = new TypeToken<ShotDetail>(){};
    private static final TypeToken<Comment> SHOT_COMMENT_TYPE = new TypeToken<Comment>(){};
    private static final TypeToken<Creatives> CREATIVES_LIST_TYPE = new TypeToken<Creatives>(){};

    public static final int ITEM_COUNT_PER_PAGE = 48;
    public static final int COMMENT_COUNT_PER_PAGE = 10;

    private static final OkHttpClient client = new OkHttpClient();

    private static Request.Builder requestBuilder(String url) {
        return new Request.Builder().url(url);
    }

    private static Response makeRequest(Request request) {
        try {
            Response response = client.newCall(request).execute();
            return response;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static Response makeGetRequest(String url) {
        Request request = requestBuilder(url).build();
        return makeRequest(request);
    }

    private static <T> T parseResponse(Response response, TypeToken<T> typeToken) {
        String responseString = null;
        try {
            responseString = response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.i("RS", responseString);
        return ModelUtils.toObject(responseString, typeToken);
    }

    public static List<Project> getShots(int page, String cityName) {
        String url = PROJECTS_END_POINT  + "?page=" + page + "&city=" + cityName + "&client_id=" + API_KEY;
        Shot shot = parseResponse(makeGetRequest(url), SHOT_LIST_TYPE);
        return shot.projects;
    }

    public static ProjectDetail getProjectDetails(int id) {
        String url = PROJECTS_END_POINT + "/" + id + "?client_id=" + API_KEY;
        ShotDetail shotDetail = parseResponse(makeGetRequest(url), SHOT_DETAIL_TYPE);
        return shotDetail.projectDetail;
    }

    public static List<Comment_> getComments(String id, int page) {
        String url = PROJECTS_END_POINT + "/" + id + "/comments?page=" + page + "&client_id=" + API_KEY;
        Comment comment = parseResponse(makeGetRequest(url), SHOT_COMMENT_TYPE);
        return comment.comments;
    }

    public static List<Project> getHomeProjects(String field) {
        String url = PROJECTS_END_POINT  + "?field=" + field + "&page=1&sort=appreciations&client_id=" + API_KEY;
        Shot shot = parseResponse(makeGetRequest(url), SHOT_LIST_TYPE);
        return shot.projects;
    }

    public static List<CreativesToFollow> getCreative() {
        String url = CREATIVE_END_POINT + "?client_id=" + API_KEY;
        Creatives creatives = parseResponse(makeGetRequest(url), CREATIVES_LIST_TYPE);
        Log.i("URL", url);
        return creatives.creativesToFollow;
    }

}
