package com.geektech.mytaskapp.ui.chat;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.geektech.mytaskapp.R;
import com.geektech.mytaskapp.adapters.ChatAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ChatFragment extends Fragment {

    EditText editText;
    ImageView imageBtn;
    ArrayList<String> chat;
    ChatAdapter chatAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_chat, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        editText = view.findViewById(R.id.editText);
        imageBtn = view.findViewById(R.id.btnSend);
        final RecyclerView recyclerView = view.findViewById(R.id.recyclerViewChat);
        chat = new ArrayList<>();
        chatAdapter = new ChatAdapter(chat);
        recyclerView.setAdapter(chatAdapter);

        imageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userId = FirebaseAuth.getInstance().getUid();
                String message = editText.getText().toString().trim();
                if (!message.isEmpty()) {
                    Map<String, String> map = new HashMap<>();
                    map.put("message", message);
                    map.put("id",FirebaseAuth.getInstance().getUid());
                    FirebaseFirestore.getInstance().collection("chat").add(map).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentReference> task) {
                            Toast.makeText(requireContext(), "result " + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });

        FirebaseFirestore.getInstance().collection("chat")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot snapshots,
                                        @Nullable FirebaseFirestoreException e) {
                        if (e != null) {
                            Log.w("TAG", "listen:error", e);
                            return;
                        }
                        for (DocumentChange dc : snapshots.getDocumentChanges()) {
                            switch (dc.getType()) {
                                case ADDED:
                                    String m = (String) dc.getDocument().getData().get("message");
                                    chat.add(m);
                                    recyclerView.scrollToPosition(chat.size()-1);
                                    chatAdapter.notifyDataSetChanged();
                                    editText.getText().clear();
                                    break;

                            }
                        }

                    }
                });
    }
}