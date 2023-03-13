package io.bckyrd.pmodule;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class NotificationActivity extends AppCompatActivity {
    private ListView noteListView;
    private NoteAdapter noteAdapter;

    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        // Add these two lines to show the title and the back arrow
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Notification");

        noteListView = findViewById(R.id.note_list_view);
        noteAdapter = new NoteAdapter(this, new ArrayList<Note>());

        noteListView.setAdapter(noteAdapter);



        // Get a reference to the Firebase database
        databaseReference = FirebaseDatabase.getInstance().getReference();

        // Add dummy data to the database
        /**
        Note note1 = new Note("Note 1", "2022-03-12 10:00:00");
        String note1Id = databaseReference.child("notes").push().getKey();
        databaseReference.child("notes").child(note1Id).setValue(note1);

        Note note2 = new Note("Note 2", "2022-03-12 11:00:00");
        String note2Id = databaseReference.child("notes").push().getKey();
        databaseReference.child("notes").child(note2Id).setValue(note2);
         */

        // Read the notes from the database and add them to the list





        // Read the notes from the database and add them to the list
        databaseReference.child("notes").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<Note> noteList = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Note note = snapshot.getValue(Note.class);
                    noteList.add(note);
                }
                noteAdapter.clear();
                noteAdapter.addAll(noteList);
                noteAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w("NotificationActivity", "loadNotes:onCancelled", databaseError.toException());
            }
        });
    }
}

class NoteAdapter extends BaseAdapter {
    private List<Note> notes;
    private Context context;

    public NoteAdapter(Context context, List<Note> notes) {
        this.context = context;
        this.notes = notes;
    }

    @Override
    public int getCount() {
        return notes.size();
    }

    @Override
    public Object getItem(int position) {
        return notes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.note_row, parent, false);
        }

        Note note = notes.get(position);

        TextView noteTxt = convertView.findViewById(R.id.note_txt);
        TextView noteTime = convertView.findViewById(R.id.note_time);

        // Set the text and time for the note
        noteTxt.setText(note.getText());
        noteTime.setText(note.getTime());
        return convertView;
    }

    public void clear() {
        notes.clear();
    }

    public void addAll(List<Note> notes) {
        this.notes.addAll(notes);
    }

}



class Note {
    private String text;
    private String time;

    public Note() {
    }

    public Note(String text, String time) {
        this.text = text;
        this.time = time;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}