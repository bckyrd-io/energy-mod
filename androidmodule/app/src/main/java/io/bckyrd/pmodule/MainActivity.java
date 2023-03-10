package io.bckyrd.pmodule;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CardAdapter adapter;

    private int[] icons = {R.drawable.icon_distribute, R.drawable.icon_notification, R.drawable.icon_switch, R.drawable.icon_statistics};
    private static String[] headings = {"DISTRIBUTE", "NOTIFICATION", "SWITCH", "STATISTICS"};
    private String[] subDescriptions = {"who receives power", "activities time line", "power plants state", "current station voltage"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CardAdapter();
        recyclerView.setAdapter(adapter);
    }

    private class CardAdapter extends RecyclerView.Adapter<CardViewHolder> {

        @NonNull
        @Override
        public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout, parent, false);
            return new CardViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull CardViewHolder holder, int position) {
            holder.icon.setImageResource(icons[position]);
            holder.heading.setText(headings[position]);
            holder.subDescription.setText(subDescriptions[position]);
        }

        @Override
        public int getItemCount() {
            return icons.length;
        }
    }


    private static class CardViewHolder extends RecyclerView.ViewHolder {

        ImageView icon;
        TextView heading;
        TextView subDescription;

        public CardViewHolder(@NonNull View itemView) {
            super(itemView);
            icon = itemView.findViewById(R.id.card_image);
            heading = itemView.findViewById(R.id.card_title);
            subDescription = itemView.findViewById(R.id.card_subtitle);

            itemView.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    Class<? extends Activity> activityClass = null;
                    String heading = headings[position];
                    switch (position) {
                        case 0:
                            activityClass = DistributeActivity.class;
                            break;
                        case 1:
                            activityClass = NotificationActivity.class;
                            break;
                        case 2:
                            activityClass = SwitchActivity.class;
                            break;
                        case 3:
                            activityClass = StatisticsActivity.class;
                            break;
                    }
                    if (activityClass != null) {
                        Intent intent = new Intent(v.getContext(), activityClass);
                        intent.putExtra("heading", heading);
                        v.getContext().startActivity(intent);
                    }
                }
            });
        }
    }

}
