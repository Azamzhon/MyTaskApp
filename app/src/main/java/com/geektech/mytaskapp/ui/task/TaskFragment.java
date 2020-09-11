package com.geektech.mytaskapp.ui.task;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.geektech.mytaskapp.App;
import com.geektech.mytaskapp.R;
import com.geektech.mytaskapp.adapters.TaskAdapter;
import com.geektech.mytaskapp.models.Task;
import com.geektech.mytaskapp.ui.interfaces.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

public class TaskFragment extends Fragment {

    private TaskAdapter adapter;
    ArrayList<Task> list;
    boolean isTrue;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_task, container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_home, menu);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initList(view);

    }

    private void initList(View view) {
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        list = new ArrayList<>();
        App.getInstance().getDatabase().taskDao().getAllLive().observe(getViewLifecycleOwner(), new Observer<List<Task>>() {
            @Override
            public void onChanged(List<Task> tasks) {
                list.clear();
                list.addAll(App.getInstance().getDatabase().taskDao().getAll());
                adapter.notifyDataSetChanged();
            }
        });
        adapter = new TaskAdapter(list);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItem(int position) {
                Task task = list.get(position);
                openForm(task);
            }

            @Override
            public void onItemLongClick(int position) {
                showAlert(list.get(position));
            }

            private void showAlert(final Task task) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
                builder.setMessage("Delete " + task.getTitle() + " ?");
                builder.setNegativeButton("Cancel", null);
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        App.getInstance().getDatabase().taskDao().delete(task);
                    }
                });
                builder.show();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_add) {
            openForm(null);
        }
        if (item.getItemId()==R.id.action_sort_data){
            if (!isTrue) {
                list.clear();
                list.addAll(App.getInstance().getDatabase().taskDao().getAllSort());
                adapter.notifyDataSetChanged();
                isTrue = true;
            }else{
                list.clear();
                list.addAll(App.getInstance().getDatabase().taskDao().getAll());
                isTrue = false;
                adapter.notifyDataSetChanged();
            }
        }

        if (item.getItemId()==R.id.action_sort_title){
            if (!isTrue) {
                list.clear();
                list.addAll(App.getInstance().getDatabase().taskDao().getAllTitleSort());
                adapter.notifyDataSetChanged();
                isTrue = true;
            }else{
                list.clear();
                list.addAll(App.getInstance().getDatabase().taskDao().getAllTitlesSort());
                isTrue = false;
                adapter.notifyDataSetChanged();
            }
        }

        if (item.getItemId() == R.id.action_clear) {
            App.getInstance().getDatabase().taskDao().deleteAll();
            adapter.notifyDataSetChanged();
        }
        return super.onOptionsItemSelected(item);
    }

    private void openForm(Task task) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("task", task);
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
        navController.navigate(R.id.action_navigation_task_to_formFragment, bundle);
    }
}