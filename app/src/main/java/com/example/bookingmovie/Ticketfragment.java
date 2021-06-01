package com.example.bookingmovie;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Ticketfragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_ticket, container, false);
        List<Ticket> list = new ArrayList<>();
        list.add(new Ticket(R.drawable.tthm,"Thiên thần hộ mênh","C3,C3","Phòng 3","9H30-31/05/2021"));
        list.add(new Ticket(R.drawable.tthm,"Thiên thần hộ mênh","C3,C3","Phòng 1","9H30-31/05/2021"));
        list.add(new Ticket(R.drawable.tthm,"Thiên thần hộ mênh","C3,C3","Phòng 2","9H30-31/05/2021"));
        list.add(new Ticket(R.drawable.tthm,"Thiên thần hộ mênh","C3,C3","Phòng 4","9H30-31/05/2021"));
        list.add(new Ticket(R.drawable.tthm,"Thiên thần hộ mênh","C3,C3","Phòng 6","9H30-31/05/2021"));
        list.add(new Ticket(R.drawable.tthm,"Thiên thần hộ mênh","C3,C3","Phòng 2","9H30-31/05/2021"));
        RecyclerView recyclerView = (RecyclerView) v.findViewById(R.id.rcv_ticket);
        TicketAdapter ticketAdapter = new TicketAdapter(getContext(),list);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),1));
        recyclerView.setAdapter(ticketAdapter);
        return v;
    }
}

