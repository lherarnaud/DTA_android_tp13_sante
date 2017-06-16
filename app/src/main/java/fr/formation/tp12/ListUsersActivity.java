package fr.formation.tp12;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import fr.formation.tp12.database.datasource.DataSource;
import fr.formation.tp12.database.modele.User;

public class ListUsersActivity extends AppCompatActivity {

    private DataSource<User> dataSource;
    private List<User> utilisateurs = new ArrayList<>();

    private int versionDB = 2;
    private RecyclerView recycleView;
    private RecyclerView.Adapter recycleViewAdapter;

    // Pour quitter l'application :
    private Toast toast;
    private long lastBackPressTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_users);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        recycleView = (RecyclerView) findViewById(R.id.ctl_rcy_listUsers);

        // specify an adapter (see also next example)
        recycleViewAdapter = new UserAdapter(utilisateurs, R.layout.item_user);
        recycleView.setAdapter(recycleViewAdapter);

        // use a linear layout manager
        recycleView.setLayoutManager(new LinearLayoutManager(this));


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.ctl_btn_addUser);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ListUsersActivity.this, AddUserActivity.class);
                startActivityForResult(i, 2);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        try {
            if (dataSource == null) {
                dataSource = new DataSource<>(this, User.class, versionDB);
                dataSource.open();
            }
        } catch (Exception e) {
            // Traiter le cas !
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            dataSource.close();
        } catch (Exception e) {
            // Traiter le cas !
            e.printStackTrace();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        chargerUtilisateurs();
    }

    private void chargerUtilisateurs() {
        // On charge les données depuis la base.
        try {
            List<User> users = dataSource.readAll();
            utilisateurs.clear();
            utilisateurs.addAll(users);
        } catch (Exception e) {
            // Que faire ?
            e.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == 2) {

            String flux = data.getStringExtra("utilisateur"); // Tester si pas null ;-)
            User utilisateur = new Gson().fromJson(flux, User.class);

            try {
                dataSource.insert(utilisateur);
            } catch (Exception e) {
                // Que faire :-(
                e.printStackTrace();
            }

            // Indiquer un changement au RecycleView
            chargerUtilisateurs();
            recycleViewAdapter.notifyDataSetChanged();

        }
    }

    @Override
    public void onBackPressed() {
        if (this.lastBackPressTime < System.currentTimeMillis() - 4000) {
            toast = Toast.makeText(this, "Encore !!", Toast.LENGTH_LONG);
            toast.show();
            this.lastBackPressTime = System.currentTimeMillis();
        } else {
            if (toast != null) {
                toast.cancel();
            }
            toast = Toast.makeText(this, "Bye bye !", Toast.LENGTH_LONG);
            toast.show();
            super.onBackPressed();
            this.finish();
            System.exit(0);
        }
    }
}
