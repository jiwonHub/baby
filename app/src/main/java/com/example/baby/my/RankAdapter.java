package com.example.baby.my;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baby.R;

import java.util.List;

public class RankAdapter extends RecyclerView.Adapter<RankAdapter.ViewHolder> {

    private final List<RankModel> rankList;

    public RankAdapter(List<RankModel> rankList){
        this.rankList = rankList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rank, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        RankModel rankItem = rankList.get(position);
        int rank = position +1;
        holder.bind(rankItem, rank);
    }

    @Override
    public int getItemCount() {
        return rankList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView rankNameTextView;
        private final TextView rankTextView;
        private final TextView rankPointTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            rankNameTextView = itemView.findViewById(R.id.rankUserName);
            rankTextView = itemView.findViewById(R.id.rank);
            rankPointTextView = itemView.findViewById(R.id.rankPoint);
        }

        public void bind(RankModel item, int rank) {
            rankNameTextView.setText(item.getUserName());
            rankTextView.setText(String.valueOf(rank));
            rankPointTextView.setText(String.valueOf(item.getRankPoint()));
        }
    }
}
