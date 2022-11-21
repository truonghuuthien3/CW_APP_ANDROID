package vn.edu.greenwich.cw_1_sample.ui.trip.list;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import vn.edu.greenwich.cw_1_sample.R;
import vn.edu.greenwich.cw_1_sample.models.Trip;
import vn.edu.greenwich.cw_1_sample.ui.trip.TripDetailFragment;

public class TripAdapter extends RecyclerView.Adapter<TripAdapter.ViewHolder> {
    protected ArrayList<Trip> trip;

    public TripAdapter(ArrayList<Trip> list) {
        trip = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_item_trip, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Trip trip = this.trip.get(position);

        String require = holder.itemView.getResources().getString(R.string.label_requires_risk_assessment);
        String notRequire = holder.itemView.getResources().getString(R.string.label_unrequire_risk_assessment);

        holder.listItemTripName.setText(trip.getTripName());
        holder.listItemTripDate.setText(trip.getTripDate());
        holder.listItemRequiresRiskAssessment.setText(trip.getRequiresRiskAssessment() == 1 ? require : notRequire);
    }

    @Override
    public int getItemCount() {
        return trip == null ? 0 : trip.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        protected LinearLayout listItemTrip;
        protected TextView listItemTripName, listItemTripDate, listItemRequiresRiskAssessment;

        public ViewHolder(View itemView) {
            super(itemView);

            listItemTripName = itemView.findViewById(R.id.listItemTripName);
            listItemTripDate = itemView.findViewById(R.id.listItemTripDate);
            listItemRequiresRiskAssessment = itemView.findViewById(R.id.listItemRequiresRiskAssessment);

            listItemTrip = itemView.findViewById(R.id.listItemTrip);
            listItemTrip.setOnClickListener(v -> showDetail(v));
        }

        protected void showDetail(View view) {
            Trip trip = TripAdapter.this.trip.get(getAdapterPosition());

            Bundle bundle = new Bundle();
            bundle.putSerializable(TripDetailFragment.ARG_PARAM_TRIP, trip);

            Navigation.findNavController(view).navigate(R.id.tripDetailFragment, bundle);
        }
    }
}