package vn.edu.greenwich.cw_1_sample.ui.trip.list;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;
import vn.edu.greenwich.cw_1_sample.R;
import vn.edu.greenwich.cw_1_sample.database.ResimaDAO;
import vn.edu.greenwich.cw_1_sample.models.Trip;

public class TripListFragment extends Fragment {
    protected ArrayList<Trip> tripList = new ArrayList<>();

    protected ResimaDAO db;
    protected TextView fmTripListEmptyNotice;
    protected RecyclerView fmTripListRecyclerView;

    public TripListFragment() {}

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        db = new ResimaDAO(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_trip_list, container, false);

        tripList = db.getTripList(null, null, false);

        fmTripListRecyclerView = view.findViewById(R.id.fmTripListRecyclerView);
        fmTripListEmptyNotice = view.findViewById(R.id.fmTripListEmptyNotice);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(), linearLayoutManager.getOrientation());

        fmTripListRecyclerView.addItemDecoration(dividerItemDecoration);
        fmTripListRecyclerView.setAdapter(new TripAdapter(tripList));
        fmTripListRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Show "No trip." message.
        fmTripListEmptyNotice.setVisibility(tripList.isEmpty() ? View.VISIBLE : View.GONE);

        return view;
    }
}