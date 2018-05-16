package com.example.yuhaolu.behancedisplay.view.search_screen;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yuhaolu.behancedisplay.R;
import com.example.yuhaolu.behancedisplay.model.User_;

import java.util.ArrayList;
import java.util.List;

public class SearchUserAdapter extends RecyclerView.Adapter<SearchUserViewHolder> {

    private Context context;
    private List<User_> users;

    public SearchUserAdapter(Context context, List<User_> users) {
        this.context = context;
        this.users = users;
    }

    @Override
    public SearchUserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.discover_user_item, null);
        return new SearchUserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SearchUserViewHolder holder, int position) {
        final User_ user = users.get(position);
        holder.userPicture.setImageURI(Uri.parse(user.images._50));
        holder.userName.setText(String.valueOf(user.displayName));
        holder.userField.setText(TextUtils.join(" & ", user.fields));
    }

    @Override
    public int getItemCount() {
        return users != null ? users.size() : 0;
    }

    public void appendUser(ArrayList<User_> addUsers) {
        users.addAll(addUsers);
        notifyDataSetChanged();
    }

    public void clearUser() {
        users.clear();
        notifyDataSetChanged();
    }
}
