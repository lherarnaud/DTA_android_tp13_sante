package fr.formation.tp12;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;

import fr.formation.tp12.database.datasource.DataSource;
import fr.formation.tp12.database.modele.User;

public class AddUserActivity extends AppCompatActivity {

    DataSource<User> dataSource;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_user_edit);

        final EditText newName = (EditText) findViewById(R.id.userName);
        Button addButton = (Button) findViewById(R.id.addUser);

        addButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                User utilisateur = new User();
                utilisateur.setNom(newName.getText().toString());
                // Transformation en JSON :
                String flux = (new Gson()).toJson(utilisateur);
                Log.d("Utilisateur en JSON", flux);

                // On dépose notre utilisateur jsonné dans l'intent
                Intent resultIntent = new Intent();
                resultIntent.putExtra("utilisateur", flux);
                setResult(2, resultIntent);

                // Bye l'activité
                finish();
            }
        });

//        // Create or retrieve the database
//        try {
//            dataSource = new DataSource<>(this, User.class);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        // open the database
//        openDB();

       /*
        // Insert a new record
        // -------------------
        User user = new User();
        user.setNom("Tintin");
        try {
            insertRecord(user);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // update that line
        // ----------------
        try {
            user.setNom("Bidochon");
            updateRecord(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        */

        // Query that line
        // ---------------
        //queryTheDatabase();

        // And then delete it:
        // -------------------
        //deleteRecord(user);
    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        openDB();
//    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//        closeDB();
//    }
//
//    public void openDB() throws SQLiteException {
//        dataSource.getDB();
//    }
//
//    public void closeDB() {
//        dataSource.close();
//    }
//
//    private long insertRecord(User user) throws Exception {
//
//        // Insert the line in the database
//        long rowId = dataSource.insert(user);
//
//        // Test to see if the insertion was ok
//        if (rowId == -1) {
//            Toast.makeText(this, "Error when creating an User",
//                    Toast.LENGTH_LONG).show();
//        } else {
//            Toast.makeText(this, "User created and stored in database",
//                    Toast.LENGTH_LONG).show();
//        }
//        return rowId;
//    }
//
//    /**
//     * * Update a record
//     *
//     * @return the updated row id
//     */
//    private long updateRecord(User user) throws Exception {
//
//        int rowId = dataSource.update(user);
//
//        // test to see if the insertion was ok
//        if (rowId == -1) {
//            Toast.makeText(this, "Error when updating an User",
//                    Toast.LENGTH_LONG).show();
//        } else {
//            Toast.makeText(this, "User updated in database", Toast.LENGTH_LONG)
//                    .show();
//        }
//        return rowId;
//    }
//
//    private void deleteRecord(User user) {
//        long rowId = dataSource.delete(user);
//        if (rowId == -1) {
//            Toast.makeText(this, "Error when deleting an User",
//                    Toast.LENGTH_LONG).show();
//        } else {
//            Toast.makeText(this, "User deleted in database", Toast.LENGTH_LONG)
//                    .show();
//        }
//    }
//
//    /**
//     * Query a the database
//     */
//    private void queryTheDatabase() {
//        List<User> users = dataSource.readAll();
//        displayResults(users);
//    }
//
//    private void displayResults(List<User> users) {
//
//        int count = 0;
//        for (User user : users
//                ) {
////            Toast.makeText(
////                    this,
////                    "Utilisateur :" + user.getNom() + "("
////                            + user.getId() + ")", Toast.LENGTH_LONG).show();
//            count++;
//        }
//        Toast.makeText(this,
//                "The number of elements retrieved is " + count,
//                Toast.LENGTH_LONG).show();
//
//    }
}
