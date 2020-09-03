package com.example.todonotes.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.todonotes.Database.NotesDatabase;
import com.example.todonotes.R;
import com.example.todonotes.adapter.NotesAdapter;
import com.example.todonotes.entity.Note;
import com.example.todonotes.testActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final int REQUEST_CODE_ADD_NOTE =1;
    private RecyclerView recyclerView;
    private List<Note> noteList;
    private NotesAdapter notesAdapter;
    ImageView imageAddNoteMain;
    LottieAnimationView imageInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        setRecyclerView();


        setOnclickListener();




        getNotes();
    }

    private void setOnclickListener(){

        imageAddNoteMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(
                        new Intent(getApplicationContext(),CreateNoteActivity.class),
                        REQUEST_CODE_ADD_NOTE
                );
            }
        });


        imageInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




                startActivity(new Intent(getApplicationContext(),InfoActivity.class));


//                                startActivity(new Intent(getApplicationContext(), testActivity.class));
            }
        });


//        imageInfo1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                                startActivity(new Intent(getApplicationContext(),InfoActivity.class));
//            }
//        });
    }
    private void setRecyclerView() {
        recyclerView.setLayoutManager(
                new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
        );
    }

    private void init() {
        imageAddNoteMain= findViewById(R.id.imageAddNoteMain);
        imageInfo = findViewById(R.id.imageInfo);

        recyclerView = findViewById(R.id.notesRecyclerView);
        noteList = new ArrayList<>();
        notesAdapter = new NotesAdapter(noteList);
        recyclerView.setAdapter(notesAdapter);
    }


    private void getNotes(){

    @SuppressLint("StaticFieldLeak")
    class GetNote extends AsyncTask<Void, Void, List<Note>> {

        @Override
        protected List<Note> doInBackground(Void... voids) {
            return NotesDatabase.getNotesDatabase(getApplicationContext()).noteDao().getAllNotes();


        }

        @Override
        protected void onPostExecute(List<Note> notes) {
            super.onPostExecute(notes);
            if(noteList.size()==0){
                noteList.addAll(notes);
                notesAdapter.notifyDataSetChanged();

            }else{
                noteList.add(0,notes.get(0));
                notesAdapter.notifyItemInserted(0);
            }
            recyclerView.smoothScrollToPosition(0);

        }
    }
    new GetNote().execute();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE_ADD_NOTE && resultCode==RESULT_OK){
            getNotes();
        }
    }
}