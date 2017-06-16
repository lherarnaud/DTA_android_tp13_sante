package fr.formation.tp12;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import fr.formation.tp12.database.modele.User;

/**
 * Created by admin on 14/06/2017.
 */

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

    private List<User> dataset;
    private int itemLayout;

    public UserAdapter(List<User> dataset, int itemLayout) {
        this.dataset = dataset;
        this.itemLayout = itemLayout;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(itemLayout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position)
    {
        User user = dataset.get(position);

        holder.ctl_txt_id.setText(String.valueOf(user.getId()));
        holder.ctl_txt_username.setText(user.getNom());
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        // each data item contains UI items (id#, user)
        public TextView ctl_txt_id;
        public TextView ctl_txt_username;

        public ViewHolder(View itemView) {
            super(itemView);
            ctl_txt_id = (TextView) itemView.findViewById(R.id.userId);
            ctl_txt_username = (TextView) itemView.findViewById(R.id.userName);
        }
    }

}
